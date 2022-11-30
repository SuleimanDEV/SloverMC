package br.com.slovermc.kitpvp.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.AccountAPI;
import br.com.slovermc.kitpvp.account.DeadsAPI;
import br.com.slovermc.kitpvp.account.KillsAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.mysql.RankList;
import br.com.slovermc.kitpvp.mysql.SkyAPI;
import br.com.slovermc.kitpvp.utils.TimeConverter;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Evento.EventoAPI;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;

public final class Score {

	public static final ArrayList<Player> hasScore = new ArrayList<Player>();

	public static final HashMap<Player, ScoreboardAPI> sscore = new HashMap<>();

	public static final String getPlayerFithing(final Player bp) {
		if (!X1WarpListener.playerfigh.containsKey(bp)) {
			return "§aNinguém";
		} else if (X1WarpListener.playerfigh.containsKey(bp)) {
			return "§a" + X1WarpListener.playerfigh.get(bp);
		} else {
			return "§aNinguém";
		}
	}
	
	public static final void updateAllScoreboards() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (final Player all : Bukkit.getOnlinePlayers()) {
					updateScoreboardSpawn(all);
					updateScoreboardFps(all);
					updateScoreboard1v1(all);
					updateScoreboardChallenge(all);
					updateScoreboardEvento(all);
				}
			}
		}, 0, 3L);
	}

	///////////// UPDATES //////////////

	// SPAWN //
	public static final void updateScoreboardSpawn(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
					.setDisplayName("§6§lKITPVP");

			sb.updateLine("Kills§7: ", "§a" + KillsAPI.getKills(bp) , 10);
			sb.updateLine("Deaths§7: ", "§a" + DeadsAPI.getDead(bp), 9);
			sb.updateLine("KillStreak§7: ", "§a" + AccountAPI.getBattlePlayerKillStreak(bp), 8);
			sb.updateLine("Xp§7: ", "§a" + SkyAPI.getXp(bp), 6);
			sb.updateLine("Liga§7: ", RankList.getScoreRank(bp), 5);
			sb.updateLine("Kit: ", "§a" + KitAPI.getKit(bp), 3);
		}
	}

	// EVENTO //
	public static final void updateScoreboardEvento(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.EVENTO) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
					.setDisplayName("§6§lEVENTO");

			sb.updateLine("Tempo§7: ", "§a" + TimeConverter.ConvertTime(EventoAPI.eventtime), 6);
			sb.updateLine("Liga§7: ", RankList.getScoreRank(bp), 4);
			sb.updateLine("Jogadores§7: ", "§a" + EventoAPI.Players.size() + "/" + EventoAPI.maxEventSlots, 3);
		}
	}

	// FPS //
	public static final void updateScoreboard1v1(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.FPS) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score").setDisplayName("§6§lFPS");

			sb.updateLine("Kills§7: ", "§a" + KillsAPI.getKills(bp), 10);
			sb.updateLine("Deaths§7: ", "§a" + DeadsAPI.getDead(bp), 9);
			sb.updateLine("KillStreak§7: ", "§a" + AccountAPI.getBattlePlayerKillStreak(bp), 8);
			sb.updateLine("Xp§7: ", "§a" + SkyAPI.getXp(bp), 6);
			sb.updateLine("Liga§7: ", RankList.getScoreRank(bp), 5);
			sb.updateLine("Warp: ", "§a" + WarpAPI.getWarp(bp), 3);
		}
	}

	// 1v1 //
	public static final void updateScoreboardFps(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.ONEVSONE) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
					.setDisplayName("§6§l1V1");

			sb.updateLine("Kills§7: ", "§a" + KillsAPI.getKills(bp), 10);
			sb.updateLine("Deaths§7: ", "§a" + DeadsAPI.getDead(bp), 9);
			sb.updateLine("KillStreak§7: ", "§a" + AccountAPI.getBattlePlayerKillStreak(bp), 8);
			sb.updateLine("Liga§7: ", RankList.getScoreRank(bp), 6);
			sb.updateLine(getPlayerFithing(bp), "", 3);
		}
	}

	// CHALLENGE //
	public static final void updateScoreboardChallenge(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.LAVA_CHALLENGE) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
					.setDisplayName("§6§lCHALLENGE");

			sb.updateLine("Xp§7: ", "§a" + SkyAPI.getXp(bp), 4);
			sb.updateLine("Liga§7: ", RankList.getScoreRank(bp), 3);
		}
	}

	///////// BUILDS/CREATES ///////////

	// SPAWN //
	public static final void createScoreSpawn(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lKITPVP");

			WarpAPI.setWarp(bp, "SPAWN");
			score.add("", "", 11);
			score.add("Kills§7: ", "§a" + KillsAPI.getKills(bp), 10);
			score.add("Deaths§7: ", "§a" + DeadsAPI.getDead(bp), 9);
			score.add("KillStreak§7: ", "§a" + AccountAPI.getBattlePlayerKillStreak(bp), 8);
			score.add("", "", 7);
			score.add("Xp§7: ", "§a" + SkyAPI.getXp(bp), 6);
			score.add("Liga§7: ", RankList.getScoreRank(bp), 5);
			score.add("", "", 4);
			score.add("Kit: ", "§a" + KitAPI.getKit(bp), 3);
			score.add("", "", 2);
			score.add("§e       matchmc", "§e.com.br   ", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}

	// FPS //
	public static final void createScoreFps(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.FPS) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lFPS");

			WarpAPI.setWarp(bp, "FPS");
			score.add("", "", 11);
			score.add("Kills§7: ", "§a" + KillsAPI.getKills(bp), 10);
			score.add("Deaths§7: ", "§a" + DeadsAPI.getDead(bp), 9);
			score.add("KillStreak§7: ", "§a" + AccountAPI.getBattlePlayerKillStreak(bp), 8);
			score.add("", "", 7);
			score.add("Xp§7: ", "§a" + SkyAPI.getXp(bp), 6);
			score.add("Liga§7: ", RankList.getScoreRank(bp), 5);
			score.add("", "", 4);
			score.add("Warp: ", "§a" + WarpAPI.getWarp(bp), 3);
			score.add("", "", 2);
			score.add("§e       matchmc", "§e.com.br   ", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}

	// 1v1 //
	public static final void createScore1v1(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.ONEVSONE) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§l1V1");

			WarpAPI.setWarp(bp, "1v1");
			score.add("", "", 11);
			score.add("Kills§7: ", "§a" + KillsAPI.getKills(bp), 10);
			score.add("Deaths§7: ", "§a" + DeadsAPI.getDead(bp), 9);
			score.add("KillStreak§7: ", "§a" + AccountAPI.getBattlePlayerKillStreak(bp), 8);
			score.add("", "", 7);
			score.add("Liga§7: ", RankList.getScoreRank(bp), 6);
			score.add("", "", 5);
			score.add("§fLutando", " §fcontra§7:", 4);
			score.add(getPlayerFithing(bp), "", 3);
			score.add("", "", 2);
			score.add("§e       matchmc", "§e.com.br   ", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}

	// CHALLENGE //
	public static final void createScoreChallenge(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.LAVA_CHALLENGE) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lCHALLENGE");

			WarpAPI.setWarp(bp, "CHALLENGE");
			score.add("", "", 5);
			score.add("Xp§7: ", "§a" + SkyAPI.getXp(bp), 4);
			score.add("Liga§7: ", RankList.getScoreRank(bp), 3);
			score.add("", "", 2);
			score.add("§e       matchmc", "§e.com.br   ", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}

	// EVENTO
	public static final void createScoreEvento(final Player bp) {
		if (hasScore.contains(bp) && WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.EVENTO) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lEVENTO");

			WarpAPI.setWarp(bp, "EVENTO");
			score.add("", "", 7);
			score.add("Tempo§7: ", "§a" + TimeConverter.ConvertTime(EventoAPI.eventtime), 6);
			score.add("", "", 5);
			score.add("Liga§7: ", RankList.getScoreRank(bp), 4);
			score.add("Jogadores§7: ", "§a" + EventoAPI.Players.size() + "/" + EventoAPI.maxEventSlots, 3);
			score.add("", "", 2);
			score.add("§e       matchmc", "§e.com.br   ", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}
}