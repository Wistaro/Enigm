package fr.modofuzeiii.enigm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.modofuzeiii.enigm.database.DBConnection;

public class joinLeaveEvents implements Listener {
	
	private EnigmMain enigmMain;
	private ScoreBoardHandler currentSbHandler;
	private String prefixMessage = ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l]" + ChatColor.RESET;
	
	public joinLeaveEvents(EnigmMain mainInstance) {
		enigmMain = mainInstance;
		currentSbHandler = enigmMain.sbHandler;
	}
	
	@EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        
        Bukkit.broadcastMessage(prefixMessage + "Le joueur " + p.getName() + " vient de se connecter!" );
        
        currentSbHandler.updateSb(p);
        
        TitleAPI.sendTitle(p, 10, 20, 10, "§cHey, "+p.getDisplayName()+"!", "Bienvenue sur Enigm!");
        
        if(p.isOp()) {
        	p.setPlayerListName("§6§l[GameMaster]§r§6 " + p.getName());
        	
        }else {
        
        /*Check is player is in a team*/
        
        String playerTeam = getPlayerTeam(p);
        
        if (playerTeam.equalsIgnoreCase("0")) {
        	p.sendMessage("Oh oh! Vous n'avez pas de team! ");
        	p.sendMessage("Laissons le hasard vous trouver une équipe... ");
        	
        	int redPlayers = getNbPlayersInTeam("rouge");
        	int bluePlayers = getNbPlayersInTeam("bleu");
        	int yellowPlayers = getNbPlayersInTeam("jaune");
        	int greenPlayers = getNbPlayersInTeam("vert");
        	
        	int maxPerTeam = enigmMain.getConfig().getInt("gameData.teams.maxPerTeam");
        	
        	int teamFound = 0;
        	
        	while(teamFound == 0) {
        		
        		int rdm = 1 + (int)(Math.random() *4);
        		
        		switch(rdm) {
        		  case 1:
        		    if(redPlayers < maxPerTeam) {
        		    	Bukkit.broadcastMessage(prefixMessage+"Le joueur "+p.getDisplayName()+" rejoint la team ROUGE!");
        		    	teamFound = 42;
        		    	p.setPlayerListName("§4§l[ROUGE]§r§4 " + p.getName());
        		    }
        		    break;
        		    
        		  case 2:
          		    if(bluePlayers < maxPerTeam) {
          		    	Bukkit.broadcastMessage(prefixMessage+"Le joueur "+p.getDisplayName()+" rejoint la team BLEUE!");
          		    	teamFound = 42;
          		    	p.setPlayerListName("§1§l[BLEUE]§r§1 " + p.getName());
          		    }
          		    break;
          		    
        		  case 3:
        		    if(yellowPlayers < maxPerTeam) {
        		    	Bukkit.broadcastMessage(prefixMessage+"Le joueur "+p.getDisplayName()+" rejoint la team JAUNE!");
        		    	teamFound = 42;
        		    	p.setPlayerListName("§e§l[JAUNE]§r§e " + p.getName());
        		    }
        		    break;
        		    
        		  case 4:
          		    if(greenPlayers < maxPerTeam) {
          		    	Bukkit.broadcastMessage(prefixMessage+"Le joueur "+p.getDisplayName()+" rejoint la team VERTE!");
          		    	teamFound = 42;
          		    	p.setPlayerListName("§2§l[VERT]§r§2 " + p.getName());
          		    }
          		    break;
          		    

        		  default:
        			  teamFound = 0;
        		    
        		}
        		
        		if(teamFound == 0) {
      		    	p.sendMessage("C'est compliqué de te trouver une team...Voyons où il reste de la place...");
      		    }
        		
        		
        		
        		
        	}
        	
        	
        	
        }
        
	}
        
        
    }
	
	@EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        
        Bukkit.broadcastMessage(ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET + "Le joueur " + p.getName() + " vient de se déconnecter!" );

    }
	
	
	
    private String getPlayerTeam(Player p) {
    	
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
    
 private int getNbPlayersInTeam(String team) {
    	
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
    
    
}
