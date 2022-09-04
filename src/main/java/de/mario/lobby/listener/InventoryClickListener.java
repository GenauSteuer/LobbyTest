package de.mario.lobby.listener;

import de.mario.lobby.LobbyPlugin;
import de.mario.lobby.utils.HideType;
import de.mario.lobby.utils.SerializedLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author GenauSteuer
 * @since 1.0
 */

public class InventoryClickListener implements Listener {

    private final LobbyPlugin lobbyPlugin;

    public InventoryClickListener(LobbyPlugin lobbyPlugin) {
        this.lobbyPlugin = lobbyPlugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
         if(event.getClickedInventory() == null)
             return;

         if(event.getCurrentItem() == null)
             return;

         if(!event.getCurrentItem().getType().equals(Material.GREEN_DYE) || !event.getCurrentItem().getType().equals(Material.BLACK_DYE) || !event.getCurrentItem().getType().equals(Material.PURPLE_DYE) || !event.getCurrentItem().getType().equals(Material.GRASS_BLOCK) || !event.getCurrentItem().getType().equals(Material.WHITE_BED) || !event.getCurrentItem().getType().equals(Material.MAGMA_CREAM))
             return;

        Player player = (Player) event.getWhoClicked();

         if(event.getCurrentItem().getType().equals(Material.MAGMA_CREAM)) {
             if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Spawn")) {
                 player.closeInventory();

                 Location spawnLocation = SerializedLocation.fromConfig(lobbyPlugin.getConfig(), "spawn");

                 if(spawnLocation == null) {
                     player.sendMessage(LobbyPlugin.PREFIX + "Die Spawn Location wurde noch nicht gesetzt§8.");
                     return;
                 }

                 player.teleport(spawnLocation);
                 player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
             }
         } else if(event.getCurrentItem().getType().equals(Material.GRASS_BLOCK)) {
             if(event.getCurrentItem().getItemMeta().getDisplayName().contains("SkyWars")) {
                 player.closeInventory();

                 Location skyWarsLocation = SerializedLocation.fromConfig(lobbyPlugin.getConfig(), "skywars");

                 if(skyWarsLocation == null) {
                     player.sendMessage(LobbyPlugin.PREFIX + "Die SkyWars Location wurde noch nicht gesetzt§8.");
                     return;
                 }

                 player.teleport(skyWarsLocation);
                 player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
             }
         } else if(event.getCurrentItem().getType().equals(Material.WHITE_BED)) {
             if(event.getCurrentItem().getItemMeta().getDisplayName().contains("BedWars")) {
                 player.closeInventory();

                 Location bedWarsLocation = SerializedLocation.fromConfig(lobbyPlugin.getConfig(), "bedwars");

                 if(bedWarsLocation == null) {
                     player.sendMessage(LobbyPlugin.PREFIX + "Die BedWars Location wurde noch nicht gesetzt§8.");
                     return;
                 }

                 player.teleport(bedWarsLocation);
                 player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
             }
         } else if(event.getCurrentItem().getType().equals(Material.GREEN_DYE)) {
             if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Alle")) {
                 player.closeInventory();
                 player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

                 PlayerJoinListener.HIDE_MAP.put(player, HideType.ALL);

                 for(Player onlinePlayer : Bukkit.getOnlinePlayers())
                     player.showPlayer(lobbyPlugin, onlinePlayer);
             }
         } else if(event.getCurrentItem().getType().equals(Material.BLACK_DYE)) {
             if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Keinen")) {
                 player.closeInventory();
                 player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

                 PlayerJoinListener.HIDE_MAP.put(player, HideType.NONE);

                 for(Player onlinePlayer : Bukkit.getOnlinePlayers())
                     player.hidePlayer(lobbyPlugin, onlinePlayer);
             }
         } else if(event.getCurrentItem().getType().equals(Material.PURPLE_DYE)) {
             if(event.getCurrentItem().getItemMeta().getDisplayName().contains("VIP")) {
                 player.closeInventory();
                 player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

                 PlayerJoinListener.HIDE_MAP.put(player, HideType.VIP);

                 for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                     if(!onlinePlayer.hasPermission("lobby.vip"))
                         player.hidePlayer(lobbyPlugin, onlinePlayer);
                     else
                         player.showPlayer(lobbyPlugin, onlinePlayer);
                 }
             }
         }
    }
}
