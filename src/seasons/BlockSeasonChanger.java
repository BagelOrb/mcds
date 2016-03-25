package seasons;

import java.util.Iterator;
import java.util.List;

import main.Debug;

import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockSeasonChanger extends BukkitRunnable {

	World world;
	Season season;
	Iterator<BlockArrayIO.Datum> iter;
	List<BlockArrayIO.Datum> data;
	int batch_size;
	
	public BlockSeasonChanger(World world, List<BlockArrayIO.Datum> data, Season season, int batch_size)
	{
		this.world = world;
		this.data = data;
		this.season = season;
		this.batch_size = batch_size;
		iter = data.iterator();
	}
	
	@Override
	public void run() 
	{
		for (int n = 0; n < batch_size; n++)
		{
			if (!iter.hasNext())
			{
				Debug.out("finished changing season.");
				this.cancel();
			}
			BlockArrayIO.Datum datum = iter.next();
			if (datum == null)
			{
				Debug.out("Couldn't read!!! WTFFFF");
				this.cancel();
			}
			
			world.setBiome(datum.x, datum.z, SeasonBiomes.getNewBiome(datum.biome, season));	
			if (season == Season.WINTER)
			{
				SeasonBlockUtils.makeIceOrSnow(datum.x, datum.z);
			}
			else if (season == Season.SPRING)
			{
				SeasonBlockUtils.thaw(datum.x, datum.z);				
			}
		}
		if (!iter.hasNext())
		{
			Debug.out("finished changing season.");
			this.cancel();
		}
		
	}

	
}
