package br.com.slovermc.gladiator.hologramas;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public final class HologramLoc {

	public static final ArrayList<String> inSpawn = new ArrayList<>();
	public static final ArrayList<String> inFps = new ArrayList<>();
	public static final ArrayList<String> inOnevsOne = new ArrayList<>();
	public static final ArrayList<String> inLavaChallenge = new ArrayList<>();
	public static final ArrayList<String> inSimulator = new ArrayList<>();
	public static final ArrayList<String> inCapiroto = new ArrayList<>();
	public static final ArrayList<String> inEvento = new ArrayList<>();

	public static final int getBattlePlayersInWarpSpawn() {
		return inSpawn.size();
	}

	public static final int getBattlePlayersInWarpFps() {
		return inFps.size();
	}

	public static final int getBattlePlayersInWarpOnevsOne() {
		return inOnevsOne.size();
	}

	public static final int getBattlePlayersInWarpLavaChallenge() {
		return inLavaChallenge.size();
	}

	public static final int getBattlePlayersInWarpSimulator() {
		return inSimulator.size();
	}

	public static final int getBattlePlayersInWarpCapiroto() {
		return inCapiroto.size();
	}

	public static final int getBattlePlayersInWarpEvento() {
		return inEvento.size();
	}

	public static final String convertWarpName(final String args) {
		if (args.equalsIgnoreCase("spawn")) {
			return "Spawn";

		} else if (args.equalsIgnoreCase("fps")) {
			return "Fps";
		} else if (args.equalsIgnoreCase("ss") || args.equalsIgnoreCase("screenshare")) {
			return "Screenshare";
		} else if (args.equalsIgnoreCase("hologram") || args.equalsIgnoreCase("halogram") || args.equalsIgnoreCase("hl")
				|| args.equalsIgnoreCase("holograma")) {
			return "Hologram";
		} else if (args.equalsIgnoreCase("top")) {
			return "Top";
		} else if (args.equalsIgnoreCase("1v1") || args.equalsIgnoreCase("onevsone")) {
			return "1v1";
		} else if (args.equalsIgnoreCase("1v1loc1") || args.equalsIgnoreCase("1v1pos1")) {
			return "1v1loc1";
		} else if (args.equalsIgnoreCase("1v1loc2") || args.equalsIgnoreCase("1v1pos2")) {
			return "1v1loc2";
		} else if (args.equalsIgnoreCase("challenge") || args.equalsIgnoreCase("lavachallenge")) {
			return "LavaChallenge";
		} else if (args.equalsIgnoreCase("simulator") || args.equalsIgnoreCase("hgsimulator")) {
			return "Simulator";
		} else if (args.equalsIgnoreCase("capiroto") || args.equalsIgnoreCase("gapple")) {
			return "Capiroto";
		} else if (args.equalsIgnoreCase("evento") || args.equalsIgnoreCase("event")) {
			return "Evento";
		}
		return args;
	}

	public static final ArrayList<Player> teleporting = new ArrayList<>();


	public static final HashMap<Player, Integer> task = new HashMap<>();

	public static int tasked;

	public static final HashMap<String, BukkitTask> Queen = new HashMap<>();

	public HologramLoc(final Player bp, final String warpName) {
		if (!LocationsHl.checkBattleWarpNotNull(warpName)) {
			bp.sendMessage("§9§lTELEPORTE§f Este local não foi setado ou não existe!");
			return;
		}
		if (warpName.equalsIgnoreCase("Hologram")) {
			bp.sendMessage("§9§lTELEPORTE§f Este local não foi setado ou não existe.");
			return;
		}
		if (!LocationsHl.checkBattleWarpNotNull(warpName)) {
			bp.sendMessage("§9§lTELEPORTE§f Este local não foi setado ou não existe!");
			return;
		}
		if (warpName.equalsIgnoreCase("Top")) {
			bp.sendMessage("§9§lTELEPORTE§f Este local não foi setado ou não existe.");
			return;
		}
	}
}
