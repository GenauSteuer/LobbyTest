package de.mario.lobby.listener;

import de.mario.lobby.LobbyPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author GenauSteuer
 * @since 1.0
 */

public class PlayerQuitListener implements Listener {

    private final LobbyPlugin lobbyPlugin;

    public PlayerQuitListener(LobbyPlugin lobbyPlugin) {
        this.lobbyPlugin = lobbyPlugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        PlayerJoinListener.HIDE_MAP.remove(player);

        event.setQuitMessage(LobbyPlugin.PREFIX + "§8[§c§l-§8] §e§l" + player.getName() + " §7hat den Server verlassen§8.");
    }
}
