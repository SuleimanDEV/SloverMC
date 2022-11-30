package br.com.slovermc.kitpvp.warps;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;
import br.com.slovermc.kitpvp.combat.CombatSystem;
import br.com.slovermc.kitpvp.command.essentials.BuildCommand;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.kits.KitResetor;
import br.com.slovermc.kitpvp.warps.Capiroto.CapirotoWarpListener;
import br.com.slovermc.kitpvp.warps.Challenge.ChallengeWarpListener;
import br.com.slovermc.kitpvp.warps.Evento.EventoAPI;
import br.com.slovermc.kitpvp.warps.Evento.EventoWarpListener;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class WarpsAPI {

	public static final HashMap<Player, Warps> battlePlayerWarp = new HashMap<>();

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

	public static enum Warps {
		SPAWN, FPS, ONEVSONE, LAVA_CHALLENGE, SIMULATOR, CAPIROTO, EVENTO, SS
	}

	public static final void resetPlayerWarps(final Player bp) {
		if (inSpawn.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inSpawn.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
		if (inFps.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inFps.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
		if (inOnevsOne.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inOnevsOne.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
		if (inLavaChallenge.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inLavaChallenge.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
		if (inSimulator.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inSimulator.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
		if (inCapiroto.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inCapiroto.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
		if (inEvento.contains(BattlePlayerAPI.getBattlePlayerName(bp))) {
			inEvento.remove(BattlePlayerAPI.getBattlePlayerName(bp));
		}
	}

	public static final void addPlayerInWarpByArgs(final Player bp, final String args) {
		resetPlayerWarps(bp);
		if (args.equalsIgnoreCase("spawn")) {
			inSpawn.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		} else if (args.equalsIgnoreCase("fps")) {
			inFps.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		} else if (args.equalsIgnoreCase("1v1") || args.equalsIgnoreCase("onevsone")) {
			inOnevsOne.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		} else if (args.equalsIgnoreCase("challenge") || args.equalsIgnoreCase("lavachallenge")) {
			inLavaChallenge.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		} else if (args.equalsIgnoreCase("simulator") || args.equalsIgnoreCase("hgsimulator")) {
			inSimulator.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		} else if (args.equalsIgnoreCase("capiroto") || args.equalsIgnoreCase("gapple")) {
			inCapiroto.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		} else if (args.equalsIgnoreCase("evento") || args.equalsIgnoreCase("event")) {
			inEvento.add(BattlePlayerAPI.getBattlePlayerName(bp));
			return;
		}
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

	public static final void loadWarpItensFromArgs(final Player bp, final String args) {
		if (args.equalsIgnoreCase("fps")) {
			FpsWarpListener.loadFpsWarpMethods(bp);
		} else if (args.equalsIgnoreCase("spawn")) {
			SpawnWarpListener.loadWarpSpawnMethods(bp);
		} else if (args.equalsIgnoreCase("1v1")) {
			X1WarpListener.loadWarp1v1Methods(bp);
		} else if (args.equalsIgnoreCase("LavaChallenge")) {
			ChallengeWarpListener.loadWarpChallengeMethods(bp);
		} else if (args.equalsIgnoreCase("Capiroto")) {
			CapirotoWarpListener.loadCapirotoMethods(bp);
		} else if (args.equalsIgnoreCase("Evento")) {
			EventoWarpListener.loadWarpEventoMethods(bp);
		}
	}

	public static final HashMap<Player, Integer> task = new HashMap<>();

	public static int tasked;

	public static final HashMap<String, BukkitTask> Queen = new HashMap<>();

	public WarpsAPI(final Player bp, final String warpName) {
		if (!LocationsConstructor.checkBattleWarpNotNull(warpName)) {
			bp.sendMessage("§9§lTELEPORTE§f Este local não foi setado ou não existe!");
			return;
		}
		if (warpName.equalsIgnoreCase("Evento") && !EventoAPI.onEvent) {
			bp.sendMessage("§3§lEVENTO§f Nenhum evento está ocorrendo no momento.");
			return;
		}
		if (warpName.equalsIgnoreCase("Evento") && EventoAPI.Players.size() >= EventoAPI.maxEventSlots
				&& !bp.hasPermission("pvp.beneficio.fullevent")) {
			bp.sendMessage("       ");
			bp.sendMessage("         §3§lEVENTO§f O evento está lotado!           ");
			bp.sendMessage(" Compre seu §6§lVIP§f para ter slots da warp evento garantido!   ");
			bp.sendMessage("       ");
			return;
		}
		if (warpName.equalsIgnoreCase("Evento") && EventoAPI.blocked) {
			bp.sendMessage("§3§lEVENTO§f A entrada para o evento está bloqueada!");
			return;
		}
		if (warpName.equalsIgnoreCase("Evento") && EventoAPI.mode == EventoAPI.EventMode.ANDAMENTO) {
			bp.sendMessage("§3§lEVENTO§f O Evento ja iniciou!");
			return;
		}
		if (warpName.equalsIgnoreCase("Hologram")) {
			bp.sendMessage("§9§lTELEPORTE§f Este local não foi setado ou não existe.");
			return;
		}
		if (CombatSystem.combat.containsKey(bp.getName())) {
			if (bp.getFallDistance() > 0) {
				bp.sendMessage("§9§lTELEPORTE§f Você precisa estar no chão para teleportar.");
				return;
			}
			if (teleporting.contains(bp)) {
				Queen.get(bp.getName()).cancel();
				teleporting.remove(bp);
				Queen.remove(bp.getName());
				bp.sendMessage("§9§lTELEPORTE§f Você cancelou o teleporte.");
				return;
			}
			if (teleporting.contains(bp)) {
				teleporting.remove(bp);
			}
			teleporting.add(bp);
			bp.sendMessage("§9§lTELEPORTE§f Você será teleportado em 5 segundos, não se mexa!");
			Queen.put(bp.getName(), new BukkitRunnable() {
				@Override
				public void run() {
					if (bp.isOnline()) {
						if (teleporting.contains(bp)) {
							teleporting.remove(bp);
							Queen.remove(bp.getName());
							if (X1WarpListener.firstMatch == bp.getUniqueId()) {
								X1WarpListener.firstMatch = null;
							}
							KitResetor.resetPlayerKit(bp);
							if (CombatSystem.combat.containsKey(bp.getName())) {
								CombatSystem.combat.remove(bp.getName());
							}
							if (EventoAPI.Players.contains(bp.getName()) && BuildCommand.hasBuild(bp)) {
								BuildCommand.hasBuild.remove(bp);
							}
							for (PotionEffect effect : bp.getActivePotionEffects()) {
								bp.removePotionEffect(effect.getType());
							}
							if (EventoAPI.Players.contains(bp.getName())) {
								EventoAPI.Players.remove(bp.getName());
							}
							KitResetor.resetPlayerKit(bp);
							KitAPI.setKit(bp, "Nenhum");
							CooldownAPI.removeCooldown(bp);
							addPlayerInWarpByArgs(bp, warpName);
							LocationsConstructor.teleportToBattleWarpLocation(bp, warpName);
							bp.sendMessage("§9§lTELEPORTE§f Você foi teleportado para a Warp §b" + warpName + "§f.");
							loadWarpItensFromArgs(bp, warpName);
							TittleAPI.sendTittle(bp, "§e§l" + warpName);
							TittleAPI.sendSubTittle(bp, "§fVocê foi teleportado.");
						}
					}
				}
			}.runTaskLater(BukkitMain.getPlugin(), 100L));
		} else if (!CombatSystem.combat.containsKey(bp.getName()) && !teleporting.contains(bp)) {
			KitResetor.resetPlayerKit(bp);
			if (X1WarpListener.firstMatch == bp.getUniqueId()) {
				X1WarpListener.firstMatch = null;
			}
			if (Queen.containsKey(bp.getName())) {
				Queen.remove(bp.getName());
			}
			if (CombatSystem.combat.containsKey(bp.getName())) {
				CombatSystem.combat.remove(bp.getName());
			}
			if (EventoAPI.Players.contains(bp.getName()) && BuildCommand.hasBuild(bp)) {
				BuildCommand.hasBuild.remove(bp);
			}
			for (PotionEffect effect : bp.getActivePotionEffects()) {
				bp.removePotionEffect(effect.getType());
			}
			if (EventoAPI.Players.contains(bp.getName())) {
				EventoAPI.Players.remove(bp.getName());
			}
			addPlayerInWarpByArgs(bp, warpName);
			KitResetor.resetPlayerKit(bp);
			KitAPI.setKit(bp, "Nenhum");
			CooldownAPI.removeCooldown(bp);
			LocationsConstructor.teleportToBattleWarpLocation(bp, warpName);
			bp.sendMessage("§9§lTELEPORTE§f Você foi teleportado para a Warp §b" + warpName + "§f.");
			loadWarpItensFromArgs(bp, warpName);
			TittleAPI.sendTittle(bp, "§e§l" + warpName);
			TittleAPI.sendSubTittle(bp, "§fVocê foi teleportado.");
		}
	}
}
