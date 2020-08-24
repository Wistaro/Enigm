package fr.modofuzeiii.enigm.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.modofuzeiii.enigm.EnigmMain;
import fr.modofuzeiii.enigm.ScoreBoardHandler;
import fr.modofuzeiii.enigm.database.DBConnection;

public class PointsManager implements CommandExecutor {
	
	private EnigmMain enigmMain;
	private ScoreBoardHandler currentSbHandler;
	private String[] teams;
	private String pf = ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET;
	
	public PointsManager(EnigmMain mainInstance) {
		
		enigmMain = mainInstance;
		currentSbHandler = enigmMain.sbHandler;
		teams = currentSbHandler.teams;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("pts")) {
				
				if(args[0].equalsIgnoreCase("set")) {
					
					String selectedTeam = args[1].toString();
					int points2set = Integer.parseInt(args[2]);
					
					if(Arrays.asList(teams).contains(selectedTeam)) {
						
						currentSbHandler.setInternalTeamPoints(selectedTeam, points2set);
						currentSbHandler.updateScoreboard4All();
						
						p.sendMessage(pf+"La team "+selectedTeam+" a désormais "+points2set+" points!");
						
						
					}else {
						p.sendMessage(pf+"La team "+selectedTeam+" n'existe pas!");
					}
					
					
				}else if(args[0].equalsIgnoreCase("reloadsb")) {
					
					currentSbHandler.updateScoreboard4All();
					 Bukkit.broadcastMessage(pf + "Le scoreboard a été actualisé par "+p.getDisplayName());
				
				}else if(args[0].equalsIgnoreCase("export")) {
					
					saveScoreToDatabase();
					
					
				}else if(args[0].equalsIgnoreCase("import")) {
					
					importScoreFromDatabase();
					
					currentSbHandler.updateScoreboard4All();
					
				}else if(args[0].equalsIgnoreCase("reset")) {
					
					this.clearAllPoints();
					Bukkit.broadcastMessage("§4§l[DEBUG] §rLes scores ont été réinitialisés");
					currentSbHandler.updateScoreboard4All();
				}
				
			}
		}
		return false;
	}
	
	public void clearAllPoints() {
		for(String team : teams) {
			currentSbHandler.setInternalTeamPoints(team.toString(), 0);
		}
	}
	
	private void updateTeamPointsPointsBdd(String team, int points) {
		
    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
    	
    	Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {
    		   	    	
        	try {
        		
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE eventTeams SET points = ? WHERE nom = ?");
    			
    			
    			preparedStatement.setInt(1, points);
    			preparedStatement.setString(2,team.toString());
    			
    			
    			preparedStatement.executeUpdate();
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
	});
	}
	
	public void saveScoreToDatabase() {
		
		Bukkit.broadcastMessage("§4§l[DEBUG] §rSauvegarde des scores en cours....");
		
		for(String team : teams) {
	    		
	    		switch(team) {
	        	
	    		  case "rouge":
	    			  updateTeamPointsPointsBdd("rouge", currentSbHandler.pointsRouge);
	    			break;
	    			
	    		  case "bleu":
	    			  updateTeamPointsPointsBdd("bleu", currentSbHandler.pointsBleu);
	    			
	    		  case "vert":
	    			  updateTeamPointsPointsBdd("vert", currentSbHandler.pointsVert);
	    			break;
	    			
	    		  case "jaune":
	    			  updateTeamPointsPointsBdd("jaune", currentSbHandler.pointsJaune);
	    			break;
	    			
		  		  default:
		  			
		  		    break;
	          	}
	    		
		}
		
		Bukkit.broadcastMessage("§4§l[DEBUG] §rSauvegarde des scores terminée!");
	}
	
	private void importScoreFromDatabase() {
		
		Bukkit.broadcastMessage("§4§l[DEBUG] §rImportation des scores en cours...");
		
		for(String team : teams) {
			
			switch(team) {
        	
	  		  case "rouge":
	  			  currentSbHandler.pointsRouge = currentSbHandler.getTeamPointsFromBdd("rouge");
	  			break;
	  			
	  		  case "bleu":
	  			currentSbHandler.pointsBleu = currentSbHandler.getTeamPointsFromBdd("bleu");
	  			
	  		  case "vert":
	  			currentSbHandler.pointsVert = currentSbHandler.getTeamPointsFromBdd("vert");
	  			break;
	  			
	  		  case "jaune":
	  			currentSbHandler.pointsJaune = currentSbHandler.getTeamPointsFromBdd("jaune");
	  			break;
	  			
	  		  default:
	  			
	  		    break;
        	}
			
		}
		
		Bukkit.broadcastMessage("§4§l[DEBUG] §rLes scores ont été importés depuis la base de donnée!");
	}

}
