package br.com.slovermc.lobby.hologramas;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import br.com.slovermc.lobby.BukkitMain;
import br.com.slovermc.lobby.api.XP;
import br.com.slovermc.lobby.ranks.RankList;

public final class HologramaAPI {

	public static final String getColorTopPlayer(final int i) {
		if (i == 1) {
			return "§a";
		} else if (i == 2) {
			return "§e";
		} else if (i == 3) {
			return "§c";
		} else {
			return "§f";
		}
	}

	public static final ArrayList<String> TopXP() {
		HashMap<String, Integer> players = new HashMap<String, Integer>();
		final ArrayList<String> top = new ArrayList<String>();
		top.add("§b§lTOP 10 XP GLOBAL");
		top.add("§fO rank é ordenado de acordo");
		top.add("§fcom o player com mais XP's");
		top.add("");
		for (String playerName : XP.Xp.getKeys(false)) {
			players.put(playerName, XP.Xp.getInt(playerName));
		}

		String nextTop = "None";
		Integer nextTopKills = 0;

		for (int i = 1; i < 11; i++) {
			for (String playerName : players.keySet()) {
				if (players.get(playerName) > nextTopKills) {
					nextTop = playerName;
					nextTopKills = players.get(playerName);
				}
			}
			top.add(getColorTopPlayer(i) + i + ". " + nextTop + " §7| " + RankList.getScoreRankA(nextTopKills) + " §7| §b" + nextTopKills + "§b XP's");
			players.remove(nextTop);
			nextTop = "None";
			nextTopKills = 0;
		}
		return top;
	}

	public static final HashMap<String, Holograma> holograma = new HashMap<>();

	public static void addHalogram(final Location loc, final String text[]) {
		Holograma hologram = new Holograma(text[0], getFrontLocation(loc), false);
		for (int i = 1; i < text.length; i++) {
			hologram.addLine(text[i]);
		}
		holograma.put("hl", hologram);
	}

	public static final ArrayList<Holograma> holograms = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public static void simpleHologram(final Location loc, final String text[]) {
		Holograma hologram = new Holograma(text[0], getFrontLocation(loc), false);
		for (int i = 1; i < text.length; i++) {
			hologram.addLine(text[i]);
		}
		for (Player all : Bukkit.getOnlinePlayers()) {
			hologram.show(all);
		}
		holograms.add(hologram);
	}

	public static final ArrayList<Player> canSee = new ArrayList<>();

	public static boolean hasHologramTopXp = false;

	public static int tasked;

	@SuppressWarnings("deprecation")
	public static final void removeoldXpHologramLocation() {
		Bukkit.getScheduler().cancelTask(tasked);
		Holograma hl = holograma.get("hl");
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (canSee.contains(all)) {
				hl.hide(all);
			}
		}
		hl.remove();
		topXpHologramLoader();
	}

	@SuppressWarnings("deprecation")
	public static final void topXpHologramLoader() {
		if (!LocationsHl.checkBattleWarpNotNull("Hologram")) {
			Bukkit.getConsoleSender().sendMessage("§b§lHOLOGRAM TOP XP§f Você precisar setar usando /set hl..");
			return;
		}
		addHalogram(LocationsHl.getBattleWarpLocation("Hologram"), TopXP().toArray(new String[] {}));
		Holograma hl = holograma.get("hl");
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (canSee.contains(all)) {
				hl.show(all);
			}
		}
		tasked = Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				Holograma hl = holograma.get("hl");
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (canSee.contains(all)) {
						hl.hide(all);
					}
				}
				hl.remove();
				topXpHologramLoader();
			}
		}, 1200L);
	}

	private static final Location getFrontLocation(Location loc) {
		return loc.toVector().add(loc.getDirection().multiply(1).subtract(new Vector(0, 1, 0)))
				.toLocation(loc.getWorld());
	}
}
