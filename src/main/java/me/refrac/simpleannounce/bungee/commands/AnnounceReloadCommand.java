/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.commands;

import me.refrac.simpleannounce.bungee.BungeeAnnounce;
import me.refrac.simpleannounce.bungee.tasks.AnnounceTask;
import me.refrac.simpleannounce.bungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceReloadCommand extends Command {

    private final BungeeAnnounce instance;

    public AnnounceReloadCommand(BungeeAnnounce instance) {
        super("announcereload", "simpleannounce.admin");
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        instance.loadConfig();
        ProxyServer.getInstance().getScheduler().cancel(instance);
        ProxyServer.getInstance().getScheduler().schedule(instance, new AnnounceTask(instance), 0, instance.getConfig().getInt("Interval"), TimeUnit.SECONDS);
        sender.sendMessage(Utils.formatComponent("&7Config files reloaded. Changes should be live in-game!"));
    }
}