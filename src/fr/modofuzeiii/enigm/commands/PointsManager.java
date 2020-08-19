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
						
						updateTeamPointsPoints(selectedTeam, points2set);
						currentSbHandler.updateScoreboard4All();
						p.sendMessage("La team "+selectedTeam+" a désormais "+points2set+" points!");
						
					}else {
						p.sendMessage("La team "+selectedTeam+" n'existe pas!");
					}
					
					
				}else if(args[0].equalsIgnoreCase("reload")) {
					
					currentSbHandler.updateScoreboard4All();
					 Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET + "Le scoreboard a été actualisé par "+p.getDisplayName());
				}
				
			}
		}
		return false;
	}
	
	private void updateTeamPointsPoints(String team, int points) {
		
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

}
