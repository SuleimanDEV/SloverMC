package br.com.slovermc.gladiator.events;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.spigotmc.ProtocolInjector;
import org.spigotmc.ProtocolInjector.PacketTabHeader;

import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.mysql.RankList;

@SuppressWarnings("unused")
public class Tablist implements Listener {
	
  private static int VERSION = 47;
  
  @EventHandler
  void TabDoServidor(PlayerJoinEvent e)
  {
    final Player jogador = e.getPlayer();
	final int ping = ((CraftPlayer)jogador).getHandle().playerConnection.player.ping;
    Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getInstance(), new Runnable()
    {
      @SuppressWarnings("deprecation")
	public void run()
      {
        PlayerConnection connect = ((CraftPlayer)jogador).getHandle().playerConnection;
        IChatBaseComponent top = ChatSerializer.a("{'extra': [{text: '', color: 'aqua'}],'color': gold, 'text': '\n                    §6§lSLOVERMC §e§lGLADIATOR                \n            §fAtualmente temos §a" + Bukkit.getOnlinePlayers().length + " §fjogador(es) no §aGladiator \n'}");
        
        IChatBaseComponent bottom = ChatSerializer.a("{'extra': [{'color': 'aqua', 'text': '\n §bNick: §f" + jogador.getName() + " §1- §bLiga: " + RankList.getScoreRank(jogador) + "\n§bMais informações em §f@SloverMC_\n"
				+ "', 'underline': 'true'}], 'color': 'gold', 'text': ''}");
        if (((CraftPlayer)jogador).getHandle().playerConnection.networkManager.getVersion() < Tablist.VERSION) {
          return;
        }
        connect.sendPacket(new ProtocolInjector.PacketTabHeader(top, bottom));
      }
    }, 1L, 20L);
  }
} 
