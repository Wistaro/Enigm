package fr.modofuzeiii.enigm.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
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
		
		gameTimer = new ScoreboardUpdate(this);	 
		introCounter = new StartupCounter(this);
		
		maxSeconds = 10;
		currentSeconds = 0;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			
			
			if(cmd.getName().equalsIgnoreCase("estart")) {
				
				if(enigmMain.isGameStarted != 1) { //Si la partie n'a pas déjà commencée...
				
					/*
					 * Timer 30sec Done
					 * Title "bon jeu" etc + effets blindness etc Done
					 * Tp tout le monde au spawn (co dans message épinglés) Done
					 * scoreboard qui apparaît Done
					 * */
					
					 
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
					p.sendMessage("Impossible de démarrer la partie, celle -ci est déjà en cours!");
				}
			}
			
			
			if(cmd.getName().equalsIgnoreCase("epause")) {
				
			}
			if(cmd.getName().equalsIgnoreCase("estop")) {
				
				enigmMain.isGameStarted = 0;
				gameTimeCounter = 0;
				currentSbHandler.updateScoreboard4All();
				gameTimer.cancel();
				
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
	
	public  void initGame() {
		
		introCounter.cancel();
		
		pointsHandler.clearAllPoints();
		currentSbHandler.updateScoreboard4All();
		
		//todo: start general counter (in scoreboard)
		gameTimeCounter = 0;
		
		gameTimer.runTaskTimer(enigmMain, 0, 20);
		
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
