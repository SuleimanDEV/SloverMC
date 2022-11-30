package br.com.slovermc.hg.inventarios;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.slovermc.hg.*;
import br.com.slovermc.hg.utils.*;

public class InventoryKit2 implements Listener {

	private static InventoryKit2 instance = new InventoryKit2();

	public static InventoryKit2 getInstance() {
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void kitSelector2(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, "Kits secund�rios [1]");

		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) 10);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("�8�nKits prim�rios [2]");
		a.setItemMeta(am);

		ItemStack c = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("�8�nLoja de kits");
		c.setItemMeta(cm);

		ItemStack b = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("�8�nKits prim�rios");
		b.setItemMeta(bm);

		ItemStack e = new ItemStack(Material.getMaterial(351), 1, (short) 8);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName("�cP�gina anterior");
		e.setItemMeta(em);

		ItemStack d = new ItemStack(Material.getMaterial(351), 1, (short) 10);
		ItemMeta dm = d.getItemMeta();
		dm.setDisplayName("�aPr�xima p�gina");
		d.setItemMeta(dm);

		inv.setItem(3, b);
		inv.setItem(4, a);
		inv.setItem(5, c);
		inv.setItem(27, e);
		inv.setItem(35, d);
		inv.setItem(19, item(p, Permission.getInstance().KIT2 + "achilles", Material.WOOD_SWORD, "�aAchilles", Arrays.asList("�7Imune a tudo menos madeira")));
		inv.setItem(20, item(p, Permission.getInstance().KIT2 + "anchor", Material.ANVIL, "�aAnchor", Arrays.asList( "�7N�o leve knockback")));
		inv.setItem(21, item(p, Permission.getInstance().KIT2 + "antitower", Material.GOLD_HELMET, "�aAntitower", Arrays.asList("�7Anti-stomper")));
		inv.setItem(22, item(p, Permission.getInstance().KIT2 + "archer", Material.BOW, "�aArcher", Arrays.asList("�7Seja um camper poderosissimo")));
		inv.setItem(23, item(p, Permission.getInstance().KIT2 + "blink", Material.NETHER_STAR, "�aBlink", Arrays.asList("�7Teleporte e crie um bloco de folha")));
		inv.setItem(24, item(p, Permission.getInstance().KIT2 + "boxer", Material.STONE_SWORD, "�aBoxer", Arrays.asList("�7Tome menos 1 cora��o e d� mais um cora��o")));
		inv.setItem(25, item(p, Permission.getInstance().KIT2 + "copycat", Material.NAME_TAG, "�aCopycat", Arrays.asList("�7Ao matar jogadores copie o kit dos mesmos")));
		inv.setItem(28, item(p, Permission.getInstance().KIT2 + "cultivator", Material.SEEDS, "�aCultivator", Arrays.asList("�7Seja um agricultor")));
		//inv.setItem(29, item(p, Permission.getInstance().KIT + "deshfire", Material.REDSTONE_BLOCK, "�aDeshfire", Arrays.asList("�7Domine o fogo e seja rapido")));
		inv.setItem(30, item(p, Permission.getInstance().KIT2 + "demoman", Material.GRAVEL, "�aDemoman", Arrays.asList("�7Placa de press�o mais gravel � igual � explos�o")));
		inv.setItem(31, item(p, Permission.getInstance().KIT2 + "endermage", Material.PORTAL, "�aEndermage", Arrays.asList("�7Puxe os players")));
		inv.setItem(32, item(p, Permission.getInstance().KIT2 + "fireman", Material.LAVA_BUCKET, "�aFireman", Arrays.asList("�7Seja imune a lava e fogo")));
		inv.setItem(33, item(p, Permission.getInstance().KIT2 + "fisherman", Material.FISHING_ROD, "�aFisherman", Arrays.asList("�7Puxe seus inimigos com a vara")));
		inv.setItem(34, item(p, Permission.getInstance().KIT2 + "flash", Material.REDSTONE_TORCH_ON, "�aFlash", Arrays.asList( "�7Seja muito rapido")));
		inv.setItem(37, item(p, Permission.getInstance().KIT2 + "forger", Material.COAL, "�aForger", Arrays.asList("�7Asse ferros com um click")));
		inv.setItem(38, item(p, Permission.getInstance().KIT2 + "gladiator", Material.IRON_FENCE, "�aGladiator", Arrays.asList("�7Chame um jogador por x1")));
		inv.setItem(39, item(p, Permission.getInstance().KIT2 + "grappler", Material.LEASH, "�aGrappler", Arrays.asList("�7Use seu la�o para ir ate lugares")));
		inv.setItem(40, item(p, Permission.getInstance().KIT2 + "grandpa", Material.STICK, "�aGrandpa", Arrays.asList("�7Use seu la�o para ir ate lugares")));
		inv.setItem(41, item(p, Permission.getInstance().KIT2 + "launcher", Material.SPONGE, "�aLauncher", Arrays.asList("�7Tenha uma esponja voadora")));
		inv.setItem(42, item(p, Permission.getInstance().KIT2 + "lumberjack", Material.WOOD, "�aLumberjack", Arrays.asList( "�7Quebre madeira rapido")));
		inv.setItem(43, item(p, Permission.getInstance().KIT2 + "madman", Material.FERMENTED_SPIDER_EYE, "�aMadman", Arrays.asList( "�7Deixe os players fracos")));
		inv.setItem(29, item(p, Permission.getInstance().KIT2 + "tank", Material.TNT, "�aTank", Arrays.asList("�7N�o leve dano de explos�o")));
		//inv.setItem(1, item(p, Permission.getInstance().KIT2 + "thor", Material.WOOD_AXE, "�aThor", Arrays.asList("�7Solte raios com o machado")));

		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public void kitSelector2p2(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, "Kits secund�rios [2]");

		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) 10);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("�8�nKits ssecund�rios [2]");
		a.setItemMeta(am);

		ItemStack c = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("�8�nLoja de kits");
		c.setItemMeta(cm);

		ItemStack b = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("�8�nKits prim�rios");
		b.setItemMeta(bm);

		ItemStack e = new ItemStack(Material.getMaterial(351), 1, (short) 8);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName("�cP�gina anterior");
		e.setItemMeta(em);

		ItemStack d = new ItemStack(Material.getMaterial(351), 1, (short) 10);
		ItemMeta dm = d.getItemMeta();
		dm.setDisplayName("�aPr�xima p�gina");
		d.setItemMeta(dm);

		inv.setItem(3, b);
		inv.setItem(4, a);
		inv.setItem(5, c);
		inv.setItem(27, e);
		inv.setItem(35, d);
		inv.setItem(19, item(p, Permission.getInstance().KIT2 + "timelord", Material.WATCH, "�aTimelord", Arrays.asList("�7Congele os players ao redor")));
		inv.setItem(20, item(p, Permission.getInstance().KIT2 + "hulk", Material.SADDLE, "�aHulk", Arrays.asList("�7Pegue players no colo")));
		inv.setItem(21, item(p, Permission.getInstance().KIT2 + "kangaroo", Material.FIREWORK, "�aKangaroo", Arrays.asList("�7Transforme-se em um canguru")));
		inv.setItem(22, item(p, Permission.getInstance().KIT2 + "urgal", Material.getMaterial(373), "�aUrgal", Arrays.asList("�7Tenha 3 po��es de for�a"), 8201, 3));
		inv.setItem(23, item(p, Permission.getInstance().KIT2 + "vacuum", Material.EYE_OF_ENDER, "�aVacuum", Arrays.asList("�7Puxe os players at� voc�")));
		inv.setItem(24, item(p, Permission.getInstance().KIT2 + "viking", Material.IRON_AXE, "�aViking", Arrays.asList("�7Causa mais dano com machados")));
		inv.setItem(25, item(p, Permission.getInstance().KIT2 + "viper", Material.SPIDER_EYE, "�aViper", Arrays.asList("�7Deixe os players com veneno")));
		//inv.setItem(28, item(p, Permission.getInstance().KIT + "worm", Material.DIRT, "�aWorm", Arrays.asList("�7Coma terra")));
		inv.setItem(29, item(p, Permission.getInstance().KIT2 + "ajnin", Material.NAME_TAG, "�aAjnin", Arrays.asList("�7Aperte shift e puxe jogadores")));
		inv.setItem(30, item(p, Permission.getInstance().KIT2 + "weakhand", Material.getMaterial(373), "�aWeakhand", Arrays.asList("�7De fraqueza e tontura nos jogadores"), 8200, 1));
		inv.setItem(31, item(p, Permission.getInstance().KIT2 + "magma", Material.FIRE, "�aMagma", Arrays.asList( "�7Ao tomar um hit tenha chance de deixa-lo queimando")));
		inv.setItem(32, item(p, Permission.getInstance().KIT2 + "miner", Material.STONE_PICKAXE, "�aMiner", Arrays.asList("�7Minere mais r�pido que o normal")));
		inv.setItem(33, item(p, Permission.getInstance().KIT2+ "monk", Material.BLAZE_ROD, "�aMonk", Arrays.asList("�7Embaralhe o inventario do player")));
		inv.setItem(34, item(p, Permission.getInstance().KIT2 + "ninja", Material.EMERALD, "�aNinja", Arrays.asList("�7Uma dadiva dos ninjas!")));
		inv.setItem(37, item(p, Permission.getInstance().KIT2 + "reaper", Material.WOOD_HOE, "�aReaper", Arrays.asList("�7Deixei os players em decomposi��o")));
		inv.setItem(38, item(p, Permission.getInstance().KIT2 + "stomper", Material.IRON_BOOTS, "�aStomper", Arrays.asList("�7Esmague seus inimigos")));
		inv.setItem(39, item(p, Permission.getInstance().KIT2 + "phantom", Material.FEATHER, "�aPhantom", Arrays.asList("�7Voe como um p�ssaro")));
		inv.setItem(40, item(p, Permission.getInstance().KIT2 + "poseidon", Material.WATER_BUCKET, "�aPoseidon", Arrays.asList("�7Ganhe for�a e speed na �gua")));
		inv.setItem(41, item(p, Permission.getInstance().KIT2 + "scout", Material.getMaterial(373), "�aScout", Arrays.asList("�7Tenha 3 po��es de velocidade"), 8194, 2));
		//inv.setItem(42, item(p, Permission.getInstance().KIT + "sonic", Material.LAPIS_BLOCK, "�aSonic", Arrays.asList("�7Vire o sonic e seja rapido")));
		inv.setItem(43, item(p, Permission.getInstance().KIT2 + "snail", Material.FERMENTED_SPIDER_EYE, "�aSnail", Arrays.asList("�7Deixe os players lentos")));
		inv.setItem(28, item(p, Permission.getInstance().KIT2 + "specialist", Material.WRITTEN_BOOK, "�aSpecialist", Arrays.asList("�7Encante seus itens")));
		inv.setItem(42, item(p, Permission.getInstance().KIT2 + "thor", Material.WOOD_AXE, "�aThor", Arrays.asList("�7Solte raios com o machado")));
		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	protected void onClickInventory(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		if (i != null && e.getInventory().getTitle().equalsIgnoreCase("Kits secund�rios [1]") && i.getTypeId() != 0) {
			e.setCancelled(true);
			if (handle(p, i, "Achilles")) {
			} else if (handle(p, i, "Anchor")) {
			} else if (handle(p, i, "Fireman")) {
			} else if (handle(p, i, "Fisherman")) {
			} else if (handle(p, i, "Flash")) {
			} else if (handle(p, i, "Blink")) {
			} else if (handle(p, i, "Launcher")) {
			} else if (handle(p, i, "Grappler")) {
			} else if (handle(p, i, "Lumberjack")) {
			} else if (handle(p, i, "Endermage")) {
			} else if (handle(p, i, "Madman")) {
			} else if (handle(p, i, "Ninja")) {
			} else if (handle(p, i, "Phantom")) {
			} else if (handle(p, i, "Stomper")) {
			} else if (handle(p, i, "Deshfire")) {
			} else if (handle(p, i, "Sonic")) {
			} else if (handle(p, i, "Gladiator")) {
			} else if (handle(p, i, "Specialist")) {
			} else if (handle(p, i, "Snail")) {
			} else if (handle(p, i, "Poseidon")) {
			} else if (handle(p, i, "Tank")) {
			} else if (handle(p, i, "Thor")) {
			} else if (handle(p, i, "Monk")) {
			} else if (handle(p, i, "Antitower")) {
			} else if (handle(p, i, "Boxer")) {
			} else if (handle(p, i, "Archer")) {
			} else if (handle(p, i, "Miner")) {
			} else if (handle(p, i, "Magma")) {
			} else if (handle(p, i, "Copycat")) {
			} else if (handle(p, i, "Forger")) {
			} else if (handle(p, i, "Cultivator")) {
			} else if (handle(p, i, "Reaper")) {
			} else if (handle(p, i, "Demoman")) {
			} else if (handle(p, i, "Jackhammer")) {
			} else if (handle(p, i, "Scout")) {
			} else if (handle(p, i, "Grandpa")) {
			} else if (handle(p, i, "Surprise")) {
			}

			try {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aPr�xima p�gina")) { 
					e.setCancelled(true);
					kitSelector2p2(p);
				}
			} catch (NullPointerException ex) {
			}

			try {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�8�nKits prim�rios")) {
					e.setCancelled(true);
					InventoryKit.getInstance().kitSelector1(p);
				}
			} catch (NullPointerException ex) {
			}
		}



		if (i != null && e.getInventory().getTitle().equalsIgnoreCase("Kits secund�rios [2]") && i.getTypeId() != 0) {
			e.setCancelled(true);
			if (handle(p, i, "Timelord")) {
			} else if (handle(p, i, "Hulk")) {
			} else if (handle(p, i, "Jackhammer")) {
			} else if (handle(p, i, "Kangaroo")) {
			} else if (handle(p, i, "Urgal")) {
			} else if (handle(p, i, "Vacuum")) {
			} else if (handle(p, i, "Viking")) {
			} else if (handle(p, i, "Viper")) {
			} else if (handle(p, i, "Worm")) {
			} else if (handle(p, i, "Weakhand")) {
			} else if (handle(p, i, "Ajnin")) {
			} else if (handle(p, i, "Anchor")) {
			} else if (handle(p, i, "Fireman")) {
			} else if (handle(p, i, "Fisherman")) {
			} else if (handle(p, i, "Flash")) {
			} else if (handle(p, i, "Blink")) {
			} else if (handle(p, i, "Launcher")) {
			} else if (handle(p, i, "Grappler")) {
			} else if (handle(p, i, "Lumberjack")) {
			} else if (handle(p, i, "Endermage")) {
			} else if (handle(p, i, "Madman")) {
			} else if (handle(p, i, "Ninja")) {
			} else if (handle(p, i, "Phantom")) {
			} else if (handle(p, i, "Stomper")) {
			} else if (handle(p, i, "Deshfire")) {
			} else if (handle(p, i, "Sonic")) {
			} else if (handle(p, i, "Gladiator")) {
			} else if (handle(p, i, "Specialist")) {
			} else if (handle(p, i, "Snail")) {
			} else if (handle(p, i, "Poseidon")) {
			} else if (handle(p, i, "Tank")) {
			} else if (handle(p, i, "Thor")) {
			} else if (handle(p, i, "Monk")) {
			} else if (handle(p, i, "Antitower")) {
			} else if (handle(p, i, "Boxer")) {
			} else if (handle(p, i, "Archer")) {
			} else if (handle(p, i, "Miner")) {
			} else if (handle(p, i, "Magma")) {
			} else if (handle(p, i, "Copycat")) {
			} else if (handle(p, i, "Forger")) {
			} else if (handle(p, i, "Cultivator")) {
			} else if (handle(p, i, "Reaper")) {
			} else if (handle(p, i, "Demoman")) {
			} else if (handle(p, i, "Jackhammer")) {
			} else if (handle(p, i, "Scout")) {
			} else if (handle(p, i, "Grandpa")) {
			} else if (handle(p, i, "Surprise")) {

				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�cP�gina anterior")) { 
						e.setCancelled(true);
						kitSelector2(p);
					}
				} catch (NullPointerException ex) {
				}

				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�8�nKits prim�rios")) {
						e.setCancelled(true);
						InventoryKit.getInstance().kitSelector1(p);
					}
				} catch (NullPointerException ex) {
				}
			}
		}
	}

	private boolean handle(Player player, ItemStack item, String kit) {
		if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("�a" + kit)) {
			player.performCommand("kit2 " + kit);
			player.closeInventory();
			return true;
		}
		return false;
	}

	public ItemStack item(Player p, String permissao, Material m, String display, List<String> lore) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(display);
		meta.setLore(lore);
		item.setItemMeta(meta);
		if (p.hasPermission(permissao)) {
			return item;
		} else if (permissao == "") {
			return item;
		}
		return new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)14);
	}

	public ItemStack item(Player p, String permissao, Material m, String display, List<String> lore, int id, int amount) {
		ItemStack item = new ItemStack(m, amount, (byte) id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(display);
		meta.setLore(lore);
		item.setItemMeta(meta);
		if (p.hasPermission(permissao)) {
			return item;
		} else if (permissao == "") {
			return item;
		}
		return new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)14);
	}

	public ItemStack item(Player p, String permissao, Material m, String display, List<String> lore, Enchantment enchant, int level) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchant, level, true);
		meta.setDisplayName(display);
		meta.setLore(lore);
		item.setItemMeta(meta);
		if (p.hasPermission(permissao)) {
			return item;
		} else if (permissao == "") {
			return item;
		}
		return new ItemStack(Material.STAINED_GLASS_PANE, 1,(short)14);
	}

	@EventHandler
	protected void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if (BukkitMain.state == StateEnum.STARTING) {
			e.setCancelled(true);
			if (!item.hasItemMeta() || item == null || e.getAction() == Action.PHYSICAL)
				return;
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("�aKit 2 �7(Clique)")) {
				kitSelector2(p);
				return;
			}
		}
	}
}
