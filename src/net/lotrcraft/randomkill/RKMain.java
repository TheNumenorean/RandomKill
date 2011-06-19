package net.lotrcraft.randomkill;

import java.util.logging.Logger;
import org.bukkit.util.config.*;
import org.bukkit.plugin.java.JavaPlugin;

public class RKMain extends JavaPlugin{
	public static int randomnumber, max, min;
	//File file =new File("//RandomKill//config.yml");
	Logger log = Logger.getLogger("minecraft");
	//ConfigurationNode timeamt = this.getConfiguration();
	Configuration config;
	
	public void onDisable() {
		log.info("RandomKill Disabled.");
	}

	public void onEnable() {
		config = this.getConfiguration();
		// TODO Auto-generated method stub
		this.getServer().getPluginManager();
		log.info("RandomKill Enabled.");
		//if(config.()){
		//	System.out.println("[RandomKill] Config doesnt exist or is outdated. Creating...");
			//file.getParentFile().mkdir();
		//}
		if (config.getInt("minimum", 0) <= 0) {
			config.setProperty("minimum", 60);
		}
		if (config.getInt("maximum", 0) <= 0) {
			config.setProperty("maximum", 120);
		}
		if (config.getInt("maximum", 0) < config.getInt("minimum", 0)) {
			int max = config.getInt("maximum", 0);
			config.setProperty("maximum", config.getInt("minimum", 0));
			config.setProperty("minimum", max);
		}
		config.setHeader("Version 0.1");
		config.save();
		max = config.getInt("maximum", 120);
		min = config.getInt("minimum", 60);
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new WarnTimer(this), 20 * 30);
	}
	
}
