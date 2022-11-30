package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;
import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class TeleportCommand extends Command {

	public TeleportCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}
	
	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (!bp.hasPermission("slovermc.teleport")) {
			bp.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length == 0) {
			bp.sendMessage("§6§lTELEPORT§f Utilize /tp <player>, /tp <player1> <player2> ou /tp <player> (x) (y) (z).");
			return true;
		} else if (args.length == 1) {
			final Player toTeleport = BattlePlayerAPI.BattlePlayer(args[0]);
			if (toTeleport == null) {
				bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
				return true;
			}
			bp.teleport(toTeleport);
			bp.sendMessage("§6§lTELEPORTE§f Você foi teleportado para §e" + toTeleport.getName() + "§f.");
			return true;
		} else if (args.length == 2) {
			final Player player = BattlePlayerAPI.BattlePlayer(args[0]);
			final Player toTeleport = BattlePlayerAPI.BattlePlayer(args[1]);
			if (player == null) {
				bp.sendMessage("§c§lERRO§f O player §e" + args[0] + "§f não está online§f.");
				return true;
			} else if (toTeleport == null) {
				bp.sendMessage("§c§lERRO§f O player §e" + args[1] + "§f não está online§f.");
				return true;
			}
			player.teleport(toTeleport);
			bp.sendMessage("§6§lTELEPORTE§f Você teleportou o player§e " + args[0] + " §fpara o player §e" + args[1] + "§f.");
			return true;
		} else if (args.length > 2 && args.length < 4) {
			bp.sendMessage("§6§lTELEPORT§f Utilize /tp <player>, /tp <player1> <player2> ou /tp <player> (x) (y) (z).");
			return true;
		} else if (args.length == 4) {
			final Player toTeleport = BattlePlayerAPI.BattlePlayer(args[0]);
			if (toTeleport == null) {
				bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
				return true;
			}
			try {
				toTeleport.teleport(new Location(bp.getWorld(), Integer.valueOf(args[1]), Integer.valueOf(args[2]),
						Integer.valueOf(args[3])));
				bp.sendMessage(
						"§6§lTELEPORTE§f Você teleportou o player §b" + args[0] + " §fpara §e" + args[1] + " §e" + args[2] + " §e" + args[3] + "§f.");
				return true;
			} catch (Exception e) {
				bp.sendMessage("§6§lTELEPORTE§f Utilize apenas números para indicar coordenadas!");
				return true;
			}
		} else if (args.length > 4) {
			bp.sendMessage("§6§lTELEPORT§f Utilize /tp <player>, /tp <player1> <player2> ou /tp <player> (x) (y) (z).");
			return true;
		}
		return false;
	}
}
