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
		Inventory inv = Bukkit.createInventory(p, 54, "Kits secundários [1]");

		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) 10);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§8§nKits primários [2]");
		a.setItemMeta(am);

		ItemStack c = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("§8§nLoja de kits");
		c.setItemMeta(cm);

		ItemStack b = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("§8§nKits primários");
		b.setItemMeta(bm);

		ItemStack e = new ItemStack(Material.getMaterial(351), 1, (short) 8);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName("§cPágina anterior");
		e.setItemMeta(em);

		ItemStack d = new ItemStack(Material.getMaterial(351), 1, (short) 10);
		ItemMeta dm = d.getItemMeta();
		dm.setDisplayName("§aPróxima página");
		d.setItemMeta(dm);

		inv.setItem(3, b);
		inv.setItem(4, a);
		inv.setItem(5, c);
		inv.setItem(27, e);
		inv.setItem(35, d);
		inv.setItem(19, item(p, Permission.getInstance().KIT2 + "achilles", Material.WOOD_SWORD, "§bAchilles", Arrays.asList("§fImune a tudo menos madeira")));
		inv.setItem(20, item(p, Permission.getInstance().KIT2 + "anchor", Material.ANVIL, "§bAnchor", Arrays.asList( "§fNão leve knockback")));
		inv.setItem(21, item(p, Permission.getInstance().KIT2 + "antitower", Material.GOLD_HELMET, "§bAntitower", Arrays.asList("§fAnti-stomper")));
		inv.setItem(22, item(p, Permission.getInstance().KIT2 + "archer", Material.BOW, "§bArcher", Arrays.asList("§fSeja um camper poderosissimo")));
		inv.setItem(23, item(p, Permission.getInstance().KIT2 + "blink", Material.NETHER_STAR, "§bBlink", Arrays.asList("§fTeleporte e crie um bloco de folha")));
		inv.setItem(24, item(p, Permission.getInstance().KIT2 + "boxer", Material.STONE_SWORD, "§bBoxer", Arrays.asList("§fTome menos 1 coração e dê mais um coração")));
		inv.setItem(25, item(p, Permission.getInstance().KIT2 + "copycat", Material.NAME_TAG, "§bCopycat", Arrays.asList("§fAo matar jogadores copie o kit dos mesmos")));
		inv.setItem(28, item(p, Permission.getInstance().KIT2 + "cultivator", Material.SEEDS, "§bCultivator", Arrays.asList("§fSeja um agricultor")));
		//inv.setItem(29, item(p, Permission.getInstance().KIT + "deshfire", Material.REDSTONE_BLOCK, "§bDeshfire", Arrays.asList("§fDomine o fogo e seja rapido")));
		inv.setItem(30, item(p, Permission.getInstance().KIT2 + "demoman", Material.GRAVEL, "§bDemoman", Arrays.asList("§fPlaca de pressão mais gravel é igual à explosão")));
		inv.setItem(31, item(p, Permission.getInstance().KIT2 + "endermage", Material.PORTAL, "§bEndermage", Arrays.asList("§fPuxe os players")));
		inv.setItem(32, item(p, Permission.getInstance().KIT2 + "fireman", Material.LAVA_BUCKET, "§bFireman", Arrays.asList("§fSeja imune a lava e fogo")));
		inv.setItem(33, item(p, Permission.getInstance().KIT2 + "fisherman", Material.FISHING_ROD, "§bFisherman", Arrays.asList("§fPuxe seus inimigos com a vara")));
		inv.setItem(34, item(p, Permission.getInstance().KIT2 + "flash", Material.REDSTONE_TORCH_ON, "§bFlash", Arrays.asList( "§fSeja muito rapido")));
		inv.setItem(37, item(p, Permission.getInstance().KIT2 + "forger", Material.COAL, "§bForger", Arrays.asList("§fAsse ferros com um click")));
		inv.setItem(38, item(p, Permission.getInstance().KIT2 + "gladiator", Material.IRON_FENCE, "§bGladiator", Arrays.asList("§fChame um jogador por x1")));
		inv.setItem(39, item(p, Permission.getInstance().KIT2 + "grappler", Material.LEASH, "§bGrappler", Arrays.asList("§fUse seu laço para ir ate lugares")));
		inv.setItem(40, item(p, Permission.getInstance().KIT2 + "grandpa", Material.STICK, "§bGrandpa", Arrays.asList("§fUse seu laço para ir ate lugares")));
		inv.setItem(41, item(p, Permission.getInstance().KIT2 + "launcher", Material.SPONGE, "§bLauncher", Arrays.asList("§fTenha uma esponja voadora")));
		inv.setItem(42, item(p, Permission.getInstance().KIT2 + "lumberjack", Material.WOOD, "§bLumberjack", Arrays.asList( "§fQuebre madeira rapido")));
		inv.setItem(43, item(p, Permission.getInstance().KIT2 + "madman", Material.FERMENTED_SPIDER_EYE, "§bMadman", Arrays.asList( "§fDeixe os players fracos")));
		inv.setItem(29, item(p, Permission.getInstance().KIT2 + "tank", Material.TNT, "§bTank", Arrays.asList("§fNão leve dano de explosão")));
		//inv.setItem(1, item(p, Permission.getInstance().KIT2 + "thor", Material.WOOD_AXE, "§bThor", Arrays.asList("§fSolte raios com o machado")));

		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public void kitSelector2p2(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, "Kits secundários [2]");

		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) 10);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§8§nKits ssecundários [2]");
		a.setItemMeta(am);

		ItemStack c = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("§8§nLoja de kits");
		c.setItemMeta(cm);

		ItemStack b = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("§8§nKits primários");
		b.setItemMeta(bm);

		ItemStack e = new ItemStack(Material.getMaterial(351), 1, (short) 8);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName("§cPágina anterior");
		e.setItemMeta(em);

		ItemStack d = new ItemStack(Material.getMaterial(351), 1, (short) 10);
		ItemMeta dm = d.getItemMeta();
		dm.setDisplayName("§aPróxima página");
		d.setItemMeta(dm);

		inv.setItem(3, b);
		inv.setItem(4, a);
		inv.setItem(5, c);
		inv.setItem(27, e);
		inv.setItem(35, d);
		inv.setItem(19, item(p, Permission.getInstance().KIT2 + "timelord", Material.WATCH, "§bTimelord", Arrays.asList("§fCongele os players ao redor")));
		inv.setItem(20, item(p, Permission.getInstance().KIT2 + "hulk", Material.SADDLE, "§bHulk", Arrays.asList("§fPegue players no colo")));
		inv.setItem(21, item(p, Permission.getInstance().KIT2 + "kangaroo", Material.FIREWORK, "§bKangaroo", Arrays.asList("§fTransforme-se em um canguru")));
		inv.setItem(22, item(p, Permission.getInstance().KIT2 + "urgal", Material.getMaterial(373), "§bUrgal", Arrays.asList("§fTenha 3 poções de força"), 8201, 3));
		inv.setItem(23, item(p, Permission.getInstance().KIT2 + "vacuum", Material.EYE_OF_ENDER, "§bVacuum", Arrays.asList("§fPuxe os players até você")));
		inv.setItem(24, item(p, Permission.getInstance().KIT2 + "viking", Material.IRON_AXE, "§bViking", Arrays.asList("§fCausa mais dano com machados")));
		inv.setItem(25, item(p, Permission.getInstance().KIT2 + "viper", Material.SPIDER_EYE, "§bViper", Arrays.asList("§fDeixe os players com veneno")));
		//inv.setItem(28, item(p, Permission.getInstance().KIT + "worm", Material.DIRT, "§bWorm", Arrays.asList("§fComa terra")));
		inv.setItem(29, item(p, Permission.getInstance().KIT2 + "ajnin", Material.NAME_TAG, "§bAjnin", Arrays.asList("§fAperte shift e puxe jogadores")));
		inv.setItem(30, item(p, Permission.getInstance().KIT2 + "weakhand", Material.getMaterial(373), "§bWeakhand", Arrays.asList("§fDe fraqueza e tontura nos jogadores"), 8200, 1));
		inv.setItem(31, item(p, Permission.getInstance().KIT2 + "magma", Material.FIRE, "§bMagma", Arrays.asList( "§fAo tomar um hit tenha chance de deixa-lo queimando")));
		inv.setItem(32, item(p, Permission.getInstance().KIT2 + "miner", Material.STONE_PICKAXE, "§bMiner", Arrays.asList("§fMinere mais rápido que o normal")));
		inv.setItem(33, item(p, Permission.getInstance().KIT2+ "monk", Material.BLAZE_ROD, "§bMonk", Arrays.asList("§fEmbaralhe o inventario do player")));
		inv.setItem(34, item(p, Permission.getInstance().KIT2 + "ninja", Material.EMERALD, "§bNinja", Arrays.asList("§fUma dadiva dos ninjas!")));
		inv.setItem(37, item(p, Permission.getInstance().KIT2 + "reaper", Material.WOOD_HOE, "§bReaper", Arrays.asList("§fDeixei os players em decomposição")));
		inv.setItem(38, item(p, Permission.getInstance().KIT2 + "stomper", Material.IRON_BOOTS, "§bStomper", Arrays.asList("§fEsmague seus inimigos")));
		inv.setItem(39, item(p, Permission.getInstance().KIT2 + "phantom", Material.FEATHER, "§bPhantom", Arrays.asList("§fVoe como um pássaro")));
		inv.setItem(40, item(p, Permission.getInstance().KIT2 + "poseidon", Material.WATER_BUCKET, "§bPoseidon", Arrays.asList("§fGanhe força e speed na água")));
		inv.setItem(41, item(p, Permission.getInstance().KIT2 + "scout", Material.getMaterial(373), "§bScout", Arrays.asList("§fTenha 3 poções de velocidade"), 8194, 2));
		//inv.setItem(42, item(p, Permission.getInstance().KIT + "sonic", Material.LAPIS_BLOCK, "§bSonic", Arrays.asList("§fVire o sonic e seja rapido")));
		inv.setItem(43, item(p, Permission.getInstance().KIT2 + "snail", Material.FERMENTED_SPIDER_EYE, "§bSnail", Arrays.asList("§fDeixe os players lentos")));
		inv.setItem(28, item(p, Permission.getInstance().KIT2 + "specialist", Material.WRITTEN_BOOK, "§bSpecialist", Arrays.asList("§fEncante seus itens")));
		inv.setItem(42, item(p, Permission.getInstance().KIT2 + "thor", Material.WOOD_AXE, "§bThor", Arrays.asList("§fSolte raios com o machado")));
		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	protected void onClickInventory(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		if (i != null && e.getInventory().getTitle().equalsIgnoreCase("Kits secundários [1]") && i.getTypeId() != 0) {
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
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPróxima página")) { 
					e.setCancelled(true);
					kitSelector2p2(p);
				}
			} catch (NullPointerException ex) {
			}

			try {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8§nKits primários")) {
					e.setCancelled(true);
					InventoryKit.getInstance().kitSelector1(p);
				}
			} catch (NullPointerException ex) {
			}
		}



		if (i != null && e.getInventory().getTitle().equalsIgnoreCase("Kits secundários [2]") && i.getTypeId() != 0) {
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
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPágina anterior")) { 
						e.setCancelled(true);
						kitSelector2(p);
					}
				} catch (NullPointerException ex) {
				}

				try {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8§nKits primários")) {
						e.setCancelled(true);
						InventoryKit.getInstance().kitSelector1(p);
					}
				} catch (NullPointerException ex) {
				}
			}
		}
	}

	private boolean handle(Player player, ItemStack item, String kit) {
		if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§b" + kit)) {
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
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§aKit 2 §7(Clique)")) {
				kitSelector2(p);
				return;
			}
		}
	}
}

