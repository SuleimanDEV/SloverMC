package br.com.slovermc.kitpvp.kits;

import org.bukkit.entity.Player;

public final class KitResetor {

	public static final void resetPlayerKit(final Player bp) {
		if (Ajnin.ajnin.containsKey(bp)) {
			Ajnin.ajnin.remove(bp);
		}
		if (Ninja.ninja.containsKey(bp)) {
			Ninja.ninja.remove(bp);
		}
	}
}
