package de.mario.lobby.commands;

import de.mario.lobby.LobbyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author GenauSteuer
 * @since 1.0
 */

public class SetupCommand implements CommandExecutor {

    private final LobbyPlugin lobbyPlugin;

    public SetupCommand(LobbyPlugin lobbyPlugin) {
        this.lobbyPlugin = lobbyPlugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player))
            return false;

        Player player = (Player) commandSender;

        if(!player.hasPermission("lobby.setup")) {
            player.sendMessage(LobbyPlugin.PREFIX + "Du hast keine Rechte für diesen Befehl§8!");
            return false;
        }

        if(args.length != 1) {
            player.sendMessage(LobbyPlugin.PREFIX + "Benutze §8/§e§lsetup spawn§8|§e§lskywars§8|§e§lbedwars§8!");
            return false;
        }

        FileConfiguration fileConfiguration = lobbyPlugin.getConfig();

        switch (args[0].toLowerCase()) {
            case "spawn":
                fileConfiguration.set("spawn.world", player.getWorld().getName());
                fileConfiguration.set("spawn.x", player.getLocation().getX());
                fileConfiguration.set("spawn.y", player.getLocation().getY());
                fileConfiguration.set("spawn.z", player.getLocation().getZ());
                fileConfiguration.set("spawn.yaw", player.getLocation().getYaw());
                fileConfiguration.set("spawn.pitch", player.getLocation().getPitch());

                lobbyPlugin.saveConfig();

                player.sendMessage(LobbyPlugin.PREFIX + "Der Spawn wurde erfolgreich gesetzt§8.");
                break;
            case "skywars":
                fileConfiguration.set("skywars.world", player.getWorld().getName());
                fileConfiguration.set("skywars.x", player.getLocation().getX());
                fileConfiguration.set("skywars.y", player.getLocation().getY());
                fileConfiguration.set("skywars.z", player.getLocation().getZ());
                fileConfiguration.set("skywars.yaw", player.getLocation().getYaw());
                fileConfiguration.set("skywars.pitch", player.getLocation().getPitch());

                lobbyPlugin.saveConfig();

                player.sendMessage(LobbyPlugin.PREFIX + "Der SkyWars Spawn wurde erfolgreich gesetzt§8.");
                break;
            case "bedwars":
                fileConfiguration.set("bedwars.world", player.getWorld().getName());
                fileConfiguration.set("bedwars.x", player.getLocation().getX());
                fileConfiguration.set("bedwars.y", player.getLocation().getY());
                fileConfiguration.set("bedwars.z", player.getLocation().getZ());
                fileConfiguration.set("bedwars.yaw", player.getLocation().getYaw());
                fileConfiguration.set("bedwars.pitch", player.getLocation().getPitch());

                lobbyPlugin.saveConfig();

                player.sendMessage(LobbyPlugin.PREFIX + "Der BedWars Spawn wurde erfolgreich gesetzt§8.");
                break;
            default:
                player.sendMessage(LobbyPlugin.PREFIX + "Benutze §8/§e§lsetup spawn§8|§e§lskywars§8|§e§lbedwars§8!");
                break;
        }



        return false;
    }
}
