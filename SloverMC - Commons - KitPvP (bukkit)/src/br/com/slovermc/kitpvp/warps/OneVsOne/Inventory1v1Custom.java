package br.com.slovermc.kitpvp.warps.OneVsOne;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.utils.BattleStrings;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class Inventory1v1Custom implements Listener {

	public static final HashMap<Player, String> playername = new HashMap<>();
	public static final HashMap<Player, String> armaduras = new HashMap<>();
	public static final HashMap<Player, Material> espada = new HashMap<>();
	public static final HashMap<Player, String> recrafttype = new HashMap<>();
	public static final HashMap<Player, Boolean> recraft = new HashMap<>();
	public static final HashMap<Player, Boolean> sharpness = new HashMap<>();
	public static final HashMap<Player, Boolean> fullsoup = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public static final void loadItensCustom(final Player bp1, final Player bp2) {
		bp1.getInventory().clear();
		bp2.getInventory().clear();
		bp1.getInventory().setArmorContents(null);
		bp2.getInventory().setArmorContents(null);
		if (armaduras.containsKey(bp1) && armaduras.get(bp1) != "SEM") {
			if (armaduras.get(bp1) == "LEATHER") {
				bp1.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				bp1.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				bp1.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				bp1.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
				
				bp2.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				bp2.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				bp2.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
				bp2.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			} else if (armaduras.get(bp1) == "IRON") {
				bp1.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
				bp1.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				bp1.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				bp1.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
				
				bp2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
				bp2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				bp2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				bp2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			} else if (armaduras.get(bp1) == "DIAMOND") {
				bp1.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
				bp1.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				bp1.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				bp1.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
				
				bp2.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
				bp2.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				bp2.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				bp2.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			}
		}
		if (fullsoup.containsKey(bp1)) {
			if (fullsoup.get(bp1)) {
				for (int i = 1; i < 36; i++) {
					bp1.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
					bp2.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
				}
			} else {
				for (int i = 1; i < 9; i++) {
					bp1.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
					bp2.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
				}
			}
		}
		if (espada.containsKey(bp1)) {
			if (espada.get(bp1) == Material.WOOD_SWORD) {
				if (sharpness.get(bp1)) {
					bp1.getInventory().setItem(0, espadaMadeira(true));
					bp2.getInventory().setItem(0, espadaMadeira(true));
				} else {
					bp1.getInventory().setItem(0, espadaMadeira(false));
					bp2.getInventory().setItem(0, espadaMadeira(false));
				}
			}
			if (espada.get(bp1) == Material.STONE_SWORD) {
				if (sharpness.get(bp1)) {
					bp1.getInventory().setItem(0, espadaPedra(true));
					bp2.getInventory().setItem(0, espadaPedra(true));
				} else {
					bp1.getInventory().setItem(0, espadaPedra(false));
					bp2.getInventory().setItem(0, espadaPedra(false));
				}
			}
			if (espada.get(bp1) == Material.IRON_SWORD) {
				if (sharpness.get(bp1)) {
					bp1.getInventory().setItem(0, espadaFerro(true));
					bp2.getInventory().setItem(0, espadaFerro(true));
				} else {
					bp1.getInventory().setItem(0, espadaFerro(false));
					bp2.getInventory().setItem(0, espadaFerro(false));
				}
			}
			if (espada.get(bp1) == Material.DIAMOND_SWORD) {
				if (sharpness.get(bp1)) {
					bp1.getInventory().setItem(0, espadaDiamante(true));
					bp2.getInventory().setItem(0, espadaDiamante(true));
				} else {
					bp1.getInventory().setItem(0, espadaDiamante(false));
					bp2.getInventory().setItem(0, espadaDiamante(false));
				}
			}
		}
		if (recraft.containsKey(bp1)) {
			if (recraft.get(bp1)) {
				if (recrafttype.get(bp1) == "COGUMELO") {
					bp1.getInventory().setItem(13, new ItemStack(Material.BOWL, 64, (byte) 0));
					bp1.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 64, (byte) 0));
					bp1.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64, (byte) 0));
					
					bp2.getInventory().setItem(13, new ItemStack(Material.BOWL, 64, (byte) 0));
					bp2.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 64, (byte) 0));
					bp2.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64, (byte) 0));
				} else if (recrafttype.get(bp1) == "COCOABEAN") {
					
					bp1.getInventory().setItem(13, new ItemStack(Material.BOWL, 64, (byte) 0));
					bp1.getInventory().setItem(14, new ItemStack(Material.getMaterial(351), 64, (byte) 3));
					bp1.getInventory().setItem(15, new ItemStack(Material.getMaterial(351), 64, (byte) 3));
					
					bp2.getInventory().setItem(13, new ItemStack(Material.BOWL, 64, (byte) 0));
					bp2.getInventory().setItem(14, new ItemStack(Material.getMaterial(351), 64, (byte) 3));
					bp2.getInventory().setItem(15, new ItemStack(Material.getMaterial(351), 64, (byte) 3));
				}
			}
		}
		bp1.updateInventory();
		bp2.updateInventory();
	}

	public static final void removeDefaultCustoms(final Player bp) {
		if (playername.containsKey(bp)) {
			playername.remove(bp);
		}
		if (armaduras.containsKey(bp)) {
			armaduras.remove(bp);
		}
		if (espada.containsKey(bp)) {
			espada.remove(bp);
		}
		if (recrafttype.containsKey(bp)) {
			recrafttype.remove(bp);
		}
		if (sharpness.containsKey(bp)) {
			sharpness.remove(bp);
		}
		if (fullsoup.containsKey(bp)) {
			fullsoup.remove(bp);
		}
	}
	
	public static final ItemStack espadaDiamante(final boolean enchanted) {
		final ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
		final ItemMeta ik = i.getItemMeta();
		if (enchanted) {
			ik.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		}
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack espadaFerro(final boolean enchanted) {
		final ItemStack i = new ItemStack(Material.IRON_SWORD);
		final ItemMeta ik = i.getItemMeta();
		if (enchanted) {
			ik.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		}
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack espadaPedra(final boolean enchanted) {
		final ItemStack i = new ItemStack(Material.STONE_SWORD);
		final ItemMeta ik = i.getItemMeta();
		if (enchanted) {
			ik.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		}
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack espadaMadeira(final boolean enchanted) {
		final ItemStack i = new ItemStack(Material.WOOD_SWORD);
		final ItemMeta ik = i.getItemMeta();
		if (enchanted) {
			ik.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		}
		i.setItemMeta(ik);
		return i;
	}

	public static final void setDefaultCustoms(final Player bp, final String playerName) {
		removeDefaultCustoms(bp);
		playername.put(bp, playerName);
		armaduras.put(bp, "LEATHER");
		espada.put(bp, Material.WOOD_SWORD);
		recrafttype.put(bp, "COGUMELO");
		recraft.put(bp, Boolean.valueOf(false));
		sharpness.put(bp, Boolean.valueOf(true));
		fullsoup.put(bp, Boolean.valueOf(false));
	}

	public static final void openCustomInventory(final Player bp, final Player customer) {
		final Inventory custom = Bukkit.createInventory(bp, 54, "§c1v1 contra " + customer.getName());

		for (int i = 0; i < 54; i++) {
			custom.setItem(i, SpawnItens.newItem(Material.STAINED_GLASS_PANE, "§b§l-", 1, (byte) 8));
		}
		
		custom.setItem(43, SpawnItens.newItem(Material.WOOL, "§a§lDesafiar Jogador", 1, (byte) 5));
		custom.setItem(44,SpawnItens.newItem(Material.WOOL, "§a§lDesafiar Jogador", 1, (byte) 5));
		custom.setItem(52, SpawnItens.newItem(Material.WOOL, "§a§lDesafiar Jogador", 1, (byte) 5));
		custom.setItem(53,SpawnItens.newItem(Material.WOOL, "§a§lDesafiar Jogador", 1, (byte) 5));

		if (espada.containsKey(bp)) {
			if (espada.get(bp) == Material.WOOD_SWORD) {
				custom.setItem(20, SpawnItens.newItem(Material.WOOD_SWORD, "§6Espada de Madeira",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua espada!", "" }));
			} else if (espada.get(bp) == Material.STONE_SWORD) {
				custom.setItem(20, SpawnItens.newItem(Material.STONE_SWORD, "§6Espada de Pedra",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua espada!", "" }));
			} else if (espada.get(bp) == Material.IRON_SWORD) {
				custom.setItem(20, SpawnItens.newItem(Material.IRON_SWORD, "§6Espada de Ferro",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua espada!", "" }));
			} else if (espada.get(bp) == Material.DIAMOND_SWORD) {
				custom.setItem(20, SpawnItens.newItem(Material.DIAMOND_SWORD, "§6Espada de Diamante",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua espada!", "" }));
			}
		}
		if (armaduras.containsKey(bp)) {
			if (armaduras.get(bp) == "LEATHER") {
				custom.setItem(21, SpawnItens.newItem(Material.LEATHER_CHESTPLATE, "§eArmadura de Couro",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua armadura!", "" }));
			} else if (armaduras.get(bp) == "IRON") {
				custom.setItem(21, SpawnItens.newItem(Material.IRON_CHESTPLATE, "§eArmadura de Ferro",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua armadura!", "" }));
			} else if (armaduras.get(bp) == "DIAMOND") {
				custom.setItem(21, SpawnItens.newItem(Material.DIAMOND_CHESTPLATE, "§eArmadura de Diamante",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua armadura!", "" }));
			} else if (armaduras.get(bp) == "SEM") {
				custom.setItem(21, SpawnItens.newItem(Material.GOLD_HELMET, "§eSem Armadura",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de sua armadura!", "" }));
			}
		}
		if (recrafttype.containsKey(bp)) {
			if (recrafttype.get(bp) == "COGUMELO") {
				custom.setItem(22, SpawnItens.newItem(Material.RED_MUSHROOM, "§bRecrafts de Cogumelo",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de seu recraft!", "" }));
			} else if (recrafttype.get(bp) == "COCOABEAN") {
				custom.setItem(22, SpawnItens.newItem(Material.COCOA, "§bRecrafts de Cocoabean",
						new String[] { "§3Clique aqui para mudar", "§3o tipo de seu recraft!", "" }));
			}
		}
		if (recraft.containsKey(bp)) {
			if (recraft.get(bp)) {
				custom.setItem(23, SpawnItens.newItem(Material.BROWN_MUSHROOM, "§aCom Recraft",
						new String[] { "§3Clique aqui para", "§3desativar o recraft!", "" }));
			} else if (!recraft.get(bp)) {
				custom.setItem(23, SpawnItens.newItem(Material.MAGMA_CREAM, "§cSem Recraft",
						new String[] { "§3Clique aqui para", "§3ativar o recraft!", "" }));
			}
		}
		if (sharpness.containsKey(bp)) {
			if (sharpness.get(bp)) {
				custom.setItem(24, SpawnItens.newItem(Material.ENCHANTED_BOOK, "§3Com sharpness",
						new String[] { "§3Clique aqui para", "§3tirar a afiaçao da espada!", "" }));
			} else if (!sharpness.get(bp)) {
				custom.setItem(24, SpawnItens.newItem(Material.BOOK, "§3Sem sharpness",
						new String[] { "§3Clique aqui para", "§3colocar afiaçao na espada!", "" }));
			}
		}
		if (fullsoup.containsKey(bp)) {
			if (fullsoup.get(bp)) {
				custom.setItem(29, SpawnItens.newItem(Material.MUSHROOM_SOUP, "§2Full sopa",
						new String[] { "§3Clique aqui para", "§3usar 1 hotbar apenas", "" }));
			} else if (!fullsoup.get(bp)) {
				custom.setItem(29, SpawnItens.newItem(Material.BOWL, "§21 Hotbar",
						new String[] { "§3Clique aqui para", "§3usar full sopa", "" }));
			}
		}
		bp.openInventory(custom);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public final void onCustomItensChange(final InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			final Player bp = (Player) e.getWhoClicked();
			if (e.getInventory().getName().equalsIgnoreCase("§c1v1 contra " + playername.get(bp))
					&& e.getCurrentItem() != null) {
				// espadas //
				if (e.getCurrentItem().getType() == Material.WOOD_SWORD) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					espada.put(bp, Material.STONE_SWORD);
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					espada.put(bp, Material.IRON_SWORD);
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.IRON_SWORD) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					espada.put(bp, Material.DIAMOND_SWORD);
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					espada.put(bp, Material.WOOD_SWORD);
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				// armaduras //
				if (e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					armaduras.put(bp, "IRON");
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					armaduras.put(bp, "DIAMOND");
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					armaduras.put(bp, "SEM");
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.GOLD_HELMET) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					armaduras.put(bp, "LEATHER");
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				// tipo de recraft //
				if (e.getCurrentItem().getType() == Material.RED_MUSHROOM) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					recrafttype.put(bp, "COCOABEAN");
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.COCOA) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					recrafttype.put(bp, "COGUMELO");
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				// recraft ativado/desativado //
				if (e.getCurrentItem().getType() == Material.BROWN_MUSHROOM) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					recraft.put(bp, Boolean.valueOf(false));
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.MAGMA_CREAM) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					recraft.put(bp, Boolean.valueOf(true));
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				// sharpness ativado/desativado //
				if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					sharpness.put(bp, Boolean.valueOf(false));
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.BOOK) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					sharpness.put(bp, Boolean.valueOf(true));
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				// fullsopa ativado/desativado //
				if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					fullsoup.put(bp, Boolean.valueOf(false));
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.BOWL) {
					e.setCancelled(true);
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					fullsoup.put(bp, Boolean.valueOf(true));
					openCustomInventory(bp, Bukkit.getPlayer(playername.get(bp)));
				}
				if (e.getCurrentItem().getType() == Material.WOOL) {
					e.setCancelled(true);
					bp.closeInventory();
					if (Bukkit.getPlayer(playername.get(bp)) == null) {
						bp.closeInventory();
						bp.sendMessage(BattleStrings.getBattlePlayerIsNotOnlineMessage());
						return;
					}
					bp.sendMessage(
							"§e§l1v1 CUSTOM §Você enviou um desafio de 1v1 customizado para §b" + playername.get(bp) + "§f.");
					Bukkit.getPlayer(playername.get(bp)).sendMessage("§e§l1v1 CUSTOM §fVocê recebeu desafio de 1v1 customizado de §e" + bp.getName() + "§f.");
					X1WarpListener.cooldown.add(bp);
					X1WarpListener.challengec.put(bp, Bukkit.getPlayer(playername.get(bp)));
					Bukkit.getScheduler().runTaskLater(BukkitMain.getPlugin(), new Runnable() {
						@Override
						public void run() {
							if (X1WarpListener.cooldown.contains(bp)) {
								X1WarpListener.cooldown.remove(bp);
							}
							if (X1WarpListener.challengec.containsKey(bp)) {
								X1WarpListener.challengec.remove(bp);
							}
						}
					}, 5 * 20);
				} else {
					e.setCancelled(true);
				}
			}
		}
	}
}
