package fr.modofuzeiii.enigm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class joinLeaveEvents implements Listener {
	
	private EnigmMain enigmMain;
	
	public joinLeaveEvents(EnigmMain mainInstance) {
		enigmMain = mainInstance;
	}
	
	@EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        
        Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET + "Le joueur " + p.getName() + " vient de se connecter!" );
        
        ScoreBoardHandler ScoreBoardHandler = new ScoreBoardHandler(p, enigmMain);
        ScoreBoardHandler.setupSb();
        
        
        
    }
	
	@EventHandler
    public void onJoin(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        
        Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET + "Le joueur " + p.getName() + " vient de se déconnecter!" );

    }
}
