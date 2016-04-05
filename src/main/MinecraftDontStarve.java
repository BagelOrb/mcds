package main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

import seasons.BlockArrayIO;
import seasons.Season;
import seasons.SeasonIO;
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
	
	public static int currentFileNumber = 0;
	public static boolean isCancelled = true;
	public static boolean isCurrentlyDoingASeasonTask = false;
	
	public static final String defaultSavePath = "plugins/minecraftdontstarve/saves/";
	public static final String seasonSaveFile = "season.txt";
	
	public static final int ticksPerMinecraftDay = 24000; // Default: 24000
	public static final int maxLifeTimeInMinecraftDays = 10000; // Ultimate maximum lifetime of a food item. Default: 10000
	public static final int delayBetweenPlayerSpoilageChecks = 200; // Time in ticks between spoilage-checking all player invs. Default: 100
	
	public static final double maxBorderSize = 10000; // Maximum size (length of a side) of the worldborder to still work with this plugin. Default: 10000
	public static final int batchSize = 500; // Number of columns in a batch. Default: 500
	public static final int ticksBetweenBatches = 2; // Number of ticks between each batch. Default: 1
	
	public final SeasonListener seasonListener = new SeasonListener(this);
	public final SpoilListener spoilListener = new SpoilListener(this);
	
	
	public static MinecraftDontStarve getCurrentPlugin() {
//		return currentPlugin; 
		return (MinecraftDontStarve) Bukkit.getServer().getPluginManager().getPlugin("MinecraftDontStarve"); 
	}
	
	@Override
	public void onEnable() {
		
		defaultWorld = Bukkit.getServer().getWorld("world");

		if(!SeasonIO.readSeasonFromFile())
		{
			Debug.out("ERROR: Couldn't read season from file!!!");
		}
		
		//defaultWorld.getWorldBorder().setCenter(defaultWorld.getSpawnLocation());
		
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
