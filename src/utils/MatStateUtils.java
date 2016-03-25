package utils;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class MatStateUtils {

	public static enum Shape { Block, Step, Stairs };
	public static enum MatType { 
		Oak(true), Spruce(true), Birch(true), Jungle(true), Acacia(true), DarkOak(true), // wood types 
		Cobble(false), MossyCobble(false), StoneBricks(false), Stone(false), Quartz(false), SandStone(false), NetherBrick(false), Brick(false); // stone types
		public boolean isWoodType;
		MatType(boolean isWood) { isWoodType = isWood; };
	};
	
	public static class MatShape
	{
		MatType matType;
		Shape shape;
		BlockFace stairDirection;
		boolean upsideDown;
		public MatShape(MatType matType, Shape shape) { this.matType = matType; this.shape = shape; this.stairDirection = BlockFace.DOWN; this.upsideDown = false; }
		public MatShape(MatType matType, Shape shape, boolean upsideDown) { this.matType = matType; this.shape = shape; this.stairDirection = BlockFace.DOWN; this.upsideDown = upsideDown; }
		public MatShape(MatType matType, Shape shape, BlockFace stairDirection, boolean upsideDown) { this.matType = matType; this.shape = shape; this.stairDirection = stairDirection; this.upsideDown = upsideDown; }
		public MatState getMatState(BlockFace dir, boolean upperHalf) { return MatStateUtils.getMatState(matType, shape, dir, upperHalf); }
		public MatState getMatState(boolean upperHalf) { return MatStateUtils.getMatState(matType, shape, stairDirection, upperHalf); }
		public MatState getMatState() { return MatStateUtils.getMatState(matType, shape, stairDirection, upsideDown); }
	}
	
	public static MatState getMatState(MatType matType, Shape shape, BlockFace dir, boolean upperHalf) {
		Material mat = getMat(matType, shape);
		byte data = 0;
		
		if (shape == Shape.Stairs)
		{
			switch(dir) {
			case NORTH: data = 3; break;
			case EAST: data = 0; break;
			case SOUTH: data = 2; break;
			case WEST: data = 1; break;
			default: // nothing: leave zero
			}
			if (upperHalf) data += 4;
			return new MatState(mat, data);
		}
		
		switch (mat) {
		case WOOD:
			switch(matType) {
			case Acacia: data = 4; break;
			case Birch: data = 2; break;
			case DarkOak: data = 5; break;
			case Jungle: data = 3; break;
			case Oak: data = 0; break;
			case Spruce: data = 1; break;
			default: // nothing
			}
			break;
		case WOOD_STEP:
			switch(matType) {
			case Acacia: data = 4; break;
			case Birch: data = 2; break;
			case DarkOak: data = 5; break;
			case Jungle: data = 3; break;
			case Oak: data = 0; break;
			case Spruce: data = 1; break;
			default: // nothing
			}
			if (upperHalf) data += 8;
			break;
		case STEP:
			switch(matType) {
			case Brick:			data = 4; break;
			case MossyCobble:
			case Cobble:		data = 3; break;
			case NetherBrick:	data = 6; break;
			case Quartz:		data = 7; break;
			case SandStone:		data = 1; break;
			case Stone:			data = 0; break;
			case StoneBricks:	data = 5; break;
			default: // nothing
			}
			if (upperHalf) data += 8;
			break;
		default: // nothing
		}

		return new MatState(mat, data);

		
	};
	
	public static Material getMat(MatType matType, Shape shape) {
		
		switch (shape) {
		case Block: 
			{
				switch(matType) {
				case Brick:			return Material.BRICK;
				case MossyCobble:	return Material.MOSSY_COBBLESTONE;
				case Cobble:		return Material.COBBLESTONE;
				case NetherBrick:	return Material.NETHER_BRICK;
				case Quartz:		return Material.QUARTZ;
				case SandStone:		return Material.SANDSTONE;
				case Stone:			return Material.STONE;
				case StoneBricks:	return Material.SMOOTH_BRICK;
				default:
					return Material.WOOD;
				}
			}
		case Stairs: 
			{
				switch(matType) {
				case Acacia: 	return Material.ACACIA_STAIRS; 
				case Birch: 	return Material.BIRCH_WOOD_STAIRS; 
				case DarkOak: 	return Material.DARK_OAK_STAIRS; 
				case Jungle: 	return Material.JUNGLE_WOOD_STAIRS; 
				case Oak: 		return Material.WOOD_STAIRS; 
				case Spruce: 	return Material.SPRUCE_WOOD_STAIRS;
				case Brick:		return Material.BRICK_STAIRS;
				case MossyCobble:
				case Cobble:	return Material.COBBLESTONE_STAIRS;
				case NetherBrick:return Material.NETHER_BRICK_STAIRS;
				case Quartz:	return Material.QUARTZ_STAIRS;
				case SandStone: return Material.SANDSTONE_STAIRS;
				case Stone:		return Material.SMOOTH_STAIRS;
				case StoneBricks:return Material.SMOOTH_STAIRS;
				}
			}
		case Step:  
			{
				if (matType.isWoodType) return Material.WOOD_STEP;
				else return Material.STEP;
			}
		}

		return Material.SPONGE;
	};


}
