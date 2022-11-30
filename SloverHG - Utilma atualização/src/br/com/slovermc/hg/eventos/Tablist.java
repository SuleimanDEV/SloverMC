package br.com.slovermc.hg.eventos;

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

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.mysql.RankList;

@SuppressWarnings("unused")
public class Tablist implements Listener {

	private static int VERSION = 47;

	@EventHandler
	private void Tab(PlayerJoinEvent e) {

		final Player jogador = e.getPlayer();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				PlayerConnection connect = ((CraftPlayer)jogador).getHandle().playerConnection;
				IChatBaseComponent top = ChatSerializer.a("{'extra': [{text: '', color: 'aqua'}],'color': gold, 'text': '\n                    §6§lMatch§f§lMC §eHardcore Games              \n         §fAtualmente temos §a" + Bukkit.getOnlinePlayers().length + " §fjogadores no §aHG-" + StatusHG.getIP() + "\n'}");

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
