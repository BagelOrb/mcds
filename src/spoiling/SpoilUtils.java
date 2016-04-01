package spoiling;

import java.util.ArrayList;
import java.util.List;

import main.Debug;
import main.MinecraftDontStarve;

import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import spoiling.FreshnessStages.Freshness;

import com.massivecraft.massivecore.util.Txt;

public class SpoilUtils {
	
	static long timeFromString(String in)
	{
		return Long.parseLong(in);
	}
	public static boolean isLong(String s) {
	    try { 
	        Long.parseLong(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	static long getTimeInMinecraftDays()
	{
		return Math.round(MinecraftDontStarve.defaultWorld.getFullTime()/MinecraftDontStarve.ticksPerMinecraftDay);
	}

	public static void setLore(ItemStack item, List<String> lines)
	{
		ItemMeta meta = item.getItemMeta();
		if (meta == null)
		{
			Debug.out("WARNING: tries to set lore of item without meta!");
			return;
		}
		meta.setLore(lines);		
		item.setItemMeta(meta);
	}
	
	public static void removeTimeOfBirthLore(ItemStack item)
	{
		setLore(item, new ArrayList<String>());
	}	
	
	public static long getTimeOfBirth(ItemStack item)
	{
		if (item == null)
		{
			return -1;
		}
		ItemMeta meta = item.getItemMeta();
		if (meta == null)
		{
			return -1;
		}
		List<String> lore = meta.getLore();
		if (lore != null && lore.size() > 1)
		{
			String creationDay = lore.get(1).replace(Txt.parse("<info>Created on: <purple>Day "), "");
			if(isLong(creationDay))
			{
				return timeFromString(creationDay);
			}
		}
		return -1;
	}

	/*!
	 * return whether the item is updated
	 *
	 */
	public static boolean updateSpoilage(ItemStack item)
	{
		return updateSpoilage(item, 1.0f);
	}
	public static boolean updateSpoilage(ItemStack item, float new_freshness)
	{
		if (item == null || !SpoilingMaterials.isSpoilable(item))
		{
			return false;
		}
//		String str;
//		try {
//			throw new Exception();
//		} catch (Exception e) {
////			str = "Debug.out: "+e.getStackTrace()[1]+"\r\n\t\t\t:::"+str;
//			str = e.getStackTrace()[1].getFileName()+":"+e.getStackTrace()[1].getLineNumber()+" :>>> \t"+new_freshness;
//			
////			e.printStackTrace();
//		}
//		Bukkit.getServer().getConsoleSender().sendMessage(str);

		long time_of_birth = getTimeOfBirth(item);
		long time_now = getTimeInMinecraftDays();
		if (time_of_birth == -1)
		{
			Debug.out("no time of birth attached yet");
			long simulated_time_passed = (long) ((1.0f - new_freshness) * SpoilingMaterials.getFoodLifeTime(item)); 
			Debug.out("simulated_time_passed = " + simulated_time_passed + ", new_freshness = "+ new_freshness);
			time_of_birth = time_now - simulated_time_passed;
		}
		long age = time_now - time_of_birth;
		long life_time = SpoilingMaterials.getFoodLifeTime(item);
		if (age > life_time || age > MinecraftDontStarve.maxLifeTimeInMinecraftDays)
		{
			boolean decayed = SpoilingMaterials.decayItem(item);
			if (!decayed)
			{
				Debug.out("ERROR: " + item.getType().toString() + " couldnt decay!");
			}
			if (SpoilingMaterials.isSpoilable(item)) // after the item has been upted and could have become bonemeal...
			{
				Debug.out("item decayed to " + item.getType().toString());
				time_of_birth = time_now;
				age = 0;
				life_time = SpoilingMaterials.getFoodLifeTime(item);
			}
			else 
			{
				Debug.out("item became bonemeal");
				removeTimeOfBirthLore(item);
				return true;
			}
		}
		
		float percentage_gone = ((float) age) / life_time;
		
		List<String> new_lore = new ArrayList<String>();
		Freshness freshness = FreshnessStages.getFreshness(percentage_gone);
		new_lore.add(Txt.parse("<info>Freshness: " + freshness.toString() + " (" + (100 - (int) (percentage_gone * 100)) + "%)"));
		new_lore.add(Txt.parse("<info>Created on: <purple>Day " + time_of_birth));
		new_lore.add(Txt.parse("<info>Best before: <purple>Day " + (time_of_birth + life_time) + freshness.getColor() + " (" + (life_time - age) + " days left)"));
		new_lore.add(Txt.parse(""));
		new_lore.add(Txt.parse("<info>All food items will spoil over time!"));
		new_lore.add(Txt.parse("<info>Different food types spoil at different rates."));
		new_lore.add(Txt.parse("<lime>Fresh <info>food is healthier than <orange>stale<info> food."));
		setLore(item, new_lore);
		
		return true;
	}
	
	public static float getFreshnessPercentage(ItemStack item, boolean update_if_not_present)
	{
		long time_of_birth = getTimeOfBirth(item);
		long time_now = getTimeInMinecraftDays();
		if (time_of_birth == -1)
		{
			if (update_if_not_present)
			{
				updateSpoilage(item);
			}
			return 1.0f;
		}
		long time_diff = time_now - time_of_birth;
		long life_time = SpoilingMaterials.getFoodLifeTime(item);
		float percentage_gone = ((float) time_diff) / life_time;
		return 1.0f - percentage_gone;
	}

	public static void spoilInventory(Inventory inv) {
		spoilInventory(inv, 1.0f);
	}
	public static void spoilInventory(Inventory inv, float new_freshness) {
		ItemStack[] items = inv.getContents();
		if (CraftingInventory.class.isInstance(inv))
		{
			items = ((CraftingInventory) inv).getMatrix();
		}
		if (FurnaceInventory.class.isInstance(inv))
		{
			ItemStack[] items_here = { ((FurnaceInventory) inv).getSmelting() };
			items = items_here;
		}
		for (ItemStack item : items)
		{
			updateSpoilage(item, new_freshness);
		}
	}
}
