package br.com.slovermc.ycommons.bungee.sheduler;

import java.util.concurrent.TimeUnit;

import br.com.slovermc.ycommons.bungee.BungeeMain;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.scheduler.TaskScheduler;

@SuppressWarnings("unused")
public class Sheduler {
  private BungeeMain ab;
  
  public Sheduler(BungeeMain main)
  {
    this.ab = main;
  }
  
  public void msg()
  {
    ProxyServer.getInstance().getScheduler().schedule(this.ab, new Runnable()
    {
      int i = 0;
      int last = (int)(Math.random() * Sheduler.this.ab.messages.size());
      
      public void run()
      {
        if (Sheduler.this.ab.random)
        {
          int r;
          do
          {
            r = (int)(Math.random() * Sheduler.this.ab.messages.size());
          } while (r == this.last);
          ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(String.valueOf(Sheduler.this.ab.px) + (String)Sheduler.this.ab.messages.get(r)));
          this.last = r;
          return;
        }
        if (this.i < Sheduler.this.ab.messages.size())
        {
          ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(String.valueOf(Sheduler.this.ab.px) + (String)Sheduler.this.ab.messages.get(this.i)));
          this.i += 1;
          return;
        }
        this.i = 0;
        ProxyServer.getInstance().broadcast(TextComponent.fromLegacyText(String.valueOf(Sheduler.this.ab.px) + (String)Sheduler.this.ab.messages.get(this.i)));
        this.i += 1;
      }
    }, this.ab.sec, this.ab.sec, TimeUnit.SECONDS);
  }
}
