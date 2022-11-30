package br.com.slovermc.gladiator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import br.com.slovermc.gladiator.api.API;
import br.com.slovermc.gladiator.api.DeathsAPI;
import br.com.slovermc.gladiator.api.GladiatorAPI;
import br.com.slovermc.gladiator.api.WinsAPI;
import br.com.slovermc.gladiator.api.WsAPI;
import br.com.slovermc.gladiator.api.XpAPI;
import br.com.slovermc.gladiator.commands.cAddxpCMD;
import br.com.slovermc.gladiator.commands.cAdminCMD;
import br.com.slovermc.gladiator.commands.cChatCMD;
import br.com.slovermc.gladiator.commands.cGmCMD;
import br.com.slovermc.gladiator.commands.cLigaCMD;
import br.com.slovermc.gladiator.commands.cLimparchatCMD;
import br.com.slovermc.gladiator.commands.cLobbyCMD;
import br.com.slovermc.gladiator.commands.cRemoveXpCMD;
import br.com.slovermc.gladiator.commands.cSetSpawnCMD;
import br.com.slovermc.gladiator.commands.cSsCMD;
import br.com.slovermc.gladiator.commands.cTellCMD;
import br.com.slovermc.gladiator.commands.cTpCMD;
import br.com.slovermc.gladiator.commands.cXpCMD;
import br.com.slovermc.gladiator.events.EventAdmin;
import br.com.slovermc.gladiator.events.Tablist;
import br.com.slovermc.gladiator.events.PlayerEvent;
import br.com.slovermc.gladiator.events.ListenerItens;
import br.com.slovermc.gladiator.hologramas.SetHl;
import br.com.slovermc.gladiator.hologramas.FileHl;
import br.com.slovermc.gladiator.hologramas.HologramEvent;
import br.com.slovermc.gladiator.hologramas.HologramaCommand;
import br.com.slovermc.gladiator.mysql.MySQL_Connection;
import br.com.slovermc.gladiator.mysql.MySQL_Settings;
import br.com.slovermc.gladiator.score.Score;
import br.com.slovermc.gladiator.timer.ClearArena;
import br.com.slovermc.gladiator.utils.Messages;
import br.com.slovermc.gladiator.utils.UtilsManager;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BukkitMain extends JavaPlugin implements PluginMessageListener {
	
	public YamlConfiguration stats;
	public File stats1;
	public static Plugin plugin;
	public static ItemStack FerramentaNormal;
	public static String semp = "§c§lERRO §fVocê não tem permissão para executar este comando.";
	public static ArrayList<Player> Na1v1 = new ArrayList();
	public static ArrayList<Player> EmPvP = new ArrayList();
	private static BukkitMain instance;
	protected static UtilsManager utilsManager;
	public static final String commandMapFieldClass = "commandMap";
	public static final MySQL_Connection connect = new MySQL_Connection();
	public static final MySQL_Settings mysql = new MySQL_Settings();

	public static Plugin getPlugin() {
		return plugin;
	}

	public static BukkitMain getInstance() {
		return instance;
	}

	public static UtilsManager getUtilsManager() {
		return utilsManager;
	}

	@SuppressWarnings("static-access")
	private void setInstance(BukkitMain instance) {
		this.instance = instance;
	}

	public void onEnable() {
		setInstance(this);
		plugin = this;
		saveDefaultConfig();
		connect.ConnectMySQL();
		mysql.newTable();
		Score.updateAllScoreboards();
		XpAPI.createFile();
		WinsAPI.createFile();
		DeathsAPI.createFile();
		WsAPI.createFile();
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("  §a§lGLADIATOR §fPlugin iniciado.");
		Bukkit.getConsoleSender().sendMessage("  §a§lSERVER: §6SloverMC");
		Bukkit.getConsoleSender().sendMessage("");
		createConfig();
		RegisterCommands();
		RegisterEvents();
		FileHl.createLocationsFile(plugin);
		Sopas();
		configs();
		CommandsHologramas();
	}


	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("  §a§lGLADIATOR §fPlugin desabilitado.");
		Bukkit.getConsoleSender().sendMessage("  §a§lSERVER: §6SloverMC");
		Bukkit.getConsoleSender().sendMessage("");
	}

	public void createConfig() {

		File stats = new File(getDataFolder(), "config.yml");

		if (!stats.exists()) {
			saveResource("config.yml", false);
			stats1 = new File(getDataFolder(), "config.yml");
			this.stats = YamlConfiguration.loadConfiguration(stats1);

		}
	}

	@SuppressWarnings("deprecation")
	public final void Sopas() {

		ItemStack icone = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName("§fSopa");
		ArrayList<String> lore = new ArrayList<>();

		lore.add(Messages.getPrefix());
		iconem.setLore(lore);
		icone.setItemMeta(iconem);

		ShapelessRecipe cactu = new ShapelessRecipe(icone);
		API.I(Material.MUSHROOM_SOUP, "§aSopa de Cactus", 0, 1);
		cactu.addIngredient(1, Material.CACTUS);
		cactu.addIngredient(1, Material.BOWL);
		getServer().addRecipe(cactu);

		ShapelessRecipe cacau = new ShapelessRecipe(icone);
		API.I(Material.MUSHROOM_SOUP, "§8Sopa de Cacau", 0, 1);
		cacau.addIngredient(1, Material.INK_SACK, 3);
		cacau.addIngredient(1, Material.BOWL);
		getServer().addRecipe(cacau);
	}

	public final static void IniciarGladiator(Player player1, Player player2) {
		inventory(player1);
		inventory(player2);
		GladiatorAPI.emluta.put(player1.getName(), player2.getName());
		GladiatorAPI.emluta.put(player2.getName(), player1.getName());
		GladiatorAPI.spawnArena(player1, player2, new Location(player1.getWorld(), 0, 0, 0), Material.GLASS);
	}

	@SuppressWarnings("deprecation")
	public final static void inventory(Player p) {
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		p.closeInventory();
		p.setGameMode(GameMode.SURVIVAL);
		ItemStack icone = new ItemStack(351, 64, (short) 3);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName("§fRecraft");
		ArrayList<String> lore = new ArrayList<>();
		lore.add(null);
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		ItemStack a = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§bEspada");
		am.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		a.setItemMeta(am);
		p.getInventory().setItem(0, a);
		API.item(p.getInventory(), p, Material.COBBLE_WALL, 64, 0, "§7Castelo", null, 1);
		API.item(p.getInventory(), p, Material.LAVA_BUCKET, 1, 0, "§6Lava", null, 2);
		API.item(p.getInventory(), p, Material.WATER_BUCKET, 1, 0, "§9Agua", null, 3);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 4);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 5);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 6);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 7);
		API.item(p.getInventory(), p, Material.WOOD, 64, 0, "§cMadeira", null, 8);
		API.item(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura", null, 9);
		API.item(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura", null, 10);
		API.item(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", null, 11);
		API.item(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", null, 12);
		API.item(p.getInventory(), p, Material.BOWL, 64, 0, "§fRecraft", null, 13);
		p.getInventory().setItem(14, icone);
		p.getInventory().setItem(15, icone);
		p.getInventory().setItem(16, icone);
		API.item(p.getInventory(), p, Material.STONE_AXE, 1, 0, "§cMachado", null, 17);
		API.item(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura", null, 18);
		API.item(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura", null, 19);
		API.item(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", null, 20);
		API.item(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", null, 21);
		API.item(p.getInventory(), p, Material.BOWL, 64, 0, "§fRecraft", null, 22);
		p.getInventory().setItem(23, icone);
		p.getInventory().setItem(24, icone);
		p.getInventory().setItem(25, icone);
		API.item(p.getInventory(), p, Material.STONE_PICKAXE, 1, 0, "§cPicareta", null, 26);
		API.item(p.getInventory(), p, Material.LAVA_BUCKET, 1, 0, "§6Lava", null, 27);
		API.item(p.getInventory(), p, Material.LAVA_BUCKET, 1, 0, "§6Lava", null, 28);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 29);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 30);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 31);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 32);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 33);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 34);
		API.item(p.getInventory(), p, Material.MUSHROOM_SOUP, 1, 0, "§fSopa", null, 35);
		ItemStack h = API.item2(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura", null);
		ItemStack c = API.item2(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura", null);
		ItemStack l = API.item2(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", null);
		ItemStack b = API.item2(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", null);
		p.getInventory().setHelmet(h);
		p.getInventory().setChestplate(c);
		p.getInventory().setLeggings(l);
		p.getInventory().setBoots(b);
		p.updateInventory();
	}

	protected final void SaveConfig(InputStream i, File config) {
		try {
			OutputStream out = new FileOutputStream(config);
			byte[] buf = new byte[710];
			int len;
			while ((len = i.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			i.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public final void RegisterEvents() {
		PluginManager BukkitEvents = Bukkit.getPluginManager();
		BukkitEvents.registerEvents(new PlayerEvent(), this);
		BukkitEvents.registerEvents(new ListenerItens(), this);
		BukkitEvents.registerEvents(new GladiatorAPI(), this);
		BukkitEvents.registerEvents(new Tablist(), this);
		BukkitEvents.registerEvents(new EventAdmin(), this);
		BukkitEvents.registerEvents(new ClearArena(), this);
		BukkitEvents.registerEvents(new cChatCMD(), this);
		BukkitEvents.registerEvents(new Tablist(), this);
		BukkitEvents.registerEvents(new HologramEvent(), this);
	}

	public final void RegisterCommands() {
		getCommand("setspawn").setExecutor(new cSetSpawnCMD());
		getCommand("admin").setExecutor(new cAdminCMD());
		getCommand("cc").setExecutor(new cLimparchatCMD());
		getCommand("gm").setExecutor(new cGmCMD());
		getCommand("tp").setExecutor(new cTpCMD());
		getCommand("removexp").setExecutor(new cRemoveXpCMD());
		getCommand("liga").setExecutor(new cLigaCMD());
		getCommand("ligas").setExecutor(new cLigaCMD());
		getCommand("gamemode").setExecutor(new cGmCMD());
		getCommand("tell").setExecutor(new cTellCMD());
		getCommand("r").setExecutor(new cTellCMD());
		getCommand("addxp").setExecutor(new cAddxpCMD());
		getCommand("xp").setExecutor(new cXpCMD());
		getCommand("lobby").setExecutor(new cLobbyCMD());
		getCommand("ss").setExecutor(new cSsCMD());
		getCommand("chat").setExecutor(new cChatCMD());
		getCommand("screenshare").setExecutor(new cSsCMD());
	}

	void configs() {
		File f = new File(BukkitMain.getPlugin().getDataFolder(), "delete.schematic");
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			SaveConfig(BukkitMain.getPlugin().getResource("delete.schematic"), f);
		}
	}
	public static boolean getVersion(Player player) {
		((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();
		return false;
	}

	@SuppressWarnings("deprecation")
	public final static void getitemDefault(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		ItemStack b = new ItemStack(Material.IRON_FENCE);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("§eDesafiar Gladiator §7(Clique)");
		b.setItemMeta(bm);
		ItemStack icone = new ItemStack(351, 1, (short) 8);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName("§eProcurar Partida §7(Clique)");
		icone.setItemMeta(iconem);
		player.getInventory().setItem(5, icone);
		player.getInventory().setItem(3, b);

	}
	
	public static final boolean NotInGame(final CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			return false;
		}
	}
	
	public void CommandsHologramas() {
		try {
			final Field commandField = Bukkit.getServer().getClass().getDeclaredField(commandMapFieldClass);
			commandField.setAccessible(true);
			final CommandMap commandMap = (CommandMap) commandField.get(Bukkit.getServer());

			commandMap.register("pvp", new SetHl("setdrau", "Comando para setar warps", "/setdrau",
					Arrays.asList("set", "madelist")));
			
			commandMap.register("pvp",
					new HologramaCommand("kingmade", "Comando para escolher warp", "/warp", Arrays.asList("kingmade", "madelist")));


		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
	}
}
