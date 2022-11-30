package br.com.slovermc.kitpvp.topks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.KsAPI;

public final class TopKillStreakAPI {

	public static HashMap<Player, String> NameSpawn = new HashMap<Player, String>();
	public static HashMap<Player, String> NameFps = new HashMap<Player, String>();
	public static HashMap<Player, String> Name1v1 = new HashMap<Player, String>();
	public static HashMap<Player, Integer> KsTopSpawn = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> KsTopFps = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> KsTop1v1 = new HashMap<Player, Integer>();
	
	public static final void updateTopKs() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {			
				 createTopKs();
			}			
		}, 0, 30L);
	}
	
	@SuppressWarnings("deprecation")
	public static final void createTopKs() {
		if (Bukkit.getOnlinePlayers().length <= 0) {
			return;
		}
		HashMap<String, Integer> players = new HashMap<String, Integer>();
		for (String playerName : KsAPI.Ks.getKeys(false)) {
			players.put(playerName, KsAPI.Ks.getInt(playerName));
		}

		String nextTop = "Ninguém";
		Integer nextTopKs = 0;

		for (int i = 1; i < KsAPI.Ks.getKeys(false).size(); i++) {
			for (String playerName : players.keySet()) {
				if (players.get(playerName) > nextTopKs) {
					for (Player ks : Bukkit.getOnlinePlayers()) {
						if (playerName.equalsIgnoreCase(ks.getName()) || playerName.contains(ks.getName())) {
							nextTop = playerName;
							nextTopKs = players.get(playerName);
							NameSpawn.put(ks, nextTop);
							NameFps.put(ks, nextTop);
							KsTopSpawn.put(ks, nextTopKs);
							KsTopFps.put(ks, nextTopKs);
							
							players.remove(nextTop);
							nextTop = "Ninguém";
							nextTopKs = 0;
							return;
						} else {
							NameSpawn.put(ks, "Ninguém");
							NameFps.put(ks, "Ninguém");
							KsTopSpawn.put(ks, 0);
							KsTopFps.put(ks, 0);
							
							players.remove(nextTop);
							nextTop = "Ninguém";
							nextTopKs = 0;
						}
					}
				}
			}
		}
	}

	public static final String getWarpSpawnTopKsName(final Player bp) {
		if (NameSpawn.containsKey(bp) && NameSpawn.get(bp) == null) {
			return "§3Ninguém";
		} else if (NameSpawn.containsKey(bp) && NameSpawn.get(bp) == "") {
			return "§3Ninguém";
		} else if (NameSpawn.containsKey(bp)) {
			return "§3" + NameSpawn.get(bp);
		} else {
			return "§3Ninguém";
		}
	}

	public static final String getWarpFpsTopKsName(final Player bp) {
		if (NameFps.containsKey(bp) && NameFps.get(bp) == null) {
			return "§3Ninguém";
		} else if (NameFps.containsKey(bp) && NameFps.get(bp) == "") {
			return "§3Ninguém";
		} else if (NameFps.containsKey(bp)) {
			return "§3" + NameFps.get(bp);
		} else {
			return "§3Ninguém";
		}
	}

	public static final String getWarp1v1TopKsName(final Player bp) {
		if (Name1v1.containsKey(bp) && Name1v1.get(bp) == null) {
			return "§3Ninguém";
		} else if (Name1v1.containsKey(bp) && Name1v1.get(bp) == "") {
			return "§3Ninguém";
		} else if (Name1v1.containsKey(bp)) {
			return "§3" + Name1v1.get(bp);
		} else {
			return "§3Ninguém";
		}
	}

	public static final String getWarpSpawnTopKsNumber(final Player bp) {
		if (KsTopSpawn.containsKey(bp) && KsTopSpawn.get(bp) == null) {
			return " §3- 0";
		} else if (KsTopSpawn.containsKey(bp)) {
			return " §3- " + KsTopSpawn.get(bp);
		} else {
			return " §3- 0";
		}
	}

	public static final String getWarpFpsTopKsNumber(final Player bp) {
		if (KsTopFps.containsKey(bp) && KsTopFps.get(bp) == null) {
			return " §3- 0";
		} else if (KsTopFps.containsKey(bp)) {
			return " §3- " + KsTopFps.get(bp);
		} else {
			return " §3- 0";
		}
	}

	public static final String getWarp1v1TopKsNumber(final Player bp) {
		if (KsTop1v1.containsKey(bp) && KsTop1v1.get(bp) == null) {
			return " §3- 0";
		} else if (KsTop1v1.containsKey(bp)) {
			return " §3- " + KsTop1v1.get(bp);
		} else {
			return " §3- 0";
		}
	}
}
