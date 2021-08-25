/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.commands;

import me.refrac.simpleannounce.bungee.*;
import me.refrac.simpleannounce.bungee.utils.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;

public class AnnounceCommand extends Command {
    private final BungeeAnnounce instance;
    
    public AnnounceCommand(BungeeAnnounce instance) {
        super(instance.getFileUtil().getConfig().getString("Commands.ANNOUNCE.COMMAND"), instance.getFileUtil().getConfig().getString("Commands.ANNOUNCE.PERMISSION"),  instance.getFileUtil().getConfig().getString("Commands.ANNOUNCE.ALIAS"));
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            player.sendMessage(Utils.formatComponent("&b&lSimpleAnnounce &7by &bRefrac"));
            player.sendMessage(new TextComponent(""));
            player.sendMessage(Utils.formatComponent("&b/announce <message> &7- Announce your messages"));
            player.sendMessage(Utils.formatComponent("&b/announcereload &7- Reload your config files"));
        }

        if (!(args.length >= 1)) return;

        if (instance.getFileUtil().getConfig().getBoolean("Format.ENABLED")) {
            for (String format : instance.getFileUtil().getConfig().getStringList("Format.LINES")) {
                ProxyServer.getInstance().getPlayers().forEach(p -> p.sendMessage(Utils.formatComponent(format.replace("{arrow}", "»").replace("{message}", stringArrayToString(args)))));
            }
        } else {
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                p.sendMessage(Utils.formatComponent(instance.getFileUtil().getConfig().getString("Prefix") + stringArrayToString(args)));
            }
        }

        if (instance.getFileUtil().getDiscord().getBoolean("Discord.EMBED-MESSAGE")) {
            instance.getDiscordImpl().sendEmbed(stringArrayToString(args).replace("{arrow}", "»"), Color.CYAN);
        } else
            instance.getDiscordImpl().sendMessage(stringArrayToString(args).replace("{arrow}", "»"));
    }

    protected String stringArrayToString(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}