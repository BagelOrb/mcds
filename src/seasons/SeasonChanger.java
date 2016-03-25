package seasons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.MinecraftDontStarve;

import org.bukkit.World;

public class SeasonChanger {

	public static List<OriginalBlock> originalBlocks = new ArrayList<OriginalBlock>();
	
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
	public static float thunderChanceModifier()
	{
		switch (MinecraftDontStarve.current_season)
		{
		case SPRING:
			return 0.5f;
		case SUMMER:
			return 2.0f;
		case AUTUMN:
			return 1.0f;
		case WINTER:
			return 0.75f;
		default:
			return 1.0f;
		}
	}
	
	public static float rainChanceModifier()
	{
		switch (MinecraftDontStarve.current_season)
		{
		case SPRING:
			return 1.0f;
		case SUMMER:
			return 0.1f;
		case AUTUMN:
			return 4.0f;
		case WINTER:
			return 5.0f;
		default:
			return 1.0f;
		}
	}

}
