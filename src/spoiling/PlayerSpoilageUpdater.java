package spoiling;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerSpoilageUpdater extends BukkitRunnable {

	@Override
	public void run() {
		Collection<? extends Player> onlinePlayerList = Bukkit.getServer().getOnlinePlayers();
		for (Player player : onlinePlayerList)
		{
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

	
}
