package commands;


import java.util.ArrayList;

import org.bukkit.block.Biome;
import org.bukkit.scheduler.BukkitRunnable;

import seasons.BlockArrayIO;
import seasons.Season;
import seasons.SeasonChanger;
import main.Debug;
import main.MinecraftDontStarve;

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
			

		// Args
		String rw = this.readArg();
		
		// Apply
		if (rw.equals("w"))
		{
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
				}
			}.runTaskAsynchronously(MinecraftDontStarve.getCurrentPlugin());
		}
		else if (rw.equals("r"))
		{
			new BukkitRunnable() {
				
				@Override
				public void run() {
					Debug.out("starting reading biomes");
					
					boolean success = BlockArrayIO.read();
					if (!success)
					{
						Debug.out("Couldn't read file!");
					}
					Debug.out("biome file contains "+MinecraftDontStarve.original_biomes.getSize());
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
