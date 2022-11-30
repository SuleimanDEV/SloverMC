package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;

public final class cTagCMD implements CommandExecutor {

	public static boolean Console(CommandSender sender) {
		if (!(sender instanceof Player)) {
			return true;
		} else {
			return false;
		}
	}

	public static final String utilizando = "§9§lTAGS§f Você está §3§lUTILIZANDO§f a tag: ";
	public static final String noperm = "§9§lTAGS§f Você não tem a tag ";

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (Console(sender)) {
			sender.sendMessage("§9§lTAGS§f Console não pode usar este comando!");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("tag")) {
			if (args.length == 0) {
				BukkitMain.tag.checkTags(p);
				return true;
			}
			if (args.length == 1) {
				String tag = args[0];
				if (tag.equalsIgnoreCase("dono") || tag.equalsIgnoreCase("master") || tag.equalsIgnoreCase("owner")) {
					if (!p.hasPermission("tag.dono")) {
						p.sendMessage(noperm + "§4§lDONO§f!");
						return true;
					}
					BukkitMain.tag.setDono(p);
					p.sendMessage(utilizando + "§4§lDONO§f!");
					return true;
				} else if (tag.equalsIgnoreCase("programmer") || tag.equalsIgnoreCase("dev")
						|| tag.equalsIgnoreCase("coder") || tag.equalsIgnoreCase("developer")) {
					if (!p.hasPermission("tag.developer")) {
						p.sendMessage(noperm + "§3§lDEVELOPER§f!");
						return true;
					}
					BukkitMain.tag.setProgrammer(p);
					p.sendMessage(utilizando + "§3§lDEVELOPER§f!");
					return true;
				} else if (tag.equalsIgnoreCase("gerente")) {
					if (!p.hasPermission("tag.gerente")) {
						p.sendMessage(noperm + "§c§lGERENTE§f!");
						return true;
					}
					BukkitMain.tag.setGerente(p);
					p.sendMessage(utilizando + "§c§lGERENTE§f!");
					return true;
				} else if (tag.equalsIgnoreCase("diretor")) {
					if (!p.hasPermission("tag.diretor")) {
						p.sendMessage(noperm + "§4§lDIRETOR§f!");
						return true;
					}
					BukkitMain.tag.setDiretor(p);
					p.sendMessage(utilizando + "§4§lDIRETOR§f!");
					return true;
				} else if (tag.equalsIgnoreCase("designer")) {
					if (!p.hasPermission("tag.designer")) {
						p.sendMessage(noperm + "§2§lDESIGNER§f!");
						return true;
					}
					BukkitMain.tag.setDesigner(p);
					p.sendMessage(utilizando + "§2§lDESIGNER§f!");
					return true;
				} else if (tag.equalsIgnoreCase("admin") || tag.equalsIgnoreCase("administrador")) {
					if (!p.hasPermission("tag.admin")) {
						p.sendMessage(noperm + "§c§lADMIN§f!");
						return true;
					}
					BukkitMain.tag.setAdmin(p);
					p.sendMessage(utilizando + "§c§lADMIN§f!");
					return true;
				} else if (tag.equalsIgnoreCase("modgc") || tag.equalsIgnoreCase("gcdetector")) {
					if (!p.hasPermission("tag.modgc")) {
						p.sendMessage(noperm + "§5§lMODGC§f!");
						return true;
					}
					BukkitMain.tag.setModGc(p);
					p.sendMessage(utilizando + "§5§lMODGC§f!");
					return true;
				} else if (tag.equalsIgnoreCase("mod") || tag.equalsIgnoreCase("moderador")) {
					if (!p.hasPermission("tag.mod")) {
						p.sendMessage(noperm + "§5§lMOD§f!");
						return true;
					}
					BukkitMain.tag.setMod(p);
					p.sendMessage(utilizando + "§5§lMOD§f!");
					return true;
				} else if (tag.equalsIgnoreCase("trial") || tag.equalsIgnoreCase("trialmod")) {
					if (!p.hasPermission("tag.trial")) {
						p.sendMessage(noperm + "§d§lTRIAL§f!");
						return true;
					}
					BukkitMain.tag.setTrial(p);
					p.sendMessage(utilizando + "§d§lTRIAL§f!");
					return true;
				} else if (tag.equalsIgnoreCase("yt+") || tag.equalsIgnoreCase("youtuber+")
						|| tag.equalsIgnoreCase("youtuberplus") || tag.equalsIgnoreCase("ytplus")) {
					if (!p.hasPermission("tag.ytplus")) {
						p.sendMessage(noperm + "§3§lYOUTUBER+§f!");
						return true;
					}
					BukkitMain.tag.setYtPlus(p);
					p.sendMessage(utilizando + "§3§lYOUTUBER+§f!");
					return true;
				} else if (tag.equalsIgnoreCase("trialgc")) {
					if (!p.hasPermission("tag.trialgc")) {
						p.sendMessage(noperm + "§d§lTRIALGC§f!");
						return true;
					}
					BukkitMain.tag.setTrialGC(p);
					p.sendMessage(utilizando + "§d§lTRIALGC§f!");
					return true;
				} else if (tag.equalsIgnoreCase("builder") || tag.equalsIgnoreCase("construtor")) {
					if (!p.hasPermission("tag.builder")) {
						p.sendMessage(noperm + "§2§lBUILDER§f!");
						return true;
					}
					BukkitMain.tag.setBuilder(p);
					p.sendMessage(utilizando + "§2§lBUILDER§f!");
					return true;
				} else if (tag.equalsIgnoreCase("ajudante") || tag.equalsIgnoreCase("helper")) {
					if (!p.hasPermission("tag.ajudante")) {
						p.sendMessage(noperm + "§9§lAJUDANTE§f!");
						return true;
					}
					BukkitMain.tag.setAjudante(p);
					p.sendMessage(utilizando + "§9§lAJUDANTE§f!");
					return true;
				} else if (tag.equalsIgnoreCase("yt") || tag.equalsIgnoreCase("youtuber")) {
					if (!p.hasPermission("tag.yt")) {
						p.sendMessage(noperm + "§b§lYOUTUBER§f!");
						return true;
					}
					BukkitMain.tag.setYt(p);
					p.sendMessage(utilizando + "§b§lYOUTUBER§f!");
					return true;
				} else if (tag.equalsIgnoreCase("pro") || tag.equalsIgnoreCase("vippro")) {
					if (!p.hasPermission("tag.pro")) {
						p.sendMessage(noperm + "§6§lPRO§f!");
						return true;
					}
					BukkitMain.tag.setPro(p);
					p.sendMessage(utilizando + "§6§lPRO§f!");
					return true;
				} else if (tag.equalsIgnoreCase("light") || tag.equalsIgnoreCase("viplight")) {
					if (!p.hasPermission("tag.light")) {
						p.sendMessage(noperm + "§a§lLIGHT§f!");
						return true;
					}
					BukkitMain.tag.setLight(p);
					p.sendMessage(utilizando + "§a§lLIGHT§f!");
					return true;
				} else if (tag.equalsIgnoreCase("beta") || tag.equalsIgnoreCase("viplight")) {
					if (!p.hasPermission("tag.beta")) {
						p.sendMessage(noperm + "§1§lBETA§f!");
						return true;
					}
					BukkitMain.tag.setBeta(p);
					p.sendMessage(utilizando + "§1§lBETA§f!");
					return true;
				} else if (tag.equalsIgnoreCase("sapphire") || tag.equalsIgnoreCase("viplight")) {
					if (!p.hasPermission("tag.sapphire")) {
						p.sendMessage(noperm + "§3§lSAPPHIRE§f!");
						return true;
					}
					BukkitMain.tag.setSapphire(p);
					p.sendMessage(utilizando + "§3§lSAPPHIRE§f!");
					return true;
				} else if (tag.equalsIgnoreCase("elite") || tag.equalsIgnoreCase("viplight")) {
					if (!p.hasPermission("tag.elite")) {
						p.sendMessage(noperm + "§b§lELITE§f!");
						return true;
					}
					BukkitMain.tag.setElite(p);
					p.sendMessage(utilizando + "§b§lELITE§f!");
					return true;
				} else if (tag.equalsIgnoreCase("vip") || tag.equalsIgnoreCase("vipao")) {
					if (!p.hasPermission("tag.vip")) {
						p.sendMessage(noperm + "§a§lVIP§f!");
						return true;
					}
					BukkitMain.tag.setVip(p);
					p.sendMessage(utilizando + "§a§lVIP§f!");
					return true;
				} else if (tag.equalsIgnoreCase("membro") || tag.equalsIgnoreCase("normal")) {
					BukkitMain.tag.setMembro(p);
					p.sendMessage(utilizando + "§7§lMEMBRO§f!");
					return true;
				} else {
					p.sendMessage("§9§lTAGS§f Tag não existente!");
					return true;
				}
			}
			if (args.length > 1) {
				p.sendMessage("§9§lTAGS§f Utilize: /tag ou /tag <tag>");
				return true;
			}
		}
		return false;
	}
}