package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.Countdown;

public class cInfoCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("info")) {
			if (BukkitMain.state == StateEnum.STARTING) {
				player.sendMessage("");
				player.sendMessage("        §e§lINFORMAÇÕES");
				player.sendMessage("");
				player.sendMessage("§fEstágio: §cIniciando.");
				player.sendMessage("§fTempo §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()));
				player.sendMessage("§fKi1: §b" + KitAPI.getInstance().getKit(player));
				player.sendMessage("§fKit2: §b" + KitAPI.getInstance().getKit2(player));
				player.sendMessage("");
			}
		} else if (BukkitMain.state == StateEnum.INVINCIBILITY) {
			player.sendMessage("");
			player.sendMessage("        §e§lINFORMAÇÕES");
			player.sendMessage("");
			player.sendMessage("§fEstágio: §cInvencibilidade.");
			player.sendMessage("§fTempo §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()));
			player.sendMessage("§fKi1: §b" + KitAPI.getInstance().getKit(player));
			player.sendMessage("§fKit2: §b" + KitAPI.getInstance().getKit2(player));
			player.sendMessage("§fKills: §c0");
			player.sendMessage("");
		} else if (BukkitMain.state == StateEnum.GAME) {
			player.sendMessage("");
			player.sendMessage("        §e§lINFORMAÇÕES");
			player.sendMessage("");
			player.sendMessage("§fEstágio: §cAndamento.");
			player.sendMessage("§fTempo §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()));
			player.sendMessage("§fKi1: §b" + KitAPI.getInstance().getKit(player));
			player.sendMessage("§fKit2: §b" + KitAPI.getInstance().getKit2(player));
			player.sendMessage("§fKills: §c" + PlayerAPI.getInstance().getKills(player));
			player.sendMessage("");
		} else if (BukkitMain.state == StateEnum.END) {
			player.sendMessage("");
			player.sendMessage("        §e§lINFORMAÇÕES");
			player.sendMessage("");
			player.sendMessage("§fEstágio: §cAndamento.");
			player.sendMessage("§fTempo §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()));
			player.sendMessage("§fKi1: §b" + KitAPI.getInstance().getKit(player));
			player.sendMessage("§fKit2: §b" + KitAPI.getInstance().getKit2(player));
			player.sendMessage("§fKills: §c" + PlayerAPI.getInstance().getKills(player));
			player.sendMessage("");
		}
		return false;
	}
}
