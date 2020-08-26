package fr.modofuzeiii.enigm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlaySong implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("playsong")) {
				
				if(args[0].equalsIgnoreCase("decide")) {
					playSong4All("enigm.decide");
					p.sendMessage("Tu as envoyé le son décide!");
					
				}else if(args[0].equalsIgnoreCase("welcome")) {
					playSong4All("enigm.welcome");
					p.sendMessage("Tu as envoyé le son welcome!");
					
				}else if(args[0].equalsIgnoreCase("intro")) {
					playSong4All("enigm.intro");
					p.sendMessage("Tu as envoyé le son intro!");
					
				}else if(args[0].equalsIgnoreCase("mario")) {
					playSong4All("enigm.mario");
					p.sendMessage("Tu as envoyé le son intro!");	
					
				}else {
					return false;
				}
			}
		}
			
		return false;
	}
	
	private void playSong4All(String song) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			p_online.playSound(p_online.getLocation(), song , 1F, 1F);
		}
	}

}
