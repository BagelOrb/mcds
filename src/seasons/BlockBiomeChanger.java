package seasons;

import main.Debug;
import main.MinecraftDontStarve;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBiomeChanger extends BukkitRunnable {

	World world;
	Biome biome;
	int x, z;
	Block spawn;
	int world_border_radius;
	
	public BlockBiomeChanger(World world, Biome biome)
	{
		this.world = world;
		this.biome = biome;
		x = -world_border_radius; 
		z = -world_border_radius;
		world_border_radius = (int) (MinecraftDontStarve.defaultWorld.getWorldBorder().getSize() / 2);
		spawn = world.getBlockAt(MinecraftDontStarve.defaultWorld.getWorldBorder().getCenter());
		
	}
	
	@Override
	public void run() {
		
//		for (int x = -d; x < d; x++)
		{
			for (int n = 0; n < 500; n++)
			{
				
				if (z == world_border_radius)
				{
					Debug.out("" + x);
					z = -world_border_radius;
					x++;
				}
				Block block = spawn.getRelative(x, 0, z);
				if (block.getBiome() != Biome.OCEAN && block.getBiome() != Biome.DEEP_OCEAN)
				{
					block.setBiome(biome);					
				}
				z++;
			}
		}
		if (x >= world_border_radius && z >= world_border_radius)
		{
			this.cancel();
		}
		
	}

	
}
