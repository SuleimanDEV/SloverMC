package br.com.slovermc.groupmanager.api;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;
import org.bukkit.plugin.Plugin;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public class Setting {
    public static final String permissions_list = "permission_list.yml";
    public static Setting ymlfile;
    FileConfiguration ymlfile_configuration;
    File newFile;
    
    static {
        Setting.ymlfile = new Setting();
    }
    
    public Setting() {
        super();
    }
    
    public void createYMLFil(final Plugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        this.newFile = new File(plugin.getDataFolder(), "permission_list.yml");
        if (this.newFile.exists()) {
            try {
                this.newFile.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.ymlfile_configuration = (FileConfiguration)YamlConfiguration.loadConfiguration(this.newFile);
    }
    
    public Setting getFile() {
        return Setting.ymlfile;
    }
    
    public FileConfiguration config() {
        return this.ymlfile_configuration;
    }
    
    public void save() {
        try {
            this.ymlfile_configuration.save(this.newFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}