package groupmanager.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Setting {
	
	public Setting() {
	}
	
	public static final String permissions_list = "permission_list.yml";
	
	public static Setting ymlfile = new Setting();
	FileConfiguration ymlfile_configuration;
	File newFile;

	public void createYMLFil(Plugin plugin) {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		newFile = new File(plugin.getDataFolder(), permissions_list);
		if (newFile.exists()) {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ymlfile_configuration = YamlConfiguration.loadConfiguration(newFile);
	}
	
	public Setting getFile() {
		return ymlfile;
	}
	
	public FileConfiguration config() {
		return ymlfile_configuration;
	}

	public void save() {
		try {
			ymlfile_configuration.save(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}