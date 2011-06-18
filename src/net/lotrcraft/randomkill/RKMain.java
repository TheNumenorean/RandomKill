package net.lotrcraft.randomkill;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.util.config.*;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class RKMain extends JavaPlugin{
	File file =new File("//RandomKill//config.yml");
	Logger log = Logger.getLogger("minecraft");
	//ConfigurationNode timeamt = this.getConfiguration();
	Configuration config = this.getConfiguration();
	Random r = new Random();
	
	@Override
	public void onDisable() {
		log.info("RandomKill Disabled.");
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		this.getServer().getPluginManager();
		log.info("RandomKill Enabled.");
		if(!file.exists()){
			System.out.println("[RandomKill] Config doesnt exist or is outdated. Creating...");
			//file.getParentFile().mkdir();
		}
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
		config.save();
		config.setHeader("Version 0.1");
		int max = config.getInt("maximum", 120);
		int min = config.getInt("minimum", 60);
		int randomnumber = r.nextInt((max - min) * 20) + min * 20;
		int a;
	}
	
}
