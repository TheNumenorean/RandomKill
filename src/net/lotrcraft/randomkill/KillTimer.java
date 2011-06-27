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
	//Player[] players = RKMain.players;
    public KillTimer(Plugin p) {
        this.p = p;
    }
    public void run() {
		int randomnumber = r.nextInt((RKMain.max - RKMain.min) * 20) + RKMain.min * 20;
        Player[] onlineplayers = Bukkit.getServer().getOnlinePlayers();
        for (int i = 0; i <= onlineplayers.length; i++){
        	
        }
        
        if (onlineplayers.length != 0){
        	Player sac = onlineplayers[new Random().nextInt(onlineplayers.length)];
        	Bukkit.getServer().broadcastMessage("A Sacrifice has been chosen!");
        	log.info("[RandomKill] A player has been exterminated.");
        } else log.info("[RandomKill] No players to kill.");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new WarnTimer(p), randomnumber - 600);
        /*
         *   if (!(MyPlugin).permissionHandler.has(player, "a.custom.node")) {
      return;
  }

         */
		
    }
}
