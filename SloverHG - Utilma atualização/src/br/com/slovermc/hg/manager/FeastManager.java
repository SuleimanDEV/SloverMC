package br.com.slovermc.hg.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.google.common.collect.Lists;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.jnbt.DataException;
import br.com.slovermc.hg.utils.Schematic;

@SuppressWarnings({"deprecation"})
public class FeastManager {

	private static FeastManager instance = new FeastManager();
	public boolean feastpronto = false;
	public boolean feastspawnado = false;
	public BukkitTask task = null;
	public int feasttime = 300;
	private List<Block> chests = Lists.newArrayList();
	private List<Block> enchants = Lists.newArrayList();
	public Location feast = null;
	
	public static FeastManager getInstance() {
		return instance;
	}

	public void initialiseFeast(final Location location) {
		if (feastpronto) {
			return;
		}
		if (!feastspawnado) {
			int radius = 20;
			for (double d1 = -radius; d1 <= radius; d1 += 1.0D) {
				for (double d2 = -radius; d2 <= radius; d2 += 1.0D) {
					Location locationradius = new Location(Bukkit.getWorlds().get(0), location.getX() + d1,
							location.getY(), location.getZ() + d2);
					if (locationradius.distance(location) <= radius) {
						removerEmbaixo(locationradius.getBlock());
					}
				}
			}
			feastspawnado = true;
		}
		spawnarFeast(location);
		for (Iterator<?> iterator = Manager.getInstance().feast.iterator(); iterator.hasNext();) {
			Block block = (Block)iterator.next();
			if (block.getType() == Material.CHEST) {
				chests.add(block);
				block.setType(Material.AIR);
				block.getState().update();
			} else if (block.getType() == Material.ENCHANTMENT_TABLE) {
				enchants.add(block);
				block.setType(Material.AIR);
				block.getState().update();
				
				if (feast == null) {
					feast = block.getLocation();
				}
			}
		}

		task = new BukkitRunnable() {
			public void run() {
				if (feasttime % 60 == 0 && feasttime < 301 && feasttime > 60) {
					Bukkit.broadcastMessage("§c§lFEAST §fO feast irá spawnar nas cordenadas §b(" + feast.getBlockX() + ", " + feast.getBlockY() + ", " + feast.getBlockZ() + "§b)§f em " + feasttime / 60 + " minutos.");
				}
				if (feasttime == 60) {
					Bukkit.broadcastMessage("§c§lFEAST §fO feast irá spawnar nas cordenadas §b(" + feast.getBlockX() + ", " + feast.getBlockY() + ", " + feast.getBlockZ() + "§b)§f em " + feasttime / 60 + " minuto.");
				}
				if (feasttime % 15 == 0 && feasttime > 0 && feasttime < 60) {
					Bukkit.broadcastMessage("§c§lFEAST §fO feast irá spawnar nas cordenadas §b(" + feast.getBlockX() + ", " + feast.getBlockY() + ", " + feast.getBlockZ() + "§b)§f em " + feasttime + " segundos.");
				}
				if (feasttime < 6 && feasttime > 0) {		
					String timee = feasttime + " segundos.";
					if (feasttime == 1) {
						timee = "1 segundo.";
						for (Block block : chests) {
							FallingBlock blockfall = block.getLocation().getWorld().spawnFallingBlock(block.getLocation().add(0.0D, 4.0D, 0.0D), Material.WOOD, (byte) 0);
							blockfall.setDropItem(false);
						}
					}
					Bukkit.broadcastMessage("§c§lFEAST §fO feast irá spawnar nas cordenadas §b(" + feast.getBlockX() + ", " + feast.getBlockY() + ", " + feast.getBlockZ() + "§b)§f em " + timee);
				}
				if (feasttime <= 0) {
					for (Block enchant : enchants) {
						enchant.setType(Material.ENCHANTMENT_TABLE);
						enchant.getState().update();
					}
					for (Block chest : chests) {
						chest.setType(Material.CHEST);
						chest.getState().update();
					}
					for (Iterator<?> iterator = Manager.getInstance().feast.iterator(); iterator.hasNext();){
						Block block = (Block) iterator.next();
						if (block.getType() == Material.CHEST){
							Chest chest = (Chest) block.getState();
							encherFeast(chest);
						}
					}
					feastpronto = true;
					Bukkit.broadcastMessage("§c§lFEAST §fO feast spawnou nas cordenadas §b(" + feast.getBlockX() + ", " + feast.getBlockY() + ", " + feast.getBlockZ() + "§b)§f.");
					cancel();
					return;
				}
				--feasttime;
			}
		}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
	}

