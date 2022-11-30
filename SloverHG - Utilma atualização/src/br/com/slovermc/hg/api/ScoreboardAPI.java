package br.com.slovermc.hg.api;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ScoreboardAPI {

	private Scoreboard scoreboard;

	private String title;
	private Map<String, Integer> scores;
	private List<Team> teams;

	public ScoreboardAPI(String title) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.title = title;
		this.scores = Maps.newLinkedHashMap();
		this.teams = Lists.newArrayList();
	}

	public void setLine(String text) {
		add(text, null);
	}

	public void add(String text, Integer score) {
		Preconditions.checkArgument(text.length() < 500, "text cannot be over 500 characters in length");
		text = fixDuplicates(text);
		scores.put(text, score);
	}

	private String fixDuplicates(String text) {
		while (scores.containsKey(text))
			text += "§r";
		if (text.length() > 500)
			text = text.substring(0, 499);
		return text;
	}

	private Map.Entry<Team, String> createTeam(String text) {
		String result = "";
		if (text.length() <= 16)
			return new AbstractMap.SimpleEntry<>(null, text);
		Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
		Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
		team.setPrefix(iterator.next());
		result = iterator.next();
		if (text.length() > 32)
			team.setSuffix(iterator.next());
		teams.add(team);
		return new AbstractMap.SimpleEntry<>(team, result);
	}

	@SuppressWarnings("deprecation")
	public void build() {
		Objective obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title),
				"dummy");
		obj.setDisplayName(title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		int index = scores.size();

		for (Map.Entry<String, Integer> text : scores.entrySet()) {
			Map.Entry<Team, String> team = createTeam(text.getKey());
			Integer score = text.getValue() != null ? text.getValue() : index;
			// Modification start
			final Map.Entry<Team, String> teamf = team;
			OfflinePlayer player = new OfflinePlayer() {
				public void setOp(boolean arg0) {
				}

				public void setWhitelisted(boolean arg0) {
				}

				public void setBanned(boolean arg0) {
				}

				public Map<String, Object> serialize() {
					return null;
				}

				public boolean isOp() {
					return false;
				}

				public boolean isWhitelisted() {
					return false;
				}

				public boolean isOnline() {
					return false;
				}

				public boolean isBanned() {
					return false;
				}

				public boolean hasPlayedBefore() {
					return false;
				}

				public UUID getUniqueId() {
					return null;
				}

				public Player getPlayer() {
					return null;
				}

				public String getName() {
					return teamf.getValue();
				}

				public long getLastPlayed() {
					return 0;
				}

				public long getFirstPlayed() {
					return 0;
				}

				public Location getBedSpawnLocation() {
					return null;
				}
			};
			// Modification end
			if (team.getKey() != null)
				team.getKey().addPlayer(player);
			obj.getScore(player).setScore(score);
			index -= 1;
		}
	}

	public Team registerNewTeam(String team) {
		return scoreboard.registerNewTeam(team);
	}

	public Objective registerNewObjective(String a1, String a2) {
		return scoreboard.registerNewObjective(a1, a2);
	}

	public void reset() {
		title = null;
		scores.clear();
		for (Team t : teams)
			t.unregister();
		teams.clear();
	}

	public void clearScore() {
		getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void send(Player... players) {
		for (Player p : players)
			p.setScoreboard(scoreboard);
	}

}