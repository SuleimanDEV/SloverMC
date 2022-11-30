package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.utils.BattleStrings;
import br.com.slovermc.kitpvp.warps.WarpsAPI;

public final class ScoreboardCommand extends Command {

	public ScoreboardCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	public static final Scoreboard noScoreboard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		return board;
	}

	public static final void loadScoreboard(final Player bp) {
		if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			Score.createScoreSpawn(bp);
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.FPS) {
			Score.createScoreFps(bp);
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.EVENTO) {
			Score.createScoreEvento(bp);
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.ONEVSONE) {
			Score.createScore1v1(bp);
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.LAVA_CHALLENGE) {
			Score.createScoreChallenge(bp);
		} else {
			loadScoreboard(bp);
		}
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (args.length >= 0) {
			if (Score.hasScore.contains(bp)) {
				// remover //
				Score.hasScore.remove(bp);
				noScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				bp.setScoreboard(noScoreboard());
				bp.sendMessage("§6§lSCOREBOARD §fVocê desativou sua scoreboard.");
				return true;
			} else {
				// adicionar //
				Score.hasScore.add(bp);
				loadScoreboard(bp);
				bp.sendMessage("§6§lSCOREBOARD §fVocê ativou sua scoreboard.");
				return true;
			}
		}
		return false;
	}
}
