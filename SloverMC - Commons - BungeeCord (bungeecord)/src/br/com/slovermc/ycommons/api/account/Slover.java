package br.com.slovermc.ycommons.api.account;

import br.com.slovermc.ycommons.api.mysql.connection.MySQL;

public class Slover {

	public static SloverPlayer getSloverPlayer(String name) {
		return new SloverPlayer(name);
	}
	
	public static boolean isDatabaseConnected() {
		return MySQL.isConnected();
	}
}
