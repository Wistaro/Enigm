package fr.modofuzeiii.enigm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.modofuzeiii.enigm.database.DBConnection;

public class ScoreBoardHandler implements Listener {
	
	private  Scoreboard sb;
	private Player p;
	
	private int pointsRouge;
	private int pointsVert;
	private int pointsJaune;
	private int pointsBleu;
	
	private EnigmMain enigmMain;
	
	public ScoreBoardHandler(Player pl, EnigmMain mainInstance) {
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
		p = pl;
		
		enigmMain = mainInstance;
		
		pointsRouge = -2;
		pointsVert = -2;
		pointsJaune = -2;
		pointsBleu = -2;
		
	}
	
	public void setupSb() {
		
		this.getTeamPoints("jaune"); //update local attribute
		
		System.out.println("La team jaune a "+this.pointsJaune+" points");
		
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
    
    private int getTeamPoints(String team) {
    	
    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
    	
    	Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {
    		   	
        	
        	try {
        		
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT points FROM eventTeams WHERE nom = ?");
    			
    			preparedStatement.setString(1,team.toString());
    			
    			final ResultSet result = preparedStatement.executeQuery();
    			
    			if(result.next()) {
    				
    				this.pointsJaune =  result.getInt("points");
    				
            		System.out.println("points = "+result.getInt("points"));
            		System.out.println("points = "+this.pointsJaune);
    			
    			}else {
    				this.pointsJaune = -1;
    			}
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
    	});
    	
 
		return 0;
    }

}
