package br.com.slovermc.groupmanager.events;

import br.com.slovermc.groupmanager.Main;
import br.com.slovermc.groupmanager.api.IGroupApi;
import br.com.slovermc.groupmanager.databaseapi.SqlConnection;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {
   @EventHandler
   public void onLogin(PlayerLoginEvent e) {
      if (!Main.database.verifyPlayerRegister(e.getPlayer().getName())) {
         Main.database.newPlayer(e.getPlayer());
      }

      IGroupApi.loadPermission(e.getPlayer(), Main.database.getGroup(e.getPlayer()));
   }

   @EventHandler
   public void onJoin(final PlayerJoinEvent e) {
      e.setJoinMessage((String)null);
      IGroupApi.loadPermission(e.getPlayer(), Main.database.getGroup(e.getPlayer()));
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
         public void run() {
            if (SqlConnection.connection == null) {
               e.getPlayer().sendMessage("§6§lGROUPSET§f A §a§lCONEXAO§f com a §b§lMYSQL§f está §c§lNULA!§f Não foi possível carregar seu §e§lGRUPO!");
            }

         }
      }, 40L);
   }

   @EventHandler
   public void onQuit(PlayerQuitEvent e) {
      e.setQuitMessage((String)null);
      IGroupApi.unsetPermissions(e.getPlayer(), Main.database.getGroup(e.getPlayer()));
   }
}