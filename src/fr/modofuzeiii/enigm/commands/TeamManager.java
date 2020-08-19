package fr.modofuzeiii.enigm.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class TeamManager implements CommandExecutor {
	
	private EnigmMain enigmMain;
	private ScoreBoardHandler currentSbHandler;
	private String[] teams;
	
	public TeamManager(EnigmMain mainInstance) {
		
		enigmMain = mainInstance;
		currentSbHandler = enigmMain.sbHandler;
		teams = currentSbHandler.teams;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("teams")) {
				
				if(args[0].equalsIgnoreCase("clear")) {
					
					String team2clear = args[1].toString();
					clearTeam(team2clear);
					
					p.sendMessage("Vous venez de vider l'équipe "+team2clear);		
					
				}else if(args[0].equalsIgnoreCase("add")) {
					
					String psdPlayer = args[1].toString();
					String team2moove = args[2].toString();
					
					Player targetPlayer = Bukkit.getPlayer(psdPlayer);
					
					addPlayerTeam(team2moove, targetPlayer);
					
					p.sendMessage("Vous venez d'ajouter le joueur "+psdPlayer+" dans la team "+team2moove);
					targetPlayer.setPlayerListName("§2§l["+team2moove+"]§r§2 " + targetPlayer.getName());
					
				}else if(args[0].equalsIgnoreCase("remove")) {
					
					String psdPlayer = args[1].toString();
					String team2remove = args[2].toString();
					
					Player targetPlayer = Bukkit.getPlayer(psdPlayer);
					
					clearUserFromTeam(team2remove, targetPlayer);
					
					p.sendMessage("Vous venez de virer le joueur "+psdPlayer+" de la team "+team2remove);
					targetPlayer.setPlayerListName("§r " + targetPlayer.getName());
				
			}else {
				return false;
			}
				
			}
			return false;
		}
		return false;
	}
	
	public void addPlayerTeam(String team, Player p) {
		
    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
    	
    	Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {
    		   	    	
        	try {
        		
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (uuid, psd, team) VALUES (?, ?, ?)");
    			
    			
    			preparedStatement.setString(1, p.getUniqueId().toString());
    			preparedStatement.setString(2, p.getDisplayName());
    			preparedStatement.setString(3, team);
    			
    			
    			preparedStatement.executeUpdate();
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
	});
	}
	
	private void clearTeam(String team) {
		
    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
    	
    	Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {
    		   	    	
        	try {
        		
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE team = ?");
    			
    			
    			preparedStatement.setString(1, team);
    			
    			
    			preparedStatement.executeUpdate();
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
	});
	}
	
	private void clearUserFromTeam(String team, Player p) {
		
    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
    	
    	Bukkit.getScheduler().runTaskAsynchronously(enigmMain, (Runnable) () -> {
    		   	    	
        	try {
        		
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE team = ? AND uuid = ?");
    			
    			
    			preparedStatement.setString(1, team);
    			preparedStatement.setString(2, p.getUniqueId().toString());
    			
    			
    			preparedStatement.executeUpdate();
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
	});
	}
	

}
