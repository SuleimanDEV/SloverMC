package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cDanoCMD implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("dano")) {
			if (player.hasPermission(Permission.getInstance().DANO)) { 
				if (args.length == 0) { 
					player.sendMessage("§cUtilize: /dano (on|off)");
					return true;
				}
				if (args[0].equalsIgnoreCase("on")) { 
					if (BukkitMain.getPlugin().dano) { 
						player.sendMessage("§4§lDANO §fO dano já está ativado");
					} else { 
						Bukkit.broadcastMessage("§4§lDANO §fO dano foi ativado!");
						BukkitMain.getPlugin().dano = true;
					}
				}
				if (args[0].equalsIgnoreCase("off")) { 
					if (!BukkitMain.getPlugin().dano) { 
						player.sendMessage("§4§lDANO §fO dano já está desativado!");
					} else { 
						Bukkit.broadcastMessage("§4§lDANO §fO dano foi desativado!");
						BukkitMain.getPlugin().dano = false;
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) { 
		if (!(e.getEntity() instanceof Player)) return;
		if (!BukkitMain.getPlugin().dano) { 
			e.setCancelled(true);
		}
	}
}
