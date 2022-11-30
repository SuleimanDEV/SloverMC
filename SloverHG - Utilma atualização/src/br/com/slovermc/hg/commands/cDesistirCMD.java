package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.utils.Permission;

public class cDesistirCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("desistir")) {
			if (args.length == 0) { 
				if (BukkitMain.state == StateEnum.STARTING || BukkitMain.state == StateEnum.END) { 
					player.sendMessage("§b§lSPECTADOR §fVocê não pode desistir da partida agora!");
					return true;
				} else { 
					if (!PlayerAPI.getInstance().hasEspectator(player)) { 
						if (player.hasPermission(Permission.getInstance().ADMIN)) { 
							String kit = KitAPI.getInstance().getKit(player) + ", " + KitAPI.getInstance().getKit2(player);
							Bukkit.broadcastMessage("§b" + player.getName() + "(" + kit + ") §bnão aguente a pressão e desistiu!");
							Bukkit.broadcastMessage("§e" + PlayerAPI.getInstance().getPlayers().length + " Jogadores restantes.");
							player.performCommand("admin");
							Manager.getInstance().checkWin();
						}
						else if (player.hasPermission(Permission.getInstance().SPECT)) { 
							String kit = KitAPI.getInstance().getKit(player) + ", " + KitAPI.getInstance().getKit2(player);
							Bukkit.broadcastMessage("§b" + player.getName() + "(" + kit + ") §bnão aguente a pressão e desistiu!");
							Bukkit.broadcastMessage("§e" + PlayerAPI.getInstance().getPlayers().length + " Jogadores restantes.");
							Manager.getInstance().setSpectador(player);
							Manager.getInstance().checkWin();
						}
						else if ((player.hasPermission(Permission.getInstance().SPECT)) || (player.hasPermission(Permission.getInstance().ADMIN))) { 
							String kit = KitAPI.getInstance().getKit(player) + ", " + KitAPI.getInstance().getKit2(player);
							Bukkit.broadcastMessage("§c" + player.getName() + "(" + kit + ") §nnão aguente a pressão e desistiu!");
							Bukkit.broadcastMessage("§e" + PlayerAPI.getInstance().getPlayers().length + " Jogadores restantes.");
							cLobbyCMD.sendServer(player, "lobby");
							Manager.getInstance().checkWin();
						}
					} else { 
						player.sendMessage("§b§lSPECTADOR §fVocê já está espectando!");
					}
				}
			}
		}
		return false;
	}
}
