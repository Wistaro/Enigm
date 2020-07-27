package fr.modofuzeiii.enigm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EnigmAdminCommands implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			
			if(cmd.getName().equalsIgnoreCase("egm")) {
					
				 PlayerInventory invPlayer = p.getInventory();
				 invPlayer.addItem(new ItemStack(Material.COMPASS));
				 
			     p.sendMessage("Tu as reçu les items administrateur de Enigm");
			                                     
			}
			
			
		}
		
		return false;
	}

}
