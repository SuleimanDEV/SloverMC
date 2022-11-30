package br.com.slovermc.hg.tempos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.scoreboard.Score;

public class InvincibilityListeners implements Listener {

	@EventHandler
	protected void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.INVINCIBILITY) {
			Score.hasScore.add(player);
			Score.createScoreInv(player);
			e.setJoinMessage("§a");
			player.getInventory().clear();
			player.getInventory().setArmorContents(null);
			if (!PlayerAPI.getInstance().hasPlayer(player))
				PlayerAPI.getInstance().addPlayer(player);
			KitAPI.getInstance().setKit(player, "Nenhum");
			if (BukkitMain.getPlugin().duoKit())
				KitAPI.getInstance().setKit2(player, "Nenhum");
			Manager.getInstance().checkKit(player);
		}
	}

	//@EventHandler
	//public void onPickup(PlayerPickupItemEvent e) {
	//	if (Main.state == StateEnum.INVINCIBILITY) {
	//		e.setCancelled(false);
	//	}
	//}

	@EventHandler
	protected void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (BukkitMain.state == StateEnum.INVINCIBILITY) { 
			String player_name = player.getName();
			String kit_name = null;
			if (!BukkitMain.getPlugin().duoKit()) {
				kit_name = KitAPI.getInstance().getKit(player);
			} else {
				kit_name = KitAPI.getInstance().getKit(player) + " e " + KitAPI.getInstance().getKit2(player);
			}
			Bukkit.broadcastMessage("§b" + player_name + "(" + kit_name + ") §bfugiu da partida e foi desclassificado.");
			Bukkit.broadcastMessage("§e" + PlayerAPI.getInstance().getPlayers().length + " Jogadores restantes.");
			PlayerAPI.getInstance().removePlayer(player);
			Manager.getInstance().checkWin();
		}
	}

	@EventHandler
	protected void onDamage(EntityDamageEvent e) {
		if (BukkitMain.state == StateEnum.INVINCIBILITY) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	protected void onFood(FoodLevelChangeEvent e) {
		if (BukkitMain.state == StateEnum.INVINCIBILITY) {
			e.setCancelled(true);
		}
	}
}
