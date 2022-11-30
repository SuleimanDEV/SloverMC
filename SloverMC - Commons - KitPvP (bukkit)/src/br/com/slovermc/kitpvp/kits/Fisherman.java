package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class Fisherman implements Listener {
	
	public static final void setFishermanKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Fisherman");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.FISHING_ROD, "§b§lFisherman", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lFisherman");
		TittleAPI.sendTittle(bp, "§bKit Fisherman");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	  public final void onPlayerFishEvent(final PlayerFishEvent e) {
	    final Player p = e.getPlayer();
	    final Entity caught = e.getCaught();
	    final Block block = e.getHook().getLocation().getBlock();
	    if ((caught != null) && (caught != block) &&  (KitAPI.getKit(p) == "Fisherman"))
	      caught.teleport(p.getPlayer().getLocation());
	  }
}
