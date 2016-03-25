package main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

import seasons.BlockArrayIO;
import seasons.Season;
import seasons.SeasonListener;
import spoiling.PlayerSpoilageUpdater;
import spoiling.SpoilListener;

import com.massivecraft.massivecore.MassivePlugin;
import commands.CmdMCDSCommand;



public class MinecraftDontStarve extends MassivePlugin {

	public CmdMCDSCommand outerCommand;

	public static World defaultWorld;
	
	public static List<BlockArrayIO.Datum> original_biomes;
	
	
	public static Season current_season = Season.AUTUMN;
	public static final int ticksPerMinecraftDay = 24000; // default: 24000
	public static final int maxLifeTimeInMinecraftDays = 10000; // Ultimate maximum lifetime of a food item, default: 10000
	public static final int delayBetweenPlayerSpoilageChecks = 100; // Time in ticks between spoilage-checking all player invs
	
	public final SeasonListener seasonListener = new SeasonListener(this);
	public final SpoilListener spoilListener = new SpoilListener(this);
	
	
	public static MinecraftDontStarve getCurrentPlugin() {
//		return currentPlugin; 
		return (MinecraftDontStarve) Bukkit.getServer().getPluginManager().getPlugin("MinecraftDontStarve"); 
	}
	
	@Override
	public void onEnable() {
//		ItemStack rum = new ItemStack(Material.MILK_BUCKET, 1);
//		ItemMeta meta = rum.getItemMeta();
//		meta.setDisplayName("Lol Juice");
//		rum.setItemMeta(meta);
//		
//		ShapedRecipe craftRum = new ShapedRecipe(rum);
//		craftRum.shape("%%%","%*%","%%%");
//		craftRum.setIngredient('%', Material.EMERALD);
//		craftRum.setIngredient('*', Material.BUCKET);
//		getServer().addRecipe(craftRum);
		
		
		defaultWorld = Bukkit.getServer().getWorld("world");

		defaultWorld.getWorldBorder().setCenter(defaultWorld.getSpawnLocation());
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.seasonListener, this);
		pm.registerEvents(this.spoilListener, this);
		
		PlayerSpoilageUpdater spoilage_updater = new PlayerSpoilageUpdater();
		spoilage_updater.runTaskTimer(this, 20, delayBetweenPlayerSpoilageChecks);
		
		outerCommand = new CmdMCDSCommand();
		//outerCommand.register(getCurrentPlugin());
		this.activate(outerCommand);
	}
}
