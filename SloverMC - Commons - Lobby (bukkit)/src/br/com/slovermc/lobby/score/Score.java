package br.com.slovermc.lobby.score;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.groupmanager.api.IGroupApi;
import br.com.slovermc.lobby.BukkitMain;
import br.com.slovermc.lobby.api.SkyAPI;
import br.com.slovermc.lobby.ranks.RankList;

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
					updateScoreboardLobby(all);
				}
			}
		}, 0, 3L);
	}
	
	public static final void updateScoreboardLobby(final Player bp) {
		if (hasScore.contains(bp)) {

			final ScoreboardAPI sb = sscore.get(bp);

			bp.getScoreboard().getObjective("score")
					.setDisplayName("§6§lMATCHNETWORK");

			sb.updateLine("Cargo§7: ", Grupo(bp), 6);
			sb.updateLine("Liga§7: ", "" + RankList.getScoreRank(bp), 4);
			sb.updateLine("Xp§7: ", "§b" + SkyAPI.getXp(bp), 3);
			sb.updateLine("Online§7: ", "§a" + br.com.slovermc.lobby.BukkitMain.onlinePlayers, 5);
		}
	}

	public static final void createScoreLobby(final Player bp) {
		if (hasScore.contains(bp)) {
			final ScoreboardAPI score = new ScoreboardAPI(bp, "§6§lMATCHNETWORK");

			score.add("", "", 7);
			score.add("Cargo§7: ", Grupo(bp), 6);
			score.add("Online§7: ", "§a" + br.com.slovermc.lobby.BukkitMain.onlinePlayers, 5);
			score.add("Liga§7: ", "" + RankList.getScoreRank(bp), 4);
			score.add("Xp§7: ", "§b" + SkyAPI.getXp(bp), 3);
			score.add("", "", 2);
			score.add("§e       matchmc", "§e.com.br   ", 1);

			sscore.put(bp, score);
			bp.setScoreboard(score.getSoreboard());
		}
	}
}