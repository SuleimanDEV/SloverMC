package br.com.slovermc.lobby.utils;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class LobbyItens {

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
		ItemStack head = new ItemStack(Material.PAPER, quantity, (byte) id);
		SkullMeta headmeta = (SkullMeta) head.getItemMeta();
		headmeta.setOwner(owner);
		headmeta.setDisplayName(name);
		head.setItemMeta(headmeta);
		return head;
	}
	
	public static final ItemStack newHead(final Player p, final String owner, final String name, final String[] desc, final int quantity,
			final byte id) {
		ItemStack head = new ItemStack(Material.PAPER, quantity, (byte) id);
		SkullMeta headmeta = (SkullMeta) head.getItemMeta();
		headmeta.setOwner(owner);
		headmeta.setDisplayName(name);
		headmeta.setLore(Arrays.asList(desc));
		head.setItemMeta(headmeta);
		return head;
	}

	public static void setLobbyItens(Player p) {
		p.getInventory().clear();
		p.getInventory().setItem(4, newItem(Material.COMPASS, "§aServidores §7(Clique)", new String[] {null}));

	}
}