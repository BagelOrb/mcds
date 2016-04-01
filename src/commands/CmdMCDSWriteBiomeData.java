package commands;


import java.util.ArrayList;

import main.Debug;
import main.MinecraftDontStarve;

import org.bukkit.scheduler.BukkitRunnable;

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
			
			Debug.out("starting writing biomes");
			boolean success = BlockArrayIO.write(player.getWorld());
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
		else if (rw.equals("r"))
		{
			new BukkitRunnable() {
				
				@Override
				public void run() {
					Debug.out("starting reading biomes");
					
					MinecraftDontStarve.original_biomes = new ArrayList<BlockArrayIO.Datum>(); 
					boolean success = BlockArrayIO.readAll(MinecraftDontStarve.original_biomes, player.getWorld());
					if (!success)
					{
						Debug.out("Couldn't read file!");
					}
					Debug.out("biome file contains "+MinecraftDontStarve.original_biomes.size());
				}
			}.runTaskAsynchronously(MinecraftDontStarve.getCurrentPlugin());
		}
		else
		{
			Debug.out("invalid argument");
		}
		
		// Inform
//		player.sendMessage(Txt.parse("<info>Season changed from "+MinecraftDontStarve.current_season.toString() + " to " + chosenSeason.toString()));
		
	}

}
