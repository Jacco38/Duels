package com.jacco.duels.menus;

import com.jacco.duels.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DuelsMenuInventory extends InventoryGUI{

    public DuelsMenuInventory(String[] args, Player owner) {
        super(args, owner);
    }

    @Override
    protected Inventory createInventory() {
        Player target = Bukkit.getPlayer(this.getArgs()[0]);
        return Bukkit.createInventory(null, 27, "Duel Menu");
    }

    @Override
    public void decorate(Player player) {

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

        this.addButton(10, this.createCancelButton());
        this.addButton(16, this.createDuelButton());

        super.decorate(player);

    }

    private InventoryButton createDuelButton() {
        Player target = Bukkit.getPlayer(this.getArgs()[0]);

        String title = ColorUtils.replacePlaceholders("&r&aSend Duel Request", this.getOwner(), target);
        String lore = ColorUtils.replacePlaceholders("&r&aClick to send %target% a duel request", this.getOwner(), target);

        title = ColorUtils.translateColorCodes(title);
        lore = ColorUtils.translateColorCodes(lore);

        ItemStack duelItem = this.createGuiItem(Material.LIME_STAINED_GLASS_PANE, title, lore);

        return new InventoryButton()
                .creator(player -> duelItem)
                .consumer(event -> {
                    Player sender = (Player) event.getWhoClicked();
                    sender.sendMessage(ColorUtils.translateColorCodes("&aYou have sent a duel request to " + target.getName()));

                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    target.sendMessage(ColorUtils.translateColorCodes("&aYou have received a duel request from " + sender.getName()));

                    sender.closeInventory();
                });

    }

    private InventoryButton createCancelButton() {
        Player target = Bukkit.getPlayer(this.getArgs()[0]);

        String title = ColorUtils.replacePlaceholders("&r&cCancel Duel Request", this.getOwner(), target);
        String lore = ColorUtils.replacePlaceholders("&r&cClick to cancel %target%'s duel request", this.getOwner(), target);

        title = ColorUtils.translateColorCodes(title);
        lore = ColorUtils.translateColorCodes(lore);

        ItemStack cancelItem = this.createGuiItem(Material.RED_STAINED_GLASS_PANE, title, lore);

        return new InventoryButton()
                .creator(player -> cancelItem)
                .consumer(event -> {
                    Player sender = (Player) event.getWhoClicked();
                    sender.sendMessage(ColorUtils.translateColorCodes("&cYou have cancelled the duel request to " + target.getName()));
                    sender.closeInventory();
                });
    }

}
