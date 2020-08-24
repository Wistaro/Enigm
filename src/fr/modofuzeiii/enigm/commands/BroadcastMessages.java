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
					p.sendMessage("§6§l» §rErreur ! La commande est : /a <message>");
				}
				
				if(args.length >= 1) {	 
					
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part +" ");
					}
					
					Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lAnnonce" + ChatColor.YELLOW + "§l] §6§l" + p.getName() + "§r: " + bc.toString());
					
				}              
			      for(Player p_online : Bukkit.getOnlinePlayers()) {
			            p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 2F);			            			                         
			      } 
			
			
			}
		
		}
		
		return false;

	}
}
