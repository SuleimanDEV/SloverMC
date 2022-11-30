package br.com.slovermc.kitpvp.warps.Fps;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class FpsItens {

	public static final ItemStack newSimpleItemStack(final Material material, int quantity) {
		return new ItemStack(material, quantity);
	}

	public static final ItemStack newDiamondSwordSharpness() {
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		return sword;
	}

	public static final void setWarpFpsItensToBattlePlayer(final Player bp) {
		bp.getInventory().clear();
		bp.getInventory().setArmorContents(null);
		bp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		bp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		bp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		bp.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		for (int i = 1; i < 36; i++) {
			bp.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
		}
		bp.getInventory().setItem(0, newDiamondSwordSharpness());
	}
}
