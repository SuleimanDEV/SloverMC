package br.com.slovermc.hg.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cAdminCMD implements CommandExecutor, Listener {

	private HashMap<Player, ItemStack[]> player_items = new HashMap<>();
	private HashMap<Player, ItemStack[]> player_armor = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("admin")) {
			if (BukkitMain.state != StateEnum.GAME) { 
				
			}
			if (player.hasPermission(Permission.getInstance().ADMIN)) {
				if (!PlayerAPI.getInstance().hasAdmin(player)) {
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (!all.hasPermission(Permission.getInstance().ADMIN)) { 
							all.hidePlayer(player);
						}
					}
					//PlayerAPI.getInstance().addAdmin(player);
					//PlayerAPI.getInstance().removePlayer(player);
					player_items.put(player, player.getInventory().getContents());
					player_armor.put(player, player.getInventory().getArmorContents());
					player.getInventory().clear();
					player.getInventory().setArmorContents(null);

					ItemStack f = new ItemStack(Material.MAGMA_CREAM);
					ItemMeta fm = f.getItemMeta();
					fm.setDisplayName("§aQuick");
					f.setItemMeta(fm);
			        
			        player.getInventory().setItem(4, f);
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage("§c§lADMIN §fVocê entrou no modo admin.");
				} else {
					for (Player all : Bukkit.getOnlinePlayers()) {
						player.showPlayer(all);
					}
					//PlayerAPI.getInstance().addPlayer(player);
					//PlayerAPI.getInstance().removeAdmin(player);
					player.setGameMode(GameMode.SURVIVAL);
					player.getInventory().setContents((ItemStack[]) player_items.get(player));
					player.getInventory().setArmorContents((ItemStack[]) player_armor.get(player));
					player_items.remove(player);
					player_armor.remove(player);
					player.sendMessage("§c§lADMIN §fVocê entrou no modo admin.");
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		if (PlayerAPI.getInstance().hasAdmin(player)) {
			ItemStack item = player.getItemInHand();
			if (item == null || !item.hasItemMeta()) return;
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aQuick") && item.getType() == Material.MAGMA_CREAM) {
				player.performCommand("admin");
				
				new BukkitRunnable() {
					public void run() {
						player.performCommand("admin");
					}
				}.runTaskLater(BukkitMain.getPlugin(), 7);
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	private void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) return;
		Player player = e.getPlayer();
		Player clicked = (Player)e.getRightClicked();
		if (PlayerAPI.getInstance().hasAdmin(player)) {
			ItemStack item = player.getItemInHand();
			if (item.getType() == Material.AIR) {
				player.openInventory(clicked.getInventory());
				e.setCancelled(true);
				return;
			}
			if (item.getType() == Material.BEDROCK && item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aPrisão")) {
				player.performCommand("prision " + clicked.getName());
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	private void onPlayerPickUpItem(PlayerPickupItemEvent e) {
		Player player = e.getPlayer();
		if (PlayerAPI.getInstance().hasAdmin(player)) {
			e.setCancelled(true);
		}
	}
}
