package seasons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import main.MinecraftDontStarve;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Biome;

public class BlockArrayIO {

	public static Biome[] biomes = Biome.values();
	public static class Datum
	{
		public short x, z;
		public Biome biome;
		public Datum(short x, short z, char biome)
		{
			this.x = x;
			this.z = z;
			this.biome = biomes[biome];
		}
		public Datum(short x, short y, Biome biome)
		{
			this.x = x;
			this.z = y;
			this.biome = biome;
		}
	}
	
	public static class Data implements Iterable<Datum>
	{
		char[] data;
		int size;
		public Data(int size)
		{
			this.size = size;
			data = new char[size * 3];
		}
		public Datum getDatum(int index)
		{
			int data_idx = index * 3;
			return new Datum((short) data[data_idx], (short) data[data_idx + 1], biomes[data[data_idx + 2]]);
		}
		public void setDatum(int index, char x, char z, char biome)
		{
			int data_idx = index * 3;
			data[data_idx + 0] = x;
			data[data_idx + 1] = z;
			data[data_idx + 2] = biome;
			
		}
		public int getSize() {
			return data.length / 3;
		}
		
		public class DatumIterator implements Iterator<Datum>
		{
			int data_idx = 0;
			@Override
			public boolean hasNext() {
				return data_idx < size - 1;
			}

			@Override
			public Datum next() {
				Datum ret = new Datum((short) data[data_idx], (short) data[data_idx + 1], data[data_idx + 2]);
				data_idx++;
				return ret;
			}
			
		}
		
		@Override
		public Iterator<Datum> iterator() {
			return new DatumIterator();
		}
	}
	
	public static boolean write(World world)
	{
		int fileNumber = 0;
		int startingZ = 0;
		int endingZ = 0;
		int numberOfRowsPerFile = 16;
		
		WorldBorder border = world.getWorldBorder();
		
		if(border.getSize() > MinecraftDontStarve.maxBorderSize)
		{
			return false;
		}
		
		long ticksPerFile = (long) Math.ceil(border.getSize() / 1000) + 1;
		
		//TODO Backup old files!
		
		//Remove old files
		if(!deleteAll(world))
		{
			return false;
		}
		
		//Create New files
		int radius = (int) (border.getSize() / 2);
		
		startingZ = border.getCenter().getBlockZ() - radius;
		
		double numberOfFullFiles = Math.floor(border.getSize() / numberOfRowsPerFile);
		int numberOfFiles = (int) (Math.ceil(border.getSize() / numberOfRowsPerFile));
		
		for(int i = 0; i < numberOfFullFiles; i++)
		{
			endingZ = startingZ + numberOfRowsPerFile;
			new BlockArrayWriter(world, startingZ, endingZ, fileNumber, numberOfFiles).runTaskLater(MinecraftDontStarve.getCurrentPlugin(), ticksPerFile * i);
			fileNumber++;
			startingZ = endingZ;
		}
		
		endingZ = border.getCenter().getBlockZ() + radius;
		
		if(startingZ < endingZ)
		{
			new BlockArrayWriter(world, startingZ, endingZ, fileNumber, numberOfFiles).runTaskLater(MinecraftDontStarve.getCurrentPlugin(), (long) (ticksPerFile * numberOfFullFiles));
		}
		
		return true;
	}
	
	public static boolean writeSingleFile(List<Datum> data, World world, int fileNumber)
	{		
		return writeSingleFile(data, world, "biomedata_"+fileNumber+".xzdata");
	}
	
	public static boolean writeSingleFile(Iterable<Datum> data, World world, String fileName)
	{
		try {
			Path path = Paths.get(MinecraftDontStarve.defaultSavePath+world.getName()+"/"+fileName);
			if (!Files.exists(path))
			{
				Files.createDirectories(Paths.get(MinecraftDontStarve.defaultSavePath+world.getName()+"/"));
				Files.createFile(path);
			}
			
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(MinecraftDontStarve.defaultSavePath+world.getName()+"/"+fileName));
			for (Datum datum : data)
			{
				char[] dats = new char[] { (char) datum.x, (char) datum.z, (char) datum.biome.ordinal() };
				writer.write(dats);
			}
			writer.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;		
	}
	
	public static boolean readSingleFile(List<Datum> data, World world, String fileName)
	{
		BufferedReader reader = null;
		try {
			Path path = Paths.get(MinecraftDontStarve.defaultSavePath+world.getName()+"/"+fileName);
			if (!Files.exists(path))
			{
				return false;
			}
			
			reader = Files.newBufferedReader(path);
			
			char[] dats = new char[3];
			while (reader.read(dats) != -1) 
			{
				data.add(new Datum((short) dats[0], (short) dats[1], dats[2]));
			}
			reader.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
//		finally
//		{
//			if (reader != null)
//			{
//				reader.close();
//			}
//		}
//		
		
		return true;
	}
	
	public static boolean readSingleFile(List<Datum> data, World world, int currentFileNumber)
	{		
		return readSingleFile(data, world, "biomedata_"+currentFileNumber+".xzdata");
	}
	
	public static boolean deleteAll(World world)
	{
		boolean success = true;
	    File[] files = new File(MinecraftDontStarve.defaultSavePath+world.getName()+"/").listFiles();
	    for (File file : files)
	    {
	    	//TODO Add type check for safe files
	    	if(file.isFile())
	    	{
	    		success = success && file.delete();
	    	}
	    }
		
		return success;
	}
	
	public static int countFiles(World world)
	{
	    File[] files = new File(MinecraftDontStarve.defaultSavePath+world.getName()+"/").listFiles();
	    return files.length;
	}
	
}
