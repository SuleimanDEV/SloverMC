package br.com.slovermc.kitpvp.warps.Spawn;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.kits.Ajnin;
import br.com.slovermc.kitpvp.kits.Anchor;
import br.com.slovermc.kitpvp.kits.AntiStomper;
import br.com.slovermc.kitpvp.kits.Fireman;
import br.com.slovermc.kitpvp.kits.Fisherman;
import br.com.slovermc.kitpvp.kits.Hulk;
import br.com.slovermc.kitpvp.kits.Kangaroo;
import br.com.slovermc.kitpvp.kits.Magma;
import br.com.slovermc.kitpvp.kits.Minato;
import br.com.slovermc.kitpvp.kits.Monk;
import br.com.slovermc.kitpvp.kits.Ninja;
import br.com.slovermc.kitpvp.kits.Phantom;
import br.com.slovermc.kitpvp.kits.PvP;
import br.com.slovermc.kitpvp.kits.Stomper;
import br.com.slovermc.kitpvp.kits.Thor;
import br.com.slovermc.kitpvp.kits.Timelord;
import br.com.slovermc.kitpvp.kits.Viking;

public final class SpawnKitsMenu implements Listener {

	@SuppressWarnings("deprecation")
	public static final void oPenKitsMenu(final Player bp) {

		Inventory menu = Bukkit.createInventory(bp, 54, "§8§nKits [1]");
		menu.setItem(27, SpawnItens.newItem(Material.getMaterial(351), "§8Pagina Anterior", new String[] { null }, 1,
				(byte) 8));
		menu.setItem(35, SpawnItens.newItem(Material.getMaterial(351), "§aPróxima página", new String[] { null }, 1,
				(byte) 10));
		menu.setItem(5,
				SpawnItens.newItem(Material.getMaterial(351), "§3§lWarps", new String[] { "",
						"§7Clique aqui para ver a lista de", "§7warps do servidor", "" }, 1,
						(byte) 8));
		menu.setItem(6,
				SpawnItens.newItem(Material.getMaterial(351), "§c§lMenu desativado", new String[] { "",
						"§7Esta opçao esta desativada", "" }, 1,
						(byte) 8));
		menu.setItem(7,
				SpawnItens.newItem(Material.getMaterial(351), "§c§lMenu desativado", new String[] { "",
						"§7Esta opçao esta desativada", "" }, 1,
						(byte) 8));
		menu.setItem(4, SpawnItens.newItem(Material.getMaterial(351), "§e§lRandom Kit",
				new String[] { "", "§7Clique aqui para Selecionar um", "§7kit aleatório escolhido pelo sistema!", "" },
				1, (byte) 8));
		menu.setItem(2,
				SpawnItens.newItem(Material.getMaterial(351), "§a§lTodos os Kits",
						new String[] { "", "§7Clique aqui para ver a lista de", "§7todos os kits do servidor!", "" }, 1,
						(byte) 8));
		menu.setItem(3, SpawnItens.newItem(Material.getMaterial(351), "§b§lLoja de Kits",
				new String[] { "", "§7Clique aqui para comprar kits na", "§7loja online ou com moedas in-game!", "" },
				1, (byte) 8));
		menu.setItem(1, SpawnItens.newItem(Material.getMaterial(351), "§c§lSeus kits",
				new String[] { "", "§7Seus kits dispíveis", "§7para seu uso no servidor", "" }, 1,
				(byte) 10));

		menu.setItem(19, ItensKit.PvP());

		if (bp.hasPermission("kit.kangaroo")) {
			menu.setItem(20, ItensKit.Kangaroo());
		}

		if (bp.hasPermission("kit.gladiator")) {
			menu.setItem(21, ItensKit.Gladiator());
		}

		if (bp.hasPermission("kit.anchor")) {
			menu.setItem(22, ItensKit.Anchor());
		}

		if (bp.hasPermission("kit.ajnin")) {
			menu.setItem(23, ItensKit.Ajnin());
		}

		if (bp.hasPermission("kit.ninja")) {
			menu.setItem(24, ItensKit.Ninja());
		}

		if (bp.hasPermission("kit.minato")) {
			menu.setItem(25, ItensKit.Minato());
		}

		if (bp.hasPermission("kit.stomper")) {
			menu.setItem(28, ItensKit.Stomper());
		}

		if (bp.hasPermission("kit.antistomper")) {
			menu.setItem(29, ItensKit.AntiStomper());
		}

		if (bp.hasPermission("kit.fireman")) {
			menu.setItem(30, ItensKit.Fireman());
		}

		if (bp.hasPermission("kit.magma")) {
			menu.setItem(31, ItensKit.Magma());
		}

		if (bp.hasPermission("kit.hulk")) {
			menu.setItem(32, ItensKit.Hulk());
		}

		if (bp.hasPermission("kit.monk")) {
			menu.setItem(33, ItensKit.Monk());
		}

		if (bp.hasPermission("kit.switcher")) {
			menu.setItem(34, ItensKit.Switcher());
		}

		if (bp.hasPermission("kit.thor")) {
			menu.setItem(37, ItensKit.Thor());
		}

		if (bp.hasPermission("kit.timelord")) {
			menu.setItem(38, ItensKit.Timelord());
		}

		if (bp.hasPermission("kit.viking")) {
			menu.setItem(39, ItensKit.Viking());
		}
		
		if (bp.hasPermission("kit.*") || bp.isOp()) {
			menu.setItem(20, ItensKit.Kangaroo());
			menu.setItem(21, ItensKit.Gladiator());
			menu.setItem(22, ItensKit.Anchor());
			menu.setItem(23, ItensKit.Ajnin());
			menu.setItem(24, ItensKit.Ninja());
			menu.setItem(25, ItensKit.Minato());
			menu.setItem(28, ItensKit.Stomper());
			menu.setItem(29, ItensKit.AntiStomper());
			menu.setItem(30, ItensKit.Fireman());
			menu.setItem(31, ItensKit.Magma());
			menu.setItem(32, ItensKit.Hulk());
			menu.setItem(33, ItensKit.Monk());
			menu.setItem(34, ItensKit.Switcher());
			menu.setItem(37, ItensKit.Thor());
			menu.setItem(38, ItensKit.Timelord());
			menu.setItem(39, ItensKit.Viking());
		}
		bp.openInventory(menu);
	}

