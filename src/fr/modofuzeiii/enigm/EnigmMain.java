package fr.modofuzeiii.enigm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.java.JavaPlugin;

import fr.modofuzeiii.enigm.commands.AdminCommands;
import fr.modofuzeiii.enigm.commands.BroadcastMessages;
import fr.modofuzeiii.enigm.commands.HelpEnigmPlugin;
import fr.modofuzeiii.enigm.database.DatabaseManager;
import fr.modofuzeiii.enigm.game.GameManager;

public class EnigmMain extends JavaPlugin {
	
	/*Database info*/
	private DatabaseManager databaseManager;
    
    
	
	@Override
	public void onEnable() {
		System.out.println("*******************");
		System.out.println("*                 *");
		System.out.println("*                 *");
		System.out.println("*  Le plugin est  *");
		System.out.println("*    en marche    *");
		System.out.println("*                 *");
		System.out.println("*                 *");
		System.out.println("*******************");
		
		
		/*Commandes*/
		
		getCommand("ehelp").setExecutor(new HelpEnigmPlugin());
		getCommand("etest").setExecutor(new HelpEnigmPlugin());
		getCommand("a").setExecutor(new BroadcastMessages());	
		getCommand("egm").setExecutor(new AdminCommands());
		getCommand("estart").setExecutor(new GameManager());
		getCommand("estop").setExecutor(new GameManager());
		getCommand("epause").setExecutor(new GameManager());
		
		/*events*/
		getServer().getPluginManager().registerEvents(new AdminEvents(), this);
		getServer().getPluginManager().registerEvents(new joinLeaveEvents(this), this);
		
		/*Database handler*/
		
		databaseManager = new DatabaseManager();
		
		/*Databse settings
		
		host = "db4free.net";
        port = 3306;
        database = "enigm_bdd";
        username = "fiouze";
        password = "recrutements";  */
        
  
		
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
