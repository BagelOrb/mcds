package seasons;

import org.bukkit.block.Biome;

public class SeasonBiomes {

	public static boolean biomeRemainsUnchanged(Biome biome)
	{
		switch (biome)
		{
		case DEEP_OCEAN:
		case OCEAN:
			
		case DESERT:
		case DESERT_HILLS:
		case MUTATED_DESERT:
			
		case SAVANNA:
		case SAVANNA_ROCK:
		case MUTATED_SAVANNA:
		case MUTATED_SAVANNA_ROCK:
			
		case MESA:
		case MESA_CLEAR_ROCK:
		case MESA_ROCK:
		case MUTATED_MESA:
		case MUTATED_MESA_CLEAR_ROCK:
		case MUTATED_MESA_ROCK:
			
		case FROZEN_OCEAN:
		case FROZEN_RIVER:
		case ICE_FLATS:
		case ICE_MOUNTAINS:
		case MUTATED_ICE_FLATS:
			
		case COLD_BEACH:
		case MUTATED_TAIGA_COLD:
		case TAIGA_COLD:
		case TAIGA_COLD_HILLS:
			return true;
		default:
			return false;
		}
	}
	
	public static Biome getNewBiome(Biome original, Season season)
	{
		if (biomeRemainsUnchanged(original))
		{
			return original;
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
