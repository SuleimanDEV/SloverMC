package br.com.slovermc.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;

public final class AntiStomper {

	public static final void setAntiStomperKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "AntiStomper");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lAntiStomper");
		TittleAPI.sendTittle(bp, "§bKit AntiStomper");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
}
