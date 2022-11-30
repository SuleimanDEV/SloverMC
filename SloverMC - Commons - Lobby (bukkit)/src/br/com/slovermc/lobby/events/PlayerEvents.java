package br.com.slovermc.lobby.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.spigotmc.ProtocolInjector;

import br.com.slovermc.lobby.BukkitMain;
import br.com.slovermc.lobby.api.TittleAPI;
import br.com.slovermc.lobby.api.XP;
import br.com.slovermc.lobby.hologramas.HologramaAPI;
import br.com.slovermc.lobby.hologramas.Holograma;
import br.com.slovermc.lobby.mysql.MySQL_Connection;
import br.com.slovermc.lobby.ranks.RankList;
import br.com.slovermc.lobby.score.Score;
import br.com.slovermc.lobby.utils.LobbyItens;
import br.com.slovermc.lobby.utils.Menus;
import net.minecraft.server.v1_7_R4.ChatClickable;
import net.minecraft.server.v1_7_R4.ChatHoverable;
import net.minecraft.server.v1_7_R4.ChatMessage;
import net.minecraft.server.v1_7_R4.ChatModifier;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.EnumClickAction;
import net.minecraft.server.v1_7_R4.EnumHoverAction;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import net.minecraft.server.v1_7_R4.PlayerList;

public class PlayerEvents implements Listener {

	public static boolean hasHologram = false;
	private static int VERSION = 47;

	/**
	 * Main login event
	 * @param e
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(final PlayerLoginEvent e) {
		if (MySQL_Connection.connection != null) {
			if (!BukkitMain.mysql.verifyPlayerRegister(e.getPlayer().getUniqueId())) {
				BukkitMain.mysql.registerPlayer(e.getPlayer());
			}
		}
	}

	public final static void sendLobbyConnection(Player p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (p.isOnline()) {
					TittleAPI.sendActionbarTitle(p, "§6§lMINIMATCH §fas §e§l14, 17 e 19 HORAS!");
				}
			}
		}, 0, 20L);
	}


	public final static void updateOnline(Player p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (p.isOnline()) {
					TittleAPI.sendActionbarTitle(p, "§6§lMINIMATCH §fas §e§l14, 17 e 19 HORAS!");
				}
			}
		}, 0, 20L);
	}

	public static final ArrayList<Player> playersoff = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public final static void updatePlayers(Player p) {
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (playersoff.contains(todos)) {
				todos.hidePlayer(p);
			}
		}
	}
	
	  @EventHandler
	  public final static void TabEntrar(PlayerJoinEvent e) {
	    Player p = e.getPlayer();
	    
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getInstance(), new Runnable() {
		public void run() {
	      
	        PlayerConnection connect = ((CraftPlayer)p).getHandle().playerConnection;
	        IChatBaseComponent cima = ChatSerializer.a("{'extra': [{text: '', color: 'aqua'}],'color': gold, 'text': '\n                    §6§lMatch§f§lMC §eNetwork                \n            §fAtualmente temos §a" + br.com.slovermc.lobby.BukkitMain.onlinePlayers + " §fjogador(es) no §aServidor \n'}");
	        
	        IChatBaseComponent baixo = ChatSerializer.a("{'extra': [{'color': 'aqua', 'text': '\n §bNick: §f" + p.getName() + " §1- §bLiga: " + RankList.getScoreRank(p) + "\n§bMais informações em §f@RedeMatchMC_\n"
					+ "', 'underline': 'true'}], 'color': 'gold', 'text': ''}");
	        if (((CraftPlayer)p).getHandle().playerConnection.networkManager.getVersion() < VERSION) {
	          return;
	        }
	        connect.sendPacket(new ProtocolInjector.PacketTabHeader(cima, baixo));
	      }
	    }, 1L, 20L);
	  }


	/**
	 * Main join event
	 * @param e
	 */
	@EventHandler
	public final static void ListenerJoibEvent(final PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.teleport(p.getWorld().getSpawnLocation());
		e.getPlayer().setGameMode(GameMode.SURVIVAL);
		updatePlayers(e.getPlayer());
		if (!hasHologram) {
			hasHologram = true;
			HologramaAPI.topXpHologramLoader();
		}
		final Player battleplayer = e.getPlayer();
		if (!HologramaAPI.canSee.contains(battleplayer)) {
			HologramaAPI.canSee.add(battleplayer);
		}
		for (Holograma hl : HologramaAPI.holograms) {
			if (hl != null) {
				hl.show(battleplayer);
			}
		}

		Holograma hl = HologramaAPI.holograma.get("hl");
		if (hl != null) {
			hl.show(battleplayer);
		}
		TittleAPI.sendTitle(e.getPlayer(), "§6§lMatch§f§lMC");
		TittleAPI.sendSubTitle(e.getPlayer(), "§fSeja bem-vindo a nossa rede!");
		sendLobbyConnection(e.getPlayer());
		e.getPlayer().getInventory().clear();
		e.getPlayer().updateInventory();
		LobbyItens.setLobbyItens(e.getPlayer());
		Score.hasScore.add(p);
		Score.createScoreLobby(p);
		XP.addXP(p, 0);
		for (int clearchat = 1; clearchat < 100; clearchat++) {
			e.getPlayer().sendMessage("");
		}
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("§6§lLOBBY §fEstamos com um novo IP para se divertir!");
		e.getPlayer().sendMessage("§6§lLOBBY §fAdicione agora esse IP em sua lista de servidores §6(jogar-matchmc.tk)§f.");
		
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		e.setQuitMessage(null);
		//		ScoreboardPlayer.hasscore.remove(e.getPlayer());
		//		ScoreboardPlayer.scoresz.remove(e.getPlayer());
	}

