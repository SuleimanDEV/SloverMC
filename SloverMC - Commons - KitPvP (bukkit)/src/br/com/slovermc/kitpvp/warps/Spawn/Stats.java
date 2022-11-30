package br.com.slovermc.kitpvp.warps.Spawn;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Stats implements Listener {

	public static void Status(Player p) {

		Inventory stats = Bukkit.createInventory(p, 3 * 9, "§6§lStatus");

		stats.setItem(12, SpawnItens.newItem(Material.SKULL_ITEM, "§b§lSeus status", new String[] {"§7Clique para ver seus status"}, 1, (byte) 3));
		stats.setItem(14, SpawnItens.newItem(Material.PAPER, "§e§lLigas", new String[] {"§7Clique para ver os requesitos das ligas"}));
		p.openInventory(stats);
	}

@EventHandler
public final void onInventoryAllKitsClick(final InventoryClickEvent e) {
	if (e.getWhoClicked() instanceof Player) {
		final Player bp = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§6§lStatus") && e.getCurrentItem() != null) {
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				e.setCancelled(true);
				bp.closeInventory();
				bp.chat("/account");
			}
			if (e.getCurrentItem().getType() == Material.PAPER) {
				e.setCancelled(true);
				bp.closeInventory();
				bp.chat("/liga");
			} else {
				e.setCancelled(true);
			}
		}
	}
}
}
