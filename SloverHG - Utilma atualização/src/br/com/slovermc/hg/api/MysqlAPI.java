package br.com.slovermc.hg.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class MysqlAPI {

	private String url_base, host, name, username, password, table;
	private Connection connection;

	public MysqlAPI(String url_base, String host, String name, String username, String password, String table) {
		this.url_base = url_base;
		this.host = host;
		this.name = name;
		this.username = username;
		this.password = password;
		this.table = table;
	}

	public void connection() {
		if (!isConnected()) {
			try {
				connection = DriverManager.getConnection(url_base + host + "/" + name, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deconnection() {
		if (isConnected()) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isConnected() {
		try {
			if ((connection == null) || (connection.isClosed()) || (!connection.isValid(5))) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Connection getConnection() {
		return connection;
	}

	public void createAccount(Player p, String rank) {
		try {

			PreparedStatement sts = getConnection().prepareStatement("SELECT coins FROM " + table + " WHERE uuid = ?");
			sts.setString(1, p.getUniqueId().toString());
			ResultSet rs = sts.executeQuery();
			if (!rs.next()) {
				sts.close();

				PreparedStatement sts2 = getConnection().prepareStatement(
						"INSERT INTO " + table + "(uuid, kills, deaths, wins, coins, rank) VALUES (?, ?, ?, ?, ?, ?)");
				sts2.setString(1, p.getUniqueId().toString());
				sts2.setInt(2, 0);
				sts2.setInt(3, 0);
				sts2.setInt(4, 0);
				sts2.setInt(5, 0);
				sts2.setString(6, rank);
				sts2.executeUpdate();
				sts2.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addKills(String uuid, int kills) {
		try {

			PreparedStatement sts = getConnection()
					.prepareStatement("UPDATE " + table + " SET kills = kills + ? WHERE uuid = ?");
			sts.setInt(1, kills);
			sts.setString(2, uuid);
			sts.executeUpdate();
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addDeaths(String uuid, int deaths) {
		try {
			PreparedStatement sts = getConnection()
					.prepareStatement("UPDATE " + table + " SET deaths = deaths + ? WHERE uuid = ?");
			sts.setInt(1, deaths);
			sts.setString(2, uuid);
			sts.executeUpdate();
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addWins(String uuid, int wins) {
		try {
			PreparedStatement sts = getConnection()
					.prepareStatement("UPDATE " + table + " SET wins = wins + ? WHERE uuid = ?");
			sts.setInt(1, wins);
			sts.setString(2, uuid);
			sts.executeUpdate();
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addCoins(String uuid, int coins) {
		try {
			PreparedStatement sts = getConnection()
					.prepareStatement("UPDATE " + table + " SET coins = coins + ? WHERE uuid = ?");
			sts.setInt(1, coins);
			sts.setString(2, uuid);
			sts.executeUpdate();
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeCoins(String uuid, int coins) {
		try {
			PreparedStatement sts = getConnection()
					.prepareStatement("UPDATE " + table + " SET coins = coins - ? WHERE uuid = ?");
			sts.setInt(1, coins);
			sts.setString(2, uuid);
			sts.executeUpdate();
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setRank(String uuid, String rank) {
		try {
			PreparedStatement sts = getConnection()
					.prepareStatement("UPDATE " + table + " SET rank = ? WHERE uuid = ?");
			sts.setString(1, rank);
			sts.setString(2, uuid);
			sts.executeUpdate();
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getKills(String uuid) {
		int kills = 0;

		try {
			PreparedStatement sts = getConnection().prepareStatement("SELECT kills FROM " + table + " WHERE uuid = ?");
			sts.setString(1, uuid);
			ResultSet rs = sts.executeQuery();
			if (!rs.next()) {
				return kills;
			}
			kills = rs.getInt("kills");
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kills;
	}

	public Integer getDeaths(String uuid) {
		int deaths = 0;

		try {
			PreparedStatement sts = getConnection().prepareStatement("SELECT deaths FROM " + table + " WHERE uuid = ?");
			sts.setString(1, uuid);
			ResultSet rs = sts.executeQuery();
			if (!rs.next()) {
				return deaths;
			}
			deaths = rs.getInt("deaths");
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deaths;
	}

	public Integer getWins(String uuid) {
		int wins = 0;

		try {
			PreparedStatement sts = getConnection().prepareStatement("SELECT wins FROM " + table + " WHERE uuid = ?");
			sts.setString(1, uuid);
			ResultSet rs = sts.executeQuery();
			if (!rs.next()) {
				return wins;
			}
			wins = rs.getInt("wins");
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wins;
	}

	public Integer getCoins(String uuid) {
		int coins = 0;

		try {
			PreparedStatement sts = getConnection().prepareStatement("SELECT coins FROM " + table + " WHERE uuid = ?");
			sts.setString(1, uuid);
			ResultSet rs = sts.executeQuery();
			if (!rs.next()) {
				return coins;
			}
			coins = rs.getInt("coins");
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coins;
	}

	public String getRank(String uuid) {
		String rank = "S/ Rank";

		try {
			PreparedStatement sts = getConnection().prepareStatement("SELECT rank FROM " + table + " WHERE uuid = ?");
			sts.setString(1, uuid);
			ResultSet rs = sts.executeQuery();
			if (!rs.next()) {
				return rank;
			}
			rank = rs.getString("rank");
			sts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rank;
	}
}
