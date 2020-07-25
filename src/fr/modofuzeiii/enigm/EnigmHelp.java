package fr.modofuzeiii.enigm;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EnigmHelp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			/*==============[Commandes]=============*/
			
			if(cmd.getName().equalsIgnoreCase("ehelp")) {
				if(args.length == 0) {
					p.sendMessage("§6------------------------------------------");
					p.sendMessage("");
					p.sendMessage("§e§l            §7(§e1§7/§e2§7) §e§lEnigm");
					p.sendMessage("");
					p.sendMessage("§e/etest §7Tester si le plugin fonctionne");
					p.sendMessage("§e/a <message> §7Envoyer une annonce à tout le monde");
					p.sendMessage("§e/estart §7Démarrer la partie");
			        p.sendMessage("");
			        p.sendMessage("§6------------------------------------------");
			        System.out.println("Le ehelp fonctionne correctement !");
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("etest")) {
				if(args.length == 0) {
					p.sendMessage("Le plugin fonctionne correctement !");
					System.out.println("Le plugin fonctionne correctement !");
				}
			}
			
			
		}/*Fin du IF général*/
		
		return false;
	}

}
