package me.Josvth.RandomSpawn.Commands;

import java.util.List;

import me.Josvth.RandomSpawn.RandomSpawnCommandExecutor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomSpawnEnableCommand extends RandomSpawnCommandExecutor{
	
	public RandomSpawnEnableCommand(){
		this.name = "enable";
	}

	public boolean onCommand(CommandSender sender, List<String> args){
		Player player = (Player) sender;
		String worldname = player.getWorld().getName();
		plugin.getYamlHandler().worlds.set(worldname + ".randomspawnenabled", true);
		
		if (!(plugin.getYamlHandler().worlds.contains(worldname +".spawnarea"))){
			plugin.getYamlHandler().worlds.set(worldname + ".spawnarea.x-min", -100);
			plugin.getYamlHandler().worlds.set(worldname + ".spawnarea.x-max", 100);
			plugin.getYamlHandler().worlds.set(worldname + ".spawnarea.z-min", -100);
			plugin.getYamlHandler().worlds.set(worldname + ".spawnarea.z-max", 100);
		}
		
		plugin.getYamlHandler().saveWorlds();
		plugin.playerInfo(player, "Random Spawn is now enabled in this world!");
		return true;
	}
}
