package groupmanager.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import groupmanager.api.IGroupApi;
import groupmanager.databaseapi.SqlConnection;
import groupmanager.main.Main;

public class PlayerEvents implements Listener {
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if (!Main.database.verifyPlayerRegister(e.getPlayer().getName())) {
			Main.database.newPlayer(e.getPlayer());
		}
		IGroupApi.loadPermission(e.getPlayer(), Main.database.getGroup(e.getPlayer()));
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		IGroupApi.loadPermission(e.getPlayer(), Main.database.getGroup(e.getPlayer()));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (SqlConnection.connection == null) {
					e.getPlayer().sendMessage("§6§lGROUPSET§f A §a§lCONEXAO§f com a §b§lMYSQL§f está §c§lNULA!§f Não foi possível carregar seu §e§lGRUPO!");
				}
			}			
		}, 40L);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		IGroupApi.unsetPermissions(e.getPlayer(), Main.database.getGroup(e.getPlayer()));
	}
}