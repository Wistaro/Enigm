package fr.modofuzeiii.enigm.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpEnigmPlugin implements CommandExecutor {
	
	int e = 0;
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			/*==============[Commandes]=============*/
			
			if(cmd.getName().equalsIgnoreCase("ehelp")) {
				if(args.length == 0) {
					if(e == 2) {
						p.sendMessage("§6---------------------------------");
						p.sendMessage("");
						p.sendMessage("§e§l            §7(§e3§7/§e3§7) §e§lEnigm");
						p.sendMessage("");
						p.sendMessage("§e/etest §7Tester si le plugin fonctionne.");
						p.sendMessage("§e/egm §7Mode administrateur.");
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage("§6---------------------------------");
						e=0;
					}
					else if(e == 1) {
						p.sendMessage("§6---------------------------------");
						p.sendMessage("");
						p.sendMessage("§e§l            §7(§e2§7/§e3§7) §e§lEnigm");
						p.sendMessage("");
						p.sendMessage("§e/estart §7Commencer la partie.");
						p.sendMessage("§e/estop §7Stopper la partie.");
						p.sendMessage("§e/epause §7Mettre en pause la partie.");
						p.sendMessage("");
						p.sendMessage("§6---------------------------------");
						e++;
					}
					else if(e == 0) {
						p.sendMessage("§6---------------------------------");
						p.sendMessage("");
						p.sendMessage("§e§l            §7(§e1§7/§e3§7) §e§lEnigm");
						p.sendMessage("");
						p.sendMessage("§e/ecode <code> §7Commande utile au jeu.");
						p.sendMessage("§e/a <message> §7Envoyer une annonce à tout le monde.");
						p.sendMessage("§e/ehelp §7Réutilisez la commande pour plus de pages.");
						p.sendMessage("");
						p.sendMessage("§6---------------------------------");	
						e++;
					}
					else {
						p.sendMessage("Erreur : Commande inconnue !");
					}
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
