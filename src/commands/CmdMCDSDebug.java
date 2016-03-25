package commands;

import com.massivecraft.massivecore.util.Txt;

import main.Debug;



public class CmdMCDSDebug extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdMCDSDebug()
	{
		// Aliases
		this.addAliases("d", "debug");

		this.setDesc("Debug something");
		this.setHelp("This command is used to try out anything");

		// Requirements
		//		this.addRequirements(ReqFactionsEnabled.get());
		//		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public void perform()
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
		Debug.out("sat: " + player.getSaturation() + "; food: "+player.getFoodLevel());
	}

}
