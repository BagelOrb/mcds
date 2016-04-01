package spoiling;

import main.MinecraftDontStarve;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class SpoilListener implements Listener {

	public SpoilListener(MinecraftDontStarve minecraftDontStarve) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onInvOpen(InventoryOpenEvent event)
	{
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onInvOpen");
		SpoilUtils.spoilInventory(event.getInventory());
		
	}

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onItemSpawn");
    	SpoilUtils.updateSpoilage(event.getEntity().getItemStack());	
    }
    
    //Crafting table
    @EventHandler
    public void onCraft(CraftItemEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onCraft");
    	CraftingInventory inv = event.getInventory();
    	float total_freshness = 0;
    	int n_items = 0;
    	for (ItemStack item : inv.getMatrix())
    	{
    		if (item != null && SpoilingMaterials.isSpoilable(item))
    		{
    			total_freshness += SpoilUtils.getFreshnessPercentage(item, false);
    			n_items++;
    		}
    	}
    	float result_freshness = 0.5f + 0.5f * total_freshness / n_items;
    	if(event.getClick().isShiftClick())
    	{
    		InventorySpoilageUpdater spoilage_updater = new InventorySpoilageUpdater(event.getWhoClicked().getInventory(), result_freshness);
    		spoilage_updater.runTaskLater(MinecraftDontStarve.getCurrentPlugin(), 1);
    	}
    	else
    	{
    		SpoilUtils.updateSpoilage(event.getCurrentItem(), result_freshness);
    	}
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onInventoryClick");
    }
    //Hoppers
    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onMoveItem");
    	
    	SpoilUtils.updateSpoilage(event.getItem());
    }
    
    //Furnace
    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onFurnaceSmelt");
    	ItemStack source = event.getSource();
    	if (!SpoilingMaterials.isSpoilable(source))
    	{
    		return;
    	}
    	float freshness = 0.5f + 0.5f * SpoilUtils.getFreshnessPercentage(source, true);
    	ItemStack result = event.getResult();
    	boolean changed = SpoilUtils.updateSpoilage(result, freshness);
    	if (changed)
    	{
    		event.setResult(result);
    	}
    	
    }
    
    @EventHandler
    public void onPlayerPicksUpItem(PlayerPickupItemEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onPlayerPicksUpItem");
    	SpoilUtils.updateSpoilage(event.getItem().getItemStack());
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
//    	Debug.out("onPlayerJoin");
    	SinglePlayerSpoilageUpdater spoilage_updater = new SinglePlayerSpoilageUpdater(event.getPlayer());
		spoilage_updater.runTaskLater(MinecraftDontStarve.getCurrentPlugin(), 100);
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event)
    {
    	if (event.isCancelled())
    	{
    		return;
    	}
//    	Debug.out("onEat");
    	Player player = event.getPlayer();
    	float freshness = SpoilUtils.getFreshnessPercentage(event.getItem(), true);
    	if (freshness < 0.5)
    	{
    		float loss = (0.25f + (1.0f - freshness * 2.0f) * 0.75f);
    		{
    			float current_sat = player.getSaturation();
	    		float sat_loss = loss * ((float) SpoilingMaterials.getSaturation(event.getItem()));
	    		
    			player.setSaturation(Math.max(0, current_sat - sat_loss));
    		}
    		{
    			int current_food = player.getFoodLevel();
	    		int food_loss = (int) (loss * ((float) SpoilingMaterials.getFoodRestored(event.getItem())));
	    		
    			player.setFoodLevel(Math.max(0, current_food - food_loss));
    		}
    		
    	}
    	
    }
}
