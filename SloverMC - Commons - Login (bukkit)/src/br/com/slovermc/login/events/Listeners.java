package br.com.slovermc.login.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;

import br.com.slovermc.login.Main;
import br.com.slovermc.login.api.API;
import br.com.slovermc.login.api.APIItems;
import br.com.slovermc.login.api.APILogin;
import br.com.slovermc.login.api.APITitle;
import br.com.slovermc.login.api.Menu;

public final class Listeners implements Listener {

	public static final int[] bytes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	@SuppressWarnings("deprecation")
	@EventHandler
	public final void onAnsyncPlayerPreLoginEvent(final AsyncPlayerPreLoginEvent e) {
		for (final Player all : Bukkit.getOnlinePlayers()) {
			if (e.getName().equalsIgnoreCase(all.getName())) {
				e.disallow(Result.KICK_OTHER,
						"§a§lONLINE " + "\n" + "§fJá possui um player online com este nickname!");
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoinEvent2(PlayerJoinEvent e) {
		e.setJoinMessage(null);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	public final void onPlayerInteract(final PlayerInteractEvent e) throws Exception {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.COMPASS) {
			e.setCancelled(true);
			Menu.Inventory(p);
		}
	}
	
	@EventHandler
	public void ComandoDesconhecido(PlayerCommandPreprocessEvent e) {
		if (e.isCancelled()) {
			return;
		}
		String msg = e.getMessage().split(" ")[0];
		HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
		if (topic == null) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
    public void Antivoid(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        if (loc.getBlockY() <= 0){
        	p.teleport(e.getPlayer().getWorld().getSpawnLocation());
        }
    }  

	@EventHandler
	public final void onPlayerLoginEvent(final PlayerLoginEvent e) {
		final Player p = e.getPlayer();

		APILogin.playerAddress.put(p, e.getAddress());

		APILogin.setPlayerLogMode(p, APILogin.Logins.Logging);
		APILogin.addPlayerToQueeu(p);
	}

	public static final void startCounting(final Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (p.isOnline() && APILogin.isLogging(p)) {
					p.kickPlayer(
							"§4§lLOGIN\n         §fVocê demorou demais para se logar!\n§fVolte e logue em 30 segundos \n§ewww.slovermc.com");
				}
			}
		}, 800L);
	}

	@EventHandler
	public final void onPlayerJoinEvent(final PlayerJoinEvent e) {
		e.setJoinMessage(null);
		final Player p = e.getPlayer();
		
		APITitle.sendTitle(p, "§6§lSLOVER");
		APITitle.sendSubTitle(p, "§fBem-vindo ao §6Login");

		p.getInventory().clear();
		p.getInventory().setArmorContents(null);

		p.setGameMode(GameMode.SURVIVAL);

		for (int i = 1; i < 50; i++) {
			API.sendPlayerMessage(p, " ");
		}

		p.getInventory().setItem(4, APIItems.createItem(Material.COMPASS, "§aConectar §7(Clique para abrir)",
				new String[] { null }, 1, (byte) 0));
		
		if (!APILogin.isLogging(p)) {
			
			APILogin.setPlayerLogMode(p, APILogin.Logins.Logging);
			APILogin.addPlayerToQueeu(p);
		}

		startCounting(p);

		if (APILogin.isRegistered(p)) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				@Override
				public void run() {
					if (p.isOnline() && APILogin.isRegistered(p) && APILogin.isLogging(p)) {
						API.sendPlayerMessage(p, "§4§lLOGIN§f Utilize /login <senha> para se logar.");
					}
				}
			}, 0, 200L);
		}
		if (!APILogin.isRegistered(p)) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				@Override
				public void run() {
					if (p.isOnline() && !APILogin.isRegistered(p) && APILogin.isLogging(p)) {
						API.sendPlayerMessage(p, "§4§lREGISTER§f Utilize /register <senha> <confirmarsenha> para se registrar.");
					}
				}
			}, 0, 160L);
		}
	}

	@EventHandler
	public final void onPlayerQuitEvent(final PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	public final void onPlayerCommandPreprocessEvent(final PlayerCommandPreprocessEvent e) {
		if (!e.getMessage().startsWith("/login") && !e.getMessage().startsWith("/LOGIN")
				&& !e.getMessage().startsWith("/register") && !e.getMessage().startsWith("/REGISTER")
				&& !e.getMessage().startsWith("/setworldspawn") && !e.getMessage().startsWith("/SETWORLDSPAWN")
				&& !e.getMessage().startsWith("/changepass") && !e.getMessage().startsWith("/CHANGEPASS")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§c§lERRO §fVocê não pode executar comandos no servidor de login.");
		}
	}

	@EventHandler
	public final void onEntityDamageEvent(final EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onBlockPlaceEvent(final BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.LAVA_BUCKET || p.getItemInHand().getType() == Material.WATER_BUCKET
				|| p.getItemInHand().getType() == Material.WATER || p.getItemInHand().getType() == Material.LAVA) {
			e.setCancelled(true);
			API.sendPlayerMessage(p, "§c§lERRO§f O uso deste material está bloqueado em nosso servidor de login.");
			p.getItemInHand().setType(Material.AIR);
		} else if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			}
	}

	@EventHandler
	public final void onBlockBreakEvent(final BlockBreakEvent e) {
		final Player p = e.getPlayer();
		if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public final void onWeatherChangeEvent(final WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onFoodLevelChangeEvent(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onPlayerAchievementAwardedEvent(final PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onCreatureSpawnEvent(final CreatureSpawnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onPlayerDropItemEvent(final PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public final void onPlayerPickupItemEvent(final PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void Chat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		e.getPlayer().sendMessage("§cVocê não pode utilizar chat nesse servidor");
	}
	
	@EventHandler
	public void Entrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.teleport(new Location(Bukkit.getWorld("world"), 1993, 133, 1967));
	}
}
