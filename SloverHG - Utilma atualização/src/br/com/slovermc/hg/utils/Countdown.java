package br.com.slovermc.hg.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.TittleAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.manager.FeastManager;
import br.com.slovermc.hg.manager.Manager;

@SuppressWarnings("deprecation")
public class Countdown {

	private static Countdown c = new Countdown();
	private static BukkitTask task = null;
	private static int tempo;
	private static int temporestart;

	public static Countdown getCountDown() {
		return c;
	}

	public void startingTimer() {
		BukkitMain.state = StateEnum.STARTING;
		cancel();
		tempo = 300;
		temporestart = 180;
		task = new BukkitRunnable() {
			public void run() {
				if (tempo <= 300 && tempo > 60 && tempo % 60 == 0) {
					Bukkit.broadcastMessage("§3§lTORNEIO §fTorneio irá iniciar em " + tempo / 60 + " minutos");
				} else if (tempo == 60) {
					Bukkit.broadcastMessage("§3§lTORNEIO §fTorneio irá iniciar em " + tempo / 60 + " minuto.");
				} else if (tempo == 15) {
					Bukkit.broadcastMessage("§3§lTORNEIO §fTorneio irá iniciar em " + tempo + " segundos.");
				} else if (tempo < 6 && tempo > 1) {
					for (Player ps : Bukkit.getOnlinePlayers()) {
						sound(ps, Sound.CLICK);
					}
					Bukkit.broadcastMessage("§3§lTORNEIO §fTorneio irá iniciar em " + tempo + " segundos.");
				} else if (tempo == 1) {
					for (Player ps : Bukkit.getOnlinePlayers()) {
						sound(ps, Sound.CLICK);
					}
					Bukkit.broadcastMessage("§3§lTORNEIO §fTorneio irá iniciar em " + tempo + " segundo.");
				} else if (tempo == 0) {
					if (PlayerAPI.getInstance().getPlayers().length < 3) {
						tempo = temporestart + 1;
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (!players.getInventory().contains(Material.COMPASS) || !PlayerAPI.getInstance().hasPlayer(players)) continue;
							PlayerAPI.getInstance().itemsStarting(players);
							sound(players, Sound.BURP);
						}
						Bukkit.broadcastMessage("§3§lTORNEIO §fTempo reiniciado por falta de jogadores.");
					} else {
						for (Player ps : Bukkit.getOnlinePlayers()) {
							sound(ps, Sound.BURP);
							ps.closeInventory();
							TittleAPI.sendTitle(ps, "§a§lINICIANDO");
							TittleAPI.sendSubTitle(ps, "§fBoa sorte, bom jogo!");
						}
						Bukkit.broadcastMessage("§3§lTORNEIO §fTorneio iniciou.");
						Manager.getInstance().invincibilityTime();
					}
				}
				--tempo;
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
	}

	public void invincibilityTimer() {
		BukkitMain.state = StateEnum.INVINCIBILITY;
		cancel();
		tempo = 120;
		task = new BukkitRunnable() {
			public void run() {
				if (tempo == 120) {
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade irá acabar em " + tempo / 60 + " minutos.");
				} else if (tempo == 60) {
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade irá acabar em " + tempo / 60 + " minuto.");
				} else if (tempo == 30) {
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade irá acabar em " + tempo + " segundos.");
				} else if (tempo == 15) {
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade irá acabar em " + tempo + " segundos.");
				} else if (tempo < 6 && tempo > 1) {
					for (Player ps : Bukkit.getOnlinePlayers()) {
						sound(ps, Sound.CLICK);
					}
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade irá acabar em " + tempo + " segundos.");
				} else if (tempo == 1) {
					for (Player ps : Bukkit.getOnlinePlayers()) {
						sound(ps, Sound.CLICK);
					}
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade irá acabar em " + tempo + " segundo.");
				} else if (tempo == 0) {
					for (Player ps : Bukkit.getOnlinePlayers()) {
						sound(ps, Sound.BURP);
					}
					Bukkit.broadcastMessage("§e§lINVENCIBILIDADE §fA Invencibilidade acabou, o pvp foi ativado, boa sorte!");
					Manager.getInstance().gameTime();
				}
				--tempo;
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
	}

	public void gameTimer() {
		BukkitMain.state = StateEnum.GAME;
		cancel();
		tempo = 0;
		Manager.getInstance().checkWin();
		task = new BukkitRunnable() {
			public void run() {
				if (tempo % 300 == 0 && tempo > 299) {
					FeastManager.getInstance().createMinifeast();
				}
				if (tempo == 900) {
					int x = new Random().nextInt(80), z = new Random().nextInt(80), y = 100;
					boolean stop = false;
					Location location;
					do {
						location = new Location(Bukkit.getWorld("world"), x, y, z);
						if (location.getBlock().getType() == Material.AIR) {
							y--;
						} else {
							location = new Location(Bukkit.getWorld("world"), x, y, z);
							stop = true;
						}
					} while (stop == false);
					FeastManager.getInstance().initialiseFeast(location);
				}
				if (tempo == 2700) { 
					Bukkit.broadcastMessage("§3§lARENA §fArena final será gerada em 5 minutos!");
				}
				if (tempo == 3000) { 
					Bukkit.broadcastMessage("§3§lARENA §fArena final foi gerada!");
					double x = 0.0D;
					double y = 80.0D;
					double z = 0.0D;
					Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
					ArrayList<Block> arenagrande = new ArrayList<>();
					try { 
						Schematic schematic = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "arenagrande.schematic"));
						Schematic.getInstance().generateSchematic(Bukkit.getWorlds().get(0), location, schematic, arenagrande);
						for (Player all : Bukkit.getOnlinePlayers()) { 
							Location location2 = new Location(Bukkit.getWorlds().get(0), x, y + 2.0D, z);
							all.teleport(location2);
						}
					} catch (Exception e) {
					}
				}
				++tempo;
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
	}

	public void endTimer() {
		task = new BukkitRunnable() {
			public void run() {
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
	}

	public void cancel() {
		if (task != null) {
			task.cancel();
		}
	}

	public void changeTime(int time) {
		tempo = time;
	}

	public Integer getTime() {
		return tempo;
	}

	public String Timer(int i) {
		String m = (i / 60 < 10 ? "" : "") + i / 60;
		String s = (i % 60 < 10 ? "0" : "") + i % 60;
		if (i / 60 < 1)
			return s + "s";
		else
			return m + "m " + s + "s";
	}

	public void updateTeam(String teamName, String value, Scoreboard sb) {
		Team team = null;

		if (sb.getTeam(teamName) == null) {
			team = sb.registerNewTeam(teamName);
		} else {
			team = sb.getTeam(teamName);
		}

		team.setSuffix(value);
	}

	protected void sound(Player ps, Sound sound) {
		ps.playSound(ps.getLocation(), sound, 1.0F, 1.0F);
	}
}
