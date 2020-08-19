package fr.modofuzeiii.enigm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.modofuzeiii.enigm.commands.AdminCommands;
import fr.modofuzeiii.enigm.commands.BroadcastMessages;
import fr.modofuzeiii.enigm.commands.HelpEnigmPlugin;
import fr.modofuzeiii.enigm.commands.PointsManager;
import fr.modofuzeiii.enigm.commands.TeamManager;
import fr.modofuzeiii.enigm.database.DatabaseManager;
import fr.modofuzeiii.enigm.game.GameManager;

@SuppressWarnings("unused")
public class EnigmMain extends JavaPlugin {
	
	/*Database info*/
	private DatabaseManager databaseManager;
	
	public  ScoreBoardHandler sbHandler;
	public TeamManager teamHandler;
    
    
	
	@Override
	public void onEnable() {
		
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
		
		
		/*Commandes*/
		
		getCommand("ehelp").setExecutor(new HelpEnigmPlugin());
		getCommand("etest").setExecutor(new HelpEnigmPlugin());
		getCommand("a").setExecutor(new BroadcastMessages());	
		getCommand("egm").setExecutor(new AdminCommands());
		getCommand("estart").setExecutor(new GameManager());
		getCommand("estop").setExecutor(new GameManager());
		getCommand("epause").setExecutor(new GameManager());
		getCommand("pts").setExecutor(new PointsManager(this));
		getCommand("teams").setExecutor(teamHandler);
		
		/*events*/
		getServer().getPluginManager().registerEvents(new AdminEvents(), this);
		getServer().getPluginManager().registerEvents(new joinLeaveEvents(this), this);
		
		/*Database handler*/
		
		databaseManager = new DatabaseManager();
		
		/*Debug */
		
		//String code1 = this.getConfig().getString("gameData.codes.code1");
		
		
        
  
		
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
