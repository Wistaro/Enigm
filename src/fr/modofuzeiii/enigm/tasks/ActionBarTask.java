package fr.modofuzeiii.enigm.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.modofuzeiii.enigm.EnigmMain;
import fr.modofuzeiii.enigm.ScoreBoardHandler;
import fr.modofuzeiii.enigm.commands.PointsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionBarTask extends BukkitRunnable {
	
	EnigmMain mainInstance;
	ScoreBoardHandler sbHandler;
	
	private int toggleMessage;
	
	public ActionBarTask(EnigmMain mainInstance) {
		this.mainInstance = mainInstance;
		this.sbHandler = this.mainInstance.sbHandler;
		toggleMessage = 0;
	}

	@Override
	public void run() {
		
		if(toggleMessage == 0) {

			sendActionBarTeamAllPlayers("§c§l Votre équipe: §r");
			toggleMessage = 1;
		
		}else {
			
			if(mainInstance.isGameStarted == 1) {
				sendActionBarPointsAllPlayers("§c§l Vous avez: §r");
			}else {
				sendActionBarMgAllPlayers("§c§lEn attente du lancement de la partie...");
			}
			
			
			toggleMessage = 0;
			
		}

	}

	public void sendActionBarTeamAllPlayers(String message) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			p_online.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(message+mainInstance.mapPlayersTeam.get(p_online.getUniqueId())));
		}
	 }
	
	public void sendActionBarPointsAllPlayers(String message) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			
			String playerTeam = mainInstance.mapPlayersTeam.get(p_online.getUniqueId());
			p_online.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(message+sbHandler.getInternalTeamPoints(playerTeam)+" points!"));
		}
	 }
	
	public void sendActionBarMgAllPlayers(String message) {
			
			for(Player p_online : Bukkit.getOnlinePlayers()) {
				
				String playerTeam = mainInstance.mapPlayersTeam.get(p_online.getUniqueId());
				p_online.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(message));
			}
		 }
}
