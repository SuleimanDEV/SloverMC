package br.com.slovermc.hg.habilidades;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;

public class AchillesAndAnchorKit implements Listener {

	@EventHandler
	private void onEntityDamageByEntityAnchor(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player)e.getEntity();
		Player damager = (Player)e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Anchor") || KitAPI.getInstance().hasKit2(player, "Anchor")) {
			player.setVelocity(new Vector());
			
			new BukkitRunnable() {
				public void run() {
					player.setVelocity(new Vector());
					damager.playSound(damager.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				}
			}.runTaskLater(BukkitMain.getPlugin(), 1);
			damager.setVelocity(new Vector());
			
			new BukkitRunnable() {
				public void run() {
					damager.setVelocity(new Vector());
					damager.playSound(damager.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				}
			}.runTaskLater(BukkitMain.getPlugin(), 1);
		}
        if (KitAPI.getInstance().hasKit(damager, "Anchor") || KitAPI.getInstance().hasKit2(damager, "Anchor")) {
			damager.setVelocity(new Vector());
			
			new BukkitRunnable() {
				public void run() {
					damager.setVelocity(new Vector());
					damager.playSound(damager.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				}
			}.runTaskLater(BukkitMain.getPlugin(), 1);
        	player.setVelocity(new Vector());
        	
        	new BukkitRunnable() {
				public void run() {
					player.setVelocity(new Vector());
					damager.playSound(damager.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				}
			}.runTaskLater(BukkitMain.getPlugin(), 1);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	private void onEntityDamageByEntityAchilles(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
			Player player = (Player) e.getEntity();
			Player damager = (Player) e.getDamager();
			ItemStack item = damager.getItemInHand();
			if (item == null) return;
			if (KitAPI.getInstance().hasKit(player, "Achilles") || KitAPI.getInstance().hasKit2(player, "Achilles")) {
				if (item.getType().equals(Material.WOOD_SWORD)) {
					e.setDamage(6.0D);
				} else if (item.getType().equals(Material.WOOD_AXE)) {
					e.setDamage(e.getDamage() + 6.0D);
				} else if (item.getType().equals(Material.WOOD_SPADE)) {
					e.setDamage(e.getDamage() + 6.0D);
				} else if (item.getType().equals(Material.WOOD_PICKAXE)) {
					e.setDamage(e.getDamage() + 6.0D);
				} else if (item.getType().equals(Material.STONE_SWORD)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.STONE_PICKAXE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.STONE_AXE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.STONE_SPADE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.IRON_SWORD)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.IRON_PICKAXE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.IRON_SPADE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.IRON_AXE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.GOLD_SWORD)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.GOLD_PICKAXE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.GOLD_AXE)) {
					e.setDamage(e.getDamage() - 2.0D);
				} else if (item.getType().equals(Material.DIAMOND_SWORD)) {
					e.setDamage(e.getDamage() - 3.0D);
				} else if (item.getType().equals(Material.DIAMOND_SPADE)) {
					e.setDamage(e.getDamage() - 3.0D);
				} else if (item.getType().equals(Material.DIAMOND_AXE)) {
					e.setDamage(e.getDamage() - 3.0D);
				} else if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
					e.setDamage(e.getDamage() - 3.0D);
				}
			}
		}
	}
}
