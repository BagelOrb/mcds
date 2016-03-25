package commands;


import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.MassiveCommand;

public abstract class MCDSCommand extends MassiveCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //

	public Player player;

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void senderFields(boolean set)
	{
		this.player = set ? MPlayer.get(sender).getPlayer() : null;
		//this.msenderFaction = set ? this.msender.getFaction() : null;
	}

}
