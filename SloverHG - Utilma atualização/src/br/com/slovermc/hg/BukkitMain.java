package br.com.slovermc.hg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import br.com.slovermc.hg.api.ConfigAPI;
import br.com.slovermc.hg.api.MysqlAPI;
import br.com.slovermc.hg.commands.cAddxpCMD;
import br.com.slovermc.hg.commands.cChatCMD;
import br.com.slovermc.hg.commands.cGmCMD;
import br.com.slovermc.hg.commands.cLigaCMD;
import br.com.slovermc.hg.commands.cLimparchatCMD;
import br.com.slovermc.hg.commands.cLobbyCMD;
import br.com.slovermc.hg.commands.cRemoveXpCMD;
import br.com.slovermc.hg.commands.cSsCMD;
import br.com.slovermc.hg.commands.cTellCMD;
import br.com.slovermc.hg.commands.cXpCMD;
import br.com.slovermc.hg.commands.cAdminCMD;
import br.com.slovermc.hg.commands.cArenaCMD;
import br.com.slovermc.hg.commands.cBuildCMD;
import br.com.slovermc.hg.commands.cClearDropsCMD;
import br.com.slovermc.hg.commands.cDanoCMD;
import br.com.slovermc.hg.commands.cDesistirCMD;
import br.com.slovermc.hg.commands.cEventoCMD;
import br.com.slovermc.hg.commands.cFeastCMD;
import br.com.slovermc.hg.commands.cFlyCMD;
import br.com.slovermc.hg.commands.cForceFeastCMD;
import br.com.slovermc.hg.commands.cForceKitCMD;
import br.com.slovermc.hg.commands.cGiveItemCMD;
import br.com.slovermc.hg.commands.cInfoCMD;
import br.com.slovermc.hg.commands.cInvSeeCMD;
import br.com.slovermc.hg.commands.cIrCMD;
import br.com.slovermc.hg.commands.cKitCMD;
import br.com.slovermc.hg.commands.cKit2CMD;
import br.com.slovermc.hg.commands.cKitReadyCMD;
import br.com.slovermc.hg.commands.cPvPCMD;
import br.com.slovermc.hg.commands.cSkitCMD;
import br.com.slovermc.hg.commands.cEspectarCMD;
import br.com.slovermc.hg.commands.cIniciarCMD;
import br.com.slovermc.hg.commands.cTempoCMD;
import br.com.slovermc.hg.commands.cTogglekitCMD;
import br.com.slovermc.hg.commands.cTpCMD;
import br.com.slovermc.hg.eventos.BordaListeners;
import br.com.slovermc.hg.eventos.NoDropKitItems;
import br.com.slovermc.hg.eventos.SpectatorListeners;
import br.com.slovermc.hg.eventos.Tablist;
import br.com.slovermc.hg.habilidades.AchillesAndAnchorKit;
import br.com.slovermc.hg.habilidades.ArcherAndBoxerKit;
import br.com.slovermc.hg.habilidades.CultivatorAndCopyCatKit;
import br.com.slovermc.hg.habilidades.DemomanAndForgerKit;
import br.com.slovermc.hg.habilidades.DeshfireAndSonicKit;
import br.com.slovermc.hg.habilidades.FiremanAndFisherman;
import br.com.slovermc.hg.habilidades.FlashAndBlinkKit;
import br.com.slovermc.hg.habilidades.GladiatorKit;
import br.com.slovermc.hg.habilidades.HulkAndMonkKit;
import br.com.slovermc.hg.habilidades.KangarooKit;
import br.com.slovermc.hg.habilidades.LauncherAndGrapplerKit;
import br.com.slovermc.hg.habilidades.LumberjackAndEndermageKit;
import br.com.slovermc.hg.habilidades.MadmanAndNinjaKit;
import br.com.slovermc.hg.habilidades.MagmaAndMinerKit;
import br.com.slovermc.hg.habilidades.PhantomAndStomperKit;
import br.com.slovermc.hg.habilidades.PoseidonAndVikingKit;
import br.com.slovermc.hg.habilidades.SpecialistAndSnailKit;
import br.com.slovermc.hg.habilidades.SurpriseAndWormKit;
import br.com.slovermc.hg.habilidades.TimelordAndReaperKit;
import br.com.slovermc.hg.habilidades.VacuumAndTankKit;
import br.com.slovermc.hg.habilidades.ViperAndThorKit;
import br.com.slovermc.hg.habilidades.WeakhandAndAjninKit;
import br.com.slovermc.hg.inventarios.InventoryKit;
import br.com.slovermc.hg.inventarios.InventoryKit2;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.mysql.MySQL_Connection;
import br.com.slovermc.hg.mysql.MySQL_Settings;
import br.com.slovermc.hg.scoreboard.Score;
import br.com.slovermc.hg.tempos.EndListeners;
import br.com.slovermc.hg.tempos.GameListeners;
import br.com.slovermc.hg.tempos.InvincibilityListeners;
import br.com.slovermc.hg.tempos.StartingListeners;
import br.com.slovermc.hg.utils.SecondsEvent;

