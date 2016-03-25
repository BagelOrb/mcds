package utils;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * A block prototype without coords!
 * 
 * @author TK
 *
 */
public class MatState {

	public final Material mat;
	public final byte data;
	
	public MatState (Material mat2, byte data2) {
		this.mat = mat2;
		this.data = data2;
	}
	public MatState (Material mat2, int data2) {
		this.mat = mat2;
		this.data = (byte) data2;
	}
	
	public MatState(Material mat) {
		this.mat = mat;
		data = 0;
	}
	@SuppressWarnings("deprecation")
	public boolean isMetBy(Block block){
		if (block.getType() != mat) return false;
		if (block.getData() != data) return false;
		return true; // otherwise
	}
	
	@SuppressWarnings("deprecation")
	public void setBlock(Block block) {
		block.setType(mat);
		if (data != -1)
			block.setData(data);
	}
}
