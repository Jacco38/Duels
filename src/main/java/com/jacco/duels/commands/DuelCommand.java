package com.jacco.duels.commands;

import com.jacco.duels.menus.DuelsMenuInventory;
import com.jacco.duels.menus.GUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {

    GUIManager guiManager;

    public DuelCommand(GUIManager guiManager) {
        this.guiManager = guiManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 0) {
            commandSender.sendMessage("Usage: /duel <player>");
            return false;
        }

        Player target = commandSender.getServer().getPlayer(args[0]);

        Player player = commandSender.getServer().getPlayer(commandSender.getName());
        this.guiManager.openGUI(new DuelsMenuInventory(target), player);

        return true;
    }

}
