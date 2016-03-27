package seasons;

import java.util.Random;

import main.MinecraftDontStarve;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class SeasonBlockUtils {

	static Random random = new Random(121);
	
	public static boolean isExtremeHills(Biome biome)
	{
		switch(biome)
		{
		case EXTREME_HILLS:
		case EXTREME_HILLS_WITH_TREES:
		case MUTATED_EXTREME_HILLS:
		case MUTATED_EXTREME_HILLS_WITH_TREES:
		case SMALLER_EXTREME_HILLS:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean blockRemainsUnchanged(Block block)
	{		
		if (block.getY() > 92 && isExtremeHills(block.getBiome()))
		{
			if (block.getY() > 96 || random.nextBoolean())
			{
				return true;
			}
		}
		return false;
	}
	
	public static void makeIceOrSnow(int x, int z)
	{
		Block block = MinecraftDontStarve.defaultWorld.getHighestBlockAt(x, z);
		if (blockRemainsUnchanged(block))
		{
			return;
		}
		if (block.getType() == Material.AIR)
		{
			block.setType(Material.SNOW);					
		}
		if (block.getRelative(BlockFace.DOWN).getType() == Material.WATER)
		{
			block.getRelative(BlockFace.DOWN).setType(Material.ICE);			
		}
	}
	public static void thaw(int x, int z)
	{
		Block block = MinecraftDontStarve.defaultWorld.getHighestBlockAt(x, z);
		if (blockRemainsUnchanged(block))
		{
			return;
		}

		if (block.getType() == Material.SNOW)
		{
			block.setType(Material.AIR);					
		}
		if (block.getRelative(BlockFace.DOWN).getType() == Material.ICE)
		{
			block.getRelative(BlockFace.DOWN).setType(Material.WATER);			
		}
	}
}
