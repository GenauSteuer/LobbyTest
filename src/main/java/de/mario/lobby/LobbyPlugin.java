package de.mario.lobby;

import de.mario.lobby.commands.SetupCommand;
import de.mario.lobby.listener.*;
import de.mario.lobby.utils.HideType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mario
 * @since 1.0
 */

public class LobbyPlugin extends JavaPlugin {

    public static String PREFIX = "§a§lLobby §8| §7";

    private LobbyPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        File path = new File("plugins/Lobby");

        if(!path.exists())
            path.mkdirs();

        this.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        this.getCommand("setup").setExecutor(new SetupCommand(this));
    }

    @Override
    public void onDisable() {
        PlayerJoinListener.HIDE_MAP.clear();
    }

    public LobbyPlugin getInstance() {
        return instance;
    }
}