public class BukkitMain extends JavaPlugin implements PluginMessageListener {

	private static BukkitMain main;
	public static String prefix;
	public static StateEnum state;
	public HashMap<Player, String> tag1 = new HashMap<>();
	public ArrayList<Player> Quit = new ArrayList<>();
	private FileConfiguration config;
	public boolean chat = true;
	public boolean kit = true;
	public boolean kit2 = true;
	public boolean dano = true;
	public boolean build = true;
	public boolean evento = false;
	public MysqlAPI mysql = null;
	public boolean buycraft = false;
	public boolean manutencao = false;
	private boolean duoKit;
	public int IP = 1;
	public Plugin plugin;

	public static final MySQL_Settings mysql1 = new MySQL_Settings();
	public static final MySQL_Connection connect = new MySQL_Connection();

	public static final PluginManager pm = Bukkit.getPluginManager();
	public static BukkitMain instance;
	
	public static BukkitMain getInstance() {
		return instance;
	}

	public void onLoad() {
		Manager.getInstance().deleteFile(new File("world"));
	}

	public static ArrayList<Player> hasScore = new ArrayList<>();

	public void onEnable() {
		main = this;
		plugin = this;
		instance = this;
		connect.ConnectMySQL();
		mysql1.newTable();
		saveDefaultConfig();
		config = getConfig();
		duoKit = true;
		ConfigAPI.getInstance().setup();
		for (Entity i : Bukkit.getWorlds().get(0).getEntities()) {
			if (i instanceof Item) {
				i.remove();
			}
		}
		//Score.updateAllScoreboards();
		Score.updateAllScoreboards();
		//BukkitTasks.updateAllAnimations();
		Manager.getInstance().setupConfigs();
		Manager.getInstance().startingTime();
		BukkitMain.getPlugin().registerCmds();
		BukkitMain.getPlugin().registerEvents();
		Manager.getInstance().initialiseRecipes();
		Manager.getInstance().initialiseAutobroadcast();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		for (Player all : Bukkit.getOnlinePlayers()) { 
			cLobbyCMD.sendServer(all, "lobby");
		}
	}

	public static BukkitMain getPlugin() {
		return main;
	}

	public boolean duoKit() {
		return duoKit;
	}

	public FileConfiguration config() {
		return config;
	}

	private void registerCmds() {
		getCommand("forcefeast").setExecutor(new cForceFeastCMD());
		getCommand("cleardrops").setExecutor(new cClearDropsCMD());
		getCommand("forcekitall").setExecutor(new cForceKitCMD());
		getCommand("giveitemkit").setExecutor(new cGiveItemCMD());
		getCommand("togglekit").setExecutor(new cTogglekitCMD());
		getCommand("createarena").setExecutor(new cArenaCMD());
		getCommand("forcekit").setExecutor(new cForceKitCMD());
		getCommand("desistir").setExecutor(new cDesistirCMD());
		getCommand("kitready").setExecutor(new cKitReadyCMD());
		getCommand("iniciar").setExecutor(new cIniciarCMD());
		getCommand("invsee").setExecutor(new cInvSeeCMD());
		getCommand("evento").setExecutor(new cEventoCMD());
		getCommand("build").setExecutor(new cBuildCMD());
		getCommand("admin").setExecutor(new cAdminCMD());
		getCommand("feast").setExecutor(new cFeastCMD());
		getCommand("specs").setExecutor(new cEspectarCMD());
		getCommand("tempo").setExecutor(new cTempoCMD());
		getCommand("dano").setExecutor(new cDanoCMD());
		getCommand("info").setExecutor(new cInfoCMD());
		getCommand("skit").setExecutor(new cSkitCMD());
		getCommand("tphere").setExecutor(new cTpCMD());
		getCommand("tpall").setExecutor(new cTpCMD());
		getCommand("kit").setExecutor(new cKitCMD());
		getCommand("kit2").setExecutor(new cKit2CMD());
		getCommand("pvp").setExecutor(new cPvPCMD());
		getCommand("fly").setExecutor(new cFlyCMD());
		getCommand("ir").setExecutor(new cIrCMD());
		getCommand("tp").setExecutor(new cTpCMD());
		getCommand("tell").setExecutor(new cTellCMD());
		getCommand("r").setExecutor(new cTellCMD());
		getCommand("addxp").setExecutor(new cAddxpCMD());
		getCommand("xp").setExecutor(new cXpCMD());
		getCommand("lobby").setExecutor(new cLobbyCMD());
		getCommand("ss").setExecutor(new cSsCMD());
		getCommand("chat").setExecutor(new cChatCMD());
		getCommand("screenshare").setExecutor(new cSsCMD());
		getCommand("removexp").setExecutor(new cRemoveXpCMD());
		getCommand("liga").setExecutor(new cLigaCMD());
		getCommand("ligas").setExecutor(new cLigaCMD());
		getCommand("cc").setExecutor(new cLimparchatCMD());
		getCommand("gm").setExecutor(new cGmCMD());
		getCommand("gamemode").setExecutor(new cGmCMD());
	}

