package com.jacco.duels.manager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        // Make a list with aliases for the /duel command
        List<String> duelList = new ArrayList<String>();
        duelList.add("duel");
        duelList.add("1v1");

        // Check if the command is /duel or /1v1
        if (duelList.contains(command.getName().toLowerCase())) {

            List<String> list = new ArrayList<String>();

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    list.add(p.getName());
                }
            }

            return list;

        }

        return null;

    }

}
