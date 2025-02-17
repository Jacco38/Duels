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
            commandSender.sendMessage("Usage:");
            return false;
        }

        // Get the target player
        Player target = commandSender.getServer().getPlayer(args[0]);

        // If target is not online
        if (target == null) {
            commandSender.sendMessage("Player not found");
            return false;
        }

        Player player = commandSender.getServer().getPlayer(commandSender.getName());

        // If target is the same as the player
        if (target == player) {
            commandSender.sendMessage("You can't duel yourself");
            return false;
        }

        this.guiManager.openGUI(new DuelsMenuInventory(args), player);

        return true;
    }

}
