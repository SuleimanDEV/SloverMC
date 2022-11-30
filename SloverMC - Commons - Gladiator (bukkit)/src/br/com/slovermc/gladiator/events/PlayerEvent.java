package br.com.slovermc.gladiator.events;


import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.gladiator.api.TittleAPI;
import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.api.DeathsAPI;
import br.com.slovermc.gladiator.api.GladiatorAPI;
import br.com.slovermc.gladiator.api.SkyAPI;
import br.com.slovermc.gladiator.api.WinsAPI;
import br.com.slovermc.gladiator.api.WsAPI;
import br.com.slovermc.gladiator.hologramas.HologramaAPI;
import br.com.slovermc.gladiator.hologramas.HologramaWinsStreak;
import br.com.slovermc.gladiator.hologramas.Holograma;
import br.com.slovermc.gladiator.mysql.MySQL_Connection;
import br.com.slovermc.gladiator.score.Score;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EnumClientCommand;
import net.minecraft.server.v1_7_R4.PacketPlayInClientCommand;
import net.minecraft.server.v1_7_R4.PlayerConnection;

public class PlayerEvent implements Listener {

	public static HashMap<Player, String> damage = new HashMap<>();
	public static ArrayList<String> addelo = new ArrayList<>();
	public static boolean hasHologram = false;

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(final PlayerLoginEvent e) {
		if (MySQL_Connection.connection != null) {
			if (!BukkitMain.mysql.verifyPlayerRegister(e.getPlayer().getUniqueId())) {
				BukkitMain.mysql.registerPlayer(e.getPlayer());
			}
		}
	}



	@SuppressWarnings("deprecation")
	@EventHandler
	public final static void ListenerJoinEvent(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		Score.hasScore.add(p);
		Score.ScoreGladiator(p);
		p.setHealth(20);
		EventAdmin.jogadores.add(p.getUniqueId());
		TittleAPI.sendTitle(p, "§6§lSLOVER");
		TittleAPI.sendSubTitle(p, "Seja bem-vindo ao §6Gladiator");
		ListenerItens.dawn.remove(p.getName());
		ListenerItens.desafiar.remove(p);
		p.teleport(p.getWorld().getSpawnLocation());
		GladiatorAPI.emluta.remove(p.getName());
		BukkitMain.getitemDefault(p);
		p.setGameMode(GameMode.SURVIVAL);
		p.setFlying(false);
		p.setFoodLevel(20);
		p.sendMessage("");
		p.sendMessage("§b§lCONNECT §fConectando...");
		p.sendMessage("§b§lCONNECT §fVocê foi conectado com sucesso.");
		p.sendMessage("");

		if (!hasHologram) {
			hasHologram = true;
			HologramaAPI.topXpHologramLoader();
		}
		final Player battleplayer = e.getPlayer();
		if (! HologramaAPI.canSee.contains(battleplayer)) {
			HologramaAPI.canSee.add(battleplayer);
		}
		for (Holograma hl : HologramaAPI.holograms) {
			if (hl != null) {
				hl.show(battleplayer);
			}
		}
		Holograma hl = HologramaAPI.holograma.get("hl");
		if (hl != null) {
			hl.show(battleplayer);
		}

		if (!hasHologram) {
			hasHologram = true;
			HologramaWinsStreak.topTopHologramLoader();
		}
		if (!HologramaWinsStreak.canSee.contains(battleplayer)) {
			HologramaWinsStreak.canSee.add(battleplayer);
		}
		for (Holograma top : HologramaWinsStreak.holograms) {
			if (top != null) {
				top.show(battleplayer);
			}
		}
		Holograma top = HologramaWinsStreak.holograma.get("top");
		if (top != null) {
			top.show(battleplayer);
		}
	}

