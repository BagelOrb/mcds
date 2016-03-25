package seasons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import main.Debug;
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
	
	public static boolean write()
	{
		LinkedList<Datum> data = new LinkedList<Datum>();
		
		World world = MinecraftDontStarve.defaultWorld;
		WorldBorder border = world.getWorldBorder();
		int radius = (int) (border.getSize() / 2);
		for (short x = (short) (border.getCenter().getBlockX() - radius); x < border.getCenter().getBlockX() + radius; x++)
		{
			for (short z = (short) (border.getCenter().getBlockZ() - radius); z < border.getCenter().getBlockZ() + radius; z++)
			{
				data.add(new Datum(x, z, world.getBiome(x,  z)));
			}
			if (x % 8 == 0)
			{
				Debug.out("x: "+x);
			}
		}
		
		Debug.out("data size: "+data.size());
		
		long seed = 123;
		Collections.shuffle(data, new Random(seed));
		
		Debug.out("data size: "+data.size());
		
		return write(data);
	}
	
	public static boolean write(Iterable<Datum> data)
	{
		try {
			Path path = Paths.get("BS.xyzData");
			if (!Files.exists(path))
			{
				Files.createFile(path);
			}
			
			BufferedWriter writer = Files.newBufferedWriter(Paths.get("BS.xyzdata"));
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
	
	public static boolean read(List<Datum> data)
	{

		BufferedReader reader = null;
		try {
			Path path = Paths.get("BS.xyzData");
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
	
	//TODO: Make not banana: 
	public static void main(String [ ] args)
	{
		ArrayList<Datum> data = new ArrayList<Datum>();
		boolean success = read(data);
		for (Datum datum : data)
		{
			System.out.println(""+ datum.x +", "+datum.z+": "+datum.biome);
		}
	}
}
