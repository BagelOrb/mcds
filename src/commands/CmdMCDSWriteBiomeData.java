package commands;


import main.Debug;
import main.MinecraftDontStarve;

import seasons.BlockArrayIO;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;

public class CmdMCDSWriteBiomeData extends MCDSCommand{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public CmdMCDSWriteBiomeData()
	{
		// Aliases
		this.addAliases("o", "originalbiomes");

		// Args
		this.addParameter(TypeString.get(), "read/write");
		
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
		
		if(!player.isOp())
		{
			player.sendMessage(Txt.parse("<bad>You must be OP to use this command!"));
			return;
		}
		
		if(MinecraftDontStarve.isCurrentlyDoingASeasonTask)
		{
			player.sendMessage(Txt.parse("<bad>There is already a season task in process!"));
			return;
		}

		// Args
		String rw = this.readArg();
		
		// Apply
		if (rw.equals("w"))
		{
			MinecraftDontStarve.isCurrentlyDoingASeasonTask = true;
			/*
			new BukkitRunnable() {
				
				@Override
				public void run() 
				{
					Debug.out("starting writing biomes");
					boolean success = BlockArrayIO.write();
					if (success)
					{
						Debug.out("finished writing biomes");
					}
					else 
					{
						Debug.out("couldnt write biomes");						
					}
					
					MinecraftDontStarve.isCurrentlyDoingASeasonTask = false;
				}
			}.runTaskAsynchronously(MinecraftDontStarve.getCurrentPlugin());
			*/
			
			Debug.out("Starting to write biomes");
			boolean success = BlockArrayIO.write(player.getWorld());
			if (success)
			{
				Debug.out("Creating biome files...");
			}
			else 
			{
				Debug.out("couldnt write biomes!");						
			}
			MinecraftDontStarve.isCurrentlyDoingASeasonTask = false;
			
		}
		else
		{
			Debug.out("invalid argument");
		}
		
		// Inform
//		player.sendMessage(Txt.parse("<info>Season changed from "+MinecraftDontStarve.current_season.toString() + " to " + chosenSeason.toString()));
		
	}

}
