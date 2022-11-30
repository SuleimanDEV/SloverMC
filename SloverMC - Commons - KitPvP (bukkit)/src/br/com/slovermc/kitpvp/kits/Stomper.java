package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;

public final class Stomper implements Listener {

	public static final void setStomperKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Stomper");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lStomper");
		TittleAPI.sendTittle(bp, "§bKit Stomper");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onStomper(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (KitAPI.getKit(p) == "Stomper" && e.getCause() == DamageCause.FALL) {
				e.setDamage(4.0D);
				if (e.getDamage() > 6) {
					for (Entity s : p.getNearbyEntities(3.5, 1, 3.5)) {
						if (s instanceof Player) {
							final Player stomped = (Player) s;
							e.setDamage(4.0D);
							stomped.sendMessage((KitAPI.getKit(stomped) == "AntiStomper"
									? "§3§lANTISTOMPER§f Voce recebeu dano reduzido do §3§l" + p.getName()
									: "§3§lSTOMPER§f Voce foi §3§lstompado§f pelo " + p.getName()));
							p.sendMessage("§3§lSTOMPER§f Voce §3§lstompou§f o player " + stomped.getName());
							p.getWorld().playSound(p.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.0F);
							if (!stomped.isSneaking() && KitAPI.getKit(stomped) != "AntiStomper") {
								stomped.damage(999999999, p);
							} else {
								stomped.damage(4, p);
							}
						}
					}
				}
			}
			return;
		}
	}
}
