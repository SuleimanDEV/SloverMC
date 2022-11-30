package br.com.slovermc.hg.api;

import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;

public class StatsAPI {

	private static StatsAPI instance = new StatsAPI();

	public static StatsAPI getInstance() {
		return instance;
	}

	public void createAccount(Player p, String rank) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.createAccount(p, rank);
			return;
		}

		if (!ConfigAPI.getInstance().getStats().contains("jogadores." + p.getUniqueId().toString())) {
			ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".nick", p.getName());
			ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".kills", 0);
			ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".deaths", 0);
			ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".wins", 0);
			ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".coins", 0);
			ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".rank", rank);
			ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
		}
	}

	public void addKills(Player p, int kills) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.addKills(p.getUniqueId().toString(), kills);
			return;
		}

		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".kills",
				getKills(p) + kills);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void addDeaths(Player p, int mortes) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.addDeaths(p.getUniqueId().toString(), mortes);
			return;
		}

		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".deaths",
				getDeaths(p) + mortes);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void addWins(Player player, int wins) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.addWins(player.getUniqueId().toString(), wins);
			return;
		}

		ConfigAPI.getInstance().getStats().set("jogadores." + player.getUniqueId().toString() + ".wins",
				getWins(player) + wins);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void addCoins(Player p, int coins) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.addCoins(p.getUniqueId().toString(), coins);
			return;
		}

		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".coins",
				getCoins(p) + coins);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void removeKills(Player p, int kills) {
		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".kills",
				getKills(p) - kills);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void removeDeaths(Player p, int mortes) {
		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".deaths",
				getDeaths(p) - mortes);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void removeCoins(Player p, int coins) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.removeCoins(p.getUniqueId().toString(), coins);
			return;
		}

		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".coins",
				getCoins(p) - coins);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public void setRank(Player p, String rank) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			BukkitMain.getPlugin().mysql.setRank(p.getUniqueId().toString(), rank);
			return;
		}

		ConfigAPI.getInstance().getStats().set("jogadores." + p.getUniqueId().toString() + ".rank", rank);
		ConfigAPI.getInstance().save("status", ConfigAPI.getInstance().getStats());
	}

	public Integer getKills(Player p) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			return BukkitMain.getPlugin().mysql.getKills(p.getUniqueId().toString());
		}

		return ConfigAPI.getInstance().getStats().get("jogadores." + p.getUniqueId().toString() + ".kills") != null
				? ConfigAPI.getInstance().getStats().getInt("jogadores." + p.getUniqueId().toString() + ".kills") : 0;
	}

	public Integer getDeaths(Player p) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			return BukkitMain.getPlugin().mysql.getDeaths(p.getUniqueId().toString());
		}

		return ConfigAPI.getInstance().getStats().get("jogadores." + p.getUniqueId().toString() + ".deaths") != null
				? ConfigAPI.getInstance().getStats().getInt("jogadores." + p.getUniqueId().toString() + ".deaths") : 0;
	}

	public Integer getWins(Player player) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			return BukkitMain.getPlugin().mysql.getWins(player.getUniqueId().toString());
		}

		return ConfigAPI.getInstance().getStats().get("jogadores." + player.getUniqueId().toString() + ".wins") != null
				? ConfigAPI.getInstance().getStats().getInt("jogadores." + player.getUniqueId().toString() + ".wins")
				: 0;
	}

	public Integer getCoins(Player p) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			return BukkitMain.getPlugin().mysql.getCoins(p.getUniqueId().toString());
		}

		return ConfigAPI.getInstance().getStats().get("jogadores." + p.getUniqueId().toString() + ".coins") != null
				? ConfigAPI.getInstance().getStats().getInt("jogadores." + p.getUniqueId().toString() + ".coins") : 0;
	}

	public String getRank(Player p) {
		if (BukkitMain.getPlugin().getConfig().getBoolean("Mysql.ativar")) {
			return BukkitMain.getPlugin().mysql.getRank(p.getUniqueId().toString());
		}

		return ConfigAPI.getInstance().getStats().get("jogadores." + p.getUniqueId().toString() + ".rank") != null
				? ConfigAPI.getInstance().getStats().getString("jogadores." + p.getUniqueId().toString() + ".rank").replace("&", "§")
				: "Unranked";
	}
}
