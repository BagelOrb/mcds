package commands;


import main.MinecraftDontStarve;
import spoiling.PlayerSpoilageUpdater;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.util.Txt;

public class CmdMCDSCheck extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdMCDSCheck()
	{
		// Aliases
		this.addAliases("c", "check");

		// Args

		this.setDesc("Check food freshness");
		this.setHelp("This command is used to update the freshness of all food items in player invs");
		
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

		PlayerSpoilageUpdater spoilage_updater = new PlayerSpoilageUpdater();
		spoilage_updater.runTask(MinecraftDontStarve.getCurrentPlugin());
		
		player.sendMessage(Txt.parse("<good>All food items in player inventories were updated!"));
	}

}
