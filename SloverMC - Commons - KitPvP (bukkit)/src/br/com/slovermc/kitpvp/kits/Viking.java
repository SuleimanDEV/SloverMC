package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;

public final class Viking {
	
	public static final ItemStack VikingAxe() {
		final ItemStack i = new ItemStack(Material.DIAMOND_AXE);
		final ItemMeta ik = i.getItemMeta();
		
		ik.setDisplayName("§b§lViking");
		ik.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		i.setItemMeta(ik);
		return i;
	}

	public static final void setVikingKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Viking");
		bp.getInventory().setItem(0, VikingAxe());
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lViking");
		TittleAPI.sendTittle(bp, "§bKit Viking");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
}
