package spoiling;

import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class InventorySpoilageUpdater extends BukkitRunnable {

	Inventory inventory;
	
	float freshness = 1.0f;
	
	public InventorySpoilageUpdater(Inventory inv)
	{
		this.inventory = inv;
	}	
	public InventorySpoilageUpdater(Inventory inv, float freshness)
	{
		this.inventory = inv;
		this.freshness = freshness;
	}
	
	@Override
	public void run() {
		if(inventory != null)
		{
			SpoilUtils.spoilInventory(inventory, freshness);
		}
	}

	
}
