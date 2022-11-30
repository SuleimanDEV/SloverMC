package br.com.slovermc.hg.habilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

public class MagmaAndMinerKit implements Listener {

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		Player target = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(target, "Magma") || KitAPI.getInstance().hasKit2(target, "Magma")) {
			if (BukkitMain.state != StateEnum.STARTING || BukkitMain.state != StateEnum.INVINCIBILITY) return;
			if (new Random().nextInt(100) < 33) {
				player.setFireTicks(100);
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
			Material mat = e.getBlock().getType();
			if (KitAPI.getInstance().hasKit(player, "Miner") || KitAPI.getInstance().hasKit2(player, "Miner")) { 
			if (!mat.name().contains("ORE")) return;
			e.setCancelled(true);
			int minerio = 0;
			for (Block b : getNearbyBlocks(e.getBlock(), 5)) {
				if (b.getType() == mat) {
					minerio++;
					b.setType(Material.AIR);
				}
			}
			e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(mat == Material.COAL_ORE ? Material.COAL : mat, minerio));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Block> getNearbyBlocks(Block block, int i) {
		List<Block> blocos = new ArrayList();
		for (int x = -i; x <= i; x++) {
			for (int y = -i; y <= i; y++) {
				for (int z = -i; z <= i; z++) {
					blocos.add(block.getLocation().clone().add(x, y, z).getBlock());
				}
			}
		}
		return blocos;
	}
}
