package br.com.slovermc.groupmanager;

import br.com.slovermc.groupmanager.api.IGroupApi;
import br.com.slovermc.groupmanager.api.Setting;
import br.com.slovermc.groupmanager.commands.cMiniliga;
import br.com.slovermc.groupmanager.commands.cSetGroup;
import br.com.slovermc.groupmanager.databaseapi.PermissionManager;
import br.com.slovermc.groupmanager.databaseapi.SqlConnection;
import br.com.slovermc.groupmanager.events.PlayerEvents;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {
  
	public static Main main;
   public static Plugin plugin;
   public static final SqlConnection connection = new SqlConnection();
   public static final PermissionManager database = new PermissionManager();
   public static final Setting yml = new Setting();
   public static final HashMap<UUID, PermissionAttachment> perms = new HashMap();

   public static final Main getInstance() {
      return main;
   }

   public static final Plugin getPlugin() {
      return plugin;
   }

   public void onEnable() {
      plugin = this;
      this. getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
      this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
      connection.ConnectMySQL();
      database.newTable();
      Setting.ymlfile.createYMLFil(plugin);
      IGroupApi.gerarTodosYML();
      Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
      this.getCommand("setgroup").setExecutor(new cSetGroup());
      this.getCommand("groupset").setExecutor(new cSetGroup());
      this.getCommand("miniliga").setExecutor(new cMiniliga());
      this.getCommand("evento").setExecutor(new cMiniliga());
      this.getCommand("minizenix").setExecutor(new cMiniliga());
      this. getCommand("event").setExecutor(new cMiniliga());
      IGroupApi.updateAllRanks();
   }

   public void onDisable() {
      connection.DesconnectMySQL();
   }

   public static void loadPermission(Player p, String group) {
      setPermissions(p, group);
   }

   public static void setPermissions(Player p, String group) {
      ArrayList<String> list = (ArrayList)yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");
      PermissionAttachment attachment = p.addAttachment(plugin);
      perms.put(p.getUniqueId(), attachment);
      PermissionAttachment pperms = (PermissionAttachment)perms.get(p.getUniqueId());

      for(int i = list.size(); i > 0; --i) {
         if (!p.hasPermission(list.toString())) {
            pperms.setPermission(list.toString(), true);
         }
      }

   }

   public static void unsetPermissions(Player p, String group) {
      ArrayList<String> list = (ArrayList)yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");

      for(int i = list.size(); i > 0; --i) {
         if (p.hasPermission(list.toString())) {
            ((PermissionAttachment)perms.get(p.getUniqueId())).unsetPermission(list.toString());
         }
      }

   }

@Override
public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
	// TODO Auto-generated method stub
	
}
}