package commands;


import main.MinecraftDontStarve;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

public class CmdMCDSCancel extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdMCDSCancel()
	{
		// Aliases
		this.addAliases("c", "cancel");

		// Args

		this.setDesc("Cancel running tasks");
		this.setHelp("This command is used to cancel running tasks");
		
		// Requirements
//		this.addRequirements(ReqFactionsEnabled.get());
//		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public void perform() throws MassiveException
	{
		if(player == null)
		{
			sender.sendMessage(Txt.parse("<bad>You must be a player to use this command!"));
			return;
		}
		if(!player.isOp())
		{
			player.sendMessage(Txt.parse("<bad>You must be OP to use this command!"));
			return;
		}

		MinecraftDontStarve.isCancelled = true;
		
		player.sendMessage(Txt.parse("<good>All running tasks should stop!"));
	}

}
