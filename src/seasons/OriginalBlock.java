package seasons;

import org.bukkit.World;
import org.bukkit.block.Biome;

public class OriginalBlock {

	String world;
	String originalBiome;
	int x, z;

	public OriginalBlock(World world, int x, int z, Biome originalBiome)
	{
		this.world = world.getName();
		this.originalBiome = originalBiome.toString();
		this.x = x;
		this.z = z;
	}
}
