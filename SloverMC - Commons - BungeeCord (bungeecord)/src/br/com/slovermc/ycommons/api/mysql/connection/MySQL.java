package br.com.slovermc.ycommons.api.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private String ip;
	private String database;
	private String user;
	private String pass;
	
	public static Connection connection;
	
	public static boolean isConnected() {
		return connection != null;
	}

	public MySQL(String mIp, String mData, String mUser, String mPass) {
		this.ip = mIp;
		this.database = mData;
		this.user = mUser;
		this.pass = mPass;
		if (connection != null) {
			return;
		}
		else {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + this.ip + ":" + "3306" + "/"
						+ this.database + "?autoReconnect=true", this.user, this.pass);
				System.out.println("[MySQL] yCommons: Conectada com sucesso");
			} catch (SQLException e) {
				System.out.println("[MySQL] yCommons: Falha na Conexao");
				e.printStackTrace();
			}
		}
	}
	
	public MySQL() {
		if (connection == null) {
			return;
		}
		else {
			try {
				connection.close();
				System.out.println("CONEXAO COM MYSQL [DESATIVADA]");
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
}
