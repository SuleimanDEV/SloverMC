package br.com.slovermc.kitpvp.api;

import java.util.HashMap;

import org.bukkit.entity.Player;

public final class WarpAPI {

	public static final HashMap<Player, String> warp = new HashMap<>();
	
	public static final void setWarp(final Player bp, final String warpName) {
		warp.put(bp, warpName);
	}
	
	public static final String getWarp(final Player bp) {
		return warp.get(bp);
	}
}
