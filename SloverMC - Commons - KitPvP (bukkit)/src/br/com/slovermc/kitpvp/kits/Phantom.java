package br.com.slovermc.kitpvp.kits;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class Phantom implements Listener {

	public static HashMap<Player, ItemStack[]> salvarArmadura = new HashMap<>();
	
	public static final void setPhantomKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Phantom");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.FEATHER, "§b§lPhantom", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lPhantom");
		TittleAPI.sendTittle(bp, "§bKit Phantom");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler
	public final void interagir(final PlayerInteractEvent e) {

		final Player p = e.getPlayer();
		if ((KitAPI.getKit(p) == "Phantom") && (CooldownAPI.Cooldown.containsKey(p.getName()))
				&& (p.getItemInHand().getType() == Material.FEATHER)) {
			e.setCancelled(true);
			p.sendMessage("§3§lPHANTOM§f Voce esta em cooldown de §c§l" + CooldownAPI.getCooldown(p));
			return;
		} else {
			if ((KitAPI.getKit(p) == "Phantom") && (!CooldownAPI.Cooldown.containsKey(p.getName())
					&& (p.getItemInHand().getType() == Material.FEATHER))) {

				salvarArmadura.put(p, p.getInventory().getArmorContents());

				p.getInventory().setArmorContents(null);
				ItemStack Peito = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta kPeito = (LeatherArmorMeta) Peito.getItemMeta();
				kPeito.setDisplayName("§b§l-");
				kPeito.setColor(Color.BLUE);
				Peito.setItemMeta(kPeito);

				ItemStack Calça = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta kCaça = (LeatherArmorMeta) Calça.getItemMeta();
				kCaça.setDisplayName("§b§l-");
				kCaça.setColor(Color.BLUE);
				Calça.setItemMeta(kCaça);

				ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta kBota = (LeatherArmorMeta) Bota.getItemMeta();
				kBota.setDisplayName("§b§l-");
				kBota.setColor(Color.BLUE);
				Bota.setItemMeta(kBota);

				ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta kCasapete = (LeatherArmorMeta) Capacete.getItemMeta();
				kCasapete.setDisplayName("§b§l-");
				kCasapete.setColor(Color.BLUE);
				Capacete.setItemMeta(kCasapete);

				p.getInventory().setChestplate(Peito);
				p.getInventory().setLeggings(Calça);
				p.getInventory().setHelmet(Capacete);
				p.getInventory().setBoots(Bota);
				p.updateInventory();

				p.sendMessage("§3§lPHANTOM§f Voce pode §e§lvoar§f por §c§l7 SEGUNDOS!");
				CooldownAPI.runCooldown(p, 30);

				p.setAllowFlight(true);
				Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
					public void run() {
						p.sendMessage("§3§lPHANTOM§f Seu modo §e§lvoo§f acabou!");
						p.getInventory().setArmorContents(null);

						p.closeInventory();
						p.getInventory().remove(Material.LEATHER_BOOTS);
						p.getInventory().remove(Material.LEATHER_CHESTPLATE);
						p.getInventory().remove(Material.LEATHER_HELMET);
						p.getInventory().remove(Material.LEATHER_LEGGINGS);
						p.updateInventory();

						p.getInventory().setArmorContents(salvarArmadura.get(p));
						p.setAllowFlight(false);
					}
				}, 140L);
			}
		}
	}
}
