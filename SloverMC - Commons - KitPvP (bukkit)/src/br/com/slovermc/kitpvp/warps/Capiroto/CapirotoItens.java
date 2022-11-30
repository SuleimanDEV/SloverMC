package br.com.slovermc.kitpvp.warps.Capiroto;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.warps.WarpsAPI;

public final class CapirotoItens {
	
	public static final ItemStack capaceteP4() {
		final ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
		final ItemMeta ik = i.getItemMeta();
		ik.addEnchant(Enchantment.DURABILITY, 3, true);
		ik.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack peitoralP4() {
		final ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
		final ItemMeta ik = i.getItemMeta();
		ik.addEnchant(Enchantment.DURABILITY, 3, true);
		ik.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack calçaP4() {
		final ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
		final ItemMeta ik = i.getItemMeta();
		ik.addEnchant(Enchantment.DURABILITY, 3, true);
		ik.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack botasP4() {
		final ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
		final ItemMeta ik = i.getItemMeta();
		ik.addEnchant(Enchantment.DURABILITY, 3, true);
		ik.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack espada() {
		final ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
		final ItemMeta ik = i.getItemMeta();
		ik.addEnchant(Enchantment.DURABILITY, 3, true);
		ik.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		ik.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static final ItemStack arco() {
		final ItemStack i = new ItemStack(Material.BOW);
		final ItemMeta ik = i.getItemMeta();
		ik.addEnchant(Enchantment.ARROW_INFINITE, 5, true);
		ik.addEnchant(Enchantment.ARROW_FIRE, 5, true);
		ik.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
		ik.addEnchant(Enchantment.ARROW_KNOCKBACK, 5, true);
		i.setItemMeta(ik);
		return i;
	}
	
	public static final void updatePotionEffects() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (WarpsAPI.battlePlayerWarp.get(all) == WarpsAPI.Warps.CAPIROTO) {
						all.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 1));
						all.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10000, 2));
					}
				}
			}			
		}, 0, 15L);
	}

	public static final void onInventoryCapiroto(final Player bp) {
		bp.getInventory().clear();
		bp.getInventory().setArmorContents(null);
		bp.getInventory().setHelmet(capaceteP4());
		bp.getInventory().setChestplate(peitoralP4());
		bp.getInventory().setLeggings(calçaP4());
		bp.getInventory().setBoots(botasP4());
		
		bp.getInventory().setItem(0, espada());
		bp.getInventory().setItem(1, new ItemStack(Material.GOLDEN_APPLE, 10, (byte) 1));
		bp.getInventory().setItem(2, arco());
		
		bp.getInventory().setItem(9, capaceteP4());
		bp.getInventory().setItem(10, peitoralP4());
		bp.getInventory().setItem(11, calçaP4());
		bp.getInventory().setItem(12, botasP4());
		bp.getInventory().setItem(13, new ItemStack(Material.ARROW, 32));
		bp.updateInventory();
	}
}
