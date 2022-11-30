package br.com.slovermc.ycommons.api.permission;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GroupAPI {

	public static String getGroupColor(ProxiedPlayer lp) {
		if (lp.hasPermission("tag.dono")) {
			return "§4§lDONO §4";
		} else {
			if (lp.hasPermission("tag.gerente")) {
				return "§c§lGERENTE §c";
			} else {
				if (lp.hasPermission("tag.diretor")) {
					return "§4§lDIRETOR §4";
				} else {
					if (lp.hasPermission("tag.admin")) {
						return "§c§lADMIN §c";
					} else {
						if (lp.hasPermission("tag.mod")) {
							return "§5§lMOD §5";
						} else {
							if (lp.hasPermission("tag.modgc")) {
								return "§5§lMODGC §5";
							} else {
								if (lp.hasPermission("tag.trial")) {
									return "§5§lTRIAL §5";
								} else {
									if (lp.hasPermission("tag.ytplus")) {
										return "§3§lYOUTUBER+ §3";
									} else {
										if (lp.hasPermission("tag.developer")) {
											return "§3§lDEVELOPER §3";
										} else {
											if (lp.hasPermission("tag.ajudante")) {
												return "§9§lAJUDANTE §9";
											} else {
												return "§7";
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static String getGroupLaterColor(String group) {
		if (group.equalsIgnoreCase("membro")) {
			return "§7";
		} else if (group.equalsIgnoreCase("gamer")) {
			return "§a";
		} else if (group.equalsIgnoreCase("designer")) {
			return "§2";
		} else if (group.equalsIgnoreCase("mvp")) {
			return "§9";
		} else if (group.equalsIgnoreCase("spro")) {
			return "§e";
		} else if (group.equalsIgnoreCase("pro")) {
			return "§6";
		} else if (group.equalsIgnoreCase("beta")) {
			return "§1";
		} else if (group.equalsIgnoreCase("sapphire")) {
			return "§3";
		} else if (group.equalsIgnoreCase("premium")) {
			return "§6";
		} else if (group.equalsIgnoreCase("ultimate")) {
			return "§d";
		} else if (group.equalsIgnoreCase("elite")) {
			return "§5";
		} else if (group.equalsIgnoreCase("builder")) {
			return "§2";
		} else if (group.equalsIgnoreCase("ajudante")) {
			return "§9";
		} else if (group.equalsIgnoreCase("youtuber")) {
			return "§b";
		} else if (group.equalsIgnoreCase("youtuber+")) {
			return "§3";
		} else if (group.equalsIgnoreCase("trial")) {
			return "§d";
		} else if (group.equalsIgnoreCase("mod")) {
			return "§5";
		} else if (group.equalsIgnoreCase("modgc")) {
			return "§5";
		} else if (group.equalsIgnoreCase("mod+")) {
			return "§5";
		} else if (group.equalsIgnoreCase("admin")) {
			return "§c";
		} else if (group.equalsIgnoreCase("gerente")) {
			return "§c";
		} else if (group.equalsIgnoreCase("diretor")) {
			return "§4";
		} else if (group.equalsIgnoreCase("dono")) {
			return "§4";
		} else {
			return getGroupLaterColor(group);
		}
	}
}
