package br.com.slovermc.hg.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cEspectarCMD implements CommandExecutor {
	
	ArrayList<Player> hasOff = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("specs")) { 
			if (player.hasPermission(Permission.getInstance().ADMIN)) { 
				if (args.length == 0) { 
					player.sendMessage("§b§lSPECTAR §fUtilize /specs <on/off>.");
					return true;
				}
				if (args[0].equalsIgnoreCase("on")) { 
					if (hasOff.contains(player)) { 
						player.sendMessage("§b§lESPECTAR §fOs espectadores já estão ativados para você!");
					} else { 
						player.sendMessage("§b§lESPECTAR §fVocê desativou os espectadores para si mesmo!");
						hasOff.add(player);
						for (Player all : Bukkit.getOnlinePlayers()) { 
							for (Player specs : PlayerAPI.getInstance().getAdmins()) { 
								all.hidePlayer(specs);
							}
						}
					}
				}
				if (args[0].equalsIgnoreCase("off")) { 
					if (!hasOff.contains(player)) { 
						player.sendMessage("§b§lESPECTAR §fOs espectadores já estão desativados para você!");
					} else { 
						player.sendMessage("§b§lESPECTAR §fVocê desativou os espectadores para si mesmo!");
						hasOff.add(player);
						for (Player all : Bukkit.getOnlinePlayers()) { 
							for (Player specs : PlayerAPI.getInstance().getAdmins()) { 
								all.showPlayer(specs);
							}
						}
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
