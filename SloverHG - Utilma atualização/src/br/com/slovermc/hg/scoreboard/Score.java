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
import br.com.slovermc.hg.utils.Countdown;
import br.com.slovermc.hg.mysql.RankList;

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
		}, 0, 15L);
	}

	///////////// UPDATES //////////////

	// INICIANDO //
	public static final void updateScoreboardIniciando(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.STARTING)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score").setDisplayName("§6§lHARDCORE GAMES");

			sb.updateLine("", "", 15);
			sb.updateLine("Iniciando em§7: ", "§a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()),
					14);
			sb.updateLine("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
			sb.updateLine("", "", 12);
			sb.updateLine("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
			sb.updateLine("Kit 2§7: ", "§a" + KitAPI.getInstance().getKit2(bp), 10);
			sb.updateLine("", "", 9);
			sb.updateLine("Liga: ", "" + RankList.getScoreRank(bp), 8);
			sb.updateLine("", "", 7);
			sb.updateLine("§e       matchmc", "§e.com.br   ", 6);
		}
	}

	// INV //
	public static final void updateScoreboardInv(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.INVINCIBILITY)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score").setDisplayName("§6§lHARDCORE GAMES");

			sb.updateLine("", "", 15);
			sb.updateLine("Invencibilidade",
					"§f: §a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 14);
			sb.updateLine("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
			sb.updateLine("", "", 12);
			sb.updateLine("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
			sb.updateLine("Kit 2§7: ", "§a" + KitAPI.getInstance().getKit2(bp), 10);
			sb.updateLine("", "", 9);
			sb.updateLine("Liga: ", "" + RankList.getScoreRank(bp), 8);
			sb.updateLine("", "", 7);
			sb.updateLine("§e       matchmc", "§e.com.br   ", 6);
		}
	}

	// TEMPO //
	public static final void updateScoreboardTempo(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.GAME)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score").setDisplayName("§6§lHARDCORE GAMES");

			sb.updateLine("", "", 15);
			sb.updateLine("Tempo§7: ", "§a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 14);
			sb.updateLine("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
			sb.updateLine("", "", 12);
			sb.updateLine("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
			sb.updateLine("Kills§7: ", "§a" + PlayerAPI.getInstance().getKills(bp), 10);
			sb.updateLine("", "", 9);
			sb.updateLine("Liga: ", "" + RankList.getScoreRank(bp), 8);
			sb.updateLine("", "", 7);
			sb.updateLine("§e       matchmc", "§e.com.br   ", 6);
		}
	}

	// FINAL //
	public static final void updateScoreboardEnd(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.END)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score").setDisplayName("§6§lHARDCORE GAMES");

			sb.updateLine("", "", 15);
			sb.updateLine("Tempo§7: ", "§a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 14);
			sb.updateLine("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
			sb.updateLine("", "", 12);
			sb.updateLine("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
			sb.updateLine("Kills§7: ", "§a" + PlayerAPI.getInstance().getKills(bp), 10);
			sb.updateLine("", "", 9);
			sb.updateLine("Liga: ", "" + RankList.getScoreRank(bp), 8);
			sb.updateLine("", "", 7);
			sb.updateLine("§e       matchmc", "§e.com.br   ", 6);
		}
	}
	///////// BUILDS/CREATES ///////////

	// INICIANDO //
	public static final void createScoreIniciando(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.STARTING)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHARDCORE GAMES");

			if (BukkitMain.state == StateEnum.STARTING) {
				score.add("", "", 15);
				score.add("Iniciando em: ", "§a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()),
						14);
				score.add("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
				score.add("", "", 12);
				score.add("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
				score.add("Kit 2§7 ", "§a" + KitAPI.getInstance().getKit2(bp), 10);
				score.add("", "", 9);
				score.add("Liga: ", "" + RankList.getScoreRank(bp), 8);
				score.add("", "", 7);
				score.add("§e       matchmc", "§e.com.br   ", 6);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}

	// INV //
	public static final void createScoreInv(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.INVINCIBILITY)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHARDCORE GAMES");

			if (BukkitMain.state == StateEnum.INVINCIBILITY) {
				score.add("", "", 15);
				score.add("Invencibilidade",
						"§f: §a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 14);
				score.add("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
				score.add("", "", 12);
				score.add("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
				score.add("Kit 2§7: ", "§a" + KitAPI.getInstance().getKit2(bp), 10);
				score.add("", "", 9);
				score.add("Liga: ", "" + RankList.getScoreRank(bp), 8);
				score.add("", "", 7);
				score.add("§e       matchmc", "§e.com.br   ", 6);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}

	// TEMPO //
	public static final void createScoreTempo(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.GAME)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHARDCORE GAMES");

			if (BukkitMain.state == StateEnum.GAME) {
				score.add("", "", 15);
				score.add("Tempo§7: ", "§a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 14);
				score.add("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
				score.add("", "", 12);
				score.add("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
				score.add("Kills§7: ", "§a" + PlayerAPI.getInstance().getKills(bp), 10);
				score.add("", "", 9);
				score.add("Liga: ", "" + RankList.getScoreRank(bp), 8);
				score.add("", "", 7);
				score.add("§e       matchmc", "§e.com.br   ", 6);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}

	// FINAL //
	public static final void createScoreEnd(final Player bp) {
		if (hasScore.contains(bp) && (BukkitMain.state == StateEnum.END)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lHARDCORE GAMES");

			if (BukkitMain.state == StateEnum.END) {
				score.add("", "", 15);
				score.add("Tempo§7: ", "§a" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()), 14);
				score.add("Online§7: ", "§a" + PlayerAPI.getInstance().getPlayers().length + "/60", 13);
				score.add("", "", 12);
				score.add("Kit 1§7: ", "§a" + KitAPI.getInstance().getKit(bp), 11);
				score.add("Kills§7: ", "§a" + PlayerAPI.getInstance().getKills(bp), 10);
				score.add("", "", 9);
				score.add("Liga: ", "" + RankList.getScoreRank(bp), 8);
				score.add("", "", 7);
				score.add("§e       matchmc", "§e.com.br   ", 6);

				sscore.put(bp, score);
				bp.setScoreboard(score.getSoreboard());
			}
		}
	}
}