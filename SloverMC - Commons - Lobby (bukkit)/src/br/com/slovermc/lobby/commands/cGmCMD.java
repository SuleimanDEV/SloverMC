package br.com.slovermc.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.lobby.BukkitMain;

public class cGmCMD implements CommandExecutor {
	
	public static final String survival = "0";
	public static final String criative = "1";
	public static final String adventure = "2";

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(BukkitMain.notplayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")) {
			if (!p.hasPermission("slovermc.gm")) {
				p.sendMessage(BukkitMain.noperm);
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§3§lGAMEMODE§f Utilize /gamemode (1/2/3) (player).");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase(survival) && args[0].equalsIgnoreCase(criative) && args[0].equalsIgnoreCase(adventure)) {
					p.sendMessage("§3§lGAMEMODE§f Utilize /gamemode (1/2/3).");
					return true;
				}
				if (args[0].equalsIgnoreCase(survival)) {
					if (p.getGameMode() == GameMode.SURVIVAL) {
						p.sendMessage("§3§lGAMEMODE§f Modo de jogo alterado para §b§lSURVIVAL§f.");
						return true;
					}
					p.setGameMode(GameMode.SURVIVAL);
					p.updateInventory();
					p.sendMessage("§3§lGAMEMODE§f Modo de jogo alterado para §b§lSURVIVAL§f.");
					return true;
				}
				if (args[0].equalsIgnoreCase(criative)) {
					if (p.getGameMode() == GameMode.CREATIVE) {
						p.sendMessage("§3§lGAMEMODE§f Modo de jogo alterado para §b§lCRIATIVO§f.");
						return true;
					}
					p.setGameMode(GameMode.CREATIVE);
					p.updateInventory();
					p.sendMessage("§3§lGAMEMODE§f Modo de jogo alterado para §b§lCRIATIVO§f.");
					return true;
				}
				if (args[0].equalsIgnoreCase(adventure)) {
					if (p.getGameMode() == GameMode.ADVENTURE) {
						p.sendMessage("§3§lGAMEMODE§f Modo de jogo alterado para §b§lADVENTURE§f.");
						return true;
					}
					p.setGameMode(GameMode.ADVENTURE);
					p.updateInventory();
					p.sendMessage("§3§lGAMEMODE§f Modo de jogo alterado para §b§lADVENTURE§f.");
					return true;
				}
			}
			if (args.length == 2) {
				Player player = Bukkit.getPlayer(args[1]);
				if (args[0].equalsIgnoreCase(survival) && args[0].equalsIgnoreCase(criative) && args[0].equalsIgnoreCase(adventure)) {
					p.sendMessage("§3§lGAMEMODE§f Utilize §3§l/gamemode (1/2/3).");
					return true;
				}
				if (player == null) {
					p.sendMessage(BukkitMain.notonline);
					return true;
				}
				if (player == p) {
					p.sendMessage("§3§lGAMEMODE§f Utilize /gamemode (1/2/3).");
					return true;
				}
				if (args[0].equalsIgnoreCase(survival) && player != null && player != p) {
					if (player.getGameMode() == GameMode.SURVIVAL) {
						p.sendMessage("§3§lGAMEMODE§f O modo de jogo do player §e" + player.getName() + "§f já está no §b§lSURVIVAL§f.");
						return true;
					}
					player.setGameMode(GameMode.SURVIVAL);
					player.updateInventory();
					player.sendMessage("§3§lGAMEMODE§f O player §e" + p.getName() + "§f alterou seu modo de jogo para §b§lSURVIVAL§f.");
					p.sendMessage("§3§lGAMEMODE§f O modo de jogo do player §e" + player.getName() + "§f foi alterado para §b§lSURVIVAL§f.");
					return true;
				}
				if (args[0].equalsIgnoreCase(criative) && player != null && player != p) {
					if (player.getGameMode() == GameMode.CREATIVE) {
						p.sendMessage("§3§lGAMEMODE§f O modo de jogo do player §e" + player.getName() + "§f já está no §b§lCRIATIVO§f.");
						return true;
					}
					player.setGameMode(GameMode.CREATIVE);
					player.updateInventory();
					player.sendMessage("§3§lGAMEMODE§f O player §e" + p.getName() + "§f alterou seu modo de jogo para §b§lCRIATIVO§f.");
					p.sendMessage("§3§lGAMEMODE§f O modo de jogo do player §e" + player.getName() + "§f foi alterado para §b§lCRIATIVO§f.");
					return true;
				}
				if (args[0].equalsIgnoreCase(adventure) && player != null && player != p) {
					if (player.getGameMode() == GameMode.ADVENTURE) {
						p.sendMessage("§3§lGAMEMODE§f O modo de jogo do player §e" + player.getName() + "§f já está no §b§lADVENTURE§f.");
						return true;
					}
					player.setGameMode(GameMode.ADVENTURE);
					player.updateInventory();
					player.sendMessage("§3§lGAMEMODE§f O player §e" + p.getName() + "§f alterou seu modo de jogo para §b§lADVENTURE§f.");
					p.sendMessage("§3§lGAMEMODE§f O modo de jogo do player §e" + player.getName() + "§f foi alterado para §b§lADVENTURE§f.");
					return true;
				}
			}
		}
		return false;
	}
}