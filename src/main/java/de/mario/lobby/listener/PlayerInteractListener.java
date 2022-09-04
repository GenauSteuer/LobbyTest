package de.mario.lobby.listener;

import de.mario.lobby.LobbyPlugin;
import de.mario.lobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author GenauSteuer
 * @since 1.0
 */

public class PlayerInteractListener implements Listener {

    private final LobbyPlugin lobbyPlugin;

    public PlayerInteractListener(LobbyPlugin lobbyPlugin) {
        this.lobbyPlugin = lobbyPlugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        event.setCancelled(true);

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(event.getItem() == null)
                return;

            if(event.getItem().getItemMeta() == null)
                return;

            if(!event.getItem().getType().equals(Material.COMPASS) || !event.getItem().getType().equals(Material.BLAZE_ROD))
                return;

            Player player = event.getPlayer();

            if(event.getItem().getItemMeta().getDisplayName().contains("Navigator")) {
                Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§e§lNavigator");

                inventory.setItem(2, new ItemBuilder(Material.MAGMA_CREAM).setDisplayName("§a§lSpawn").build());
                inventory.setItem(0, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§b§lSkyWars").build());
                inventory.setItem(4, new ItemBuilder(Material.WHITE_BED).setDisplayName("§c§lBedWars").build());

                inventory.setItem(1, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("§8 ").build());
                inventory.setItem(3, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("§8  ").build());

                player.openInventory(inventory);
            } else if(event.getItem().getItemMeta().getDisplayName().contains("verstecken")) {
                Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§e§lSpieler verstecken");

                inventory.setItem(2, new ItemBuilder(Material.PURPLE_DYE).setDisplayName("§d§lVIP zeigen").build());
                inventory.setItem(0, new ItemBuilder(Material.GREEN_DYE).setDisplayName("§a§lAlle zeigen").build());
                inventory.setItem(4, new ItemBuilder(Material.BLACK_DYE).setDisplayName("§7§lKeinen zeigen").build());

                inventory.setItem(1, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("§8 ").build());
                inventory.setItem(3, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayName("§8  ").build());

                player.openInventory(inventory);
            }
        }
    }
}
