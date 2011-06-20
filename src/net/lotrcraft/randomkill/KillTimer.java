package net.lotrcraft.randomkill;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KillTimer implements Runnable{
    private Plugin p;
    Logger log = Logger.getLogger("minecraft");
	Random r = new Random();
    public KillTimer(Plugin p) {
        this.p = p;
    }
    public void run() {
		int randomnumber = r.nextInt((RKMain.max - RKMain.min) * 20) + RKMain.min * 20;
        Player[] players = Bukkit.getServer().getOnlinePlayers();
        log.info(players + "");
        if (players.length != 0){
        	players[new Random().nextInt(players.length)].damage(100);
        	Bukkit.getServer().broadcastMessage("A Sacrifice has been chosen!");
        	log.info("DOOOOOOMMM");
        } else log.info("[RandomKill] No players to kill.");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new KillTimer(p), randomnumber );
        
		
    }
}
