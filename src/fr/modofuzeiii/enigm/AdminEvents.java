package fr.modofuzeiii.enigm;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class AdminEvents implements Listener {
	
	@EventHandler
	public void OnItemClick(PlayerInteractEvent ev){
		
		Player p = ev.getPlayer();
		ItemStack clickItem = p.getInventory().getItemInMainHand();
		
		//Todo : - vérifier les perms du joueur
		//       - Si le gars a 2 compass ça marche po
		
		if(clickItem.equals(new ItemStack(Material.COMPASS))) {
			//open GUI  - 
		}
	}

}
