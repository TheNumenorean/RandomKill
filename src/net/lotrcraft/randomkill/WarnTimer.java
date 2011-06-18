package net.lotrcraft.randomkill;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class WarnTimer implements Runnable{
	    private Plugin p;
	    public WarnTimer(Plugin p) {
	        this.p = p;
	    }
	    public void run() {
	        Bukkit.getServer().broadcastMessage("A sacrifice will be chosen in thirty seconds!");
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new KillTimer(p), 30 * 20);
	    }

}
