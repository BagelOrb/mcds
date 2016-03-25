package commands;

import com.massivecraft.massivecore.util.Txt;

import main.Debug;



public class CmdMCDSHunger extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdMCDSHunger()
	{
		// Aliases
		this.addAliases("f", "food");

		this.setDesc("Get hungry");
		this.setHelp("This command is used to make you hungry");

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

//		final CommandSender sender = this.sender;
		Debug.out("performing command /mcds hunger");
		player.setFoodLevel(10);
		player.setSaturation(5f);
		
	}

}
