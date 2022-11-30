package br.com.slovermc.lobby.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class MySQL_Settings {
	
	public MySQL_Settings() {
	}
	
	public void newTable() {
		try {
			PreparedStatement p = MySQL_Connection.connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS SPlayers (UUID VARCHAR(100), NICK VARCHAR(100), XP INT(100), MOEDAS INT(100))");
			p.executeUpdate();
			p.close();
			Bukkit.getConsoleSender().sendMessage("§aVERIFICANDO §fEstamos verificando a inicialização deste código.");
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao criar a tabela na MySQL");
			e.printStackTrace();
		}
	}
	
	public void registerPlayer(Player p) {
		try {
			PreparedStatement ps = MySQL_Connection.connection.prepareStatement(
					"INSERT INTO SPlayers (UUID, NICK, XP, MOEDAS) VALUES (?, ?, ?, ?)");
			ps.setString(1, p.getUniqueId().toString());
			ps.setString(2, p.getName());
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao registrar dados do player " + p.getName() + " na MySQL");
			e.printStackTrace();
		}
	}
	
	public void registerPlayer(OfflinePlayer p) {
		try {
			PreparedStatement ps = MySQL_Connection.connection.prepareStatement(
					"INSERT INTO SPlayers (UUID, NICK, XP, MOEDAS) VALUES (?, ?, ?, ?)");
			ps.setString(1, p.getUniqueId().toString());
			ps.setString(2, p.getName());
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.close();
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao registrar dados do player " + p.getName() + " na MySQL");
			e.printStackTrace();
		}
	}
	
	public boolean verifyPlayerRegister(UUID uuid) {
		try {
			PreparedStatement ps = MySQL_Connection.connection.prepareStatement(
					"SELECT * FROM SPlayers WHERE UUID= ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			boolean player = rs.next();
			rs.close();
			ps.close();
			return player;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getXp(Player p) {
		try {
			ResultSet rs = MySQL_Connection.connection.createStatement().executeQuery(
					"SELECT * FROM SPlayers WHERE UUID='" + p.getUniqueId().toString() + "'");
			rs.next();
			return rs.getInt("XP");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addXP(Player p, int xp) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET XP='" + (getXp(p) + xp) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeXP(Player p, int xp) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET XP='" + (getXp(p) - xp) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getMoney(Player p) {
		try {
			ResultSet rs = MySQL_Connection.connection.createStatement().executeQuery(
					"SELECT * FROM SPlayers WHERE UUID='" + p.getUniqueId().toString() + "'");
			rs.next();
			return rs.getInt("MOEDAS");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addMoney(Player p, int money) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET MOEDAS='" + (getMoney(p) + money) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeMoney(Player p, int money) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET MOEDAS='" + (getMoney(p) - money) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getMoney(OfflinePlayer p) {
		try {
			ResultSet rs = MySQL_Connection.connection.createStatement().executeQuery(
					"SELECT * FROM SPlayers WHERE UUID='" + p.getUniqueId().toString() + "'");
			rs.next();
			return rs.getInt("MOEDAS");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addMoney(OfflinePlayer p, int money) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET MOEDAS='" + (getMoney(p) + money) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeMoney(OfflinePlayer p, int money) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET MOEDAS='" + (getMoney(p) - money) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getXp(OfflinePlayer p) {
		try {
			ResultSet rs = MySQL_Connection.connection.createStatement().executeQuery(
					"SELECT * FROM SPlayers WHERE UUID='" + p.getUniqueId().toString() + "'");
			rs.next();
			return rs.getInt("XP");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void addXP(OfflinePlayer p, int xp) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET XP='" + (getXp(p) + xp) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
			
		}
	}
	
	public void removeXP(OfflinePlayer p, int xp) {
		try {
			MySQL_Connection.connection.createStatement().executeUpdate(
					"UPDATE SPlayers SET XP='" + (getXp(p) - xp) + "' WHERE UUID='" + p.getUniqueId().toString() + "'");
		}catch (Exception e) {
		}
	}
}