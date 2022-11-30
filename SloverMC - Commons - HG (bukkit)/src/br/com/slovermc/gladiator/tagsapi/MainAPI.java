package br.com.slovermc.gladiator.tagsapi;

import org.bukkit.entity.Player;

import br.com.slovermc.hg.mysql.RankList;

public final class MainAPI {

	public MainAPI() {

	}

	public String getDono(Player p) {
		if (p.isOp()) {
			return "§4§lDONO§f, ";
		}
		return "";
	}

	public String getProgrammer(Player p) {
		if (p.hasPermission("tag.developer")) {
			return "§3§lDEVELOPER §3";
		}
		return "";
	}

	public String getGerente(Player p) {
		if (p.hasPermission("tag.gerente")) {
			return "§c§lGERENTE§f, ";
		}
		return "";
	}

	public String getAdmin(Player p) {
		if (p.hasPermission("tag.admin")) {
			return "§c§lADMIN§f, ";
		}
		return "";
	}

	public String getModGc(Player p) {
		if (p.hasPermission("tag.modgc")) {
			return "§5§lMODGC§f, ";
		}
		return "";
	}
	
	public String getDesigner(Player p) {
		if (p.hasPermission("tag.designer")) {
			return "§2§lDESIGNER§f, ";
		}
		return "";
	}

	public String getTrialGC(Player p) {
		if (p.hasPermission("tag.trialgc")) {
			return "§d§lTRIALGC§f, ";
		}
		return "";
	}


	public String getMod(Player p) {
		if (p.hasPermission("tag.mod")) {
			return "§5§lMOD§f, ";
		}
		return "";
	}

	public String getTrial(Player p) {
		if (p.hasPermission("tag.trial")) {
			return "§d§lTRIAL§f, ";
		}
		return "";
	}

	public String getYtPlus(Player p) {
		if (p.hasPermission("tag.ytplus")) {
			return "§3§lYOUTUBER+§f, ";
		}
		return "";
	}

	public String getBuilder(Player p) {
		if (p.hasPermission("tag.builder")) {
			return "§2§lBUILDER§f, ";
		}
		return "";
	}

	public String getAjudante(Player p) {
		if (p.hasPermission("tag.ajudante")) {
			return "§9§lAJUDANTE§f, ";
		}
		return "";
	}

	public String getYt(Player p) {
		if (p.hasPermission("tag.yt")) {
			return "§b§lYOUTUBER§f, ";
		}
		return "";
	}

	public String getPro(Player p) {
		if (p.hasPermission("tag.pro")) {
			return "§6§lPRO§f, ";
		}
		return "";
	}
	
	public String getBeta(Player p) {
		if (p.hasPermission("tag.beta")) {
			return "§1§lBETA§f, ";
		}
		return "";
	}

	public String getLight(Player p) {
		if (p.hasPermission("tag.light")) {
			return "§a§lLIGHT§f, ";
		}
		return "";
	}
	
	public String getDiretor(Player p) {
		if (p.hasPermission("tag.diretor")) {
			return "§4§lDIRETOR§4, ";
		}
		return "";
	}

	public String getSapphire(Player p) {
		if (p.hasPermission("tag.sapphire")) {
			return "§3§lSAPPHIRE§3, ";
		}
		return "";
	}

	public String getVip(Player p) {
		if (p.hasPermission("tag.vip")) {
			return "§a§lVIP§f, ";
		}
		return "";
	}
	
	public String getElite(Player p) {
		if (p.hasPermission("tag.elite")) {
			return "§b§lELITE§f, ";
		}
		return "";
	}

	public String getMembro(Player p) {
		return "§7§lMEMBROf, ";
	}

	public void checkTags(Player p) {
		p.sendMessage("§9§lTAGS§f Suas tags: " + getDono(p) + getProgrammer(p) + getDiretor(p) + getGerente(p) + getAdmin(p)
				+ getModGc(p) + getMod(p) + getYtPlus(p) + getBuilder(p) + getAjudante(p) + getYt(p) + getSapphire(p)
				+ getBeta(p) + getElite(p) + getPro(p) + getLight(p) + getVip(p) + getDesigner(p) + getMembro(p));
	}

