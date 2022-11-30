package br.com.slovermc.kitpvp.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.command.essentials.BuildCommand;
import br.com.slovermc.kitpvp.command.essentials.FlyCommand;
import br.com.slovermc.kitpvp.kits.Gladiator;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Evento.EventoAPI;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;

public final class MainListeners implements Listener {

	@EventHandler
	public final void onEntityExplodeEvent(final EntityExplodeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onShoot(final EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player bp = (Player) e.getEntity();
			if (bp.getItemInHand().getType() == Material.BOW) {
				bp.getItemInHand().setDurability((short) 0);
			}
			if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.CAPIROTO) {
				if (X1WarpListener.itemsInInventory(bp.getInventory(), new Material[] { Material.ARROW }) < 32) {
					bp.getInventory().addItem(new ItemStack(Material.ARROW));
				}
			}
			bp.updateInventory();
		}
	}
	
	@EventHandler
	public void ComandoDesconhecido(PlayerCommandPreprocessEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage().split(" ")[0];
		HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
		if (topic == null) {
			e.setCancelled(true);
			p.sendMessage("§c§lERRO §fComando não encontrado.");
		}
	}
	
	@EventHandler
	public final void onJump(final PlayerMoveEvent e) {
		final Player localPlayer = e.getPlayer();
		final Block localBlock1 = e.getTo().getBlock();
		final Location localLocation = localBlock1.getLocation();
		localLocation.setY(localLocation.getY() - 1.0);
		final Block localBlock2 = localLocation.getBlock();
		if (localBlock2.getType() == Material.SPONGE) {
			localPlayer.setFallDistance(-50.0f);
			localPlayer.setVelocity(new Vector(0, 5, 0));
			localPlayer.setFallDistance(-50.0f);
			localPlayer.playSound(localPlayer.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);

		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onDrop(final PlayerDropItemEvent e) {

		if (EventoAPI.Players.contains(e.getPlayer().getName())) {
			e.setCancelled(false);
		} else if ((e.getItemDrop().getItemStack().getType() == Material.getMaterial(351)
				&& WarpsAPI.battlePlayerWarp.get(e.getPlayer()) == WarpsAPI.Warps.ONEVSONE)) {
			e.setCancelled(true);
		} else if ((e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP)
				&& (e.getItemDrop().getItemStack().getType() != Material.BOWL)
				&& (e.getItemDrop().getItemStack().getType() != Material.GOLDEN_APPLE)
				&& (e.getItemDrop().getItemStack().getType() != Material.ENDER_PEARL)
				&& (e.getItemDrop().getItemStack().getType() != Material.getMaterial(351)
						&& (e.getItemDrop().getItemStack().getType() != Material.RED_MUSHROOM)
						&& (e.getItemDrop().getItemStack().getType() != Material.BROWN_MUSHROOM)
						&& (e.getItemDrop().getItemStack().getType() != Material.IRON_HELMET)
						&& (e.getItemDrop().getItemStack().getType() != Material.IRON_CHESTPLATE)
						&& (e.getItemDrop().getItemStack().getType() != Material.IRON_LEGGINGS)
						&& (e.getItemDrop().getItemStack().getType() != Material.IRON_BOOTS)
						&& (e.getItemDrop().getItemStack().getType() != Material.DIAMOND_HELMET)
						&& (e.getItemDrop().getItemStack().getType() != Material.DIAMOND_CHESTPLATE)
						&& (e.getItemDrop().getItemStack().getType() != Material.DIAMOND_LEGGINGS)
						&& (e.getItemDrop().getItemStack().getType() != Material.DIAMOND_BOOTS)
						&& (e.getItemDrop().getItemStack().getType() != Material.GLASS_BOTTLE))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public final void onCreatureSpawnEvent(final CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.CUSTOM) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public final void onBreakCancell(final BlockBreakEvent e) {
		final Player p = e.getPlayer();
		if (!BuildCommand.hasBuild.contains(p)) {
			e.setCancelled(true);
		} else {
			if (BuildCommand.hasBuild.contains(p)) {
				e.setCancelled(false);
			}
		}
	}

	@EventHandler
	public void onProjectileHitEvent(final ProjectileHitEvent e) {
		final Entity entity = e.getEntity();
		if (entity.getType() == EntityType.ARROW) {
			entity.remove();
		}
	}

	@EventHandler
	public final void onItemSpawnEvent(final ItemSpawnEvent e) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			public void run() {
				e.getEntity().remove();
			}
		}, 160L);
	}

	@EventHandler
	public final void onPlayerCommandPreprocessEvent(final PlayerCommandPreprocessEvent e) {
		final Player bp = e.getPlayer();
		if (e.getMessage().startsWith("/me")) {
			e.setCancelled(true);
		}
		if (X1WarpListener.playerfigh.containsKey(bp)) {
			if (!e.getMessage().startsWith("/report") && !e.getMessage().startsWith("/kill")
					&& !e.getMessage().startsWith("/tell") && !e.getMessage().startsWith("/msg")
					&& !e.getMessage().startsWith("/w") && !e.getMessage().startsWith("/score")
					&& !e.getMessage().startsWith("/scoreboard")) {
				e.setCancelled(true);
				bp.sendMessage("§6§l1v1§f Você não pode usar este comando em batalha!");
			}
		} else if (FlyCommand.hasFly.contains(bp)) {
			if (!e.getMessage().startsWith("/report") && !e.getMessage().startsWith("/tell")
					&& !e.getMessage().startsWith("/fly") && !e.getMessage().startsWith("/msg")
					&& !e.getMessage().startsWith("/w") && !e.getMessage().startsWith("/score")
					&& !e.getMessage().startsWith("/voar") && !e.getMessage().startsWith("/scoreboard")) {
				e.setCancelled(true);
				bp.sendMessage("§c§lERRO §fVocê só pode usar os comandos §c/tell§f, §c/report§f,§c /score §fe §v/fly§f no modo vôo.");
			}
		} else if (Gladiator.inFight.containsKey(bp.getName())) {
			if (!e.getMessage().startsWith("/report") && !e.getMessage().startsWith("/tell")
					&& !e.getMessage().startsWith("/msg") && !e.getMessage().startsWith("/w")
					&& !e.getMessage().startsWith("/score") && !e.getMessage().startsWith("/scoreboard")
					&& !e.getMessage().startsWith("/ss") && !e.getMessage().startsWith("/screenshare")
					&& !e.getMessage().startsWith("/gm") && !e.getMessage().startsWith("/gamemode")
					&& !e.getMessage().startsWith("/kick") && !e.getMessage().startsWith("/ban")
					&& !e.getMessage().startsWith("/ipban") && !e.getMessage().startsWith("/banip")
					&& !e.getMessage().startsWith("/scoreboard") && !e.getMessage().startsWith("/admin")) {
				e.setCancelled(true);
				bp.sendMessage("§c§lERRO§f Este comando não pode ser usado em batalha.");
			}
		}
	}

	public static final int getItensAmount(final Player bp, final Material m) {
		int amount = 0;
		for (ItemStack item : bp.getInventory().getContents()) {
			if ((item != null) && (item.getType() == m) && (item.getAmount() > 0)) {
				amount += item.getAmount();
			}
		}
		return amount;
	}

	public static final HashMap<Player, ItemStack[]> invP = new HashMap<Player, ItemStack[]>();
	public static final ArrayList<Player> checking = new ArrayList<>();

	@EventHandler
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if ((e.getDamager() instanceof Player)) {
			if (e.isCancelled()) {
				return;
			}
			final Player d = (Player) e.getDamager();
			if ((d.getItemInHand().getType() == Material.DIAMOND_SWORD)
					|| (d.getItemInHand().getType() == Material.STONE_SWORD)
					|| (d.getItemInHand().getType() == Material.WOOD_SWORD)
					|| (d.getItemInHand().getType() == Material.STONE_SWORD)
					|| (d.getItemInHand().getType() == Material.IRON_SWORD)
					|| (d.getItemInHand().getType() == Material.GOLD_SWORD)
					|| (d.getItemInHand().getType() == Material.FISHING_ROD)
					|| (d.getItemInHand().getType() == Material.DIAMOND_AXE)
					|| (d.getItemInHand().getType() == Material.GOLD_AXE)
					|| (d.getItemInHand().getType() == Material.STONE_AXE)
					|| (d.getItemInHand().getType() == Material.WOOD_AXE)
					|| (d.getItemInHand().getType() == Material.IRON_AXE)) {
				d.getItemInHand().setDurability((short) 0);
				d.updateInventory();
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!BuildCommand.hasBuild.contains(p)) {
			e.setCancelled(true);
		} else {
			if (BuildCommand.hasBuild.contains(p)) {
				e.setCancelled(false);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onPickup(final PlayerPickupItemEvent e) {
		final Player bp = e.getPlayer();
		if (FlyCommand.hasFly.contains(bp)) {
			e.setCancelled(true);
		} else if (EventoAPI.Players.contains(bp.getName())) {
			e.setCancelled(false);
		} else if (WarpsAPI.battlePlayerWarp.get(bp) != WarpsAPI.Warps.ONEVSONE
				&& e.getItem().getItemStack().getType() != Material.MUSHROOM_SOUP
				&& (e.getItem().getItemStack().getType() != Material.RED_MUSHROOM)
				&& (e.getItem().getItemStack().getType() != Material.BROWN_MUSHROOM)
				&& (e.getItem().getItemStack().getType() != Material.getMaterial(351))
				&& (e.getItem().getItemStack().getType() != Material.GOLDEN_APPLE)
				&& (e.getItem().getItemStack().getType() != Material.BOWL)) {
			e.setCancelled(true);
		} else {
			if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.ONEVSONE) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerInteractSoup(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Damageable hp = p;
		if (hp.getHealth() != 20.0D) {
			if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
				int sopa = 7;
				if (e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
					p.setHealth(hp.getHealth() + sopa > hp.getMaxHealth() ? hp.getMaxHealth() : hp.getHealth() + sopa);
					p.getItemInHand().setType(Material.BOWL);
				}
			}
		}
	}

	@EventHandler
	public final void onWheaterLevelChangeEvent(final WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onFoodLevelChangeEvent(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onAchievementAwardedEvent(final PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Player)) {
			Player d = (Player) e.getDamager();
			if ((d.getItemInHand().getType() == Material.DIAMOND_SWORD)
					|| (d.getItemInHand().getType() == Material.STONE_SWORD)
					|| (d.getItemInHand().getType() == Material.WOOD_SWORD)
					|| (d.getItemInHand().getType() == Material.STONE_SWORD)
					|| (d.getItemInHand().getType() == Material.IRON_SWORD)
					|| (d.getItemInHand().getType() == Material.GOLD_SWORD)
					|| (d.getItemInHand().getType() == Material.FISHING_ROD)
					|| (d.getItemInHand().getType() == Material.DIAMOND_AXE)
					|| (d.getItemInHand().getType() == Material.GOLD_AXE)
					|| (d.getItemInHand().getType() == Material.STONE_AXE)
					|| (d.getItemInHand().getType() == Material.WOOD_AXE)
					|| (d.getItemInHand().getType() == Material.IRON_AXE)) {
				d.getItemInHand().setDurability((short) 0);
				d.updateInventory();
			}
		}
	}

	@EventHandler
	public void onSignChangeEvent(final SignChangeEvent e) {
		if (e.getLine(0).contains("&")) {
			e.setLine(0, e.getLine(0).replace("&", "§"));
		}
		if (e.getLine(1).contains("&")) {
			e.setLine(1, e.getLine(1).replace("&", "§"));
		}
		if (e.getLine(2).contains("&")) {
			e.setLine(2, e.getLine(2).replace("&", "§"));
		}
		if (e.getLine(3).contains("&")) {
			e.setLine(3, e.getLine(3).replace("&", "§"));
		}
	}
}