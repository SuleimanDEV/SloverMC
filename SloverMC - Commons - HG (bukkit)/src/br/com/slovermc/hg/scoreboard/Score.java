package br.com.slovermc.hg.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.groupmanager.api.IGroupApi;
import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.eventos.StatusHG;
import br.com.slovermc.hg.mysql.RankList;
import br.com.slovermc.hg.utils.Countdown;

public final class Score {

	public static String Grupo(Player p) {
		return IGroupApi.convertGroupColor(br.com.slovermc.groupmanager.Main.database.getGroup(p));
	}

	public static final ArrayList<Player> hasScore = new ArrayList<Player>();

	public static final HashMap<Player, ScoreboardAPI> sscore = new HashMap<>();

	public static final void updateAllScoreboards() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (final Player all : Bukkit.getOnlinePlayers()) {
					updateScoreboardIniciando(all);
					updateScoreboardInv(all);
					updateScoreboardTempo(all);
					updateScoreboardEnd(all);
				}
			}
		}, 0, 3L);
	}

	///////////// UPDATES //////////////

	// INICIANDO //
	public static final void updateScoreboardIniciando(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.STARTING)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
			.setDisplayName("§6§lHG-" + StatusHG.getIP());

			sb.updateLine("Rank ", Grupo(bp), 10);
			sb.updateLine("Liga ", RankList.getScoreRank(bp), 9);
			sb.updateLine("Iniciando em ", "§e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 7);
			sb.updateLine("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
			sb.updateLine("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
			sb.updateLine("Kit2 ", "§b" + KitAPI.getInstance().getKit2(bp), 3);
		}
	}

	// INV //
	public static final void updateScoreboardInv(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.INVINCIBILITY)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score").setDisplayName("§6§lHG-" + StatusHG.getIP());

			sb.updateLine("Rank ", Grupo(bp), 10);
			sb.updateLine("Liga ", RankList.getScoreRank(bp), 9);
			sb.updateLine("Invencível ", "§e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 7);
			sb.updateLine("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
			sb.updateLine("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
			sb.updateLine("Kit2 ", "§b" + KitAPI.getInstance().getKit2(bp), 3);
		}
	}
	
	// TEMPO //
	public static final void updateScoreboardTempo(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.GAME)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
			.setDisplayName("§6§lHG-" + StatusHG.getIP());

			sb.updateLine("Rank ", Grupo(bp), 10);
			sb.updateLine("Liga ", RankList.getScoreRank(bp), 9);
			sb.updateLine("Andamento ", "§e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 7);
			sb.updateLine("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
			sb.updateLine("Kills ", "§b" + PlayerAPI.getInstance().getKills(bp), 3);
			sb.updateLine("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
		}
	}

	// FINAL //
	public static final void updateScoreboardEnd(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.END)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
			.setDisplayName("§6§lHG-" + StatusHG.getIP());

			sb.updateLine("Rank ", Grupo(bp), 10);
			sb.updateLine("Liga ", RankList.getScoreRank(bp), 9);
			sb.updateLine("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
			sb.updateLine("Kills ", "§b" + PlayerAPI.getInstance().getKills(bp), 3);
			sb.updateLine("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
			sb.updateLine("Você ", "§fGanhou!", 7);
		}
	}
	///////// BUILDS/CREATES ///////////

	// INICIANDO //
	public static final void createScoreIniciando(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.STARTING)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHG-" + StatusHG.getIP());

			if (BukkitMain.state == StateEnum.STARTING) {
				score.add("", "", 11);
				score.add("Rank ", Grupo(bp), 10);
				score.add("Liga ", RankList.getScoreRank(bp), 9);
				score.add("", "", 8);
				score.add("Iniciando em ", "§e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 7);
				score.add("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
				score.add("", "", 5);
				score.add("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
				score.add("Kit2 ", "§b" + KitAPI.getInstance().getKit2(bp), 3);
				score.add("", "", 2);
				score.add("§ewww.matchmc", "§e.com.br", 1);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}

	// INV //
	public static final void createScoreInv(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.INVINCIBILITY)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHG-" + StatusHG.getIP());

			if (BukkitMain.state == StateEnum.INVINCIBILITY) {
				score.add("", "", 11);
				score.add("Rank ", Grupo(bp), 10);
				score.add("Liga ", RankList.getScoreRank(bp), 9);
				score.add("", "", 8);
				score.add("Invencível ", "§e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 7);
				score.add("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
				score.add("", "", 5);
				score.add("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
				score.add("Kit2 ", "§b" + KitAPI.getInstance().getKit2(bp), 3);
				score.add("", "", 2);
				score.add("§ewww.matchmc", "§e.com.br", 1);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}

	// TEMPO //
	public static final void createScoreTempo(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.GAME)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHG-" + StatusHG.getIP());

			if (BukkitMain.state == StateEnum.GAME) {
				score.add("§a", "§a", 11);
				score.add("Rank ", Grupo(bp), 10);
				score.add("Liga ", RankList.getScoreRank(bp), 9);
				score.add("§a", "§a", 8);
				score.add("Andamento ", "§e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 7);
				score.add("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
				score.add("§a", "§a", 5);
				score.add("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
				score.add("Kills ", "§b" + PlayerAPI.getInstance().getKills(bp), 3);
				score.add("§a", "§a", 2);
				score.add("§ewww.matchmc", "§e.com.br", 1);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}

	// FINAL //
	public static final void createScoreEnd(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.END)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHG-" + StatusHG.getIP());

			if (BukkitMain.state == StateEnum.END) {
				score.add("§a", "§a", 11);
				score.add("Rank ", Grupo(bp), 10);
				score.add("Liga ", RankList.getScoreRank(bp), 9);
				score.add("§a", "§a", 8);
				score.add("Você ", "§fGanhou!", 7);
				score.add("Jogadores ", "§e" + PlayerAPI.getInstance().getPlayers().length, 6);
				score.add("§a", "§a", 5);
				score.add("Kit1 ", "§b" + KitAPI.getInstance().getKit(bp), 4);
				score.add("Kills ", "§b" + PlayerAPI.getInstance().getKills(bp), 3);
				score.add("§a", "§a", 2);
				score.add("§ewww.matchmc", "§e.com.br", 1);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}
}