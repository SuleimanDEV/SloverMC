package br.com.slovermc.ycommons.bungee.utils;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Broadcast {

	@SuppressWarnings("deprecation")
	public static void broadcastToStaff(String permission, String toBroadcast) {
		for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
			if (online.hasPermission(permission) && !online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
				online.sendMessage(toBroadcast);
			}
		}
	}
}
