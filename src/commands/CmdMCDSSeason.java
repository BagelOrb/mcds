package commands;


import main.MinecraftDontStarve;
import seasons.Season;
import seasons.SeasonChanger;

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
		this.addParameter("read", TypeString.get(), "season");
		
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
		if(season==null || season.equalsIgnoreCase(("read")))
		{
			player.sendMessage(Txt.parse("<info>Current season: "+MinecraftDontStarve.current_season.toString()));
			return;
		}
		
		if(!player.isOp())
		{
			player.sendMessage(Txt.parse("<bad>You must be OP to use this command like that!"));
			return;
		}
		
		if(MinecraftDontStarve.isCurrentlyDoingASeasonTask)
		{
			player.sendMessage(Txt.parse("<bad>There is already a season task in process!"));
			return;
		}
			
		Season chosenSeason;
		
		// Apply
		Season previousSeason = MinecraftDontStarve.current_season;
		
		switch (season) {
		case "spring":
			chosenSeason = Season.SPRING;
			SeasonChanger.startSeason(player.getWorld(), chosenSeason);
			break;
			
		case "summer":
			chosenSeason = Season.SUMMER;
			SeasonChanger.startSeason(player.getWorld(), chosenSeason);
			break;
			
		case "autumn":
			chosenSeason = Season.AUTUMN;
			SeasonChanger.startSeason(player.getWorld(), chosenSeason);
			break;
			
		case "winter":
			chosenSeason = Season.WINTER;
			SeasonChanger.startSeason(player.getWorld(), chosenSeason);
			break;

		default:
			player.sendMessage(Txt.parse("<bad>Couldn't read the season!"));
			return;
		}
		
		// Inform
		player.sendMessage(Txt.parse("<info>Season changed from "+previousSeason.toString() + " <info>to " + chosenSeason.toString()+"<info>!"));
		
		
	}

}
