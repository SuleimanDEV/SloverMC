package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.Permission;

public class cKitReadyCMD implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("kitready")) {
			if (player.hasPermission(Permission.getInstance().KITREADY)) { 
				if (args.length == 0) { 
					player.sendMessage("§6§lKITALREADY §fUtilize /kitready <arena/gladiator>.");
					return true;
				}
				if (args[0].equalsIgnoreCase("arena")) { 
					if (BukkitMain.state == StateEnum.STARTING || BukkitMain.state == StateEnum.END) return true;
					for (Player all : Bukkit.getOnlinePlayers()) { 
						all.getInventory().clear();
						if (PlayerAPI.getInstance().hasPlayer(all)) { 
							setArena(all);
						}
					}
					Bukkit.broadcastMessage("§6§lKITALREADY §fO kit pronto Arena foi setado para todos!");
				}
				if (args[0].equalsIgnoreCase("gladiator")) { 
					if (BukkitMain.state == StateEnum.STARTING || BukkitMain.state == StateEnum.END) return true;
					for (Player all : Bukkit.getOnlinePlayers()) { 
						all.getInventory().clear();
						if (PlayerAPI.getInstance().hasPlayer(all)) { 
							setGladiator(all);
						}
					}
					Bukkit.broadcastMessage("§6§lKITALREADY §fO kit pronto Gladiator foi setado para todos!");
				}
			} else { 
				player.sendMessage("§cSem permissão!");
			}
		}
		return false;
	}
	
	public void setGladiator(Player all) { 
		all.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		all.getInventory().setItem(1, new ItemStack(Material.IRON_FENCE));
		all.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		all.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		all.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		all.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		all.getInventory().setItem(8, new ItemStack(Material.COMPASS));
		all.getInventory().setItem(9, new ItemStack(Material.IRON_HELMET));
		all.getInventory().setItem(10, new ItemStack(Material.IRON_CHESTPLATE));
		all.getInventory().setItem(11, new ItemStack(Material.IRON_LEGGINGS));
		all.getInventory().setItem(12, new ItemStack(Material.IRON_BOOTS));
		all.getInventory().setItem(13, new ItemStack(Material.BOWL, 64));
		all.getInventory().setItem(14, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(15, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(16, new ItemStack(Material.LAVA_BUCKET));
		all.getInventory().setItem(17, new ItemStack(Material.WATER_BUCKET));
		all.getInventory().setItem(18, new ItemStack(Material.IRON_HELMET));
		all.getInventory().setItem(19, new ItemStack(Material.IRON_CHESTPLATE));
		all.getInventory().setItem(20, new ItemStack(Material.IRON_LEGGINGS));
		all.getInventory().setItem(21, new ItemStack(Material.IRON_BOOTS));
		all.getInventory().setItem(22, new ItemStack(Material.BOWL, 64));
		all.getInventory().setItem(23, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(24, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(25, new ItemStack(Material.LAVA_BUCKET));
		all.getInventory().setItem(26, new ItemStack(Material.MILK_BUCKET));
		all.getInventory().setItem(27, new ItemStack(Material.WOOD_PICKAXE));
		all.getInventory().setItem(28, new ItemStack(Material.WOOD_AXE));
		all.getInventory().setItem(29, new ItemStack(Material.COBBLE_WALL, 64));
		all.getInventory().setItem(30, new ItemStack(Material.WOOD, 32));
	}
	
	public void setArena(Player all) { 
		all.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		all.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		all.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		all.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		all.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		all.getInventory().setItem(8, new ItemStack(Material.COMPASS));
		all.getInventory().setItem(9, new ItemStack(Material.IRON_HELMET));
		all.getInventory().setItem(10, new ItemStack(Material.IRON_CHESTPLATE));
		all.getInventory().setItem(11, new ItemStack(Material.IRON_LEGGINGS));
		all.getInventory().setItem(12, new ItemStack(Material.IRON_BOOTS));
		all.getInventory().setItem(13, new ItemStack(Material.BOWL, 64));
		all.getInventory().setItem(14, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(15, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(16, new ItemStack(Material.LAVA_BUCKET));
		all.getInventory().setItem(17, new ItemStack(Material.WATER_BUCKET));
		all.getInventory().setItem(18, new ItemStack(Material.IRON_HELMET));
		all.getInventory().setItem(19, new ItemStack(Material.IRON_CHESTPLATE));
		all.getInventory().setItem(20, new ItemStack(Material.IRON_LEGGINGS));
		all.getInventory().setItem(21, new ItemStack(Material.IRON_BOOTS));
		all.getInventory().setItem(22, new ItemStack(Material.BOWL, 64));
		all.getInventory().setItem(23, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(24, new ItemStack(Material.CACTUS, 64));
		all.getInventory().setItem(25, new ItemStack(Material.LAVA_BUCKET));
	}
}
