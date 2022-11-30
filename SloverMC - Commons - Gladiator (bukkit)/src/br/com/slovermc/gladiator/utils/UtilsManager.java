package br.com.slovermc.gladiator.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import br.com.slovermc.gladiator.BukkitMain;

public class UtilsManager {

	private BukkitMain plugin;

	public UtilsManager() {

		plugin = BukkitMain.getPlugin(BukkitMain.class);
		plugin.saveDefaultConfig();
	}

	/**
	 * Register a listener.
	 * 
	 * @param listener
	 *            Listener means events
	 */

	public void registerListener(Listener listener) {
		PluginManager manager = Bukkit.getPluginManager();
		manager.registerEvents(listener, getPlugin());
	}

	/**
	 * Getter for the instances.
	 */

	public FileConfiguration getConfig() {
		return BukkitMain.getPlugin(BukkitMain.class).getConfig();
	}

	public BukkitMain getPlugin() {
		return plugin;
	}
}