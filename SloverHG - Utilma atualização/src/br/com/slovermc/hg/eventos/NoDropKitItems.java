package br.com.slovermc.hg.eventos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.hg.api.KitAPI;

public class NoDropKitItems implements Listener {

	@EventHandler
	private void onPlayerDropKitItems(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		ItemStack item = e.getItemDrop().getItemStack();
		if (handle(item, player, "Deshfire", Material.REDSTONE_BLOCK, e)) {
		} else if (handle(item, player, "Sonic", Material.LAPIS_BLOCK, e)) {
		} else if (handle(item, player, "Flash", Material.REDSTONE_TORCH_ON, e)) {
		} else if (handle(item, player, "Blink", Material.NETHER_STAR, e)) {
		} else if (handle(item, player, "Grappler", Material.LEASH, e)) {
		} else if (handle(item, player, "Endermage", Material.PORTAL, e)) {
		} else if (handle(item, player, "Phantom", Material.FEATHER, e)) {
		} else if (handle(item, player, "Gladiator", Material.IRON_FENCE, e)) {
		} else if (handle(item, player, "Specialist", Material.WRITTEN_BOOK, e)) {
		} else if (handle(item, player, "Vacuum", Material.EYE_OF_ENDER, e)) {
		} else if (handle(item, player, "Thor", Material.WOOD_AXE, e)) {
		} else if (handle(item, player, "Kangaroo", Material.FIREWORK, e)) {
		} else if (handle(item, player, "Lumberjack", Material.WOOD_AXE, e)) {
		} else if (handle(item, player, "Jackhammer", Material.STONE_AXE, e)) {
		} else if (handle(item, player, "Archer", Material.BOWL, e)) {
		} else if (handle(item, player, "Archer", Material.ARROW, e)) {
		} else if (handle(item, player, "Demoman", Material.GRAVEL, e)) {
		} else if (handle(item, player, "Demoman", Material.STONE_PLATE, e)) {
		} else if (handle(item, player, "Forger", Material.COAL, e)) {
		}
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		e.setJoinMessage(null);
	}
	
	
	@EventHandler
	private void onPlayerDeathKitItems(PlayerDeathEvent e) {
		Player player = e.getEntity();
		for (ItemStack item : e.getDrops()) {
			if (handle(item, player, "Deshfire", Material.REDSTONE_BLOCK)) {
			} else if (handle(item, player, "Sonic", Material.LAPIS_BLOCK)) {
			} else if (handle(item, player, "Flash", Material.REDSTONE_TORCH_ON)) {
			} else if (handle(item, player, "Blink", Material.NETHER_STAR)) {
			} else if (handle(item, player, "Grappler", Material.LEASH)) {
			} else if (handle(item, player, "Endermage", Material.PORTAL)) {
			} else if (handle(item, player, "Phantom", Material.FEATHER)) {
			} else if (handle(item, player, "Gladiator", Material.IRON_FENCE)) {
			} else if (handle(item, player, "Specialist", Material.WRITTEN_BOOK)) {
			} else if (handle(item, player, "Vacuum", Material.EYE_OF_ENDER)) {
			} else if (handle(item, player, "Thor", Material.WOOD_AXE)) {
			} else if (handle(item, player, "Kangaroo", Material.FIREWORK)) {
			} else if (handle(item, player, "Lumberjack", Material.WOOD_AXE)) {
			} else if (handle(item, player, "Jackhammer", Material.STONE_AXE)) {
			} else if (handle(item, player, "Archer", Material.BOWL)) {
			} else if (handle(item, player, "Archer", Material.ARROW)) {
			} else if (handle(item, player, "Demoman", Material.GRAVEL)) {
			} else if (handle(item, player, "Demoman", Material.STONE_PLATE)) {
			} else if (handle(item, player, "Forger", Material.COAL)) {
			}
		}
	}
	
	private boolean handle(ItemStack item, Player player, String kit, Material material) {
		if (item.getType() == material && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			if (KitAPI.getInstance().hasKit(player, kit) || KitAPI.getInstance().hasKit2(player, kit)) {
				item.setType(Material.AIR);
				item.setAmount(0);
				return true;
			}
		}
		return false;
	}
	
	private boolean handle(ItemStack item, Player player, String kit, Material material, PlayerDropItemEvent e) {
		if (item.getType() == material && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			if (KitAPI.getInstance().hasKit(player, kit) || KitAPI.getInstance().hasKit2(player, kit)) {
				e.setCancelled(true);
				player.updateInventory();
				return true;
			}
		}
		return false;
	}
}
