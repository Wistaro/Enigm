package fr.modofuzeiii.enigm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardHandler implements Listener {
	
	private  Scoreboard sb;
	private Player p;
	
	public ScoreBoardHandler(Player pl) {
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		p = pl;
		
	}
	
	public void setupSb() {
		
        sb = Bukkit.getScoreboardManager().getNewScoreboard();

        String statsBleu = "§9Bleue §r20pts";
        String statsRouge = "§cRouge §r20pts";
        String statsVert = "§2Vert §r20pts";
        String statsJaune = "§eJaune §r20pts";
        String timer = "§200h00m00s";
        String emptyStr = "     ";
        
        String spacer = "§7§m-------------------";

        Objective o = sb.registerNewObjective("title", "dummy");
        o.setDisplayName("Enigm v0.1");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Score top = o.getScore(spacer);
        top.setScore(13);
        
        Score rien1 = o.getScore(emptyStr);
        rien1.setScore(12);
        
        Score bleu = o.getScore(statsBleu);
        bleu.setScore(11);

        Score rouge = o.getScore(statsRouge);
        rouge.setScore(10);
        
        Score vert = o.getScore(statsVert);
        vert.setScore(9);
     
        Score jaune = o.getScore(statsJaune);
        jaune.setScore(8);
        
        
        Score rien2 = o.getScore(emptyStr);
        rien2.setScore(7);
        
        Score bottom = o.getScore(spacer);
        bottom.setScore(6);
        
        Score infoTimer = o.getScore(timer);
        infoTimer.setScore(5);

        
        p.setScoreboard(sb);
	}
	
    public Scoreboard getScoreboard() {
        return this.sb;
    }

}