	@SuppressWarnings("deprecation")
	public static final void oPenAllKitsMenu(final Player bp) {

		Inventory menu = Bukkit.createInventory(bp, 54, "§8§nTodos os Kits");

		menu.setItem(5,
				SpawnItens.newItem(Material.getMaterial(351), "§3§lWarps", new String[] { "",
						"§7Clique aqui para ver a lista de", "§7warps do servidor", "" }, 1,
						(byte) 8));
		menu.setItem(6,
				SpawnItens.newItem(Material.getMaterial(351), "§c§lMenu desativado", new String[] { "",
						"§7Esta opçao esta desativada", "" }, 1,
						(byte) 8));
		//menu.setItem(5,
		//		SpawnItens.newItem(Material.getMaterial(351), "§c§lMenu desativado", new String[] { "",
		//				"§7Esta opçao esta desativada", "" }, 1,
		//				(byte) 8));
		menu.setItem(7,
				SpawnItens.newItem(Material.getMaterial(351), "§c§lMenu desativado", new String[] { "",
						"§7Esta opçao esta desativada", "" }, 1,
						(byte) 8));
		menu.setItem(4, SpawnItens.newItem(Material.getMaterial(351), "§e§lRandom Kit",
				new String[] { "", "§7Clique aqui para Selecionar um", "§7kit aleatório escolhido pelo sistema!", "" },
				1, (byte) 8));
		menu.setItem(2,
				SpawnItens.newItem(Material.getMaterial(351), "§a§lTodos os Kits",
						new String[] { "", "§7Clique aqui para ver a lista de", "§7todos os kits do servidor!", "" }, 1,
						(byte) 10));
		menu.setItem(3, SpawnItens.newItem(Material.getMaterial(351), "§b§lLoja de Kits",
				new String[] { "", "§7Clique aqui para comprar kits na", "§7loja online ou com moedas in-game!", "" },
				1, (byte) 8));
		menu.setItem(1, SpawnItens.newItem(Material.getMaterial(351), "§c§lSeus kits",
				new String[] { "", "§7Seus kits dispíveis", "§7para seu uso no servidor", "" }, 1,
				(byte) 8));

		menu.setItem(19, ItensKit.PvP());

		menu.setItem(20, ItensKit.Kangaroo());

		menu.setItem(21, ItensKit.Gladiator());

		menu.setItem(22, ItensKit.Anchor());

		menu.setItem(23, ItensKit.Ajnin());

		menu.setItem(24, ItensKit.Ninja());

		menu.setItem(25, ItensKit.Minato());

		menu.setItem(28, ItensKit.Stomper());

		menu.setItem(29, ItensKit.AntiStomper());

		menu.setItem(30, ItensKit.Fireman());

		menu.setItem(31, ItensKit.Magma());

		menu.setItem(32, ItensKit.Hulk());

		menu.setItem(33, ItensKit.Monk());

		menu.setItem(34, ItensKit.Switcher());

		menu.setItem(37, ItensKit.Thor());

		menu.setItem(38, ItensKit.Timelord());

		menu.setItem(39, ItensKit.Viking());

		bp.openInventory(menu);
	}

