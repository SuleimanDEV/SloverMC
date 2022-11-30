package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.utils.BattleStrings;
import br.com.slovermc.kitpvp.utils.TimeConverter;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Evento.EventoAPI;

public final class EventoCommand extends Command {

	public EventoCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (!bp.hasPermission("slovermc.evento")) {
			bp.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length == 0) {
			bp.sendMessage("             §3§lEVENTO");
			bp.sendMessage("");
			bp.sendMessage("§f* /evento (iniciar/fechar/bloquear/desbloquear/send).");
			bp.sendMessage("§f* /evento (setslot) (slots).");
			bp.sendMessage("§f* /evento (setbuild) (on/off).");
			bp.sendMessage("§f* /evento (settime) (tempo).");
			bp.sendMessage("");
			bp.sendMessage(
					"§c§lINFO:§f Se você não quiser participar do evento, apenas fazer/espectar, utilize o /event send para ser enviado ao local, pois com /warp evento você será adicionado a lista de players.");
			bp.sendMessage(" ");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("iniciar") || args[0].equalsIgnoreCase("start")) {
				if (EventoAPI.onEvent) {
					bp.sendMessage("§3§lEVENTO§f Já esta iniciado!");
					return true;
				}
				bp.sendMessage("§3§lEVENTO§f Você iniciou um evento!");
				EventoAPI.onEvent = true;
				EventoAPI.startEvent();
				return true;
			}
			if (args[0].equalsIgnoreCase("fechar") || args[0].equalsIgnoreCase("close")) {
				if (!EventoAPI.onEvent) {
					bp.sendMessage("§3§lEVENTO§f O evento não foi iniciado!");
					return true;
				}
				if (EventoAPI.Players.size() > 0) {
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (EventoAPI.Players.contains(all.getName())) {
							if (BuildCommand.hasBuild.contains(all)) {
								BuildCommand.hasBuild.remove(all);
							}
							new WarpsAPI(all, "Spawn");
						}
					}
				}
				EventoAPI.mode = EventoAPI.EventMode.FECHADO;
				EventoAPI.onEvent = false;
				EventoAPI.task.cancel();
				EventoAPI.eventtime = 241;
				EventoAPI.maxEventSlots = 30;
				Bukkit.getServer().broadcastMessage("§3§lEVENTO§f O evento foi fechado!");
				return true;
			}
			if (args[0].equalsIgnoreCase("bloquear")) {
				if (!EventoAPI.onEvent) {
					bp.sendMessage("§3§lEVENTO§f O evento não foi iniciado!");
					return true;
				}
				if (EventoAPI.blocked) {
					bp.sendMessage("§3§lEVENTO§f O evento já está bloqueado.");
					return true;
				}
				EventoAPI.blocked = true;
				bp.sendMessage("§3§lEVENTO§f Você bloqueou a entrada para o evento!");
				return true;
			}
			if (args[0].equalsIgnoreCase("desbloquear")) {
				if (!EventoAPI.onEvent) {
					bp.sendMessage("§3§lEVENTO§f O evento não foi iniciando.");
					return true;
				}
				if (!EventoAPI.blocked) {
					bp.sendMessage("§3§lEVENTO§f O evento já está desbloqueado.");
					return true;
				}
				EventoAPI.blocked = false;
				bp.sendMessage("§3§lEVENTO§f Você desbloqueou a entrada para o evento!");
				return true;
			}
			if (args[0].equalsIgnoreCase("send")) {
				if (!LocationsConstructor.checkBattleWarpNotNull("Evento")) {
					bp.sendMessage("§3§lEVENTO§f A Warp evento não foi setada.");
					return true;
				}
				LocationsConstructor.teleportToBattleWarpLocation(bp, "Evento");
				WarpsAPI.battlePlayerWarp.put(bp, WarpsAPI.Warps.EVENTO);
				Score.createScoreEvento(bp);
				bp.sendMessage("§3§lEVENTO§f Você foi teleportado para o local evento.");
				return true;
			} else {
				bp.sendMessage("             §3§lEVENTO");
				bp.sendMessage("");
				bp.sendMessage("§f* /evento (iniciar/fechar/bloquear/desbloquear/send).");
				bp.sendMessage("§f* /evento (setslot) (slots).");
				bp.sendMessage("§f* /evento (setbuild) (on/off).");
				bp.sendMessage("§f* /evento (settime) (tempo).");
				bp.sendMessage("");
				bp.sendMessage(
						"§c§lINFO:§f Se você não quiser participar do evento, apenas fazer/espectar, utilize o /event send para ser enviado ao local, pois com /warp evento você será adicionado a lista de players.");
				bp.sendMessage(" ");
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("setslot") || args[0].equalsIgnoreCase("setslots")) {
				try {
					if (Integer.valueOf(args[1]) <= 0) {
						bp.sendMessage("§3§lEVENTO§f Você não pode setar slots negativos.");
						return true;
					}
					EventoAPI.maxEventSlots = Integer.valueOf(args[1]);
					bp.sendMessage("§3§lEVENTO§f Slots alterado para §e" + args[1] + "§f.");
				} catch (Exception e) {
					bp.sendMessage("§3§lEVENTO§f Utilize apenas números para indicar os slots!");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("setbuild")) {
				if (args[1].equalsIgnoreCase("on")) {
					if (EventoAPI.onBuild) {
						bp.sendMessage("§3§lEVENTO§f Build para os players já ativado!");
						return true;
					}
					EventoAPI.onBuild = true;
					if (EventoAPI.mode == EventoAPI.EventMode.ANDAMENTO) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (EventoAPI.Players.contains(all.getName())) {
								if (!BuildCommand.hasBuild.contains(all)) {
									BuildCommand.hasBuild.add(all);
								}
							}
						}
					}
					bp.sendMessage("§3§lEVENTO§f Build para os players agora ativado!");
					return true;
				}
				if (args[1].equalsIgnoreCase("off")) {
					if (!EventoAPI.onBuild) {
						bp.sendMessage("§3§lEVENTO§f Build para os players já desativado!");
						return true;
					}
					EventoAPI.onBuild = false;
					if (EventoAPI.mode == EventoAPI.EventMode.ANDAMENTO) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (EventoAPI.Players.contains(all.getName())) {
								if (BuildCommand.hasBuild.contains(all)) {
									BuildCommand.hasBuild.remove(all);
								}
							}
						}
					}
					bp.sendMessage("§3§lEVENTO§f Build para os players agora desativado!");
					return true;
				} else {
					bp.sendMessage("§3§lEVENTO§f Utilize /evento §e" + args[0] + "§f (on/off).");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("settime")) {
				try {
					if (Integer.valueOf(args[1]) <= 0) {
						bp.sendMessage("§3§lEVENTO§f Você não pode utilizar valores negativos!");
						return true;
					}
					EventoAPI.eventtime = Integer.valueOf(args[1]);
					bp.sendMessage("§3§lEVENTO§f Tempo alterado para "
							+ TimeConverter.ConvertTime(Integer.valueOf(args[1])));
				} catch (Exception e) {
					bp.sendMessage("§3§lEVENTO§f Utilize apenas números para indicar o tempo!");
					return true;
				}
			} else {
				bp.sendMessage("             §3§lEVENTO");
				bp.sendMessage("");
				bp.sendMessage("§f* /evento (iniciar/fechar/bloquear/desbloquear/send).");
				bp.sendMessage("§f* /evento (setslot) (slots).");
				bp.sendMessage("§f* /evento (setbuild) (on/off).");
				bp.sendMessage("§f* /evento (settime) (tempo).");
				bp.sendMessage("");
				bp.sendMessage(
						"§c§lINFO:§f Se você não quiser participar do evento, apenas fazer/espectar, utilize o /event send para ser enviado ao local, pois com /warp evento você será adicionado a lista de players.");
				bp.sendMessage(" ");

				return true;
			}
		}
		if (args.length > 2) {
			bp.sendMessage("             §3§lEVENTO");
			bp.sendMessage("");
			bp.sendMessage("§f* /evento (iniciar/fechar/bloquear/desbloquear/send).");
			bp.sendMessage("§f* /evento (setslot) (slots).");
			bp.sendMessage("§f* /evento (setbuild) (on/off).");
			bp.sendMessage("§f* /evento (settime) (tempo).");
			bp.sendMessage("");
			bp.sendMessage(
					"§c§lINFO:§f Se você não quiser participar do evento, apenas fazer/espectar, utilize o /event send para ser enviado ao local, pois com /warp evento você será adicionado a lista de players.");
			bp.sendMessage(" ");
			return true;
		}
		return false;
	}
}
