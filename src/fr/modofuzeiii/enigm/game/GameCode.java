package fr.modofuzeiii.enigm.game;

import org.bukkit.Bukkit;
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
import fr.modofuzeiii.enigm.TitleAPI;

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
	
	private final int IDLE = 0;
	private final int DROPPER = 1;
	private final int REDSTONE = 2;
	private final int JUMPNLEVER = 3;
	private final int ARENA = 4;
	private final int AQUA = 5;
	
	private final int FIRST_PLACE = 16; 
	private final int SECOND_PLACE = 14; 
	private final int THIRD_PLACE = 12;
	private final int FOURTH_PLACE = 10;
	
	private final int FINAL_CHALLENGE  = 5;	
	private int pointsMalus = 1;
	
	public GameCode(EnigmMain enigmMain) {
		this.enigmMain = enigmMain;
	}
	
	private int getStateChallengeTeamPlayer(Player p) {
		
		String team = enigmMain.mapPlayersTeam.get(p.getUniqueId());
		
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
	
	private void increaseChallengeTeamPlayer(Player p) {
		
		String team = enigmMain.mapPlayersTeam.get(p.getUniqueId());
				
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
	
	
	private void increaseScoreTeam(Player p, int challenge) {
		
		
		String team = enigmMain.mapPlayersTeam.get(p.getUniqueId());
		int actualTeamPoints = enigmMain.sbHandler.getInternalTeamPoints(team);
		
		int initCapitalPoints = FIRST_PLACE;
		// 16 14 12 10
		int rouge = this.enigmMain.redStateChallenge;
		int bleu = this.enigmMain.blueStateChallenge;
		int vert = this.enigmMain.greenStateChallenge;
		int jaune = this.enigmMain.yellowStateChallenge;
		
		if(rouge >= challenge && !team.equals("rouge")) {
			initCapitalPoints = initCapitalPoints - 2;
		}
		if(jaune >= challenge && !team.equals("jaune")) {
			initCapitalPoints = initCapitalPoints - 2;
		}
		if(vert >= challenge && !team.equals("vert")) {
			initCapitalPoints = initCapitalPoints - 2;
		}
		if(bleu >= challenge && !team.equals("bleu")) {
			initCapitalPoints = initCapitalPoints - 2;
		}
		
		
		
		enigmMain.sbHandler.setInternalTeamPoints(team, actualTeamPoints + initCapitalPoints);	
		
		
				
	}
	
	private void malusScoreTeam(Player p) {
		
		String team = enigmMain.mapPlayersTeam.get(p.getUniqueId());
		int actualTeamPoints = enigmMain.sbHandler.getInternalTeamPoints(team);
		
		enigmMain.sbHandler.setInternalTeamPoints(team, actualTeamPoints - pointsMalus);	
				
	}

	private String pf = "§e§l[§b§lEnigm§e§l] §r";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
		Player p = (Player)sender;	
		
		int stateChallengePlayer = this.getStateChallengeTeamPlayer(p);
			
			if(cmd.getName().equalsIgnoreCase("ecode")) {
				if(args.length == 0){
					p.sendMessage("§6§l» §rErreur ! La commande est : /ecode <message>");
				}
				if(args.length >= 1) {
					
					/* Les Objets */
					if(args[0].equals("excalibur") && stateChallengePlayer >= JUMPNLEVER && stateChallengePlayer < ARENA ) {
						p.sendMessage(pf +"La puissance d'excalibur vous revient !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						Excalibur(p);
					}
					else if(args[0].equals("water") && stateChallengePlayer >= ARENA) {
						p.sendMessage(pf +"La puissance de Poséidon vous revient !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						Poseidon(p);
					}
				
					/* Les Codes */
					
					else if(args[0].equals("5uYkjhlm") && stateChallengePlayer == IDLE) { // Code Dropper
						
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						increaseChallengeTeamPlayer(p);
						increaseScoreTeam(p, DROPPER);
					}
					else if(args[0].equals("code")  && stateChallengePlayer == DROPPER) { // Code Redstone
						
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						increaseChallengeTeamPlayer(p);
						increaseScoreTeam(p, REDSTONE);
					}
					else if(args[0].equals("3erTfGBns4")  && stateChallengePlayer == REDSTONE ) { // Code JumpNLever
						
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						increaseChallengeTeamPlayer(p);
						increaseScoreTeam(p, JUMPNLEVER);
					}
					else if(args[0].equals("Fanatmoche")  && stateChallengePlayer == JUMPNLEVER) { // Code Arena
						
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						increaseChallengeTeamPlayer(p);
						increaseScoreTeam(p, ARENA);
					}
					else if(args[0].equals("Oubliezpaslefeedback")  && stateChallengePlayer == ARENA ) { // Code Dome Water
						
						p.sendMessage(pf +"Code bon !");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
						increaseChallengeTeamPlayer(p);
						increaseScoreTeam(p, AQUA);
						
						finish(p);
						
					}
					else {
						p.sendMessage("§6§l» §rErreur ! Mauvais code ou pas au bon moment :)");	
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 0.7F);
						malusScoreTeam(p);
					}
				}
			}
		}
		return false;
	}
	
	private void finish(Player p) {
		
		String team = enigmMain.mapPlayersTeam.get(p.getUniqueId());

		int rouge = this.enigmMain.redStateChallenge;
		int bleu = this.enigmMain.blueStateChallenge;
		int vert = this.enigmMain.greenStateChallenge;
		int jaune = this.enigmMain.yellowStateChallenge;
		
		int scoreComplet = rouge + vert + bleu + jaune ;
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			
			 TitleAPI.sendTitle(p_online, 10, 20, 10, "L'équipe "+team, "s'est échappée de Enigm!");
			 p_online.playSound(p_online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
			 p_online.playSound(p_online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
		} 
		
		Bukkit.broadcastMessage("COUNT : "+scoreComplet);
		
		if(scoreComplet == (4 * FINAL_CHALLENGE ) ) {
			
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(Player p_online : Bukkit.getOnlinePlayers()) {
				 TitleAPI.sendTitle(p_online, 10, 20, 10, "Toutes les équipes", "se sont échappées!");
			} 
		}
		
	}
	private void Excalibur(Player p) {
		ItemStack exca = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta excaM = exca.getItemMeta();
		excaM.setDisplayName("§c§k§l||| §e§l§oExcalibur §c§k§l|||");
		excaM.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		exca.setItemMeta(excaM);
		p.getInventory().setItem(8, exca);
		p.updateInventory();
	}
	private void Poseidon(Player p) {
		ItemStack pose = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta poseM = pose.getItemMeta();
		poseM.setDisplayName("§c§k§l||| §e§l§oPoséidon §c§k§l|||");
		poseM.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
		pose.setItemMeta(poseM);
		p.getInventory().setItem(7, pose);
		p.updateInventory();
	}
}
