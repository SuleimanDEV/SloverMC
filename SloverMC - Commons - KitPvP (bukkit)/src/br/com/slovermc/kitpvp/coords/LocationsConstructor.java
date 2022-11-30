package br.com.slovermc.kitpvp.coords;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class LocationsConstructor {

	public static final void saveNewBattleWarpLocation(final Player bp, final String warpName, final int x, final int y,
			final int z) {
		LocationsFile.getWarpsFile().set("BattleWarps." + warpName + ".X", x);
		LocationsFile.getWarpsFile().set("BattleWarps." + warpName + ".Y", y);
		LocationsFile.getWarpsFile().set("BattleWarps." + warpName + ".Z", z);
		LocationsFile.getWarpsFile().set("BattleWarps." + warpName + ".World", bp.getWorld().getName());
		LocationsFile.saveWarpsFile();
	}
	
	public static final void setBattleWarpSpawnDistanceProtection(final int distance) {
		LocationsFile.getWarpsFile().set("SpawnProtections." + "SPAWN" + ".Distance", distance);
		LocationsFile.saveWarpsFile();
	}
	
	public static final void setBattleWarpFpsDistanceProtection(final int distance) {
		LocationsFile.getWarpsFile().set("SpawnProtections." + "FPS" + ".Distance", distance);
		LocationsFile.saveWarpsFile();
	}

	public static final void setNewBattleWarpLocation(final Player bp, final String warpName, final Location location) {
		final int X = location.getBlockX();
		final int Y = location.getBlockY();
		final int Z = location.getBlockZ();
		saveNewBattleWarpLocation(bp, warpName, X, Y, Z);
	}

	public static final Location getWarpLocation(final Player bp, final String warpName) {
		final int X = LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".X");
		final int Y = LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".Y");
		final int Z = LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".Z");
		return new Location(bp.getWorld(), X, Y, Z);
	}

	public static final boolean checkBattleWarpNotNull(final String warpName) {
		if (LocationsFile.getWarpsFile().contains("BattleWarps." + warpName)) {
			return true;
		} else {
			return false;
		}
	}

	public static final void teleportToBattleWarpLocation(final Player bp, final String warpName) {
		final Location warpLocation = new Location(bp.getWorld(),
				LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".X"),
				LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".Y"),
				LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".Z"));
		bp.teleport(warpLocation);
	}

	public static final Location getBattleWarpLocation(final String warpName) {
		final Location warpLocation = new Location(
				Bukkit.getServer()
						.getWorld(LocationsFile.getWarpsFile().getString("BattleWarps." + warpName + ".World")),
				LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".X"),
				LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".Y"),
				LocationsFile.getWarpsFile().getInt("BattleWarps." + warpName + ".Z"));
		return warpLocation;
	}
}
