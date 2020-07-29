package fr.modofuzeiii.enigm.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class AdminCompassGui implements Listener {
	
	public Inventory gui;
	int numbersOfPlayers;
	Player currentPlayer;

    public AdminCompassGui(Player p) {
    	
    	 //Initialisation
    	
    	numbersOfPlayers = Bukkit.getOnlinePlayers().size();
    	gui =  Bukkit.getServer().createInventory(p, 18, "§3§lSe TP a un joueur");
    	currentPlayer = p;
    	 
    	 //Build the interface
    	 FillWithConnectedPlayer();
    	 
    	 //Open the interface
         p.openInventory(gui);
         
         
	}
    
    private void FillWithConnectedPlayer() {
    	
    	ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
    	SkullMeta meta = (SkullMeta) skull.getItemMeta();
    	String playerName;
        
         int i = 0;
         
         for (Player player : Bukkit.getOnlinePlayers()) {
        	 
        	 playerName = player.getName();
        	 
        	 meta.setOwner(playerName);
         	 meta.setDisplayName(playerName);
        	 skull.setItemMeta(meta);
        	 gui.setItem(i, skull);
        	 
        	 i++;
         }  
         
         
     }
       

}
