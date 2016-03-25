package utils;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;

public class MatUtils {

	public static String prettyPrint(Material mat) {
		if (mat==null)
			return "Empty";
		
		switch (mat) {
		case WORKBENCH:
			return "Crafting Table";
		case LOG:
			return "Wood";
		case LOG_2:
			return "Wood";
		case WOOD:
			return "Wood Planks";
		default:
			String str = mat.name();
			str = str.replace('_', ' ').toLowerCase();
			str = WordUtils.capitalize(str);
			return str;
		}
	}
	
	public static Material getStairs(TreeSpecies species) {
		switch (species) {
		case ACACIA: return Material.ACACIA_STAIRS;
		case BIRCH: return Material.BIRCH_WOOD_STAIRS;
		case DARK_OAK: return Material.DARK_OAK_STAIRS;
		case GENERIC: return Material.WOOD_STAIRS;
		case JUNGLE: return Material.JUNGLE_WOOD_STAIRS;
		case REDWOOD: return Material.SPRUCE_WOOD_STAIRS;
		}
		return Material.WOOD_STAIRS;
	}

//	public static Material getBlock(TreeSpecies species) {
//		switch (species) {
//		case ACACIA: return Material.ACACIA;
//		case BIRCH: return Material.BIRCH_WOOD_STAIRS;
//		case DARK_OAK: return Material.DARK_OAK_STAIRS;
//		case GENERIC: return Material.WOOD_STAIRS;
//		case JUNGLE: return Material.JUNGLE_WOOD_STAIRS;
//		case REDWOOD: return Material.SPRUCE_WOOD_STAIRS;
//		}
//		return Material.WOOD;
//	}

	public static Material getStairs(Material blockType) {
		switch(blockType) {
		case COBBLESTONE: return Material.COBBLESTONE_STAIRS;
		case BRICK: return Material.BRICK_STAIRS;
		case SANDSTONE: return Material.SANDSTONE_STAIRS;
		case WOOD: return Material.WOOD_STAIRS;
		case SMOOTH_BRICK: return Material.SMOOTH_STAIRS;
		case QUARTZ: return Material.QUARTZ_STAIRS;
		case NETHER_BRICK: return Material.NETHER_BRICK_STAIRS;
		case RED_SANDSTONE: return Material.RED_SANDSTONE_STAIRS;
		default: return Material.SMOOTH_STAIRS;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(prettyPrint(Material.BAKED_POTATO));
	}




}
