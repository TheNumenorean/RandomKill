package net.lotrcraft.randomkill;

import java.util.ArrayList;
import java.util.List;
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
        
        if (!RKMain.useList){                                                              //Test if Hit List should be used. If not...
        	if (onlineplayers.length != 0){                                                //Check to make sure there is anyone to kill
        		Player sac = onlineplayers[new Random().nextInt(onlineplayers.length)];    //Get a random player
        		sac.getLocation().getWorld().strikeLightning(sac.getLocation());
        		sac.damage(20);
        		Bukkit.getServer().broadcastMessage("A Sacrifice has been chosen!");
        		log.info("[RandomKill] A player has been exterminated.");
        	} else log.info("[RandomKill] No players to kill.");
        } else {
        	if (onlineplayers.length != 0){ 
        		List<Player> pd = new ArrayList<Player>();
        		for(Player pl : Bukkit.getServer().getOnlinePlayers()) {// Thanks BR_
        			if(RKMain.players.contains(pl.getName())) {
        				pd.add(pl);
        			}
        		}
        		if (pd.size() !=0){
  
        			Player sac = pd.get(new Random().nextInt(pd.size()));
        			sac.damage(20);
        			Bukkit.getServer().broadcastMessage("A Sacrifice has been chosen!");
        			log.info("[RandomKill] A player has been exterminated.");
        		}else log.info("[RandomKill] No players on the list to kill.");
        		
        	} else log.info("[RandomKill] No players to kill.");
        }
    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new WarnTimer(p), randomnumber - 600);

		
    }
}
