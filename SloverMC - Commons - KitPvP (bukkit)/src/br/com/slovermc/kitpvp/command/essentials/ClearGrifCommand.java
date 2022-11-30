package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class ClearGrifCommand extends Command {

	public ClearGrifCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	public static final void clearLava(final Location location, final int radius) {
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					if (location.getWorld().getBlockAt(x, y, z).getType() == Material.STATIONARY_LAVA
							|| location.getWorld().getBlockAt(x, y, z).getType() == Material.LAVA) {
						location.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
					}
				}
			}
		}
	}

	public static final void clearWater(final Location location, final int radius) {
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					if (location.getWorld().getBlockAt(x, y, z).getType() == Material.STATIONARY_WATER
							|| location.getWorld().getBlockAt(x, y, z).getType() == Material.WATER) {
						location.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
					}
				}
			}
		}
	}
	
	public static final int MAX_DISTANCE = 200;

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (!bp.hasPermission("slovermc.clearlava")) {
			bp.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length <= 1) {
			bp.sendMessage("§9§lCLEAR GRIF§f Utilize /cleargrif (água/lava) (distância).");
			return true;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("water") || args[0].equalsIgnoreCase("agua")) {
				try {
					if (Integer.valueOf(args[1]) > MAX_DISTANCE) {
						bp.sendMessage("§c§lERRO§f A distência máxima para raios permitidas é de §c" + MAX_DISTANCE + " §fblocos§f!");
						return true;
					}
					if (Integer.valueOf(args[1]) <= 0) {
						bp.sendMessage("§9§lCLEAR GRIF§f O raio precisa ser maior que 0.");
						return true;
					}
					clearWater(bp.getLocation(), Integer.valueOf(args[1]));
					bp.sendMessage("§9§lCLEAR GRIF§f Você limpou qualquer tipo de água em um raio de §e"
							+ args[1] + "§f blocos§f.");
					return true;
				} catch (Exception e) {
					bp.sendMessage("§c§lERRO§f Utilize apenas números para indicar os raios de distância.");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("lava")) {
				try {
					if (Integer.valueOf(args[1]) > MAX_DISTANCE) {
						bp.sendMessage("§c§lERRO§f A distância maxima para raios permitidas é de §c" + MAX_DISTANCE + " §fblocos§f!");
						return true;
					}
					if (Integer.valueOf(args[1]) <= 0) {
						bp.sendMessage("§9§lCLEAR GRIF§f O raio precisa ser maior que 0.");
						return true;
					}
					clearLava(bp.getLocation(), Integer.valueOf(args[1]));
					bp.sendMessage("§9§lCLEAR GRIF§f Você limpou qualquer tipo de lava em um raio de §e"
							+ args[1] + " §fblocos§f.");
					return true;
				} catch (Exception e) {
					bp.sendMessage("§c§lERRO§f Utilize apenas números para indicar os raios de distância.");
					return true;
				}
			} else {
				bp.sendMessage("§9§lCLEAR GRIF§f Utilize /cleargrif §b(água) §fou §c(lava) §fe §f(distância).");
				return true;
			}
		}
		if (args.length > 1) {
			bp.sendMessage("§9§lCLEAR GRIF§f Utilize /cleargrif (água/lava) (distância).");
			return true;
		}
		return false;
	}
}
