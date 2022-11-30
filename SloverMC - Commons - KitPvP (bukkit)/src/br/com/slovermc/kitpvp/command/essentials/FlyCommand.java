package br.com.slovermc.kitpvp.command.essentials;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.kits.KitResetor;
import br.com.slovermc.kitpvp.utils.BattleStrings;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class FlyCommand extends Command {

	public FlyCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	public static final ArrayList<Player> hasFly = new ArrayList<>();

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (!bp.hasPermission("slovermc.fly")) {
			bp.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return true;
		}
		if (args.length >= 0) {
			if (hasFly.contains(bp)) {
				hasFly.remove(bp);
				bp.setAllowFlight(false);
				bp.setFlying(false);
				WarpsAPI.addPlayerInWarpByArgs(bp, "Spawn");
				KitResetor.resetPlayerKit(bp);
				KitAPI.setKit(bp, "Nenhum");
				CooldownAPI.removeCooldown(bp);
				LocationsConstructor.teleportToBattleWarpLocation(bp, "Spawn");
				WarpsAPI.loadWarpItensFromArgs(bp, "Spawn");
				return true;
			} else if (!SpawnWarpListener.onWarpSpawnProtection.contains(bp)) {
				bp.sendMessage("§a§lFLY §fVocê só pode voar com proteção de spawn.");
				return true;
			} else if (KitAPI.getKit(bp) != "Nenhum") {
				bp.sendMessage("§a§lFLY §fVocê só pode voar sem nenhum kit.");
				return true;
			} else {
				hasFly.add(bp);
				bp.getInventory().clear();
				bp.getInventory().setArmorContents(null);
				bp.setAllowFlight(true);
				bp.setFlying(true);
				bp.sendMessage("§a§lFLY §fVocê agora está voando!");
				return true;
			}
		}
		return false;
	}
}
