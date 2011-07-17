package net.lotrcraft.randomkill;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.util.config.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class RKMain extends JavaPlugin{
	public static PermissionHandler permissionHandler;
	public static int randomnumber, max, min;
	public static boolean useList;
	public static List<String> players;
	//public static Player[] players;
	//File file =new File("//RandomKill//config.yml");
	Logger log = Logger.getLogger("minecraft");
	Random r = new Random();
	//ConfigurationNode timeamt = this.getConfiguration();
	Configuration config;
	
	public void onDisable() {
		log.info("RandomKill Disabled.");
	}

	public void onEnable() {
		config = this.getConfiguration();
		this.getServer().getPluginManager();
		log.info("RandomKill Enabled.");
		//if(config.()){
		//	System.out.println("[RandomKill] Config doesnt exist or is outdated. Creating...");
			//file.getParentFile().mkdir();
		//}
		if (config.getInt("minimum", 60) <= 0) {
			config.setProperty("minimum", 60);
		}
		if (config.getInt("maximum", 120) <= 0) {
			config.setProperty("maximum", 120);
		}
		if (config.getInt("maximum", 120) < config.getInt("minimum", 60)) {
			int max = config.getInt("maximum", 120);
			config.setProperty("maximum", config.getInt("minimum", 60));
			config.setProperty("minimum", max);
		}
		if (config.getProperty("useList") == null){
			config.setProperty("useList", false);
		}
		if (config.getProperty("Players") == null){
			config.setProperty("Players", null);
		}
		players = config.getStringList("Players", null);
		
		config.setHeader("#Version 0.1");
		config.save();
		useList = config.getBoolean("useList", false); 
		max = config.getInt("maximum", 120);
		min = config.getInt("minimum", 60);
		randomnumber = r.nextInt((max - min) * 20) + min * 20;
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new WarnTimer(this), randomnumber-30);
		//Hello!
		setupPermissions();

	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//log.info(args[0]);
		if (args.length > 2 || args.length <= 0){
			return false;
		}
		if (!(sender instanceof ConsoleCommandSender)){
		if (args[0].equals("add")){
			if (!RKMain.permissionHandler.has((Player) sender, "RK.list.add")){
				sender.sendMessage("[RandomKill] You dont have permission to use this command!");
				return true;
			}
			if (players.contains(args[1])){
				sender.sendMessage("[RandomKill] This player already added.");
				return true;
			}
			players.add(args[1]);
			config.setProperty("Players", players);
			config.save();
			sender.sendMessage("[RandomKill] Player " + args[1] + " added.");
			return true;
		}
		if (args[0].equals("remove")){
			if (!RKMain.permissionHandler.has((Player) sender, "RK.list.remove") && !(sender instanceof ConsoleCommandSender)){
				sender.sendMessage("[RandomKill] You dont have permission to use this command!");
				return true;
			}
			if (!players.contains(args[1])){
				sender.sendMessage("[RandomKill] Player not found.");
				return true;
			}
			players.remove(args[1]);
			config.setProperty("Players", players);
			config.save();
			sender.sendMessage("[RandomKill] Player " + args[1] + " removed.");
			return true;
		}
		if(args[0].equals("kill")){
			if (!RKMain.permissionHandler.has((Player) sender, "RK.kill") && !(sender instanceof ConsoleCommandSender)){
				sender.sendMessage("[RandomKill] You dont have permission to use this command!");
				return true;
			}
			Player p = Bukkit.getServer().matchPlayer(args[1]).get(0);
			p.damage(100);
			return true;
		}
		return false;
		}
		
		
		
		if (args[0].equals("add")){
			if (players.contains(args[1])){
				log.info("[RandomKill] This player already added.");
				return true;
			}
			players.add(args[1]);
			config.setProperty("Players", players);
			config.save();
			log.info("[RandomKill] Player " + args[1] + " added.");
			return true;
		}
		if (args[0].equals("remove")){
			if (!players.contains(args[1])){
				log.info("[RandomKill] Player not found.");
				return true;
			}
			players.remove(args[1]);
			config.setProperty("Players", players);
			config.save();
			log.info("[RandomKill] Player " + args[1] + " removed.");
			return true;
		}
		if(args[0].equals("kill")){
			if (Bukkit.getServer().matchPlayer(args[1]) == null){
				log.info("[RandomKill] Player not found.");
				return true;
			}
			Player p = Bukkit.getServer().matchPlayer(args[1]).get(0);
			p.damage(100);
			log.info("[RandomKill] Player " + args[1] + " killed.");
			return true;
		}
		return false;
	}
	
	private void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        log.info("Permission system not detected, defaulting to OP");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    log.info("Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}

	
}
