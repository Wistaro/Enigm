package fr.modofuzeiii.enigm.game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("estart")) {
				
				/*
				 * Timer 30sec
				 * Title "bon jeu" etc + effets blindness etc
				 * Tp tout le monde au spawn (co dans message �pingl�s)
				 * scoreboard qui appara�t
				 * */
			}
			if(cmd.getName().equalsIgnoreCase("epause")) {
				
			}
			if(cmd.getName().equalsIgnoreCase("estop")) {
				
			}
		}
		
		return false;
	}

}
