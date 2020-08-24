package fr.modofuzeiii.enigm.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionBarTask extends BukkitRunnable {

	@Override
	public void run() {
		sendActionBarAllPlayers("§6§l» §r§c§l Votre équipe: §r <enigmPlayerTeam>");

	}

	public void sendActionBarAllPlayers(String message) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			p_online.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent(message));
		}
	 }
}
