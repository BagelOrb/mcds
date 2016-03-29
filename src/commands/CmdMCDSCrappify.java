package commands;

import main.Debug;
import main.MinecraftDontStarve;



public class CmdMCDSCrappify extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdMCDSCrappify()
	{
		// Aliases
		this.addAliases("q", "qrappify");

		this.setDesc("Spoil food by advancing 10 days");
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
//		final CommandSender sender = this.sender;
		Debug.out("performing command /mcds crappify");
		long time = MinecraftDontStarve.defaultWorld.getFullTime();
		MinecraftDontStarve.defaultWorld.setFullTime(time + MinecraftDontStarve.ticksPerMinecraftDay * 50);
		
	}

}
