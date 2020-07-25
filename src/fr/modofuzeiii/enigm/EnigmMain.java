package fr.modofuzeiii.enigm;

import org.bukkit.plugin.java.JavaPlugin;

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
		System.out.println("*******************");
		
		
		/*Commandes*/
		
		getCommand("ehelp").setExecutor(new EnigmHelp());
		getCommand("etest").setExecutor(new EnigmHelp());
		
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
