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
			
			if(cmd.getName().equalsIgnoreCase("a")) {
				if(args.length == 0){
					p.sendMessage("§c[Erreur]" + ChatColor.RESET +" La commande est : /a <message>");
				}
				
				if(args.length >= 1) {	 
					
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part +" ");
					}
					
					Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET + bc.toString());
					
				}              
			      for(Player p_online : Bukkit.getOnlinePlayers()) {
			            p_online.playSound(p_online.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.5F, 2F);			            			                         
			      } 
			
			
			}
		
		}
		
		return false;

	}
}
