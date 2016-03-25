package seasons;

import org.bukkit.block.Biome;

public class SeasonBiomes {

	public static Biome getNewBiome(Biome original, Season season)
	{
		switch (original)
		{
		case DEEP_OCEAN:
		case DESERT:
		case SAVANNA:
		case MESA:
			return original;
		default:
			// execute normal code below
		}
		switch (season)
		{
		case SPRING:
			return original;
		case SUMMER:
			return Biome.DESERT;
		case AUTUMN:
			return original;
		case WINTER:
			return Biome.MUTATED_TAIGA_COLD;
		default:
			return original;	
		}
	}
}
