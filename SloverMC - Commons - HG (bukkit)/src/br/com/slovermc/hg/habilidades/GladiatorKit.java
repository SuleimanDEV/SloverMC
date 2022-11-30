package br.com.slovermc.hg.habilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GladiatorKit implements Listener {
	
	public static boolean generateGlass = true;
	public static HashMap<String, Location> oldl = new HashMap();
	public static HashMap<String, String> fighting = new HashMap();
	public static HashMap<Player, Location> localizacao = new HashMap();
	public static HashMap<Location, Block> bloco = new HashMap();
	public static HashMap<Integer, String[]> players = new HashMap();
	public static HashMap<String, Integer> tasks = new HashMap();
	public static List<BlockState> blockplaced = new ArrayList();
	public static int nextID = 0;
	public static int id1;
	public static int id2;

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onPlace2(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (fighting.containsKey(player)) {
			blockplaced.add(e.getBlockReplacedState());
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		if (block == bloco) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (((KitAPI.getInstance().hasKit(p, "Gladiator")) || (KitAPI.getInstance().hasKit2(p, "Gladiator")))
				&& (e.getItemInHand().getType() == Material.IRON_FENCE)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void OnGladiatorKit(PlayerInteractEntityEvent event) {
		final Player p = event.getPlayer();
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		if (BukkitMain.state == StateEnum.GAME) {
			final Player r = (Player) event.getRightClicked();
			if ((p.getItemInHand().getType() == Material.IRON_FENCE) && ((KitAPI.getInstance().hasKit(p, "Gladiator"))
					|| (KitAPI.getInstance().hasKit2(p, "Gladiator")))) {
				event.setCancelled(true);
				Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY() + 70,
						p.getLocation().getBlockZ());
				localizacao.put(p, loc);
				localizacao.put(r, loc);
				Location loc2 = new Location(p.getWorld(), p.getLocation().getBlockX() + 8,
						p.getLocation().getBlockY() + 73, p.getLocation().getBlockZ() + 8);
				Location loc3 = new Location(p.getWorld(), p.getLocation().getBlockX() - 8,
						p.getLocation().getBlockY() + 73, p.getLocation().getBlockZ() - 8);
				if ((fighting.containsKey(p.getName())) || (fighting.containsKey(r.getName()))) {
					event.setCancelled(true);
					p.sendMessage("§b§lGLADIATOR §fVocê já está em um gladiator!");
					return;
				}
				Integer currentID = Integer.valueOf(nextID);
				nextID += 1;
				ArrayList<String> list = new ArrayList();
				list.add(p.getName());
				list.add(r.getName());
				players.put(currentID, (String[]) list.toArray(new String[1]));
				oldl.put(p.getName(), p.getLocation());
				oldl.put(r.getName(), r.getLocation());
				if (generateGlass) {
					List<Location> cuboid = new ArrayList();
					cuboid.clear();
					int bY;
					for (int bX = -10; bX <= 10; bX++) {
						for (int bZ = -10; bZ <= 10; bZ++) {
							for (bY = -1; bY <= 10; bY++) {
								Block b = loc.clone().add(bX, bY, bZ).getBlock();
								if (!b.isEmpty()) {
									event.setCancelled(true);
									p.sendMessage("§b§lGLADIATOR §fJá possui uma arena aqui em cima!");
									return;
								}
								if (bY == 10) {
									cuboid.add(loc.clone().add(bX, bY, bZ));
								} else if (bY == -1) {
									cuboid.add(loc.clone().add(bX, bY, bZ));
								} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
									cuboid.add(loc.clone().add(bX, bY, bZ));
								}
							}
						}
					}
					for (Location loc1 : cuboid) {
						loc1.getBlock().setType(Material.GLASS);
						bloco.put(loc1, loc1.getBlock());
					}
					loc2.setYaw(135.0F);
					p.teleport(loc2);
					loc3.setYaw(-45.0F);
					r.teleport(loc3);
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
					r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
					p.sendMessage("§b§lGLADIATOR §fVocê desafiou §e" + r.getName() + " §fpara um 1v1!");
					r.sendMessage("§b§lGLADIATOR §fVocê foi desafiado por §e" + p.getName() + " §fpara um 1v1!");
					fighting.put(p.getName(), r.getName());
					fighting.put(r.getName(), p.getName());
					id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
						public void run() {
							if ((GladiatorKit.fighting.containsKey(p.getName()))
									&& (((String) GladiatorKit.fighting.get(p.getName())).equalsIgnoreCase(r.getName()))
									&& (GladiatorKit.fighting.containsKey(r.getName()))
									&& (((String) GladiatorKit.fighting.get(r.getName()))
											.equalsIgnoreCase(p.getName()))) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2400, 0));
								r.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2400, 0));
							}
						}
					}, 2400L);
					id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
						public void run() {
							if ((GladiatorKit.fighting.containsKey(p.getName()))
									&& (((String) GladiatorKit.fighting.get(p.getName())).equalsIgnoreCase(r.getName()))
									&& (GladiatorKit.fighting.containsKey(r.getName()))
									&& (((String) GladiatorKit.fighting.get(r.getName()))
											.equalsIgnoreCase(p.getName()))) {
								GladiatorKit.fighting.remove(p.getName());
								GladiatorKit.fighting.remove(r.getName());
								p.teleport((Location) GladiatorKit.oldl.get(p.getName()));
								r.teleport((Location) GladiatorKit.oldl.get(r.getName()));
								GladiatorKit.oldl.remove(p.getName());
								GladiatorKit.oldl.remove(r.getName());
								p.sendMessage("§b§lGLADIATOR §fNinguém venceu, voltando ao local de origem!");
								r.sendMessage("§b§lGLADIATOR §fNinguém venceu, voltando ao local de origem!");
								Location loc = (Location) GladiatorKit.localizacao.get(p);
								List<Location> cuboid = new ArrayList();
								int bY;
								for (int bX = -10; bX <= 10; bX++) {
									for (int bZ = -10; bZ <= 10; bZ++) {
										for (bY = -1; bY <= 10; bY++) {
											if (bY == 10) {
												cuboid.add(loc.clone().add(bX, bY, bZ));
											} else if (bY == -1) {
												cuboid.add(loc.clone().add(bX, bY, bZ));
											} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
												cuboid.add(loc.clone().add(bX, bY, bZ));
											}
										}
									}
								}
								for (Location loc1 : cuboid) {
									if (loc1.getBlock().getType() != Material.GLASS) {
										loc1.getBlock().setType(Material.AIR);
									}
									((Block) bloco.get(loc1)).setType(Material.AIR);
									for (BlockState block : blockplaced) { 
										block.setType(block.getType());
									}
									blockplaced.clear();
								}
							}
						}
					}, 4800L);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteractGlad(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((p.getItemInHand().getType() == Material.IRON_FENCE)
				&& ((KitAPI.getInstance().hasKit(p, "Gladiator")) || (KitAPI.getInstance().hasKit2(p, "Gladiator")))) {
			e.setCancelled(true);
			p.updateInventory();
			return;
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlyaerInteract(final PlayerInteractEvent e) {
		if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.GLASS)
				&& (e.getPlayer().getGameMode() != GameMode.CREATIVE)
				&& (fighting.containsKey(e.getPlayer().getName()))) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.BEDROCK);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
				public void run() {
					if (GladiatorKit.fighting.containsKey(e.getPlayer().getName())) {
						e.getClickedBlock().setType(Material.GLASS);
					}
				}
			}, 100L);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLeft(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (fighting.containsKey(p.getName())) {
			Player t = Bukkit.getServer().getPlayer((String) fighting.get(p.getName()));
			fighting.remove(t.getName());
			fighting.remove(p.getName());
			Location old = (Location) oldl.get(t.getName());
			t.teleport(old);
			t.sendMessage("§b§lGLADIATOR §fO jogador §e" + p.getName() + " §fdeslogou!");
			Bukkit.getScheduler().cancelTask(id1);
			Bukkit.getScheduler().cancelTask(id2);
			t.removePotionEffect(PotionEffectType.WITHER);
			Location loc = (Location) localizacao.get(p);
			List<Location> cuboid = new ArrayList();
			int bY;
			for (int bX = -10; bX <= 10; bX++) {
				for (int bZ = -10; bZ <= 10; bZ++) {
					for (bY = -1; bY <= 10; bY++) {
						if (bY == 10) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						}
					}
				}
			}
			for (Location loc1 : cuboid) {
				for (BlockState block : blockplaced) { 
					block.setType(block.getType());
				}
				loc1.getBlock().setType(Material.AIR);
				((Block) bloco.get(loc1)).setType(Material.AIR);
				blockplaced.clear();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeathGladiator(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (fighting.containsKey(p.getName())) {
			Player k = Bukkit.getServer().getPlayer((String) fighting.get(p.getName()));
			for (ItemStack items : e.getDrops()) {
				if ((items.getType() != Material.AIR) && (items.getType() != null) && (items.getTypeId() != 0)) {
					Bukkit.getWorld("world").dropItemNaturally((Location) oldl.get(k.getName()), items.clone());
				}
			}
			e.getDrops().clear();
			Location old = (Location) oldl.get(p.getName());
			k.teleport(old);
			p.getInventory().clear();
			k.sendMessage("§b§lGLADIATOR §fVocê venceu o 1v1 contra §e" + p.getName() + "§f.");
			Bukkit.getScheduler().cancelTask(id1);
			Bukkit.getScheduler().cancelTask(id2);
			k.removePotionEffect(PotionEffectType.WITHER);
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
			fighting.remove(k.getName());
			fighting.remove(p.getName());
			Location loc = (Location) localizacao.get(p);
			List<Location> cuboid = new ArrayList();
			cuboid.clear();
			int bY;
			for (int bX = -10; bX <= 10; bX++) {
				for (int bZ = -10; bZ <= 10; bZ++) {
					for (bY = -1; bY <= 10; bY++) {
						if (bY == 10) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						}
					}
				}
			}
			for (Location loc1 : cuboid) {
				for (BlockState block : blockplaced) { 
					block.setType(block.getType());
				}
				loc1.getBlock().setType(Material.AIR);
				((Block) bloco.get(loc1)).setType(Material.AIR);
				blockplaced.clear();
			}
			return;
		}
	}
}
