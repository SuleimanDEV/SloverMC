package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cForceKitCMD implements CommandExecutor {
	
	String[] kits = {"Achilles", "Ajnin", "Boxer", "Archer", "Anchor", "Deshfire", "Sonic", "Fireman", "Fisherman", "Flash", "Blink", "Gladiator", "Hulk", "Monk", "Kangaroo", "Jackhammer", "Launcher", "Grappler", "Lumberjack", "Endermage", "Madmam", "Ninja", "Magma", "Miner", "Phantom", "Stomper", "Poseidon", "Viking", "Specialist", "Snail", "Vacuum", "Weakhand", "Tank", "Viper", "Thor"};
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("forcekit")) {
			if (player.hasPermission(Permission.getInstance().FORCEKIT)) { 
				if (args.length == 0 || args.length == 1 || args.length == 2) { 
					player.sendMessage("§9§lFORCEKIT §fUse /forcekit (pri ou sec) (jogador) (kit)");
					return true;
				}
				if (args[0].equalsIgnoreCase("pri")) {
					Player target = Bukkit.getPlayerExact(args[1]);
					if (target != null) { 
						if (PlayerAPI.getInstance().hasPlayer(target)) { 
							for (String kit : kits) { 
								if (args[2].equalsIgnoreCase(kit)) { 
									KitAPI.getInstance().setKit(target, kit);
									player.sendMessage("§3§lFORCEKIT §fVocê setou o kit§b " + kit + " §fpara " + target.getName() + " como primário!");
									target.sendMessage("§3§lFORCEKIT §f" + player.getName() + " forçou o kit§b " + kit + " §fcomo primário para você!");
								}
							}
						} else { 
							player.sendMessage("§c§lERRO §fJogador offline ou inexistente.");
						}
					} else { 
						player.sendMessage("§c§lERRO §fJogador offline ou inexistente.");
					}
				}
				if (args[0].equalsIgnoreCase("sec")) { 
					Player target = Bukkit.getPlayerExact(args[1]);
					if (target != null) { 
						if (PlayerAPI.getInstance().hasPlayer(target)) { 
							for (String kit : kits) { 
								if (args[2].equalsIgnoreCase(kit)) { 
									KitAPI.getInstance().setKit2(target, kit);
									player.sendMessage("§3§lFORCEKIT §fVocê setou o kit§b " + kit + " §fpara " + target.getName() + " como secundário!");
									target.sendMessage("§3§lFORCEKIT §f" + player.getName() + " forçou o kit§b " + kit + " §fcomo secundário para você!");
								}
							}
						} else { 
							player.sendMessage("§c§lERRO §fJogador offline ou inexistente.");
						}
					} else { 
						player.sendMessage("§c§lERRO §fJogador offline ou inexistente.");
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		else if (label.equalsIgnoreCase("forcekitall")) { 
			if (player.hasPermission(Permission.getInstance().FORCEKIT)) { 
				if (args.length == 0) { 
					player.sendMessage("§3§lFORCEKIT §fUse /forcekitall (pri ou sec) (kit).");
					return true;
				}
				if (args[0].equalsIgnoreCase("pri")) { 
					for (String kit : kits) { 
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (args[1].equalsIgnoreCase(kit)) { 
								if (PlayerAPI.getInstance().hasPlayer(all)) { 
									KitAPI.getInstance().setKit(all, kit);
								}
							}
						}
	 				}
					Bukkit.broadcastMessage("§3§lFORCEKIT §f" + player.getName() + " forçado um kit como primário para todos!");
				}
				if (args[0].equalsIgnoreCase("sec")) { 
					for (String kit : kits) { 
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (args[1].equalsIgnoreCase(kit)) { 
								if (PlayerAPI.getInstance().hasPlayer(all)) { 
									KitAPI.getInstance().setKit2(all, kit);
								}
							}
						}
	 				}
					Bukkit.broadcastMessage("§3§lFORCEKIT §f" + player.getName() + " forçado um kit como secundário para todos!");
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
