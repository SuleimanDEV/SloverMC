package br.com.slovermc.ycommons.api.mysql.functions;

public class MySQLFunctions {
	
	public static final void createTables() {
		getAccounts().table();
		getBans().table();
		getIpAddress().table();
	}
	
	public static void registerOfflinePlayer(String name) {
		getAccounts().register(name, "Nenhum");
		getBans().register(name);
	}
	
	/*
	 * Return data Bans MySQL
	 * 
	 */
	public static final Server_Punish getBans() {
		return new Server_Punish();
	}
	
	/*
	 * Return data Accounts MySQL
	 * 
	 */
	public static final Server_Accounts getAccounts() {
		return new Server_Accounts();
	}
	
	/*
	 * Return data IpAddress MySQL
	 * 
	 */
	public static final Server_IpAddress getIpAddress() {
		return new Server_IpAddress();
	}
}
