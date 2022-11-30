package br.com.slovermc.ycommons.api.permission.bungee;

import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerGroup {

	public static boolean isGroup(SloverPlayer lp, String group) {
		return lp.getGroupName().contains(group.toUpperCase());
	}
	
	public static boolean isGroup(ProxiedPlayer p, String group) {
		return MySQLFunctions.getAccounts().getGroup(p.getName()).contains(group.toUpperCase());
	}
}
