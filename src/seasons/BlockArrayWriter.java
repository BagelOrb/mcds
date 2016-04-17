package seasons;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import main.Debug;
import main.MinecraftDontStarve;

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
		boolean firstRow = fileNumber < 1;
		LinkedList<Datum> data1 = new LinkedList<Datum>();
		LinkedList<Datum> data2 = new LinkedList<Datum>();
		WorldBorder border = world.getWorldBorder();
		int radius = (int) (border.getSize() / 2 + MinecraftDontStarve.blocksBeyondBorder);
		
		for (short z = (short) startingZ; z < endingZ; z++)
		{
			for (short x = (short) (border.getCenter().getBlockX() - radius); x < border.getCenter().getBlockX() + radius; x++)
			{
			
				Biome biome = world.getBiome(x,  z);
				if (!SeasonBiomes.biomeRemainsUnchanged(biome))
				{
					if(Math.random() < 0.5)
					{
						data1.add(new Datum(x, z, biome));
					}
					else
					{
						data2.add(new Datum(x, z, biome));
					}
				}
			}
		}
		
		if(firstRow)
		{
			data1.addAll(data2);
		}
		else
		{
			if(!BlockArrayIO.addToFile(data2, world, fileNumber-1))
			{
				Debug.out("ERROR Biome file "+(fileNumber)+"/"+numberOfFiles+" FAILED TO APPEND!");
				data1.addAll(data2);
			}
		}
		
		if(!data1.isEmpty())
		{
			long seed = 123;
			Collections.shuffle(data1, new Random(seed));
			if (BlockArrayIO.writeSingleFile(data1, world, fileNumber))
			{
				Debug.out("Biome file "+(fileNumber+1)+"/"+numberOfFiles+" saved!");
			}
			else
			{
				Debug.out("ERROR Biome file "+(fileNumber+1)+"/"+numberOfFiles+" FAILED TO WRITE!");
			}
		}

		data1 = null;
		data2 = null;
	}

	
}
