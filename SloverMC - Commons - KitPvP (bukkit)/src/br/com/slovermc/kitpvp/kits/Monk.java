package br.com.slovermc.kitpvp.kits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class Monk implements Listener {
	
	public static final void setMonkKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Monk");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.BLAZE_ROD, "§b§lMonk", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lMonk");
		TittleAPI.sendTittle(bp, "§bKit Monk");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void monkHabilidade(PlayerInteractEntityEvent e) {
		final Player p = e.getPlayer();
		if ((e.getRightClicked() instanceof Player)) {
			Player p2 = (Player) e.getRightClicked();
			if (SpawnWarpListener.onWarpSpawnProtection.contains(p2)) {
				return;
			}

			if ((p.getItemInHand().getType() == Material.BLAZE_ROD) && (KitAPI.getKit(p) == "Monk")) {
				if (CooldownAPI.Cooldown.containsKey(p.getName())) {
					p.sendMessage("§3§lMONK§f Voce esta em cooldown de §c§l" + CooldownAPI.getCooldown(p));
					return;
				}

				int random = new Random().nextInt(p2.getInventory().getSize() - 10 + 1 + 10);

				ItemStack otheritem = p2.getInventory().getItem(random);
				ItemStack changed = p2.getItemInHand();

				p2.setItemInHand(otheritem);
				p2.getInventory().setItem(random, changed);
				p2.sendMessage("§3§lMONK§f O player §3§l" + p.getName() + "§f bagunçou seu inventario!");
				CooldownAPI.runCooldown(p, 15);
			}
		}
	}
}