	public static final void runRandomKit(final Player bp) {
		if (randomKit.contains(bp.getName())) {
			bp.closeInventory();
			bp.sendMessage(
					"§e§lRANDOMKIT§f Você já usou seu §e§lrandom kit§f! Espere §c§l10 MINUTOS§f para usar novamente!");
			return;
		}
		randomKit.add(bp.getName());
		switch (new Random().nextInt(12)) {
		case 0:
			bp.closeInventory();
			Ninja.setNinjaKit(bp);
			break;
		case 1:
			bp.closeInventory();
			Anchor.setAnchorKit(bp);
			break;
		case 2:
			bp.closeInventory();
			Ajnin.setAjninKit(bp);
			break;
		case 3:
			bp.closeInventory();
			Ninja.setNinjaKit(bp);
			break;
		case 4:
			bp.closeInventory();
			PvP.setPvPKit(bp);
			break;
		case 5:
			bp.closeInventory();
			Minato.setMinatoKit(bp);
			;
			break;
		case 6:
			bp.closeInventory();
			Stomper.setStomperKit(bp);
			;
			break;
		case 7:
			bp.closeInventory();
			AntiStomper.setAntiStomperKit(bp);
			;
			break;
		case 8:
			bp.closeInventory();
			Fireman.setFiremanKit(bp);
			break;
		case 9:
			bp.closeInventory();
			Magma.setMagmaKit(bp);
			break;
		case 10:
			bp.closeInventory();
			Fireman.setFiremanKit(bp);
			break;
		case 11:
			bp.closeInventory();
			Monk.setMonkKit(bp);
			break;
		case 12:
			bp.closeInventory();
			Kangaroo.setKangarooKit(bp);
			break;
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (randomKit.contains(bp.getName())) {
					randomKit.remove(bp.getName());
				}
			}
		}, 12000L);
	}

	public static final ArrayList<String> randomKit = new ArrayList<>();

	@EventHandler
	public final void onInventoryKitsClick(final InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			final Player bp = (Player) e.getWhoClicked();
			if (e.getInventory().getName().equalsIgnoreCase("§8§nKits [1]") && e.getCurrentItem() != null) {
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lRandom Kit")) {
						e.setCancelled(true);
						runRandomKit(bp);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lLoja de Kits")) {
						e.setCancelled(true);
						bp.closeInventory();
						bp.sendMessage("§b§lLOJA DE KITS§f A loja de kits está sendo §c§lDESENVOLVIDA §fem breve estará disponível.");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lTodos os Kits")) {
						e.setCancelled(true);
						oPenAllKitsMenu(bp);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lWarps")) {
						e.setCancelled(true);
						MenusWarpSpawn.openWarpsMenuToBattlePlayer(bp);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
					bp.closeInventory();
					PvP.setPvPKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.ANVIL) {
					e.setCancelled(true);
					bp.closeInventory();
					Anchor.setAnchorKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
					e.setCancelled(true);
					bp.closeInventory();
					Ajnin.setAjninKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.SADDLE) {
					e.setCancelled(true);
					bp.closeInventory();
					Hulk.setHulkKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.FIREWORK) {
					e.setCancelled(true);
					bp.closeInventory();
					Kangaroo.setKangarooKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
					e.setCancelled(true);
					bp.closeInventory();
					AntiStomper.setAntiStomperKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
					e.setCancelled(true);
					bp.closeInventory();
					Stomper.setStomperKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.FIRE) {
					e.setCancelled(true);
					bp.closeInventory();
					Fireman.setFiremanKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
					e.setCancelled(true);
					bp.closeInventory();
					Magma.setMagmaKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
					e.setCancelled(true);
					bp.closeInventory();
					Fisherman.setFishermanKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
					e.setCancelled(true);
					bp.closeInventory();
					Monk.setMonkKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.ARROW) {
					e.setCancelled(true);
					bp.closeInventory();
					Minato.setMinatoKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
					e.setCancelled(true);
					bp.closeInventory();
					bp.sendMessage("§b§lKIT §fEste kit está desativado.");
	//				Gladiator.setGladiatorKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.FEATHER) {
					e.setCancelled(true);
					bp.closeInventory();
					Phantom.setPhantomKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.GOLD_AXE) {
					e.setCancelled(true);
					bp.closeInventory();
					Thor.setThorKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.WATCH) {
					e.setCancelled(true);
					bp.closeInventory();
					Timelord.setThorKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_AXE) {
					e.setCancelled(true);
					bp.closeInventory();
					Viking.setVikingKit(bp);
				}
				if (e.getCurrentItem().getType() == Material.EMERALD) {
					e.setCancelled(true);
					bp.closeInventory();
					Ninja.setNinjaKit(bp);
				} else {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public final void onInventoryAllKitsClick(final InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			final Player bp = (Player) e.getWhoClicked();
			if (e.getInventory().getName().equalsIgnoreCase("§8§nTodos os Kits") && e.getCurrentItem() != null) {
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lRandom Kit")) {
						e.setCancelled(true);
						runRandomKit(bp);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lLoja de Kits")) {
						e.setCancelled(true);
						bp.closeInventory();
						bp.sendMessage("§b§lLOJA DE KITS§f A loja de kits está sendo §c§lDESENVOLVIDA §fem breve estará disponível.");
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lWarps")) {
						e.setCancelled(true);
						MenusWarpSpawn.openWarpsMenuToBattlePlayer(bp);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSeus kits")) {
						e.setCancelled(true);
						oPenKitsMenu(bp);
					}
				} catch (NullPointerException ex) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.ANVIL) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.SADDLE) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.FIREWORK) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.FIRE) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.ARROW) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.IRON_FENCE) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.FEATHER) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.GOLD_AXE) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.WATCH) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_AXE) {
					e.setCancelled(true);
				}
				if (e.getCurrentItem().getType() == Material.EMERALD) {
					e.setCancelled(true);
				} else {
					e.setCancelled(true);
				}
			}
		}
	}
}
