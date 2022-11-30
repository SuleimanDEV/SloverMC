package br.com.slovermc.gladiator.timer;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ClearArena implements Listener {

	public static ArrayList<Block> bloco = new ArrayList<Block>();

	@EventHandler
	public final static void getBlocksInArena(BlockBreakEvent evento) {
		bloco.remove(evento.getBlock());
	}

	@EventHandler
	public final void getBreakBlocksInArena(BlockPlaceEvent e) {
		bloco.add(e.getBlock());
	}

	public final static void ClearArenaInGame(Player p) {
		for (Block bloco : bloco) {
			bloco.getLocation().getBlock().setType(Material.AIR);
		}
	}
}
