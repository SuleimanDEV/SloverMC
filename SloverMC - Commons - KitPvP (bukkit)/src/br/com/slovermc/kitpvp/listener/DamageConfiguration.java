package br.com.slovermc.kitpvp.listener;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.command.essentials.DanoCommand;

public final class DamageConfiguration implements Listener {
	
	/**
	 * Esta class foi copiada do plugin OFICIAL do battlebits
	 */
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEvent(EntityDamageByEntityEvent event) {
		if (!DanoCommand.onDamage) {
			event.setCancelled(true);
		}
		if (!(event.getDamager() instanceof Player))
			return;
		final Player p = (Player) event.getDamager();
		ItemStack sword = p.getItemInHand();
		double damage = event.getDamage();
		double swordDamage = getDamage(sword.getType());
		boolean isMore = false;
		if (damage > 1) {
			isMore = true;
		}
		if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
			for (PotionEffect effect : p.getActivePotionEffects()) {
				if (effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
					double minus;
					if (isCrital(p)) {
						minus = (swordDamage + (swordDamage / 2)) * 1.3 * (effect.getAmplifier() + 1);
					} else {
						minus = swordDamage * 1.3 * (effect.getAmplifier() + 1);
					}
					damage = damage - minus;
					damage += 2 * (effect.getAmplifier() + 1);
					break;
				}
			}
		}
		if (!sword.getEnchantments().isEmpty()) {
			if (sword.containsEnchantment(Enchantment.DAMAGE_ARTHROPODS) && isArthropod(event.getEntityType())) {
				damage = damage - (1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS));
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_UNDEAD) && isUndead(event.getEntityType())) {
				damage = damage - (1.5 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD));
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
			}
			if (sword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
				damage = damage - 1.25 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
				damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			}
		}
		if (isCrital(p)) {
			damage = damage - (swordDamage / 2);
			damage += 1;
		}
		if (isMore)
			damage -= 2;
		if (KitAPI.getKit(p) == "Boxer") {
			damage = damage += 2;
		}
		event.setDamage(damage);
	}

	@SuppressWarnings("deprecation")
	private boolean isCrital(final Player p) {
		return p.getFallDistance() > 0 && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
	}

	private boolean isArthropod(final EntityType type) {
		switch (type) {
		case CAVE_SPIDER:
			return true;
		case SPIDER:
			return true;
		case SILVERFISH:
			return true;
		default:
			break;
		}
		return false;
	}

	private boolean isUndead(final EntityType type) {
		switch (type) {
		case SKELETON:
			return true;
		case ZOMBIE:
			return true;
		case WITHER_SKULL:
			return true;
		case PIG_ZOMBIE:
			return true;
		default:
			break;
		}
		return false;
	}

	private double getDamage(final Material type) {
		double damage = 1.0;
		if (type.toString().contains("DIAMOND_")) {
			damage = 8.0;
		} else if (type.toString().contains("IRON_")) {
			damage = 7.0;
		} else if (type.toString().contains("STONE_")) {
			damage = 6.0;
		} else if (type.toString().contains("WOOD_")) {
			damage = 5.0;
		} else if (type.toString().contains("GOLD_")) {
			damage = 5.0;
		}
		if (!type.toString().contains("_SWORD")) {
			damage--;
			if (!type.toString().contains("_AXE")) {
				damage--;
				if (!type.toString().contains("_PICKAXE")) {
					damage--;
					if (!type.toString().contains("_SPADE")) {
						damage = 1.0;
					}
				}
			}
		}
		return damage;
	}
}