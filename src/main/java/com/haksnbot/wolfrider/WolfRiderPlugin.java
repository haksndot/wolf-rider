package com.haksnbot.wolfrider;

import org.bukkit.plugin.java.JavaPlugin;

public class WolfRiderPlugin extends JavaPlugin {

    private WolfMoveHandler moveHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new WolfMountListener(this), this);

        moveHandler = new WolfMoveHandler(this);
        moveHandler.start();

        getLogger().info("WolfRider enabled! Tame a wolf and right-click to ride.");
    }

    @Override
    public void onDisable() {
        if (moveHandler != null) {
            moveHandler.stop();
        }
        getLogger().info("WolfRider disabled.");
    }

    public double getRideSpeed() {
        return getConfig().getDouble("ride-speed", 1.0);
    }

    public boolean requiresBone() {
        return getConfig().getBoolean("require-bone", true);
    }

    public boolean isOwnerOnly() {
        return getConfig().getBoolean("owner-only", true);
    }
}
