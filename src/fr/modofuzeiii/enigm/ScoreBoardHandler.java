package fr.modofuzeiii.enigm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.modofuzeiii.enigm.database.DBConnection;
import net.md_5.bungee.api.ChatColor;

public class ScoreBoardHandler implements Listener {
	
	private  Scoreboard currentSb;
	private Player currentPlayer;
	private Objective o;
	
	private int pointsRouge;
	private int pointsVert;
	private int pointsJaune;
	private int pointsBleu;
	
	private EnigmMain enigmMain;
	
	public String[] teams;
	
	public ScoreBoardHandler(EnigmMain mainInstance) {
		
		enigmMain = mainInstance;
		teams = new String[] { "rouge", "vert", "bleu", "jaune" };
		
		pointsRouge = -2;
		pointsVert = -2;
		pointsJaune = -2;
		pointsBleu = -2;
		
	}
	
	@SuppressWarnings("deprecation")
	public void setupSb() {
		
        currentSb = Bukkit.getScoreboardManager().getNewScoreboard();
        
        String statsBleu = "§9Bleue §r20pts";
        String statsRouge = "§cRouge §r20pts";
        String statsVert = "§2Vert §r20pts";
        String statsJaune = "§eJaune §r20pts";
        String timer = "§200h00m00s";
        String emptyStr = "     ";
        String emptyStr2 = "     ";
        String spacer = "§6---------";
        String bigspacer = "§6---------";

        Objective o = currentSb.registerNewObjective("title", "dummy");
        o.setDisplayName("§lEnigm v2.0");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
                
        Score seperate = o.getScore(bigspacer);
        seperate.setScore(9);
        
        Score rien1 = o.getScore(emptyStr2);
        rien1.setScore(8);
        
        Score bleu = o.getScore(statsBleu);
        bleu.setScore(7);

        Score rouge = o.getScore(statsRouge);
        rouge.setScore(6);
        
        Score vert = o.getScore(statsVert);
        vert.setScore(5);
     
        Score jaune = o.getScore(statsJaune);
        jaune.setScore(4);
        
        
        Score rien2 = o.getScore(emptyStr);
        rien2.setScore(3);
        
        Score bottom = o.getScore(spacer);
        bottom.setScore(2);
        
        Score infoTimer = o.getScore(timer);
        infoTimer.setScore(1);
	}
        
	@SuppressWarnings("deprecation")
	public void updateSb(Player p) {
		
		currentPlayer = p;
		
		currentSb = Bukkit.getScoreboardManager().getNewScoreboard();
		o = currentSb.registerNewObjective("Enigm", "Titouan?");
		
		currentSb.clearSlot(DisplaySlot.SIDEBAR);

		Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {	
			
			System.out.println("Updating scoreboard!");
		
			this.pointsBleu = getTeamPoints("bleu"); 
			this.pointsJaune = getTeamPoints("jaune");
			this.pointsVert = getTeamPoints("vert");
			this.pointsRouge = getTeamPoints("rouge");
	
	
	        String statsBleu = "§9Bleu §r"+this.pointsBleu+"pts";
	        String statsRouge = "§cRouge §r"+this.pointsRouge+"pts";
	        String statsVert = "§2Vert §r"+this.pointsVert+"pts";
	        String statsJaune = "§eJaune §r"+this.pointsJaune+"pts";
	        String timer = "§a00h00m00s";
	        String emptyStr = "     ";
	        
	        String spacer = "§6----------";
	        String espace = "§6----------";
	        
	        o.setDisplayName("§lEnigm v0.1");
	        o.setDisplaySlot(DisplaySlot.SIDEBAR);
	        
	        o.getScore(espace + " ").setScore(9);
	        
	        o.getScore("").setScore(8);
	        
	        o.getScore(statsBleu).setScore(7);
	        
	        o.getScore(statsRouge).setScore(6);
	        
	        o.getScore(statsVert).setScore(5);
	        
	        o.getScore(statsJaune).setScore(4);
	        
	        o.getScore(emptyStr).setScore(3);
	        
	        o.getScore(spacer).setScore(2);
	        
	        o.getScore(timer).setScore(1);
	        
	        currentPlayer.setScoreboard(currentSb);
		});
	}
	
    public Scoreboard getScoreboard() {
        return this.currentSb;
    }
    
    public void updateScoreboard4All() {
    	
    	for(Player p_online : Bukkit.getOnlinePlayers()) {
				this.updateSb(p_online);
	      } 
    }
    
    private int getTeamPoints(String team) {
    	
    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
    		   		
        	try {
        		
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT points FROM eventTeams WHERE nom = ?");
    			
    			preparedStatement.setString(1,team.toString());
    			
    			final ResultSet result = preparedStatement.executeQuery();
    			
    			if(result.next()) {
    				
    				return result.getInt("points");
    			
    			}else {
    				return -1;
    			}
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
 
		return -1;
    }

}
