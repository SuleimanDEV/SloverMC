package br.com.slovermc.kitpvp.kits;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class Ajnin implements Listener {
	
	public static final HashMap<Player, String> ajnin = new HashMap<>();
	
	public static final void setAjninKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Ajnin");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lAjnin");
		TittleAPI.sendTittle(bp, "§bKit Ajnin");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
	
	@EventHandler
	public final void onAjninHit(final EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player damaged = (Player) e.getEntity();
			final Player damager = (Player) e.getDamager();
			if (SpawnWarpListener.onWarpSpawnProtection.contains(damaged) || FpsWarpListener.onFpsSpawnProtection.contains(damaged)) {
				return;
			}
			if (KitAPI.getKit(damager) == "Ajnin") {
				ajnin.put(damager, damaged.getName());
			}
		}
	}
	
	@EventHandler
	public final void onSneakAjnin(final PlayerToggleSneakEvent e) {
		final Player bp = e.getPlayer();
		if (KitAPI.getKit(bp) == "Ajnin" && ajnin.containsKey(bp)) {
			final Player ajned = Bukkit.getPlayer(ajnin.get(bp));
			if (ajned == null) {
				bp.sendMessage("§3§lAJNIN§f Este player não está §c§lonline§f!");
				return;
			}
			if (bp.getLocation().distance(ajned.getLocation()) > 50.0D) {
				bp.sendMessage("§3§lAJNIN§f O ultimo jogador hitado está a mais de §c§l50 BLOCOS§f de distancia!");
				return;
			}
			if (SpawnWarpListener.onWarpSpawnProtection.contains(ajned) || FpsWarpListener.onFpsSpawnProtection.contains(ajned)) {
				bp.sendMessage("§3§lAJNIN§f O ultimo jogador hitado está com §8§lPROTEÇAO§f de spawn!");
				return;
			}
			if (WarpsAPI.battlePlayerWarp.get(ajned) != WarpsAPI.Warps.SPAWN) {
				bp.sendMessage("§3§lAJNIN§f O ultimo jogador hitado nao está no modo §e§lKITPVP§f!");
				return;
			}
			if (Gladiator.inFight.containsKey(ajned.getName())) {
				bp.sendMessage("§3§lAJNIN§f O jogador esta em um §b§lGLADIATOR§f!");
				return;
			}
			if (CooldownAPI.Cooldown.containsKey(bp.getName())) {
				bp.sendMessage("§3§lAJNIN§f Você está em cooldown de §c§l" + CooldownAPI.getCooldown(bp));
				return;
			}
			CooldownAPI.runCooldown(bp, 11);
			ajned.teleport(bp);
			bp.sendMessage("§3§lAJNIN§f Teleportado!");
		}
	}
}
