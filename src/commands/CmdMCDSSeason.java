package commands;


import seasons.Season;
import seasons.SeasonChanger;
import main.MinecraftDontStarve;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;

public class CmdMCDSSeason extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdMCDSSeason()
	{
		// Aliases
		this.addAliases("s", "season");

		// Args
		this.addParameter(TypeString.get(), "season");
		
		this.setDesc("Change season");
		this.setHelp("This command is used to change season");
		
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

		// Args
		String season = this.readArg();
		
		// Verify
		if(season==null)
		{
			player.sendMessage(Txt.parse("<info>Current season: "+MinecraftDontStarve.current_season.toString()));
			return;
		}
		
		if(!player.isOp())
		{
			player.sendMessage(Txt.parse("<bad>You must be OP to use this command!"));
			return;
		}
		
			
		Season chosenSeason;
		
		// Apply
		switch (season) {
		case "spring":
			chosenSeason = Season.SPRING;
			SeasonChanger.startSeason(MinecraftDontStarve.defaultWorld, chosenSeason);
			break;
			
		case "summer":
			chosenSeason = Season.SUMMER;
			SeasonChanger.startSeason(MinecraftDontStarve.defaultWorld, chosenSeason);
			break;
			
		case "autumn":
			chosenSeason = Season.AUTUMN;
			SeasonChanger.startSeason(MinecraftDontStarve.defaultWorld, chosenSeason);
			break;
			
		case "winter":
			chosenSeason = Season.WINTER;
			SeasonChanger.startSeason(MinecraftDontStarve.defaultWorld, chosenSeason);
			break;

		default:
			player.sendMessage(Txt.parse("<bad>Couldn't read the season!"));
			return;
		}
		
		// Inform
		player.sendMessage(Txt.parse("<info>Season changed from "+MinecraftDontStarve.current_season.toString() + " to " + chosenSeason.toString()));
		
	}

}
