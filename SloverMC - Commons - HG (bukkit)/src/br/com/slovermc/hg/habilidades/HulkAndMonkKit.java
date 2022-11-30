package br.com.slovermc.hg.habilidades;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

public class HulkAndMonkKit implements Listener {

	private boolean random = true;
	
	@EventHandler
	private void onPlayerInteractEntityMonk(PlayerInteractEntityEvent e) {
        if (!(e.getRightClicked() instanceof Player)) return;
		Player player = e.getPlayer();
		Player clicked = (Player) e.getRightClicked();
		if (KitAPI.getInstance().hasKit(player, "Monk")) {
			if (player.getItemInHand().getType() != Material.BLAZE_ROD) return;
			if (BukkitMain.state != StateEnum.GAME) return;
			if (KitAPI.getInstance().hasCooldown(player) == false) {
				KitAPI.getInstance().addCooldown(player, 35);
				PlayerInventory inv = clicked.getInventory();
				int slot = new Random().nextInt(random ? 36 : 9);
				ItemStack replaced = clicked.getItemInHand();
				if (replaced == null) {
					replaced = new ItemStack(Material.AIR);
				}
				ItemStack replacer = inv.getItem(slot);
				clicked.setItemInHand(replacer);
				inv.setItem(slot, replaced);
				player.sendMessage("§b§lMONK §fVocê monkou o jogador§e " + clicked.getName() + "§f.");
			} else {
				KitAPI.getInstance().messageCooldown(player);
			}
		}
		
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Monk")) {
				if (player.getItemInHand().getType() != Material.BLAZE_ROD) return;
				if (BukkitMain.state != StateEnum.GAME) return;
				if (KitAPI.getInstance().hasCooldown2(player) == false) {
					KitAPI.getInstance().addCooldown2(player, 35);
					PlayerInventory inv = clicked.getInventory();
					int slot = new Random().nextInt(random ? 36 : 9);
					ItemStack replaced = clicked.getItemInHand();
					if (replaced == null) {
						replaced = new ItemStack(Material.AIR);
					}
					ItemStack replacer = inv.getItem(slot);
					clicked.setItemInHand(replacer);
					inv.setItem(slot, replaced);
					player.sendMessage("§b§lMONK §fVocê monkou o jogador§e " + clicked.getName() + "§f.");
				} else {
					KitAPI.getInstance().messageCooldown2(player);
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerHulk(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) return;
		Player player = e.getPlayer();
		Player clicked = (Player) e.getRightClicked();
		if (KitAPI.getInstance().hasKit(player, "Hulk")) {
			if (player.getItemInHand().getType() != Material.AIR) return;
			if (BukkitMain.state != StateEnum.GAME) return;
			if (KitAPI.getInstance().hasCooldown(player) == false) {
				KitAPI.getInstance().addCooldown(player, 15);
				player.setPassenger(clicked);
				player.sendMessage("§b§lHULK §fVocê puxou o jogador §e" + clicked.getName() + " §fpara seu cavalinho!");
			} else {
				KitAPI.getInstance().messageCooldown(player);
			}
			return;
		}
		
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Hulk")) {
				if (player.getItemInHand().getType() != Material.AIR) return;
				if (BukkitMain.state != StateEnum.GAME) return;
				if (KitAPI.getInstance().hasCooldown2(player) == false) {
					KitAPI.getInstance().addCooldown2(player, 15);
					player.setPassenger(clicked);
					player.sendMessage("§b§lHULK §fVocê puxou o jogador§e " + clicked.getName() + " §fpara seu cavalinho!");
				} else {
					KitAPI.getInstance().messageCooldown2(player);
				}
			}
		}
	}
}
