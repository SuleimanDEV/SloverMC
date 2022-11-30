package br.com.slovermc.gladiator.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import br.com.slovermc.gladiator.events.PlayerEvent;
import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.events.ListenerItens;
import br.com.slovermc.gladiator.schematics.Schematic;
import br.com.slovermc.gladiator.timer.ClearArena;

public class GladiatorAPI implements Listener {

	public static final ArrayList<Block> gladiator = new ArrayList<>();
	public static boolean generateGlass = true;
	public static HashMap<String, String> emluta = new HashMap<String, String>();
	public static HashMap<Player, Location> localizacao = new HashMap<Player, Location>();
	public static HashMap<Location, Block> bloco = new HashMap<Location, Block>();
	public static HashMap<Integer, String[]> players = new HashMap<Integer, String[]>();
	private static HashMap<Player, Location> arenah = new HashMap<Player, Location>();
	private static int k = 0;

	public static int nextID = 0;

	@SuppressWarnings("deprecation")
	public final static void ConfigureArena(Player p1, Player p2) {
		for (Iterator<?> i = gladiator.iterator(); i.hasNext();) {
			Block tudo = (Block) i.next();
			if (tudo.getType() == Material.getMaterial(41)) {
				p1.teleport(new Location(p1.getWorld(), tudo.getLocation().getBlockX(),
						tudo.getLocation().getBlockY() + 5, tudo.getLocation().getBlockZ()));
				tudo.setType(Material.AIR);
			}
			if (tudo.getType() == Material.getMaterial(57)) {
				p2.teleport(new Location(p1.getWorld(), tudo.getLocation().getBlockX(),
						tudo.getLocation().getBlockY() + 5, tudo.getLocation().getBlockZ()));
				tudo.setType(Material.AIR);
			}
			if (tudo.getType() == Material.GLASS) {
				tudo.setTypeIdAndData(95, (byte) 1, true);
			}
		}
		gladiator.clear();
	}

