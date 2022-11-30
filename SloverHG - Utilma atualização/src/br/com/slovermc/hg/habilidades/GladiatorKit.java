package br.com.slovermc.hg.habilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.utils.Schematic;

public class GladiatorKit implements Listener {
	
	public static final ArrayList<Block> gladblocos = new ArrayList<>();
	public static boolean generateGlass = true;
	public static HashMap<Integer, String[]> players = new HashMap<Integer, String[]>();
	public static final ArrayList<Block> gladiator = new ArrayList<>();
	public static HashMap<Player, ArrayList<Block>> bloquin = new HashMap<Player, ArrayList<Block>>();
	public static final HashMap<Player, String> batalhando = new HashMap<>();
	private static HashMap<Player, Location> oldLocation = new HashMap<Player, Location>();
	private static HashMap<Player, Location> arenah = new HashMap<Player, Location>();
	public static int nextID = 0;

	public static void clearBlocks(Player p) {
		try {
			Schematic s = Schematic.getInstance()
					.carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "glad.schematic"));
			Schematic.getInstance().deletSchematic(BukkitMain.getPlugin().getServer().getWorlds().get(0), arenah.get(p), s);
		} catch (Exception e) {
			p.sendMessage("§b§lGLADIATOR§f Não foi possível APAGAR a arena!");
		}
	}

	@SuppressWarnings("deprecation")
	public static void configure(Player p1, Player p2) {
		for (Iterator<?> i = gladiator.iterator(); i.hasNext();) {
			Block tudo = (Block) i.next();
			if (tudo.getType() == Material.GOLD_BLOCK) {
				p1.teleport(new Location(Bukkit.getWorld("world"), tudo.getLocation().getBlockX(),
						tudo.getLocation().getBlockY() + 5, tudo.getLocation().getBlockZ()));
				tudo.setType(Material.AIR);
			}
			if (tudo.getType() == Material.DIAMOND_BLOCK) {
				p2.teleport(new Location(Bukkit.getWorld("world"), tudo.getLocation().getBlockX(),
						tudo.getLocation().getBlockY() + 5, tudo.getLocation().getBlockZ()));
				tudo.setType(Material.AIR);
			}
			if (tudo.getType() == Material.GLASS) {
				tudo.setTypeIdAndData(95, (byte) 1, true);
			}
			if (tudo.getType() != Material.AIR) {
				gladblocos.add(tudo);
			}
		}
		gladiator.clear();
	}

	public static boolean isClear(Player p, Location local) {
		final Location locale = new Location(p.getWorld(), local.getX(), 130, local.getZ());
		for (int blockX = -20; blockX <= 20; blockX++) {
			for (int blockZ = -20; blockZ <= 20; blockZ++) {
				for (int blockY = -1; blockY <= 10; blockY++) {
					Block arena = locale.clone().add(blockX, blockY, blockZ).getBlock();
					if (!arena.isEmpty()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static final ArrayList<Player> whiter = new ArrayList<>();
	
	public static int w;
	public static int ww;

	public static Object newArena(Player play1, Player play2, Location l) {
		double x = l.getX();
		double z = l.getZ();
		Location player1 = play1.getLocation();
		Location player2 = play2.getLocation();
		Location loc = new Location(play1.getWorld(), x, 130, z);
		loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
		int bZ;
		for (int bX = -10; bX <= 10; bX++) {
			for (bZ = -10; bZ <= 10; bZ++) {
				for (int bY = -1; bY <= 10; bY++) {
					Block b = loc.clone().add(bX, bY, bZ).getBlock();
					if (!b.isEmpty()) {
						Random r = new Random();
						x = r.nextInt(220);
						z = r.nextInt(220);
						Location lk = new Location(Bukkit.getWorld("world"), x, 130, z);
						return newArena(play1, play2, lk);
					}
				}
			}
		}
		try {
			Schematic s = Schematic.getInstance()
					.carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "glad.schematic"));
			Schematic.getInstance().newSchematic(BukkitMain.getPlugin().getServer().getWorlds().get(0),
					new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), x, 130, z), s, gladiator);
			arenah.put(play1, new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), x, 130, z));
			arenah.put(play2, new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), x, 130, z));
			batalhando.put(play1, play2.getName());
			batalhando.put(play2, play1.getName());
			oldLocation.put(play1, player1);
			oldLocation.put(play2, player2);
			configure(play1, play2);
			play1.sendMessage(
					"§b§lGLADIATOR§f Você §3§lDESAFIOU§f o jogador §b" + play2.getName() + "§f para uma batalha!");
			play2.sendMessage("§b§lGLADIATOR§f Você foi §3§lDESAFIADO§f pelo jogador §b" + play1.getName()
					+ "§f para uma batalha!");
		} catch (Exception e) {
			play1.sendMessage("§b§lGLADIATOR§f Não foi possível CRIAR a arena!");
		}
		return null;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(final PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player killed = (Player) e.getEntity();
			if (batalhando.containsKey(killed)) {
				final Player killer = Bukkit.getPlayerExact(batalhando.get(killed));
				clearBlocks(killer);
				Location old1 = oldLocation.get(killer);
				if (whiter.contains(killed)) {
					whiter.remove(killed);
				}
				if (whiter.contains(killer)) {
					whiter.remove(killer);
				}
				killed.removePotionEffect(PotionEffectType.WITHER);
				killer.removePotionEffect(PotionEffectType.WITHER);
				killer.sendMessage("§b§lGLADIATOR§f Você §a§lVENCEU§f a batalha contra §a" + killed.getName());
				killer.teleport(old1);
				batalhando.remove(killed);
				batalhando.remove(killer);
				bloquin.remove(killer);
				bloquin.remove(killed);
				oldLocation.remove(killed);
				oldLocation.remove(killer);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(final PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (batalhando.containsKey(p)) {
			final Player killer = Bukkit.getPlayerExact(batalhando.get(p));
			Location old1 = oldLocation.get(killer);
			clearBlocks(killer);
			if (whiter.contains(p)) {
				whiter.remove(p);
			}
			if (whiter.contains(killer)) {
				whiter.remove(killer);
			}
			p.removePotionEffect(PotionEffectType.WITHER);
			killer.removePotionEffect(PotionEffectType.WITHER);
			killer.sendMessage("§b§lGLADIATOR§f Seu oponente §c§lDESLOGOU§f portanto você §a§lVENCEU§f a batalha!");
			killer.teleport(old1);
			batalhando.remove(p);
			batalhando.remove(killer);
			bloquin.remove(p);
			bloquin.remove(killer);
			oldLocation.remove(killer);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (((KitAPI.getInstance().hasKit(p, "Gladiator")) || (KitAPI.getInstance().hasKit2(p, "Gladiator")))
				&& (e.getItemInHand().getType() == Material.IRON_FENCE)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void OnGladiatorKit(PlayerInteractEntityEvent event) {
		final Player p = event.getPlayer();
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		if (BukkitMain.state == StateEnum.GAME) {
			final Player r = (Player) event.getRightClicked();
			if (!batalhando.containsKey(p) && !batalhando.containsKey(r)) {
				newArena(p, r, p.getLocation());
			}
		}	
	}

	@EventHandler
	public void onPlayerInteractGlad(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((p.getItemInHand().getType() == Material.IRON_FENCE)
				&& ((KitAPI.getInstance().hasKit(p, "Gladiator")) || (KitAPI.getInstance().hasKit2(p, "Gladiator")))) {
			e.setCancelled(true);
			p.updateInventory();
			return;
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlyaerInteract(final PlayerInteractEvent e) {
		if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.GLASS)
				&& (e.getPlayer().getGameMode() != GameMode.CREATIVE)
				&& (batalhando.containsKey(e.getPlayer()))) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.BEDROCK);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
				public void run() {
					if (GladiatorKit.batalhando.containsKey(e.getPlayer())) {
						e.getClickedBlock().setType(Material.GLASS);
					}
				}
			}, 100L);
		}
	}
}
