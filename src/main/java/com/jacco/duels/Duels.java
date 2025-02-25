package com.jacco.duels;

import com.jacco.duels.commands.DuelCommand;
import com.jacco.duels.listeners.BlockBreakListener;
import com.jacco.duels.manager.TabCompleter;
import com.jacco.duels.menus.GUIListener;
import com.jacco.duels.menus.GUIManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Duels extends JavaPlugin {

    private File kitConfigFile;
    private static FileConfiguration kitConfig;

    private File messagesConfigFile;
    private static FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Initialize GUIManager
        GUIManager guiManager = new GUIManager();

        // Add event listeners
        GUIListener guiListener = new GUIListener(guiManager);

        getServer().getPluginManager().registerEvents(guiListener, this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        // Register tab completer
        TabCompleter tabCompleter = new TabCompleter();

        // Register commands
        getCommand("duel").setTabCompleter(tabCompleter);
        getCommand("duel").setExecutor(new DuelCommand(guiManager));

        // Load config
        saveDefaultConfig();

        // Load Kit Config Files
        kitConfigFile = new File(getDataFolder(), "kits.yml");

        if (!kitConfigFile.exists()) {
            kitConfigFile.getParentFile().mkdirs();
            saveResource("kits.yml", false);
        }

        kitConfig = new YamlConfiguration();
        try {
            kitConfig.load(kitConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load Messages Config Files
        messagesConfigFile = new File(getDataFolder(), "lang/messages.yml");

        if (!messagesConfigFile.exists()) {
            messagesConfigFile.getParentFile().mkdirs();
            saveResource("lang/messages.yml", false);
        }

        messagesConfig = new YamlConfiguration();
        try {
            messagesConfig.load(messagesConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            kitConfig.save(kitConfigFile);
            messagesConfig.save(messagesConfigFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileConfiguration getKitConfig() {
        return kitConfig;
    }

    public static FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

}