	public void joinNametag(Player p) {
		if (p.isOp()) {
			setDono(p);
			return;
		} else if (p.hasPermission("tag.developer")) {
			setProgrammer(p);
			return;
		} else if (p.hasPermission("tag.gerente")) {
			setGerente(p);
			return;
		} else if (p.hasPermission("tag.diretor")) {
			setDiretor(p);
			return;
		} else if (p.hasPermission("tag.admin")) {
			setAdmin(p);
			return;
		} else if (p.hasPermission("tag.modgc")) {
			setModGc(p);
			return;
		} else if (p.hasPermission("tag.mod")) {
			setMod(p);
			return;
		} else if (p.hasPermission("tag.trial")) {
			setTrial(p);
			return;
		} else if (p.hasPermission("tag.trialgc")) {
			setTrialGC(p);
			return;
		} else if (p.hasPermission("tag.ytplus")) {
			setYtPlus(p);
			return;
		} else if (p.hasPermission("tag.builder")) {
			setBuilder(p);
			return;
		} else if (p.hasPermission("tag.ajudante")) {
			setAjudante(p);
			return;
		} else if (p.hasPermission("tag.yt")) {
			setYt(p);
			return;
		} else if (p.hasPermission("tag.beta")) {
			setBeta(p);
			return;
		} else if (p.hasPermission("tag.sapphire")) {
			setSapphire(p);
			return;
		} else if (p.hasPermission("tag.elite")) {
			setElite(p);
			return;
		} else if (p.hasPermission("tag.pro")) {
			setPro(p);
			return;
		} else if (p.hasPermission("tag.light")) {
			setLight(p);
			return;
		} else if (p.hasPermission("tag.vip")) {
			setVip(p);
			return;
		} else if (p.hasPermission("tag.designer")) {
			setDesigner(p);
			return;
		} else {
			setMembro(p);
			return;
		}
	}

	public void setDono(Player p) {
		p.setDisplayName("§4§lDONO §4" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§4§lDONO §4", RankList.getDisplayNameRank(p));
	}
	
	public void setDiretor(Player p) {
		p.setDisplayName("§4§lDIRETOR §4" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§4§lDIRETOR §4", RankList.getDisplayNameRank(p));
	}
	
	public void setElite(Player p) {
		p.setDisplayName("§b§lELITE §b" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§b§lELITE §b", RankList.getDisplayNameRank(p));
	}
	
	public void setDesigner(Player p) {
		p.setDisplayName("§2§lDESIGNER §2" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§2§lDESIGNER §2", RankList.getDisplayNameRank(p));
	}
	
	public void setSapphire(Player p) {
		p.setDisplayName("§3§lSAPPHIRE §3" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§3§lSAPPHIRE §3", RankList.getDisplayNameRank(p));
	}

	public void setProgrammer(Player p) {
		p.setDisplayName("§3§lDEVELOPER §3" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§3§lDEVELOPER §3", RankList.getDisplayNameRank(p));
	}

	public void setGerente(Player p) {
		p.setDisplayName("§c§lGERENTE §c" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§c§lGERENTE §c", RankList.getDisplayNameRank(p));
	}

	public void setAdmin(Player p) {
		p.setDisplayName("§c§lADMIN §c" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§c§lADMIN §c", RankList.getDisplayNameRank(p));
	}

	public void setModGc(Player p) {
		p.setDisplayName("§5§lMODGC §5" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§5§lMODGC §5", RankList.getDisplayNameRank(p));
	}

	public void setMod(Player p) {
		p.setDisplayName("§5§lMOD §5" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§5§lMOD §5", RankList.getDisplayNameRank(p));
	}

	public void setTrial(Player p) {
		p.setDisplayName("§d§lTRIAL §d" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§d§lTRIAL §d", RankList.getDisplayNameRank(p));
	}
	
	public void setTrialGC(Player p) {
		p.setDisplayName("§d§lTRIALGC §d" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§d§lTRIALGC §d", RankList.getDisplayNameRank(p));
	}

	public void setYtPlus(Player p) {
		p.setDisplayName("§3§lYOUTUBER+ §3" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§3§lYOUTUBER+ §3", RankList.getDisplayNameRank(p));
	}

	public void setBuilder(Player p) {
		p.setDisplayName("§2§lBUILDER §2" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§2§lBUILDER §2", RankList.getDisplayNameRank(p));
	}

	public void setAjudante(Player p) {
		p.setDisplayName("§9§lAJUDANTE §9" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§9§lAJUDANTE §9", RankList.getDisplayNameRank(p));
	}

	public void setYt(Player p) {
		p.setDisplayName("§b§lYOUTUBER §b" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§b§lYOUTUBER §b", RankList.getDisplayNameRank(p));
	}
	
	public void setBeta(Player p) {
		p.setDisplayName("§1§lBETA §1" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§1§lBETA §1", RankList.getDisplayNameRank(p));
	}

	public void setPro(Player p) {
		p.setDisplayName("§6§lPRO §6" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§6§lPRO §6", RankList.getDisplayNameRank(p));
	}

	public void setLight(Player p) {
		p.setDisplayName("§a§lLIGHT §a" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§a§lLIGHT §a", RankList.getDisplayNameRank(p));
	}

	public void setVip(Player p) {
		p.setDisplayName("§a§lVIP §a" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§a§lVIP §a", RankList.getDisplayNameRank(p));
	}

	public void setMembro(Player p) {
		p.setDisplayName("§7" + p.getName() + RankList.getDisplayNameRank(p));
		NametagAPI.setTag(p.getName(), "§7", RankList.getDisplayNameRank(p));
	}
}