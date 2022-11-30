package br.com.slovermc.hg.habilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class KangarooKit implements Listener {

	List<Player> kanga = new ArrayList();
	HashMap<Player, Long> hitNerf = new HashMap();

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Kangaroo") || KitAPI.getInstance().hasKit2(player, "Kangaroo")) {
			if (player.getItemInHand().getType() == Material.FIREWORK) {
				e.setCancelled(true);
				if (kanga.contains(player))
					return;
				if ((hitNerf.containsKey(player))
						&& (((Long) hitNerf.get(player)).longValue() > System.currentTimeMillis())) {
					player.setVelocity(new Vector(0.0D, -1.0D, 0.0D));
					player.sendMessage("§b§lKANGAROO §fVocê está em combate!");
					return;
				}
				Vector v = new Vector();
				if (player.isSneaking()) {
					v = player.getEyeLocation().getDirection().multiply(1.25F).setY(0.6F);
				} else {
					v = player.getEyeLocation().getDirection().multiply(0.2F).setY(0.8F);
				}
				player.setVelocity(v);
				kanga.add(player);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (kanga.contains(player)) {
			if ((player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)
					|| ((player.isOnGround()))) {
				kanga.remove(player);
			}
		}
	}

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player))
			return;
		if (!(e.getEntity() instanceof Player))
			return;
		Player player = (Player) e.getEntity();
		Player target = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Kangaroo") || KitAPI.getInstance().hasKit2(player, "Kangaroo")) {
			if (PlayerAPI.getInstance().hasPlayer(target)) {
				hitNerf.put(player, Long.valueOf(System.currentTimeMillis() + 5000L));
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player player = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			if (KitAPI.getInstance().hasKit(player, "Kangaroo") || KitAPI.getInstance().hasKit2(player, "Kangaroo")) {
				if (e.getDamage() >= 5.0D) {
					e.setDamage(4.0D);
				}
			}
		}
	}
}