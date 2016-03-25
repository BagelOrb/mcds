package spoiling;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;

public class SinglePlayerSpoilageUpdater extends BukkitRunnable {

	Player player;
	
	public SinglePlayerSpoilageUpdater(Player player)
	{
		this.player = player;
	}
	
	@Override
	public void run() {
		if (player.isOnline())
		{
			SpoilUtils.spoilInventory(player.getInventory());
			InventoryView inv_viewed = player.getOpenInventory();
			if (inv_viewed != null)
			{
				SpoilUtils.spoilInventory(inv_viewed.getTopInventory());
				SpoilUtils.spoilInventory(inv_viewed.getBottomInventory());
			}
		}
	}

	
}