	public static final ArrayList<Player> delayender = new ArrayList<>();

	public static void removesdelay(final Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (delayender.contains(p)) {
					delayender.remove(p);
				}
			}		
		}, 100L);
	}

	@EventHandler
	public void Antivoid(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if (loc.getBlockY() <= 0){
			p.teleport(e.getPlayer().getWorld().getSpawnLocation());
		}
	}   

	@SuppressWarnings("deprecation")
	public static void hidePlayers(Player p) {
		for (Player todos : Bukkit.getOnlinePlayers()) {
			p.hidePlayer(todos);
		}
	}

	@SuppressWarnings("deprecation")
	public static void showPlayers(Player p) {
		for (Player todos : Bukkit.getOnlinePlayers()) {
			p.showPlayer(todos);
		}
	}

	public static final ArrayList<String> down = new ArrayList<>();
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getItemInHand().getType() == Material.LAVA || e.getPlayer().getItemInHand().getType() == Material.LAVA_BUCKET ||
				e.getPlayer().getItemInHand().getType() == Material.STATIONARY_LAVA) {
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR ||
					e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.getPlayer().sendMessage("§c§lERRO §fVocê não pode colocar §6§lLAVA§f em nossa rede!");
				e.setCancelled(true);
			}
		}
		else if (e.getPlayer().getItemInHand().getType() == Material.WATER || e.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET ||
				e.getPlayer().getItemInHand().getType() == Material.STATIONARY_WATER) {
			if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR ||
					e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.getPlayer().sendMessage("§c§lERRO §fVocê não pode colocar §b§lÁGUA§f no lobby!");
				e.setCancelled(true);
			}
		}
		//else if (e.getPlayer().getItemInHand().getType() == Material.MAGMA_CREAM) {
		//	if (delayender.contains(e.getPlayer())) {
		//		e.getPlayer().sendMessage("§c§lCOOLDOWN§f Aguarde para desabilitar os Jogadores.");
		//		e.setCancelled(true);
		//		e.getPlayer().updateInventory();
		////		return;
		//	}
		//	delayender.add(e.getPlayer());
		//	removesdelay(e.getPlayer());
		//	playersoff.add(e.getPlayer());
		//	hidePlayers(e.getPlayer());
		//	e.getPlayer().setItemInHand(LobbyItens.newItem(e.getPlayer(), Material.SLIME_BALL, 1, "§9§lJogadores §7(§a§lON§7)", 
		//				new String[] {null} , (byte) 0));
		//		e.setCancelled(true);
		//	}
		//else if (e.getPlayer().getItemInHand().getType() == Material.SLIME_BALL) {
		//	if (delayender.contains(e.getPlayer())) {
		//		e.getPlayer().sendMessage("§c§lCOOLDOWN§f Aguarde para habilitar os Jogadores.");
		//		e.setCancelled(true);
		//		e.getPlayer().updateInventory();
		//		return;
		//	}
		//	delayender.add(e.getPlayer());
		//	removesdelay(e.getPlayer());
		//	playersoff.remove(e.getPlayer());
		//	showPlayers(e.getPlayer());
		//	e.getPlayer().setItemInHand(LobbyItens.newItem(e.getPlayer(), Material.MAGMA_CREAM, 1, "§9§lJogadores §7(§c§lOFF§7)", 
		//		new String[] {null} , (byte) 0));
		//e.setCancelled(true);
		//}
		else if (e.getPlayer().getItemInHand().getType() == Material.NETHER_STAR) {
			e.setCancelled(true);
		}
		else if (e.getPlayer().getItemInHand().getType() == Material.COMPASS) {
			e.setCancelled(true);
			Menus.Servidores(e.getPlayer());
		}
	}

	@EventHandler
	public final void onItemSpawnClick(final PlayerInteractEvent e) throws Exception {
		final Player bp = e.getPlayer();
		if (bp.getItemInHand().getType() == Material.SKULL_ITEM) {
			e.setCancelled(true);
		}
	}

	public static void Link(String p, String msg, String link, String upmsg) {
		IChatBaseComponent base = new ChatMessage(msg, new Object[0]);
		base.setChatModifier(new ChatModifier());
		base.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.OPEN_URL, link));
		base.getChatModifier().a(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage(upmsg, new Object[0])));
		PlayerList list = MinecraftServer.getServer().getPlayerList();
		list.getPlayer(p).sendMessage(base);
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onFood(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onAwarded(final PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onWheater(final WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onDrop(final PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBreak(final BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlaca(final BlockPlaceEvent e) {
		e.setCancelled(true);
	}


	// Impulso ao apertar espaço 2 vezes
	//@EventHandler
	//public void onPlayerToggleFly(final PlayerToggleFlightEvent e) {
	//	final Player p = e.getPlayer();
	//	if (p.getGameMode() == GameMode.CREATIVE) {
	//		return;
	//	}
		//e.setCancelled(true);
		//p.setFlying(false);
		//p.setAllowFlight(false);
		//if (!p.isSneaking()) {
		//	p.setFallDistance(-2.0f);
		//	final Vector vector = p.getEyeLocation().getDirection();
		//	vector.multiply(3.3f);
		//	vector.setY(1.7f);
		//	p.setVelocity(vector);
		//} else {
		//	p.setFallDistance(-5.0f);
		//	final Vector vector = p.getEyeLocation().getDirection();
		//	vector.multiply(3.35f);
		//	vector.setY(1.9);
		//	p.setVelocity(vector);
	//	}
	//}

	//@SuppressWarnings("deprecation")
	//@EventHandler
	//public void onPlayerMove(final PlayerMoveEvent e) {
		//final Player p = e.getPlayer();
		//if (p.getGameMode() == GameMode.CREATIVE) {
		//	return;
		//}
		//if (p.isOnGround() && !p.getAllowFlight()) {
		//	p.setAllowFlight(true);
		//}
		//if (cFly.fly.add(p)) {
		//	return;
		//}
		//**if (cFly.fly.remove(p)) {
			//p.setAllowFlight(true);
		//}
	//}//

	@EventHandler
	public void ComandoDesconhecido(PlayerCommandPreprocessEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage().split(" ")[0];
		HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
		if (topic == null) {
			e.setCancelled(true);
			p.sendMessage("§c§lERRO §fComando não encontrado.");
		}
	}
}