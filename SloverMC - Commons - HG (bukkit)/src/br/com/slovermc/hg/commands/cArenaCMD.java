package br.com.slovermc.hg.commands;


import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;
import br.com.slovermc.hg.utils.Schematic;

public class cArenaCMD implements CommandExecutor {
	
	ArrayList<Block> arenapequena = new ArrayList<>();
	ArrayList<Block> arenamedia = new ArrayList<>();
	ArrayList<Block> arenagrande = new ArrayList<>();
	ArrayList<Block> arenaextrema = new ArrayList<>();
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("createarena")) {
			if (player.hasPermission(Permission.getInstance().ARENAS)) { 
				if (args.length == 0) { 
					player.sendMessage("§3§lARENA §fUtilize /createarena (pequena|media|grande|extrema).");
					return true;
				}
				if (args[0].equalsIgnoreCase("pequena")) { 
					try { 
						Schematic schematic = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "arenapequena.schematic"));
						Schematic.getInstance().generateSchematic(Bukkit.getWorlds().get(0), player.getLocation(), schematic, arenapequena);
						player.sendMessage("§3§lARENA §fVocê spawnou a arena pequena!");
						Manager.getInstance().sendWarn(player, "§3§lARENA §fO " + player.getName() + " criou a arena pequena!");
					} catch (Exception e) {
						player.sendMessage("§3§lARENA §fOcorreu um erro ao tentar spawnar a arena pequena!");
					}
				}
				if (args[0].equalsIgnoreCase("media")) { 
					try { 
						Schematic schematic = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "arenamedia.schematic"));
						Schematic.getInstance().generateSchematic(Bukkit.getWorlds().get(0), player.getLocation(), schematic, arenamedia);
						player.sendMessage("§3§lARENA §fVocê spawnou a arena média!");
						Manager.getInstance().sendWarn(player, "§3§lARENA §fO " + player.getName() + " criou a arena média!");
					} catch (Exception e) {
						player.sendMessage("§3§lARENA §fOcorreu um erro ao tentar spawnar a arena pequena!");
					}
				}
				if (args[0].equalsIgnoreCase("grande")) { 
					try { 
						Schematic schematic = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "arenagrande.schematic"));
						Schematic.getInstance().generateSchematic(Bukkit.getWorlds().get(0), player.getLocation(), schematic, arenagrande);
						player.sendMessage("§3§lARENA §fVocê spawnou a arena grande!");
						Manager.getInstance().sendWarn(player, "§3§lARENA §fO " + player.getName() + " criou a arena grande!");
					} catch (Exception e) {
						player.sendMessage("§3§lARENA §fOcorreu um erro ao tentar spawnar a arena pequena!");
					}
				}
				if (args[0].equalsIgnoreCase("extrema")) { 
					try { 
						Schematic schematic = Schematic.getInstance().carregarSchematics(new File(BukkitMain.getPlugin().getDataFolder(), "arenaextrema.schematic"));
						Schematic.getInstance().generateSchematic(Bukkit.getWorlds().get(0), player.getLocation(), schematic, arenaextrema);
						player.sendMessage("§3§lARENA §fVocê spawnou a arena extrema!");
						Manager.getInstance().sendWarn(player, "§3§lARENA §fO " + player.getName() + " criou a arena extrema!");
					} catch (Exception e) {
						player.sendMessage("§3§lARENA §fOcorreu um erro ao tentar spawnar a arena pequena!");
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
