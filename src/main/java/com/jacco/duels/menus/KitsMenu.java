package com.jacco.duels.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class KitsMenu extends InventoryGUI {

    public KitsMenu(String[] args, Player owner) {
        super(args, owner);
    }

    @Override
    protected Inventory createInventory() {
        return Bukkit.createInventory(null, 27, "Kits");
    }

    @Override
    public void decorate(Player player) {
        super.decorate(player);
    }

//    public InventoryButton[] getKits() {
//
//        InventoryButton[] buttons = new InventoryButton[];
//
//        return buttons;
//
//    }

}
