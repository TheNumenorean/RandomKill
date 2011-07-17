package net.lotrcraft.randomkill;

import java.util.ArrayList;
import java.util.Collection;
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
        
        if (RKMain.useList == false){
        	if (onlineplayers.length != 0){
        		Player sac = onlineplayers[new Random().nextInt(onlineplayers.length)];
        		sac.damage(20);
        		Bukkit.getServer().broadcastMessage("A Sacrifice has been chosen!");
        		log.info("[RandomKill] A player has been exterminated.");
        	} else log.info("[RandomKill] No players to kill.");
        } else {
        	if (onlineplayers.length != 0){
        		List<Player> pd = new ArrayList<Player>();
        		for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
        		if(pd.contains(pl.getName())) {
        		pd.add(pl);
        		}
        		}
        		
        		
        		
        		
        		/*
        		List<Player> list;
        		Collection onpl;
        		onpl.add(onlineplayers);
        		list.retainAll(onpl);

        		for (int i=0; i<list.size(); i++){
        			Player a = Bukkit.getServer().getPlayer(list[i]);
        			
        		}
        		Player[] saclist = list.;*/
        		Player sac = Bukkit.getServer().getPlayer(pd[new Random().nextInt(pd.length)]);
        		sac.damage(20);
        		Bukkit.getServer().broadcastMessage("A Sacrifice has been chosen!");
        		log.info("[RandomKill] A player has been exterminated.");
        	} else log.info("[RandomKill] No players to kill.");
        }
    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new WarnTimer(p), randomnumber - 600);

		
    }
}
