package br.com.slovermc.gladiator.score;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.api.DeathsAPI;
import br.com.slovermc.gladiator.api.GladiatorAPI;
import br.com.slovermc.gladiator.api.WinsAPI;
import br.com.slovermc.gladiator.api.WsAPI;
import br.com.slovermc.gladiator.mysql.RankList;

public final class Score {

	public static final ArrayList<Player> hasScore = new ArrayList<Player>();

	public static final HashMap<Player, ScoreboardAPI> sscore = new HashMap<>();

	public static final void updateAllScoreboards() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (final Player all : Bukkit.getOnlinePlayers()) {
					AtualizarScoreGladiator(all);
				}
			}
		}, 0, 3L);
	}

	@SuppressWarnings("deprecation")
	public static final void AtualizarScoreGladiator(final Player bp) {
		if (hasScore.contains(bp)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
			.setDisplayName("§6§lGLAD-1");

			sb.updateLine("Liga: ", "" + RankList.getScoreRank(bp), 5);
			sb.updateLine("WinStreak: ", "§b" + WsAPI.getWs(bp), 6);
			sb.updateLine("Mortes: ", "§b" + DeathsAPI.getDeaths(bp), 7);
			sb.updateLine("Vitórias: ", "§b" + WinsAPI.getWins(bp), 8);
			sb.updateLine("Jogadores: ", "§e" + Bukkit.getOnlinePlayers().length, 3);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static final void AtualizarScorePlayer1(final Player bp) {
		if (hasScore.contains(bp)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
			.setDisplayName("§6§lGLAD-1");

			sb.updateLine("Liga: ", "" + RankList.getScoreRank(bp), 5);
			sb.updateLine("WinStreak: ", "§b" + WsAPI.getWs(bp), 6);
			sb.updateLine("Mortes: ", "§b" + DeathsAPI.getDeaths(bp), 7);
			sb.updateLine("Vitórias: ", "§b" + WinsAPI.getWins(bp), 8);
			sb.updateLine("Jogadores: ", "§e" + Bukkit.getOnlinePlayers().length, 3);
		}
	}

	@SuppressWarnings("deprecation")
	public static final void ScoreGladiator(final Player bp) {
		if (hasScore.contains(bp) || (!GladiatorAPI.emluta.containsKey(bp.getName())) ){
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lGLAD-1");

			score.add("", "", 9);
			score.add("Vitórias: ", "§b" + WinsAPI.getWins(bp), 8);
			score.add("Mortes: ", "§b" + DeathsAPI.getDeaths(bp), 7);
			score.add("WinStreak: ", "§b" + WsAPI.getWs(bp), 6);
			score.add("Liga: ", "" + RankList.getScoreRank(bp), 5);
			score.add("", "", 4);
			score.add("Jogadores: ", "§e" + Bukkit.getOnlinePlayers().length, 3);
			score.add("", "", 2);
			score.add("§6www.slover", "§6mc.com", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}

	@SuppressWarnings("deprecation")
	public static final void ScorePlayer1(final Player bp) {
		if (hasScore.contains(bp) || (GladiatorAPI.emluta.containsKey(bp.getName())) ){
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lGLAD-1");

			if (GladiatorAPI.emluta.containsKey(bp.getName())) {
				score.add("", "", 9);
				score.add("Vitórias: ", "§b" + WinsAPI.getWins(bp), 8);
				score.add("Mortes: ", "§b" + DeathsAPI.getDeaths(bp), 7);
				score.add("WinStreak: ", "§b" + WsAPI.getWs(bp), 6);
				score.add("Liga: ", "" + RankList.getScoreRank(bp), 5);
				score.add("", "", 4);
				score.add("Jogadores: ", "§e" + Bukkit.getOnlinePlayers().length, 3);
				score.add("", "", 2);
				score.add("§6www.slover", "§6mc.com", 1);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}
}