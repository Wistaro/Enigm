package fr.modofuzeiii.enigm;

import org.bukkit.plugin.java.JavaPlugin;

public class EnigmMain extends JavaPlugin {
	@Override
	public void onEnable() {
		System.out.println("*******************");
		System.out.println("*                 *");
		System.out.println("*                 *");
		System.out.println("*  Le plugin est  *");
		System.out.println("*    En marche    *");
		System.out.println("*                 *");
		System.out.println("*                 *");
		System.out.println("*******************");
	}
    @Override
    public void onDisable() {
        System.out.println("*******************");
    	System.out.println("*                 *");
    	System.out.println("*                 *");
    	System.out.println("*  Le plugin est  *");
    	System.out.println("*      Arrêté     *");
    	System.out.println("*                 *");
    	System.out.println("*                 *");
    	System.out.println("*******************");
        }
}
