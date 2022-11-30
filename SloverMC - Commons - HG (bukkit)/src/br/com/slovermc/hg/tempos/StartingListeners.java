package br.com.slovermc.hg.tempos;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.TittleAPI;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.api.StatsAPI;
import br.com.slovermc.hg.eventos.StatusHG;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.scoreboard.Score;
import br.com.slovermc.hg.utils.Permission;

@SuppressWarnings({ "deprecation" })
public class StartingListeners implements Listener {
	
	String[] block = {"/me", "/bukkit:me", "/help", "/bukkit:help", "/pl", "/ver", "/bukkit:about" ,"/?", "/plugins", "/bukkit:plugins", "/bukkit:ver", "/ipwl", "/ipwhitelist", "/plugin", "/bukkit:pl", "/help ", "/help", "/help "};;

	@EventHandler
	protected void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		if (BukkitMain.state == StateEnum.STARTING) {
			Score.hasScore.add(p);
			Score.createScoreIniciando(p);
			
			ItemStack b = new ItemStack(Material.ENDER_CHEST);
			ItemMeta bm = b.getItemMeta();
			bm.setDisplayName("§aSelecionar kit 2");
			b.setItemMeta(bm);
			p.getInventory().setItem(1, b);
			
			for (PotionEffect effect : p.getActivePotionEffects()) {
				PotionEffectType type = effect.getType();
				p.removePotionEffect(type);
			}
			if (p.getGameMode() != GameMode.SURVIVAL) {
				p.setGameMode(GameMode.SURVIVAL);
			}
			p.setExp(0);
			p.setFoodLevel(20);
			p.setLevel(0);
			TittleAPI.sendTitle(p, "§6§lHG-" + StatusHG.getIP());
			TittleAPI.sendSubTitle(p, "§fBem vindo ao §6HG-" + StatusHG.getIP());
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (PlayerAPI.getInstance().hasAdmin(all)) {
					if (!p.hasPermission(Permission.getInstance().ADMIN)) {
						p.hidePlayer(all);
					}
				}
			}
			if (BukkitMain.state != StateEnum.STARTING)
				return;
			if (!PlayerAPI.getInstance().hasPlayer(p)) {
				PlayerAPI.getInstance().addPlayer(p);
			}
			StatsAPI.getInstance().createAccount(p, "Unranked");
			double x = BukkitMain.getPlugin().getConfig().getDouble("Spawn.x");
			double y = BukkitMain.getPlugin().getConfig().getDouble("Spawn.y");
			double z = BukkitMain.getPlugin().getConfig().getDouble("Spawn.z");
			p.teleport(new Location(p.getWorld(), x, y, z));
			KitAPI.getInstance().setKit(p, "Nenhum");
			if (BukkitMain.getPlugin().duoKit()) {
				KitAPI.getInstance().setKit2(p, "Nenhum");
			}
			PlayerAPI.getInstance().itemsStarting(p);
		}
	}

	@EventHandler
	protected void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage("");
		if (BukkitMain.state == StateEnum.STARTING) {
			if (PlayerAPI.getInstance().hasPlayer(p)) {
				PlayerAPI.getInstance().removePlayer(p);
			}
		}
	}

	@EventHandler
	protected void onPlayerLeaveColiseu(PlayerMoveEvent e) {
		Player player = e.getPlayer();

		if (BukkitMain.state == StateEnum.STARTING) {
			if (player.getLocation().getY() < 120 && BukkitMain.getPlugin().getConfig().getBoolean("Coliseu")) {
				double x = BukkitMain.getPlugin().getConfig().getDouble("Spawn.x");
				double y = BukkitMain.getPlugin().getConfig().getDouble("Spawn.y");
				double z = BukkitMain.getPlugin().getConfig().getDouble("Spawn.z");
				player.teleport(new Location(player.getWorld(), x, y, z));
				player.setFallDistance(0.0F);
				player.sendMessage("§c§lERRO §fVocê não pode sair do coliseu!");
			}
		}
	}

	@EventHandler
	protected void onPlayerDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
			player.updateInventory();
		}
	}

	@EventHandler
	protected void onItemSpawn(ItemSpawnEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onClickInventory(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;
		ItemStack i = e.getCurrentItem();
		if (i != null && e.getInventory().getTitle().equals("§cStatus")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onBreakBlock(BlockBreakEvent e) {
		Block block = e.getBlock();
		if (Manager.getInstance().border.contains(block)) {
			e.setCancelled(true);
		}
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onBreakPlace(BlockPlaceEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onBreakBurn(BlockBurnEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onLeaveDecay(LeavesDecayEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onEntityExplode(EntityExplodeEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onFoodChange(FoodLevelChangeEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
			e.setFoodLevel(20);
		}
	}

	@EventHandler
	protected void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	protected void onCreatureSpawn(CreatureSpawnEvent e) {
		if (BukkitMain.state != StateEnum.GAME) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onPickup(PlayerPickupItemEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onBreak(BlockDamageEvent e) {
		Block b = e.getBlock();
		if (b.getType() == Material.CACTUS) {
			b.breakNaturally();
		}
	}
	
	@EventHandler
	public void onMessage(PlayerCommandPreprocessEvent e) { 
		Player player = e.getPlayer();
		for (String commands : block) { 
			if (e.getMessage().equalsIgnoreCase(commands)) { 
				player.sendMessage("§c§lERRO §fEste comando se encontra bloqueado.");
				e.setCancelled(true);
			}
		}
	}
}
