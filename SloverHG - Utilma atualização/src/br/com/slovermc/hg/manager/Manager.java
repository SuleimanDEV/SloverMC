package br.com.slovermc.hg.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.jnbt.DataException;
import br.com.slovermc.hg.utils.Countdown;
import br.com.slovermc.hg.utils.Permission;
import br.com.slovermc.hg.utils.Schematic;

@SuppressWarnings({ "deprecation" })
public class Manager {

	public ArrayList<Block> coliseu = new ArrayList<>();
	public ArrayList<Block> feast = new ArrayList<>();
	public ArrayList<Block> winner = new ArrayList<>();
	public ArrayList<Block> border = new ArrayList<>();
	
	public ArrayList<Player> antabuser = new ArrayList<>();

	private static Manager instance = new Manager();
	public static Manager getInstance() {
		return instance;
	}
	
	public void sendWarn(Player player, String message) { 
		for (Player all : Bukkit.getOnlinePlayers()) { 
			if (all.hasPermission(Permission.getInstance().ADMIN)) { 
				if (!all.getName().equals(player.getName())) { 
					if (!antabuser.contains(all)) { 
						all.sendMessage("§7[" + message + "§7]");
					}
				}
			}
		}
	}

	public void initialiseAutobroadcast() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			public void run() {
				String[] messages = { "", "", "", "", "", ""};
				Bukkit.broadcastMessage(messages[new java.util.Random().nextInt(messages.length)]);
			}
		}, 20L, 1300L);
	}

	public void startingTime() {
		Countdown.getCountDown().startingTimer();
		if (BukkitMain.getPlugin().getConfig().getBoolean("Coliseu")) {
			try {
				Schematic s = Schematic.getInstance()
						.carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "coliseu.schematic"));
				Schematic.getInstance().generateSchematic(BukkitMain.getPlugin().getServer().getWorlds().get(0),
						new Location(BukkitMain.getPlugin().getServer().getWorlds().get(0), -27.5D, 200.0D, -27.5D), s,
						coliseu);
			} catch (IOException | DataException e) {
			}
		}
		try {
			//Schematic.getInstance().generateBord(border);
		} catch (Exception e) {
		}
		Bukkit.getWorlds().get(0).setTime(0L);
	}

	public void invincibilityTime() {
		BukkitMain.getPlugin().registerHabilitys();
		Countdown.getCountDown().invincibilityTimer();
		if (BukkitMain.getPlugin().getConfig().getBoolean("Coliseu")) {
			for (Iterator<?> i = coliseu.iterator(); 
				 i.hasNext();) {
				Block b = (Block) i.next();
				for (Integer id : BukkitMain.getPlugin().getConfig().getIntegerList("ColiseuBlocks")) {
					if (b.getType() == Material.getMaterial(id)) {
						b.setType(Material.AIR);
					}
				}
			}
		}
		for (Player p : PlayerAPI.getInstance().getPlayers()) {
			checkKit(p);
		}
		Bukkit.getWorlds().get(0).setTime(0L);
	}

	public void gameTime() {
		Schematic.getInstance().removeColiseu();
		Countdown.getCountDown().cancel();
		Countdown.getCountDown().gameTimer();
	}

	public void endTimer() {
		Countdown.getCountDown().cancel();
		Countdown.getCountDown().endTimer();
		BukkitMain.state = StateEnum.END;
	}

	public void checkWin() {
		PlayerAPI.getInstance().checkWin();
	}

	public void setupConfigs() {
		File file = new File(BukkitMain.getPlugin().getDataFolder(), "coliseu.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("coliseu.schematic"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "feast.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("feast.schematic"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "minifeast.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("minifeast.schematic"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "bolo.png");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("bolo.png"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "arenapequena.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("arenapequena.schematic"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "arenamedia.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("arenamedia.schematic"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "arenagrande.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("arenagrande.schematic"), file);
		}
		file = new File(BukkitMain.getPlugin().getDataFolder(), "arenaextrema.schematic");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			copiarConfig(BukkitMain.getPlugin().getResource("arenaextrema.schematic"), file);
		}
	}

	protected void copiarConfig(InputStream i, File config) {
		try {
			OutputStream out = new FileOutputStream(config);
			byte[] buf = new byte[710];
			int len;
			while ((len = i.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			i.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ArrayList<Location> locationsfirework = new ArrayList<>();

	public void firework(Player p) {
		Location loc = p.getLocation();
		final Firework f = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fm = f.getFireworkMeta();
		FireworkEffect.Type t = FireworkEffect.Type.BALL_LARGE;
		Color c1 = Color.ORANGE;
		Color c2 = Color.ORANGE;
		Color c3 = Color.ORANGE;
		FireworkEffect effect = FireworkEffect.builder().flicker(new Random().nextBoolean()).withColor(c1).withColor(c2)
				.withFade(c3).with(t).trail(new Random().nextBoolean()).build();
		fm.addEffect(effect);
		fm.setPower(0);
		f.setFireworkMeta(fm);

		new BukkitRunnable() {

			@Override
			public void run() {
				f.detonate();
			}
		}.runTaskLater(BukkitMain.getPlugin(), 4);
	}

	public void checkKit(Player p) {
		p.closeInventory();
		p.setAllowFlight(false);
		if (!p.getInventory().contains(Material.COMPASS)) {
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.getInventory().addItem(new ItemStack(Material.COMPASS));
		}
		if (KitAPI.getInstance().hasKit(p, "Fireman")) {
			p.getInventory().addItem(itemKit(Material.WATER_BUCKET, "§aFireman"));
		} else if (KitAPI.getInstance().hasKit(p, "Fisherman")) {
			p.getInventory().addItem(itemKit(Material.FISHING_ROD, "§aFisherman"));
		} else if (KitAPI.getInstance().hasKit(p, "Flash")) {
			p.getInventory().addItem(itemKit(Material.REDSTONE_TORCH_ON, "§aFlash"));
		} else if (KitAPI.getInstance().hasKit(p, "Blink")) {
			p.getInventory().addItem(itemKit(Material.NETHER_STAR, "§aBlink"));
		} else if (KitAPI.getInstance().hasKit(p, "Grappler")) {
			p.getInventory().addItem(itemKit(Material.LEASH, "§aGrappler"));
		} else if (KitAPI.getInstance().hasKit(p, "Launcher")) {
			p.getInventory().addItem(itemKit(Material.SPONGE, "§aLauncher", 15));
		} else if (KitAPI.getInstance().hasKit(p, "Lumberjack")) {
			p.getInventory().addItem(itemKit(Material.WOOD_AXE, "§aLumberjack"));
		} else if (KitAPI.getInstance().hasKit(p, "Endermage")) {
			p.getInventory().addItem(itemKit(Material.PORTAL, "§aEndermage"));
		} else if (KitAPI.getInstance().hasKit(p, "Phantom")) {
			p.getInventory().addItem(itemKit(Material.FEATHER, "§aPhantom"));
		} else if (KitAPI.getInstance().hasKit(p, "Deshfire")) {
			p.getInventory().addItem(itemKit(Material.REDSTONE_BLOCK, "§aDeshfire"));
		} else if (KitAPI.getInstance().hasKit(p, "Sonic")) {
			p.getInventory().addItem(itemKit(Material.LAPIS_BLOCK, "§aSonic"));
		} else if (KitAPI.getInstance().hasKit(p, "Gladiator")) {
			p.getInventory().addItem(itemKit(Material.IRON_FENCE, "§aGladiator"));
		} else if (KitAPI.getInstance().hasKit(p, "Specialist")) {
			p.getInventory().addItem(itemKit(Material.BOOK, "§aSpecialist"));
		} else if (KitAPI.getInstance().hasKit(p, "Vacuum")) {
			p.getInventory().addItem(itemKit(Material.EYE_OF_ENDER, "§aVacuum"));
		} else if (KitAPI.getInstance().hasKit(p, "Thor")) {
			p.getInventory().addItem(itemKit(Material.WOOD_AXE, "§aThor"));
		} else if (KitAPI.getInstance().hasKit(p, "Monk")) {
			p.getInventory().addItem(itemKit(Material.BLAZE_ROD, "§aMonk"));
		} else if (KitAPI.getInstance().hasKit(p, "Archer")) {
			p.getInventory().addItem(itemKit(Material.BOW, "§aArcher", 1, Enchantment.ARROW_DAMAGE, 1));
			p.getInventory().addItem(itemKit(Material.ARROW, "§aArcher", 25));
		} else if (KitAPI.getInstance().hasKit(p, "Miner")) {
			p.getInventory().addItem(itemKit(Material.STONE_PICKAXE, "§aMiner", 1, Enchantment.DURABILITY, 1));
		} else if (KitAPI.getInstance().hasKit(p, "Kangaroo")) {
			p.getInventory().addItem(itemKit(Material.FIREWORK, "§aKangaroo"));
		} else if (KitAPI.getInstance().hasKit(p, "Jackhammer")) {
			p.getInventory().addItem(itemKit(Material.STONE_AXE, "§aJachammer"));
		} else if (KitAPI.getInstance().hasKit(p, "Demoman")) {
			p.getInventory().addItem(itemKit(Material.STONE_PLATE, "§aDemoman", 6));
			p.getInventory().addItem(itemKit(Material.GRAVEL, "§aDemoman", 6));
		} else if (KitAPI.getInstance().hasKit(p, "Timelord")) {
			p.getInventory().addItem(itemKit(Material.WATCH, "§aTimelord"));
		} else if (KitAPI.getInstance().hasKit(p, "Forger")) {
			p.getInventory().addItem(itemKit(Material.COAL, "§aForger", 3));
		} else if (KitAPI.getInstance().hasKit(p, "Urgal")) {
			p.getInventory().addItem(itemKit(Material.getMaterial(373), "§aUrgal", 3, 8201));
		} else if (KitAPI.getInstance().hasKit(p, "Scout")) {
			p.getInventory().addItem(itemKit(Material.getMaterial(373), "§aScout", 2, 8194));
		} else if (KitAPI.getInstance().hasKit2(p, "Grandpa")) {
			p.getInventory().addItem(itemKit(Material.STICK, "§aGrandpa", 1, Enchantment.KNOCKBACK, 2));
		}
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(p, "Fireman")) {
				p.getInventory().addItem(itemKit(Material.WATER_BUCKET, "§aFireman"));
			} else if (KitAPI.getInstance().hasKit2(p, "Fisherman")) {
				p.getInventory().addItem(itemKit(Material.FISHING_ROD, "§aFisherman"));
			} else if (KitAPI.getInstance().hasKit2(p, "Flash")) {
				p.getInventory().addItem(itemKit(Material.REDSTONE_TORCH_ON, "§aFlash"));
			} else if (KitAPI.getInstance().hasKit2(p, "Blink")) {
				p.getInventory().addItem(itemKit(Material.NETHER_STAR, "§aBlink"));
			} else if (KitAPI.getInstance().hasKit2(p, "Grappler")) {
				p.getInventory().addItem(itemKit(Material.LEASH, "§aGrappler"));
			} else if (KitAPI.getInstance().hasKit2(p, "Launcher")) {
				p.getInventory().addItem(itemKit(Material.SPONGE, "§aLauncher", 15));
			} else if (KitAPI.getInstance().hasKit2(p, "Lumberjack")) {
				p.getInventory().addItem(itemKit(Material.WOOD_AXE, "§aLumberjack"));
			} else if (KitAPI.getInstance().hasKit2(p, "Endermage")) {
				p.getInventory().addItem(itemKit(Material.PORTAL, "§aEndermage"));
			} else if (KitAPI.getInstance().hasKit2(p, "Phantom")) {
				p.getInventory().addItem(itemKit(Material.FEATHER, "§aPhantom"));
			} else if (KitAPI.getInstance().hasKit2(p, "Deshfire")) {
				p.getInventory().addItem(itemKit(Material.REDSTONE_BLOCK, "§aDeshfire"));
			} else if (KitAPI.getInstance().hasKit2(p, "Sonic")) {
				p.getInventory().addItem(itemKit(Material.LAPIS_BLOCK, "§aSonic"));
			} else if (KitAPI.getInstance().hasKit2(p, "Gladiator")) {
				p.getInventory().addItem(itemKit(Material.IRON_FENCE, "§aGladiator"));
			} else if (KitAPI.getInstance().hasKit2(p, "Specialist")) {
				p.getInventory().addItem(itemKit(Material.BOOK, "§aSpecialist"));
			} else if (KitAPI.getInstance().hasKit2(p, "Vacuum")) {
				p.getInventory().addItem(itemKit(Material.EYE_OF_ENDER, "§aVacuum"));
			} else if (KitAPI.getInstance().hasKit2(p, "Thor")) {
				p.getInventory().addItem(itemKit(Material.WOOD_AXE, "§aThor"));
			} else if (KitAPI.getInstance().hasKit2(p, "Monk")) {
				p.getInventory().addItem(itemKit(Material.BLAZE_ROD, "§aMonk"));
			} else if (KitAPI.getInstance().hasKit2(p, "Archer")) {
				p.getInventory().addItem(itemKit(Material.BOW, "§aArcher", 1, Enchantment.ARROW_DAMAGE, 1));
				p.getInventory().addItem(itemKit(Material.ARROW, "§aArcher", 25));
			} else if (KitAPI.getInstance().hasKit2(p, "Miner")) {
				p.getInventory().addItem(itemKit(Material.STONE_PICKAXE, "§aMiner", 1, Enchantment.DURABILITY, 1));
			} else if (KitAPI.getInstance().hasKit2(p, "Kangaroo")) {
				p.getInventory().addItem(itemKit(Material.FIREWORK, "§aKangaroo"));
			} else if (KitAPI.getInstance().hasKit2(p, "Jackhammer")) {
				p.getInventory().addItem(itemKit(Material.STONE_AXE, "§aJachammer"));
			} else if (KitAPI.getInstance().hasKit2(p, "Demoman")) {
				p.getInventory().addItem(itemKit(Material.STONE_PLATE, "§aDemoman", 6));
				p.getInventory().addItem(itemKit(Material.GRAVEL, "§aDemoman", 6));
			} else if (KitAPI.getInstance().hasKit2(p, "Timelord")) {
				p.getInventory().addItem(itemKit(Material.WATCH, "§aTimelord"));
			} else if (KitAPI.getInstance().hasKit2(p, "Forger")) {
				p.getInventory().addItem(itemKit(Material.COAL, "§aForger", 3));
			} else if (KitAPI.getInstance().hasKit2(p, "Urgal")) {
				p.getInventory().addItem(itemKit(Material.getMaterial(373), "§aUrgal", 3, 8201));
			} else if (KitAPI.getInstance().hasKit2(p, "Scout")) {
				p.getInventory().addItem(itemKit(Material.getMaterial(373), "§aScout", 2, 8194));
			} else if (KitAPI.getInstance().hasKit2(p, "Grandpa")) {
				p.getInventory().addItem(itemKit(Material.STICK, "§aGrandpa", 1, Enchantment.KNOCKBACK, 2));
			}
		}
		p.updateInventory();
	}

	public void initialiseRecipes() {
		ItemStack a = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("Ensopado de chocolate");
		a.setItemMeta(am);
		
		ItemStack b = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta bm = b.getItemMeta();
		bm.setDisplayName("Ensopado de cacto");
		b.setItemMeta(bm);
		
		ItemStack b1 = new ItemStack(Material.COMPASS);
		ItemMeta bm1 = b.getItemMeta();
		bm1.setDisplayName("§3§lBussola");
		b1.setItemMeta(bm1);

		ItemStack c = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("Ensopado de flores");
		c.setItemMeta(cm);

		ItemStack d = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta dm = d.getItemMeta();
		dm.setDisplayName("Ensopado de abobora");
		d.setItemMeta(dm);

		ItemStack e = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName("Ensopado de melancia");
		e.setItemMeta(em);

		ItemStack f = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta fm = f.getItemMeta();
		fm.setDisplayName("Ensopado de nether");
		f.setItemMeta(fm);

		ShapelessRecipe a1 = new ShapelessRecipe(a);
		a1.addIngredient(1, Material.INK_SACK, 3);
		a1.addIngredient(1, Material.BOWL);

		ShapelessRecipe a22 = new ShapelessRecipe(b);
		a22.addIngredient(1, Material.CACTUS);
		a22.addIngredient(1, Material.BOWL);

		ShapelessRecipe a3 = new ShapelessRecipe(c);
		a3.addIngredient(1, Material.RED_ROSE);
		a3.addIngredient(1, Material.YELLOW_FLOWER);
		a3.addIngredient(1, Material.BOWL);

		ShapelessRecipe a4 = new ShapelessRecipe(d);
		a4.addIngredient(1, Material.PUMPKIN_SEEDS);
		a4.addIngredient(1, Material.BOWL);

		ShapelessRecipe a5 = new ShapelessRecipe(e);
		a5.addIngredient(1, Material.MELON_SEEDS);
		a5.addIngredient(1, Material.BOWL);

		ShapelessRecipe a6 = new ShapelessRecipe(f);
		a6.addIngredient(1, Material.NETHER_STALK);
		a6.addIngredient(1, Material.BOWL);

		Bukkit.getServer().addRecipe(a1);
		Bukkit.getServer().addRecipe(a22);
		Bukkit.getServer().addRecipe(a3);
		Bukkit.getServer().addRecipe(a4);
		Bukkit.getServer().addRecipe(a5);
		Bukkit.getServer().addRecipe(a6);
	}

	protected ItemStack itemKit(Material material, String display) {
		ItemStack stack = new ItemStack(material);
		ItemMeta stackmeta = stack.getItemMeta();
		stackmeta.setDisplayName(display);
		stack.setItemMeta(stackmeta);
		return stack;
	}

	protected ItemStack itemKit(Material material, String display, int amount, int id) {
		ItemStack stack = new ItemStack(material, amount, (byte) id);
		ItemMeta stackmeta = stack.getItemMeta();
		stackmeta.setDisplayName(display);
		stack.setItemMeta(stackmeta);
		return stack;
	}

	protected ItemStack itemKit(Material material, String display, int amount) {
		ItemStack stack = new ItemStack(material);
		ItemMeta stackmeta = stack.getItemMeta();
		stackmeta.setDisplayName(display);
		stack.setItemMeta(stackmeta);
		stack.setAmount(amount);
		return stack;
	}

	protected ItemStack itemKit(Material material, String display, int amount, Enchantment enchant, int level) {
		ItemStack stack = new ItemStack(material);
		ItemMeta stackmeta = stack.getItemMeta();
		stackmeta.addEnchant(enchant, level, true);
		stackmeta.setDisplayName(display);
		stack.setItemMeta(stackmeta);
		stack.setAmount(amount);
		return stack;
	}

	public void deleteFile(File file) {
		if (file.isDirectory()) {
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				deleteFile(new File(file, list[i]));
			}
		}
		file.delete();
	}

	public void setSpectador(Player player) {
		PlayerAPI.getInstance().removePlayer(player);
		PlayerAPI.getInstance().addEspectator(player);
		KitAPI.getInstance().removeKit(player);
		KitAPI.getInstance().removeKit2(player);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setGameMode(GameMode.SURVIVAL);
		player.setMaxHealth(20.0D);
		player.setFoodLevel(20);
		player.setHealth(20.0D);
		player.setAllowFlight(true);
		player.getInventory().addItem(itemKit(Material.CHEST, "§aJogadores"));
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (!PlayerAPI.getInstance().hasEspectator(all)) {
				all.hidePlayer(player);
			}
		}
	}
}
