package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.warps.Spawn.MenusWarpSpawn;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class PvP {

	@SuppressWarnings("deprecation")
	public static final void defaultItens(final Player bp) {
		bp.getInventory().clear();
		bp.getInventory().setArmorContents(null);
		bp.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		bp.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		bp.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		bp.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		for (int i = 1; i < 36; i++) {
			bp.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
		}
		bp.getInventory().setItem(13, SpawnItens.newItem(Material.BOWL, "", 64, (byte) 0));
		bp.getInventory().setItem(14,
				(MenusWarpSpawn.cocoabean.contains(bp.getName())
						? new ItemStack(Material.getMaterial(351), 64, (byte) 3)
						: SpawnItens.newItem(Material.RED_MUSHROOM, "", 64, (byte) 0)));
		bp.getInventory().setItem(15,
				(MenusWarpSpawn.cocoabean.contains(bp.getName())
						? new ItemStack(Material.getMaterial(351), 64, (byte) 3)
						: SpawnItens.newItem(Material.BROWN_MUSHROOM, "", 64, (byte) 0)));
		bp.getInventory().setItem(8, SpawnItens.newItem(Material.COMPASS, "§3§lBussola", 1, (byte) 0));
		WarpAPI.setWarp(bp, "PVP");
	}

	public static final ItemStack swordEnchanted() {
		ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
		i.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		return i;
	}

	public static final void setPvPKit(final Player bp) {
		KitAPI.setKit(bp, "PvP");
		defaultItens(bp);
		bp.getInventory().setItem(8, SpawnItens.newItem(Material.COMPASS, "§3§lBussola", 1, (byte) 0));
		bp.getInventory().setItem(0, swordEnchanted());
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lPvP");
		TittleAPI.sendTittle(bp, "§bKit PvP");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
}
