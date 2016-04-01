package seasons;

import java.util.ArrayList;

import main.Debug;
import main.MinecraftDontStarve;

import org.bukkit.World;

public class SeasonChanger {
	
	public static void startSeason(World world, Season season) 
	{
		MinecraftDontStarve.current_season = season;
		
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
	
	public static boolean startNextFile(World world, Season season)
	{
		MinecraftDontStarve.original_biomes = new ArrayList<BlockArrayIO.Datum>();
		if(BlockArrayIO.readSingleFile(MinecraftDontStarve.original_biomes, world, MinecraftDontStarve.currentFileNumber))
		{
			Debug.out("Starting file "+MinecraftDontStarve.currentFileNumber+".");
			
			if (season == Season.SPRING || season == Season.WINTER)
			{
				new BlockSeasonChanger(world, MinecraftDontStarve.original_biomes, season, MinecraftDontStarve.batchSize / 2).runTaskTimer(MinecraftDontStarve.getCurrentPlugin(), 0, MinecraftDontStarve.ticksBetweenBatches);	
			}
			else
			{
				new BlockSeasonChanger(world, MinecraftDontStarve.original_biomes, season, MinecraftDontStarve.batchSize).runTaskTimer(MinecraftDontStarve.getCurrentPlugin(), 0, MinecraftDontStarve.ticksBetweenBatches);
			}
			
			MinecraftDontStarve.currentFileNumber++;
			return true;
		}
		else
		{
			MinecraftDontStarve.isCurrentlyDoingASeasonTask = false;
			MinecraftDontStarve.currentFileNumber = 0;
			return false;
		}
	}
	

}
