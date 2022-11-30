package br.com.slovermc.hg.tempos;

import javax.swing.ImageIcon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.scoreboard.Score;
import br.com.slovermc.hg.utils.Permission;

public class EndListeners implements Listener {
	
	@EventHandler
	protected void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.END) {
			if (player.hasPermission(Permission.getInstance().ADMIN)) { 
				player.performCommand("admin");
				Score.hasScore.add(player);
				Score.createScoreEnd(player);
				KitAPI.getInstance().removeKit(player);
				KitAPI.getInstance().removeKit2(player);
			}
			else if (player.hasPermission(Permission.getInstance().SPECT)) { 
				Manager.getInstance().setSpectador(player);
				KitAPI.getInstance().removeKit(player);
				KitAPI.getInstance().removeKit2(player);
			}
		}
	}

	@EventHandler
	protected void inicializarMapa(MapInitializeEvent evento) {
		MapView mapa;
		for (MapRenderer render : (mapa = evento.getMap()).getRenderers()) {
			mapa.removeRenderer(render);
		}
		mapa.addRenderer(new MapRenderer() {
			public final void render(MapView ver, MapCanvas map, Player player) {
				map.drawText(30, 10, MinecraftFont.Font, "Parabéns");
				map.drawText(30, 20, MinecraftFont.Font, player.getName());
				map.drawText(30, 30, MinecraftFont.Font, "Você venceu!");
				map.drawImage(15, 42, new ImageIcon(BukkitMain.getPlugin().getDataFolder().getPath() + "/bolo.png").getImage());
			}
		});
	}

	@EventHandler
	protected void onDamage(EntityDamageEvent e) {
		if (BukkitMain.state != StateEnum.END) return;
		e.setCancelled(true);
	}
}
