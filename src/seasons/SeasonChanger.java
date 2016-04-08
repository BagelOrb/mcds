package seasons;

import java.util.ArrayList;
import java.util.List;

import main.Debug;
import main.MinecraftDontStarve;

import org.bukkit.World;

import com.massivecraft.massivecore.util.Txt;

public class SeasonChanger {
	
	public static void startSeason(World world, Season season) 
	{
		MinecraftDontStarve.current_season = season;
		if(!SeasonIO.writeSeasonToFile())
		{
			Debug.out("ERROR: Couldnt write seaon file!!!");
		}
		
		switch (season) {
		case SPRING:
			world.setStorm(false);
			break;
		case SUMMER:
			world.setStorm(false);
			break;
		case AUTUMN:
			break;
		case WINTER:
			world.setStorm(true);
			break;

		default:
			break;
		}
		
		MinecraftDontStarve.isCancelled = false;
		MinecraftDontStarve.isCurrentlyDoingASeasonTask = true;
		startNextFile(world, season);
	}
	
	public static void startNextFile(World world, Season season)
	{
		List<BlockArrayIO.Datum> original_biomes = new ArrayList<BlockArrayIO.Datum>();
		if(BlockArrayIO.readSingleFile(original_biomes, world, MinecraftDontStarve.currentFileNumber))
		{
			Debug.out("Starting file "+MinecraftDontStarve.currentFileNumber+".");
			
			if (season == Season.SPRING || season == Season.WINTER)
			{
				new BlockSeasonChanger(world, original_biomes, season, MinecraftDontStarve.batchSize / 2).runTaskTimer(MinecraftDontStarve.getCurrentPlugin(), 0, MinecraftDontStarve.ticksBetweenBatches);	
			}
			else
			{
				Debug.out(Txt.parse("Season fully changed to "+MinecraftDontStarve.current_season.toString()+"!"));
				MinecraftDontStarve.isCurrentlyDoingASeasonTask = false;
				MinecraftDontStarve.currentFileNumber = 0;
				return;
				//TODO: Enable this when it has some effect?
				//new BlockSeasonChanger(world, MinecraftDontStarve.original_biomes, season, MinecraftDontStarve.batchSize).runTaskTimer(MinecraftDontStarve.getCurrentPlugin(), 0, MinecraftDontStarve.ticksBetweenBatches);
			}
		}
		else
		{
			Debug.out("Couldn't start file "+MinecraftDontStarve.currentFileNumber+". Skipping it.");
		}
		
		MinecraftDontStarve.currentFileNumber++;
		
		if(MinecraftDontStarve.currentFileNumber > BlockArrayIO.countFiles(world))
		{
			MinecraftDontStarve.isCurrentlyDoingASeasonTask = false;
			MinecraftDontStarve.currentFileNumber = 0;
			Debug.out(Txt.parse("Season fully changed to "+MinecraftDontStarve.current_season.toString()+"!"));
		}
	}
	

}
