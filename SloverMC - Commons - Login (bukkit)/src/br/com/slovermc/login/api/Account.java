package br.com.slovermc.login.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Account {
	
	private static FileConfiguration player_accounts;
	private static File player_account;
	
	public static void createFile(Plugin plugin){
		
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		player_account = new File(plugin.getDataFolder(), "accounts.yml");
		if(player_account.exists()){
			try{
				player_account.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		player_accounts = YamlConfiguration.loadConfiguration(player_account);
	}
	public static FileConfiguration getAccounts(){
		return player_accounts;
	}
	
	public static void saveAccounts(){
		try{
			player_accounts.save(player_account);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}