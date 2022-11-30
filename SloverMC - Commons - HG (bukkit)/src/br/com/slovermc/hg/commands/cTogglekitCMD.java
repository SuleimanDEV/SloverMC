package br.com.slovermc.hg.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.utils.Permission;

public class cTogglekitCMD implements CommandExecutor {
	
	public static HashMap<Player, String> hasSaveKit = new HashMap<>();
	public static HashMap<Player, String> hasSaveKit2 = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("togglekit")) {
			if (player.hasPermission(Permission.getInstance().TOGGLEKIT)) { 
				if (args.length == 0 || args.length == 1) { 
					player.sendMessage("§9§lTOGGLEKIT §fUse /togglekit (pri ou sec) (on ou off)");
					return true;
				}
				if (args[0].equalsIgnoreCase("pri")) { 
					if (args[1].equalsIgnoreCase("on")) { 
						if (BukkitMain.getPlugin().kit) {
							 player.sendMessage("§9§lTOGGLEKIT §fOs kits primários já estão habilitados.");
						} else { 
							for (Player all : Bukkit.getOnlinePlayers()) { 
								KitAPI.getInstance().setKit(all, hasSaveKit.get(all));
							}
							Bukkit.broadcastMessage("§9§lTOGGLEKIT §fOs kits primários foram ativados!");
							BukkitMain.getPlugin().kit = true;
						}
					}
					if (args[1].equalsIgnoreCase("off")) { 
						if (!BukkitMain.getPlugin().kit) {
							player.sendMessage("§9§lTOGGLEKIT §fOs kits primários já estão desativados.");
						} else { 
							for (Player all : Bukkit.getOnlinePlayers()) { 
								hasSaveKit.put(all, KitAPI.getInstance().getKit(all));
								KitAPI.getInstance().removeKit(all);
							}
							Bukkit.broadcastMessage("§9§lTOGGLEKIT §fOs kits primários foram desativados!");
							BukkitMain.getPlugin().kit = false;
						}
					}
				}
				if (args[0].equalsIgnoreCase("sec")) { 
					if (args[1].equalsIgnoreCase("on")) { 
						if (BukkitMain.getPlugin().kit2) {
							Bukkit.broadcastMessage("§9§lTOGGLEKIT §fOs kits secundários já estão ativados!");
						} else { 
							for (Player all : Bukkit.getOnlinePlayers()) { 
								KitAPI.getInstance().setKit2(all, hasSaveKit2.get(all));
							}
							Bukkit.broadcastMessage("§9§lTOGGLEKIT §fOs kits secundários foram ativados!");
							BukkitMain.getPlugin().kit2 = true;
						}
					}
					if (args[1].equalsIgnoreCase("off")) { 
						if (!BukkitMain.getPlugin().kit2) {
							Bukkit.broadcastMessage("§9§lTOGGLEKIT §fOs kits secundários já estão desativados!");
						} else { 
							for (Player all : Bukkit.getOnlinePlayers()) { 
								hasSaveKit2.put(all, KitAPI.getInstance().getKit2(all));
								KitAPI.getInstance().removeKit2(all);
							}
							Bukkit.broadcastMessage("§9§lTOGGLEKIT §fOs kits secundários foram desativados!");
							BukkitMain.getPlugin().kit2 = false;
						}
					}
				}
			} else { 
				player.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			}
		}
		return false;
	}
}
