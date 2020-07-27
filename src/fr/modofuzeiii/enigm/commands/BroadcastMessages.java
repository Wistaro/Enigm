package fr.modofuzeiii.enigm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastMessages implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			StringBuilder message = new StringBuilder();
			
			for(int i = 0; i< args.length; i++) {
				message.append(args[i]);
				message.append(" ");
			}
			
			if(cmd.getName().equalsIgnoreCase("a")) {
			                    
			      for(Player p_online : Bukkit.getOnlinePlayers()) {
			            p_online.playSound(p_online.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1F, 1F);			            			                         
			      }
			      
			      Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.YELLOW+ "[" + ChatColor.AQUA + "Enigm" + ChatColor.YELLOW + "] " + ChatColor.RESET +message);      
			
			
			}
		
		}
		
		return false;

	}
}
