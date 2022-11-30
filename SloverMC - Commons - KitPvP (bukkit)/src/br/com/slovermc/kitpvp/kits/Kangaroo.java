package br.com.slovermc.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.combat.CombatSystem;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;
public final class Kangaroo implements Listener {
	
	public static ArrayList<String> delay = new ArrayList<>();
	public static ArrayList<String> cancell = new ArrayList<>();
	
	public static final void setKangarooKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Kangaroo");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.FIREWORK, "§b§lKangaroo", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lKangaroo");
		TittleAPI.sendTittle(bp, "§bKit Kangaroo");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	@EventHandler
	public void onFall(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (((e.getEntity() instanceof Player)) && (e.getCause() == EntityDamageEvent.DamageCause.FALL) && (KitAPI.getKit(p) == "Kangaroo" 
					&& (e.getDamage() >= 9.0D)) && !e.isCancelled()) {
				e.setDamage(9.0D);
			}
		}
	}
	
	@EventHandler
	public void aoDelay(final PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (delay.contains(p.getName())) {
			Block b = p.getLocation().getBlock();
			if ((b.getType() != Material.AIR) || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
				delay.remove(p.getName());
			}
		}
	}
	
	@EventHandler
	public void aoKangaroo(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.FIREWORK && KitAPI.getKit(p) == "Kangaroo") {
			e.setCancelled(true);
			Vector vector = p.getEyeLocation().getDirection();
			if (CombatSystem.combat.containsKey(p.getName())) {
				p.sendMessage("§3§lKANGAROO§f Voce esta em combate!");
				return;
			}
			if (!cancell.contains(p.getName())) {
				if (!delay.contains(p.getName())) {
					delay.add(p.getName());
					if (!p.isSneaking()) {
						p.setFallDistance(-1.0F);
						vector.multiply(0.5F);
						vector.setY(1.0D);
						p.setVelocity(vector);
					} else {
						p.setFallDistance(-1.0F);
						vector.multiply(1.5F);
						vector.setY(0.5D);
						p.setVelocity(vector);
					}
				}
			} else if (!delay.contains(p.getName())) {
				delay.add(p.getName());
				p.setFallDistance(0.0F);
				vector.multiply(0.0F);
				vector.setY(0.0D);
				p.setVelocity(vector);
			}
		}
	}
}
