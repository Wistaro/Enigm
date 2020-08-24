package fr.modofuzeiii.enigm.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.modofuzeiii.enigm.game.GameManager;

public class BootsTask extends BukkitRunnable {
	private GameManager currentGame;
	
	public BootsTask(GameManager currentGame){
		this.currentGame = currentGame;
		}
	
	@Override
	public void run() {
		if(currentGame.enigmMain.isGameStarted == 1) {
			GiveEffect();
		}else if(currentGame.enigmMain.isGameStarted == 0){
			return;
		}
	}
	
	private void GiveEffect() {
		Player p = (Player) Bukkit.getPlayer("");
		if (p.getInventory().getBoots() != null) {
			if(p.getInventory().getBoots().hasItemMeta()) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 99999, 255, false));
			}
		}
		if(p.getInventory().getBoots() == null){
			p.removePotionEffect(PotionEffectType.WATER_BREATHING);
		}
	}
}
