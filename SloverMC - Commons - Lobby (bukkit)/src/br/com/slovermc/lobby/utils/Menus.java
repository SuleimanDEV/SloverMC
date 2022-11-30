package br.com.slovermc.lobby.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.lobby.BukkitMain;

public final class Menus implements Listener {

	public static String randomcolor()  {
		Random random = new Random();
		random.nextInt(9);
		return "§" + random;
	}

	public static int randomByte() {
		return new Random().nextInt(12);
	}

	public static void sendServer(Player p, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		p.sendPluginMessage(br.com.slovermc.lobby.BukkitMain.getInstance(), "BungeeCord", out.toByteArray());
	}

	public static int totalhg() {
		int i1 = br.com.slovermc.lobby.BukkitMain.onlinehga1;
		int i2 = br.com.slovermc.lobby.BukkitMain.onlinehga2;
		int i3 = br.com.slovermc.lobby.BukkitMain.onlinehga3;
		int i4 = br.com.slovermc.lobby.BukkitMain.onlinehga4;
		int i5 = br.com.slovermc.lobby.BukkitMain.onlinehga5;
		int i6 = br.com.slovermc.lobby.BukkitMain.onlinehga6;
		int total = i1 + i2 + i3 + i4 + i5 +i6;
		return total;
	}

	@SuppressWarnings("deprecation")
	public static void hgRooms(Player p) {
		Inventory menu = Bukkit.createInventory(p, 27, "§7Escolha uma sala de hg");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), () -> {

		menu.setItem(10, LobbyItens.newItem(Material.getMaterial(351), "§a§lHG (1)",  new String[] {"", "§3§l" + br.com.slovermc.lobby.BukkitMain.onlinehga1 + " §fjogadores conectados.", "", "§b§lClique para conectar"}, 1,
				(byte) 10));

		menu.setItem(11, LobbyItens.newItem(Material.getMaterial(351), "§a§lHG (2)",
				new String[] {"", "§3§l" + br.com.slovermc.lobby.BukkitMain.onlinehga2 + " §fjogadores conectados.", "", "§b§lClique para conectar"}, 1,
				(byte) 10));

		menu.setItem(12, LobbyItens.newItem(Material.getMaterial(351), "§a§lHG (3)",
				new String[] {"", "§3§l" + br.com.slovermc.lobby.BukkitMain.onlinehga3 + " §fjogadores conectados.", "", "§b§lClique para conectar"}, 1,
				(byte) 10));

		menu.setItem(13, LobbyItens.newItem(Material.getMaterial(351), "§a§lHG (4)",
				new String[] {"", "§3§l" + br.com.slovermc.lobby.BukkitMain.onlinehga4 + " §fjogadores conectados.", "", "§b§lClique para conectar"}, 1,
				(byte) 10));
		
		menu.setItem(14, LobbyItens.newItem(Material.getMaterial(351), "§a§lHG (5)",
				new String[] {"", "§3§l" + br.com.slovermc.lobby.BukkitMain.onlinehga5 + " §fjogadores conectados.", "", "§b§lClique para conectar"}, 1,
				(byte) 1));
		menu.setItem(15, LobbyItens.newItem(Material.getMaterial(351), "§a§lHG (6)",
				new String[] {"", "§3§l" + br.com.slovermc.lobby.BukkitMain.onlinehga6 + " §fjogadores conectados.", "", "§b§lClique para conectar"}, 1,
				(byte) 1));

		menu.setItem(22, LobbyItens.newItem(Material.ARROW, "§aVoltar ao menu inicial.",
				new String[] {""}));
		}, 0, 20);
		p.openInventory(menu);
	}

	public static void Servidores(Player p) {
		Inventory menu = Bukkit.createInventory(p, 27, "§7Escolha um modo de jogo");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), () -> {

		menu.setItem(11, LobbyItens.newItem(Material.MUSHROOM_SOUP, "§aHungerGames", 
				new String[] {"§8Competitivo", "", "§7HungerGames é um modo de jogo onde", "§7o ultimo jogador sobrevivente é o", "§7vencedor.", "", "§a"+ totalhg() + " conectados ao servidor"}, 1,
				(byte) 0));
		menu.setItem(13, LobbyItens.newItem(Material.IRON_FENCE, "§aGladiator §e§lNOVO",
				new String[] {"§8Treinamento", "", "§7Gladiator é um modo de jogo onde", "§7você desafiará seu opopente em um", "§7duelo.", "", "§a" + br.com.slovermc.lobby.BukkitMain.onlinegladiator +
		" conectados ao servidor."}, 1,
				(byte) 10));
		menu.setItem(15, LobbyItens.newItem(Material.DIAMOND_SWORD, "§aKitPvP",
				new String[] {"§8Treinamento", "", "§7KitPvP é um modo de jogo onde você", "§7pode ir em warps, duelar, e desafiar", "§7seus amigos.", "", "§a" + br.com.slovermc.lobby.BukkitMain.onlinekitpvp + 
		" conectados ao servidor."}, 1,
				(byte) 0));
		}, 0, 20);
		p.openInventory(menu);
	}

	@EventHandler
	public void aoServersClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getTitle().equalsIgnoreCase("§7Escolha um modo de jogo") && e.getCurrentItem() != null) {
				e.setCancelled(true);
				if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
					p.closeInventory();
					hgRooms(p);
				}
				if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
					p.closeInventory();
					sendServer(p, "gladiator");
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					p.closeInventory();
					sendServer(p, "pvp-1");
				}
			}
			else if (e.getInventory().getTitle().equalsIgnoreCase("§7Escolha uma sala de hg") && e.getCurrentItem() != null) {
				e.setCancelled(true);
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lHG (1)")) {
						sendServer(p, "hga1");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lHG (2)")) {
						sendServer(p, "hga2");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lHG (3)")) {
						sendServer(p, "hga3");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lHG (4)")) {
						sendServer(p, "hga4");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lHG (5)")) {
						sendServer(p, "hga5");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lHG (6)")) {
						sendServer(p, "hga6");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aVoltar ao menu inicial.")) {
						e.setCancelled(true);
						p.closeInventory();
						Menus.Servidores(p);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
			}
		}
	}
}