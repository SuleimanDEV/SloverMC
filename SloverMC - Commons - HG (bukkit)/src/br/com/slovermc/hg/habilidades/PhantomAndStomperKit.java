package br.com.slovermc.hg.habilidades;

import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;

public class PhantomAndStomperKit implements Listener {

	private HashMap<Player, ItemStack[]> armors = new HashMap<>();

	@EventHandler(priority = EventPriority.HIGH)
	private void onPlayerFallStomper(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if (BukkitMain.state != StateEnum.GAME) return;
		if (e.getCause() != DamageCause.FALL) return;
		if (KitAPI.getInstance().hasKit(player, "Stomper") || KitAPI.getInstance().hasKit2(player, "Stomper")) {
			for (Entity et : player.getNearbyEntities(6.0D, 3.0D, 6.0D)) {
				if (et instanceof Player) {
					Player d = (Player) et;
					if (d == player) continue;
					if (PlayerAPI.getInstance().hasPlayer(d)) {
						if (d.isSneaking()) {
							d.damage(0.1D, player);
							d.damage(3.9D);
						} else {
							if (KitAPI.getInstance().hasKit(d, "Antitower") || KitAPI.getInstance().hasKit2(d, "Antitower")) {
								d.damage(0.0D);
							} else { 
								d.damage(0.1D, player);
								d.damage(player.getFallDistance() - 8.1F);
							}
						}
					}
				}
			}
			Location player_location = player.getLocation();
			int radius = 2;
			for (int i = 0; i < 6; i++) {
				for (double x = -radius; x <= radius; x = x + 1.0D) {
					for (double z = -radius; z <= radius; z = z + 1.0D) {
						Location effect_location = new Location(player_location.getWorld(), player_location.getX() + x,
								player_location.getY(), player_location.getZ() + z);
						effect_location.getWorld().playEffect(effect_location, Effect.WITCH_MAGIC, 500);
					}
				}
			}
			if (e.getDamage() > 4.0D) {
				e.setDamage(4.0D);
			}
		}
	}
	
	@EventHandler
	protected void onPlayerInteractPhantom(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (KitAPI.getInstance().hasKit(p, "Phantom")) {
			if (p.getItemInHand().getType() == Material.FEATHER) {
				if (!KitAPI.getInstance().hasCooldown(p)) {
					KitAPI.getInstance().addCooldown(p, 40);
					p.setAllowFlight(true);
					armors.put(p, p.getInventory().getArmorContents());
					p.getInventory()
							.setArmorContents(new ItemStack[] { Cor(Material.LEATHER_BOOTS, Color.WHITE),
									Cor(Material.LEATHER_LEGGINGS, Color.WHITE),
									Cor(Material.LEATHER_CHESTPLATE, Color.WHITE),
									Cor(Material.LEATHER_HELMET, Color.WHITE) });
					p.updateInventory();
					new BukkitRunnable() {
						int tempo = 5;

						public void run() {
							if (tempo > 0 && tempo != 1) {
								p.sendMessage("§b§lPHANTOM §fVocê ainda tem " + tempo
										+ " segundos de vôo!");
							}
							if (tempo == 1) {
								p.sendMessage("§b§lPHANTOM §fVocê ainda tem " + tempo
										+ " segundo de vôo!");
							}
							if (tempo == 0) {
								p.setAllowFlight(false);
								p.getInventory().setArmorContents((ItemStack[])armors.get(p));
								armors.remove(p);
								p.sendMessage("§b§lPHANTOM §fSuas asas foram removidas!");
								cancel();
							}
							--tempo;
						}
					}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
				} else {
					KitAPI.getInstance().messageCooldown(p);
				}
			}
		} else if (KitAPI.getInstance().hasKit2(p, "Phantom")) {
			if(!BukkitMain.getPlugin().duoKit())return;
			
			if(p.getItemInHand().getType()==Material.FEATHER){
				if(!KitAPI.getInstance().hasCooldown2(p)){
					KitAPI.getInstance().addCooldown2(p, 40);
					p.setAllowFlight(true);
					armors.put(p, p.getInventory().getArmorContents());
					p.getInventory()
							.setArmorContents(new ItemStack[] { Cor(Material.LEATHER_BOOTS, Color.WHITE),
									Cor(Material.LEATHER_LEGGINGS, Color.WHITE),
									Cor(Material.LEATHER_CHESTPLATE, Color.WHITE),
									Cor(Material.LEATHER_HELMET, Color.WHITE) });
					p.updateInventory();
					new BukkitRunnable() {
						int tempo = 5;

						public void run() {
							if (tempo > 0 && tempo != 1) {
								p.sendMessage("§b§lPHANTOM §fVocê ainda tem " + tempo
										+ " segundos de vôo!");
							}
							if (tempo == 1) {
								p.sendMessage("§b§lPHANTOM §fVocê ainda tem " + tempo
										+ " segundo de vôo!");
							}
							if (tempo == 0) {
								p.setAllowFlight(false);
								p.getInventory().setArmorContents((ItemStack[])armors.get(p));
								armors.remove(p);
								p.sendMessage("§b§lPHANTOM §fSuas asas foiram removidas!");
								cancel();
							}
							--tempo;
						}
					}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
				}else{
					KitAPI.getInstance().messageCooldown2(p);
				}
			}
		}
	}

	@EventHandler
	protected void onPlayerThiefPhantomAndDeshfireAndSonic(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() != null) {
			if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS
					|| e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE
					|| e.getCurrentItem().getType() == Material.LEATHER_HELMET
					|| e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS) {
				if (KitAPI.getInstance().hasKit(p, "Phantom") || KitAPI.getInstance().hasKit(p, "Sonic")
						|| KitAPI.getInstance().hasKit(p, "Deshfire")) {
					if (KitAPI.getInstance().hasCooldown(p)) {
						e.setCancelled(true);
					}
				} else if (KitAPI.getInstance().hasKit2(p, "Phantom") || KitAPI.getInstance().hasKit2(p, "Sonic")
						|| KitAPI.getInstance().hasKit2(p, "Deshfire")) {
					if (!BukkitMain.getPlugin().duoKit()) return;
					if (KitAPI.getInstance().hasCooldown2(p)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	private ItemStack Cor(Material mat, Color cor) {
		ItemStack armor = new ItemStack(mat);
		LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
		meta.setColor(cor);
		armor.setItemMeta(meta);
		return armor;
	}
}
