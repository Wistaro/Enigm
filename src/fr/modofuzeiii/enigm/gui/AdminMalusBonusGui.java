package fr.modofuzeiii.enigm.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminMalusBonusGui implements Listener {
	public Inventory gui;
	
	public AdminMalusBonusGui(Player p) {
		
		/* GUI Setup
		 * */
    	gui =  Bukkit.getServer().createInventory(p, 27, "§a§l§oBONUS §c§l§oMALUS");
    	
    	/* Create All Items
    	 * */
    	ItemStack malus = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    	ItemStack bonus = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    	ItemMeta malusM = malus.getItemMeta();
    	ItemMeta bonusM = bonus.getItemMeta();
    	
    	/* Setup All Items
    	 * */
    	malusM.setDisplayName("§c§l§oMALUS");
    	bonusM.setDisplayName("§a§l§oBONUS");
    	malus.setItemMeta(malusM);
    	bonus.setItemMeta(bonusM);
    	
    	p.openInventory(gui);
    	p.openInventory(gui).setItem(15, malus);
    	p.openInventory(gui).setItem(11, bonus);
    	p.updateInventory();
    
	}
}
