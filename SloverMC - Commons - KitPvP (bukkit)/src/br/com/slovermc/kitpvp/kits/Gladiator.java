package br.com.slovermc.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class Gladiator implements Listener {
	
	public static final void showPlayer(final Player one, final Player two) {
		while (inFight.get(one.getName()) == two.getName() && inFight.get(two.getName()) == one.getName()) {
			one.showPlayer(two);
			two.showPlayer(one);
		}
	}
	
	public static final void setGladiatorKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Gladiator");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.getInventory().setItem(1, SpawnItens.newItem(Material.IRON_FENCE, "§3§lGladiator", 1, (byte) 0));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lGladiator");
		TittleAPI.sendTittle(bp, "§bKit Gladiator");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}

	public static final HashMap<String, String> inFight = new HashMap<>();
	public static final HashMap<String, Location> oldLocation = new HashMap<>();
	public static final HashMap<String, List<Location>> blocks = new HashMap<>();
	
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onGladiator(final PlayerInteractEntityEvent e) {
		final Player bp = e.getPlayer();
		if (e.getRightClicked() instanceof Player) {
			final Player toGlad = (Player) e.getRightClicked();
			if (KitAPI.getKit(bp) == "Gladiator" && bp.getItemInHand().getType() == Material.IRON_FENCE) {
				if (inFight.containsKey(bp.getName())) {
					return;
				}
				if (SpawnWarpListener.onWarpSpawnProtection.contains(toGlad)) {
					bp.sendMessage("§3§lGLADIATOR§f Este jogador esta com proteçao de spawn.");
					return;
				}
				newGladiatorArena(bp, toGlad, bp.getLocation());
			}
		}
	}
	
	public static int id = 0;

	@SuppressWarnings("deprecation")
	public static final Object newGladiatorArena(final Player p1, final Player p2, final Location loc) {
		if (id > 15) {
			id = 0;
		}
		id++;
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		Location loc1 = new Location(p1.getWorld(), x, y + 120, z);
		Location loc2 = new Location(p1.getWorld(), x, y + 120, z + 8);
		Location loc3 = new Location(p1.getWorld(), x - 8, y + 120, z - 8);
		loc1.getWorld().refreshChunk(loc1.getChunk().getX(), loc1.getChunk().getZ());

		final List<Location> location = new ArrayList<Location>();
		location.clear();
		for (int blockX = -10; blockX <= 10; blockX++) {
			for (int blockZ = -10; blockZ <= 10; blockZ++) {
				for (int blockY = -1; blockY <= 10; blockY++) {
					Block b = loc1.clone().add(blockX, blockY, blockZ).getBlock();
					if (!b.isEmpty()) {
						Random random = new Random();
						x = random.nextInt(500);
						z = random.nextInt(500);
						Location newLoc = new Location(p1.getWorld(), loc1.getBlockX() + x, 120, loc1.getBlockZ() + z);
						return newGladiatorArena(p1, p2, newLoc);
					}
					if (blockY == 10) {
						location.add(loc1.clone().add(blockX, blockY, blockZ));
					} else if (blockY == -1) {
						location.add(loc1.clone().add(blockX, blockY, blockZ));
					} else if ((blockX == -10) || (blockZ == -10) || (blockX == 10) || (blockZ == 10)) {
						location.add(loc1.clone().add(blockX, blockY, blockZ));
					}
				}
			}
		}
		for (Location arena : location) {
			arena.getBlock().setTypeIdAndData(95, (byte) id, true);
		}
		oldLocation.put(p1.getName(), p1.getLocation());
		oldLocation.put(p2.getName(), p2.getLocation());
		blocks.put(p1.getName(), location);
		blocks.put(p2.getName(), location);
		inFight.put(p1.getName(), p2.getName());
		inFight.put(p2.getName(), p1.getName());
		loc2.setYaw(135.0F);
		p1.teleport(new Location(p1.getWorld(), loc2.getX() + 8, loc2.getY() + 1, loc2.getZ() + 1));
		loc3.setYaw(-45.0F);
		p2.teleport(new Location(p2.getWorld(), loc3.getX() + 1.20, loc3.getY() + 1, loc1.getZ() + -8.5));

		p1.sendMessage("§3§lGLADIATOR§f Voce desafiou o player §b§l" + p2.getName() + "§f para uma batalha 1v1!");
		p2.sendMessage("§3§lGLADIATOR§f Voce foi desafiado pelo player " + p1.getName() + "§f para uma batalha 1v1!");
		showPlayer(p1, p2);

		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (blocks.containsKey(p1.getName()) && blocks.containsKey(p2.getName())) {
					if (blocks.get(p1.getName()) == location && blocks.get(p2.getName()) == location
							&& inFight.get(p1.getName()) == p2.getName() && inFight.get(p2.getName()) == p1.getName()) {
						p1.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10000, 2));
						p2.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10000, 2));
					}
				}
			}
		}, 2400L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (blocks.containsKey(p1.getName()) && blocks.containsKey(p2.getName())) {
					if (blocks.get(p1.getName()) == location && blocks.get(p2.getName()) == location
							&& inFight.get(p1.getName()) == p2.getName() && inFight.get(p2.getName()) == p1.getName()) {
						p1.teleport(oldLocation.get(p1.getName()));
						p2.teleport(oldLocation.get(p2.getName()));
						for (PotionEffect pot : p1.getActivePotionEffects()) {
							p1.removePotionEffect(pot.getType());
						}
						for (PotionEffect pot : p2.getActivePotionEffects()) {
							p2.removePotionEffect(pot.getType());
						}
						for (Location loc : blocks.get(p1.getName())) {
							loc.getBlock().setType(Material.AIR);
						}
						for (Location loc : blocks.get(p2.getName())) {
							loc.getBlock().setType(Material.AIR);
						}
						blocks.remove(p1.getName());
						inFight.remove(p1.getName());
						oldLocation.remove(p1.getName());
						blocks.remove(p2.getName());
						inFight.remove(p2.getName());
						oldLocation.remove(p2.getName());
						
						p1.sendMessage("§3§lGLADIATOR§f Nao houve vencedores! Voce foi teleportado para onde estava.");
						p2.sendMessage("§3§lGLADIATOR§f Nao houve vencedores! Voce foi teleportado para onde estava.");
					}
				}
			}
		}, 3600L);
		return null;
	}
	
	public static final void resetGladiatorByKill(final Player winner, final Player loser) {
		for (int i = 1; i < 5; i++) {
			winner.teleport(oldLocation.get(winner.getName()));
		}
		for (int i = 1; i < 5; i++) {
			loser.teleport(oldLocation.get(loser.getName()));
		}
		for (PotionEffect pot : winner.getActivePotionEffects()) {
			winner.removePotionEffect(pot.getType());
		}
		for (PotionEffect pot : loser.getActivePotionEffects()) {
			loser.removePotionEffect(pot.getType());
		}
		for (Location loc : blocks.get(winner.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : blocks.get(loser.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		blocks.remove(winner.getName());
		inFight.remove(winner.getName());
		oldLocation.remove(winner.getName());
		blocks.remove(loser.getName());
		inFight.remove(loser.getName());
		oldLocation.remove(loser.getName());
		
		winner.sendMessage("§3§lGLADIATOR§f Voce venceu a batalha contra §b§l" + loser.getName());
		loser.sendMessage("§3§lGLADIATOR§f Voce perdeu a batalha contra §b§l" + winner.getName());
	}
	
	public static final void resetGladiatorByScreenshare(final Player winner, final Player loser) {
		for (int i = 1; i < 5; i++) {
			winner.teleport(oldLocation.get(winner.getName()));
		}
		for (PotionEffect pot : winner.getActivePotionEffects()) {
			winner.removePotionEffect(pot.getType());
		}
		for (PotionEffect pot : loser.getActivePotionEffects()) {
			loser.removePotionEffect(pot.getType());
		}
		for (Location loc : blocks.get(winner.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : blocks.get(loser.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		blocks.remove(winner.getName());
		inFight.remove(winner.getName());
		oldLocation.remove(winner.getName());
		blocks.remove(loser.getName());
		inFight.remove(loser.getName());
		oldLocation.remove(loser.getName());
		
		winner.sendMessage("§c" + loser.getName() + " foi puxado para screenshare.");
	}
	
	public static final void resetGladiatorByTpAll(final Player winner, final Player loser) {
		for (PotionEffect pot : winner.getActivePotionEffects()) {
			winner.removePotionEffect(pot.getType());
		}
		for (PotionEffect pot : loser.getActivePotionEffects()) {
			loser.removePotionEffect(pot.getType());
		}
		for (Location loc : blocks.get(winner.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : blocks.get(loser.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		blocks.remove(winner.getName());
		inFight.remove(winner.getName());
		oldLocation.remove(winner.getName());
		blocks.remove(loser.getName());
		inFight.remove(loser.getName());
		oldLocation.remove(loser.getName());
	}
	
	public static final void resetGladiatorByQuit(final Player winner, final Player loser) {
		for (int i = 1; i < 5; i++) {
			winner.teleport(oldLocation.get(winner.getName()));
		}
		for (PotionEffect pot : winner.getActivePotionEffects()) {
			winner.removePotionEffect(pot.getType());
		}
		for (PotionEffect pot : loser.getActivePotionEffects()) {
			loser.removePotionEffect(pot.getType());
		}
		for (Location loc : blocks.get(winner.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		for (Location loc : blocks.get(loser.getName())) {
			loc.getBlock().setType(Material.AIR);
		}
		blocks.remove(winner.getName());
		inFight.remove(winner.getName());
		oldLocation.remove(winner.getName());
		blocks.remove(loser.getName());
		inFight.remove(loser.getName());
		oldLocation.remove(loser.getName());
		
		winner.sendMessage("§3§lGLADIATOR§f O player §b§l" + loser.getName() + "§f deslogou.");
	}
}
