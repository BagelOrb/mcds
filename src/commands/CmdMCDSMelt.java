package commands;


import main.Debug;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

public class CmdMCDSMelt extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdMCDSMelt()
	{
		// Aliases
		this.addAliases("m", "melt");

		// Args

		this.setDesc("Melt snow and ice");
		this.setHelp("This command is used to melt snow and ice");
		
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

		Debug.out(Txt.parse("<good>This command does nothing!"));
	}

}
