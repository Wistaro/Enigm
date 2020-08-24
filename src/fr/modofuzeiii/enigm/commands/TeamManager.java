package fr.modofuzeiii.enigm.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

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
	private String pf = ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET;
	
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
					
					p.sendMessage(pf+"Vous venez de vider l'équipe "+team2clear);		
					
				}else if(args[0].equalsIgnoreCase("add")) {
					
					String psdPlayer = args[1].toString();
					String team2moove = args[2].toString();
					
					Player targetPlayer = Bukkit.getPlayer(psdPlayer);
					
					addPlayerTeam(team2moove, targetPlayer);
					
					p.sendMessage(pf+"Vous venez d'ajouter le joueur "+psdPlayer+" dans la team "+team2moove);

					
				}else if(args[0].equalsIgnoreCase("remove")) {
					
					String psdPlayer = args[1].toString();
					String team2remove = args[2].toString();
					
					Player targetPlayer = Bukkit.getPlayer(psdPlayer);
					
					clearUserFromTeam(team2remove, targetPlayer);
					
					p.sendMessage(pf+"Vous venez de virer le joueur "+psdPlayer+" de la team "+team2remove);

				
			}else {
				return false;
			}
				
			}
			return false;
		}
		return false;
	}
	
	public void addPlayerTeam(String team, Player p) {
		
		addPlayerTeamHashmap(p, team);
		
		addColorPlayerTeam(team, p);
		
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
		
		enigmMain.mapPlayersTeam.values().removeAll(Collections.singleton(team));
		
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
		
		enigmMain.mapPlayersTeam.remove(p.getUniqueId());
		
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
    	
    	addColorPlayerTeam("noTeam", p);
	}
	
	 private void addPlayerTeamHashmap(Player p, String team) {
		 
		 UUID playerId = p.getUniqueId();
		 
		 if(!enigmMain.mapPlayersTeam.containsKey(playerId)) {
			 
			 enigmMain.mapPlayersTeam.put(playerId, team);
			 
		 }else {
			 
			 enigmMain.mapPlayersTeam.remove(playerId);
			 enigmMain.mapPlayersTeam.put(playerId, team);
		 }
		 
	 }
	 
	 public void loadTeamsIntoHashMap() {
		 
		 Bukkit.broadcastMessage("Importation des teams depuis la base de donnée...");
		 
		 enigmMain.mapPlayersTeam.clear();
		 
	    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
	   		
        	try {
    			final Connection connection = enigmEventConnection.getConnection();
    			
    			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players LIMIT ?");
    			
    			preparedStatement.setInt(1, 20);
    			
    			final ResultSet result = preparedStatement.executeQuery();
    			
    			ResultSetMetaData resultMeta = result.getMetaData();
    			
    			while(result.next()){ 
    				
    		        for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
    		        	Bukkit.broadcastMessage(result.getObject(i).toString());
    		        }
    		          

    		      }
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		 
        	Bukkit.broadcastMessage("Importation terminée!");
	 }
	 
	 public String getPlayerTeam(Player p) {
		 
		 Bukkit.broadcastMessage("yolo");
		 
		 //return enigmMain.mapPlayersTeam.get(p.getUniqueId());
		 
	    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
	    		   		
	        	try {
	    			final Connection connection = enigmEventConnection.getConnection();
	    			
	    			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT team FROM players WHERE uuid = ?");
	    			
	    			preparedStatement.setString(1,p.getUniqueId().toString());
	    			
	    			final ResultSet result = preparedStatement.executeQuery();
	    			
	    			if(result.next()) {
	    				return result.getString("team");
	    			
	    			}else {
	    				return "0";
	    			}
	    			
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
			return "0";
	    }
	 
	 public  int getNbPlayersInTeam(String team) {
		 
	    	final DBConnection enigmEventConnection = enigmMain.getDatabaseManager().getEnigmConnection();
	    		   		
	        	try {
	    			final Connection connection = enigmEventConnection.getConnection();
	    			
	    			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(ID) as nbTeam FROM players WHERE team = ?");
	    			
	    			preparedStatement.setString(1,team.toString());
	    			
	    			final ResultSet result = preparedStatement.executeQuery();
	    			
	    			if(result.next()) {
	    				return result.getInt("nbTeam");
	    			
	    			}else {
	    				return 0;
	    			}
	    			
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
			return -1;
	    }
	 
	 public void addColorPlayerTeam(String team, Player p) {
		 
		 switch(team) {
 		  case "rouge":
 			p.setDisplayName("§c"+ p.getName()+"§r");
 			p.setPlayerListName("§c§l[ROUGE]§r§c " + p.getName());
 			break;
 		  case "bleu":
 			p.setDisplayName("§9"+ p.getName()+"§r");
 			p.setPlayerListName("§9§l[BLEU]§r§9 " + p.getName());
 			break;
 		  case "vert":
 			p.setDisplayName("§2"+ p.getName()+"§r");
 			p.setPlayerListName("§2§l[VERT]§r§2 " + p.getName());
 			break;
 		  case "jaune":
 			p.setDisplayName("§e"+ p.getName()+"§r");
 			p.setPlayerListName("§e§l[JAUNE]§r§e " + p.getName());
 			break;
 		
 		  case "gameMaster":
 			p.setDisplayName("§6"+ p.getName()+"§r");
 			p.setPlayerListName("§6§l[GameMaster]§r§6 " + p.getName());
 			break;
 			
 		  case "noTeam":
 			p.setDisplayName(" " + p.getName());
 			p.setPlayerListName(" " + p.getName());
 			break;
 			
		  default:
			  p.setDisplayName("§8" + p.getName()+"§r");
			  p.setPlayerListName("§8§l[????]§r§8 " + p.getName());
		    break;
       	}
	 }
	

}
