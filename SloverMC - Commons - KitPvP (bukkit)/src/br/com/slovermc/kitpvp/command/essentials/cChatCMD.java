package br.com.slovermc.kitpvp.command.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class cChatCMD implements CommandExecutor, Listener {

	public static boolean chat = true;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fEste comando pode ser executado apenas in-game.");
			return true;
		}	
		Player p = (Player)sender;
		if (!p.hasPermission("slovermc.chat")) {
			p.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§4§lCHAT§f Utilize /chat <on/off>.");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on")) {
				if (chat) {
					sender.sendMessage("§4§lCHAT§f O chat ja está habilitado.");
					return true;
				}
				chat = true;
				Bukkit.getServer().broadcastMessage("§4§lCHAT§f O chat foi habilitado!");
				return true;
			} else if (args[0].equalsIgnoreCase("off")) {
				if (!chat) {
					sender.sendMessage("§4§lCHAT§f O chat ja está desabilidado!");
					return true;
				}
				chat = false;
				Bukkit.getServer().broadcastMessage("§4§lCHAT§f O chat foi desabilitado!");
				return true;
			} else {
				sender.sendMessage("§4§lCHAT§f Utilize /chat <on/off>.");
				return true;
			}
		} 
		if (args.length > 1) {
			sender.sendMessage("§6§lCHAT§f Utilize /chat <on/off>.");
			return true;
		}
		return false;
	}

	@EventHandler
	public void Chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (!chat && !p.hasPermission("slovermc.vipchat")) {
			e.setCancelled(true);
			p.sendMessage("§4§lCHAT §fO chat do servidor está desativado!");
		}
	}
}