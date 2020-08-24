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

import fr.modofuzeiii.enigm.commands.TeamManager;
import fr.modofuzeiii.enigm.database.DBConnection;

public class joinLeaveEvents implements Listener {
	
	private EnigmMain enigmMain;
	private ScoreBoardHandler currentSbHandler;
	private TeamManager teamHandler;
	private String prefixMessage = ChatColor.YELLOW + "�l[" + ChatColor.AQUA + "�lEnigm" + ChatColor.YELLOW + "�l] " + ChatColor.RESET;

	
	public joinLeaveEvents(EnigmMain mainInstance, TeamManager teamInstance) {
		enigmMain = mainInstance;
		currentSbHandler = enigmMain.sbHandler;
		this.teamHandler = teamInstance;
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
        e.setQuitMessage(prefixMessage + "�r�eLe joueur " + "�e�l" + p.getName() + "�r vient de se d�connecter!");
        
        if (e.getPlayer().getName().equalsIgnoreCase("FanaPik")) {
			e.setQuitMessage(prefixMessage+"La canaille de �lxXF4nAP1KXx �r n'est plus l� ! �o(c'est pas trop t�t)");
		}
        if (e.getPlayer().getName().equalsIgnoreCase("Sinaynomis")) {
			e.setQuitMessage(prefixMessage+"�lSinaynomis �raka la perte de m�moire fr�quente (KingL orga aussi l'event) n'est plus l� !");
		}
        
        if(p.isOp()) {
        	e.setQuitMessage(prefixMessage + "Le ma�tre �6�l" + p.getName()+ "�r vient de partir!");
        }
    }
	
    
    
	 @EventHandler
	 public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event){
		 if(event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
			  event.getPlayer().kickPlayer("Vous devez accepter le pack de texture de Enigm!!!!!");
			  
		 }else if(event.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
			 event.getPlayer().sendMessage("�6�l� �rTexture pack t�l�charg� avec succ�s!");
			 welcomeSequence( event.getPlayer());
		 }
	 }
	 
	 private void welcomeSequence(Player p) {
		 
		 	p.playSound(p.getLocation(), "enigm.welcome" , 1F, 1F);
		 	
	        /* Petit Troll :p */
	        if (p.getName().equalsIgnoreCase("FanaPik")) {
	        	Bukkit.broadcastMessage(prefixMessage+"Le vil �lFanaPik �rde la pl�basse est �o(malheureusement) �rl� !");
			}
	        if (p.getName().equalsIgnoreCase("Sinaynomis")) {
	        	Bukkit.broadcastMessage(prefixMessage+"�lSinaynomis �raka la perte de m�moire fr�quente (KingL orga aussi l'event) est l� !");
			}
	        /* Fin Troll */
	        
	        
	        currentSbHandler.updateSb(p);
	        TitleAPI.sendTitle(p, 10, 20, 10, "�cHey, "+p.getDisplayName()+"!", "Bienvenue sur Enigm!");
	        
	        
	        /* PlayerTeleportLobby */
	        if(enigmMain.isGameStarted == 0) {
	        	Location lobby = new Location(p.getWorld(),994.5,103,1291.5);
	        	p.teleport(lobby);
	        }else {
	        	p.sendMessage(prefixMessage + "�aLa partie est d�j� en cours !");
	        }
	        /* PlayerTeleportLobby */
	        
	        if(p.isOp()) {
	        	p.setPlayerListName("�6�l[GameMaster]�r�6 " + p.getName());
	        	p.setDisplayName("�6�l[GameMaster]�r�6 " + p.getName());
	        			
	        	teamHandler.addPlayerTeam("gameMaster", p);
	        	
	        	Bukkit.broadcastMessage(prefixMessage + "Le ma�tre �6�l" + p.getName()+ "�r est arriv�!"); //Pour l'�go mdrrr
	        }else {
	        	Bukkit.broadcastMessage(prefixMessage + "Le joueur " + "�e�l" + p.getName() + "�r vient de se connecter!");
	        	
	        	
	        /*Check if player is in a team*/
	        	
	        String currentPlayerTeam = teamHandler.getPlayerTeam(p);
	        
	        if (currentPlayerTeam.equalsIgnoreCase("0")) {
	        	p.sendMessage("�6�l� �rOh oh! Vous n'avez pas de team! ");
	        	p.sendMessage("�6�l� �rLaissons le hasard vous trouver une �quipe... ");
	        	
	        	int redPlayers = teamHandler.getNbPlayersInTeam("rouge");
	        	int bluePlayers = teamHandler.getNbPlayersInTeam("bleu");
	        	int yellowPlayers = teamHandler.getNbPlayersInTeam("jaune");
	        	int greenPlayers = teamHandler.getNbPlayersInTeam("vert");
	        	
	        	int maxPerTeam = enigmMain.getConfig().getInt("gameData.teams.maxPerTeam");
	        	
	        	int teamFound = 0;
	        	
	        	while(teamFound == 0) {
	        		
	        		int rdm = 1 + (int)(Math.random() *4);
	        		
	        		switch(rdm) {
	        		  case 1:
	        		    if(redPlayers < maxPerTeam) {
	        		    	Bukkit.broadcastMessage("�6�l� �rLe joueur �e�l"+p.getDisplayName()+"�r rejoint la team �c�lROUGE�r !");
	        		    	teamFound = 42;
	        		    	teamHandler.addColorPlayerTeam("rouge", p);
	        		    	
	        		    	
	        		    	teamHandler.addPlayerTeam("rouge", p);
	        		    }
	        		    break;
	        		    
	        		  case 2:
	          		    if(bluePlayers < maxPerTeam) {
	          		    	Bukkit.broadcastMessage("�6�l� �rLe joueur �e�l"+p.getDisplayName()+"�r rejoint la team �9�lBLEU�r !");
	          		    	teamFound = 42;
	          		    	teamHandler.addColorPlayerTeam("bleu", p);
	          		    	
	          		    	
	          		    	teamHandler.addPlayerTeam("bleu", p);
	          		    }
	          		    break;
	          		    
	        		  case 3:
	        		    if(yellowPlayers < maxPerTeam) {
	        		    	Bukkit.broadcastMessage("�6�l� �rLe joueur �e�l"+p.getDisplayName()+"�r rejoint la team �e�lJAUNE�r !");
	        		    	teamFound = 42;
	        		    	teamHandler.addColorPlayerTeam("jaune", p);
	        		    	
	        		    	teamHandler.addPlayerTeam("jaune", p);
	        		    }
	        		    break;
	        		    
	        		  case 4:
	          		    if(greenPlayers < maxPerTeam) {
	          		    	Bukkit.broadcastMessage("�6�l� �rLe joueur �e�l"+p.getDisplayName()+"�r rejoint la team �2�lVERTE �r!");
	          		    	teamFound = 42;
	          		    	teamHandler.addColorPlayerTeam("vert", p);
	          		    	
	          		    	teamHandler.addPlayerTeam("vert", p);
	          		    }
	          		    break;
	          		    

	        		  default:
	        			  teamFound = 0;
	        		    
	        		}
	        		
	        		if(teamFound == 0) {
	      		    	p.sendMessage("�6�l� �rC'est compliqu� de te trouver une team...Voyons o� il reste de la place...");
	      		    	p.sendMessage("�6�l� �rPlace non trouv�e, vous devenez un simple spectateur!");
	      		    	p.setGameMode(GameMode.SPECTATOR);
	      		    }
	        	}
	        	
	        	 
	        	 
	        }else {
	        	
	        teamHandler.addColorPlayerTeam(currentPlayerTeam, p);
	        }
		}
	 }
	 

}
