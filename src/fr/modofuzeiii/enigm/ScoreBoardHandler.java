package fr.modofuzeiii.enigm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fr.modofuzeiii.enigm.database.DBConnection;

public class ScoreBoardHandler implements Listener {
	
	private  Scoreboard currentSb;
	private Player currentPlayer;
	private Objective o;
	
	public int pointsRouge;
	public int pointsVert;
	public int pointsJaune;
	public int pointsBleu;
	
	public int gameCounterSb;
	
	
	
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
	public void updateSb(Player p) {
		
		currentPlayer = p;
		
		currentSb = Bukkit.getScoreboardManager().getNewScoreboard();
		o = currentSb.registerNewObjective("Enigm", "Titouan?");
		
		currentSb.clearSlot(DisplaySlot.SIDEBAR);
		
        String spacer = "§6----------";
        String espace = "§6----------";
		
		if(enigmMain.isGameStarted == 1) {

		//Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {	
		
			this.pointsBleu = getInternalTeamPoints("bleu"); 
			this.pointsJaune = getInternalTeamPoints("jaune");
			this.pointsVert = getInternalTeamPoints("vert");
			this.pointsRouge = getInternalTeamPoints("rouge");
	
	
	        String statsBleu = "§9Bleu §r"+this.pointsBleu+"pts";
	        String statsRouge = "§cRouge §r"+this.pointsRouge+"pts";
	        String statsVert = "§2Vert §r"+this.pointsVert+"pts";
	        String statsJaune = "§eJaune §r"+this.pointsJaune+"pts";
	        String timer = convertDate(gameCounterSb);
	        String emptyStr = "     ";
	       
	        
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
	        
		//});
		
		}else {
			
			o.setDisplayName("§lEnigm v0.1");
	        o.setDisplaySlot(DisplaySlot.SIDEBAR);
	        
	        o.getScore(espace + " ").setScore(9);
	        
	        o.getScore("").setScore(8);
	        o.getScore("Enigm n'a pas encoré démarré!").setScore(7);
	        o.getScore("[INFO] qsldkq,dlk,").setScore(5);
	        
			
		}
	        
	      currentPlayer.setScoreboard(currentSb);
		
	}
	
    public Scoreboard getScoreboard() {
        return this.currentSb;
    }
    
    public void updateScoreboard4All() {
    	
    	for(Player p_online : Bukkit.getOnlinePlayers()) {
    			updateSb(p_online);
	      } 
    }
    
    public int getTeamPointsFromBdd(String team) {
    	
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
    
    public int getInternalTeamPoints(String team) {
    	
    	int output;
    	
    	
    	if(isTeamExists(team)) {
    		
    		switch(team) {
        	
    		  case "rouge":
    			  output = this.pointsRouge;
    			break;
    			
    		  case "bleu":
    			  output = this.pointsBleu;
    			break;
    			
    		  case "vert":
    			  output = this.pointsVert;
    			break;
    			
    		  case "jaune":
    			  output = this.pointsJaune;
    			break;
    			
	  		  default:
	  			output =  -42;
	  		    break;
          	}
    	}else {
    		output = 0;
    	}
    	
    	return output;
    }
    
    public void setInternalTeamPoints(String team, int pts) {
    	
    	if(isTeamExists(team)) {
    		
    		switch(team) {
        	
    		  case "rouge":
    			  this.pointsRouge = pts;
    			break;
    			
    		  case "bleu":
    			  this.pointsBleu = pts;
    			break;
    			
    		  case "vert":
    			  this.pointsVert = pts;
    			break;
    			
    		  case "jaune":
    			  this.pointsJaune = pts;
    			break;
    			
	  		  default:
	  			
	  		    break;
          	}
    		
    		this.updateScoreboard4All();
    	}
    	
    	
    }
    
    public boolean isTeamExists(String team) {
    	return Arrays.asList(teams).contains(team);
    }
    
    public String convertDate(int seconds) {
    	
    	int sec = (int) (seconds) % 60 ;
    	int min = (int) ((seconds/60) % 60);
    	int hr   = (int) ((seconds / (60*60) ) % 24);
    	
    	
    	String sec_str = Integer.toString(sec);
    	String min_str = Integer.toString(min);
    	String hr_str = Integer.toString(hr);
    	
    	if(sec < 10) {
    		sec_str = "0"+sec_str;
    	}
    	if(min < 10) {
    		min_str = "0"+min_str;
    	}
    	if(hr < 10) {
    		hr_str = "0"+hr_str;
    	}
    	
		return "§a"+hr_str + " : "+min_str+" : "+sec_str;
    	
    }

}
