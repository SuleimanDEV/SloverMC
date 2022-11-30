package br.com.slovermc.kitpvp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class MySQL_Connection {
	
	public static Connection connection;
	
	public void ConnectMySQL() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(
				"jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "hg" + "?autoReconnect=true",
						"root", "");
				Bukkit.getConsoleSender().sendMessage("§aA conexão com a MySQL foi estabelecida!");
			}catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage("§cA conexao com a MySQL falhou!");
				e.printStackTrace();
			}
		}
	}
	
	public void DesconnectMySQL() {
		if (connection != null) {
			try {
				connection.close();
				Bukkit.getConsoleSender().sendMessage("§aA conexao com a MySQL foi feita sem erros!");
			}catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage("§cHouve um erro ao desconectar da MySQL!");
			}
		}
	}
}