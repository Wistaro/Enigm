package fr.modofuzeiii.enigm;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import fr.modofuzeiii.enigm.gui.AdminCompassGui;

public class AdminEvents implements Listener {
	
	@EventHandler
	public void OnItemClick(PlayerInteractEvent ev){
		
		Player p = ev.getPlayer();
		ItemStack clickItem = p.getInventory().getItemInMainHand();
		
		
		//Todo : - vérifier les perms du joueur
		//       - Si le gars a 2 compass ça marche po
		
		if(clickItem.getType() == Material.COMPASS && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleporter")) {
			
			p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 10, 2F);
			
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
		if (clickItem.getType() == Material.GREEN_WOOL && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§aCreative Mode")) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage("§e§l[§b§lEnigm§e§l]§r Vous passez en mode : §aCreatif");
			
		}
		if (clickItem.getType() == Material.RED_WOOL && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§cSpectator Mode")) {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("§e§l[§b§lEnigm§e§l]§r Vous passez en mode : §cSpectateur");
		}
		
	}

}
