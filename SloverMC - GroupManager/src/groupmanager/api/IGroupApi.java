package groupmanager.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import groupmanager.main.Main;

public class IGroupApi {
	
	public static void updateAllRanks() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					updateTempRanks(p);
				}
			}		
		}, 0, 10L);
	}
	
	public static void updateTempRanks(Player p) {	
		if(isTempGroup(p) && p.isOnline()) {
			long time = (long) Main.database.getTime(p);
			if (time < System.currentTimeMillis()) {
				p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 2.0F, 2.0F);
				p.sendMessage(" ");
				p.sendMessage("§6§lGROUP§f Seu §e§lGRUPO§f já se §c§lEXPIROU!");
				p.sendMessage("§6§lGROUP§f Para §a§lADQUIRI-LO§f novamente, contate á §d§lSTAFF§f pelo justo motivo ou acesse: §6§lwww.slovermc.com");
				p.sendMessage(" ");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setgroup " + p.getName() + " MEMBRO");
		        }
		    }
		}
	
	public static boolean isTempGroup(Player p) {
		return !String.valueOf(Main.database.getTime(p)).contains("-1");
	}
	
	public static void setGroup(Player p, String group) {
		Main.database.setGroup(p, group);
	}
	
	public static void setGroup(OfflinePlayer p, String group) {
		Main.database.setGroup(p, group);
	}
	
	public static String getGroup(Player p) {
		return Main.database.getGroup(p);
	}
	
	public static void setTempGroup(Player p, String group, long time) {
		Main.database.setTempGroup(p, group, time);
	}
	
	public static void setTempGroup(OfflinePlayer p, String group, long time) {
		Main.database.setTempGroup(p, group, time);
	}
	
	public static long getGroupTime(OfflinePlayer p) {
		return Main.database.getTime(p);
	}
	
	public static long getGroupTime(Player p) {
		return Main.database.getTime(p);
	}
	
	public static String getGroup(OfflinePlayer p) {
		return Main.database.getGroup(p);
	}
	
	public static String convertGroupColor(final String args) {
		if (args.equalsIgnoreCase("DONO")) {
			return "§4§lDONO";
		}
		else if (args.equalsIgnoreCase("DEVELOPER")) {
			return "§3§lDEVELOPER";
		}
		else if (args.equalsIgnoreCase("DIRETOR")) {
			return "§4§lDIRETOR";
		}
		else if (args.equalsIgnoreCase("ADMIN")) {
			return "§c§lADMIN";
		}
		else if (args.equalsIgnoreCase("GERENTE")) {
			return "§c§lGERENTE";
		}
		else if (args.equalsIgnoreCase("MODGC")) {
			return "§5§lMODGC";
		}
		else if (args.equalsIgnoreCase("MOD")) {
			return "§5§lMOD";
		}
		else if (args.equalsIgnoreCase("TRIAL")) {
			return "§d§lTRIAL";
		}
		else if (args.equalsIgnoreCase("AJUDANTE")) {
			return "§9§lAJUDANTE";
		}
		else if (args.equalsIgnoreCase("YOUTUBER+")) {
			return "§3§lYOUTUBER+";
		}
		else if (args.equalsIgnoreCase("YOUTUBER")) {
			return "§b§lYOUTUBER";
		}
		else if (args.equalsIgnoreCase("DESIGNER")) {
			return "§2§lDESIGNER";
		}
		else if (args.equalsIgnoreCase("BUILDER")) {
			return "§2§lBUILDER";
		}
		else if (args.equalsIgnoreCase("SAPPHIRE")) {
			return "§3§lSAPPHIRE";
		}
		else if (args.equalsIgnoreCase("ELITE")) {
			return "§b§lELITE";
		}
		else if (args.equalsIgnoreCase("BETA")) {
			return "§1§lBETA";
		}
		else if (args.equalsIgnoreCase("PRO")) {
			return "§6§lPRO";
		}
		else if (args.equalsIgnoreCase("LIGHT")) {
			return "§a§lLIGHT";
		}
		else if (args.equalsIgnoreCase("VIP")) {
			return "§a§lVIP";
		}
		else if (args.equalsIgnoreCase("MVP")) {
			return "§9§lMVP";
		}
		else if (args.equalsIgnoreCase("MEMBRO")) {
			return "§7§lMEMBRO";
		}
		return args;
	}
	
	public static HashMap<String, PermissionAttachment> perms = new HashMap<>();
	
	public static void loadPermission(Player p, String group) {
		setPermissions(p, group);	
	}
	
	public static void deOp(Player p) {
		if (p.isOp()) {
			p.setOp(false);
		}
	}
	
	public static void setPermissions(Player p, String group) {
		deOp(p);
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");
		PermissionAttachment attachment = p.addAttachment(Main.getPlugin());
		perms.put(p.getName(), attachment);
		
		PermissionAttachment pperms = perms.get(p.getName());
		if (group.equalsIgnoreCase("DONO") || group.equalsIgnoreCase("MASTER")) {
			p.setOp(true);
			return;
		}
		for (String permissions : list) {
			if (!p.hasPermission(permissions.replace("'*'", "*"))) {
				pperms.setPermission(permissions, true);
			}
		}
	}
	
	public static void unsetPermissions(Player p, String group) {
		deOp(p);
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");

		PermissionAttachment pperms = perms.get(p.getName());
		
		for (String permissions : list) {
			if (!p.hasPermission(permissions.replace("'*'", "*"))) {
				pperms.unsetPermission(permissions);
		        perms.remove(p.getName());
			}
		}
	}
	
	public static String returnPerm(ArrayList<String> args) {
		for (String perm : args) {
			return perm;
		}
		return args.toString();
	}
	
	public static void gerarTodosYML() {
		Bukkit.getConsoleSender().sendMessage("§aGerando arquivos .yml que contém as permissões...");
		gerarDono();
		gerarDeveloper();
		gerarDiretor();
		gerarAdmin();
		gerarGerente();
		gerarModGC();
		gerarMod();
		gerarTrial();
		gerarAjudante();
		gerarYoutuberplus();
		gerarYoutuber();
		gerarDesigner();
		gerarBuilder();
		gerarSapphire();
		gerarElite();
		gerarBeta();
		gerarPro();
		gerarLight();
		gerarVip();
		gerarMvp();
		gerarMembro();
		Bukkit.getConsoleSender().sendMessage("§aOs arquivos foram gerados/atualizados com sucesso!");
	}
	
	public static void gerarDono() {
		final String[] perm_dono = {"*"};
		for (String all : perm_dono) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("DONO" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("DONO" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarDeveloper() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("DEVELOPER" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("DEVELOPER" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	
	public static void gerarDiretor() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("DIRETOR" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("DIRETOR" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	
	public static void gerarAdmin() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("ADMIN" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("ADMIN" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarGerente() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("GERENTE" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("GERENTE" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}	
	public static void gerarModGC() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("MODGC" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("MODGC" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	
	public static void gerarMod() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("MOD" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("MOD" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	
	public static void gerarTrial() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("TRIAL" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("TRIAL" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	
	public static void gerarAjudante() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("AJUDANTE" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("AJUDANTE" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarYoutuberplus() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("YOUTUBER+" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("YOUTUBER+" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	
	public static void gerarYoutuber() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("YOUTUBER" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("YOUTUBER" + ".PERMS", list);
		Main.yml.getFile().save();
		}
		
	}
	public static void gerarDesigner() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("DESIGNER" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("DESIGNER" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarBuilder() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("MEMBRO" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("MEMBRO" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarSapphire() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("SAPPHIRE" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("SAPPHIRE" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarElite() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("ELITE" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("ELITE" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarBeta() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("BETA" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("BETA" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarPro() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("PRO" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("PRO" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarLight() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("LIGHT" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("LIGHT" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarVip() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("VIP" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("VIP" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarMvp() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("MVP" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("MVP" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
	public static void gerarMembro() {
		final String[] perm = {""};
		for (String all : perm) {
		ArrayList<String> list = (ArrayList<String>) Main.yml.getFile().config().getStringList("MEMBRO" + ".PERMS");
		 if(!list.contains(all)){
			 list.add(all);
		 }
		Main.yml.getFile().config().set("MEMBRO" + ".PERMS", list);
		Main.yml.getFile().save();
		}
	}
}