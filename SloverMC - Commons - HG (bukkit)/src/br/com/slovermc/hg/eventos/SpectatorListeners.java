package br.com.slovermc.hg.eventos;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;

public class SpectatorListeners implements Listener {
	
	@EventHandler
	protected void onFoodChange(FoodLevelChangeEvent e) {
		Player player = (Player) e.getEntity();
		if (PlayerAPI.getInstance().hasEspectator(player)) {
			e.setCancelled(true);
			e.setFoodLevel(20);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) { 
		Player player = (Player) e.getPlayer();
		if (PlayerAPI.getInstance().hasEspectator(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) { 
		Player player = (Player) e.getPlayer();
		if (PlayerAPI.getInstance().hasEspectator(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) { 
		if (!(e.getDamager() instanceof Player)) return;
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		Player target = (Player) e.getDamager();
		if (PlayerAPI.getInstance().hasEspectator(player)) { 
			e.setCancelled(true);
		}
		if (PlayerAPI.getInstance().hasEspectator(target)) { 
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) { 
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if (PlayerAPI.getInstance().hasEspectator(player)) {
			e.setCancelled(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) { 
		Player player = (Player) e.getPlayer();
		if (player.getItemInHand().getType() == Material.CHEST) { 
			if (PlayerAPI.getInstance().hasEspectator(player)) { 
				Inventory inv = Bukkit.createInventory(player, 54, "§aJogadores");
				for (Player all : Bukkit.getOnlinePlayers()) { 
					if (!all.getName().equals(player.getName())) { 
						ItemStack a = new ItemStack(Material.getMaterial(397));
				        SkullMeta am = (SkullMeta) a.getItemMeta();
				        if ((!PlayerAPI.getInstance().hasEspectator(all)) || (!PlayerAPI.getInstance().hasAdmin(all))) { 
				        	a.setDurability((short)3);
				            am.hasOwner();
				            am.setOwner(all.getName());
				            am.setDisplayName("§a" + all.getName());
				            am.setLore(Arrays.asList("  §fKit: §a" + KitAPI.getInstance().getKit(all) + " §fe Kit2: §a" + KitAPI.getInstance().getKit2(all)));
				            a.setItemMeta(am);
				            inv.addItem(new ItemStack[] {a});
				        }
					}
				}
				player.openInventory(inv);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventory(InventoryClickEvent e) { 
		if (e.getCurrentItem() != null) { 
			if (e.getCurrentItem().getType() != null) { 
				if (e.getInventory().getTitle().equalsIgnoreCase("Jogadores vivos")) { 
					ItemStack item = e.getCurrentItem();
					Player player = (Player) e.getWhoClicked();
					if (item.getType() == Material.SKULL_ITEM) { 
						e.setCancelled(true);
						player.closeInventory();
						for (Player all : Bukkit.getOnlinePlayers()) { 
							ItemMeta am = item.getItemMeta();
							if (all.getName().equals(am.getDisplayName())) {
								player.teleport(all);
								player.sendMessage("§aTeleportado para " + all.getName());
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent e) {
		if ((e.getTarget() != null) && (e.getTarget().getType() == EntityType.PLAYER)) {
			Player player = (Player) e.getTarget();
			if ((PlayerAPI.getInstance().hasEspectator(player)) && (!e.isCancelled())) {
				EntityType type = e.getEntity().getType();
				if ((type == EntityType.ENDERMAN) || (type == EntityType.WOLF) || (type == EntityType.PIG_ZOMBIE) || (type == EntityType.BLAZE) || (type == EntityType.CAVE_SPIDER) || (type == EntityType.CREEPER) || (type == EntityType.GHAST) || (type == EntityType.MAGMA_CUBE) || (type == EntityType.SILVERFISH) || (type == EntityType.SKELETON) || (type == EntityType.SLIME) || (type == EntityType.SPIDER) || (type == EntityType.WITCH) || (type == EntityType.WITHER_SKULL) || (type == EntityType.ZOMBIE) || (type == EntityType.IRON_GOLEM) || (type == EntityType.ENDER_DRAGON)|| (type == EntityType.WITHER)) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPick(PlayerPickupItemEvent e) { 
		Player player = e.getPlayer();
		if (PlayerAPI.getInstance().hasEspectator(player)) { 
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (PlayerAPI.getInstance().hasEspectator(player)) { 
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (PlayerAPI.getInstance().hasEspectator(player)) { 
			e.setCancelled(true);
			player.sendMessage("§4§lCHAT §fEspectadores não podem §e§lFALAR§f no chat.");
		}
	}
	
	@EventHandler
	public void onInventoryCLick(InventoryClickEvent e) { 
		Player player = (Player) e.getWhoClicked();
		if (PlayerAPI.getInstance().hasEspectator(player)) { 
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteracta(PlayerInteractEvent e) { 
		Player player = e.getPlayer();
		if (PlayerAPI.getInstance().hasEspectator(player)) { 
			e.setCancelled(true);
		}
	}
} 
