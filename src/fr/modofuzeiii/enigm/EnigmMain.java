package fr.modofuzeiii.enigm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.modofuzeiii.enigm.commands.AdminCommands;
import fr.modofuzeiii.enigm.commands.BroadcastMessages;
import fr.modofuzeiii.enigm.commands.HelpEnigmPlugin;
import fr.modofuzeiii.enigm.commands.PlaySong;
import fr.modofuzeiii.enigm.commands.PointsManager;
import fr.modofuzeiii.enigm.commands.TeamManager;
import fr.modofuzeiii.enigm.database.DatabaseManager;
import fr.modofuzeiii.enigm.game.GameCode;
import fr.modofuzeiii.enigm.game.GameManager;
import fr.modofuzeiii.enigm.tasks.ActionBarTask;

@SuppressWarnings("unused")
public class EnigmMain extends JavaPlugin {
	
	/*Database info*/
	private DatabaseManager databaseManager;
	
	public  ScoreBoardHandler sbHandler;
	public TeamManager teamHandler;
	public PointsManager pointsHandler;
	public GameManager gameHandler;
	public String playerTeam;
	
	private ActionBarTask actionBarHandler;
	
	/*GameData*/
    
	  public  int isGameStarted; //indicate if the game is started
	  public int currentChronoAtStartup; //0: not started, 1: counting, 2 : ended
	  public  int redStateChallenge; //store the last finished challenge for each team
	  public  int greenStateChallenge;
	  public  int yellowStateChallenge;
	  public  int blueStateChallenge;
	  
	  public HashMap<UUID, String> mapPlayersTeam;
	
	@Override
	public void onEnable() {
		
		// Game initialization
		  isGameStarted = 0;
		  currentChronoAtStartup = 0;
		  
		  redStateChallenge = 0;
		  greenStateChallenge = 0;
		  yellowStateChallenge = 0;
		  blueStateChallenge = 0;
		
		
		//saveDefaultConfig();
		
		System.out.println("*******************");
		System.out.println("*                 *");
		System.out.println("*                 *");
		System.out.println("*  Le plugin est  *");
		System.out.println("*    en marche    *");
		System.out.println("*                 *");
		System.out.println("*                 *");
		System.out.println("*******************");
		
		/*Scoreboard!*/
		
		sbHandler = new ScoreBoardHandler(this);
		teamHandler = new TeamManager(this);
		pointsHandler = new PointsManager(this);
		gameHandler = new GameManager(this, pointsHandler);
		
		sbHandler.updateScoreboard4All(); //refresh sb
		
		/*Commandes*/
		
		getCommand("ehelp").setExecutor(new HelpEnigmPlugin());
		getCommand("etest").setExecutor(new HelpEnigmPlugin());
		getCommand("a").setExecutor(new BroadcastMessages());	
		getCommand("egm").setExecutor(new AdminCommands());
		getCommand("estart").setExecutor(gameHandler);
		getCommand("estop").setExecutor(gameHandler);
		getCommand("epause").setExecutor(gameHandler);
		getCommand("pts").setExecutor(new PointsManager(this));
		getCommand("teams").setExecutor(teamHandler);
		getCommand("ecode").setExecutor(new GameCode());
		getCommand("playsong").setExecutor(new PlaySong());
		
		/*events*/
		getServer().getPluginManager().registerEvents(new AdminEvents(), this);
		getServer().getPluginManager().registerEvents(new joinLeaveEvents(this, teamHandler), this);
		
		/*Database handler*/
		
		databaseManager = new DatabaseManager();
		
		/*Debug */
		
		//String code1 = this.getConfig().getString("gameData.codes.code1");
		
		/*Actions bars*/
		
	    actionBarHandler = new ActionBarTask(this);
	    
	    
		actionBarHandler.runTaskTimer(this, 0, 3*20);
		
		mapPlayersTeam = new HashMap<UUID, String>();
		
		teamHandler.loadTeamsIntoHashMap();
		
		
	}
	
    @Override
    public void onDisable() {
        System.out.println("*******************");
    	System.out.println("*                 *");
    	System.out.println("*                 *");
    	System.out.println("*  Le plugin est  *");
    	System.out.println("*      arrete     *");
    	System.out.println("*                 *");
    	System.out.println("*                 *");
    	System.out.println("*******************");
    	
    	this.databaseManager.close();
        }
    
    public DatabaseManager getDatabaseManager() {
    	return databaseManager;
    }
    
}
