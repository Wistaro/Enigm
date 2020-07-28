package fr.modofuzeiii.enigm.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminCompassGui implements Listener {
	
	private Inventory gui;
	int numbersOfPlayers;

    public AdminCompassGui(Player p) {
    	
    	 //Initialisation
    	
    	numbersOfPlayers = Bukkit.getOnlinePlayers().size();
    	gui =  Bukkit.getServer().createInventory(p, 9, "§3§lSe TP a un joueur");
    	 
    	 
    	 //Build the interface
    	 FillWithConnectedPlayer();
    	 
    	 //Open the interface
         p.openInventory(gui);
         
         
	}
    
    private void FillWithConnectedPlayer() {
    		//remplie l'inventaire avec les joueurs connectés pour pouvoir s'y tp en cliquant
    	 ItemStack survival = new ItemStack (Material.PLAYER_HEAD);
         ItemMeta survivalMeta = survival.getItemMeta();
        
        
         survival.setItemMeta(survivalMeta);
        
         //This is where you decide what slot the item goes into
         gui.setItem(0, survival);
         gui.setItem(1, survival);
         gui.setItem(2, survival);
         gui.setItem(3, survival);
         gui.setItem(4, survival);
         gui.setItem(5, survival);
         gui.setItem(6, survival);
         gui.setItem(6, survival);
         gui.setItem(7, survival);
         gui.setItem(8, survival);
     }
    	
    	
    

    

}
