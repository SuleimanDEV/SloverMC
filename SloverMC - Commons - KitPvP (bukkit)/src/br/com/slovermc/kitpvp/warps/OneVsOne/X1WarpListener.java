package br.com.slovermc.kitpvp.warps.OneVsOne;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.KillsAPI;
import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.deathevents.Status;
import br.com.slovermc.kitpvp.mysql.SkyAPI;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class X1WarpListener implements Listener {
	
	@SuppressWarnings("deprecation")
	public static ItemStack searchingItem() {
		ItemStack i = new ItemStack(351, 1, (byte) 10);
		ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§aProcurando partidas");
		i.setItemMeta(ik);
		return i;
	}
	
	public static ItemStack customItem() {
		ItemStack i = new ItemStack(Material.IRON_FENCE, 1, (byte) 0);
		ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§a1v1 Customizado §7(Clique)");
		i.setItemMeta(ik);
		return i;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack backItem() {
		ItemStack i = new ItemStack(351, 1, (byte) 8);
		ItemMeta ik = i.getItemMeta();
		ik.setDisplayName("§a1v1 Rápido §7(Clique)");
		i.setItemMeta(ik);
		return i;
	}

	public static final HashMap<Player, String> batalhando = new HashMap<>();
	
	public static boolean wait = false;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		final ItemStack i = e.getPlayer().getItemInHand();
		if (i.getType() == Material.getMaterial(351)) {
			if (i.getItemMeta().getDisplayName().equals("§a1v1 Rápido §7(Clique)")) {
				e.getPlayer().updateInventory();
				e.getPlayer().setItemInHand(searchingItem());
				e.getPlayer().updateInventory();
				e.getPlayer().sendMessage("§a§lFAST1v1 §fO 1v1 Rápido está procurando alguém para você batalhar!");
				secondMatch = null;
				if (firstMatch == null) {
					firstMatch = e.getPlayer().getUniqueId();
					return;
				} else {
					Player findToChallenge = Bukkit.getPlayer(firstMatch);
					teleportToFight(e.getPlayer(), findToChallenge);
					fighting.add(e.getPlayer());
					fighting.add(findToChallenge);
					playerfigh.put(e.getPlayer(), findToChallenge.getName());
					playerfigh.put(findToChallenge, e.getPlayer().getName());
					batalhando.put(e.getPlayer(), findToChallenge.getName());
					batalhando.put(findToChallenge, e.getPlayer().getName());
					e.getPlayer().sendMessage(
							"§a§lFAST1v1 §fO 1v1 Rápido encontrou alguém para você lutar! O player escolhido foi §e" + findToChallenge.getName() + "§f.");
					findToChallenge
							.sendMessage("§a§lFAST1v1 §fO 1v1 Rápido encontrou alguém para você lutar! O player escolhido foi §e" + e.getPlayer().getName() + "§f.");
					firstMatch = null;
					secondMatch = null;
				}
			} else if (i.getItemMeta().getDisplayName().equals("§aProcurando partidas")) {
				firstMatch = null;
				secondMatch = null;
				e.getPlayer().setItemInHand(backItem());
				e.getPlayer().updateInventory();
				e.getPlayer().sendMessage("§a§lFAST1v1 §fO 1v1 Rápido parou de procurar alguém para você batalhar!");
			}
		}
	}

	@EventHandler
	public void onChallengeInteract(final PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		final Player challenged = (Player) e.getRightClicked();
		final ItemStack i = e.getPlayer().getItemInHand();
		if (WarpsAPI.inOnevsOne.contains(e.getPlayer().getName()) && WarpsAPI.inOnevsOne.contains(challenged.getName())) {
			if (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
				if (i.getItemMeta().getDisplayName().equals("§a1v1 Normal §7(Clique)")) {
					if (cooldown.contains(e.getPlayer())) {
						e.getPlayer().sendMessage("§c§lERRO §fAguarde para desafiar novamente.");
						return;
					}
					if (challenge.containsKey(challenged) && challenge.get(challenged) == e.getPlayer()) {
						if (firstMatch == e.getPlayer().getUniqueId()) {
							firstMatch = null;
						}
						if (firstMatch == challenged.getUniqueId()) {
							firstMatch = null;
						}
						teleportToFight(e.getPlayer(), challenged);
						fighting.add(e.getPlayer());
						fighting.add(challenged);
						playerfigh.put(e.getPlayer(), challenged.getName());
						playerfigh.put(challenged, e.getPlayer().getName());
						batalhando.put(challenged, e.getPlayer().getName());
						batalhando.put(e.getPlayer(), challenged.getName());
						challenged
								.sendMessage("§2§lDESAFIO §b" + e.getPlayer().getName() + "§f aceitou seu desafio.");
						e.getPlayer()
								.sendMessage("§2§lDESAFIO §fVocê aceitou o desafio de §b" + challenged.getName() + "§f.");
						challenge.remove(challenged);
						if (challenge.containsKey(e.getPlayer())) {
							challenge.remove(e.getPlayer());
						}
						return;
					}
					if (playerfigh.containsKey(challenged)) {
						return;
					}
					e.getPlayer().sendMessage(
							"§2§lDESAFIO §fVocê enviou um desafio de 1v1 normal para §b" + challenged.getName() + "§f.");
					challenged.sendMessage("§2§lDESAFIO §fVocê recebeu desafio de 1v1 normal de §7" + e.getPlayer().getName() + "§f.");
					cooldown.add(e.getPlayer());
					challenge.put(e.getPlayer(), challenged);
					Bukkit.getScheduler().runTaskLater(BukkitMain.getPlugin(), new Runnable() {
						@Override
						public void run() {
							if (cooldown.contains(e.getPlayer())) {
								cooldown.remove(e.getPlayer());
							}
							if (challenge.containsKey(e.getPlayer())) {
								challenge.remove(e.getPlayer());
							}
						}
					}, 5 * 20);
				}
			}
			if (e.getPlayer().getItemInHand().getType() == Material.IRON_FENCE) {
				if (i.getItemMeta().getDisplayName().equals("§a1v1 Customizado §7(Clique)")) {
					if (cooldown.contains(e.getPlayer())) {
						e.getPlayer().sendMessage("§c§lERRO §fAguarde para desafiar novamente.");
						return;
					}
					if (challengec.containsKey(challenged) && challengec.get(challenged) == e.getPlayer()) {
						if (firstMatch == e.getPlayer().getUniqueId()) {
							firstMatch = null;
						}
						if (firstMatch == challenged.getUniqueId()) {
							firstMatch = null;
						}
						teleportToCustomFight(challenged, e.getPlayer());
						fighting.add(e.getPlayer());
						fighting.add(challenged);
						playerfigh.put(e.getPlayer(), challenged.getName());
						playerfigh.put(challenged, e.getPlayer().getName());
						batalhando.put(challenged, e.getPlayer().getName());
						batalhando.put(e.getPlayer(), challenged.getName());
						challenged
								.sendMessage("§2§lDESAFIO §b" + e.getPlayer().getName() + "§f aceitou seu desafio.");
						e.getPlayer()
								.sendMessage("§2§lDESAFIO §fVocê aceitou o desafio de §b" + challenged.getName() + "§f.");
						challengec.remove(challenged);
						if (challengec.containsKey(e.getPlayer())) {
							challengec.remove(e.getPlayer());
						}
						if (challenge.containsKey(e.getPlayer())) {
							challenge.remove(e.getPlayer());
						}
						if (challenge.containsKey(challenged)) {
							challenge.remove(challenged);
						}
						return;
					}
					if (playerfigh.containsKey(challenged)) {
						return;
					}
					Inventory1v1Custom.setDefaultCustoms(e.getPlayer(), challenged.getName());
					Inventory1v1Custom.openCustomInventory(e.getPlayer(), challenged);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static int itemsInInventory(Inventory inventory, Material[] search) {
		List wanted = Arrays.asList(search);
		int found = 0;
		ItemStack[] arrayOfItemStack;
		int j = (arrayOfItemStack = inventory.getContents()).length;
		for (int i = 0; i < j; i++) {
			ItemStack item = arrayOfItemStack[i];
			if ((item != null) && (wanted.contains(item.getType()))) {
				found += item.getAmount();
			}
		}
		return found;
	}

	public static String cora(Player p) {
		Damageable vida = p;
		return NumberFormat.getCurrencyInstance().format(vida.getHealth() / 2).replace("$", "").replace("R", "").replace(",", ".");
	}

	@SuppressWarnings("deprecation")
	public static void show(Player p) {
		for (Player t : Bukkit.getOnlinePlayers()) {
			p.showPlayer(t);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(final PlayerQuitEvent e) {
		e.setQuitMessage(null);
		WarpsAPI.resetPlayerWarps(e.getPlayer());
		if (playerfigh.containsKey(e.getPlayer())) {
			Player w = Bukkit.getPlayerExact(playerfigh.get(e.getPlayer()));
			fighting.remove(e.getPlayer());
			fighting.remove(w);
			playerfigh.remove(e.getPlayer());
			playerfigh.remove(w);
			defaultItens(w);
			LocationsConstructor.teleportToBattleWarpLocation(w, "1v1");
			for (int i = 6; i > 0; i--) {
				show(w);
			}
			if (batalhando.containsKey(w)) {
				batalhando.remove(w);
			}
			if (batalhando.containsKey(e.getPlayer())) {
				batalhando.remove(e.getPlayer());
			}

			w.sendMessage("§6§lAVISO §e" + e.getPlayer().getName() + " §fdeslogou.");
		    KillsAPI.addKills(w, 1);
			Status.addMoneyToBattlePlayer(w, 100);
			SkyAPI.addXp(w, 25);
			Status.removeStreakFromBattlePlayer(e.getPlayer(), w);
			Status.addStreakToBattlePlayer(w);

			w.setHealth(20);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (WarpsAPI.battlePlayerWarp.get(p) == WarpsAPI.Warps.ONEVSONE && !playerfigh.containsKey(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	public static UUID firstMatch;
	public static UUID secondMatch;
	public static UUID thirdMatch;
	
	public static final ArrayList<Player> cooldown = new ArrayList<>();	
	public static final HashMap<Player, Player> challenge = new HashMap<>();
	
	public static final HashMap<Player, Player> challengec = new HashMap<>();
	
	public static final ArrayList<Player> fighting = new ArrayList<>();
	public static final HashMap<Player, String> playerfigh = new HashMap<>();
	
	public static ItemStack newItem(Material material, int qnt, byte color) {
		ItemStack i = new ItemStack(material, qnt, (byte) color);
		return i;
	}
	
	public static ItemStack newItem(Material material, String name, int qnt, byte color) {
		ItemStack i = new ItemStack(material, qnt, (byte) color);
		ItemMeta ik = i.getItemMeta();
		ik.setDisplayName(name);
		i.setItemMeta(ik);
		return i;
	}
	
	public static ItemStack newItemEnchant(Material material, Enchantment ench, int qnt, byte color) {
		ItemStack i = new ItemStack(material, qnt, (byte) color);
		ItemMeta ik = i.getItemMeta();
		ik.addEnchant(ench, 1, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static void prepareInventory(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setBoots(newItem(Material.IRON_BOOTS, 1, (byte) 0));
		p.getInventory().setLeggings(newItem(Material.IRON_LEGGINGS, 1, (byte) 0));
		p.getInventory().setChestplate(newItem(Material.IRON_CHESTPLATE, 1, (byte) 0));
		p.getInventory().setHelmet(newItem(Material.IRON_HELMET, 1, (byte) 0));
		p.getInventory().setItem(0, newItemEnchant(Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 1, (byte) 0));
		for (int i = 8; i > 0; i--) {
			p.getInventory().setItem(i, newItem(Material.MUSHROOM_SOUP, 1, (byte) 0));
		}
		p.updateInventory();
	}
	
	public static final void loadWarp1v1Methods(final Player bp) {
		if (FpsWarpListener.onFpsSpawnProtection.contains(bp)) {
			FpsWarpListener.onFpsSpawnProtection.remove(bp);
		}
		if (SpawnWarpListener.onWarpSpawnProtection.contains(bp)) {
			SpawnWarpListener.onWarpSpawnProtection.remove(bp);
		}
		defaultItens(bp);
		WarpAPI.setWarp(bp, "1v1");
		WarpsAPI.battlePlayerWarp.put(bp, WarpsAPI.Warps.ONEVSONE);
		Score.createScore1v1(bp);
	}
	
	public static void defaultItens(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(3, newItem(Material.BLAZE_ROD, "§a1v1 Normal §7(Clique)", 1, (byte) 0));
		p.getInventory().setItem(4, customItem());
		p.getInventory().setItem(5, backItem());
		p.updateInventory();
	}
	
	@SuppressWarnings("deprecation")
	public static void teleportToFight(Player p1, Player p2) {
		LocationsConstructor.teleportToBattleWarpLocation(p1, "1v1loc1");
		LocationsConstructor.teleportToBattleWarpLocation(p2, "1v1loc2");
		prepareInventory(p1);
		prepareInventory(p2);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p1.hidePlayer(p);
			p2.hidePlayer(p);
		}
		for (int i = 6; i > 0; i--) {
			p1.showPlayer(p2);
			p2.showPlayer(p1);
		}
	}

	
	@SuppressWarnings("deprecation")
	public static void teleportToCustomFight(Player p1, Player p2) {
		LocationsConstructor.teleportToBattleWarpLocation(p1, "1v1loc1");
		LocationsConstructor.teleportToBattleWarpLocation(p2, "1v1loc2");
		Inventory1v1Custom.loadItensCustom(p1, p2);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p1.hidePlayer(p);
			p2.hidePlayer(p);
		}
		for (int i = 6; i > 0; i--) {
			p1.showPlayer(p2);
			p2.showPlayer(p1);
		}
	}
}
