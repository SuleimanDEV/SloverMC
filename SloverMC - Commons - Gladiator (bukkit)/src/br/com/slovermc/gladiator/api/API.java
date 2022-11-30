package br.com.slovermc.gladiator.api;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.ProtocolInjector;

import br.com.slovermc.gladiator.BukkitMain;
import net.minecraft.server.v1_7_R4.AttributeInstance;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import net.minecraft.server.v1_7_R4.PathEntity;

public class API {

	private static int VERSION = 47;

	public final static void PetFollow(final Player player, final Player pet, final double speed) {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				if ((!pet.isValid() || (!player.isOnline()))) {
					this.cancel();
				}
				net.minecraft.server.v1_7_R4.Entity pett = ((CraftEntity) pet).getHandle();
				((EntityInsentient) pett).getNavigation().a(2);
				Object petf = ((CraftEntity) pet).getHandle();
				Location targetLocation = player.getLocation();
				PathEntity path;
				path = ((EntityInsentient) petf).getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(),
						targetLocation.getZ() + 1);
				if (path != null) {
					((EntityInsentient) petf).getNavigation().a(path, 1.0D);
					((EntityInsentient) petf).getNavigation().a(2.0D);
				}
				int distance = (int) Bukkit.getPlayer(player.getName()).getLocation().distance(pet.getLocation());
				if (distance > 10 && !pet.isDead() && player.isOnGround()) {
					pet.teleport(player.getLocation());
				}
				AttributeInstance attributes = ((EntityInsentient) ((CraftEntity) pet).getHandle())
						.getAttributeInstance(GenericAttributes.d);
				attributes.setValue(speed);
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0L, 20L);
	}

	public static ItemStack item(Inventory inv, Player p, Material item, int quantidade, int data, String nome,
			String description, int slot) {
		ItemStack icone = new ItemStack(item, quantidade, (short) data);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName(nome);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(description);
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		inv.setItem(slot, icone);
		return icone;

	}

	public static ItemStack item2(Inventory inv, Player p, Material item, int quantidade, int data, String nome,
			String description) {
		ItemStack icone = new ItemStack(item, quantidade, (short) data);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName(nome);
		ArrayList<String> lore = new ArrayList<>();
		if (lore != null) {
			lore.add(description);
		}
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		return icone;
	}

	public static ItemStack itemenchant(Inventory inv, Player p, Material item, int quantidade, int data, String nome,
			String description, int slot, Enchantment enchant, int levelenchant) {
		ItemStack icone = new ItemStack(item, quantidade, (short) data);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName(nome);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(description);
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		inv.setItem(slot, icone);
		icone.addEnchantment(enchant, levelenchant);
		return icone;
	}

	public static void bau(Inventory inv, Material item, int quantidade, int data, String nome, String description,
			int slot) {
		ItemStack icone = new ItemStack(item, quantidade, (short) data);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName(nome);
		ArrayList<String> lore = new ArrayList<>();
		if (lore != null) {
			lore.add(description);
		}
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		inv.setItem(slot, icone);
	}
	public static void Effect(Player p, PotionEffectType PotionEffectTy, int booster, int time) {
		p.addPotionEffect(new PotionEffect(PotionEffectTy, time * 20, booster - 1));
	}

	public static void removeGladiatorEffect(Player p) {
		p.removePotionEffect(PotionEffectType.WITHER);
	}

	public static ItemStack Skull(String nome, String owner) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM);
		skull.setDurability((short) 3);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(owner);
		sm.setDisplayName(nome);
		skull.setItemMeta(sm);
		return skull;
	}

	public static ItemStack SkullL(String nome, String owner, String[] lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM);
		skull.setDurability((short) 3);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setLore(Arrays.asList(lore));
		sm.setOwner(owner);
		sm.setDisplayName(nome);
		skull.setItemMeta(sm);
		return skull;
	}

	public static ItemStack IL(Material m, String nome, int shrt, int qnt, String[] lore) {
		ItemStack x = new ItemStack(m, qnt, (short) shrt);
		ItemMeta x2 = x.getItemMeta();
		x2.setDisplayName(nome);
		x2.setLore(Arrays.asList(lore));
		x.setItemMeta(x2);
		return x;
	}

	public static ItemStack PSN(int qnt, PotionType pt, boolean splash, boolean duration) {
		ItemStack item = new ItemStack(Material.POTION, qnt);
		Potion pot = new Potion(1);
		pot.setType(pt);
		pot.setHasExtendedDuration(duration);
		pot.setSplash(splash);
		pot.apply(item);
		return item;
	}

	public static ItemStack P(String nome, int qnt, PotionType pt, boolean splash, boolean duration) {
		ItemStack item = new ItemStack(Material.POTION, qnt);
		ItemMeta x2 = item.getItemMeta();
		item.setItemMeta(x2);
		x2.setDisplayName(nome);
		Potion pot = new Potion(1);
		pot.setType(pt);
		pot.setHasExtendedDuration(duration);
		pot.setSplash(splash);
		pot.apply(item);
		return item;
	}

	public static ItemStack PL(String nome, int qnt, PotionType pt, boolean splash, boolean duration, String[] lore) {
		ItemStack item = new ItemStack(Material.POTION, qnt);
		ItemMeta x2 = item.getItemMeta();
		x2.setLore(Arrays.asList(lore));
		x2.setDisplayName(nome);
		item.setItemMeta(x2);
		Potion pot = new Potion(1);
		pot.setType(pt);
		pot.setHasExtendedDuration(duration);
		pot.setSplash(splash);
		pot.apply(item);
		return item;
	}

	public static ItemStack PSNND(int qnt, PotionType pt, boolean splash) {
		ItemStack item = new ItemStack(Material.POTION, qnt);
		Potion pot = new Potion(1);
		pot.setType(pt);
		pot.setSplash(splash);
		pot.apply(item);
		return item;
	}

	public static ItemStack I(Material m, String nome, int shrt, int qnt) {
		ItemStack x = new ItemStack(m, qnt, (short) shrt);
		ItemMeta x2 = x.getItemMeta();
		x2.setDisplayName(nome);
		x.setItemMeta(x2);
		return x;
	}

	public static ItemStack ISN(Material m, int shrt, int qnt) {
		ItemStack x = new ItemStack(m, qnt, (short) shrt);
		ItemMeta x2 = x.getItemMeta();
		x.setItemMeta(x2);
		return x;
	}

	public static ItemStack ILE(Material m, String nome, int shrt, int qnt, String[] lore, Enchantment encht, int nvl) {
		ItemStack x = new ItemStack(m, qnt, (short) shrt);
		ItemMeta x2 = x.getItemMeta();
		x2.setDisplayName(nome);
		x2.setLore(Arrays.asList(lore));
		x2.addEnchant(encht, nvl, true);
		x.setItemMeta(x2);
		return x;
	}

	public static ItemStack IE(Material m, String nome, int shrt, int qnt, Enchantment encht, int nvl) {
		ItemStack x = new ItemStack(m, qnt, (short) shrt);
		ItemMeta x2 = x.getItemMeta();
		x2.setDisplayName(nome);
		x2.addEnchant(encht, nvl, true);
		x.setItemMeta(x2);
		return x;
	}

	public static void lotar(Inventory inv, ItemStack item) {
		ItemStack[] is;
		int restante = (is = inv.getContents()).length;
		for (int i = 0; i < restante; i++) {
			ItemStack item2 = is[i];
			if (item2 == null) {
				inv.setItem(inv.firstEmpty(), item);
			}
		}
	}

	public static void sendTitle(Player p, String title) {
		if (((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() < VERSION) {
			return;
		}
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.TITLE, ChatSerializer.a("{\"text\": \"\"}").a(title)));
	}

	public static void sendSubTitle(Player p, String subtitle) {
		if (((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() < VERSION) {
			return;
		}
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.SUBTITLE, ChatSerializer.a("{\"text\": \"\"}").a(subtitle)));
	}

	public static ItemStack add(Material m) {
		return new ItemStack(m);
	}

	public static ItemStack add(Material m, int quantidade) {
		return new ItemStack(m, quantidade);
	}

	public static ItemStack add(Material m, String nome) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add1(Material m, String nome, int quantidade) {
		ItemStack item = new ItemStack(m, quantidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, Enchantment ench, int level) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(ench, level);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, int durability) {
		ItemStack item = new ItemStack(m);
		item.setDurability((short) durability);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, int quantidade, String nada) {
		ItemStack item = new ItemStack(m);
		item.setAmount(quantidade);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(String nome, String dono, String[] lore) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1);
		item.setDurability((short) 3);
		SkullMeta skull = (SkullMeta) item.getItemMeta();
		skull.setDisplayName(nome);
		skull.setOwner(dono);
		skull.setLore(Arrays.asList(lore));
		item.setItemMeta(skull);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, int durability) {
		ItemStack item = new ItemStack(m);
		item.setDurability((short) durability);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add4(Material m, String nome, String[] lore) {
		ItemStack item = new ItemStack(m);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, Enchantment enchant, int lvl) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant, lvl);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, Enchantment enchant1, int lvl1,
			Enchantment enchant2, int lvl2) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack add(Material m, String nome, String[] lore, Enchantment enchant1, int lvl1,
			Enchantment enchant2, int lvl2, Enchantment enchant3, int lvl3) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		item.addUnsafeEnchantment(enchant3, lvl3);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack potion(PotionEffectType efeito, String nome, String[] lore) {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta eta = (PotionMeta) item.getItemMeta();
		eta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 60 * 60, 9), true);
		eta.setLore(Arrays.asList(lore));
		eta.setDisplayName(nome);
		item.setItemMeta(eta);
		return item;
	}

	public static ItemStack couro() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.BLUE);
		meta.setDisplayName("§bArmadura de Couro");
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack couro(Color cor, String nome) {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(cor);
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getNamedSkull(String nick, String nome, String[] lore) {
		@SuppressWarnings("deprecation")
		ItemStack skull = new ItemStack(397, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		meta.setOwner(nick);
		skull.setItemMeta(meta);

		return skull;
	}

	public static ItemStack outro(Material m, String nome, Enchantment enchant, int lvl) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant, lvl);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack outro(Material m, String nome, Enchantment enchant1, int lvl1, Enchantment enchant2,
			int lvl2) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack outro(Material m, String nome, Enchantment enchant1, int lvl1, Enchantment enchant2,
			int lvl2, Enchantment enchant3, int lvl3) {
		ItemStack item = new ItemStack(m);
		item.addUnsafeEnchantment(enchant1, lvl1);
		item.addUnsafeEnchantment(enchant2, lvl2);
		item.addUnsafeEnchantment(enchant3, lvl3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}
}

