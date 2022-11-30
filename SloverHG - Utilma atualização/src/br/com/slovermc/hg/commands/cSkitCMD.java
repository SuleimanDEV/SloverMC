package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.Permission;

public class cSkitCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("skit")) {
			if (player.hasPermission(Permission.getInstance().SKIT)) { 
				if (args.length == 0) {
					player.sendMessage("§9§lSKIT §fUse /skit (all ou jogador)");
					return true;
				}
				if (args[0].equalsIgnoreCase("all")) { 
					if (player.hasPermission(Permission.getInstance().SKIT)) {
						for (Player players : PlayerAPI.getInstance().getPlayers()) {
							if (PlayerAPI.getInstance().hasPlayer(players)) { 
								players.getInventory().setContents(player.getInventory().getContents());
								players.getInventory().setArmorContents(player.getInventory().getArmorContents());
							}
						}
						Bukkit.broadcastMessage("§b§lSKIT §f" + player.getName() + " setou kit para todos!");
					}
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target != null) { 
					if (PlayerAPI.getInstance().hasPlayer(target)) { 
						target.getInventory().setContents(player.getInventory().getContents());
						target.getInventory().setArmorContents(player.getInventory().getArmorContents());
						target.sendMessage("§b§lSKIT §f" + player.getName() + " setou kit para você!");
						player.sendMessage("§cVocê setou seu inventário para: " + target.getName());
					} else { 
						player.sendMessage("§c§lERRO §fJogador offline ou inexistente.");
					}
				} else { 
					player.sendMessage("§c§lERRO §fJogador offline ou inexistente.");
				}
			} else { 
				player.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			}
		}
		return false;
	}
}
