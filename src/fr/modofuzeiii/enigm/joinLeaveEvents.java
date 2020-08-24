package fr.modofuzeiii.enigm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.modofuzeiii.enigm.database.DBConnection;

public class joinLeaveEvents implements Listener {
	
	private EnigmMain enigmMain;
	private ScoreBoardHandler currentSbHandler;
	private String prefixMessage = ChatColor.YELLOW + "§l[" + ChatColor.AQUA + "§lEnigm" + ChatColor.YELLOW + "§l] " + ChatColor.RESET;

	
	public joinLeaveEvents(EnigmMain mainInstance) {
		enigmMain = mainInstance;
		currentSbHandler = enigmMain.sbHandler;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		@SuppressWarnings("unused")
		Player p = e.getPlayer();
		e.setJoinMessage("");
	}
	
	@EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(prefixMessage + "§rLe joueur " + "§e§l" + p.getName() + "§r vient de se déconnecter!");
        
        if (e.getPlayer().getName().equalsIgnoreCase("FanaPik")) {
			e.setQuitMessage(prefixMessage+"La canaille de §lxXF4nAP1KXx §r n'est plus là ! §o(c'est pas trop tôt)");
		}
        if (e.getPlayer().getName().equalsIgnoreCase("Sinaynomis")) {
			e.setQuitMessage(prefixMessage+"§lSinaynomis §raka la perte de mémoire fréquente (KingL orga aussi l'event) n'est plus là !");
		}
        
        if(p.isOp()) {
        	e.setQuitMessage(prefixMessage + "Le maître §6§l" + p.getName()+ "§r vient de partir!");
        }
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
    
	 @EventHandler
	 public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event){
		 if(event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
			  event.getPlayer().kickPlayer("Vous devez accepter le pack de texture de Enigm!!!!!");
			  
		 }else if(event.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
			 event.getPlayer().sendMessage("§6§l» §rTexture pack téléchargé avec succès!");
			 welcomeSequence(event.getPlayer());
		 }
	 }
	 
	 private void welcomeSequence(Player p) {
		 	p.chat("/playsound minecraft:enigm.welcome master "+p.getName());
		 	
	        /* Petit Troll :p */
	        if (p.getName().equalsIgnoreCase("FanaPik")) {
	        	Bukkit.broadcastMessage(prefixMessage+"Le vil §lFanaPik §rde la plèbasse est §o(malheureusement) §rlà !");
			}
	        if (p.getName().equalsIgnoreCase("Sinaynomis")) {
	        	Bukkit.broadcastMessage(prefixMessage+"§lSinaynomis §raka la perte de mémoire fréquente (KingL orga aussi l'event) est là !");
			}
	        /* Fin Troll */
	        
	        
	        currentSbHandler.updateSb(p);
	        TitleAPI.sendTitle(p, 10, 20, 10, "§cHey, "+p.getDisplayName()+"!", "Bienvenue sur Enigm!");
	        
	        
	        /* PlayerTeleportLobby */
	        if(enigmMain.isGameStarted == 0) {
	        	Location lobby = new Location(p.getWorld(),994.5,103,1291.5);
	        	p.teleport(lobby);
	        }else {
	        	p.sendMessage(prefixMessage + "§6§l» §rLa partie est déjà en cours !");
	        }
	        /* PlayerTeleportLobby */
	        
	        if(p.isOp()) {
	        	p.setPlayerListName("§6§l[GameMaster]§r§6 " + p.getName());
	        	p.setDisplayName("§6"+p.getName()+"§r");
	        	Bukkit.broadcastMessage(prefixMessage + "Le maître §6§l" + p.getName()+ "§r est arrivé!"); //Pour l'égo mdrrr
	        }else {
	        	Bukkit.broadcastMessage(prefixMessage + "Le joueur " + "§e§l" + p.getName() + "§r vient de se connecter!");
	        	
	        	
	        /*Check if player is in a team*/
	        String playerTeam = getPlayerTeam(p);
	        
	        if (playerTeam.equalsIgnoreCase("0")) {
	        	p.sendMessage("§6§l» §rOh oh! Vous n'avez pas de team! ");
	        	p.sendMessage("§6§l» §rLaissons le hasard vous trouver une équipe... ");
	        	
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
	        		    	Bukkit.broadcastMessage("§6§l» §rLe joueur §e§l"+p.getDisplayName()+"§r rejoint la team §c§lROUGE§r !");
	        		    	teamFound = 42;
	        		    	p.setPlayerListName("§c§l[ROUGE]§r§c " + p.getName());
	        		    	p.setDisplayName("§c"+p.getName()+"§r");
	        		    }
	        		    break;
	        		    
	        		  case 2:
	          		    if(bluePlayers < maxPerTeam) {
	          		    	Bukkit.broadcastMessage("§6§l» §rLe joueur §e§l"+p.getDisplayName()+"§r rejoint la team §9§lBLEU§r !");
	          		    	teamFound = 42;
	          		    	p.setPlayerListName("§9§l[BLEU]§r§9 " + p.getName());
	          		    	p.setDisplayName("§9"+p.getName()+"§r");
	          		    }
	          		    break;
	          		    
	        		  case 3:
	        		    if(yellowPlayers < maxPerTeam) {
	        		    	Bukkit.broadcastMessage("§6§l» §rLe joueur §e§l"+p.getDisplayName()+"§r rejoint la team §e§lJAUNE§r !");
	        		    	teamFound = 42;
	        		    	p.setPlayerListName("§e§l[JAUNE]§r§e " + p.getName());
	        		    	p.setDisplayName("§e"+p.getName()+"§r");
	        		    }
	        		    break;
	        		    
	        		  case 4:
	          		    if(greenPlayers < maxPerTeam) {
	          		    	Bukkit.broadcastMessage("§6§l» §rLe joueur §e§l"+p.getDisplayName()+"§r rejoint la team §2§lVERTE §r!");
	          		    	teamFound = 42;
	          		    	p.setPlayerListName("§2§l[VERT]§r§2 " + p.getName());
	          		    	p.setDisplayName("§2"+p.getName()+"§r");
	          		    }
	          		    break;
	          		    

	        		  default:
	        			  teamFound = 0;
	        			  p.setPlayerListName("§8§l[????]§r§7 " + p.getName());
	        		    
	        		}
	        		
	        		if(teamFound == 0) {
	      		    	p.sendMessage("§6§l» §rC'est compliqué de te trouver une team...Voyons où il reste de la place...");
	      		    	p.sendMessage("§6§l» §rPlace non trouvée, vous devenez un simple spectateur!");
	      		    	p.setPlayerListName("§8§l[????]§r§7 " + p.getName());
	      		    	p.setGameMode(GameMode.SPECTATOR);
	      		    }
	        	}
	        }else {
	        switch(playerTeam) {
	  		  case "rouge":
	  			p.setPlayerListName("§c§l[ROUGE]§r§c " + p.getName());
	  			break;
	  		  case "bleu":
	  			p.setPlayerListName("§9§l[BLEU]§r§9 " + p.getName());
	  			break;
	  		  case "vert":
	  			p.setPlayerListName("§2§l[VERT]§r§2 " + p.getName());
	  			break;
	  		  case "jaune":
	  			p.setPlayerListName("§e§l[JAUNE]§r§e " + p.getName());
	  			break;
			  default:
				  p.setPlayerListName("§8§l[????]§r§7 " + p.getName());
			    break;
	        	}
	        }
		}
	 }
}
