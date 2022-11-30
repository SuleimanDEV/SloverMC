package br.com.slovermc.ycommons.api.mysql.functions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import br.com.slovermc.ycommons.api.mysql.connection.MySQL;

public class Server_Punish {

	public Server_Punish() {
		
	}
	
	private final String mBanTable = "SloverBans";
	
	public void table() {
		if (!MySQL.isConnected()) {
			return;
		}
		try {
			PreparedStatement p = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.mBanTable
					+ " (`NICK` VARCHAR(100), `BANNED` VARCHAR(100), `MOTIVE` VARCHAR(100), `AUTHOR` VARCHAR(100),"
					+ " `LENTH` VARCHAR(100), `MUTED` VARCHAR(100), `MUTEDMOTIVE` VARCHAR(100), `MUTEDAUTHOR` VARCHAR(100), `MUTEDLENTH` VARCHAR(100), `BANNEDDATE` VARCHAR(100), `ID` INT(100))");
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRegistered(String name) {
		try {
			PreparedStatement ps = MySQL.connection
					.prepareStatement("SELECT * FROM " + this.mBanTable + " WHERE NICK= ?");
			ps.setString(1, name.toLowerCase());
			ResultSet rs = ps.executeQuery();
			boolean player = rs.next();
			rs.close();
			ps.close();
			return player;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void register(String name) {
		if (!isRegistered(name)) {
			try {
				PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO " + this.mBanTable
						+ " (`NICK`, `BANNED`, `MOTIVE`, `AUTHOR`, `LENTH`, `MUTED`, `MUTEDMOTIVE`, `MUTEDLENTH`, `MUTEDAUTHOR`, `BANNEDDATE`, `ID`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, name.toLowerCase());
				ps.setBoolean(2, false);
				ps.setString(3, "Nenhum");
				ps.setString(4, "Ninguem");
				ps.setString(5, "-1");
				ps.setBoolean(6, false);
				ps.setString(7, "Nenhum");
				ps.setString(8, "-1");
				ps.setString(9, "Ninguem");
				ps.setString(10, "None");
				ps.setInt(11, 0);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isBanned(String name) {
		if (!isRegistered(name)) {
			return false;
		}
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Boolean.valueOf(rs.getString("BANNED"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isMuted(String name) {
		if (!isRegistered(name)) {
			return false;
		}
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Boolean.valueOf(rs.getString("MUTED"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void setUnbanned(String name) {
		try {
			MySQL.connection.createStatement()
					.executeUpdate("UPDATE " + this.mBanTable + " SET `MOTIVE`='" + "Nenhum" + "', `BANNED`='" + false
							+ "', `LENTH`='" + "-1" + "', `AUTHOR`='" + "Ninguem" + "' WHERE `NICK`='"
							+ name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLFunctions.getIpAddress().setIpUnbanned(name);
	}

	public void setUnmuted(String name) {
		try {
			MySQL.connection.createStatement()
					.executeUpdate("UPDATE " + this.mBanTable + " SET `MUTEDMOTIVE`='" + "Nenhum" + "', `MUTED`='"
							+ false + "', `LENTH`='" + "-1" + "', `MUTEDAUTHOR`='" + "Ninguem" + "' WHERE `NICK`='"
							+ name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		Calendar calendar = GregorianCalendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(calendar.getTime());
	}
	
	public void setBanned(String name, String motive, String author, long lenth, boolean ip) {
		boolean isPermanent = false;
		if (lenth == -1) {
			isPermanent = true;
		}
		try {
			MySQL.connection.createStatement()
					.executeUpdate("UPDATE " + this.mBanTable + " SET `MOTIVE`='" + motive + "', `BANNED`='" + true
							+ "', `LENTH`='" + (isPermanent ? "-1" : lenth) + "', `BANNEDDATE`='" + getDate() + "', `AUTHOR`='" + author
							+ "' WHERE `NICK`='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (ip) {
			MySQLFunctions.getIpAddress().setIpBanned(name);
		}
	}
	
	public void setMuted(String name, String motive, String author, long lenth) {
		boolean isPermanent = false;
		if (lenth == -1) {
			isPermanent = true;
		}
		try {
			MySQL.connection.createStatement()
					.executeUpdate("UPDATE " + this.mBanTable + " SET `MUTEDMOTIVE`='" + motive + "', `MUTED`='" + true
							+ "', `MUTEDLENTH`='" + (isPermanent ? "-1" : lenth) + "', `MUTEDAUTHOR`='" + author
							+ "' WHERE `NICK`='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public long getBanLenth(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Long.valueOf(rs.getString("LENTH"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (long) -1;
	}
	
	public long getMuteLenth(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Long.valueOf(rs.getString("MUTEDLENTH"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (long) -1;
	}
	
	public String getBanAuthor(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return String.valueOf(rs.getString("AUTHOR"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Ninguem";
	}

	public String getMuteAuthor(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return String.valueOf(rs.getString("MUTEDAUTHOR"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Ninguem";
	}

	public String getBanMotive(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return String.valueOf(rs.getString("MOTIVE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Nenhum";
	}

	public String getMuteMotive(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return String.valueOf(rs.getString("MUTEDMOTIVE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Nenhum";
	}
	
	public String getBanDate(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mBanTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return String.valueOf(rs.getString("BANNEDDATE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "None";
	}
}
