package fr.modofuzeiii.enigm.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminBonusGui implements Listener {

	public Inventory guibonus;
	public AdminBonusGui(Player p) {
		
		/* GUI Setup
		 * */
    	guibonus = Bukkit.getServer().createInventory(p, 9, "§a§l§oBONUS");
    	
    	/* Create All Items
    	 * */
    	ItemStack retour = new ItemStack(Material.BARRIER);
    	ItemMeta retourM = retour.getItemMeta();
    	retourM.setDisplayName("§cRetour");
    	retour.setItemMeta(retourM);
    	
    	/* Setup All Items
    	 * */
    	ItemStack speed = new ItemStack(Material.FEATHER);
    	ItemStack haste = new ItemStack(Material.GOLDEN_PICKAXE);
    	ItemMeta speedM = speed.getItemMeta();
    	ItemMeta hasteM = haste.getItemMeta();
    	speedM.setDisplayName("§eSpeed");
    	hasteM.setDisplayName("§6Haste");
    	speed.setItemMeta(speedM);
    	haste.setItemMeta(hasteM);
    	
    	/* Open Inv
    	 * */
		p.openInventory(guibonus);
		p.openInventory(guibonus).setItem(0, speed);
		p.openInventory(guibonus).setItem(1, haste);
		p.openInventory(guibonus).setItem(8, retour);
		p.updateInventory();
	}
}
