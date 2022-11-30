package br.com.slovermc.ycommons.api.mysql.functions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.slovermc.ycommons.api.mysql.connection.MySQL;

public class Server_IpAddress {

	public Server_IpAddress() {
		
	}
	
	private final String mIpBanTable = "SloverIPS";

	public void table() {
		if (MySQL.connection == null) {
			System.out.println("CONEXAO COM MYSQL NULA");
			return;
		}
		try {
			PreparedStatement p = MySQL.connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS " + this.mIpBanTable + " (`IP` VARCHAR(100), `BANNEDIP` VARCHAR(100), `NICK` VARCHAR(100))");
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRegistered(String adress) {
		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM " + this.mIpBanTable + " WHERE IP= ?");
			ps.setString(1, adress);
			ResultSet rs = ps.executeQuery();
			boolean check = rs.next();
			rs.close();
			ps.close();
			return check;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void register(String name, String adress) {
		if (!isRegistered(adress)) {
			try {
				PreparedStatement ps = MySQL.connection
						.prepareStatement("INSERT INTO " + this.mIpBanTable + " (`IP`, `BANNEDIP`, `NICK`) VALUES (?, ?, ?)");
				ps.setString(1, adress);
				ps.setBoolean(2, false);
				ps.setString(3, name);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isBanned(String adress) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mIpBanTable + " WHERE IP='" + adress + "'");
			rs.next();
			return Boolean.valueOf(rs.getString("BANNEDIP"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getIpBanNickName(String adress) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mIpBanTable + " WHERE IP='" + adress + "'");
			rs.next();
			return String.valueOf(rs.getString("NICK"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Ninguem";
	}
	
	public void setIpBanned(String name) {
		try {
			MySQL.connection.createStatement()
					.executeUpdate("UPDATE " + this.mIpBanTable + " SET `BANNEDIP`='" + true + "' WHERE `NICK`='" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setIpUnbanned(String name) {
		try {
			MySQL.connection.createStatement()
					.executeUpdate("UPDATE " + this.mIpBanTable + " SET `BANNEDIP`='" + false + "' WHERE `NICK`='" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
