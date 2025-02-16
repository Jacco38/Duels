package com.jacco.duels.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DuelsMenuInventory extends InventoryGUI{

    Player target;

    public DuelsMenuInventory(Player target) {
        this.target = target;
    }

    @Override
    protected Inventory createInventory() {
        return Bukkit.createInventory(null, 27, "Duel " + this.target.getName());
    }

    @Override
    public void decorate(Player player) {

        int inventorySize = this.getInventory().getSize();

        this.addButton(13, this.createDuelButton());

        super.decorate(player);

    }

    private InventoryButton createDuelButton() {
        ItemStack duelItem = this.createGuiItem(Material.DIAMOND_SWORD, ChatColor.RED + "Duel a player", ChatColor.RESET + "" + ChatColor.GREEN + "Click to duel someone");

        return new InventoryButton()
                .creator(player -> duelItem)
                .consumer(event -> {
                    event.getWhoClicked().sendMessage("You clicked the duel button");
                });

    }

}
