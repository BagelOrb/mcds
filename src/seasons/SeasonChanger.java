package seasons;

import java.io.File;

import main.MinecraftDontStarve;

import org.bukkit.World;

public class SeasonChanger {
	
	public static File baseSaveLocation = new File("plugins\\mcds\\saves\\");

	
	public static void startSeason(World world, Season season) 
	{
		MinecraftDontStarve.current_season = season;
		
		switch (season) {
		case SPRING:
			break;
		case SUMMER:
			break;
		case AUTUMN:
			break;
		case WINTER:
			world.setStorm(true);
			break;

		default:
			break;
		}
		
		if (season == Season.SPRING)
		{
			new BlockSeasonChanger(MinecraftDontStarve.defaultWorld, MinecraftDontStarve.original_biomes, season, 250).runTaskTimer(MinecraftDontStarve.getCurrentPlugin(), 0, 1);
			
		}
		else 
		{
			new BlockSeasonChanger(MinecraftDontStarve.defaultWorld, MinecraftDontStarve.original_biomes, season, 500).runTaskTimer(MinecraftDontStarve.getCurrentPlugin(), 0, 1);
			
		}
		

	}
	

}
