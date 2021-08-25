/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.command.commands;

import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.command.*;
import me.refrac.simpleannounce.spigot.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@CommandInfo(name = "announcdev", description = "Allows the developer to debug problems if needed", requiresPlayer = true, requiresDev = true)
public class AnnounceDevCommand extends CommandFramework implements Listener {
    private final SimpleAnnounce instance;

    public AnnounceDevCommand(SimpleAnnounce instance) {
        this.instance = instance;
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(Utils.format(""));
        player.sendMessage(Utils.format("&fHello!"));
        player.sendMessage(Utils.format("&7Server Version - &f" + Bukkit.getServer().getVersion()));
        player.sendMessage(Utils.format("&7Plugin Name - &f" + Utils.getName));
        player.sendMessage(Utils.format("&7Plugin Version - &f" + Utils.getVersion));
        player.sendMessage(Utils.format("&7End of log."));
        player.sendMessage(Utils.format(""));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.getName().equalsIgnoreCase("Zachzdb")) return;

        player.sendMessage("");
        player.sendMessage(Utils.format("&cRefrac Support Debug Message"));
        player.sendMessage("");
        player.sendMessage(Utils.format("&aThis server is running " + Utils.getName + " v" + Utils.getVersion));
        player.sendMessage(Utils.format("&aRunning version " + Bukkit.getServer().getVersion()));
        player.sendMessage("");
    }
}