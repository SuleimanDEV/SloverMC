package br.com.slovermc.kitpvp.warps.Spawn;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public final class SpawnItens {

	public static final ItemStack newItem(final Material mat, final String name, final String[] desc) {
		final ItemStack item = new ItemStack(mat);
		final ItemMeta itemk = item.getItemMeta();
		itemk.setDisplayName(name);
		itemk.setLore(Arrays.asList(desc));
		item.setItemMeta(itemk);
		return item;
	}
	
	public static final ItemStack newItem(final Material mat, final String name, final String[] desc, final int qt, final byte bt) {
		final ItemStack item = new ItemStack(mat, qt, (byte) bt);
		final ItemMeta itemk = item.getItemMeta();
		itemk.setDisplayName(name);
		itemk.setLore(Arrays.asList(desc));
		item.setItemMeta(itemk);
		return item;
	}
	
	public static final ItemStack newItem(final Material mat, final String name, final int qt, final byte bt) {
		final ItemStack item = new ItemStack(mat, qt, (byte) bt);
		final ItemMeta itemk = item.getItemMeta();
		itemk.setDisplayName(name);
		item.setItemMeta(itemk);
		return item;
	}

	public static final ItemStack newHead(final Player p, final String owner, final String name, final int quantity,
			final byte id) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, quantity, (byte) id);
		SkullMeta headmeta = (SkullMeta) head.getItemMeta();
		headmeta.setOwner(owner);
		headmeta.setDisplayName(name);
		head.setItemMeta(headmeta);
		return head;
	}
	
	public static final ItemStack newHead(final Player p, final String owner, final String name, final String[] desc, final int quantity,
			final byte id) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, quantity, (byte) id);
		SkullMeta headmeta = (SkullMeta) head.getItemMeta();
		headmeta.setOwner(owner);
		headmeta.setDisplayName(name);
		headmeta.setLore(Arrays.asList(desc));
		head.setItemMeta(headmeta);
		return head;
	}

	public static final void setWarpSpawnItensToBattlePlayer(final Player bp) {
		bp.getInventory().clear();
		bp.getInventory().setArmorContents(null);
		bp.getInventory().setItem(0,
				newItem(Material.CHEST, "§aKits §7(Clique)", new String[] { "§bSelecione um de seus Kits" }));
		bp.getInventory().setItem(1,
				newItem(Material.PAPER, "§aWarps §7(Clique)", new String[] { "§bEscolha uma warp para jogar" }));		
//		bp.getInventory().setItem(6, newItem(Material.DIAMOND, "§b§lShop", new String[] { "", "§7Compre beneficios in-game ou online" }));
	//	bp.getInventory().setItem(6, newItem(Material.ENCHANTED_BOOK, "§b§lEventos", new String[] { "", "§7Clique para checar se um Evento esta ocorrendo"}));
		bp.updateInventory();
	} 
}