package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cGiveItemCMD implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("giveitemkit")) {
			if (player.hasPermission(Permission.getInstance().GIVEITEMKIT)) {
				if (args.length == 0) { 
					player.sendMessage("§3§lKIT §fUse /giveitemkit (all ou jogador).");
					return true;
				}
				if (args[0].equalsIgnoreCase("all")) { 
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (PlayerAPI.getInstance().hasPlayer(all)) { 
							checkKit(all);
						}
					}
					Bukkit.broadcastMessage("§3§lGIVEKIT §fO " + player.getName() + " deu o item do kit de todos jogadores!");
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target != null) { 
					if (PlayerAPI.getInstance().hasPlayer(target)) { 
						checkKit(target);
						target.sendMessage("§3§lGIVEKIT §f" + player.getName() + " setou o item do seu kit!");
						player.sendMessage("§3§lGIVEKIT §fVocê setou o item do kit de " + target.getName() + "§f.");
					} else { 
						player.sendMessage(MessageStrings.notonline);
					}
				} else { 
					player.sendMessage(MessageStrings.notonline);
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public void checkKit(Player p) {
		String kitname = "§a" + KitAPI.getInstance().getKit(p);
		if (KitAPI.getInstance().hasKit(p, "Fireman")) {
			p.getInventory().addItem(itemKit(Material.WATER_BUCKET, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Fisherman")) {
			p.getInventory().addItem(itemKit(Material.FISHING_ROD, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Flash")) {
			p.getInventory().addItem(itemKit(Material.REDSTONE_TORCH_ON, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Blink")) {
			p.getInventory().addItem(itemKit(Material.NETHER_STAR, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Grappler")) {
			p.getInventory().addItem(itemKit(Material.LEASH, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Launcher")) {
			p.getInventory().addItem(itemKit(Material.SPONGE, kitname, 15));
		} else if (KitAPI.getInstance().hasKit(p, "Lumberjack")) {
			p.getInventory().addItem(itemKit(Material.WOOD_AXE, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Endermage")) {
			p.getInventory().addItem(itemKit(Material.PORTAL, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Phantom")) {
			p.getInventory().addItem(itemKit(Material.FEATHER, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Deshfire")) {
			p.getInventory().addItem(itemKit(Material.REDSTONE_BLOCK, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Sonic")) {
			p.getInventory().addItem(itemKit(Material.LAPIS_BLOCK, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Gladiator")) {
			p.getInventory().addItem(itemKit(Material.IRON_FENCE, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Specialist")) {
			p.getInventory().addItem(itemKit(Material.BOOK, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Vacuum")) {
			p.getInventory().addItem(itemKit(Material.EYE_OF_ENDER, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Thor")) {
			p.getInventory().addItem(itemKit(Material.WOOD_AXE, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Monk")) {
			p.getInventory().addItem(itemKit(Material.BLAZE_ROD, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Archer")) {
			p.getInventory().addItem(itemKit(Material.BOW, kitname));
			p.getInventory().addItem(itemKit(Material.ARROW, kitname, 25));
		} else if (KitAPI.getInstance().hasKit(p, "Miner")) {
			p.getInventory().addItem(itemKit(Material.STONE_PICKAXE, kitname, 1, Enchantment.DURABILITY, 1));
		} else if (KitAPI.getInstance().hasKit(p, "Kangaroo")) {
			p.getInventory().addItem(itemKit(Material.FIREWORK, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Jackhammer")) {
			p.getInventory().addItem(itemKit(Material.STONE_AXE, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Demoman")) {
			p.getInventory().addItem(itemKit(Material.STONE_PLATE, kitname, 6));
			p.getInventory().addItem(itemKit(Material.GRAVEL, kitname, 6));
		} else if (KitAPI.getInstance().hasKit(p, "Timelord")) {
			p.getInventory().addItem(itemKit(Material.WATCH, kitname));
		} else if (KitAPI.getInstance().hasKit(p, "Forger")) {
			p.getInventory().addItem(itemKit(Material.COAL, kitname, 3));
		} else if (KitAPI.getInstance().hasKit(p, "Urgal")) {
			p.getInventory().addItem(itemKit(Material.getMaterial(373), kitname, 3, 8201));
		} else if (KitAPI.getInstance().hasKit(p, "Scout")) {
			p.getInventory().addItem(itemKit(Material.getMaterial(373), kitname, 2, 8194));
		} else if (KitAPI.getInstance().hasKit2(p, "Grandpa")) {
			p.getInventory().addItem(itemKit(Material.STICK, kitname, 1, Enchantment.KNOCKBACK, 2));
		}
		if (BukkitMain.getPlugin().duoKit()) {
			String kitname2 = "§a" + KitAPI.getInstance().getKit2(p);
			if (KitAPI.getInstance().hasKit2(p, "Fireman")) {
				p.getInventory().addItem(itemKit(Material.WATER_BUCKET, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Fisherman")) {
				p.getInventory().addItem(itemKit(Material.FISHING_ROD, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Flash")) {
				p.getInventory().addItem(itemKit(Material.REDSTONE_TORCH_ON, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Blink")) {
				p.getInventory().addItem(itemKit(Material.NETHER_STAR, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Grappler")) {
				p.getInventory().addItem(itemKit(Material.LEASH, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Launcher")) {
				p.getInventory().addItem(itemKit(Material.SPONGE, kitname2, 15));
			} else if (KitAPI.getInstance().hasKit2(p, "Lumberjack")) {
				p.getInventory().addItem(itemKit(Material.WOOD_AXE, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Endermage")) {
				p.getInventory().addItem(itemKit(Material.PORTAL, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Phantom")) {
				p.getInventory().addItem(itemKit(Material.FEATHER, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Deshfire")) {
				p.getInventory().addItem(itemKit(Material.REDSTONE_BLOCK, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Sonic")) {
				p.getInventory().addItem(itemKit(Material.LAPIS_BLOCK, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Gladiator")) {
				p.getInventory().addItem(itemKit(Material.IRON_FENCE, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Specialist")) {
				p.getInventory().addItem(itemKit(Material.BOOK, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Vacuum")) {
				p.getInventory().addItem(itemKit(Material.EYE_OF_ENDER, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Thor")) {
				p.getInventory().addItem(itemKit(Material.WOOD_AXE, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Monk")) {
				p.getInventory().addItem(itemKit(Material.BLAZE_ROD, kitname2));
			} else if (KitAPI.getInstance().hasKit2(p, "Archer")) {
				p.getInventory().addItem(itemKit(Material.BOW, kitname));
				p.getInventory().addItem(itemKit(Material.ARROW, kitname, 25));
			} else if (KitAPI.getInstance().hasKit2(p, "Miner")) {
				p.getInventory().addItem(itemKit(Material.STONE_PICKAXE, kitname));
			} else if (KitAPI.getInstance().hasKit2(p, "Kangaroo")) {
				p.getInventory().addItem(itemKit(Material.FIREWORK, kitname));
			} else if (KitAPI.getInstance().hasKit2(p, "Jackhammer")) {
				p.getInventory().addItem(itemKit(Material.STONE_AXE, kitname));
			} else if (KitAPI.getInstance().hasKit2(p, "Demoman")) {
				p.getInventory().addItem(itemKit(Material.STONE_PLATE, kitname, 6));
				p.getInventory().addItem(itemKit(Material.GRAVEL, kitname, 6));
			} else if (KitAPI.getInstance().hasKit2(p, "Timelord")) {
				p.getInventory().addItem(itemKit(Material.WATCH, kitname));
			} else if (KitAPI.getInstance().hasKit2(p, "Forger")) {
				p.getInventory().addItem(itemKit(Material.COAL, kitname, 3));
			} else if (KitAPI.getInstance().hasKit2(p, "Urgal")) {
				p.getInventory().addItem(itemKit(Material.getMaterial(373), kitname, 3, 8201));
			} else if (KitAPI.getInstance().hasKit2(p, "Scout")) {
				p.getInventory().addItem(itemKit(Material.getMaterial(373), kitname, 2, 8194));
			} else if (KitAPI.getInstance().hasKit2(p, "Grandpa")) {
				p.getInventory().addItem(itemKit(Material.STICK, kitname, 1, Enchantment.KNOCKBACK, 2));
			}
		}
		p.updateInventory();
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
}
