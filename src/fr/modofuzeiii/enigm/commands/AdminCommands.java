package fr.modofuzeiii.enigm.commands;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminCommands implements CommandExecutor {
	
	int r = 0;
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("egm")) {

				if(r == 1) {
					p.getInventory().clear();
					p.sendMessage("§e§l[§b§lEnigm§e§l]§r GameMaster mode : §cOff");
				    p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1, 1F);
					r = 0;
				}
				
				else {
				 ItemStack egmcomp = new ItemStack(Material.COMPASS);
				 ItemStack egmgm1 = new ItemStack(Material.GREEN_WOOL);
				 ItemStack egmgm3 = new ItemStack(Material.RED_WOOL);
				 
				 ItemMeta egmcompM = egmcomp.getItemMeta();
				 ItemMeta egmgm1M = egmgm1.getItemMeta();
				 ItemMeta egmgm3M = egmgm3.getItemMeta();
				 
				 
				 egmcompM.setDisplayName("§6Teleporter");
				 egmgm1M.setDisplayName("§aCreative Mode");
				 egmgm3M.setDisplayName("§cSpectator Mode");
				 
				 egmcomp.setItemMeta(egmcompM);
				 egmgm1.setItemMeta(egmgm1M);
				 egmgm3.setItemMeta(egmgm3M);
				 
				 p.getInventory().setItem(0, egmcomp);
				 p.getInventory().setItem(7, egmgm1);
				 p.getInventory().setItem(8, egmgm3);
				 p.updateInventory();
				 
			     p.sendMessage("§e§l[§b§lEnigm§e§l]§r GameMaster mode : §aOn");
			     p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5F);
			     r++;
				}
			}
			
		}
		
		return false;
	}

}
