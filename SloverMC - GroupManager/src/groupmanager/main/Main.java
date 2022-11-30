package groupmanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import groupmanager.api.IGroupApi;
import groupmanager.api.Setting;
import groupmanager.commands.cSetGroup;
import groupmanager.databaseapi.PermissionManager;
import groupmanager.databaseapi.SqlConnection;
import groupmanager.events.PlayerEvents;

public class Main extends JavaPlugin {
	
	public static Main main;
	public static final Main getInstance() {
		return main;
	}
	
	public static Plugin plugin;
	public static final Plugin getPlugin() {
		return plugin;
	}
	
	public static final SqlConnection connection = new SqlConnection();
	public static final PermissionManager database = new PermissionManager();
	public static final Setting yml = new Setting();
	
	public void onEnable() {
		plugin = this;
		connection.ConnectMySQL();
		database.newTable();
		Setting.ymlfile.createYMLFil(plugin);
		IGroupApi.gerarTodosYML();
		Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
		getCommand("setgroup").setExecutor(new cSetGroup());
		getCommand("groupset").setExecutor(new cSetGroup());
		IGroupApi.updateAllRanks();
	}
	
	public void onDisable() {
		connection.DesconnectMySQL();
	}
	
public static final HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
	
	public static void loadPermission(Player p, String group) {
		setPermissions(p, group);	
	}
	
	public static void setPermissions(Player p, String group) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");
		PermissionAttachment attachment = p.addAttachment(plugin);
		perms.put(p.getUniqueId(), attachment);
		
		PermissionAttachment pperms = perms.get(p.getUniqueId());
		
		for (int i = list.size(); i > 0; i--) {
			if (!p.hasPermission(list.toString())) {
				pperms.setPermission(list.toString(), true);
			}
		}
	}
	
	public static void unsetPermissions(Player p, String group) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");

		for (int i = list.size(); i > 0; i--) {
			if (p.hasPermission(list.toString())) {
		        perms.get(p.getUniqueId()).unsetPermission(list.toString());
			}
		}
	}
}