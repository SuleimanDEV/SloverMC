package br.com.slovermc.login.api;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.login.Main;

public final class Menu implements Listener {

	public static final void Inventory(final Player p) throws Exception {
		final Inventory menu = Bukkit.createInventory(p, 9, "§8§nConexões§f");

		if (APILogin.isLogging(p)) {

			menu.setItem(0,
					APIItems.createItem(Material.COMPASS, "§a§lConectar ao Lobby!", new String[] { "",
							"§6§lMatchMC§f§lMC §7» §fLogue-se para teleporar ao servidor §aLobby§f!", "§6§lMatchMC§f§lMC §7» §fClique para conectar ao servidor de §aLobby§f!", "" }, 1, (byte) 10));
		
			final ItemStack book = new ItemStack(Material.BOOK, 1, (byte) 0);
			final ItemMeta bookk = book.getItemMeta();
			bookk.setDisplayName("§7§lInformaçoes de sua Conexão");
			bookk.setLore(Arrays.asList(new String[] { "",
					"§7Jogador: §f" + p.getName(), 
					"§7IP: §f" + p.getAddress(), 
					"§7Computer IP: §f" + ComputerIP.getComputerIP(APILogin.playerAddress.get(p))}));
			bookk.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			book.setItemMeta(bookk);
			menu.setItem(1, book);
			
			p.openInventory(menu);
			return;
		} else {
			menu.setItem(0,
					APIItems.createItem(Material.COMPASS, "§a§lConectar ao Lobby!", new String[] { "",
							"§6§lMatchMC§f§lMC §7» §fLogue-se para teleporar ao servidor §aLobby§f!", "§6§lMatchMC§f§lMC §7» §fClique para conectar ao servidor de §aLobby§f!", "" }, 1, (byte) 10));
		
				final ItemStack book = new ItemStack(Material.BOOK, 1, (byte) 0);
				final ItemMeta bookk = book.getItemMeta();
				bookk.setDisplayName("§7§lInformaçoes de sua Conexão");
				bookk.setLore(Arrays.asList(new String[] { "",
						"§7Jogador: §f" + p.getName(), 
						"§7IP: §f" + p.getAddress(), 
						"§7Computer IP: §f" + ComputerIP.getComputerIP(APILogin.playerAddress.get(p))}));
				bookk.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				book.setItemMeta(bookk);
				menu.setItem(1, book);
			
		//	menu.setItem(1,
			//		APIItems.createItem(Material.COMPASS, "§7§lInformaçoes de sua Conexão", new String[] { "",
					//		"§7Jogador: §f" + p.getName(), 
						//	"§7IP: §f" + p.getAddress(), 
						//	"§7Computer IP: §f" + ComputerIP.getComputerIP(APILogin.playerAddress.get(p))}, 1, (byte) 0));
			p.openInventory(menu);
		}
	}
	
	public static void sendServer(final Player p, final String server) {
		 ByteArrayDataOutput out = ByteStreams.newDataOutput();
	     out.writeUTF("Connect");
		 out.writeUTF(server);
		 p.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
   }
	
	@EventHandler
	public final void onClick(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§8§nConexões§f") && e.getCurrentItem() != null) {
			if (e.getCurrentItem().getType() == Material.COMPASS) {
				if (APILogin.isLogging(p)) {
					e.setCancelled(true);
					p.closeInventory();
					API.sendPlayerMessage(p, "§cVocê precisa estar logado para se conectar!");
					return;
				} else {
					e.setCancelled(true);
					p.closeInventory();
					sendServer(p, "lobby");
				}
			}
			if (e.getCurrentItem().getType() == Material.BOOK) {
				e.setCancelled(true);
			}
		}
	}
}