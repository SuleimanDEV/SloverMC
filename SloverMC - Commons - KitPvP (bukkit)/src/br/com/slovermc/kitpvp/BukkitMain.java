package br.com.slovermc.kitpvp;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.kitpvp.account.AccountFile;
import br.com.slovermc.kitpvp.account.DeadsAPI;
import br.com.slovermc.kitpvp.account.KillsAPI;
import br.com.slovermc.kitpvp.account.KsAPI;
import br.com.slovermc.kitpvp.api.ConfigAPI;
import br.com.slovermc.kitpvp.api.TabAPI;
import br.com.slovermc.kitpvp.api.XP;
import br.com.slovermc.kitpvp.combat.CombatSystem;
import br.com.slovermc.kitpvp.command.essentials.BroadcastCommand;
import br.com.slovermc.kitpvp.command.essentials.BuildCommand;
import br.com.slovermc.kitpvp.command.essentials.CcCommand;
import br.com.slovermc.kitpvp.command.essentials.ClearDropsCommand;
import br.com.slovermc.kitpvp.command.essentials.ClearGrifCommand;
import br.com.slovermc.kitpvp.command.essentials.DanoCommand;
import br.com.slovermc.kitpvp.command.essentials.DoubleXpCommand;
import br.com.slovermc.kitpvp.command.essentials.EventoCommand;
import br.com.slovermc.kitpvp.command.essentials.FlyCommand;
import br.com.slovermc.kitpvp.command.essentials.GameModeCommand;
import br.com.slovermc.kitpvp.command.essentials.KitCommand;
import br.com.slovermc.kitpvp.command.essentials.LobbyCommand;
import br.com.slovermc.kitpvp.command.essentials.PvPCommand;
import br.com.slovermc.kitpvp.command.essentials.ScoreboardCommand;
import br.com.slovermc.kitpvp.command.essentials.SetSpawnProtectionCommand;
import br.com.slovermc.kitpvp.command.essentials.SetWarpCommand;
import br.com.slovermc.kitpvp.command.essentials.SpawnCommand;
import br.com.slovermc.kitpvp.command.essentials.TeleportCommand;
import br.com.slovermc.kitpvp.command.essentials.WarpCommand;
import br.com.slovermc.kitpvp.command.essentials.cAddxpCMD;
import br.com.slovermc.kitpvp.command.essentials.cSsCMD;
import br.com.slovermc.kitpvp.command.essentials.cAdminCMD;
import br.com.slovermc.kitpvp.command.essentials.cChatCMD;
import br.com.slovermc.kitpvp.command.essentials.cDoubleXpCommand;
import br.com.slovermc.kitpvp.command.essentials.cLigaCMD;
import br.com.slovermc.kitpvp.command.essentials.cRemoveXpCMD;
import br.com.slovermc.kitpvp.command.essentials.cTellCMD;
import br.com.slovermc.kitpvp.command.essentials.cXpCMD;
import br.com.slovermc.kitpvp.coords.LocationsFile;
import br.com.slovermc.kitpvp.deathevents.PlayerDeathEvents;
import br.com.slovermc.kitpvp.hologram.HologramEvent;
import br.com.slovermc.kitpvp.kits.Ajnin;
import br.com.slovermc.kitpvp.kits.Anchor;
import br.com.slovermc.kitpvp.kits.Boxer;
import br.com.slovermc.kitpvp.kits.Fireman;
import br.com.slovermc.kitpvp.kits.Fisherman;
import br.com.slovermc.kitpvp.kits.Gladiator;
import br.com.slovermc.kitpvp.kits.Hulk;
import br.com.slovermc.kitpvp.kits.Kangaroo;
import br.com.slovermc.kitpvp.kits.Magma;
import br.com.slovermc.kitpvp.kits.Minato;
import br.com.slovermc.kitpvp.kits.Monk;
import br.com.slovermc.kitpvp.kits.Ninja;
import br.com.slovermc.kitpvp.kits.Stomper;
import br.com.slovermc.kitpvp.kits.Switcher;
import br.com.slovermc.kitpvp.kits.Thor;
import br.com.slovermc.kitpvp.kits.Timelord;
import br.com.slovermc.kitpvp.listener.BattlePlayerJoinServerEvents;
import br.com.slovermc.kitpvp.listener.BattlePlayerQuitEvent;
import br.com.slovermc.kitpvp.listener.CompassSystemListener;
import br.com.slovermc.kitpvp.listener.DamageConfiguration;
import br.com.slovermc.kitpvp.listener.EAdmin;
import br.com.slovermc.kitpvp.listener.EntityDamageByEntity;
import br.com.slovermc.kitpvp.listener.InteractFramesEvent;
import br.com.slovermc.kitpvp.listener.MainListeners;
import br.com.slovermc.kitpvp.mysql.MySQL_Connection;
import br.com.slovermc.kitpvp.mysql.MySQL_Settings;
import br.com.slovermc.kitpvp.mysql.RankList;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.topks.TopKillStreakAPI;
import br.com.slovermc.kitpvp.warps.Capiroto.CapirotoItens;
import br.com.slovermc.kitpvp.warps.Challenge.ChallengeWarpListener;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.OneVsOne.Inventory1v1Custom;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.MenusWarpSpawn;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnKitsMenu;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.Stats;

