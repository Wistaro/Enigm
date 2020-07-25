package fr.modofuzeiii.enigm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnigmBroadcast implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			String message = "";
			
			for(int i = 0; i< args.length; i++) {
				message+=args[i];
			}
			
			if(cmd.getName().equalsIgnoreCase("a")) {
				if(args.length == 1) {
					Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.RED+"[Enigm Info] "+message);
				}
			}
			
			
		}
		
		return false;
	}

}
