package br.com.slovermc.groupmanager.commands;

import br.com.slovermc.groupmanager.Main;
import br.com.slovermc.groupmanager.api.IGroupApi;
import br.com.slovermc.groupmanager.databaseapi.SqlConnection;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cSetGroup implements CommandExecutor {
   public static boolean validGroup(String args) {
      String[] grouplist = new String[]{"dono", "developer", "diretor", "gerente", "admin", "modgc", "mod", "trial", "ajudante", "youtuber+", "youtuber", "elite", "sapphire", "beta", "light", "pro", "designer", "vip", "membro"};
      String[] var5 = grouplist;
      int var4 = grouplist.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         String list = var5[var3];
         if (args.equalsIgnoreCase(list)) {
            return true;
         }
      }

      return false;
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (cmd.getName().equalsIgnoreCase("setgroup") || cmd.getName().equalsIgnoreCase("groupset")) {
         if (!sender.hasPermission("slover.setgroup")) {
            sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
            return true;
         }

         if (args.length == 0) {
            sender.sendMessage("§6§lGROUPSET§f Para setar um rank utilize /setgroup <player> (grupo)");
            return true;
         }

         if (args.length == 1) {
            sender.sendMessage("§6§lGROUPSET§f Sintaxe incompleta: §3§l/setgroup " + args[0] + " <grupo>");
            return true;
         }

         if (args.length == 2) {
            Player grouped = Bukkit.getPlayer(args[0]);
            if (SqlConnection.connection == null) {
               sender.sendMessage("§6§lGROUPSET§f A §a§lCONEXAO§f com a §b§lMYSQL§f está §c§lNULA!§f Não será possível setar §e§lGRUPOS!");
               return true;
            }

            else if (!validGroup(args[1])) {
               sender.sendMessage("§6§lGROUPSET§f Este grupo não existe entre nosso CÓDIGOS!");
               return true;
            }

            else if (!sender.isOp() && args[1].equalsIgnoreCase("dono")) {
               sender.sendMessage("§6§lGROUPSET §fVocê não pode setar este grupo.");
               return true;
            }

           else if (grouped == null) {
               OfflinePlayer goff = Bukkit.getOfflinePlayer(args[0]);
               if (goff.getName().length() > 16) {
                  sender.sendMessage("§6§lGROUPSET§f Este nick possui mais de §9§l16 CARATERES!§f Não é um nick §2§lVALIDO!");
                  return true;
               }

              else  if (!Main.database.verifyPlayerRegister(goff.getName())) {
                  Main.database.newPlayer(goff);
               }

               Main.database.setGroup(goff, args[1].toUpperCase());
               sender.sendMessage("§6§lGROUPSET§f Você §balterou§ o cargo de" + grouped.getName() + grouped.getUniqueId() + "§f para o cargo de " + IGroupApi.convertGroupColor(args[1]));
               return true;
            }

           else if (grouped.getName().length() > 16) {
               sender.sendMessage("§6§lGROUPSET§f Este nick possui mais de §9§l16 CARATERES!§f Não é um nick §2§lVALIDO!");
               return true;
            }

          else if (!Main.database.verifyPlayerRegister(grouped.getName())) {
               Main.database.newPlayer(grouped);
            }

            IGroupApi.unsetPermissions(grouped, Main.database.getGroup(grouped));
            Main.database.setGroup(grouped, args[1].toUpperCase());
            IGroupApi.setPermissions(grouped, args[1]);
            grouped.sendMessage("§6§lGROUPSET§f Você teve seu rank alterado para" + IGroupApi.convertGroupColor(args[1]));
            sender.sendMessage("§6§lGROUPSET§f Você alterou o §6§lGRUPO§f do §e " + grouped.getName() + "§e(" + grouped.getUniqueId() + "§e) " + "§f para " + IGroupApi.convertGroupColor(args[1]));
            return true;
         }

         if (args.length > 2) {
            sender.sendMessage("§6§lGROUPSET§f Para setar um rank para o jogador utilize: §e/setgroup <player> (grupo)§f.");
            return true;
         }
      }

      return false;
   }
}