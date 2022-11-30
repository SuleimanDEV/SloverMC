package br.com.slovermc.hg.habilidades;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;

public class DemomanAndForgerKit implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		if (KitAPI.getInstance().hasKit(player, "Demoman") || KitAPI.getInstance().hasKit2(player, "Demoman")) { 
			if (block.getType() == Material.STONE_PLATE) {
				block.setMetadata("demoman", new FixedMetadataValue(BukkitMain.getPlugin(), player));
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) { 
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();
		if (e.getAction() == Action.PHYSICAL) { 
			if (block.hasMetadata("demoman")) { 
				if (block.getRelative(BlockFace.DOWN).getType() == Material.GRAVEL) { 
					if (PlayerAPI.getInstance().hasPlayer(player)) { 
						player.getWorld().createExplosion(player.getLocation(), 4.0F);
					}
				}
			}
 		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) { 
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if (KitAPI.getInstance().hasKit(player, "Demoman") || KitAPI.getInstance().hasKit2(player, "Demoman")) { 
			if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) { 
				double d = e.getDamage();
				double porcent = 50.0D;
				if (player.isBlocking()) { 
					porcent = 70.D;
				}
				e.setDamage(e.getDamage() - d / 100.0D * porcent);
			}
		}
	} 
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventory(InventoryClickEvent e) {
		ItemStack ci = e.getCurrentItem();
		Player player = (Player) e.getWhoClicked();
		if ((ci != null) && (ci.getType() != Material.AIR) && (KitAPI.getInstance().hasKit(player, "Forger"))) {
			int ca = 0;
			Inventory inv = e.getView().getBottomInventory();
			for (ItemStack item : inv.getContents()) {
				if ((item != null) && (item.getType() == Material.COAL)) {
					ca += item.getAmount();
				}
			}
			if (ca == 0) return;
			int hadCoal = ca;
			if (ci.getType() == Material.COAL) {
				for (int slot = 0; slot < inv.getSize(); slot++) {
					ItemStack item = inv.getItem(slot);
					if ((item != null) && (item.getType().name().contains("ORE"))) {
						while ((item.getAmount() > 0) && (ca > 0) && ((item.getType() == Material.IRON_ORE) || (item.getType() == Material.GOLD_ORE))) {
							item.setAmount(item.getAmount() - 1);
							ca--;
							if (item.getType() == Material.IRON_ORE) {
								player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
							} else if (item.getType() == Material.GOLD_ORE) {
								player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
							}
						}
						if (item.getAmount() == 0) {
							inv.setItem(slot, new ItemStack(0));
						}
					}
				}
			} else if (ci.getType().name().contains("ORE")) {
				while ((ci.getAmount() > 0) && (ca > 0) && ((ci.getType() == Material.IRON_ORE) || (ci.getType() == Material.GOLD_ORE))) {
					ci.setAmount(ci.getAmount() - 1);
					ca--;
					if (ci.getType() == Material.IRON_ORE) {
						player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
					} else if (ci.getType() == Material.GOLD_ORE) {
						player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
					}
				}
				if (ci.getAmount() == 0) e.setCurrentItem(new ItemStack(0));
			}
			if (ca != hadCoal) {
				for (int slot = 0; slot < inv.getSize(); slot++) {
					ItemStack item = inv.getItem(slot);
					if ((item != null) && (item.getType() == Material.COAL)) {
						while ((ca < hadCoal) && (item.getAmount() > 0)) {
							item.setAmount(item.getAmount() - 1);
							ca++;
						}
						if (item.getAmount() == 0) inv.setItem(slot, new ItemStack(0));
					}
				}
			}
		}
	}
}
