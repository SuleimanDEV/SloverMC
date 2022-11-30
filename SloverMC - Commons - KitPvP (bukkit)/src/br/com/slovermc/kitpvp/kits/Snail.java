package br.com.slovermc.kitpvp.kits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;

public final class Snail implements Listener {
	
	public static final void setSnailKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Snail");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lSnail");
		TittleAPI.sendTittle(bp, "§bKit Snail");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public final void onPosion(final EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			if (e.isCancelled()) {
				return;
			}
			if (KitAPI.getKit(d) == "Snail") {
				Random r = new Random();
				int rand = r.nextInt(100);
				if (rand >= 67)
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 0));
			}
		}
	}
}
