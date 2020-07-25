package fr.modofuzeiii.enigm;

import org.bukkit.plugin.java.JavaPlugin;

import fr.modofuzeiii.enigm.commands.EnigmBroadcast;
import fr.modofuzeiii.enigm.commands.EnigmHelp;
import fr.modofuzeiii.enigm.game.EnigmManager;

public class EnigmMain extends JavaPlugin {
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
		
		getCommand("ehelp").setExecutor(new EnigmHelp());
		getCommand("etest").setExecutor(new EnigmHelp());
		getCommand("a").setExecutor(new EnigmBroadcast());
		getCommand("estart").setExecutor(new EnigmManager());
		getCommand("estop").setExecutor(new EnigmManager());
		getCommand("epause").setExecutor(new EnigmManager());
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
        }
}
