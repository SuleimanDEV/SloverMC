package br.com.slovermc.kitpvp.kits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;

public final class Switcher implements Listener {
	
	public static final void setSwitcherKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Switcher");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, new ItemStack(Material.SNOW, 64));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lSwitcher");
		TittleAPI.sendTittle(bp, "§bKit Switcher");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onSnown(final EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Snowball)) && ((e.getEntity() instanceof Player))) {
			Snowball s = (Snowball) e.getDamager();
			if ((s.getShooter() instanceof Player)) {
				Player shooter = (Player) s.getShooter();
				if (KitAPI.getKit(shooter) == "Switcher") {
					Location shooterLoc = shooter.getLocation();
					shooter.teleport(e.getEntity().getLocation());
					e.getEntity().teleport(shooterLoc);
					shooter.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 5));
					shooter.getWorld().playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.2F);
				}
			}
		}
	}
}
