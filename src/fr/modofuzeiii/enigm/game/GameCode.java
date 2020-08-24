package fr.modofuzeiii.enigm.game;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.modofuzeiii.enigm.EnigmMain;

public class GameCode implements CommandExecutor {
	
	/*
	 * Récupérer la team du joueur: enigmMain.nmapPlayersTeam.get(p.getUniqueId())
	 * 
	 * Récupérer le nombre de points de la team du joueur : enigmMain.sbHandler.getInternalTeamPoints(String team)
	 * 
	 * Modifier le nombre de points de la team du joueur : enigmMain.sbHandler.setInternalTeamPoints(String team)
	 * 
	 * Récupérer l'état du challenge en cours (aucune épreuve validée = 0): this.getStateChallengeTeam(String team)
	 * 
	 * Augmenter de 1 le compteur de challenge de l'équipe : this.increaseChallengeTeam(String team)
	 */
	
	private EnigmMain enigmMain;
	
	public GameCode(EnigmMain enigmMain) {
		this.enigmMain = enigmMain;
	}
	
	private int getStateChallengeTeam(String team) {
		switch(team) {
		  case "rouge":
			  return this.enigmMain.redStateChallenge;
			
		  case "bleu":
			  return this.enigmMain.blueStateChallenge;
			
		  case "vert":
			  return this.enigmMain.greenStateChallenge;
			
		  case "jaune":
			  return this.enigmMain.yellowStateChallenge;
		
			
		  default:
			  return 0;
		   
     	}

	}
	
	private void increaseChallengeTeam(String team) {
		switch(team) {
		  case "rouge":
			  enigmMain.redStateChallenge++;
			break;
		  case "bleu":
			  enigmMain.blueStateChallenge++;
			break;
		  case "vert":
			  enigmMain.greenStateChallenge++;
			break;
		  case "jaune":
			  enigmMain.yellowStateChallenge++;
			break;
			
		  default:
			  
		    break;
     	}

	}

	private String pf = "§e§l[§b§lEnigm§e§l] §r";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
		Player p = (Player)sender;	
			
			if(cmd.getName().equalsIgnoreCase("ecode")) {
				if(args.length == 0){
					p.sendMessage("§6§l» §rErreur ! La commande est : /ecode <message>");
				}
				if(args.length >= 1) {
					/* Les Objets */
					if(args[0].equalsIgnoreCase("excalibur")) {
						p.sendMessage(pf +"La puissance d'excalibur vous revient !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						Excalibur(p);
					}
					else if(args[0].equalsIgnoreCase("water")) {
						p.sendMessage(pf +"La puissance de Poséidon vous revient !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						Poseidon(p);
					}
				
					/* Les Codes */
					else if(args[0].equalsIgnoreCase("5uYkjhlm")) { // Code Dropper
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
					}
					else if(args[0].equalsIgnoreCase("code")) { // Code Redstone
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
					}
					else if(args[0].equalsIgnoreCase("3erTfGBns4")) { // Code JumpNLever
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
					}
					else if(args[0].equalsIgnoreCase("Fanatmoche")) { // Code Arena
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
					}
					else if(args[0].equalsIgnoreCase("Oubliezpaslefeedback")) { // Code Dome Water
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
					}
					else {
						p.sendMessage("§6§l» §rErreur ! Mauvais code :)");	
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 0.7F);
					}
				}
			}
		}
		return false;
	}
	public void Excalibur(Player p) {
		ItemStack exca = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta excaM = exca.getItemMeta();
		excaM.setDisplayName("§c§k§l||| §e§l§oExcalibur §c§k§l|||");
		excaM.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		exca.setItemMeta(excaM);
		p.getInventory().setItem(8, exca);
		p.updateInventory();
	}
	public void Poseidon(Player p) {
		ItemStack pose = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta poseM = pose.getItemMeta();
		poseM.setDisplayName("§c§k§l||| §e§l§oPoséidon §c§k§l|||");
		poseM.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
		pose.setItemMeta(poseM);
		p.getInventory().setItem(7, pose);
		p.updateInventory();
	}
}
