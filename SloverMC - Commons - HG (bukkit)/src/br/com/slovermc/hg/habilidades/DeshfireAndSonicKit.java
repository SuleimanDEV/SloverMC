package br.com.slovermc.hg.habilidades;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

public class DeshfireAndSonicKit implements Listener {

	private List<Player> fire = Lists.newArrayList();
	private List<Player> sonic = Lists.newArrayList();
	private HashMap<Player, ItemStack[]> armor = Maps.newHashMap();
	
	@EventHandler
	private void onPlayerInteractDeshfire(PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Deshfire")) {
			ItemStack item = player.getItemInHand();
			if (item.getType() == Material.REDSTONE_BLOCK) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					player.updateInventory();
				}
				if (BukkitMain.state == StateEnum.INVINCIBILITY || BukkitMain.state == StateEnum.STARTING) return;
				if (!KitAPI.getInstance().hasCooldown(player)) {
					KitAPI.getInstance().addCooldown(player, 40);
					armor.put(player, player.getInventory().getArmorContents());
					player.getInventory()
							.setArmorContents(new ItemStack[] { leatherColor(Material.LEATHER_BOOTS, Color.RED),
									leatherColor(Material.LEATHER_LEGGINGS, Color.RED),
									leatherColor(Material.LEATHER_CHESTPLATE, Color.RED),
									leatherColor(Material.LEATHER_HELMET, Color.RED) 
									});
					
					player.updateInventory();
					player.setVelocity(player.getEyeLocation().getDirection().multiply(6));
					fire.add(player);

					new BukkitRunnable() {
						public void run() {
							player.getInventory().setArmorContents((ItemStack[]) armor.get(player));
							player.updateInventory();
							armor.remove(player);
							fire.remove(player);
						}
					}.runTaskLater(BukkitMain.getPlugin(), 80L);
				} else {
					KitAPI.getInstance().messageCooldown(player);
				}
			}
			return;
		}
		
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Deshfire")) {
				ItemStack item = player.getItemInHand();
				if (item.getType() == Material.REDSTONE_BLOCK) {
					if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						e.setCancelled(true);
						player.updateInventory();
					}
					if (!KitAPI.getInstance().hasCooldown2(player)) {
						KitAPI.getInstance().addCooldown2(player, 40);
						armor.put(player, player.getInventory().getArmorContents());
						player.getInventory()
								.setArmorContents(new ItemStack[] { leatherColor(Material.LEATHER_BOOTS, Color.RED),
										leatherColor(Material.LEATHER_LEGGINGS, Color.RED),
										leatherColor(Material.LEATHER_CHESTPLATE, Color.RED),
										leatherColor(Material.LEATHER_HELMET, Color.RED) 
										});
						
						player.updateInventory();
						player.setVelocity(player.getEyeLocation().getDirection().multiply(6));
						fire.add(player);

						new BukkitRunnable() {
							public void run() {
								player.getInventory().setArmorContents((ItemStack[]) armor.get(player));
								player.updateInventory();
								armor.remove(player);
								fire.remove(player);
							}
						}.runTaskLater(BukkitMain.getPlugin(), 80L);
					} else {
						KitAPI.getInstance().messageCooldown2(player);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerMoveDeshfire(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (fire.contains(player)) {
			Location player_location = player.getLocation();
			player.getWorld().playEffect(player_location, Effect.MOBSPAWNER_FLAMES, 10, 0);
			for (Entity nearby_entity : player.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
				nearby_entity.setFireTicks(150);
			}
		}
	}
	
	@EventHandler
	private void onPlayerInteractSonic(PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Sonic")) {
			ItemStack item = player.getItemInHand();
			if (item.getType() == Material.LAPIS_BLOCK) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					player.updateInventory();
				}
				if (BukkitMain.state == StateEnum.INVINCIBILITY || BukkitMain.state == StateEnum.STARTING) return;
				if (!KitAPI.getInstance().hasCooldown(player)) {
					KitAPI.getInstance().addCooldown(player, 40);
					armor.put(player, player.getInventory().getArmorContents());
					player.getInventory()
							.setArmorContents(new ItemStack[] { leatherColor(Material.LEATHER_BOOTS, Color.BLUE),
									leatherColor(Material.LEATHER_LEGGINGS, Color.BLUE),
									leatherColor(Material.LEATHER_CHESTPLATE, Color.BLUE),
									leatherColor(Material.LEATHER_HELMET, Color.BLUE) 
									});
					
					player.updateInventory();
					player.setVelocity(player.getEyeLocation().getDirection().multiply(6));
					sonic.add(player);

					new BukkitRunnable() {
						public void run() {
							player.getInventory().setArmorContents((ItemStack[]) armor.get(player));
							player.updateInventory();
							armor.remove(player);
							sonic.remove(player);
						}
					}.runTaskLater(BukkitMain.getPlugin(), 80L);
				} else {
					KitAPI.getInstance().messageCooldown(player);
				}
			}
			
			return;
		}
		
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Sonic")) {
				ItemStack item = player.getItemInHand();
				if (item.getType() == Material.LAPIS_BLOCK) {
					if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						e.setCancelled(true);
						player.updateInventory();
					}
					if (BukkitMain.state == StateEnum.INVINCIBILITY || BukkitMain.state == StateEnum.STARTING) return;
					if (!KitAPI.getInstance().hasCooldown2(player)) {
						KitAPI.getInstance().addCooldown2(player, 40);
						armor.put(player, player.getInventory().getArmorContents());
						player.getInventory()
								.setArmorContents(new ItemStack[] { leatherColor(Material.LEATHER_BOOTS, Color.BLUE),
										leatherColor(Material.LEATHER_LEGGINGS, Color.BLUE),
										leatherColor(Material.LEATHER_CHESTPLATE, Color.BLUE),
										leatherColor(Material.LEATHER_HELMET, Color.BLUE) 
										});
						player.updateInventory();
						player.setVelocity(player.getEyeLocation().getDirection().multiply(6));
						sonic.add(player);

						new BukkitRunnable() {
							public void run() {
								player.getInventory().setArmorContents((ItemStack[]) armor.get(player));
								player.updateInventory();
								armor.remove(player);
								sonic.remove(player);
							}
						}.runTaskLater(BukkitMain.getPlugin(), 80L);
					} else {
						KitAPI.getInstance().messageCooldown2(player);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerMoveSonic(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (sonic.contains(player)) {
			Location player_location = player.getLocation();
			player.getWorld().playEffect(player_location, Effect.SMOKE, 10, 0);
			for (Entity nearby_entity : player.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
				if (nearby_entity instanceof LivingEntity) {
					((LivingEntity) nearby_entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
				}
			}
		}
	}
	
	private ItemStack leatherColor(Material material, Color color) {
		ItemStack item = new ItemStack(material);
		LeatherArmorMeta armormeta = (LeatherArmorMeta) item.getItemMeta();
		armormeta.setColor(color);
		item.setItemMeta(armormeta);

		return item;
	}
}
