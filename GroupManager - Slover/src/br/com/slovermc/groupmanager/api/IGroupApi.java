package br.com.slovermc.groupmanager.api;

import br.com.slovermc.groupmanager.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class IGroupApi {
   public static HashMap<String, PermissionAttachment> perms = new HashMap();

   public static void updateAllRanks() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
         public void run() {
            Player[] var4;
            int var3 = (var4 = Bukkit.getOnlinePlayers()).length;

            for(int var2 = 0; var2 < var3; ++var2) {
               Player p = var4[var2];
               IGroupApi.updateTempRanks(p);
            }

         }
      }, 0L, 10L);
   }

   public static void updateTempRanks(Player p) {
      if (isTempGroup(p) && p.isOnline()) {
         long time = Main.database.getTime(p);
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

   public static String convertGroupColor(String args) {
      if (args.equalsIgnoreCase("DONO")) {
         return "§4Dono";
      } else if (args.equalsIgnoreCase("DEVELOPER")) {
         return "§3Developer";
      } else if (args.equalsIgnoreCase("DIRETOR")) {
         return "§4Diretor";
      } else if (args.equalsIgnoreCase("ADMIN")) {
         return "§cAdmin";
      } else if (args.equalsIgnoreCase("GERENTE")) {
         return "§cGerente";
      } else if (args.equalsIgnoreCase("MODGC")) {
         return "§5ModGC";
      } else if (args.equalsIgnoreCase("MOD+")) {
         return "§5Mod+";
      } else if (args.equalsIgnoreCase("MOD")) {
         return "§5Mod";
      } else if (args.equalsIgnoreCase("AJUDANTE")) {
         return "§9Ajudante";
      } else if (args.equalsIgnoreCase("TRIAL")) {
         return "§dTrial";
      } else if (args.equalsIgnoreCase("YOUTUBER+")) {
         return "§3Youtuber+";
      } else if (args.equalsIgnoreCase("YOUTUBER")) {
         return "§bYoutuber";
      } else if (args.equalsIgnoreCase("SAPPHIRE")) {
         return "§3Sapphire";
      } else if (args.equalsIgnoreCase("ELITE")) {
         return "§bElite";
      } else if (args.equalsIgnoreCase("BETA")) {
         return "§1Beta";
      } else if (args.equalsIgnoreCase("LIGHT")) {
         return "§aLight";
      } else if (args.equalsIgnoreCase("PRO")) {
         return "§6Pro";
      } else if (args.equalsIgnoreCase("VIP")) {
         return "§aVip";
      } else if (args.equalsIgnoreCase("DESIGNER")) {
         return "§2Designer";
      } else {
         return args.equalsIgnoreCase("MEMBRO") ? "§7§lMEMBRO" : args;
      }
   }

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
      ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");
      PermissionAttachment attachment = p.addAttachment(Main.getPlugin());
      perms.put(p.getName(), attachment);
      PermissionAttachment pperms = (PermissionAttachment)perms.get(p.getName());
      if (!group.equalsIgnoreCase("DONO") && !group.equalsIgnoreCase("MASTER")) {
         Iterator var6 = list.iterator();

         while(var6.hasNext()) {
            String permissions = (String)var6.next();
            if (!p.hasPermission(permissions.replace("'*'", "*"))) {
               pperms.setPermission(permissions, true);
            }
         }

      } else {
         p.setOp(true);
      }
   }

   public static void unsetPermissions(Player p, String group) {
      deOp(p);
      ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList(group.toUpperCase().toString() + ".PERMS");
      PermissionAttachment pperms = (PermissionAttachment)perms.get(p.getName());
      Iterator var5 = list.iterator();

      while(var5.hasNext()) {
         String permissions = (String)var5.next();
         if (!p.hasPermission(permissions.replace("'*'", "*"))) {
            pperms.unsetPermission(permissions);
            perms.remove(p.getName());
         }
      }

   }

   public static String returnPerm(ArrayList<String> args) {
      Iterator var2 = args.iterator();
      if (var2.hasNext()) {
         String perm = (String)var2.next();
         return perm;
      } else {
         return args.toString();
      }
   }

   public static void gerarTodosYML() {
      Bukkit.getConsoleSender().sendMessage("§eGerando arquivos .yml que contém as permissões...");
      gerarDono();
      gerarDeveloper();
      gerarDiretor();
      gerarAdmin();
      gerarGerente();
      gerarModGC();
      gerarMod();
      gerarAjudante();
      gerarTrial();
      gerarYoutuberPlus();
      gerarYoutuber();
      gerarSapphire();
      gerarElite();
      gerarBeta();
      gerarPro();
      gerarLight();
      gerarVip();
      gerarMembro();
      gerarDesigner();
      Bukkit.getConsoleSender().sendMessage("§aOs arquivos foram gerados/atualizados com sucesso!");
   }

   public static void gerarDono() {
      String[] perm_dono = new String[]{"*"};
      String[] var4 = perm_dono;
      int var3 = perm_dono.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("DONO.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("DONO.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarDesigner() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("DESIGNER.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("DESIGNER.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarDeveloper() {
      String[] perm_developer = new String[]{""};
      String[] var4 = perm_developer;
      int var3 = perm_developer.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("DEVELOPER.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("DEVELOPER.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarDiretor() {
      String[] perm_diretor = new String[]{""};
      String[] var4 = perm_diretor;
      int var3 = perm_diretor.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("DIRETOR.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("DIRETOR.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarGerente() {
	      String[] perm_gerente = new String[]{""};
	      String[] var4 = perm_gerente;
	      int var3 = perm_gerente.length;

	      for(int var2 = 0; var2 < var3; ++var2) {
	         String all = var4[var2];
	         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("GERENTE.PERMS");
	         if (!list.contains(all)) {
	            list.add(all);
	         }

	         Main.yml.getFile().config().set("GERENTE.PERMS", list);
	         Main.yml.getFile().save();
	      }

	   }

   public static void gerarAdmin() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("ADMIN.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("ADMIN.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarModGC() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("MODGC.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("MODGC.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarMod() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("MOD.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("MOD.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarAjudante() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("AJUDANTE.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("AJUDANTE.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarTrial() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("TRIAL.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("TRIAL.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarYoutuberPlus() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("YOUTUBER+.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("YOUTUBER+.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarYoutuber() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("YOUTUBER.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("YOUTUBER.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarVip() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("VIP.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("VIP.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarElite() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("ELITE.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("ELITE.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarPro() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("PRO.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("PRO.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarSapphire() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("SAPPHIRE.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("SAPPHIRE.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarBeta() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("BETA.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("BETA.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarLight() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("LIGHT.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("LIGHT.PERMS", list);
         Main.yml.getFile().save();
      }

   }

   public static void gerarMembro() {
      String[] perm = new String[]{""};
      String[] var4 = perm;
      int var3 = perm.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String all = var4[var2];
         ArrayList<String> list = (ArrayList)Main.yml.getFile().config().getStringList("MEMBRO.PERMS");
         if (!list.contains(all)) {
            list.add(all);
         }

         Main.yml.getFile().config().set("MEMBRO.PERMS", list);
         Main.yml.getFile().save();
      }

   }
}