	public void registerHabilitys() {
		Bukkit.getPluginManager().registerEvents(new LumberjackAndEndermageKit(), this);
		Bukkit.getPluginManager().registerEvents(new KangarooKit(), this);
		Bukkit.getPluginManager().registerEvents(new KangarooKit(), this);
		Bukkit.getPluginManager().registerEvents(new CultivatorAndCopyCatKit(), this);
		Bukkit.getPluginManager().registerEvents(new CultivatorAndCopyCatKit(), this);
		Bukkit.getPluginManager().registerEvents(new LauncherAndGrapplerKit(), this);
		Bukkit.getPluginManager().registerEvents(new SpecialistAndSnailKit(), this);
		Bukkit.getPluginManager().registerEvents(new PoseidonAndVikingKit(), this);
		Bukkit.getPluginManager().registerEvents(new AchillesAndAnchorKit(), this);
		Bukkit.getPluginManager().registerEvents(new TimelordAndReaperKit(), this);
		Bukkit.getPluginManager().registerEvents(new PhantomAndStomperKit(), this);
		Bukkit.getPluginManager().registerEvents(new DeshfireAndSonicKit(), this);
		Bukkit.getPluginManager().registerEvents(new WeakhandAndAjninKit(), this);
		Bukkit.getPluginManager().registerEvents(new FiremanAndFisherman(), this);
		Bukkit.getPluginManager().registerEvents(new DemomanAndForgerKit(), this);
		Bukkit.getPluginManager().registerEvents(new SurpriseAndWormKit(), this);
		Bukkit.getPluginManager().registerEvents(new MadmanAndNinjaKit(), this);
		Bukkit.getPluginManager().registerEvents(new ArcherAndBoxerKit(), this);
		Bukkit.getPluginManager().registerEvents(new FlashAndBlinkKit(), this);
		Bukkit.getPluginManager().registerEvents(new VacuumAndTankKit(), this);
		Bukkit.getPluginManager().registerEvents(new MagmaAndMinerKit(), this);
		Bukkit.getPluginManager().registerEvents(new ViperAndThorKit(), this);
		Bukkit.getPluginManager().registerEvents(new HulkAndMonkKit(), this);
		Bukkit.getPluginManager().registerEvents(new GladiatorKit(), this);

		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getPluginManager().callEvent(new SecondsEvent());
			}
		}.runTaskTimer(this, 0, 20);
	}

	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new InvincibilityListeners(), this);
		Bukkit.getPluginManager().registerEvents(new SpectatorListeners(), this);
		Bukkit.getPluginManager().registerEvents(new StartingListeners(), this);
		Bukkit.getPluginManager().registerEvents(new BordaListeners(), this);
		Bukkit.getPluginManager().registerEvents(new NoDropKitItems(), this);
		Bukkit.getPluginManager().registerEvents(new GameListeners(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryKit(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryKit2(), this);
		Bukkit.getPluginManager().registerEvents(new EndListeners(), this);
		Bukkit.getPluginManager().registerEvents(new cBuildCMD(), this);
		Bukkit.getPluginManager().registerEvents(new cAdminCMD(), this);

		
		
		Bukkit.getPluginManager().registerEvents(new Tablist(), this);
		Bukkit.getPluginManager().registerEvents(new cChatCMD(), this);
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
	}
}
