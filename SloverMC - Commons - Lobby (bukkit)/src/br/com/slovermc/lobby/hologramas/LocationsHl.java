package br.com.slovermc.lobby.hologramas;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class LocationsHl {

	public static final void saveNewBattleWarpLocation(final Player bp, final String warpName, final int x, final int y,
			final int z) {
		FileHl.getWarpsFile().set("Hologramas." + warpName + ".X", x);
		FileHl.getWarpsFile().set("Hologramas." + warpName + ".Y", y);
		FileHl.getWarpsFile().set("Hologramas." + warpName + ".Z", z);
		FileHl.getWarpsFile().set("Hologramas." + warpName + ".World", bp.getWorld().getName());
		FileHl.saveWarpsFile();
	}
	
	public static final void setHologramaspawnDistanceProtection(final int distance) {
		FileHl.getWarpsFile().set("SpawnProtections." + "SPAWN" + ".Distance", distance);
		FileHl.saveWarpsFile();
	}
	
	public static final void setBattleWarpFpsDistanceProtection(final int distance) {
		FileHl.getWarpsFile().set("SpawnProtections." + "FPS" + ".Distance", distance);
		FileHl.saveWarpsFile();
	}

	public static final void setNewBattleWarpLocation(final Player bp, final String warpName, final Location location) {
		final int X = location.getBlockX();
		final int Y = location.getBlockY();
		final int Z = location.getBlockZ();
		saveNewBattleWarpLocation(bp, warpName, X, Y, Z);
	}

	public static final Location getWarpLocation(final Player bp, final String warpName) {
		final int X = FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".X");
		final int Y = FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".Y");
		final int Z = FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".Z");
		return new Location(bp.getWorld(), X, Y, Z);
	}

	public static final boolean checkBattleWarpNotNull(final String warpName) {
		if (FileHl.getWarpsFile().contains("Hologramas." + warpName)) {
			return true;
		} else {
			return false;
		}
	}

	public static final void teleportToBattleWarpLocation(final Player bp, final String warpName) {
		final Location warpLocation = new Location(bp.getWorld(),
				FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".X"),
				FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".Y"),
				FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".Z"));
		bp.teleport(warpLocation);
	}

	public static final Location getBattleWarpLocation(final String warpName) {
		final Location warpLocation = new Location(
				Bukkit.getServer()
						.getWorld(FileHl.getWarpsFile().getString("Hologramas." + warpName + ".World")),
				FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".X"),
				FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".Y"),
				FileHl.getWarpsFile().getInt("Hologramas." + warpName + ".Z"));
		return warpLocation;
	}
}
