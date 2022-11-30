package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class Boxer implements Listener {
	
	public static final void setBoxerKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Boxer");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lBoxer");
		TittleAPI.sendTittle(bp, "§bKit Boxer");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
	
	@EventHandler
	public final void onBoxerDamaged(final EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player) {
			final Player damaged = (Player) e.getEntity();
			if (SpawnWarpListener.onWarpSpawnProtection.contains(damaged) || FpsWarpListener.onFpsSpawnProtection.contains(damaged)) {
				return;
			}
			if (KitAPI.getKit(damaged) == "Boxer") {
				e.setDamage(e.getDamage() - 1.5D);
			}
		}
	}
}
