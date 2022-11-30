package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class Thor implements Listener {

	public static final void setThorKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Thor");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.GOLD_AXE, "§b§lThor", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lThor");
		TittleAPI.sendTittle(bp, "§bKit Thor");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public final void Interact(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((p.getItemInHand().getType() == Material.GOLD_AXE) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& (KitAPI.getKit(p) == "Thor")) {
			if (CooldownAPI.Cooldown.containsKey(p.getName())) {
				p.sendMessage("§3§lTHOR§f Voce esta em cooldown de §c§l" + CooldownAPI.getCooldown(p));
				return;
			}
			CooldownAPI.runCooldown(p, 8);
			@SuppressWarnings("deprecation")
			Block location = p.getTargetBlock(null, 999999999);
			p.getWorld().strikeLightning(location.getLocation());
			e.setCancelled(true);
		}
	}
}
