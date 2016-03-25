package spoiling;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SpoilingMaterials {

public static boolean isSpoilable(ItemStack item)
{
	switch(item.getType())
	{
		case RAW_CHICKEN:
		case COOKED_CHICKEN:
		
		case RAW_BEEF:
		case COOKED_BEEF:
		
		case PORK:
		case GRILLED_PORK:
		
		case RABBIT:
		case COOKED_RABBIT:
		case RABBIT_STEW:
		
		case RAW_FISH:
		case COOKED_FISH:
		
		case MUTTON:
		case COOKED_MUTTON:
		
		case POISONOUS_POTATO:
		case POTATO_ITEM:
		case BAKED_POTATO:
		
		case BROWN_MUSHROOM:
		case RED_MUSHROOM:
			
		case BEETROOT:
		case BEETROOT_SOUP:
		case MUSHROOM_SOUP:
		
		case CHORUS_FRUIT:
		
		//case GOLDEN_APPLE:
		case GOLDEN_CARROT:
		case CARROT_ITEM:
		case APPLE:
		case MELON:
		
		case MILK_BUCKET:
		case EGG:
		case BREAD:
		case WHEAT:
		case SUGAR:
		case SUGAR_CANE:
		
		case PUMPKIN_PIE:
		case COOKIE:
		case CAKE:
		
		case ROTTEN_FLESH:
			return true;
		default:
			return false;
	}
}

public static boolean decayItem(ItemStack item)
{
	switch (item.getType())
	{
		case RAW_CHICKEN:
		case COOKED_CHICKEN:
		
		case RAW_BEEF:
		case COOKED_BEEF:
		
		case PORK:
		case GRILLED_PORK:
		
		case MUTTON:
		case COOKED_MUTTON:
		
		case RABBIT:
		case COOKED_RABBIT:
		case RABBIT_STEW:
		
		case RAW_FISH:
		case COOKED_FISH:
		item.setType(Material.ROTTEN_FLESH);
		item.setDurability((short) 0);
			return true;
		case POISONOUS_POTATO:
		case BAKED_POTATO:
			
		case BROWN_MUSHROOM:
		case RED_MUSHROOM:
		
		case BEETROOT:
		case BEETROOT_SOUP:
		case MUSHROOM_SOUP:
		
		case CHORUS_FRUIT:
		
		case CARROT_ITEM:
		case APPLE:
		case MELON:
		
		case EGG:
		case BREAD:
		case WHEAT:
		case SUGAR:
		case SUGAR_CANE:
		
		case PUMPKIN_PIE:
		case COOKIE:
		case CAKE:
		
		case ROTTEN_FLESH:
		item.setType(Material.INK_SACK);
		item.setDurability((short) 15);
			return true;
		case GOLDEN_CARROT:
		item.setType(Material.CARROT_ITEM);
		item.setDurability((short) 0);
			return true;
		case POTATO_ITEM:
		item.setType(Material.POISONOUS_POTATO);
		item.setDurability((short) 0);
			return true;
		case MILK_BUCKET:
		item.setType(Material.WATER_BUCKET);
		item.setDurability((short) 0);
			return true;
		
		default:
			return false;
	}
}

public static long getFoodLifeTime(ItemStack item)
{
	switch(item.getType())
	{
		case RAW_CHICKEN:
		case RAW_BEEF:
		case PORK:
		case RABBIT:
		case RAW_FISH:
		case MUTTON:
			return 50; 
		case COOKED_CHICKEN:
		case COOKED_BEEF:
		case GRILLED_PORK:
		case COOKED_RABBIT:
		case COOKED_FISH:
		case COOKED_MUTTON:
			return 100;
		
		case RABBIT_STEW:
		case BEETROOT_SOUP:
		case MUSHROOM_SOUP:
			return 100;
		
		
		case POTATO_ITEM:
			return 500;
		case BAKED_POTATO:
			return 50; 
		case POISONOUS_POTATO:
			return 250;
		
		case GOLDEN_CARROT:
			return 50;
		
		case BROWN_MUSHROOM:
		case RED_MUSHROOM:
		case BEETROOT:
		case CARROT_ITEM:
			return 250;
		
		case CHORUS_FRUIT:
			return 100;
		
		case APPLE:
		case MELON:
			return 50;
		
		case MILK_BUCKET:
		case EGG:
		case BREAD:
			return 50;
		case WHEAT:
		case SUGAR:
		case SUGAR_CANE:
			return 750;
		
		case PUMPKIN_PIE:
		case CAKE:
			return 50;
			
		case COOKIE:
			return 150;
		
		case ROTTEN_FLESH:
			return 250;
		
		default:
			return 50;
	}
}

public static double getSaturation(ItemStack item)
{
	if (item == null)
	{
		return -1;
	}
	switch (item.getType())
	{
		case APPLE: 			return 2.4;
		case BAKED_POTATO :		return 7.2;
		case BEETROOT: 			return 1.2;
		case BEETROOT_SOUP:		return 7.2;
		case BREAD: 			return 6;
		//case Cake (slice): 	return 0.4;
		//case Cake (whole): 	return 2.8;
		case CARROT_ITEM: 		return 4.8;
		case CHORUS_FRUIT: 		return 2.4;
		case RAW_FISH: 
			switch (item.getDurability())
			{
				case 0: // raw fish
					return 0.4;
				case 1: //Raw Salmon: 
					return 0.4;
				case 2: // Clownfish: 
					return 0.2;
				case 3: // Pufferfish: 
					return 0.2;
				default:
					return 0.3;
			}
		case COOKED_CHICKEN: 	return 7.2;
		case COOKED_FISH: 
			switch (item.getDurability())
			{
				case 0:
					return 6;
				case 1: // Cooked Salmon: 
					return 9.6;
				default:
					return 6;
			}
		case COOKED_MUTTON: 	return 9.6;
		case GRILLED_PORK: 		return 12.8;
		case COOKED_RABBIT: 	return 6;
		case COOKIE: 			return 0.4;
		case GOLDEN_APPLE: 		return 9.6;
		//case Enchanted Golden Apple: 	return 9.6;
		case GOLDEN_CARROT: 	return 14.4;
		case MELON: 			return 1.2;
		case MUSHROOM_SOUP: 	return 7.2;
		case POISONOUS_POTATO: 	return 1.2;
		case POTATO_ITEM: 		return 0.6;
		case PUMPKIN_PIE: 		return 4.8;
		case RABBIT_STEW : 		return 12;
		case RAW_BEEF: 			return 1.8;
		case RAW_CHICKEN : 		return 1.2;
		case MUTTON: 			return 1.2;
		case PORK: 				return 1.8;
		case RABBIT:		 	return 1.8;
		case ROTTEN_FLESH:	 	return 0.8;
		case SPIDER_EYE: 		return 3.2;
		case COOKED_BEEF: 		return 12.8;
		
		default:
			return -1;
	}
}


public static int getFoodRestored(ItemStack item)
{
	if (item == null)
	{
		return -1;
	}
	switch (item.getType())
	{
		case APPLE: 		return 4;
		case BAKED_POTATO: 	return 5;
		case BEETROOT: 		return 1;
		case BEETROOT_SOUP: return 6;
		case BREAD: 		return 5;
		//case Cake (slice): 	return 2;
		//case Cake (whole): 	return 14;
		case CARROT_ITEM: 	return 3;
		case CHORUS_FRUIT: 	return 4;
		case RAW_FISH: 
		switch (item.getDurability())
		{
			case 0: // raw fish
				return 2;
			case 1: //Raw Salmon: 
				return 2;
			case 2: // Clownfish: 
				return 1;
			case 3: // Pufferfish: 
				return 1;
			default:
				return 1;
			}
			case COOKED_FISH: 
			switch (item.getDurability())
			{
				case 0:
					return 5;
				case 1: // Cooked Salmon: 
					return 6;
				default:
					return 5;
			}
			case COOKED_BEEF: 		return 8;
			case COOKED_CHICKEN:	return 6;
			case COOKED_MUTTON: 	return 6;
			case GRILLED_PORK: 		return 8;
			case COOKED_RABBIT: 	return 5;
			case COOKIE: 			return 2;
			case GOLDEN_APPLE: 		return 4;
			//case Enchanted_Golden_Apple: 	return 4;
			case GOLDEN_CARROT:		return 6;
			case MELON: 			return 2;
			case MUSHROOM_SOUP: 	return 6;
			case POISONOUS_POTATO: 	return 2;
			case POTATO: 			return 1;
			case PUMPKIN_PIE: 		return 8;
			case RABBIT_STEW: 		return 10;
			case RAW_BEEF:		 	return 3;
			case RAW_CHICKEN: 		return 2;
			case MUTTON: 			return 2;
			case PORK : 			return 3;
			case RABBIT: 			return 3;
			case ROTTEN_FLESH: 		return 4;
			case SPIDER_EYE: 		return 2;
			default:
				return -1;
		}
	}
}
