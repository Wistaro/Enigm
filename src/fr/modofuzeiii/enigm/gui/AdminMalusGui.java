package fr.modofuzeiii.enigm.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminMalusGui implements Listener {
	public Inventory guimalus;

	public AdminMalusGui(Player p) {
		
		/* GUI Setup
		 * */
    	guimalus = Bukkit.getServer().createInventory(p, 9, "§c§l§oMALUS");
    	
    	/* Create All Items
    	 * */
    	ItemStack retour1 = new ItemStack(Material.BARRIER);
    	ItemStack slow = new ItemStack(Material.ANVIL);
    	ItemStack freeze = new ItemStack(Material.PACKED_ICE);
    	ItemStack blind = new ItemStack(Material.FERMENTED_SPIDER_EYE);
    	ItemStack vomis = new ItemStack(Material.PHANTOM_MEMBRANE);
    	ItemMeta retour1M = retour1.getItemMeta();
    	ItemMeta slowM = slow.getItemMeta();
    	ItemMeta freezeM = freeze.getItemMeta();
    	ItemMeta blindM = blind.getItemMeta();
    	ItemMeta vomisM = vomis.getItemMeta();
    	
    	/* Setup All Items
    	 * */
    	retour1M.setDisplayName("§cRetour");
    	slowM.setDisplayName("§7Slowness");
    	freezeM.setDisplayName("§bFreeze");
    	blindM.setDisplayName("§8Blindness");
    	vomisM.setDisplayName("§aNausea");
    	retour1.setItemMeta(retour1M);
    	slow.setItemMeta(slowM);
    	freeze.setItemMeta(freezeM);
    	blind.setItemMeta(blindM);
    	vomis.setItemMeta(vomisM);
    	
    	/* Open Inv
    	 * */
    	p.openInventory(guimalus);
		p.openInventory(guimalus).setItem(0, slow);
		p.openInventory(guimalus).setItem(1, blind);
		p.openInventory(guimalus).setItem(2, vomis);
		p.openInventory(guimalus).setItem(3, freeze);
		p.openInventory(guimalus).setItem(8, retour1);
		p.updateInventory();
	}

}
