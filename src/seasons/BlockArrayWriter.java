package seasons;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import main.Debug;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Biome;
import org.bukkit.scheduler.BukkitRunnable;

import seasons.BlockArrayIO.Datum;

public class BlockArrayWriter extends BukkitRunnable {

	World world;
	int startingZ;
	int endingZ;
	int fileNumber;
	int numberOfFiles;
	
	public BlockArrayWriter(World world, int startingZ, int endingZ, int fileNumber, int numberOfFiles)
	{
		this.world = world;
		this.startingZ = startingZ;
		this.endingZ = endingZ;
		this.fileNumber = fileNumber;
		this.numberOfFiles = numberOfFiles;
	}
	
	@Override
	public void run() 
	{
		LinkedList<Datum> data = new LinkedList<Datum>();
		
		WorldBorder border = world.getWorldBorder();

		int radius = (int) (border.getSize() / 2);
		
		for (short z = (short) startingZ; z < endingZ; z++)
		{
			for (short x = (short) (border.getCenter().getBlockX() - radius); x < border.getCenter().getBlockX() + radius; x++)
			{
			
				Biome biome = world.getBiome(x,  z);
				if (!SeasonBiomes.biomeRemainsUnchanged(biome))
				{
					data.add(new Datum(x, z, biome));
				}
			}
		}
		
		if(!data.isEmpty())
		{
			long seed = 123;
			Collections.shuffle(data, new Random(seed));
			if (BlockArrayIO.writeSingleFile(data, world, fileNumber))
			{
				Debug.out("Biome file "+(fileNumber+1)+"/"+numberOfFiles+" saved!");
			}
			else
			{
				Debug.out("ERROR Biome file "+(fileNumber+1)+"/"+numberOfFiles+" FAILED!");
			}
		}

		data = null;
	}

	
}
