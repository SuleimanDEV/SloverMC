package br.com.slovermc.gladiator.hologramas;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.api.WsAPI;

public class HologramaWinsStreak {

	public static final String getColorTopPlayer(final int i) {
		if (i == 1) {
			return "§b";
		} else if (i == 2) {
			return "§b";
		} else if (i == 3) {
			return "§b";
		} else {
			return "§b";
		}
	}

	public static final ArrayList<String> TopWS() {
		HashMap<String, Integer> players = new HashMap<String, Integer>();
		final ArrayList<String> top = new ArrayList<String>();
		top.add("§b§lTOP 10 WINSTREAK");
		top.add("§fO rank é ordenado de acordo");
		top.add("§fcom o player com mais WINSTREAK");
		top.add("");
		for (String playerName : WsAPI.Ws.getKeys(false)) {
			players.put(playerName, WsAPI.Ws.getInt(playerName));
		}

		String nextTop = "Ninguém";
		Integer nextTopKills = 0;

		for (int i = 1; i < 11; i++) {
			for (String playerName : players.keySet()) {
				if (players.get(playerName) > nextTopKills) {
					nextTop = playerName;
					nextTopKills = players.get(playerName);
				}
			}
			top.add(getColorTopPlayer(i) + i + "º§f " + nextTop + " §3§lWinStreak de §f" + nextTopKills + " §bWins.");
			players.remove(nextTop);
			nextTop = "Ninguém";
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
		holograma.put("top", hologram);
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
	public static final void removeoldTopHologramLocation() {
		Bukkit.getScheduler().cancelTask(tasked);
		Holograma top = holograma.get("top");
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (canSee.contains(all)) {
				top.hide(all);
			}
		}
		top.remove();
		topTopHologramLoader();
	}

	@SuppressWarnings("deprecation")
	public static final void topTopHologramLoader() {
		if (!LocationsHl.checkBattleWarpNotNull("Top")) {
			Bukkit.getConsoleSender().sendMessage("§b§lHOLOGRAM TOP WS§f Você precisar setar usando /set top..");
			return;
		}
		addHalogram(LocationsHl.getBattleWarpLocation("Top"), TopWS().toArray(new String[] {}));
		Holograma top = holograma.get("top");
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (canSee.contains(all)) {
				top.show(all);
			}
		}
		tasked = Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				Holograma top = holograma.get("top");
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (canSee.contains(all)) {
						top.hide(all);
					}
				}
				top.remove();
				topTopHologramLoader();
			}
		}, 1200L);
	}

	private static final Location getFrontLocation(Location loc) {
		return loc.toVector().add(loc.getDirection().multiply(1).subtract(new Vector(0, 1, 0)))
				.toLocation(loc.getWorld());
	}
}