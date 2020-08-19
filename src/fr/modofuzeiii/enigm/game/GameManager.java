package fr.modofuzeiii.enigm.game;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
	
	private int currentSeconds;
	private int maxSeconds;
	
	private BukkitTask taskChronoStart;
	
	public GameManager(EnigmMain mainClass, PointsManager pointsManagerClass) {
		enigmMain = mainClass;
		currentSbHandler = enigmMain.sbHandler;
		pointsHandler = pointsManagerClass;
		
		maxSeconds = 10;
		currentSeconds = 0;
	}

	@SuppressWarnings("deprecation")
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
		
		taskChronoStart = new BukkitRunnable() {
			
			int counter = 0;
			int reverseCounter;
			int firstMessageDone = 0;
			
            @Override
             public void run() {
            		               
                  counter++;
                  
                  if(counter <= 4) {
                	  
                	  if(firstMessageDone == 0) {
                		  
                		  sendTitle2all("§c§lEnigm ", "§cLa partie va commencer!");
                		  
                		  firstMessageDone = 1;
                	  }
                	  
                	  
                  }else if(counter >=5 && counter <= maxSeconds + 5) {
                	  
                	  reverseCounter = 15 - counter;
                	  
                	  sendTitle2all("§cDémarrage dans",  "§c§l"+String.valueOf(reverseCounter)+" secondes!");
                	  
                  }else if(counter >= maxSeconds + 5) {
                	  
                	  enigmMain.currentChronoAtStartup = 2;
                	  sendTitle2all("§c§lC'est parti! ", "§c§lBon jeu :)");
                	  
                	  initGame();
                	  
                	  cancel();
                	  
                  }
             }
      }.runTaskTimer(enigmMain, 0, 20);
		
	}
	
	private void sendTitle2all(String message, String subtitle) {
		
		for(Player p_online : Bukkit.getOnlinePlayers()) {
			 TitleAPI.sendTitle(p_online, 10, 20, 10, message, subtitle);
			 p_online.playSound(p_online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 2F);
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
