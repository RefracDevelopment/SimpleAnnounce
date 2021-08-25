/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.commands;

import me.refrac.simpleannounce.bungee.*;
import me.refrac.simpleannounce.bungee.utils.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class AnnounceDevCommand extends Command implements Listener {
    private final BungeeAnnounce instance;

    public AnnounceDevCommand(BungeeAnnounce instance) {
        super("announcedev");
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.getName().equalsIgnoreCase("Zachzdb")) {
            sender.sendMessage(Utils.formatComponent("&cYou must be a developer to use this command."));
            return;
        }

        sender.sendMessage(Utils.formatComponent(""));
        sender.sendMessage(Utils.formatComponent("&fHello!"));
        sender.sendMessage(Utils.formatComponent("&7Server Version - &f" + ProxyServer.getInstance().getVersion()));
        sender.sendMessage(Utils.formatComponent("&7Plugin Name - &f" + Utils.getName));
        sender.sendMessage(Utils.formatComponent("&7Plugin Version - &f" + Utils.getVersion));
        sender.sendMessage(Utils.formatComponent("&7End of log."));
        sender.sendMessage(Utils.formatComponent(""));
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if (!player.getName().equalsIgnoreCase("Zachzdb")) return;

        player.sendMessage(Utils.formatComponent(""));
        player.sendMessage(Utils.formatComponent("&cRefrac Support Debug Message"));
        player.sendMessage(Utils.formatComponent(""));
        player.sendMessage(Utils.formatComponent("&aThis server is running " + Utils.getName + " v" + Utils.getVersion));
        player.sendMessage(Utils.formatComponent("&aRunning " + ProxyServer.getInstance().getVersion()));
        player.sendMessage(Utils.formatComponent(""));
    }
}