	@EventHandler
	public void Chuva(WeatherChangeEvent e) {
		if (e.toWeatherState()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public final static void ListenerSoupEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Damageable hp = p;
		ItemStack tigela = new ItemStack(Material.BOWL);
		ItemMeta tigelam = tigela.getItemMeta();
		tigela.setItemMeta(tigelam);
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
			if (hp.getHealth() != hp.getMaxHealth()) {
				p.setHealth(hp.getHealth() + 7.00 > hp.getMaxHealth() ? hp.getMaxHealth() : (hp.getHealth() + 7.0D));
				p.getItemInHand().setType(Material.BOWL);
				p.getItemInHand().setItemMeta(tigelam);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public final static void ListenerDropEvent(PlayerDropItemEvent e) {
		Material drop = e.getItemDrop().getItemStack().getType();
		if (drop == Material.IRON_FENCE) {
			e.setCancelled(true);
		}
		if (drop == Material.MELON) {
			e.setCancelled(true);
		}
		if (drop == Material.DIAMOND_SWORD) {
			e.setCancelled(true);
		}
		if (drop == Material.getMaterial(351)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public final static void ListenerPickupEvent(PlayerPickupItemEvent e) {
		if (GladiatorAPI.emluta.containsKey(e.getPlayer().getName())) {
			if (e.getItem().getItemStack().getType() == Material.MUSHROOM_SOUP) {
				return;
			}
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	private static boolean isCrital(final Player p) {
		return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
	}

	// SKYPROGRAMMER //
	@EventHandler(priority = EventPriority.HIGHEST)
	public final static void ListenerEntityDamageEvent(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player))
			return;
		final Player p = (Player) event.getDamager();
		ItemStack sword = p.getItemInHand();
		double damage = event.getDamage();
		double swordDamage = getDamage(sword.getType());
		boolean isMore = false;
		if (damage > 1) {
			isMore = true;
		}
		if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
			for (PotionEffect effect : p.getActivePotionEffects()) {
				if (effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
					double minus;
					if (isCrital(p)) {
						minus = (swordDamage + (swordDamage / 2)) * 1.3 * (effect.getAmplifier() + 1);
					} else {
						minus = swordDamage * 1.3 * (effect.getAmplifier() + 1);
					}
					damage = damage - minus;
					damage += 2 * (effect.getAmplifier() + 1);
					break;
				}
			}
		}
		if (!sword.getEnchantments().isEmpty()) {
			if (sword.containsEnchantment(Enchantment.DAMAGE_ARTHROPODS)) {
				damage = damage - (1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS));
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_UNDEAD)) {
				damage = damage - (1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD));
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
				damage = damage - 1.25 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			}
		}
		if (isCrital(p)) {
			damage = damage - (swordDamage / 2);
			damage += 1;
		}
		if (isMore)
			damage -= 2;
		event.setDamage(damage);
	}

	private static double getDamage(final Material type) {
		double damage = 1.0;
		if (type.toString().contains("DIAMOND_")) {
			damage = 10.0;
		} else if (type.toString().contains("IRON_")) {
			damage = 7.0;
		} else if (type.toString().contains("STONE_")) {
			damage = 6.0;
		} else if (type.toString().contains("WOOD_")) {
			damage = 5.0;
		} else if (type.toString().contains("GOLD_")) {
			damage = 5.0;
		}
		if (!type.toString().contains("_SWORD")) {
			damage--;
			if (!type.toString().contains("_AXE")) {
				damage--;
				if (!type.toString().contains("_PICKAXE")) {
					damage--;
					if (!type.toString().contains("_SPADE")) {
						damage = 1.0;
					}
				}
			}
		}
		return damage;
	}
	
	public static void AnunceWs(final Player p) {
		int ws = WsAPI.getWs(p);
		int[] values = {5, 10, 15, 20, 25, 30, 40, 50, 60, 70, 80, 100};
		for (int anunce : values)
		if (ws == anunce) {
			Bukkit.broadcastMessage("§b§lWINSTREAK §fO jogador §c" + p.getName() + " §ffez " + anunce + " §fde winstreak.");
		}
	}

	@EventHandler
	public final static void ListenerEntityEvent(EntitySpawnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final static void ListenerDeathPlayerEvent(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			e.getDrops().clear();
			Player p = (Player) e.getEntity();
			if (GladiatorAPI.emluta.containsKey(p.getName())) {
				Player k = Bukkit.getPlayer(GladiatorAPI.emluta.get(p.getName()));
				BukkitMain.getitemDefault(k);
				GladiatorAPI.removeArena(k);
				GladiatorAPI.emluta.remove(k.getName());
				e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 1);
				e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.LAVA_POP, 1.0F, 1.0F);
				p.sendMessage("");
				p.sendMessage("§4§lDEATH §fVocê morreu para §e" + k.getName() + "§f.");
				p.sendMessage("§a§lWINSTREAK §fVocê perdeu seu Winstreak e §a10 §fde §6§lXP§f.");
				p.sendMessage("");
				k.sendMessage("");
				k.sendMessage("§e§lKILL §fVocê matou §e" + p.getName() + "§f.");
				k.sendMessage("§a§lWINSTREAK §fVocê ganhou §a+1§f de Winstreak e §a25 §fde §6§lXP§f.");
				k.sendMessage("");
				BukkitMain.getitemDefault(p);
				k.teleport(k.getWorld().getSpawnLocation());
				DeathsAPI.addDeaths(p, 1);
				WinsAPI.addWins(k, 1);
				SkyAPI.addXp(k, 25);
				SkyAPI.removeXp(p, 10);
				WsAPI.addWs(k, 1);
				WsAPI.setWs0(p, 0);
				ListenerItens.toFight = null;
				ListenerItens.toFight1 = null;

				if (!hasHologram) {
					hasHologram = true;
					HologramaAPI.topXpHologramLoader();
				}
				if (! HologramaAPI.canSee.contains(p)) {
					HologramaAPI.canSee.add(p);
				}
				for (Holograma hl : HologramaAPI.holograms) {
					if (hl != null) {
						hl.show(p);
					}
				}
				Holograma hl = HologramaAPI.holograma.get("hl");
				if (hl != null) {
					hl.show(p);
				}

				if (!hasHologram) {
					hasHologram = true;
					HologramaWinsStreak.topTopHologramLoader();
				}
				if (!HologramaWinsStreak.canSee.contains(p)) {
					HologramaWinsStreak.canSee.add(p);
				}
				for (Holograma top : HologramaWinsStreak.holograms) {
					if (top != null) {
						top.show(p);
					}
				}
				Holograma top = HologramaWinsStreak.holograma.get("top");
				if (top != null) {
					top.show(p);
				}
			} else {
				p.sendMessage("§4§lDEATH §fVocê morreu de uma forma desconhecida§f.");
				p.sendMessage("§a§lWINSTREAK §fVocê perdeu seu Winstreak e §a10 §fde §6§lXP§f.");

				p.getInventory().clear();
				BukkitMain.getitemDefault(p);
				DeathsAPI.addDeaths(p, 1);
				WsAPI.setWs0(p, 0);

				if (!hasHologram) {
					hasHologram = true;
					HologramaAPI.topXpHologramLoader();
				}
				if (! HologramaAPI.canSee.contains(p)) {
					HologramaAPI.canSee.add(p);
				}
				for (Holograma hl : HologramaAPI.holograms) {
					if (hl != null) {
						hl.show(p);
					}
				}
				Holograma hl = HologramaAPI.holograma.get("hl");
				if (hl != null) {
					hl.show(p);
				}

				if (!hasHologram) {
					hasHologram = true;
					HologramaWinsStreak.topTopHologramLoader();
				}
				if (!HologramaWinsStreak.canSee.contains(p)) {
					HologramaWinsStreak.canSee.add(p);
				}
				for (Holograma top : HologramaWinsStreak.holograms) {
					if (top != null) {
						top.show(p);
					}
				}
				Holograma top = HologramaWinsStreak.holograma.get("top");
				if (top != null) {
					top.show(p);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public final static void ListenerRespawnEvent(PlayerRespawnEvent e) {
		e.getPlayer().closeInventory();
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		ListenerItens.dawn.remove(e.getPlayer().getName());
		ListenerItens.desafiar.remove(e.getPlayer());
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		GladiatorAPI.emluta.remove(e.getPlayer().getName());
		BukkitMain.getitemDefault(e.getPlayer());
		damage.remove(e.getPlayer());
		e.getPlayer().setHealth(20);

		if (!hasHologram) {
			hasHologram = true;
			HologramaAPI.topXpHologramLoader();
		}
		if (! HologramaAPI.canSee.contains(e.getPlayer())) {
			HologramaAPI.canSee.add(e.getPlayer());
		}
		for (Holograma hl : HologramaAPI.holograms) {
			if (hl != null) {
				hl.show(e.getPlayer());
			}
		}
		Holograma hl = HologramaAPI.holograma.get("hl");
		if (hl != null) {
			hl.show(e.getPlayer());
		}

		if (!hasHologram) {
			hasHologram = true;
			HologramaWinsStreak.topTopHologramLoader();
		}
		if (!HologramaWinsStreak.canSee.contains(e.getPlayer())) {
			HologramaWinsStreak.canSee.add(e.getPlayer());
		}
		for (Holograma top : HologramaWinsStreak.holograms) {
			if (top != null) {
				top.show(e.getPlayer());
			}
		}
		Holograma top = HologramaWinsStreak.holograma.get("top");
		if (top != null) {
			top.show(e.getPlayer());
		}
	}

@EventHandler
public final static void ListenerFoodEvent(FoodLevelChangeEvent e) {
	if (e.getEntity() instanceof Player) {
		e.setCancelled(true);
	}
}

@EventHandler
public final static void ListenerDeathEvent(PlayerDeathEvent e) {
	Player player = e.getEntity();
	e.setDeathMessage(null);
	new BukkitRunnable() {
		@Override
		public void run() {
			if (!player.isDead())
				return;
			CraftPlayer cp = (CraftPlayer) player;
			EntityPlayer ep = cp.getHandle();
			PlayerConnection pc = ep.playerConnection;
			PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
			pc.a(packet);
		}
	}.runTask(BukkitMain.getPlugin());

}

@EventHandler
public final static void ListenerPlaceBlockEvent(BlockPlaceEvent e) {
	if (GladiatorAPI.emluta.containsKey(e.getPlayer().getName())) {
		e.setCancelled(false);
	} else {
		e.setCancelled(true);
	}
}

@EventHandler
public final static void ListenerGetDamagerEvent(EntityDamageByEntityEvent e) {
	if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
		Player d = (Player) e.getDamager();
		damage.put((Player) e.getEntity(), d.getName());
	}
}

@EventHandler
public final static void CommandsBlockEvent(PlayerCommandPreprocessEvent e) {
	if (GladiatorAPI.emluta.containsKey(e.getPlayer().getName())) {
		if (e.getMessage().toLowerCase().startsWith("/gm") || (e.getMessage().toLowerCase().startsWith("/lobby") || (e.getMessage().toLowerCase().startsWith("/minecraft:gm") || (e.getMessage().toLowerCase().startsWith("/fly") || (e.getMessage().toLowerCase().startsWith("/kill") || (e.getMessage().toLowerCase().startsWith("/admin"))))))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§b§lGLADIATOR §fVocê não pode executar comandos em batalha.");	
		} else {
			if (!GladiatorAPI.emluta.containsKey(e.getPlayer().getName())) {
				e.setCancelled(false);
			}
		}
	}
}

@EventHandler
public final static void CommandsBlockedEvent2(PlayerCommandPreprocessEvent e) {
	if (e.getMessage().toLowerCase().startsWith("/plugins") || (e.getMessage().toLowerCase().startsWith("/me") || (e.getMessage().toLowerCase().startsWith("/minecraft:me") || (e.getMessage().toLowerCase().startsWith("/bukkit:help") || (e.getMessage().toLowerCase().startsWith("/help") || (e.getMessage().toLowerCase().startsWith("/bukkit:about") || (e.getMessage().toLowerCase().startsWith("/minecraft:kick") || (e.getMessage().toLowerCase().startsWith("/icanhasbukkit") || (e.getMessage().toLowerCase().startsWith("/ipwl") || (e.getMessage().toLowerCase().startsWith("/minecraft:ban") || (e.getMessage().toLowerCase().startsWith("/plugin") || (e.getMessage().toLowerCase().startsWith("/help") || (e.getMessage().toLowerCase().startsWith("/?") || (e.getMessage().toLowerCase().startsWith("/bukkit:ver") || (e.getMessage().toLowerCase().startsWith("/ver") || (e.getMessage().toLowerCase().startsWith("/about") || (e.getMessage().toLowerCase().startsWith("/pl")))))))))))))))))) {
		e.setCancelled(true);
		e.getPlayer().sendMessage("§c§lERRO §fEstes comandos de encontram bloqueados");
	}
}


@EventHandler
public final static void UnknowCommandEvent(PlayerCommandPreprocessEvent e) {
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
public final static void DamageInEntityEvent(EntityDamageByEntityEvent e) {
	if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
		Player p = (Player) e.getDamager();
		if (GladiatorAPI.emluta.containsKey(p.getName())) {
			e.setCancelled(false);
		} else {
			if (!GladiatorAPI.emluta.containsKey(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}

@EventHandler(priority = EventPriority.MONITOR)
public final static void FallDamageListener(EntityDamageEvent e) {
	Player p = (Player) e.getEntity();
	if (GladiatorAPI.emluta.containsKey(p.getName())) {
		e.setCancelled(false);
	} else {
		if (!GladiatorAPI.emluta.containsKey(p.getName())) {
			e.setCancelled(true);
			if(e.getCause() == DamageCause.FALL) {
				e.setCancelled(true);
			}
		}
	}
}


@EventHandler
public final static void BreakBlockEvent(BlockBreakEvent e) {
	Player p = e.getPlayer();
	if (GladiatorAPI.emluta.containsKey(p.getName())) {
		e.setCancelled(false);
	} else {
		e.setCancelled(true);
	}
}

@SuppressWarnings("deprecation")
@EventHandler(priority=EventPriority.MONITOR)
public final static void ListenerQuitInGame(PlayerQuitEvent e) {
	Player p = e.getPlayer();
	if (GladiatorAPI.emluta.containsKey(p.getName())) {
		Player matou = Bukkit.getPlayerExact(GladiatorAPI.emluta.get(p.getName()));
		BukkitMain.getitemDefault(matou);
		GladiatorAPI.removeArena(matou);

		if (ListenerItens.toFight != null) {
		}
		if (ListenerItens.toFight1 != null) {
		}
		GladiatorAPI.emluta.remove(matou.getName());
		matou.teleport(matou.getWorld().getSpawnLocation());
		matou.sendMessage("");
		matou.sendMessage("§4§lDESLOGOU §fSeu oponente §e" + p.getName() + " §fdeslogou!");
		matou.sendMessage("§a§lWIN §fVocê venceu a partida §c(Desistência)§f.");
		matou.sendMessage("§6§lXP §fVocê ganhou 25 Xp, §a+1 §fwinstreak e §a+1 §fwin.");
		matou.sendMessage("");
		damage.remove(matou);
		GladiatorAPI.removeArena(matou);
		// DADOS //
		WinsAPI.addWins(matou, 1);
		WsAPI.addWs(matou, 1);
		SkyAPI.addXp(matou, 25);

		p.setHealth(20);
		ListenerItens.toFight = null;
		ListenerItens.toFight1 = null;
	}
}

public final static void ListenerQuitEvent(PlayerQuitEvent e) {
	Player p = e.getPlayer();
	e.setQuitMessage(null);
	if (GladiatorAPI.emluta.containsKey(p.getName())) {
		ListenerItens.dawn.remove(p.getName());
		ListenerItens.desafiar.remove(p);
	}
	EventAdmin.jogadores.remove(p.getUniqueId());
	ListenerItens.toFight = null;
}

}

