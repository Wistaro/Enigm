package fr.modofuzeiii.enigm;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.modofuzeiii.enigm.gui.AdminApplyGui;
import fr.modofuzeiii.enigm.gui.AdminBonusGui;
import fr.modofuzeiii.enigm.gui.AdminCompassGui;
import fr.modofuzeiii.enigm.gui.AdminMalusBonusGui;
import fr.modofuzeiii.enigm.gui.AdminMalusGui;

public class AdminEvents implements Listener {
	
	int effect = 0;
	
	@EventHandler
	public void OnItemClick(PlayerInteractEvent ev){ //LES ITEMS DANS LA MAIN
		Player p = ev.getPlayer();
		ItemStack clickItem = p.getInventory().getItemInMainHand();

		
		if(clickItem.getType() == Material.COMPASS && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleporter")) {
			p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 10, 1F);
			new AdminCompassGui(p);
		}
		if (clickItem.getType() == Material.GREEN_WOOL && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§aCreative Mode")) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage("§e§l[§b§lEnigm§e§l]§r Vous passez en mode : §aCreatif");
		}
		if (clickItem.getType() == Material.RED_WOOL && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§cSpectator Mode")) {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("§e§l[§b§lEnigm§e§l]§r Vous passez en mode : §cSpectateur");
		}
		if (clickItem.getType() == Material.BOOK && clickItem.hasItemMeta() && clickItem.getItemMeta().hasDisplayName() && clickItem.getItemMeta().getDisplayName().equalsIgnoreCase("§dEffects")) {
			p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1F);
			new AdminMalusBonusGui(p);
		}
	} //Fin PlayerInteractEvent
	
	
	@EventHandler
	public void OnClick(InventoryClickEvent e) { //ITEMS DANS LES GUI
		e.getInventory();
		Player p = (Player) e.getWhoClicked();
		ItemStack current = e.getCurrentItem();
		if(current == null) return;
		
		if (current.getType() == Material.GREEN_STAINED_GLASS_PANE && current.getItemMeta().getDisplayName().equalsIgnoreCase("§a§l§oBONUS")) {
			p.closeInventory();
			new AdminBonusGui(p);
		}
		if (current.getType() == Material.RED_STAINED_GLASS_PANE && current.getItemMeta().getDisplayName().equalsIgnoreCase("§c§l§oMALUS")) {
			p.closeInventory();
			new AdminMalusGui(p);
		}
		if (current.getType() == Material.BARRIER && current.getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")) {
			p.closeInventory();
			new AdminMalusBonusGui(p);
		}
		if (current.getType() == Material.BARRIER && current.getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")) {
			p.closeInventory();
			new AdminMalusBonusGui(p);
		}
		if (current.getType() == Material.FEATHER && current.getItemMeta().getDisplayName().equalsIgnoreCase("§eSpeed")) {
			p.closeInventory();
			effect = effect + 1;
			new AdminApplyGui(p);
		}
		if (current.getType() == Material.GOLDEN_PICKAXE && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Haste")) {
			p.closeInventory();
			effect = effect + 2;
			new AdminApplyGui(p);
		}
		if (current.getType() == Material.ANVIL && current.getItemMeta().getDisplayName().equalsIgnoreCase("§7Slowness")) {
			p.closeInventory();
			effect = effect + 3;
			new AdminApplyGui(p);
		}
		if (current.getType() == Material.FERMENTED_SPIDER_EYE && current.getItemMeta().getDisplayName().equalsIgnoreCase("§8Blindness")) {
			p.closeInventory();
			effect = effect + 4;
			new AdminApplyGui(p);
		}
		if (current.getType() == Material.PACKED_ICE && current.getItemMeta().getDisplayName().equalsIgnoreCase("§bFreeze")) {
			p.closeInventory();
			effect = effect + 5;
			new AdminApplyGui(p);
		}
		if (current.getType() == Material.PHANTOM_MEMBRANE && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aNausea")) {
			p.closeInventory();
			effect = effect + 6;
			new AdminApplyGui(p);
		}
	}//FIN ITEMS DANS LES GUI
	
	
	@EventHandler
    public void onInventoryClick(final InventoryDragEvent e) { //Evite de pouvoir prendre les trucs dans l'inventaire
        
		if(e.getView() != null) {	
			if(e.getView().getTitle().equalsIgnoreCase("§3§lSe TP a un joueur")) {
				e.setCancelled(true);
			}
    	}
		
		if(e.getView() != null) {	
			if(e.getView().getTitle().equalsIgnoreCase("§a§l§oBONUS §c§l§oMALUS")) {
				e.setCancelled(true);
			}
    	}
		
		if(e.getView() != null) {	
			if(e.getView().getTitle().equalsIgnoreCase("§a§l§oBONUS")) {
				e.setCancelled(true);
			}
    	}
		
		if(e.getView() != null) {	
			if(e.getView().getTitle().equalsIgnoreCase("§a§l§oMALUS")) {
				e.setCancelled(true);
			}
    	}
		
		if(e.getView() != null) {	
			if(e.getView().getTitle().equalsIgnoreCase("§3§lAppliquer l'effet à :")) {
				e.setCancelled(true);
			}
    	}
	} //FIN "Evite de pouvoir prendre les trucs dans l'inventaire"
	
	
	 @EventHandler
	  public void onclickMenuCompass(InventoryClickEvent e){ //CLICK ON THE HEAD
		 Player p = (Player)e.getWhoClicked();
		 
		 /*****PARTIE SUR LE TP DU COMPASS****/
		 
		 if(e.getCurrentItem() != null) {		 
			 if(e.getView().getTitle().equalsIgnoreCase("§3§lSe TP a un joueur")) {
				String psdPlayer = e.getCurrentItem().getItemMeta().getDisplayName();
				Player player2teleport = Bukkit.getPlayer(psdPlayer);
				Location posPlayer = player2teleport.getLocation();
				p.teleport(posPlayer);
			}
		 }
		 
		 
		 /*****PARTIE SUR LES EFFETS MALUS BONUS****/
		 
		 if(e.getCurrentItem() != null) {		 
			 if(e.getView().getTitle().equalsIgnoreCase("§3§lAppliquer l'effet à :")) { //Apply les effets
				 p.closeInventory();
				 if(effect == 1) {
					 p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*15, 1, false)); //20 car 1sec = 20 TICKS donc 20*<le nombre de secondes>
					 effect = 0;
					 Bukkit.broadcastMessage("§e§l[§b§lEnigm§e§l]§r Un §a§lBONHEUR §rva s'abattre sur quelqu'un !");
					 for(Player p_online : Bukkit.getOnlinePlayers()) {
				            p_online.playSound(p_online.getLocation(), Sound.ENTITY_CAT_AMBIENT, 0.5F, 1F);			            			                         
				      } 
				 }
				 if(effect == 2) {
					 p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*20, 2, false));
					 effect = 0;
					 Bukkit.broadcastMessage("§e§l[§b§lEnigm§e§l]§r Un §a§lBONHEUR §rva s'abattre sur quelqu'un !");
					 for(Player p_online : Bukkit.getOnlinePlayers()) {
				            p_online.playSound(p_online.getLocation(), Sound.ENTITY_CAT_AMBIENT, 0.5F, 1F);			            			                         
				      } 
				 }
				 if(effect == 3) {
					 p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*10, 1, false));
					 effect = 0;
					 Bukkit.broadcastMessage("§e§l[§b§lEnigm§e§l]§r Un §c§lMALHEUR §rva s'abattre sur quelqu'un !");
					 for(Player p_online : Bukkit.getOnlinePlayers()) {
				            p_online.playSound(p_online.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.5F, 1F);			            			                         
				      } 
				 }
				 if(effect == 4) {
					 p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*5, 0, false));
					 effect = 0;
					 Bukkit.broadcastMessage("§e§l[§b§lEnigm§e§l]§r Un §c§lMALHEUR §rva s'abattre sur quelqu'un !");
					 for(Player p_online : Bukkit.getOnlinePlayers()) {
				            p_online.playSound(p_online.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.5F, 1F);			            			                         
				      } 
				 }
				 if(effect == 5) {
					 p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*5, 255, false));
					 effect = 0;
					 Bukkit.broadcastMessage("§e§l[§b§lEnigm§e§l]§r Un §c§lMALHEUR §rva s'abattre sur quelqu'un !");
					 for(Player p_online : Bukkit.getOnlinePlayers()) {
				            p_online.playSound(p_online.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.5F, 1F);			            			                         
				      } 
				 }
				 if(effect == 6) {
					 p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*15, 1, false));
					 effect = 0;
					 Bukkit.broadcastMessage("§e§l[§b§lEnigm§e§l]§r Un §c§lMALHEUR §rva s'abattre sur quelqu'un !");
					 for(Player p_online : Bukkit.getOnlinePlayers()) {
				            p_online.playSound(p_online.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.5F, 1F);			            			                         
				      } 
				 } 
			}
		 }
	 }//FIN CLICK ON THE HEAD
}
