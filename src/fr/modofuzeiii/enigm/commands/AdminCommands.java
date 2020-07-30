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
				
				/* EGM MODE OFF
				 * */
				if(r == 1) {
					p.getInventory().clear();
					p.sendMessage("§e§l[§b§lEnigm§e§l]§r GameMaster mode : §cOff");
				    p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1, 1F);
					r = 0;
				}
				else {
				/* Create All Items "/egm"
				 * */
				 ItemStack egmcomp = new ItemStack(Material.COMPASS);
				 ItemStack egmgm1 = new ItemStack(Material.GREEN_WOOL);
				 ItemStack egmgm3 = new ItemStack(Material.RED_WOOL);
				 ItemStack egmmb = new ItemStack(Material.BOOK);
				 ItemMeta egmcompM = egmcomp.getItemMeta();
				 ItemMeta egmgm1M = egmgm1.getItemMeta();
				 ItemMeta egmgm3M = egmgm3.getItemMeta();
				 ItemMeta egmmbM = egmmb.getItemMeta();
				 
				 /* Setup All items
				  * */
				 egmcompM.setDisplayName("§6Teleporter");
				 egmgm1M.setDisplayName("§aCreative Mode");
				 egmgm3M.setDisplayName("§cSpectator Mode");
				 egmmbM.setDisplayName("§dEffects");
				 egmcomp.setItemMeta(egmcompM);
				 egmgm1.setItemMeta(egmgm1M);
				 egmgm3.setItemMeta(egmgm3M);
				 egmmb.setItemMeta(egmmbM);
				 p.getInventory().setItem(0, egmcomp);
				 p.getInventory().setItem(7, egmgm1);
				 p.getInventory().setItem(8, egmgm3);
				 p.getInventory().setItem(4, egmmb);
				 p.updateInventory();
				 
				 /* EGM MODE ON
				  * */
			     p.sendMessage("§e§l[§b§lEnigm§e§l]§r GameMaster mode : §aOn");
			     p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5F);
			     r++;
				}
			}
			
		}
		
		return false;
	}

}
