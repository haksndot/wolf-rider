package com.haksnbot.wolfrider;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class WolfMoveHandler {

    private final WolfRiderPlugin plugin;
    private BukkitTask task;

    private static final double BASE_SPEED = 0.3;

    public WolfMoveHandler(WolfRiderPlugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    Entity vehicle = player.getVehicle();
                    if (vehicle instanceof Wolf wolf) {
                        handleWolfMovement(player, wolf);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    private void handleWolfMovement(Player player, Wolf wolf) {
        if (plugin.requiresBone()) {
            if (player.getInventory().getItemInMainHand().getType() != Material.BONE) {
                return;
            }
        }

        Location playerLoc = player.getLocation();
        Vector direction = playerLoc.getDirection();

        direction.setY(0).normalize();

        double speed = BASE_SPEED * plugin.getRideSpeed();
        Vector velocity = direction.multiply(speed);

        velocity.setY(wolf.getVelocity().getY());

        if (wolf.isOnGround() && player.getLocation().getPitch() < -30) {
            velocity.setY(0.5);
        }

        wolf.setVelocity(velocity);

        float yaw = playerLoc.getYaw();
        Location wolfLoc = wolf.getLocation();
        wolfLoc.setYaw(yaw);
        wolf.setRotation(yaw, 0);
    }
}
