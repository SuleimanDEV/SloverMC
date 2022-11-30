package br.com.slovermc.kitpvp.api;

import java.util.HashMap;

import org.bukkit.entity.Player;

public final class KitAPI {

	public static final HashMap<Player, String> kit = new HashMap<>();
	
	public static final void setKit(final Player bp, final String kitName) {
		kit.put(bp, kitName);
	}
	
	public static final String getKit(final Player bp) {
		return kit.get(bp);
	}
}
