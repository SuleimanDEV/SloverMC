package br.com.slovermc.hg.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.manager.FeastManager;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cForceFeastCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("forcefeast")) {
			if (BukkitMain.state == StateEnum.STARTING) return true;
			if (player.hasPermission(Permission.getInstance().FORCEFEAST)) { 
				if (FeastManager.getInstance().feastspawnado == false) {
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
				} else {
					if (FeastManager.getInstance().feastpronto == true) {
						player.sendMessage("§c§lFEAST §fO feast já está pronto!");
					} else {
						FeastManager.getInstance().feasttime = 6;
						player.sendMessage("§c§lFEAST §fVocê adiantou o tempo do feast para: 6s.");
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
