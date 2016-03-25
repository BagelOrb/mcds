package utils;

import org.bukkit.entity.Player;

public abstract class Messageable {

	public abstract void sendMessage(String msg);
	
	public static class PlayerMsg extends Messageable {

		public Player player;
		
		public PlayerMsg(Player player) {
			this.player = player;
		}
		
		@Override
		public void sendMessage(String msg) {
			player.sendMessage(msg);
		}
	}

}
