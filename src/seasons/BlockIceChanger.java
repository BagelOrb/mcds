package seasons;

import main.Debug;
import main.MinecraftDontStarve;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockIceChanger extends BukkitRunnable {

	World world;
	int x, z;
	Block spawn;
	int world_border_radius;
	
	public BlockIceChanger(World world)
	{
		this.world = world;
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
				Block block = world.getHighestBlockAt(spawn.getX() + x, spawn.getZ() + z);
				if (block.getType() == Material.SNOW)
				{
					block.setType(Material.AIR);					
				}
				if (block.getRelative(BlockFace.DOWN).getType() == Material.ICE)
				{
					block.getRelative(BlockFace.DOWN).setType(Material.WATER);			
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
