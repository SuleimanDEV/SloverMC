package br.com.slovermc.hg.tempos;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.api.StatsAPI;
import br.com.slovermc.hg.commands.cLobbyCMD;
import br.com.slovermc.hg.manager.FeastManager;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.scoreboard.Score;
import br.com.slovermc.hg.utils.Countdown;
import br.com.slovermc.hg.utils.Permission;

@SuppressWarnings({ "deprecation" })
public class GameListeners implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.GAME || (BukkitMain.state == StateEnum.INVINCIBILITY) || (BukkitMain.state == StateEnum.END)) {
			Bukkit.setWhitelist(false);
			if (!player.hasPermission("slovermc.espectar") || !player.hasPermission("slovermc.admin")) {
				e.disallow(PlayerLoginEvent.Result.KICK_OTHER,
						"§b§lHUNGERGAMES §fO torneio já iniciou, aguarde para entrar quando estiver aberto!");
			} else {
				e.allow();
			}
			if (!player.hasPermission("slovermc.espectar") || !player.hasPermission("slovermc.admin")) {
				e.disallow(PlayerLoginEvent.Result.KICK_OTHER,
						"§b§lHUNGERGAMES §fO torneio já iniciou, aguarde para entrar quando estiver aberto!");
			} else {
				e.allow();
			}
			if (!player.hasPermission("slovermc.espectar") || !player.hasPermission("slovermc.admin")) {
				e.disallow(PlayerLoginEvent.Result.KICK_OTHER,
						"§b§lHUNGERGAMES §fO torneio já iniciou, aguarde para entrar quando estiver aberto!");
			} else {
				e.allow();
			}
		}
	}

	public void Respawnin5mins(PlayerRespawnEvent event) {
		if (BukkitMain.state == StateEnum.GAME) {
			if (event.getPlayer().hasPermission("slovermc.renascer") || event.getPlayer().hasPermission("slovermc.renascer")) {
				if (Countdown.getCountDown().getTime() > 300) {
				}
			}
		}
	}

	@EventHandler
	public final static void PegarItem(BlockBreakEvent e) {
		Block block = e.getBlock();
		Player p = e.getPlayer();

		if (block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE) {
			block.setType(Material.AIR);

			ItemStack cobble = new ItemStack(Material.COBBLESTONE, 1);
			p.getInventory().addItem(cobble);
			p.updateInventory();

		} else if (block.getType() == Material.BROWN_MUSHROOM) {
			block.setType(Material.AIR);
			ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 1);

			p.getInventory().addItem(marrom);
			p.updateInventory();

		} else if (block.getType() == Material.RED_MUSHROOM) {
			e.getBlock().setType(Material.AIR);

			ItemStack red = new ItemStack(Material.RED_MUSHROOM, 1);
			p.getInventory().addItem(red);
			p.updateInventory();

		} else if (block.getType() == Material.COCOA) {
			block.setType(Material.AIR);
			ItemStack cocoa = new ItemStack(Material.getMaterial(351), 3, (byte) 3);
			p.getInventory().addItem(cocoa);
			p.updateInventory();

		} else if (block.getType() == Material.CACTUS) {
			block.setType(Material.AIR);

			ItemStack cacto = new ItemStack(Material.CACTUS, 1);
			p.getInventory().addItem(cacto);
			p.updateInventory();

		} else {
			e.setCancelled(false);

			if (block.getType() == Material.RED_MUSHROOM) {
				block.setType(Material.AIR);

				ItemStack red = new ItemStack(Material.RED_MUSHROOM, 1);
				p.getInventory().addItem(red);
				p.updateInventory();

			} else if (block.getType() == Material.BROWN_MUSHROOM) {
				block.setType(Material.AIR);

				ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 1);
				p.getInventory().addItem(marrom);
				p.updateInventory();

			} else if (block.getType() == Material.CACTUS) {
				block.setType(Material.AIR);

				ItemStack cacto = new ItemStack(Material.CACTUS, 1);
				p.getInventory().addItem(cacto);
				p.updateInventory();

			} else if (block.getType() == Material.COCOA) {
				block.setType(Material.AIR);

				ItemStack cocoa = new ItemStack(Material.getMaterial(351), 5, (byte) 3);
				p.getInventory().addItem(cocoa);
				p.updateInventory();

			} else {
				e.setCancelled(false);
			}
		}
	}

	@EventHandler
	protected void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.GAME) {
			KitAPI.getInstance().removeKit(player);
			KitAPI.getInstance().removeKit2(player);
			Score.hasScore.add(player);
			Score.createScoreTempo(player);
			if (player.hasPermission("slovermc.admin")) {
				player.performCommand("admin");
			} else if (player.hasPermission("slovermc.espectar")) {
				Manager.getInstance().setSpectador(player);
			}
		}
	}

	@EventHandler
	protected void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.GAME || (BukkitMain.state == StateEnum.INVINCIBILITY)) {
			PlayerAPI.getInstance().removePlayer(player);
			Manager.getInstance().checkWin();
				String player_name = player.getName();
				String kit_name = null;
			if (!BukkitMain.getPlugin().duoKit()) {
				kit_name = KitAPI.getInstance().getKit(player);
			} else {
				kit_name = KitAPI.getInstance().getKit(player) + ", " + KitAPI.getInstance().getKit2(player);
			}
			Bukkit.broadcastMessage(
					"§b" + player_name + "(" + kit_name + ") §bsaiu da partida");
			//Bukkit.broadcastMessage("§e" + PlayerAPI.getInstance().getPlayers().length + " Jogadores restantes.");
		}
	}

	@EventHandler
	public static void PegarItens(PlayerPickupItemEvent e) {
		if (BukkitMain.state == StateEnum.GAME) {
			e.setCancelled(false);
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	protected void onPlayerDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity();
		if (BukkitMain.state != StateEnum.GAME) {
			e.setDeathMessage(null);
			return;
		}
		String mensagem = null;
		String kit = null;
		if (!BukkitMain.getPlugin().duoKit()) {
			kit = KitAPI.getInstance().getKit(p);
		} else {
			kit = KitAPI.getInstance().getKit(p) + ", " + KitAPI.getInstance().getKit2(p);
		}
		String name = p.getName();
		StatsAPI.getInstance().addDeaths(p, 1);
		if (p.getKiller() instanceof Player) {
			Player k = p.getKiller();
			PlayerAPI.getInstance().addKills(k, 1);
			StatsAPI.getInstance().addKills(k, 1);
			Bukkit.getWorld("world").strikeLightningEffect(k.getLocation());
			String kit2 = "";
			if (!BukkitMain.getPlugin().duoKit()) {
				kit2 = KitAPI.getInstance().getKit(k);
			} else {
				kit2 = KitAPI.getInstance().getKit(k) + ", " + KitAPI.getInstance().getKit2(k);
			}
			String kname = k.getName();
			mensagem = "§bO jogador " + kname + "(" + kit + ") matou " + p.getName() + "(" + kit2
					+ ") usando seu " + name(k.getItemInHand()) + ".";
		} else if (p.getKiller() instanceof Monster) {
			Monster m = (Monster) p.getKiller();
			mensagem = "§b" + name + "(" + kit + ") §bmorreu para um " + m.getType().toString() + ".";
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
			mensagem = "§b" + name + "(" + kit + ") §bficou muito perto de uma tnt.";
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
			mensagem = "§b" + name + "(" + kit + ") §btentou nadar e acabou morrendo.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
			mensagem = "§b" + name + "(" + kit + ") §bficou muito perto de um creeper.";
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.FALL)) {
			mensagem = "§b" + name + "(" + kit + ") §bachou que era o superman.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu para um bloco voador.";
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.FIRE)) {
			mensagem = "§b" + name + "(" + kit + ") §btentou brincar com o fogo.";
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
			mensagem = "§b" + name + "(" + kit + ") §btentou brincar com o fogo.";
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
			mensagem = "§b" + name + "(" + kit + ") §btentou nadar na lava.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu pelo machado do thor.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.MAGIC)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu para a magia.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.POISON)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu envenenado.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
			mensagem = "§b" + name + "(" + kit + ") §bfoi acertado por um projétil.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu sufocado.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.THORNS)) {
			mensagem = "§b" + name + "(" + kit + ") §bficou muito perto do cacto.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.WITHER)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu em decomposição.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.VOID)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu para o void.";	
		} else if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.SUICIDE)) {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu de queda e foi eliminado do torneio.";	
		} else {
			mensagem = "§b" + name + "(" + kit + ") §bmorreu de uma forma desconhecida.";
		}
		if (p.hasPermission(Permission.getInstance().RESPAWN)) {
			if (Countdown.getCountDown().getTime() > 300) {

				new BukkitRunnable() {

					@Override
					public void run() {
						if (!p.hasPermission("slovermc.espectar")) {

							new BukkitRunnable() {
								public void run() {
								}
							}.runTaskLater(BukkitMain.getPlugin(), 4);
						} else {
							if (p.hasPermission("slovermc.admin")) {
								p.performCommand("admin");
								KitAPI.getInstance().removeKit(p);
								KitAPI.getInstance().removeKit2(p);
							} else if (p.hasPermission("slovermc.espectar")) {
								Manager.getInstance().setSpectador(p);
								KitAPI.getInstance().removeKit(p);
								KitAPI.getInstance().removeKit2(p);
							}
						}
					}
				}.runTaskLater(BukkitMain.getPlugin(), 3);
				e.setDeathMessage(mensagem);
			}
			e.setDeathMessage(mensagem);
		} else {
			e.setDeathMessage(mensagem);
		}

		new BukkitRunnable() {

			@Override
			public void run() {
				p.spigot().respawn();
			}

		}.runTaskLater(BukkitMain.getPlugin(), 1);
	}

	@EventHandler
	protected void onPlayerRespawn(PlayerRespawnEvent e) {
		final Player p = e.getPlayer();

		if (BukkitMain.state == StateEnum.GAME) {
			if (p.hasPermission("slovermc.espectar")) {
				if (Countdown.getCountDown().getTime() > 300) {
					PlayerAPI.getInstance().removePlayer(p);
					Manager.getInstance().checkWin();
					if (!p.hasPermission("slovermc.espectar")) {

						new BukkitRunnable() {
							public void run() {
								if (p.isOnline()) {
									cLobbyCMD.sendServer(p, "lobby");
								}
							}
						}.runTaskLater(BukkitMain.getPlugin(), 4);
					}
				} else {
					int x = new Random().nextInt(300), z = new Random().nextInt(300),
							y = Bukkit.getWorld("world").getHighestBlockYAt(x, z);
					e.setRespawnLocation(new Location(Bukkit.getWorlds().get(0), x, y, z));
					String kit1 = KitAPI.getInstance().getKit(p);
					String kit2 = KitAPI.getInstance().getKit2(p);
					KitAPI.getInstance().setKit(p, kit1);
					KitAPI.getInstance().setKit2(p, kit2);
					Manager.getInstance().checkKit(p);
					Manager.getInstance().checkWin();
				}
			} else {
				PlayerAPI.getInstance().removePlayer(p);
				Manager.getInstance().checkWin();
				if (!p.hasPermission("slovermc.espectar")) {

					new BukkitRunnable() {
						public void run() {
							cLobbyCMD.sendServer(p, "lobby");
						}
					}.runTaskLater(BukkitMain.getPlugin(), 4);
				} else {
					if (p.hasPermission("slovermc.admin")) {
						p.performCommand("admin");
					} else if (p.hasPermission("slovermc.espectar")) {
						Manager.getInstance().setSpectador(p);
					}
				}
			}
		}
	}

	@EventHandler
	protected void PlayerInteractMushroomSoup(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		ItemStack item = player.getItemInHand();
		if (item.getType() == Material.MUSHROOM_SOUP) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);
				Damageable health = player;
				int food = player.getFoodLevel();
				if (health.getHealth() < 20) {
					if (health.getHealth() + 7.0 <= 20)
						health.setHealth(health.getHealth() + 7.0);
					else
						health.setHealth(health.getMaxHealth());
					player.setSaturation(800.0F);
					player.setExhaustion(0.0F);
					player.setItemInHand(new ItemStack(Material.BOWL));
				} else if (food < 20) {
					if (food + 7 <= 20)
						player.setFoodLevel(food + 7);
					else
						player.setFoodLevel(20);
					player.setSaturation(1500.0F);
					player.setExhaustion(0.0F);
					player.setItemInHand(new ItemStack(Material.BOWL));
				}
				player.updateInventory();
			}
		}
	}

	@EventHandler
	protected void onEntityDamageByEntityNerf(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player player = (Player) e.getDamager();
			if (e.getDamage() > 1.0D) {
				e.setDamage(e.getDamage() - 1.0D);
			}
			if ((e.getDamager() instanceof Player)) {
				if ((player.getFallDistance() > 0.0F) && (!player.isOnGround())
						&& (!player.hasPotionEffect(PotionEffectType.BLINDNESS))) {
					double NewDamage = (e.getDamage() * 1.5D) - e.getDamage();
					if (e.getDamage() > 1.0D) {
						e.setDamage(e.getDamage() - NewDamage);
					}
				}
				if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
					for (PotionEffect Effect : player.getActivePotionEffects()) {
						if (Effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
							double division = (Effect.getAmplifier() + 2) * 1.3D + 1.0D;
							double damage;
							if (e.getDamage() / division <= 1.0D)
								damage = (Effect.getAmplifier() + 2) * 3 + 3;
							else {
								damage = e.getDamage() / division;
							}
							e.setDamage(Double.valueOf(damage));
							break;
						}
					}
				}
				if (player.getItemInHand().getType() == Material.AIR) {
					e.setDamage(0.5D);
				} else if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
					e.setDamage(2.0D);
				} else if (player.getItemInHand().getType() == Material.STONE_SWORD) {
					e.setDamage(3.0D);
				} else if (player.getItemInHand().getType() == Material.GOLD_SWORD) {
					e.setDamage(4.0D);
				} else if (player.getItemInHand().getType() == Material.IRON_SWORD) {
					e.setDamage(4.0D);
				} else if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					e.setDamage(5.0D);
				}
				for (PotionEffect Effect : player.getActivePotionEffects()) {
					if ((Effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) && (player.getItemInHand() != null)
							&& (player.getItemInHand().getType().name().contains("SWORD"))) {
						Random r = new Random();
						if (r.nextInt(3) == 0) {
							e.setDamage(e.getDamage() + 2.0D);
							break;
						}
						e.setDamage(e.getDamage() + 1.5D);
					}
				}
				if (player.getFallDistance() > 0.0F && !player.isOnGround()
						&& !player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
					if (player.getItemInHand().getType() == Material.AIR) {
						e.setDamage(0.5D);
					}
					if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
						e.setDamage(e.getDamage() + 1.0D);
					}
					if (player.getItemInHand().getType() == Material.STONE_SWORD) {
						e.setDamage(e.getDamage() + 1.0D);
					}
					if (player.getItemInHand().getType() == Material.GOLD_SWORD) {
						e.setDamage(e.getDamage() + 1.5D);
					}
					if (player.getItemInHand().getType() == Material.IRON_SWORD) {
						e.setDamage(e.getDamage() + 1.0D);
					}
					if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
						e.setDamage(e.getDamage() + 1.0D);
					}
				}
			}
		}
	}

	@EventHandler
	protected void onInteractCompass(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (BukkitMain.state == StateEnum.STARTING)
			return;
		Player c = null;
		if (p.getItemInHand().getType().equals(Material.COMPASS)) {
			for (Entity et : p.getNearbyEntities(500, 256, 500)) {
				if (et instanceof Player) {
					if (et.getLocation().distance(p.getLocation()) > 30
							&& !PlayerAPI.getInstance().hasAdmin((Player) et)
							&& !PlayerAPI.getInstance().hasEspectator((Player) et)) {
						c = (Player) et;
						break;
					}
				}
			}
			if (c == null) {
				p.sendMessage("§3§lBUSSOLA §fNenhum jogador foi encontrado, bússola apontando para o spawn.");
				p.setCompassTarget(new Location(p.getWorld(), 0, 256, 0));
			} else {
				p.sendMessage("§3§lBUSSOLA §fBússola apontando para §3" + c.getName() + "§f.");
				p.setCompassTarget(c.getLocation());
			}
		}
	}

	private String name(ItemStack i) {
		String nome = null;
		if (i.getType() == Material.AIR) {
			nome = "o soco";
		} else if (i.getType() == Material.WOOD_SWORD) {
			nome = "uma espada de madeira";
		} else if (i.getType() == Material.STONE_SWORD) {
			nome = "uma espada de pedra";
		} else if (i.getType() == Material.IRON_SWORD) {
			nome = "uma espada de ferro";
		} else if (i.getType() == Material.DIAMOND_SWORD) {
			nome = "uma espada de diamante";
		} else if (i.getType() == Material.GOLD_SWORD) {
			nome = "uma espada de ouro";
		} else if (i.getType() == Material.WOOD_AXE) {
			nome = "um machado de madeira";
		} else if (i.getType() == Material.STONE_AXE) {
			nome = "um machado de pedra";
		} else if (i.getType() == Material.IRON_AXE) {
			nome = "um machado de ferro";
		} else if (i.getType() == Material.DIAMOND_AXE) {
			nome = "um machado de diamante";
		} else if (i.getType() == Material.GOLD_AXE) {
			nome = "um machado de ouro";
		} else if (i.getType() == Material.WOOD_SPADE) {
			nome = "uma pá de madeira";
		} else if (i.getType() == Material.STONE_SPADE) {
			nome = "uma pá de pedra";
		} else if (i.getType() == Material.IRON_SPADE) {
			nome = "uma pá de ferro";
		} else if (i.getType() == Material.DIAMOND_SPADE) {
			nome = "uma pá de diamante";
		} else if (i.getType() == Material.GOLD_SPADE) {
			nome = "uma pá de ouro";
		} else if (i.getType() == Material.WOOD_PICKAXE) {
			nome = "uma picareta de madeira";
		} else if (i.getType() == Material.STONE_PICKAXE) {
			nome = "uma picareta de pedra";
		} else if (i.getType() == Material.IRON_PICKAXE) {
			nome = "uma picareta de ferro";
		} else if (i.getType() == Material.DIAMOND_PICKAXE) {
			nome = "uma picareta de diamante";
		} else if (i.getType() == Material.GOLD_PICKAXE) {
			nome = "uma picareta de ouro";
		} else if (i.getType() == Material.STICK) {
			nome = "um graveto";
		} else if (i.getType() == Material.MAP) {
			nome = "um mapa";
		} else if (i.getType() == Material.MUSHROOM_SOUP) {
			nome = "uma sopa";
		} else if (i.getType() == Material.RED_MUSHROOM) {
			nome = "um cogumelo";
		} else if (i.getType() == Material.BROWN_MUSHROOM) {
			nome = "um cogumelo";
		} else if (i.getType() == Material.BOWL) {
			nome = "uma tigela";
		} else if (i.getType() == Material.COMPASS) {
			nome = "uma bússola";
		} else {
			nome = i.getType().toString();
		}
		return nome;
	}

	@EventHandler
	protected void onBlockBreakFeast(BlockBreakEvent e) {
		Block block = e.getBlock();
		if (Manager.getInstance().feast.contains(block)) {
			if (FeastManager.getInstance().feastpronto == false) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	protected void onInteractBlockFeast(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null && Manager.getInstance().feast.contains(e.getClickedBlock())) {
			if (FeastManager.getInstance().feastpronto == false) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	protected void onBlockIgniteFeast(BlockIgniteEvent e) {
		if (Manager.getInstance().feast.contains(e.getBlock())) {
			if (FeastManager.getInstance().feastpronto == false) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	protected void onBlockBurnFeast(BlockBurnEvent e) {
		if (Manager.getInstance().feast.contains(e.getBlock())) {
			if (FeastManager.getInstance().feastpronto == false) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	protected void onLeavesDecayFeast(LeavesDecayEvent e) {
		if (Manager.getInstance().feast.contains(e.getBlock())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onBlockExplodeFeast(EntityExplodeEvent e) {
		for (Block block : e.blockList()) {
			if (Manager.getInstance().feast.contains(block)) {
				if (FeastManager.getInstance().feastpronto == false) {
					e.setCancelled(true);
					break;
				}
			}
		}
	}
}
