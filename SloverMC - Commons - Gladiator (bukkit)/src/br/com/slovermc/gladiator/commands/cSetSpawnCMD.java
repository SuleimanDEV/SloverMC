package br.com.slovermc.gladiator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.utils.Strings;

public class cSetSpawnCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Strings.notplayer);
			return true;
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
				if(!p.hasPermission("slovermc.setspawn")) {
					p.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
					return true;
				}
				p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
				p.sendMessage("§6§lGLADIATOR §fSpawn setado com sucesso.");
			}
		}
		return false;
	}

}
