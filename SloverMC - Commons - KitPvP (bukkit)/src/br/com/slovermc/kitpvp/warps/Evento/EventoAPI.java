package br.com.slovermc.kitpvp.warps.Evento;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.command.essentials.BuildCommand;

public final class EventoAPI {

	public static boolean onEvent = false;
	public static int maxEventSlots = 30;
	
	public static final ArrayList<String> Players = new ArrayList<>();
	
	public static boolean onBuild = false;
	
	public static int eventtime = 241;
	
	public static BukkitTask task;
	
	public static boolean blocked = false;
	
	public static enum EventMode {
		INICIANDO, ANDAMENTO, FECHADO
	}
	
	public static EventMode mode = EventMode.FECHADO;
	
	public static final void startEvent() {
		if (!onEvent) {
			return;
		}
		Players.clear();
		mode = EventMode.INICIANDO;
		blocked = false;
		maxEventSlots = 30;
		onBuild = false;
		task = new BukkitRunnable() {
			public void run() {
				eventtime--;
				switch (eventtime) {
				case 240:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 4 minutos! /warp evento");
					break;
				case 180:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 3 minutos! /warp evento");
					break;
				case 120:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 2 minutos! /warp evento");
					break;
				case 90:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 1 minuto e 30 segundos! /warp evento");
					break;
				case 60:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 1 minuto! /warp evento");
					break;
				case 30:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 30 segundos§f! /warp evento");
					break;
				case 10:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 10 segundos§f! /warp evento");
					break;
				case 5:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 5 segundos§f! /warp evento");
					break;
				case 4:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 4 segundos§f! /warp evento");
					break;
				case 3:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 3 segundos§f! /warp evento");
					break;
				case 2:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 2 segundos§f! /warp evento");
					break;
				case 1:
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciará em 1 segundo§f! /warp evento");
					break;
				case 0:
					task.cancel();
					Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento iniciou!");
					runEvent();
					break;
				}
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20L);
	}
	
	public static final void runEvent() {
		for (String all : Players) {
			if (Bukkit.getPlayer(all) != null) {
				if (onBuild) {
					if (!BuildCommand.hasBuild.contains(Bukkit.getPlayer(all))) {
						BuildCommand.hasBuild.add(Bukkit.getPlayer(all));
					}
				}
			}
		}
		mode = EventMode.ANDAMENTO;
		eventtime = 0;
		task = new BukkitRunnable() {
			public void run() {
				eventtime++;
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20L);
	}
}
