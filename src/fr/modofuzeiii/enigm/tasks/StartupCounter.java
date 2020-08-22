package fr.modofuzeiii.enigm.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.modofuzeiii.enigm.game.GameManager;

public class StartupCounter extends BukkitRunnable {
	
	private GameManager currentGame;
	
	private int counter;
	private int reverseCounter;
	private int firstMessageDone;
	
	public StartupCounter(GameManager gameManagerInstance) {
		
		currentGame = gameManagerInstance;
		counter = 0;
		firstMessageDone = 0;
	}
	
	@Override
	public void run() {
		
		if(currentGame.enigmMain.currentChronoAtStartup == 1) {
			
			currentGame.enigmMain.currentChronoAtStartup = 2; //isCounting
			
			if(counter <= 4) {
          	  
          	  if(firstMessageDone == 0) {
          		  
          		  currentGame.addPotionEffect4all(PotionEffectType.SLOW, 20*16, 255, false);
          		  currentGame.addPotionEffect4all(PotionEffectType.BLINDNESS, 20*16, 0, false);
          		  
          		  currentGame.sendTitle2all("§e§lEnigm ", "§cBy Wistaro & King");
          		  
          		  currentGame.playSound4all(Sound.BLOCK_BEACON_POWER_SELECT , 1F, 1F);
          		  firstMessageDone = 1;
          	  }
          	  
          	  
            }else if(counter >=5 && counter <= 10 + 5) {
          	  
          	  reverseCounter = 15 - counter;
          	  
          	currentGame.sendTitle2all("§cDémarrage dans",  "§a§l"+String.valueOf(reverseCounter)+" secondes!");
          	  
            }else if(counter >= 10 + 5) {
          	  
            	currentGame.sendTitle2all("§e§lC'est parti! ", "§a§lBon jeu :)");
            	currentGame.teleportAllPlayerForStart();
            	currentGame.initGame();
          	  	cancel();
            }
            
            
          	  switch (counter) {
				case 5:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 2F);
		      		Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET + "Plugin by Wistaro & King L !");
					break;
				case 6:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.9F);
					break;
				case 7:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.8F);
					break;
				case 8:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.7F);
					break;
				case 9:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.6F);
					break;
				case 10:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.5F);
					break;
				case 11:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.4F);
					break;
				case 12:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.3F);
					break;
				case 13:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.2F);
					break;
				case 14:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1.1F);
					break;
				case 15:
					currentGame.playSound4all(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
					break;

				default:
					break;
				}
			
		}

	}

}
