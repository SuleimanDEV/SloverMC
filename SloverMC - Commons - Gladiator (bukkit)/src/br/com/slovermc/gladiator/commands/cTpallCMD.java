package br.com.slovermc.gladiator.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.api.GladiatorAPI;
import br.com.slovermc.gladiator.utils.Strings;

public class cTpallCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] Args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Strings.notplayer);
			return true;
		} else {
			Player p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("tpall")) {
				if(!p.hasPermission("slovermc.tpall")) {
					p.sendMessage(BukkitMain.semp);
					return true;
				} else {
					for(Player p2 : Bukkit.getOnlinePlayers()) {
						String k = GladiatorAPI.getPlayerLuta(p2);
						Player p3 = Bukkit.getPlayer(k);
						GladiatorAPI.cancelBatalha(p3, "Sem motivo");
					}
					Bukkit.broadcastMessage("§4§lAVISO §fTodas as §a§lBATALHAS§f forão canceladas por §e" + p.getName() + "§f.");
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("getpvp")) {
				if(Args.length == 0) {
					p.sendMessage("§c§lERRO §fUtilize /getpvp (player).");
					return true;
				} else {
					Player p2 = Bukkit.getPlayer(Args[0]);
					if(p2 == null) {
						p.sendMessage(Strings.notonline);
						return true;
					}
					p.sendMessage(GladiatorAPI.getPlayerLuta(p2));
				}
				
			}
			
		}
		return false;
	}

}
