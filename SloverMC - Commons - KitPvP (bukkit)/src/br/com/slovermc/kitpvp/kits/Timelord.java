package br.com.slovermc.kitpvp.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class Timelord implements Listener {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<String> frozed = new ArrayList();
	
	public static final void setThorKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Timelord");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.WATCH, "§b§lTimelord", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lTimelord");
		TittleAPI.sendTittle(bp, "§bKit Timelord");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteractTimeLord(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((p.getItemInHand().getType() == Material.WATCH)
				&& ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
			if (KitAPI.getKit(p) == "Timelord") {
				if (CooldownAPI.Cooldown.containsKey(p.getName())) {
					p.sendMessage("§3§lTIMELORD§f Voce esta em cooldown de §c§l" + CooldownAPI.getCooldown(p));
					return;
				}
			}
			if (KitAPI.getKit(p) == "Timelord") {

				List<Entity> ne = e.getPlayer().getNearbyEntities(6.0D, 6.0D, 6.0D);
				for (Entity t : ne) {
					if ((t instanceof Player)) {
						final Player ta = (Player) t;
						frozed.add(((Player) t).getName());
						((Player) t).sendMessage("§3§lTIMELORD§f Voce foi §b§lCONGELADO!");

						CooldownAPI.runCooldown(p, 30);
						p.sendMessage("§3§lTIMELORD§f Voce §b§lCONGELOU§f jogadores a §9§l6 BLOCOS§f de distancia!");

						Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
							public void run() {
								frozed.remove(ta.getName());
							}
						}, 7 * 20);
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onMove(final PlayerMoveEvent e) {
		if (frozed.contains(e.getPlayer().getName())) {
			e.getPlayer().teleport(e.getPlayer());
		}
	}
}
