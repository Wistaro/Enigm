package fr.modofuzeiii.enigm.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.modofuzeiii.enigm.game.GameManager;

public class ScoreboardUpdate extends BukkitRunnable {
	
	private GameManager currentGame;
	
	public ScoreboardUpdate(GameManager gameInstance) {
		
		currentGame = gameInstance;
	}
	
	@Override
	public void run() {
		
		if(currentGame.enigmMain.isGameStarted == 1) { //check if the game is started and not paused
			
			currentGame.currentSbHandler.gameCounterSb++;
			//Bukkit.broadcastMessage(currentGame.currentSbHandler.convertDate(currentGame.currentSbHandler.gameCounterSb));
			currentGame.currentSbHandler.updateScoreboard4All();
		
		}else if(currentGame.enigmMain.isGameStarted == 0){
			cancel();
		}
	}
}
