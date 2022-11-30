package br.com.slovermc.ycommons.api.mysql;

import br.com.slovermc.ycommons.api.mysql.connection.MySQL;

public class MySQLAPI {

	public static void connect() {
		if (MySQL.connection != null) {
			return;
		} else {
			new MySQL("localhost", "hg", "root", "");
		}
	}
	
	public static void disconnect() {
		new MySQL();
	}
}
