package br.com.slovermc.login;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.slovermc.login.api.Account;
import br.com.slovermc.login.api.Menu;
import br.com.slovermc.login.command.cChangePass;
import br.com.slovermc.login.command.cLogin;
import br.com.slovermc.login.command.cRegister;
import br.com.slovermc.login.events.Listeners;

@SuppressWarnings("unused")
public final class Main extends JavaPlugin {

	public static Plugin login;
	public static Main instance;
	
	public static Main getInstance() {
		return instance;
	}

	public static final Plugin getPlugin() {
		return login;
	}
	

	public final void onEnable() {
		login = this;
		instance = this;
		saveDefaultConfig();
		Account.createFile(login);
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Events();
		Commands();
		//BukkitTasks.updateAllAnimations();
		Bukkit.getConsoleSender().sendMessage("§aPlugin iniciado com sucesso, acesso permitido.");
	}
	
	public final void Events() {
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		Bukkit.getPluginManager().registerEvents(new Menu(), this);
	}
	
	public final void Commands() {
		try {
			final Field commandField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			commandField.setAccessible(true);
			CommandMap newCommand = (CommandMap) commandField.get(Bukkit.getServer());
			
			newCommand.register("login", new cLogin("login"));
			newCommand.register("register", new cRegister("register"));
			newCommand.register("changepass", new cChangePass("changepass"));
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public final void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§aPlugin iniciado com sucesso, acesso permitido.");
		for (final Player on : Bukkit.getOnlinePlayers()) {
			on.kickPlayer("§c§lREINICIANDO\n§f      O servidor está reiniciando\n§fvolte dentre alguns minutos!");
		}
	}
}