	public void createMinifeast() {
		int x = new Random().nextInt(300);
	    int z = new Random().nextInt(300);
	    ArrayList<Block> array = Lists.newArrayList();
		try {
			Schematic sch = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "minifeast.schematic"));
			Schematic.getInstance().generateSchematic(Bukkit.getWorld("world"), new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockAt(x, z).getY(), z), sch, array);
		} catch (Exception e) {
			return;
		}
		int xM = x + 50;
		int xN = x - 50;
		int zM = z + 50;
		int zN = z - 50;
		for (Iterator<?> iterator = array.iterator(); iterator.hasNext(); ) {
			Block block = (Block)iterator.next();
			
			if (block.getType() == Material.CHEST) {
				Chest chest = (Chest)block.getState();
				encherMinifeast(chest);
			}
		}
		Bukkit.broadcastMessage("§c§lFEAST §fUm minifeast irá spawnar entre §b(" + xM + ", " + xN + " e " + zM + ", " + zN + ")§f!");
	}
	
	private void encherFeast(Chest chest) {
		List<String> items = BukkitMain.getPlugin().getConfig().getStringList("feast");
		for (Iterator<?> iterator = items.iterator(); iterator.hasNext();) {
			String spliter = (String) iterator.next();
			String[] split = spliter.split(",");
			Random random = new Random();
			String itemid = split[0];
			int minamount = Integer.valueOf(split[1]);
			int maxamount = Integer.valueOf(split[2]);
			boolean force = Boolean.valueOf(split[3]);
			short durability = 9999;
			boolean next = force;
			int id = 0;
			int amount = 0;
			if (force == false) next = random.nextBoolean();
			if (next == true) {
				if (spliter.contains(";")) {
					String[] Split = itemid.split(";");
					id = Integer.valueOf(Split[0]);
					durability = Short.valueOf(Split[1]);
				} else {
					id = Integer.valueOf(itemid);
				}
				ItemStack item_add = new ItemStack(id, 1);
				if (durability != 9999) item_add.setDurability(durability);
				if (split.length == 6)  item_add.addUnsafeEnchantment(Enchantment.getById(Integer.valueOf(split[4])), Integer.valueOf(split[5]));
				if (maxamount == minamount) amount = maxamount;
				else amount = random.nextInt(maxamount - minamount + 1);
				item_add.setAmount(amount);
				int slot = chest.getInventory().firstEmpty();
				if (random.nextInt(100) < BukkitMain.getPlugin().getConfig().getInt("chancefeast")) chest.getInventory().setItem(slot, item_add);
				chest.update();
			}
		}
	}
	
	private void encherMinifeast(Chest chest) {
		List<String> items = BukkitMain.getPlugin().getConfig().getStringList("minifeast");
		for (Iterator<?> iterator = items.iterator(); iterator.hasNext();) {
			String spliter = (String) iterator.next();
			String[] split = spliter.split(",");
			Random random = new Random();
			String itemid = split[0];
			int minamount = Integer.valueOf(split[1]);
			int maxamount = Integer.valueOf(split[2]);
			boolean force = Boolean.valueOf(split[3]);
			short durability = 9999;
			boolean next = force;
			int id = 0;
			int amount = 0;
			if (force == false) next = random.nextBoolean();
			if (next == true) {
				if (spliter.contains(";")) {
					String[] Split = itemid.split(";");
					id = Integer.valueOf(Split[0]);
					durability = Short.valueOf(Split[1]);
				} else {
					id = Integer.valueOf(itemid);
				}
				ItemStack item_add = new ItemStack(id, 1);
				if (durability != 9999) item_add.setDurability(durability);
				if (split.length == 6)  item_add.addUnsafeEnchantment(Enchantment.getById(Integer.valueOf(split[4])), Integer.valueOf(split[5]));
				if (maxamount == minamount) amount = maxamount;
				else amount = random.nextInt(maxamount - minamount + 1);
				item_add.setAmount(amount);
				int slot = chest.getInventory().firstEmpty();
				if (random.nextInt(100) < BukkitMain.getPlugin().getConfig().getInt("chanceminifeast")) chest.getInventory().setItem(slot, item_add);
				chest.update();
			}
		}
	}

	private void spawnarFeast(Location location) {
		try {
			Schematic sch = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "feast.schematic"));
			Schematic.getInstance().generateSchematic(Bukkit.getWorlds().get(0), location, sch, Manager.getInstance().feast);
		} catch (IOException | DataException e) {
			Bukkit.getLogger().info("ERRO: " + e.getMessage());
		}
	}

	private void removerEmbaixo(Block bloco) {
		Location location = bloco.getLocation();
		location.setY(location.getY() + 1.0D);
		Block noBlock = Bukkit.getWorlds().get(0).getBlockAt(location);
		while (location.getY() < Bukkit.getWorlds().get(0).getMaxHeight()) {
			noBlock.setType(Material.AIR);
			location.setY(location.getY() + 1.0D);
			noBlock = Bukkit.getWorlds().get(0).getBlockAt(location);
		}
	}
}
