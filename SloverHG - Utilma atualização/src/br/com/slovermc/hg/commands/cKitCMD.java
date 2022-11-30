package br.com.slovermc.hg.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.TittleAPI;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.inventarios.InventoryKit;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.utils.Permission;

public class cKitCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (BukkitMain.state != StateEnum.STARTING) {
			if (BukkitMain.state != StateEnum.INVINCIBILITY) {
				return true;
			} else {
				if (!KitAPI.getInstance().hasKit(p, "Nenhum")) {
					return true;
				}
			}
		}
		if (args.length == 0) {
			if (BukkitMain.state == StateEnum.STARTING) {
				InventoryKit.getInstance().kitSelector1(p);
			}
			return true;
		} else if (args.length == 1) {
			if (handle(p, args, "Achilles")) {
			} else if (handle(p, args, "Anchor")) {
			} else if (handle(p, args, "Fireman")) {
			} else if (handle(p, args, "Fisherman")) {
			} else if (handle(p, args, "Flash")) {
			} else if (handle(p, args, "Blink")) {
			} else if (handle(p, args, "Launcher")) {
			} else if (handle(p, args, "Grappler")) {
			} else if (handle(p, args, "Lumberjack")) {
			} else if (handle(p, args, "Endermage")) {
			} else if (handle(p, args, "Madman")) {
			} else if (handle(p, args, "Ninja")) {
			} else if (handle(p, args, "Phantom")) {
			} else if (handle(p, args, "Stomper")) {
			} else if (handle(p, args, "Deshfire")) {
			} else if (handle(p, args, "Sonic")) {
			} else if (handle(p, args, "Gladiator")) {
			} else if (handle(p, args, "Specialist")) {
			} else if (handle(p, args, "Snail")) {
			} else if (handle(p, args, "Poseidon")) {
			} else if (handle(p, args, "Viking")) {
			} else if (handle(p, args, "Vacuum")) {
			} else if (handle(p, args, "Tank")) {
			} else if (handle(p, args, "Viper")) {
			} else if (handle(p, args, "Thor")) {
			} else if (handle(p, args, "Hulk")) {
			} else if (handle(p, args, "Monk")) {
			} else if (handle(p, args, "Antitower")) {
			} else if (handle(p, args, "Boxer")) {
			} else if (handle(p, args, "Archer")) {
			} else if (handle(p, args, "Magma")) {
			} else if (handle(p, args, "Miner")) {
			} else if (handle(p, args, "Kangaroo")) {
			//} else if (handle(p, args, "Jackhammer")) {
			} else if (handle(p, args, "Copycat")) {
			} else if (handle(p, args, "Cultivator")) {
			} else if (handle(p, args, "Demoman")) {
			} else if (handle(p, args, "Forger")) {
			} else if (handle(p, args, "Urgal")) {
			} else if (handle(p, args, "Scout")) {
			} else if (handle(p, args, "Grandpa")) {
			} else if (handle(p, args, "Reaper")) {
			} else if (handle(p, args, "Timelord")) {
			} else if (handle(p, args, "Worm")) {
			} else if (handle(p, args, "Weakhand")) {
			} else if (handle(p, args, "Ajnin")) {
			} else {
				p.sendMessage("§3§lKIT §fEste kit não foi encontrado.");
			}
		}
		return false;
	}

	public boolean handle(Player p, String[] args, String kit) {
		if (args[0].equalsIgnoreCase(kit)) {
			if (p.hasPermission(Permission.getInstance().KIT + kit)) {
				if (BukkitMain.getPlugin().kit) { 
					if (KitAPI.getInstance().hasKit2(p, kit)) {
						KitAPI.getInstance().setKit2(p, "Nenhum");
					}
					KitAPI.getInstance().setKit(p, kit);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
					p.sendMessage("§3§lKIT §fVocê pegou o kit§b§l " + KitAPI.getInstance().getKit(p) + "§f.");
					TittleAPI.sendTitle(p, "§b§l" + KitAPI.getInstance().getKit(p));
					TittleAPI.sendSubTitle(p, "§fKit selecionando!");
					
					if (BukkitMain.state == StateEnum.INVINCIBILITY) {
						Manager.getInstance().checkKit(p);
					}
				} else { 
					p.sendMessage("§3§lKITS §fOs kits primários estão §c§lDESABILITADOS§f!");
				}
			} else { 
				p.sendMessage("§3§lKIT §fVocê não possui este kit.");
			}
			return true;
		}
		return false;
	}
}
