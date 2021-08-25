/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.commands;

import me.refrac.simpleannounce.bungee.*;
import me.refrac.simpleannounce.bungee.tasks.*;
import me.refrac.simpleannounce.bungee.utils.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class AnnounceReloadCommand extends Command {
    private final BungeeAnnounce instance;

    public AnnounceReloadCommand(BungeeAnnounce instance) {
        super(instance.getFileUtil().getConfig().getString("Commands.ANNOUNCE.COMMAND"), instance.getFileUtil().getConfig().getString("Commands.ANNOUNCE.PERMISSION"),  instance.getFileUtil().getConfig().getString("Commands.ANNOUNCE.ALIAS"));
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        instance.getFileUtil().loadConfig();
        instance.getFileUtil().loadDiscord();
        ProxyServer.getInstance().getScheduler().cancel(instance);
        ProxyServer.getInstance().getScheduler().schedule(instance, new AnnounceTask(instance), 0, instance.getFileUtil().getConfig().getInt("Interval"), TimeUnit.SECONDS);
        sender.sendMessage(Utils.formatComponent("&7Config files reloaded. Changes should be live in-game!"));
    }
}