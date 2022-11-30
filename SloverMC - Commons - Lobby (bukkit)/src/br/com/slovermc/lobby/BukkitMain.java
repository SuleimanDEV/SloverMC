package br.com.slovermc.lobby;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.lobby.api.XP;
import br.com.slovermc.lobby.bungeecord.BungeeManager;
import br.com.slovermc.lobby.commands.EAdmin;
import br.com.slovermc.lobby.commands.cAddxpCMD;
import br.com.slovermc.lobby.commands.cAdminCMD;
import br.com.slovermc.lobby.commands.cChatCMD;
import br.com.slovermc.lobby.commands.cGmCMD;
import br.com.slovermc.lobby.commands.cLigaCMD;
import br.com.slovermc.lobby.commands.cLimparchatCMD;
import br.com.slovermc.lobby.commands.cRemoveXpCMD;
import br.com.slovermc.lobby.commands.cSsCMD;
import br.com.slovermc.lobby.commands.cTellCMD;
import br.com.slovermc.lobby.commands.cTpCMD;
import br.com.slovermc.lobby.commands.cXpCMD;
import br.com.slovermc.lobby.events.PlayerEvents;
import br.com.slovermc.lobby.hologramas.HologramaEvent;
import br.com.slovermc.lobby.hologramas.FileHl;
import br.com.slovermc.lobby.hologramas.SetHl;
import br.com.slovermc.lobby.hologramas.HologramaCommand;
import br.com.slovermc.lobby.mysql.MySQL_Connection;
import br.com.slovermc.lobby.mysql.MySQL_Settings;
import br.com.slovermc.lobby.score.Score;
import br.com.slovermc.lobby.utils.Menus;

public class BukkitMain extends JavaPlugin implements PluginMessageListener {
	
	public static BukkitMain instance;
	public static Plugin plugin;
	
	
	public static BukkitMain getInstance() {
		return instance;
	}
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static int onlinePlayers = 0;
	public static int onlinegladiator = 0;
	public static int onlinekitpvp = 0;
	public static int onlineskywars = 0;
	public static int onlinehga1 = 0;
	public static int onlinehga2 = 0;
	public static int onlinehga3 = 0;
	public static int onlinehga4 = 0;
	public static int onlinehga5 = 0;
	public static int onlinehga6 = 0;
	
	public static final String notplayer = "§c§lERRO§f Comando apenas para jogadores.";
	public static final String noperm = "§c§lERRO§f Você não tem permissão para executar este comando.";
	public static final String notonline = "§c§lERRO §fEste player não está online.";
	
	public static final MySQL_Connection connect = new MySQL_Connection();
	public static final String BUNGEE_CHANNEL = "BungeeCord";
	public static final String commandMapFieldClass = "commandMap";
	public static final PluginManager pluginmanager = Bukkit.getPluginManager();
	public static final MySQL_Settings mysql = new MySQL_Settings();
	public static BungeeManager bungee = new BungeeManager();

	public void onEnable() {
		instance = this;
		plugin = this;
		connect.ConnectMySQL();
		mysql.newTable();
		XP.createFile();
		CommandsHologramas();
		FileHl.createLocationsFile(plugin);
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, BUNGEE_CHANNEL);
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, BUNGEE_CHANNEL, this);
	    registerEvents();
	    registerCommands();
	    run();
	   // BukkitTasks.updateAllAnimations();
		Score.updateAllScoreboards();
		Bukkit.getConsoleSender().sendMessage("");
	    Bukkit.getConsoleSender().sendMessage("§aPlugin iniciado com sucesso, acesso permitido.");
	}
	
	public static void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					Online(p, "ALL");
					Online(p, "gladiator");
					Online(p, "pvp-1");
					Online(p, "hga1");
					Online(p, "hga2");
					Online(p, "hga3");
					Online(p, "hga4");
					Online(p, "hga5");
					Online(p, "hga6");
				}
			}
		}, 0, 3L);
	}
	
	public static void Online(Player p, String server) {		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
	    out.writeUTF("PlayerCount");
	    out.writeUTF(server);
	    p.sendPluginMessage(getInstance(), BUNGEE_CHANNEL, out.toByteArray());
	}
	
	public void registerEvents() {
		pluginmanager.registerEvents(new PlayerEvents(), this);
		pluginmanager.registerEvents(new Menus(), this);
		pluginmanager.registerEvents(new EAdmin(), this);
		pluginmanager.registerEvents(new cAdminCMD(), this);
		pluginmanager.registerEvents(new HologramaEvent(), this);
		pluginmanager.registerEvents(new cChatCMD(), this);
	}
	
	public void registerCommands() {
		getCommand("gm").setExecutor(new cGmCMD());
		getCommand("tell").setExecutor(new cTellCMD());
		getCommand("tp").setExecutor(new cTpCMD());
		getCommand("ss").setExecutor(new cSsCMD());
		getCommand("screenshare").setExecutor(new cSsCMD());
		getCommand("cc").setExecutor(new cLimparchatCMD());
		getCommand("gamemode").setExecutor(new cGmCMD());
		getCommand("admin").setExecutor(new cAdminCMD());
		getCommand("addxp").setExecutor(new cAddxpCMD());
		getCommand("removexp").setExecutor(new cRemoveXpCMD());
		getCommand("xp").setExecutor(new cXpCMD());
		getCommand("account").setExecutor(new cXpCMD());
		getCommand("liga").setExecutor(new cLigaCMD());
		getCommand("ligas").setExecutor(new cLigaCMD());
		getCommand("chat").setExecutor(new cChatCMD());
	}
	
	@SuppressWarnings("deprecation")
	public void onDisable() {
		for (Player todos : Bukkit.getOnlinePlayers()) {
			todos.kickPlayer("§c§lREINICIANDO\n\n§fO servidor está reiniciando\n§fvolte dentre alguns minutos!");
		}
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player receiver, byte[] msg) {
		if (channel.equals("BungeeCord")) {
			ByteArrayDataInput in = ByteStreams.newDataInput(msg);
			String subChannel = in.readUTF();
			if (subChannel.equals("PlayerCount")) {
				try {
					String server = in.readUTF();
					if (server.equals("ALL")) {
						onlinePlayers = in.readInt();
					} 
					if (server.equals("gladiator")) {
						onlinegladiator = in.readInt();
					}
					if (server.equals("pvp-1")) {
						onlinekitpvp = in.readInt();
					}
					if (server.equals("hga1")) {
						onlinehga1 = in.readInt();
					}
					if (server.equals("hga2")) {
						onlinehga2 = in.readInt();
					}
					if (server.equals("hga3")) {
						onlinehga3 = in.readInt();
					}
					if (server.equals("hga4")) {
						onlinehga4 = in.readInt();
					}
					if (server.equals("hga5")) {
						onlinehga5 = in.readInt();
					}
					if (server.equals("hga6")) {
						onlinehga6 = in.readInt();
					}
				}catch (Exception e) {
				}
			}
		}
	}
		

		public void CommandsHologramas() {
			try {
				final Field commandField = Bukkit.getServer().getClass().getDeclaredField(commandMapFieldClass);
				commandField.setAccessible(true);
				final CommandMap commandMap = (CommandMap) commandField.get(Bukkit.getServer());

				commandMap.register("pvp", new SetHl("sethl", "Comando para setar o hl", "/sethl",
						Arrays.asList("set", "hlset")));
				
				commandMap.register("pvp",
						new HologramaCommand("hls", "Comando para ver os hologramas", "/hls", Arrays.asList("hls", "hlslist")));


			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}