package de.mario.lobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author GenauSteuer
 * @since 1.0
 */

public class SerializedLocation {

    private String worldName;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public SerializedLocation(Location location) {
        this.worldName = location.getWorld().getName();

        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();

        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public static Location fromConfig(FileConfiguration configuration, String location) {
        if(configuration.getString(location + ".world") == null)
            return null;

        String worldName = configuration.getString(location + ".world");

        double x = configuration.getDouble(location + ".x");
        double y = configuration.getDouble(location + ".y");
        double z = configuration.getDouble(location + ".z");

        float yaw = (float) configuration.getDouble(location + ".yaw");
        float pitch = (float) configuration.getDouble(location + ".pitch");

        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public String getWorldName() {
        return worldName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
