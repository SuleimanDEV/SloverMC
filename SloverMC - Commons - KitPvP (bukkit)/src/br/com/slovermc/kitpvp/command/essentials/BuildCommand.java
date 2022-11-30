package br.com.slovermc.kitpvp.command.essentials;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;
import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class BuildCommand extends Command {
	
	public static final ArrayList<Player> hasBuild = new ArrayList<>();
	
	public static boolean hasBuild(final Player bp) {
		return hasBuild.contains(bp);
	}

	public BuildCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (!bp.hasPermission("slovermc.build")) {
			bp.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length == 0) {
			bp.sendMessage("§a§lBUILD§f Utilize /build (on/off) (player).");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on")) {
				if (hasBuild(bp)) {
					bp.sendMessage("§e§lBUILD§f Seu modo builder já está habilitado.");
					return true;
				}
				hasBuild.add(bp);
				bp.sendMessage("§a§lBUILD§f Você habilitou seu modo builder.");
				return true;
			}
			else if (args[0].equalsIgnoreCase("off")) {
				if (!hasBuild(bp)) {
					bp.sendMessage("§a§lBUILD§f Seu modo builder já está desabilitado!");
					return true;
				}
				hasBuild.remove(bp);
				bp.sendMessage("§a§lBUILD§f Você desabilitou seu modo builder!");
				return true;
			} else {
				bp.sendMessage("§a§lBUILD§f Utilize /build (on/off).");
				return true;
			}
		}
		if (args.length == 2) {
			final Player toBuild = BattlePlayerAPI.BattlePlayer(args[1]);
			if (toBuild == null) {
				bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
				return true;
			}
			else if (args[0].equalsIgnoreCase("on")) {
				if (hasBuild(toBuild)) {
					bp.sendMessage("§a§lBUILD§f O modo builder do §e" + args[1] + " §fjá está habilitado!");
					return true;
				}
				hasBuild.add(toBuild);
				toBuild.sendMessage("§a§lBUILD§f Seu modo builder foi habilitado pelo §e" + bp.getName() + "§f.");
				bp.sendMessage("§a§lBUID§f Você habilitou o modo builder do §e" + args[1] + "§f.");
				return true;
			}
			else if (args[0].equalsIgnoreCase("off")) {
				if (!hasBuild(toBuild)) {
					bp.sendMessage("§a§lBUILD§f O modo builder do §e" + args[1] + " §fjá está desabilitado!");
					return true;
				}
				hasBuild.remove(toBuild);
				toBuild.sendMessage("§a§lBUILD§f Seu modo builder foi desabilitado pelo §e" + bp.getName() + "§f.");
				bp.sendMessage("§a§lBUID§f Você desabilitou o modo builder do §e" + args[1] + "§f.");
				return true;
			} else {
				bp.sendMessage("§e§lBUILD§f Utilize /build (on/off) (player).");
				return true;
			}
		}
		if (args.length > 2) {
			bp.sendMessage("§e§lBUILD§f Utilize /build (on/off) (player).");
			return true;
		}
		return false;
	}
}
