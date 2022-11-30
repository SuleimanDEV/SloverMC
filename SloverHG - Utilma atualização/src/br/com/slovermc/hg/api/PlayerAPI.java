package br.com.slovermc.hg.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.commands.cLobbyCMD;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.mysql.SkyAPI;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

public class PlayerAPI {

	private HashMap<Player, Integer> kills = new HashMap<>();
	private static PlayerAPI instance = new PlayerAPI();
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Player> spectators = new ArrayList<>();
	private ArrayList<Player> admins = new ArrayList<>();
	private ArrayList<Player> sc = new ArrayList<>();

	public static PlayerAPI getInstance() {
		return instance;
	}

	public void sendPlayerBungee(Player p, String s) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(s);
		p.sendPluginMessage(BukkitMain.getPlugin().plugin, "BungeeCord", out.toByteArray());
	}

	public void itemsStarting(final Player p) {
		final ItemStack chest = new ItemStack(Material.CHEST);
		final ItemMeta chestmeta = chest.getItemMeta();
		chestmeta.setDisplayName("§aKit 1 §7(Clique)");
		chest.setItemMeta(chestmeta);
		ItemStack chest2 = null;
		//    if (Main.getPlugin().duoKit()) {
		chest2 = new ItemStack(Material.CHEST);
		final ItemMeta chestmeta2 = chest2.getItemMeta();
		chestmeta2.setDisplayName("§aKit 2 §7(Clique)");
		chest2.setItemMeta(chestmeta2);
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("            §6§lMatch§f§lHG");
		p.sendMessage("§fVocê foi enviado ao servidor de §6Hardcore Games");
		p.sendMessage("§fAdquira Kits e vantagens agora em nossa loja!");
		p.sendMessage("");
		p.sendMessage("       §ahttps://loja.matchmc.com.br");
		p.sendMessage("");
		p.setMaxHealth(20.0D);
		p.setHealth(20.0D);
		p.setFoodLevel(20);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(0, chest);
		p.getInventory().setItem(1, chest2);
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public void addEspectator(Player p) {
		spectators.add(p);
	}

	public void addAdmin(Player p) {
		admins.add(p);
	}

	public void addSc(Player p) {
		sc.add(p);
	}

	public void addKills(Player p, int k) {
		if (!kills.containsKey(p))
			kills.put(p, 0);
		kills.put(p, kills.get(p) + k);
	}

	public void removePlayer(Player p) {
		if (players.contains(p))
			players.remove(p);
	}

	public void removeEspectator(Player p) {
		if (spectators.contains(p))
			spectators.remove(p);
	}

	public void removeAdmin(Player p) {
		admins.remove(p);
	}

	public void removeSc(Player p) {
		sc.remove(p);
	}

	public void removeKills(Player p, int k) {
		if (kills.containsKey(p))
			kills.put(p, kills.get(p) - k);
	}

	public boolean hasPlayer(Player p) {
		return players.contains(p);
	}

	public boolean hasEspectator(Player p) {
		return spectators.contains(p);
	}

	public boolean hasAdmin(Player p) {
		return admins.contains(p);
	}

	public boolean hasSc(Player p) {
		return sc.contains(p);
	}

	public Player[] getAdmins() {
		return admins.toArray(new Player[admins.size()]);
	}

	public Player[] getEspectators() {
		return spectators.toArray(new Player[spectators.size()]);
	}

	public Player getPlayer(int n) {
		return players.get(n);
	}

	public Player[] getPlayers() {
		return players.toArray(new Player[players.size()]);
	}

	public Player[] getScPlayers() {
		return sc.toArray(new Player[sc.size()]);
	}

	public Integer getKills(Player p) {
		return kills.get(p) != null ? kills.get(p) : 0;
	}

	public Integer[] getKillsTotal() {
		return kills.values().toArray(new Integer[kills.size()]);
	}

	public static void sendServer(Player player, String serverName) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(serverName);
		player.sendPluginMessage(BukkitMain.getInstance(), "BungeeCord", out.toByteArray());
	}

	@SuppressWarnings("deprecation")
	public void checkWin() {
		if (PlayerAPI.getInstance().getPlayers().length <= 1 && BukkitMain.state == StateEnum.GAME) {
			if (PlayerAPI.getInstance().getPlayers().length == 1) {
				Manager.getInstance().endTimer();
				Player v = PlayerAPI.getInstance().getPlayer(0);
				Location winner = new Location(v.getWorld(), v.getLocation().getX(), v.getLocation().getY() + 60,
						v.getLocation().getZ());
				for (double x = -2; x <= 2; x += 1) {
					for (double z = -2; z <= 2; z += 1) {
						Location glass = new Location(winner.getWorld(), winner.getX() + x, winner.getY() - 3,
								winner.getZ() + z);
						Location cake = new Location(winner.getWorld(), winner.getX() + x, winner.getY() - 2,
								winner.getZ() + z);
						glass.getBlock().setType(Material.GLASS);
						cake.getBlock().setType(Material.CAKE_BLOCK);
					}
				}
				v.teleport(winner.add(0, 1, 0));
				SkyAPI.addXp(v, 50);
				StatsAPI.getInstance().addWins(v, 1);
				StatsAPI.getInstance().addCoins(v, 500);
				v.getInventory().clear();
				v.getInventory().setArmorContents(null);
				v.getInventory().setItem(0, new ItemStack(Material.MAP));
				v.getInventory().setItem(4, new ItemStack(Material.WATER_BUCKET));

				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("§b" + v.getName() + " §evenceu a partida, parabéns a ele!");
						Manager.getInstance().firework(v);
					}
				}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
				new BukkitRunnable() {
					public void run() {
						for (Player players : Bukkit.getOnlinePlayers()) {
							cLobbyCMD.sendServer(v, "lobby");
							cLobbyCMD.sendServer(players, "lobby");
						}
					}
				}.runTaskLater(BukkitMain.getPlugin(), 300);
			}
			new BukkitRunnable() {
				public void run() {
					Bukkit.shutdown();
				}
			}.runTaskLater(BukkitMain.getPlugin(), 350);
		} else { if (PlayerAPI.getInstance().getPlayers().length == 0) {
			Manager.getInstance().endTimer();
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.kickPlayer("§cNínguem venceu a partida!\n\n§c§cCaso queira comprar VIP, visite nosso site: "
						+ "\n§cServidor reiniciando!");
			}

			Bukkit.shutdown();
		}
		}
	}
}

