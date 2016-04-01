package seasons;

import main.MinecraftDontStarve;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.massivecraft.massivecore.util.Txt;

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
	public void onWeatherChange(WeatherChangeEvent event)
	{
    	if (event.isCancelled())
    	{
    		return;
    	}
		float mod = MinecraftDontStarve.current_season.getRainChance();
		if (!event.toWeatherState())
		{
			mod = 1.0f / mod;
		}
		event.getWorld().setWeatherDuration((int) (event.getWorld().getWeatherDuration() * mod));
	}
	@EventHandler
	public void onWeatherChange(ThunderChangeEvent event)
	{
    	if (event.isCancelled())
    	{
    		return;
    	}
		float mod = MinecraftDontStarve.current_season.getThunderChance();
		if (!event.toThunderState())
		{
			mod = 1.0f / mod;
		}
		event.getWorld().setWeatherDuration((int) (event.getWorld().getWeatherDuration() * mod));
		
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
			if (!event.getBlock().getWorld().hasStorm() && rand < 0.5)
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
	
	@EventHandler
	public void serverListPing(ServerListPingEvent event)
	{
		switch(MinecraftDontStarve.current_season)
		{
			case WINTER:
				event.setMotd(Txt.parse("<reset><lime><bold>MineRight.eu<reset> <gold>Minecraft 1.9 Faction Server<reset>\n<aqua><bold><underline>WINTER IS HERE!<reset> <info>Current Season: <reset>"+MinecraftDontStarve.current_season.toString()));
				break;
			default:
				event.setMotd(Txt.parse("<reset><lime><bold>MineRight.eu<reset> <gold>Minecraft 1.9 Faction Server<reset>\n<aqua><bold><underline>WINTER IS COMING!<reset> <info>Current Season: <reset>"+MinecraftDontStarve.current_season.toString()));
				break;
		}
		
	}
}
