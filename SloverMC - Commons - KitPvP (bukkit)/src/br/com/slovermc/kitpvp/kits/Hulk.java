package br.com.slovermc.kitpvp.kits;

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

public final class Hulk implements Listener {
	
	public static final void setHulkKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Hulk");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.SADDLE, "§b§lHulk", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lHulk");
		TittleAPI.sendTittle(bp, "§bKit Hulk");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteractEntityEvent(final PlayerInteractEntityEvent e) {
		final Player p = e.getPlayer();
		if ((e.getRightClicked() instanceof Player)) {
			Player d = (Player) e.getRightClicked();
			if (KitAPI.getKit(p) == "Hulk") {
				if (KitAPI.getKit(d) == "Hulk") {
					p.sendMessage("§3§lHULK CONFLITO§f Este jogador tambem possui o kit §3§lhulk§f!");
					return;
				}
				if (CooldownAPI.Cooldown.containsKey(p.getName())) {
					p.sendMessage("§3§lHULK§f Voce esta em cooldown de §c§l" + CooldownAPI.getCooldown(p));
					return;
				}
				if (p.getPassenger() == null && p.getItemInHand().getType() == Material.SADDLE) {
					p.setPassenger(d);
					d.sendMessage("§3§lHULK§f Voce foi pego pelo §3§l" + p.getName() + "§f aperte shift para sair!");
					CooldownAPI.runCooldown(p, 17);
				} else {
					p.sendMessage("§3§lHULK§f Voce ja pegou alguem!");
				}
			}
		}
	}
}