public class BukkitMain extends JavaPlugin {

	private static Plugin kitpvp;
	private static BukkitMain instance;
	
	public static BukkitMain getInstance() {
		return instance;
	}
	public static BukkitMain pvp;

	public static Plugin getPlugin() {
		return kitpvp;
	}

	public static final MySQL_Connection connect = new MySQL_Connection();
	public static final MySQL_Settings mysql = new MySQL_Settings();
	public static final String commandMapFieldClass = "commandMap";

	public static final String receivedSpawnProtection = "§8§lPROTEÇAO§f Você recebeu sua proteção de spawn.";
	public static final String lostedSpawnProtection = "§8§lPROTEÇAO§f Você perdeu sua proteção de spawn.";

	public static final String getReceivedSpawnProtectionMessage() {
		return receivedSpawnProtection;
	}

	public static final String getLostedSpawnProtectionMessage() {
		return lostedSpawnProtection;
	}

	public static final String BungeeChannel = "BungeeCord";

	public static String version;

	public final PluginManager battleplugin = Bukkit.getPluginManager();

	@SuppressWarnings("deprecation")
	public final void onEnable() {

		kitpvp = this;
		instance = this;
		saveDefaultConfig();
		new ConfigAPI(kitpvp);
		KsAPI.createFile();
		connect.ConnectMySQL();
		mysql.newTable();
		KillsAPI.createFile();
		DeadsAPI.createFile();
		XP.createFile();
		AccountFile.createAccountFile(kitpvp);
		LocationsFile.createLocationsFile(kitpvp);

		getServer().getMessenger().registerOutgoingPluginChannel(this, BungeeChannel);

		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		registerEvents();
		registerCommands();
		//BukkitTasks.updateAllAnimations();
		Score.updateAllScoreboards();
		Magma.updateMagmaOnWater();
		TopKillStreakAPI.updateTopKs();
		CapirotoItens.updatePotionEffects();
		getCommand("gamemode").setExecutor(new GameModeCommand());
		getCommand("gm").setExecutor(new GameModeCommand());
		getCommand("tell").setExecutor(new cTellCMD());
		getCommand("liga").setExecutor(new cLigaCMD());
		getCommand("ligas").setExecutor(new cLigaCMD());
		getCommand("account").setExecutor(new cXpCMD());
		getCommand("addxp").setExecutor(new cAddxpCMD());
		getCommand("removexp").setExecutor(new cRemoveXpCMD());
		getCommand("admin").setExecutor(new cAdminCMD());
		getCommand("xp").setExecutor(new cXpCMD());
		getCommand("account").setExecutor(new cXpCMD());
		getCommand("chat").setExecutor(new cChatCMD());
		getCommand("ss").setExecutor(new cSsCMD());
		ItemStack recraft = new ItemStack(Material.MUSHROOM_SOUP, 1);

		ShapelessRecipe cocoabean = new ShapelessRecipe(recraft);
		cocoabean.addIngredient(1, Material.getMaterial(351), (byte) 3);
		cocoabean.addIngredient(1, Material.BOWL);
		Bukkit.addRecipe(cocoabean);
		onTab();

		Bukkit.getConsoleSender().sendMessage("§aPlugin iniciado com sucesso, acesso permitido.");
	}

