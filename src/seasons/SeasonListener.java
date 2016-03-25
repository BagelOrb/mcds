package seasons;

import main.MinecraftDontStarve;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class SeasonListener implements Listener 
{

	public SeasonListener(MinecraftDontStarve minecraftDontStarve) {
		// TODO Auto-generated constructor stub
	}

	/*
	@EventHandler
	public void onBlockForm(BlockFormEvent e) 
	{
		Debug.out("Block formed! " + e.getBlock().getType().toString());
		Debug.out("New state: " + e.getNewState().getType().toString());
		
		
		if(e.getNewState().getType().equals(Material.SNOW))
		{
			SeasonChanger.formedSnowBlocks.add(e.getBlock());
		}
		else if(e.getNewState().getType().equals(Material.ICE))
		{
			SeasonChanger.formedIceBlocks.add(e.getBlock());
		}
	}
	*/
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) 
	{
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event)
	{
    	if (event.isCancelled())
    	{
    		return;
    	}
		float mod = SeasonChanger.rainChanceModifier();
		if (!event.toWeatherState())
		{
			mod = 1.0f / mod;
		}
		MinecraftDontStarve.defaultWorld.setWeatherDuration((int) (MinecraftDontStarve.defaultWorld.getWeatherDuration() * mod));
	}
	@EventHandler
	public void onWeatherChange(ThunderChangeEvent event)
	{
    	if (event.isCancelled())
    	{
    		return;
    	}
		float mod = SeasonChanger.thunderChanceModifier();
		if (!event.toThunderState())
		{
			mod = 1.0f / mod;
		}
		MinecraftDontStarve.defaultWorld.setWeatherDuration((int) (MinecraftDontStarve.defaultWorld.getWeatherDuration() * mod));
		
	}
	
	@EventHandler
	public void onGrow(BlockGrowEvent event)
	{
    	if (event.isCancelled())
    	{
    		return;
    	}
		
		double rand = Math.random();
		switch (MinecraftDontStarve.current_season)
		{
		case SPRING:
			break;
		case SUMMER:
			if (rand < 0.5)
			{
				event.setCancelled(true);
			}
			break;
		case AUTUMN:
			if (rand < 0.2)
			{
				event.setCancelled(true);
			}
			break;
		case WINTER:
			if (rand < 0.9)
			{
				event.setCancelled(true);
			}
			break;
		}
	}
}
