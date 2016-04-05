package seasons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.Debug;
import main.MinecraftDontStarve;

public class SeasonIO {

	public static boolean writeSeasonToFile()
	{	
		try {
			Path path = Paths.get(MinecraftDontStarve.defaultSavePath+MinecraftDontStarve.seasonSaveFile);
			if (!Files.exists(path))
			{
				Files.createDirectories(Paths.get(MinecraftDontStarve.defaultSavePath));
				Files.createFile(path);
			}
			path = Paths.get(MinecraftDontStarve.defaultSavePath+MinecraftDontStarve.seasonSaveFile);
			BufferedWriter writer = Files.newBufferedWriter(path);
			writer.write(MinecraftDontStarve.current_season.name());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		Debug.out(MinecraftDontStarve.seasonSaveFile + " saved!");
		return true;
	}
	
	public static boolean readSeasonFromFile()
	{
		BufferedReader reader = null;
		
		try {
			Path path = Paths.get(MinecraftDontStarve.defaultSavePath+MinecraftDontStarve.seasonSaveFile);
			if (!Files.exists(path))
			{
				if(!writeSeasonToFile())
				{
					return false;
				}
			}
			
			reader = Files.newBufferedReader(path);
			String seasonInFile = reader.readLine();
			reader.close();
			
			if(seasonInFile == null || seasonInFile.isEmpty())
			{
				return false;
			}
			
			for(Season season : Season.values())
			{
				if(seasonInFile.equals(season.name()))
				{
					Debug.out(MinecraftDontStarve.seasonSaveFile + " loaded!");
					MinecraftDontStarve.current_season = season;
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;	
	}
}
