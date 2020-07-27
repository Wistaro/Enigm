package fr.modofuzeiii.enigm;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.modofuzeiii.enigm.gui.AdminCompassGui;


public class AdminEvents implements Listener {
	
	@EventHandler
	public void OnItemClick(PlayerInteractEvent ev){
		
		Player p = ev.getPlayer();
		ItemStack clickItem = p.getInventory().getItemInMainHand();
		
		
		//Todo : - vérifier les perms du joueur
		//       - Si le gars a 2 compass ça marche po
		
		if(clickItem.equals(new ItemStack(Material.COMPASS))) {
			
			p.sendMessage("Tu as cliqué sur la bousolle!");
			
			AdminCompassGui guiCompass = new AdminCompassGui(p); //open the compass GUI
			
			 
			
			 /* Inventory help = Bukkit.getServer().createInventory(p, 9, "Help");
			  
			  ItemStack ref1 = new ItemStack(Material.BOOK);
              ItemMeta metaref1 = ref1.getItemMeta();
              ArrayList<String> lore= new ArrayList<String>();

              lore.add(" ");
              lore.add("§for visit our site");
              lore.add(" ");
              lore.add("§atest.net/help");
              
              metaref1.setLore(lore);
              metaref1.setDisplayName("§6§lClick to get help");
      

              ref1.setItemMeta(metaref1);
  
          //Here opens the inventory
          p.openInventory(help);*/
		}
	}

}
