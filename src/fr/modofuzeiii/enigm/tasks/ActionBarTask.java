package fr.modofuzeiii.enigm.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.modofuzeiii.enigm.EnigmMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionBarTask extends BukkitRunnable {
	
	EnigmMain mainInstance;
	
	public ActionBarTask(EnigmMain mainInstance) {
		this.mainInstance = mainInstance;
	}

	@Override
	public void run() {

		sendActionBarAllPlayers("§c§l Votre équipe: §r");

	}

	public void sendActionBarAllPlayers(String message) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			p_online.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(message+mainInstance.mapPlayersTeam.get(p_online.getUniqueId())));
		}
	 }
}
