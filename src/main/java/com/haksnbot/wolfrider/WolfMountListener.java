package com.haksnbot.wolfrider;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class WolfMountListener implements Listener {

    private final WolfRiderPlugin plugin;

    public WolfMountListener(WolfRiderPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (!(event.getRightClicked() instanceof Wolf wolf)) {
            return;
        }

        Player player = event.getPlayer();

        if (!wolf.isTamed()) {
            return;
        }

        if (plugin.isOwnerOnly()) {
            if (wolf.getOwner() == null || !wolf.getOwner().getUniqueId().equals(player.getUniqueId())) {
                player.sendMessage("\u00a7cThis wolf doesn't belong to you!");
                return;
            }
        }

        if (wolf.getPassengers().contains(player)) {
            return;
        }

        if (!wolf.getPassengers().isEmpty()) {
            player.sendMessage("\u00a7cThis wolf already has a rider!");
            return;
        }

        event.setCancelled(true);

        wolf.setSitting(false);
        wolf.addPassenger(player);
        player.sendMessage("\u00a7aYou mounted your wolf! Hold a bone to steer.");
    }
}
