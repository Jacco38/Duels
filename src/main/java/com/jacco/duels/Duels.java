package com.jacco.duels;

import com.jacco.duels.commands.DuelCommand;
import com.jacco.duels.listeners.BlockBreakListener;
import com.jacco.duels.manager.TabCompleter;
import com.jacco.duels.menus.GUIListener;
import com.jacco.duels.menus.GUIManager;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Duels extends JavaPlugin {

    static Inventory duelMenuInventory;

    @Override
    public void onEnable() {
        // Plugin startup logic
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void SetDuelMenuInventory(Inventory inventory) {
        this.duelMenuInventory = inventory;
    }

    public static Inventory GetDuelMenuInventory() {
        return duelMenuInventory;
    }
}
