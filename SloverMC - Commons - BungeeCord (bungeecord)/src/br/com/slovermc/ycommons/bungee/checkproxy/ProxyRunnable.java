package br.com.slovermc.ycommons.bungee.checkproxy;

import java.util.concurrent.TimeUnit;

import br.com.slovermc.ycommons.bungee.BungeeMain;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public abstract class ProxyRunnable implements Runnable {
	
  public abstract void run();
  
  public ScheduledTask runAsync() {
    return ProxyServer.getInstance().getScheduler().runAsync(BungeeMain.instances, this);
  }
  
  public ScheduledTask runTaskLater(long time, TimeUnit unit) {
    return ProxyServer.getInstance().getScheduler().schedule(BungeeMain.instances, this, time, unit);
  }
  
  public ScheduledTask runTaskTimer(long time, long repeat, TimeUnit unit) {
    return ProxyServer.getInstance().getScheduler().schedule(BungeeMain.instances, this, time, repeat, unit);
  }
}
