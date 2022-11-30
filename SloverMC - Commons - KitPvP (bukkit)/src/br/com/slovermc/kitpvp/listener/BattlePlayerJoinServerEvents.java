package br.com.slovermc.kitpvp.listener;

import java.net.InetAddress;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.potion.PotionEffect;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.AccountAPI;
import br.com.slovermc.kitpvp.account.AccountConstructor;
import br.com.slovermc.kitpvp.account.AccountFile;
import br.com.slovermc.kitpvp.account.AccountManager;
import br.com.slovermc.kitpvp.account.KillsAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.hologram.HalogramAPI;
import br.com.slovermc.kitpvp.hologram.Hologram;
import br.com.slovermc.kitpvp.mysql.MySQL_Connection;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class BattlePlayerJoinServerEvents implements Listener {

	public static final HashMap<String, InetAddress> macAddress = new HashMap<>();

	public static final HashMap<String, String> NAME = new HashMap<>();

	public static boolean hasHologram = false;

	@EventHandler
	public final void onPlayerLoginEvent(final PlayerLoginEvent e) {
		final Player bp = e.getPlayer();
		KillsAPI.newKills(bp);
		if (!AccountManager.hasAccount(bp.getName())) {
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".UniqueID",
					bp.getUniqueId().toString());
			AccountFile.getAccountsFile()
					.set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".OficialNickName", bp.getName());
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Kills", 0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Deaths", 0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".KillStreak",
					0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Xp", 0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".DoubleXp", 0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Money", 0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Cash", 0);
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".FirstLogin",
					AccountConstructor.BrasilHour());
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".LastLogin",
					AccountConstructor.BrasilHour());
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Type",
					"Gamer");
			AccountFile.getAccountsFile()
					.set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".YoutubeChannel", "Nenhum");
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Twitter",
					"Nenhum");
			AccountFile.getAccountsFile().set(AccountConstructor.Prefix + bp.getName().toLowerCase() + ".Email",
					"Nenhum");
			AccountFile.saveAccountsFile();
		}
	}
	
	@EventHandler
	public void CommandsPlugins(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
			if (e.getMessage().toLowerCase().startsWith("/plugins") || (e.getMessage().toLowerCase().startsWith("/bukkit:help") || (e.getMessage().toLowerCase().startsWith("/help") || (e.getMessage().toLowerCase().startsWith("/bukkit:about") || (e.getMessage().toLowerCase().startsWith("/minecraft:kick") || (e.getMessage().toLowerCase().startsWith("/icanhasbukkit") || (e.getMessage().toLowerCase().startsWith("/ipwl") || (e.getMessage().toLowerCase().startsWith("/minecraft:ban") || (e.getMessage().toLowerCase().startsWith("/plugin") || (e.getMessage().toLowerCase().startsWith("/help") || (e.getMessage().toLowerCase().startsWith("/?") || (e.getMessage().toLowerCase().startsWith("/bukkit:ver") || (e.getMessage().toLowerCase().startsWith("/ver") || (e.getMessage().toLowerCase().startsWith("/about") || (e.getMessage().toLowerCase().startsWith("/pl")))))))))))))))) {
				e.setCancelled(true);
				p.sendMessage("§c§lERRO §fEste comando se encontra bloqueado.");
			}
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(final PlayerLoginEvent e) {
		if (MySQL_Connection.connection != null) {
			if (!BukkitMain.mysql.verifyPlayerRegister(e.getPlayer().getUniqueId())) {
				BukkitMain.mysql.registerPlayer(e.getPlayer());
			}
		}
	}
	@EventHandler
	public final void onPlayerJoinEvent(final PlayerJoinEvent e) {
		final Player battleplayer = e.getPlayer();
		if (!hasHologram) {
			hasHologram = true;
			HalogramAPI.topXpHologramLoader();
		}
		if (!HalogramAPI.canSee.contains(battleplayer)) {
			HalogramAPI.canSee.add(battleplayer);
		}
		for (Hologram hl : HalogramAPI.holograms) {
			if (hl != null) {
				hl.show(battleplayer);
			}
		}

		Hologram hl = HalogramAPI.holograma.get("hl");
		if (hl != null) {
			hl.show(battleplayer);
		}

		KitAPI.setKit(battleplayer, "Nenhum");
		AccountFile.getAccountsFile().set(AccountConstructor.Prefix + battleplayer.getName().toLowerCase() + ".LastLogin",
				AccountConstructor.BrasilHour());
		AccountFile.saveAccountsFile();
		battleplayer.setGameMode(GameMode.SURVIVAL);
		e.setJoinMessage(null);
		Score.hasScore.add(battleplayer);
		LocationsConstructor.teleportToBattleWarpLocation(battleplayer, "Spawn");
		SpawnWarpListener.loadWarpSpawnMethods(battleplayer);
		AccountAPI.updateLastLogin(BattlePlayerAPI.BattlePlayer(battleplayer));
		for (PotionEffect effect : battleplayer.getActivePotionEffects()) {
			battleplayer.removePotionEffect(effect.getType());
		}
		for (int i = 1; i < 50; i++) {
			battleplayer.sendMessage(" ");
		}
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("");
		battleplayer.sendMessage("§b§lCONNECT §fConectando.");
		battleplayer.sendMessage("§b§lCONNECT §fVocê foi conectado com sucesso.");
		battleplayer.sendMessage("");
		TittleAPI.sendTittle(battleplayer, "§6§lKITPVP");
		TittleAPI.sendSubTittle(battleplayer, "§fDivirta-se jogando na rede!");
	}
}