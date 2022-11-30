package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class Minato implements Listener {

	public static final void setMinatoKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Minato");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.BOW, "§3§lMinato", 1, (byte) 0));
		bp.getInventory().setItem(2, SpawnItens.newItem(Material.ARROW, "§6§lKunai", 10, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lMinato");
		TittleAPI.sendTittle(bp, "§bKit Minato");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onYellowFlashTeleport(final ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			Arrow flash = (Arrow) e.getEntity();
			Entity shooter = flash.getShooter();
			if (shooter instanceof Player) {
				final Player bp = (Player) shooter;
				if (KitAPI.getKit(bp) == "Minato") {
					bp.teleport(flash.getLocation());
					bp.getWorld().playSound(bp.getLocation(), Sound.AMBIENCE_THUNDER, 2.0F, 1.0F);
					bp.sendMessage("§3§lMINATO§f Teleportado!");
					for (Entity s : bp.getNearbyEntities(4.0, 4.0, 4.0)) {
						if (s instanceof Player) {
							final Player minated = (Player) s;
							minated.damage(6.0D, bp);
							minated.sendMessage("§3§lMINATO§f Algum player §b§lTELEPORTOU§f aqui perto!");
						}
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public final void onYellowFlash(final EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player bp = (Player) e.getEntity();
			if (e.getProjectile() instanceof Arrow) {
				if (KitAPI.getKit(bp) == "Minato") {
					if (CooldownAPI.Cooldown.containsKey(bp.getName())) {
						e.getProjectile().remove();
						e.setCancelled(true);
						bp.sendMessage("§3§lMINATO§f Voce esta em cooldown de §c§l" + CooldownAPI.getCooldown(bp));
						return;
					}
					CooldownAPI.runCooldown(bp, 25);
					if (X1WarpListener.itemsInInventory(bp.getInventory(), new Material[] { Material.ARROW }) < 10) {
						bp.getInventory().addItem(SpawnItens.newItem(Material.ARROW, "§6§lKunai", 1, (byte) 0));
					}
				}
			}
		}
	}
}
