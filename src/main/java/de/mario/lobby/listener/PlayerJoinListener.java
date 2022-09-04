package de.mario.lobby.listener;

import de.mario.lobby.LobbyPlugin;
import de.mario.lobby.utils.HideType;
import de.mario.lobby.utils.ItemBuilder;
import de.mario.lobby.utils.SerializedLocation;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author GenauSteuer
 * @since 1.0
 */

public class PlayerJoinListener implements Listener {

    public static Map<Player, HideType> HIDE_MAP = new HashMap<>();

    private final LobbyPlugin lobbyPlugin;

    public PlayerJoinListener(LobbyPlugin lobbyPlugin) {
        this.lobbyPlugin = lobbyPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!HIDE_MAP.containsKey(player)) {
            HIDE_MAP.put(player, HideType.ALL);
        }

        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(HIDE_MAP.get(onlinePlayer).equals(HideType.NONE))
                onlinePlayer.hidePlayer(lobbyPlugin, player);
            else if(HIDE_MAP.get(onlinePlayer).equals(HideType.VIP))
                if(!player.hasPermission("lobby.vip"))
                    onlinePlayer.hidePlayer(lobbyPlugin, player);
            else if(HIDE_MAP.get(onlinePlayer).equals(HideType.ALL))
                onlinePlayer.showPlayer(lobbyPlugin, player);
        }

        player.getInventory().clear();

        player.getInventory().setItem(3, new ItemBuilder(Material.COMPASS).setDisplayName("§e§lNavigator").build());
        player.getInventory().setItem(5, new ItemBuilder(Material.BLAZE_ROD).setDisplayName("§e§lSpieler verstecken").build());

        FileConfiguration fileConfiguration = lobbyPlugin.getConfig();

        if(fileConfiguration.getString("spawn.world") != null) {
            Location spawnLocation = SerializedLocation.fromConfig(fileConfiguration, "spawn");

            if(spawnLocation != null)
                player.teleport(spawnLocation);
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.setFoodLevel(20);
        player.setHealth(20);

        event.setJoinMessage(LobbyPlugin.PREFIX + "§8[§a§l+§8] §e§l" + player.getName() + " §7hat den Server betreten§8.");
    }
}
