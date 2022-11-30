package br.com.slovermc.kitpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.slovermc.kitpvp.account.DeadsAPI;
import br.com.slovermc.kitpvp.account.KillsAPI;
import br.com.slovermc.kitpvp.combat.CombatSystem;
import br.com.slovermc.kitpvp.deathevents.Status;
import br.com.slovermc.kitpvp.kits.Gladiator;
import br.com.slovermc.kitpvp.mysql.SkyAPI;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Evento.EventoAPI;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;

public final class BattlePlayerQuitEvent implements Listener {

	@EventHandler
	public final void onPlayerQuitEvent(final PlayerQuitEvent e) {
		e.setQuitMessage(null);
		final Player bp = e.getPlayer();
		WarpsAPI.resetPlayerWarps(bp);
		if (X1WarpListener.firstMatch == bp.getUniqueId()) {
			X1WarpListener.firstMatch = null;
		}
		if (EventoAPI.Players.contains(bp.getName())) {
			EventoAPI.Players.remove(bp.getName());
		}
		if (Gladiator.inFight.containsKey(bp.getName())) {
			final Player glader = Bukkit.getPlayerExact(Gladiator.inFight.get(bp.getName()));
			Gladiator.resetGladiatorByQuit(glader, bp);
			if (!CombatSystem.combat.containsKey(bp.getName())) {
				glader.sendMessage("§e§lKILL§f Você matou §e" + bp.getName() + "§f.");
				KillsAPI.addKills(glader, 1);
				Status.addMoneyToBattlePlayer(glader, 120);
				SkyAPI.addXp(glader, 25);
				Status.addStreakToBattlePlayer(glader);

				DeadsAPI.addDead(bp, 1);
				Status.removeStreakFromBattlePlayer(bp, glader);
			}
		}
		if (CombatSystem.combat.containsKey(bp.getName())) {
			final Player killer = Bukkit.getPlayerExact(CombatSystem.combat.get(bp.getName()));

			killer.sendMessage("§e§lKILL§f Você matou §e" + bp.getName() + "§f.");
			KillsAPI.addKills(killer, 1);
			Status.addMoneyToBattlePlayer(killer, 120);
			SkyAPI.addXp(killer, 25);
			Status.addStreakToBattlePlayer(killer);

			DeadsAPI.addDead(bp, 1);
			Status.removeStreakFromBattlePlayer(bp, killer);
		}
	}
}