	public static Object spawnArena(Player play1, Player play2, Location l, Material mat) {
		double x = l.getX();
		double y = l.getY();
		double z = l.getZ();
		Location loc = new Location(play1.getWorld(), x, y + 120, z);
		Integer currentID = Integer.valueOf(nextID);
		nextID += 1;
		ArrayList<String> list = new ArrayList<String>();
		loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
		players.put(currentID, (String[]) list.toArray(new String[1]));
		if (generateGlass) {
			List<Location> cuboid = new ArrayList<Location>();
			cuboid.clear();
			int bZ;
			for (int bX = -10; bX <= 10; bX++) {
				for (bZ = -10; bZ <= 10; bZ++) {
					for (int bY = -1; bY <= 10; bY++) {
						Block b = loc.clone().add(bX, bY, bZ).getBlock();
						if (!b.isEmpty()) {
							if (k <= 0) {
								k++;
							}
							Random rs = new Random();
							x = rs.nextInt(5000);
							y = rs.nextInt(120);
							z = rs.nextInt(1000);
							Location lk = new Location(Bukkit.getWorld("world"), x, y, z);
							return spawnArena(play1, play2, lk, mat);
						}
					}
				}
			}
			try {
				Schematic s = Schematic.getInstance()
						.loadSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "gladiator.schematic"));
				Schematic.getInstance().newSchematic(BukkitMain.getPlugin().getServer().getWorlds().get(0),
						new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), x, 131, z), s, gladiator);
			} catch (IOException | br.com.slovermc.gladiator.schematics.DataException e) {
				play1.sendMessage("§b§lGLADIATOR§f Não foi possível CRIAR a arena!");
				e.printStackTrace();
				return null;
			}
			arenah.put(play1, new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), x + 8, 131, z + 8));
			arenah.put(play2, new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), x - 8, 131, z - 8));
			ConfigureArena(play1, play2);
			play1.sendMessage(
					"§b§lGLADIATOR§f Você §3§lDESAFIOU§f o jogador §b" + play2.getName() + "§f para uma batalha!");
			play2.sendMessage("§b§lGLADIATOR§f Você foi §3§lDESAFIADO§f pelo jogador §b" + play1.getName()
			+ "§f para uma batalha!");
			Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
				@Override
				public void run() {
					ClearArena.ClearArenaInGame(play1);
					ClearArena.ClearArenaInGame(play2);
				}
			}, 300L);
			return null;
		}
			//new BukkitRunnable() {
				//@Override
				//public void run() {
				//	ClearArena.ClearArenaInGame(play1);
				//	ClearArena.ClearArenaInGame(play2);
				//}

			//}.runTaskTimer(BukkitMain.getPlugin(), 220L, 540 * 20L);

		List<Location> cuboid2 = new ArrayList<Location>();
		int bZ2;
		for (int bX = -10; bX <= 10; bX++) {
			for (bZ2 = -10; bZ2 <= 10; bZ2++) {
				for (int bY = -1; bY <= 10; bY++) {
				}
				for (Location loc1 : cuboid2) {
					loc1.getBlock().setType(Material.AIR);
				}
			}

		}
		localizacao.put(play1, loc);
		localizacao.put(play2, loc);
		return null;

	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public final static void onPlayerInteract(final PlayerInteractEvent e) {
		if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.getMaterial(95))
				&& (e.getPlayer().getGameMode() != GameMode.CREATIVE)
				&& (emluta.containsKey(e.getPlayer().getName()))) {
			e.setCancelled(true);
		}
	}

	public static void removeArena(Player p) {
		try {
			Schematic s = Schematic.getInstance()
					.loadSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "gladiator.schematic"));
			Schematic.getInstance().deletSchematic(BukkitMain.getPlugin().getServer().getWorlds().get(0), arenah.get(p), s);
		} catch (IOException | br.com.slovermc.gladiator.schematics.DataException e) {
			p.sendMessage("§b§lGLADIATOR§f Não foi possível APAGAR a arena!");
			return;
		}
	}

	public final static void cancelBatalha(Player play1, String motivo) {
		String p2 = emluta.get(play1.getName());
		Player play2 = Bukkit.getPlayer(p2);
		play1.teleport(play1.getWorld().getSpawnLocation());
		play2.teleport(play2.getWorld().getSpawnLocation());
		removeArena(play1);
		emluta.remove(play1.getName());
		emluta.remove(play2.getName());
		play1.getInventory().clear();
		play2.getInventory().clear();
		play1.getInventory().setArmorContents(null);
		play2.getInventory().setArmorContents(null);
		BukkitMain.getitemDefault(play1);
		BukkitMain.getitemDefault(play2);

	}

	@SuppressWarnings("deprecation")
	public static String getPlayerLuta(Player p) {
		String p2 = emluta.get(p.getName());
		Player play2 = Bukkit.getPlayer(p2);
		if (play2 == null) {
			emluta.remove(p.getName());
			p.teleport(new Location(Bukkit.getWorld("world"), -748, 4, -512));
			p.sendMessage("");
			p.sendMessage("§4§lDESLOGOU §fSeu oponente §e" + p.getName() + " §fdeslogou!");
			p.sendMessage("§a§lWIN §fVocê venceu a partida §c(Desistência)§f.");
			p.sendMessage("§6§lXP §fVocê ganhou 25 Xp, §a+1 §fwinstreak e §a+1 §fwin.");
			p.sendMessage("");
			PlayerEvent.damage.remove(p);
			removeArena(p);
			DeathsAPI.addDeaths(play2, 1);
			WsAPI.setWs(play2, 0);
			WinsAPI.addWins(p, 1);
			WsAPI.addWs(p, 1);
			SkyAPI.addXp(p, 25);
			p.setHealth(20);
			ListenerItens.toFight = null;
			ListenerItens.toFight1 = null;
			return "Jogandor não esta em luta";
		}
		return play2.getName();

	}

	public final static void RandomBattle(Player p, Player p2, int wither, int espada, int glass, int recraft,
			boolean ferramenta) {
		@SuppressWarnings("deprecation")
		ItemStack icone = new ItemStack(351, 64, (short) 3);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName("§fRecraft");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		p.getInventory().clear();
		p2.getInventory().clear();
		if (espada == 0) {
			API.item(p.getInventory(), p, Material.DIAMOND_SWORD, 1, 0, "§cESPADA", "", 0);
			API.item(p2.getInventory(), p2, Material.DIAMOND_SWORD, 1, 0, "§cESPADA", "", 0);
		}
		if (espada == 1) {
			API.itemenchant(p.getInventory(), p, Material.DIAMOND_SWORD, 1, 0, "§cESPADA", "",
					0, Enchantment.DAMAGE_ALL, 1);
			API.itemenchant(p2.getInventory(), p2, Material.DIAMOND_SWORD, 1, 0, "§cESPADA",
					"", 1, Enchantment.DAMAGE_ALL, 1);
		}
		if (espada == 3) {
			API.itemenchant(p.getInventory(), p, Material.DIAMOND_SWORD, 1, 0, "§cESPADA", "",
					0, Enchantment.DAMAGE_ALL, 1);
			API.itemenchant(p2.getInventory(), p2, Material.DIAMOND_SWORD, 1, 0, "§cESPADA",
					"", 1, Enchantment.DAMAGE_ALL, 1);
		}
		if (ferramenta == true) {
			API.item(p.getInventory(), p, Material.STONE_PICKAXE, 1, 0, "§cPicareta", "", 26);
			API.item(p2.getInventory(), p2, Material.STONE_PICKAXE, 1, 0, "§cPicareta", "",
					26);
			API.item(p2.getInventory(), p2, Material.STONE_AXE, 1, 0, "§cMachado", "", 17);
			API.item(p.getInventory(), p, Material.STONE_AXE, 1, 0, "§cMachado", "", 17);
		}

		if (glass == 0) {
			GladiatorAPI.spawnArena(p, p2, new Location(Bukkit.getWorld("world"), 1000, 190, 1000), Material.GLASS);
		}
		if (glass == 1) {
			GladiatorAPI.spawnArena(p, p2, new Location(Bukkit.getWorld("world"), 1000, 190, 1000), Material.BEDROCK);
		}
		if (glass == 2) {
			GladiatorAPI.spawnArena(p, p2, new Location(Bukkit.getWorld("world"), 1000, 190, 1000), Material.LEAVES);
		}
		if (wither == 1) {
			while (GladiatorAPI.emluta.containsKey(p.getName()) && emluta.containsKey(p2.getName())) {
				PotionManager(p, 20 * 1 * 60);
			}
		}
		if (wither == 3) {
			while (GladiatorAPI.emluta.containsKey(p.getName()) && emluta.containsKey(p2.getName())) {
				PotionManager(p, 20 * 1 * 60);
			}
		}
		if (wither == 5) {
			while (GladiatorAPI.emluta.containsKey(p.getName()) && emluta.containsKey(p2.getName())) {
				PotionManager(p, 20 * 1 * 60);
			}
		}

		if (recraft == 0) {
			p.getInventory().setItem(14, icone);
			p.getInventory().setItem(15, icone);
			p.getInventory().setItem(16, icone);
			p.getInventory().setItem(23, icone);
			p.getInventory().setItem(24, icone);
			p2.getInventory().setItem(14, icone);
			p2.getInventory().setItem(15, icone);
			p2.getInventory().setItem(16, icone);
			p2.getInventory().setItem(23, icone);
			p2.getInventory().setItem(24, icone);
		}

		if (recraft == 1) {
			API.item(p.getInventory(), p, Material.RED_MUSHROOM, 64, 0, "§cRecraft", "", 14);
			API.item(p.getInventory(), p, Material.RED_MUSHROOM, 64, 0, "§cRecraft", "", 15);
			API.item(p.getInventory(), p, Material.BROWN_MUSHROOM, 64, 0, "§cRecraft", "",
					16);
			API.item(p.getInventory(), p, Material.BROWN_MUSHROOM, 64, 0, "§cRecraft", "",
					23);
			p.getInventory().setItem(24, icone);
			API.item(p2.getInventory(), p2, Material.RED_MUSHROOM, 64, 0, "§cRecraft", "",
					14);
			API.item(p2.getInventory(), p2, Material.RED_MUSHROOM, 64, 0, "§cRecraft", "",
					15);
			API.item(p2.getInventory(), p2, Material.BROWN_MUSHROOM, 64, 0, "§cRecraft", "", 16);
			API.item(p2.getInventory(), p2, Material.BROWN_MUSHROOM, 64, 0, "§cRecraft", "", 23);
			p2.getInventory().setItem(24, icone);
		}
		emluta.put(p.getName(), p2.getName());
		emluta.put(p2.getName(), p.getName());
		ItensManager(p);
		ItensManager(p2);
		for (int i = 0; i < 34; i++) {
			ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta sopam = sopa.getItemMeta();
			sopam.setDisplayName("§fRecraft");
			sopa.setItemMeta(sopam);
			p.getInventory().addItem(sopa);
			p2.getInventory().addItem(sopa);

		}

	}

	private static void PotionManager(Player p, int timer) {
		new BukkitRunnable() {
			@Override
			public void run() {
				p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 9999, 1), true);
			}
		}.runTaskLater(BukkitMain.plugin, timer);

	}

	public final static void ItensManager(Player p) {
		API.item(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura", "", 18);
		API.item(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura", "", 19);
		API.item(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", "", 20);
		API.item(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", "", 21);
		API.item(p.getInventory(), p, Material.BOWL, 64, 0, "§fRecraft", "", 22);
		API.item(p.getInventory(), p, Material.WOOD, 64, 0, "§cMadeira", "", 8);
		API.item(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura", "", 9);
		API.item(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura", "", 10);
		API.item(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", "", 11);
		API.item(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", "", 12);
		API.item(p.getInventory(), p, Material.BOWL, 64, 0, "§fRecraft", "", 13);
		API.item(p.getInventory(), p, Material.COBBLE_WALL, 64, 0, "§6Castelo", "", 1);
		API.item(p.getInventory(), p, Material.LAVA_BUCKET, 1, 0, "§cLava", "", 2);
		API.item(p.getInventory(), p, Material.WATER_BUCKET, 1, 0, "§bAgua", "", 3);
		API.item(p.getInventory(), p, Material.STONE_AXE, 1, 0, "§cMachado", "", 17);
		API.item(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura", "", 18);
		API.item(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura", "", 19);
		API.item(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", "", 20);
		API.item(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", "", 21);
		API.item(p.getInventory(), p, Material.BOWL, 64, 0, "§fRecraft", "", 22);
		API.item(p.getInventory(), p, Material.LAVA_BUCKET, 1, 0, "§cLava", "", 27);
		API.item(p.getInventory(), p, Material.LAVA_BUCKET, 1, 0, "§cLava", "", 28);
		ItemStack h = API.item2(p.getInventory(), p, Material.IRON_HELMET, 1, 0, "§fArmadura","");
		ItemStack c = API.item2(p.getInventory(), p, Material.IRON_CHESTPLATE, 1, 0, "§fArmadura","");
		ItemStack l = API.item2(p.getInventory(), p, Material.IRON_LEGGINGS, 1, 0, "§fArmadura", "");
		ItemStack b = API.item2(p.getInventory(), p, Material.IRON_BOOTS, 1, 0, "§fArmadura", "");
		p.getInventory().setHelmet(h);
		p.getInventory().setChestplate(c);
		p.getInventory().setLeggings(l);
		p.getInventory().setBoots(b);
		p.updateInventory();
	}
}
