package seasons;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
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
		if (block.getY() > 96 && isExtremeHills(block.getBiome()))
		{
			if (block.getY() > 100 || random.nextBoolean())
			{
				return true;
			}
		}
		return false;
	}
	
	public static void makeIceOrSnow(World world, int x, int z)
	{
		Block block = world.getHighestBlockAt(x, z);
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
	public static void thaw(World world, int x, int z)
	{
		Block block = world.getHighestBlockAt(x, z);
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
