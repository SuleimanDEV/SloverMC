package br.com.slovermc.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;

public final class Anchor implements Listener {
	
	public static final void setAnchorKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Anchor");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lAnchor");
		TittleAPI.sendTittle(bp, "§bKit Anchor");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onAnchorHit(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		Player p2 = (Player) e.getDamager();
		if (KitAPI.getKit(p) == ("Anchor")) {
			p2.setVelocity(new Vector());
			p.setVelocity(new Vector());
			p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) BukkitMain.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p2.setVelocity(new Vector());
					p.setVelocity(new Vector());
				}
			}, 1L);
		}
		if (KitAPI.getKit(p2) == ("Anchor")) {
			p2.playSound(p2.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			p2.setVelocity(new Vector());
			p.setVelocity(new Vector());
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) BukkitMain.getPlugin(), (Runnable) new Runnable() {
				@Override
				public void run() {
					p2.setVelocity(new Vector());
					p.setVelocity(new Vector());
				}
			}, 1L);
		}
	}
}
