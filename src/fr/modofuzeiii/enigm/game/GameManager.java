package fr.modofuzeiii.enigm.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import fr.modofuzeiii.enigm.EnigmMain;
import fr.modofuzeiii.enigm.ScoreBoardHandler;
import fr.modofuzeiii.enigm.TitleAPI;
import fr.modofuzeiii.enigm.commands.PointsManager;
import fr.modofuzeiii.enigm.tasks.ScoreboardUpdate;
import fr.modofuzeiii.enigm.tasks.StartupCounter;

public class GameManager implements CommandExecutor {
	
	public EnigmMain enigmMain;
	public ScoreBoardHandler currentSbHandler;
	private PointsManager pointsHandler;
	
	
	private ScoreboardUpdate gameTimer;
	private StartupCounter introCounter;
	
	public int gameTimeCounter;
	
	@SuppressWarnings("unused")
	private int currentSeconds;
	private int maxSeconds;
	
	@SuppressWarnings("unused")
	private BukkitTask taskChronoStart;
	
	public GameManager(EnigmMain mainClass, PointsManager pointsManagerClass) {
		
		enigmMain = mainClass;
		currentSbHandler = enigmMain.sbHandler;
		pointsHandler = pointsManagerClass;
		maxSeconds = 10;
		currentSeconds = 0;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;		
			
			if(cmd.getName().equalsIgnoreCase("estart")) {
				
				if(enigmMain.isGameStarted != 1) { //Si la partie n'a pas déjà commencée...
					
					introCounter = new StartupCounter(this);
					
					enigmMain.isGameStarted = 1;
					enigmMain.currentChronoAtStartup = 1;
					currentSbHandler.gameCounterSb = 0;
					
					if(args.length > 0) {
						
						if(args[0].equalsIgnoreCase("false")) {
							
							teleportAllPlayerForStart();
							initGame();	
						}
						
					}else {
						startIntroSequence();
					}
				}else {
					p.sendMessage("§6§l» §rImpossible de démarrer la partie, celle-ci est déjà en cours!");
				}
			}
			
			
			if(cmd.getName().equalsIgnoreCase("epause")) {
				
				
			}
			if(cmd.getName().equalsIgnoreCase("estop")) {
				
				if(enigmMain.isGameStarted == 1) {
					
					stopGame();
					
				}else {
					p.sendMessage("§6§l» §rImpossible de stopper la partie, aucune partie n'est en cours!");
				}
				
			}

		}
		
		return false;
	}
	
	private void startIntroSequence() {
		introCounter.runTaskTimer(enigmMain, 0, 20);
	}
	
	public void sendTitle2all(String message, String subtitle) {
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			 TitleAPI.sendTitle(p_online, 10, 20, 10, message, subtitle);
      } 
	}
	
	public void initGame() {
		
		gameTimer = new ScoreboardUpdate(this);	 
		
		pointsHandler.clearAllPoints();
		currentSbHandler.updateScoreboard4All();
		gameTimeCounter = 0;
		
		gameTimer.runTaskTimer(enigmMain, 0, 20);
		
	}
	
	public void stopGame() {
		
		enigmMain.isGameStarted = 0;
		gameTimeCounter = 0;
		currentSbHandler.updateScoreboard4All();
		
		if(gameTimer != null) { //if th game is stopped BEFORE gameTimer instanciation
		
			if(!gameTimer.isCancelled()) {
				gameTimer.cancel();
			}
		
		}
		
		
		
		for(Player p_online : Bukkit.getOnlinePlayers()) { //remove all potions effects
		
			 for (PotionEffect effect : p_online.getActivePotionEffects()) {
				 p_online.removePotionEffect(effect.getType());		        
			 }
		 
		}
		
		sendTitle2all("§cEnigm", "§cFin de la partie!");
	}
	
	//Teleport all player for starting the game
	
	public void teleportAllPlayerForStart() {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			
      	  Location spawn = new Location(p_online.getWorld(),6.5 ,223 ,0-25.5);
      	  p_online.teleport(spawn);
      	  p_online.playSound(p_online.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1F, 0.5F);
		} 
	}
	
	public void playSound4all(Sound sound, float volume, float pitch) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
     		 p_online.playSound(p_online.getLocation(), sound , volume, pitch);
       }
		
	}
	
	public void addPotionEffect4all(PotionEffectType effectType, int duration, int ampli, boolean ambiant) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			p_online.addPotionEffect(new PotionEffect(effectType, duration, ampli, ambiant));
       }
		
	}

}