	public void onTab() {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					TabAPI.sendTabTitle(p,
							"\n  §6§lMatch§f§lMC §eKitPvP\n          §fAtualmente temos §a" + Bukkit.getOnlinePlayers().length + " §fjogador(es) no §aKitPvP\n",
							"\n§bNick:§f " + p.getName() + " §1- §bLiga: " + RankList.getScoreRank(p)
									+ "  \n §bMais informações em §f@RedeMatchMC_\n");

				}
			}
		}.runTaskTimer(getPlugin(), 0, 9L);
	}

	@SuppressWarnings("deprecation")
	public final void onDisable() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer("§c§lREINICIANDO\n   §fO servidor está reiniciando\n§fvolte dentre alguns minutos!");
		}
	}

	public void registerEvents() {
		battleplugin.registerEvents(new BattlePlayerJoinServerEvents(), this);
		battleplugin.registerEvents(new BattlePlayerQuitEvent(), this);
		battleplugin.registerEvents(new DamageConfiguration(), this);
		battleplugin.registerEvents(new MainListeners(), this);
		battleplugin.registerEvents(new EntityDamageByEntity(), this);
		battleplugin.registerEvents(new CompassSystemListener(), this);
		battleplugin.registerEvents(new HologramEvent(), this);
		battleplugin.registerEvents(new PlayerDeathEvents(), this);
		battleplugin.registerEvents(new CombatSystem(), this);
		battleplugin.registerEvents(new InteractFramesEvent(), this);
		battleplugin.registerEvents(new Stats(), this);
		battleplugin.registerEvents(new cChatCMD(), this);
		battleplugin.registerEvents(new EAdmin(), this);
		registerKits();
		registerWarpEvents();
	}

	public void registerKits() {
		battleplugin.registerEvents(new Ajnin(), this);
		battleplugin.registerEvents(new Anchor(), this);
		battleplugin.registerEvents(new Ninja(), this);
		battleplugin.registerEvents(new Boxer(), this);
		battleplugin.registerEvents(new Fireman(), this);
		battleplugin.registerEvents(new Fisherman(), this);
		battleplugin.registerEvents(new Hulk(), this);
		battleplugin.registerEvents(new Kangaroo(), this);
		battleplugin.registerEvents(new Magma(), this);
		battleplugin.registerEvents(new Minato(), this);
		battleplugin.registerEvents(new Monk(), this);
		battleplugin.registerEvents(new Stomper(), this);
		battleplugin.registerEvents(new Gladiator(), this);
		battleplugin.registerEvents(new Switcher(), this);
		battleplugin.registerEvents(new Thor(), this);
		battleplugin.registerEvents(new Timelord(), this);
	}

	public void registerWarpEvents() {
		battleplugin.registerEvents(new SpawnKitsMenu(), this);
		battleplugin.registerEvents(new FpsWarpListener(), this);
		battleplugin.registerEvents(new MenusWarpSpawn(), this);
		battleplugin.registerEvents(new SpawnWarpListener(), this);
		battleplugin.registerEvents(new X1WarpListener(), this);
		battleplugin.registerEvents(new Inventory1v1Custom(), this);
		battleplugin.registerEvents(new ChallengeWarpListener(), this);
	}

	public void registerCommands() {
		try {
			final Field commandField = Bukkit.getServer().getClass().getDeclaredField(commandMapFieldClass);
			commandField.setAccessible(true);
			final CommandMap commandMap = (CommandMap) commandField.get(Bukkit.getServer());

			commandMap.register("pvp", new SetWarpCommand("setwarp", "Comando para setar warps", "/setwarp",
					Arrays.asList("set", "warpset")));

			commandMap.register("pvp",
					new WarpCommand("warp", "Comando para escolher warp", "/warp", Arrays.asList("warps", "warplist")));

			commandMap.register("pvp", new TeleportCommand("tp", "Comando para se teleportar", "/tp",
					Arrays.asList("teleporte", "teleportar", "teleport")));

			commandMap.register("pvp", new BroadcastCommand("broadcast", "Comando para dar avisos globais",
					"/broadcast", Arrays.asList("bc", "aviso", "anuncio", "anunciar")));

			commandMap.register("pvp", new BuildCommand("build", "Comando para ativar/desativar modo build", "/build",
					Arrays.asList("builder")));

			commandMap.register("pvp",
					new CcCommand("cc", "Comando para limpar chat", "/cc", Arrays.asList("chatclear")));

			commandMap.register("pvp",
					new SpawnCommand("spawn", "Comando para retornar ao spawn", "/spawn", Arrays.asList("warpspawn")));

			commandMap.register("pvp", new ClearDropsCommand("cleardrops", "Comando para limpar drops", "/cleardrops",
					Arrays.asList("limpardrops")));

			commandMap.register("pvp", new FlyCommand("fly", "Comando para ativar/desativar modo voo", "/fly",
					Arrays.asList("voar", "voo", "flymode")));

			commandMap.register("pvp", new ScoreboardCommand("scoreboard", "Comando para ativar/desativar scoreboard",
					"/scoreboard", Arrays.asList("score")));

			commandMap.register("pvp", new ClearGrifCommand("cleargrif", "Comando para limpar agua/lava", "/cleargrif",
					Arrays.asList("grifclear", "limpargrif", "tirarlava", "tiraragua")));

			commandMap.register("pvp",
					new DoubleXpCommand("doublexp", "Comando para usar doublexp", "/doublexp", Arrays.asList("dxp")));

			commandMap.register("pvp", new cDoubleXpCommand("cdoublexp", "Comando para dar/remover doublexp",
					"/cdoublexp", Arrays.asList("givedoublexp", "doublexpadd", "doublexpremove")));

			commandMap.register("pvp",
					new KitCommand("kit", "Comando para selecionar kits", "/kit", Arrays.asList("kits")));

			commandMap.register("pvp",
					new EventoCommand("evento", "Comando para controlar evento", "/evento", Arrays.asList("event")));

			commandMap.register("pvp",
					new SetSpawnProtectionCommand("setspawnprtection",
							"Comando para setar distancia da proteçao de spawn", "/setspawnprotection",
							Arrays.asList("protection", "setspawnprotectiondistance", "spawnprotection")));

			commandMap.register("pvp", new LobbyCommand("lobby", "Comando para conectar ao Lobby", "/lobby",
					Arrays.asList("hub", "sair")));
			
			commandMap.register("pvp", new PvPCommand("pvp", "Comando para ativar/desativar pvp", "/pvp",
					Arrays.asList("setpvp")));
			
			commandMap.register("pvp", new DanoCommand("dano", "Comando para ativar/desativar dano", "/dano",
					Arrays.asList("damage")));

		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static final boolean NotInGame(final CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			return false;
		}
	}
}