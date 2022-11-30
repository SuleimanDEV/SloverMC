package br.com.slovermc.ycommons.api.mysql.functions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.slovermc.ycommons.api.mysql.connection.MySQL;

public class Server_Accounts {

	public Server_Accounts() {
		
	}
	
	private String mTable = "SloverAccounts";
	
	public void table() {
		if (!MySQL.isConnected()) {
			return;
		}
		try {
			PreparedStatement p = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.mTable
					+ " (`NICK` VARCHAR(100), `GROUP` VARCHAR(100), `XP` INT(100)," + " `MONEY` INT(100),"
					+ " `DOUBLEXP` INT(100), `BOX` INT(100), `IP` VARCHAR(100), `LENTH` VARCHAR(100))");
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRegistered(String name) {
		try {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM " + this.mTable + " WHERE NICK= ?");
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
	
	public void register(String name, String address) {
		if (!isRegistered(name)) {
			try {
				PreparedStatement ps = MySQL.connection
						.prepareStatement("INSERT INTO " + this.mTable + " (`NICK`, `GROUP`, `XP`, `MONEY`,"
								+ " `DOUBLEXP`, `BOX`, `IP`, `LENTH`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, name.toLowerCase());
				ps.setString(2, "MEMBRO");
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.setInt(5, 0);
				ps.setInt(6, 0);
				ps.setString(7, address);
				ps.setString(8, "-1");
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getGroup(String name) {
		if (isRegistered(name)) {
			try {
				ResultSet rs = MySQL.connection.createStatement()
						.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
				rs.next();
				return String.valueOf(rs.getString("GROUP"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "MEMBRO";
	}

	public long getLenth(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Long.valueOf(rs.getString("LENTH"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (long) -1;
	}
	
	public String getIp(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return String.valueOf(rs.getString("IP"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Nenhum";
	}

	public int getXp(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Integer.valueOf(rs.getString("XP"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getMoney(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Integer.valueOf(rs.getString("MONEY"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getDoubleXp(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Integer.valueOf(rs.getString("DOUBLEXP"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getBox(String name) {
		try {
			ResultSet rs = MySQL.connection.createStatement()
					.executeQuery("SELECT * FROM " + this.mTable + " WHERE NICK='" + name.toLowerCase() + "'");
			rs.next();
			return Integer.valueOf(rs.getString("BOX"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void setGroup(String name, String group, long lenth) {
		boolean isPermanent = false;
		if (lenth == -1) {
			isPermanent = true;
		}
		try {
			MySQL.connection.createStatement().executeUpdate("UPDATE " + this.mTable + " SET `GROUP`='" + group
					+ "', `LENTH`='" + (isPermanent ? "-1" : lenth) + "' WHERE `NICK`='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setXp(String name, int amount) {
		try {
			MySQL.connection.createStatement().executeUpdate("UPDATE " + this.mTable + " SET `XP`='"
					+ (getXp(name) + amount) + "' WHERE NICK='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setIp(String name, String address) {
		try {
			MySQL.connection.createStatement().executeUpdate("UPDATE " + this.mTable + " SET `IP`='"
					+ address + "' WHERE NICK='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setMoney(String name, int amount) {
		try {
			MySQL.connection.createStatement().executeUpdate("UPDATE " + this.mTable + " SET `MONEY`='"
					+ (getMoney(name) + amount) + "' WHERE NICK='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDoubleXp(String name, int amount) {
		try {
			MySQL.connection.createStatement().executeUpdate("UPDATE " + this.mTable + " SET `DOUBLEXP`='"
					+ (getDoubleXp(name) + amount) + "' WHERE NICK='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setBox(String name, int amount) {
		try {
			MySQL.connection.createStatement().executeUpdate("UPDATE " + this.mTable + " SET `BOX`='"
					+ (getBox(name) + amount) + "' WHERE NICK='" + name.toLowerCase() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
