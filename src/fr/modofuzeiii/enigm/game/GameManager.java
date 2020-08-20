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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.modofuzeiii.enigm.EnigmMain;
import fr.modofuzeiii.enigm.ScoreBoardHandler;
import fr.modofuzeiii.enigm.TitleAPI;
import fr.modofuzeiii.enigm.commands.PointsManager;

public class GameManager implements CommandExecutor {
	
	private EnigmMain enigmMain;
	private ScoreBoardHandler currentSbHandler;
	private PointsManager pointsHandler;
	
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
				
				/*
				 * Timer 30sec
				 * Title "bon jeu" etc + effets blindness etc
				 * Tp tout le monde au spawn (co dans message épinglés)
				 * scoreboard qui apparaît
				 * */
				enigmMain.isGameStarted = 1;

				
				enigmMain.currentChronoAtStartup = 1;
				launchStartupSequence();
				
			}
			if(cmd.getName().equalsIgnoreCase("epause")) {
				
			}
			if(cmd.getName().equalsIgnoreCase("estop")) {
				
				enigmMain.isGameStarted = 0;
				currentSbHandler.updateScoreboard4All();
				
			}
		}
		
		return false;
	}
	
	
	private void launchStartupSequence() {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
		
		taskChronoStart = new BukkitRunnable() {
			
			int counter = 0;
			int reverseCounter;
			int firstMessageDone = 0;
			
            @Override
             public void run() {
            		               
                  counter++;
                  
                  if(counter <= 4) {
                	  
                	  if(firstMessageDone == 0) {
                		  p_online.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*16, 255, false));
                		  p_online.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*16, 0, false));
                		  sendTitle2all("§e§lEnigm ", "§cLa partie va commencer!");
                		  sendsound();
                		  firstMessageDone = 1;
                	  }
                	  
                	  
                  }else if(counter >=5 && counter <= maxSeconds + 5) {
                	  
                	  reverseCounter = 15 - counter;
                	  
                	  sendTitle2all("§cDémarrage dans",  "§a§l"+String.valueOf(reverseCounter)+" secondes!");
                	  
                  }else if(counter >= maxSeconds + 5) {
                	  
                	  enigmMain.currentChronoAtStartup = 2;
                	  sendTitle2all("§e§lC'est parti! ", "§a§lBon jeu :)");
                	  p_online.playSound(p_online.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1F, 0.5F);
                	  Location spawn = new Location(p_online.getWorld(),6.5 ,223 ,0-25.5);
                	  p_online.teleport(spawn);
                	  initGame();
                	  cancel();
                  }
                  
                  /* PlaySound Manager */
                	  switch (counter) {
					case 5:
			      		p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 2F);
						break;
					case 6:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.9F);
						break;
					case 7:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.8F);
						break;
					case 8:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.7F);
						break;
					case 9:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.6F);
						break;
					case 10:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.5F);
						break;
					case 11:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.4F);
						break;
					case 12:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.3F);
						break;
					case 13:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.2F);
						break;
					case 14:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.1F);
						break;
					case 15:
						p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
						break;

					default:
						break;
					}
             }
      }.runTaskTimer(enigmMain, 0, 20);
	 }
	}
	
	private void sendsound() {
        for(Player p_online : Bukkit.getOnlinePlayers()) {
      		 p_online.playSound(p_online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 2F);
        }
	}
	
	private void sendTitle2all(String message, String subtitle) {
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			 TitleAPI.sendTitle(p_online, 10, 20, 10, message, subtitle);
      } 
	}
	
	private void initGame() {
		pointsHandler.clearAllPoints();
		currentSbHandler.updateScoreboard4All();
		
		//todo : tp players 
		//todo: give blindness effect
		//todo: start general counter (in scoreboard)
	}

}
