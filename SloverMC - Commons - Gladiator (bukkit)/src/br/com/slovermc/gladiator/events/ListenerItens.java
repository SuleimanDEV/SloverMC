package br.com.slovermc.gladiator.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.slovermc.gladiator.BukkitMain;

public class ListenerItens implements Listener {

	public static HashMap<Player, String> desafiar = new HashMap<>();
	public static ArrayList<String> dawn = new ArrayList<>();
	public static HashMap<Player, String> desafiar1 = new HashMap<>();
	public static ArrayList<String> dawn1 = new ArrayList<>();
	public static ArrayList<String> clicado = new ArrayList<>();
	public static ArrayList<String> clicado2 = new ArrayList<>();

	public static UUID toFight;
	public static UUID toFight1;
	public static UUID toFight2;

	@EventHandler
	public final static void ListenerClique(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		Player clike = (Player) e.getRightClicked();
		ItemStack item = p.getItemInHand();

		if (item.getType() == Material.IRON_FENCE) {
			if (item.getItemMeta().getDisplayName().equals("§eDesafiar Gladiator §7(Clique)")) {
				if (dawn.contains(clike.getName())) {
					p.sendMessage("§c§lERRO §fEspere um pouco para desafiar alguém.");
					return;
				}
				if (desafiar.containsKey(clike)) {
					BukkitMain.IniciarGladiator(clike, p);
					desafiar.remove(p);
					desafiar.remove(clike);
					return;
				}
				p.sendMessage("§2§lDESAFIO §fVocê desafiou o§e " + clike.getName() + "§f, aguardando resposta.");
				clike.sendMessage("§2§lDESAFIADO §fVocê foi desafiado por§e " + p.getName() + "§f.");
				desafiar.put(p, clike.getName());
				dawn.add(clike.getName());
				
				toFight = null;
				toFight1 = null;
			}

			Bukkit.getScheduler().runTaskLater(BukkitMain.getPlugin(), new Runnable() {
				public void run() {
					desafiar.remove(p);
					dawn.remove(clike.getName());
				}
			}, 8 * 20);
		}

	}

	@EventHandler
	public final static void ListenerGlad(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		Player clike = (Player) e.getRightClicked();
		ItemStack item = p.getItemInHand();

		if (item.getType() == Material.IRON_FENCE && item.getItemMeta().getDisplayName() == "§aDesafiar Gladiator Customizado §8(APENAS VIPs)") {
			if (!(p.hasPermission("gladiator.custom"))) {
				p.sendMessage("§c§lERRO §fVocê precisa ser §a§lVIP §fpara poder utilizar.");
			} else if (item.getItemMeta().getDisplayName().equals("§eDesafiar Gladiator Customizado §7(APENAS VIPs)")
					&& p.hasPermission("gladiator.custom")) {
				if (dawn1.contains(clike.getName())) {
					return;
				}
				p.sendMessage("§c§lERRO §fModo sendo desenvolvido.");
				desafiar1.put(p, clike.getName());
				dawn1.add(clike.getName());
			}

			Bukkit.getScheduler().runTaskLater(BukkitMain.getPlugin(), new Runnable() {
				public void run() {
					desafiar1.remove(p);
					dawn1.remove(clike.getName());
				}
			}, 8 * 20);
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public final static void ListenerFilaRanked(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInHand();
		if (item.getType() == Material.getMaterial(262)) {
			if (!(p.hasPermission("gladiator.ranked"))) {
				p.sendMessage("§c§lERRO §fVocê precisa ser §a§lVIP §fpara poder utilizar.");
			} else if (item.getItemMeta().getDisplayName().equals("§eGladiator Ranked §7(APENAS VIPs)")
					&& p.hasPermission("gladiator.ranked")) {
				ItemStack icone = new ItemStack(Material.ARROW);
				ItemMeta iconem = icone.getItemMeta();
				iconem.setDisplayName("§eGladiator Ranked §7(Procurando...)");
				iconem.addEnchant(Enchantment.OXYGEN, 10, true);
				icone.setItemMeta(iconem);
				p.updateInventory();
				p.setItemInHand(icone);
				p.sendMessage("§e§lFILA §fVocê entrou na §6lFILA§f do GLADIATOR §b§lRANKED§f.");
				if (toFight1 == null) {
					toFight1 = p.getUniqueId();
					return;
				} else {
					Player playerToFight1 = Bukkit.getPlayer(toFight1);
					BukkitMain.IniciarGladiator(p, playerToFight1);
					toFight1 = null;
				}

			}

		}

		if (item.getType() == Material.getMaterial(262)) {
			if (item.getItemMeta().getDisplayName().equals("§eGladiator Ranked §7(Procurando...)")) {
				ItemStack icone = new ItemStack(262, 1, (short) 8);
				ItemMeta iconem = icone.getItemMeta();
				iconem.setDisplayName("§eGladiator Ranked §7(APENAS VIPs)");
				icone.setItemMeta(iconem);
				p.setItemInHand(icone);
				p.updateInventory();
				p.sendMessage("§e§lFILA §fVocê saiu da §6lFILA§f do GLADIATOR §b§lRANKED§f..");
				toFight1 = null;
			}
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public final static void ListenerFila(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInHand();
		if (item.getType() == Material.getMaterial(351)) {
			if (item.getItemMeta().getDisplayName().equals("§eProcurar Partida §7(Clique)")) {
				ItemStack icone = new ItemStack(351, 1, (short) 10);
				ItemMeta iconem = icone.getItemMeta();
				iconem.setDisplayName("§eProcurando partidas...");
				icone.setItemMeta(iconem);
				p.updateInventory();
				p.setItemInHand(icone);
				p.sendMessage("§a§lFASTGLAD §fVocê entrou na fila do Gladiator.");
				toFight1 = null;
				if (toFight == null) {
					toFight = p.getUniqueId();
					return;
				} else {
					Player playerToFight = Bukkit.getPlayer(toFight);
					BukkitMain.IniciarGladiator(p, playerToFight);
					toFight = null;
					toFight1 = null;

				}

			}

		}

		if (item.getType() == Material.getMaterial(351)) {
			if (item.getItemMeta().getDisplayName().equals("§eProcurando partidas...")) {
				ItemStack icone = new ItemStack(351, 1, (short) 8);
				ItemMeta iconem = icone.getItemMeta();
				iconem.setDisplayName("§eProcurar Partida §7(Clique)");
				icone.setItemMeta(iconem);
				p.setItemInHand(icone);
				p.updateInventory();
				p.sendMessage("§a§lFASTGLAD §fVocê saiu da fila do Gladiator.");

				toFight = null;
				toFight1 = null;

			}
		}

	}
}
