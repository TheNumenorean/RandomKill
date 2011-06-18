package net.lotrcraft.randomkill;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KillTimer implements Runnable{
    private Plugin p;
	Random r = new Random();
    public KillTimer(Plugin p) {
        this.p = p;
    }
    public void run() {
		int randomnumber = r.nextInt((RKMain.max - RKMain.min) * 20) + RKMain.min * 20;
        Player[] players = Bukkit.getServer().getOnlinePlayers();
        players[new Random().nextInt(players.length)].damage(Integer.MAX_VALUE);
		Bukkit.getServer().getScheduler().registerSyncDelayedTask(p, new WarnTimer(p), randomnumber );
    }
}
