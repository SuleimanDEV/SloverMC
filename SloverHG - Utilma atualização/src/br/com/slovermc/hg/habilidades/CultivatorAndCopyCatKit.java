package br.com.slovermc.hg.habilidades;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import br.com.slovermc.hg.api.KitAPI;

public class CultivatorAndCopyCatKit implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlock(BlockPlaceEvent e) { 
		Player player = e.getPlayer();
		Block block = e.getBlock();
		if (KitAPI.getInstance().hasKit(player, "Cultivator") || KitAPI.getInstance().hasKit2(player, "Cultivator")) { 
			if (block.getType() == Material.SAPLING) { 
				block.setType(Material.AIR);
				block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
			}
			else if (block.getType() == Material.CROPS) { 
				block.setData((byte)7);
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) { 
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		Player target = player.getKiller();
		if (KitAPI.getInstance().hasKit(target, "Copycat")) { 
			KitAPI.getInstance().setKit(player, KitAPI.getInstance().getKit(target));
			target.sendMessage("§b§lCULTIVATOR §fSeu novo kit primário agora é §b§l" + KitAPI.getInstance().getKit(target) + "§f.");
		}
		if (KitAPI.getInstance().hasKit2(target, "Copycat")) { 
			KitAPI.getInstance().setKit2(player, KitAPI.getInstance().getKit2(target));
			target.sendMessage("§b§lCULTIVATOR §fSeu novo kit secundário agora é §b§l" + KitAPI.getInstance().getKit2(target) + "§f.");
		}
	}
}
