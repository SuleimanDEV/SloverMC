package br.com.slovermc.login.api;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class APIItems {

	public static final ItemStack createItem(final Material mat, final String name, final String[] desc, final int qnt,
			final byte color) {
		final ItemStack i = new ItemStack(mat, qnt, (byte) color);
		final ItemMeta im = i.getItemMeta();

		im.setDisplayName(name);
		im.setLore(Arrays.asList(desc));

		i.setItemMeta(im);

		return i;
	}
}