package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.AccountAPI;
import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;
import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class cDoubleXpCommand extends Command {

	public cDoubleXpCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String arg1, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (!bp.hasPermission("slovermc.doublexp")) {
			bp.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length >= 0 && args.length < 3) {
			bp.sendMessage("§3§lDOUBLEXP§f Utilize /cdoublexp (add/remove) (player) [quantidade].");
			return true;
		}
		if (args.length == 3) {
			final Player m = BattlePlayerAPI.BattlePlayer(args[1]);
			if (m == null) {
				final OfflinePlayer moff = BattlePlayerAPI.BattlePlayerOffline(args[1]);
				if (!args[0].equalsIgnoreCase("add") && !args[0].equalsIgnoreCase("remove")) {
					bp.sendMessage("§3§lDOUBLEXP§f Utilize /cdoublexp (add/remove) (player) [quantidade].");
					return true;
				} else if (args[0].equalsIgnoreCase("add")) {
					try {
						AccountAPI.addBattlePlayerDoubleXp(moff, Integer.valueOf(args[2]));
						bp.sendMessage(
								"§3§lDOUBLEXP§f Você adicionou §b" + args[2] + " §fdoublexp's§f para a conta do player §e" + args[1] + "§f.");
						return true;
					} catch (Exception e) {
						bp.sendMessage(
								"§3§lDOUBLEXP§f Utilize apenas números para indicar a quantidade de doublexp's!");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					try {
						if (Integer.valueOf(args[2]) > AccountAPI.getBattlePlayerMoney(moff)) {
							bp.sendMessage(
									"§3§lDOUBLEXP§f Você não pode remover uma quantidade de doublexp's maior que a do player!");
							return true;
						}
						AccountAPI.removeBattlePlayerDoubleXp(moff, Integer.valueOf(args[2]));
						bp.sendMessage("§3§lDOUBLEXP§f Você removeu §b" + args[2] + " doublexp's da conta do player §e" + args[1] + "§f.");
						return true;
					} catch (Exception e) {
						bp.sendMessage(
								"§3§lDOUBLEXP§f Utilize apenas números para indicar a quantidade de doublexp's.");
						return true;
					}
				}
			}
			if (!args[0].equalsIgnoreCase("add") && !args[0].equalsIgnoreCase("remove")) {
				bp.sendMessage("§3§lDOUBLEXP§f Utilize /cdoublexp (add/remove) (player) [quantidade].");
				return true;
			} else if (args[0].equalsIgnoreCase("add")) {
				try {
					AccountAPI.addBattlePlayerDoubleXp(m, Integer.valueOf(args[2]));
					m.sendMessage("§3§lDOUBLEXP§f Foram adicionadas na sua conta §b" + args[2] + " §fdoublexp's pelo §e" + bp.getName() + "§f.");
					bp.sendMessage("§3§lDOUBLEXP§f Você adicionou §b" + args[2] + " §fdoublexp's para a conta do player §e" + args[1] + "§f.");
					return true;
				} catch (Exception e) {
					bp.sendMessage(
							"§3§lDOUBLEXP§f Utilize apenas números para indicar a quantidade de doublexp's.");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				try {
					if (Integer.valueOf(args[2]) > AccountAPI.getBattlePlayerMoney(m)) {
						bp.sendMessage(
								"§3§lDOUBLEXP§f Você não pode remover uma quantidade de doublexp maior que a do player!");
						return true;
					}
					AccountAPI.removeBattlePlayerDoubleXp(m, Integer.valueOf(args[2]));
					m.sendMessage("§3§lDOUBLEXP§f Foram removidas da sua conta §b" + args[2] + "§f doublexp's pelo §e" + bp.getName() + "§f.");
					bp.sendMessage("§3§lDOUBLEXP§f Você removeu §b" + args[2] + " §fdoublexp's da conta do player §e" + args[1] + "§f.");
					return true;
				} catch (Exception e) {
					bp.sendMessage(
							"§3§lDOUBLEXP§f Utilize apenas números para indicar a quantidade de doublexp!");
					return true;
				}
			}
		}
		if (args.length > 3) {
			bp.sendMessage("§3§lDOUBLEXP§f Utilize /cdoublexp (add/remove) (player) [quantidade].");
			return true;
		}
		return false;
	}
}
