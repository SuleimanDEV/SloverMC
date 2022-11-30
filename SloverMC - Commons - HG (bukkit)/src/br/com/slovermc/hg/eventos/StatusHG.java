package br.com.slovermc.hg.eventos;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.Countdown;

public class StatusHG implements Listener {

	// IPS //
	public static String getIP() {
		String port = Bukkit.getServer().getPort() + "";
		if (port.equalsIgnoreCase("25523")) {
			return "1";
		}
		else if (port.equalsIgnoreCase("25524")) {
			return "2";

		}
		else if (port.equalsIgnoreCase("25525")) {
			return "3";

		}
		else if (port.equalsIgnoreCase("25526")) {
			return "4";
		}
		else if (port.equalsIgnoreCase("25527")) {
			return "5";
		}
		else if (port.equalsIgnoreCase("25528")) {
			return "6";
		}
		return "?";
	}

	// TEMPO //
	public static String ConvertTime(int i) {
		int minutos = (int) i / 60;
		int segundos = i - minutos * 60;
		if (i >= 60 && segundos >= 10) {
			return minutos + ":" + segundos;
		} else if (i >= 60 && segundos < 10) {
			return minutos + ":0" + segundos;
		} else if (i < 60 && i >= 10) {
			return "0:" + i;
		} else {
			return "0:0" + i;
		}
	}

	// MOTDS //
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPing(ServerListPingEvent e) {
		if (BukkitMain.state == StateEnum.STARTING) {
			
			e.setMotd("§fIniciando em: §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()) + "§f.");
		}
		
		if (BukkitMain.state == StateEnum.INVINCIBILITY) {
			
			e.setMotd("§fInvencibilidade: §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()) + "§f.");
		}
		
		if (BukkitMain.state == StateEnum.GAME) {
			
			e.setMotd("§fAndamento: §e" + Countdown.getCountDown().Timer(Countdown.getCountDown().getTime()) + "§f.");
		}
		
		if (BukkitMain.state == StateEnum.END) {
			
			e.setMotd("§fVencedor: §e" + PlayerAPI.getInstance().getPlayer(0).getName());
		}
	}

}
