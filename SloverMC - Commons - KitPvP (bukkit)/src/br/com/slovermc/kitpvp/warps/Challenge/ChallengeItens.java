package br.com.slovermc.kitpvp.warps.Challenge;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.warps.Spawn.MenusWarpSpawn;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnItens;

public final class ChallengeItens {

	@SuppressWarnings("deprecation")
	public static final void onInventoryChallenge(final Player bp) {
		bp.getInventory().clear();
		bp.getInventory().setArmorContents(null);
		for (int i = 0; i < 36; i++) {
			bp.getInventory().setItem(i, SpawnItens.newItem(Material.MUSHROOM_SOUP, "", 1, (byte) 0));
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
		bp.updateInventory();
	}
}
