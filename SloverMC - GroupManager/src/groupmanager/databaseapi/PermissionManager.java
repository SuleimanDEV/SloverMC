package groupmanager.databaseapi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PermissionManager {
	
	public PermissionManager() {
	}
	
	public void newTable() {
		try {
			PreparedStatement p = SqlConnection.connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS PGroups (NICK VARCHAR(100), Grupo VARCHAR(100), Time VARCHAR(100))");
			p.executeUpdate();
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cAn error ocurried while trying to create tables on mysql!");
			e.printStackTrace();
		}
	}
	
	public void newPlayer(Player p) {
		try {
			PreparedStatement ps = SqlConnection.connection.prepareStatement(
					"INSERT INTO PGroups (NICK, Grupo, Time) VALUES (?, ?, ?)");
			ps.setString(1, p.getName().toLowerCase());
			ps.setString(2, "MEMBRO");
			ps.setString(3, "-1");
			ps.executeUpdate();
			ps.close();
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cAn error ocurried while trying to save " + p.getName() + " data!");
			e.printStackTrace();
		}
	}
	
	public void newPlayer(OfflinePlayer p) {
		try {
			PreparedStatement ps = SqlConnection.connection.prepareStatement(
					"INSERT INTO PGroups (NICK, Grupo) VALUES (?, ?, ?)");
			ps.setString(1, p.getName().toLowerCase());
			ps.setString(2, "MEMBRO");
			ps.setString(3, "-1");
			ps.executeUpdate();
			ps.close();
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cAn error ocurried while trying to save " + p.getName() + " data!");
			e.printStackTrace();
		}
	}
	
	public boolean verifyPlayerRegister(String name) {
		try {
			PreparedStatement ps = SqlConnection.connection.prepareStatement(
					"SELECT * FROM PGroups WHERE NICK= ?");
			ps.setString(1, name.toLowerCase());
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
	
	public void setGroup(Player p, String Grupo) {
		try {
			SqlConnection.connection.createStatement().executeUpdate(
					"UPDATE PGroups SET Grupo='" + Grupo + "', Time='" + "-1" + "' WHERE NICK='" + p.getName().toLowerCase() + "'");			
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cAn error ocurried while trying to set " + p.getName() + " Grupo!");
			e.printStackTrace();
		}
	}
	
	public void setGroup(OfflinePlayer p, String Grupo) {
		try {
			SqlConnection.connection.createStatement().executeUpdate(
					"UPDATE PGroups SET Grupo='" + Grupo + "', Time='" + "-1" + "' WHERE NICK='" + p.getName().toLowerCase() + "'");			
		}catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cAn error ocurried while trying to set " + p.getName() + " Grupo!");
			e.printStackTrace();
		}
	}
	
	public String getGroup(Player p) {
		try {
			ResultSet rs = SqlConnection.connection.createStatement().executeQuery(
					"SELECT * FROM PGroups WHERE NICK='" + p.getName().toLowerCase() + "'");
			rs.next();
			return rs.getString("Grupo");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "MEMBRO";
	}
	
	public String getGroup(OfflinePlayer p) {
		try {
			ResultSet rs = SqlConnection.connection.createStatement().executeQuery(
					"SELECT * FROM PGroups WHERE NICK='" + p.getName().toLowerCase() + "'");
			rs.next();
			return rs.getString("Grupo");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "MEMBRO";
	}
	
	public void setTempGroup(Player p, String group, long tempo) {
		try {
			SqlConnection.connection.createStatement().executeUpdate(
					"UPDATE PGroups SET Grupo='" + group + "', Time='" + tempo + "' WHERE NICK='" + p.getName().toLowerCase() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Long getTime(Player p) {
		try {
			ResultSet rs = SqlConnection.connection.createStatement().executeQuery(
					"SELECT * FROM PGroups WHERE NICK='" + p.getName().toLowerCase() + "'");
			rs.next();
			return Long.valueOf(rs.getString("Time"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return (long) -1;
	}
	
	public Long getTime(OfflinePlayer p) {
		try {
			ResultSet rs = SqlConnection.connection.createStatement().executeQuery(
					"SELECT * FROM PGroups WHERE NICK='" + p.getName().toLowerCase() + "'");
			rs.next();
			return Long.valueOf(rs.getString("Time"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return (long) -1;
	}
	
	public void setTempGroup(OfflinePlayer p, String group, long tempo) {
		try {
			SqlConnection.connection.createStatement().executeUpdate(
					"UPDATE PGroups SET Grupo='" + group + "', Time='" + tempo + "' WHERE NICK='" + p.getName().toLowerCase() + "'");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}