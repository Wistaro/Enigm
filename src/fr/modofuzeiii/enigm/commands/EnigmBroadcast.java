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
			
			StringBuilder message = new StringBuilder();
			
			for(int i = 0; i< args.length; i++) {
				message.append(args[i]);
				message.append(" ");
			}
			
			if(cmd.getName().equalsIgnoreCase("a")) {
					Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.RED+"[Enigm Info] "+message);
			}
			
			
		}
		
		return false;
	}

}
