package br.com.slovermc.groupmanager.databaseapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.Bukkit;

public class SqlConnection {
   public static Connection connection;

   public void ConnectMySQL() {
      if (connection == null) {
         try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/server?autoReconnect=true", "root", "");
            Bukkit.getConsoleSender().sendMessage("§aMySQL connection was sucessfull complete!");
         } catch (SQLException var2) {
            Bukkit.getConsoleSender().sendMessage("§cMySQL connection has failed! Talk to skyprogrammer!");
            var2.printStackTrace();
         }
      }

   }

   public void DesconnectMySQL() {
      if (connection != null) {
         try {
            connection.close();
            Bukkit.getConsoleSender().sendMessage("§aMySQL was disconnected sucessfull!");
         } catch (SQLException var2) {
            Bukkit.getConsoleSender().sendMessage("§cMySQL has failed to disconnected! Talk to skyprogrammer!");
         }
      }

   }
}