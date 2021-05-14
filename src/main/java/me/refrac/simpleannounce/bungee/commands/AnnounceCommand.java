/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.commands;

import com.google.common.base.Joiner;
import me.refrac.simpleannounce.bungee.BungeeAnnounce;
import me.refrac.simpleannounce.bungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceCommand extends Command {

    public AnnounceCommand() {
        super("announce", "bungeeannounce.use",  "bcast", "broadcast");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length == 0) {
                player.sendMessage(Utils.formatComponent("&b&lBungeeAnnounce &7by Refrac"));
                player.sendMessage(new TextComponent(""));
                player.sendMessage(Utils.formatComponent("&b/announce <message> &7- Announce your messages"));
                player.sendMessage(Utils.formatComponent("&b/announce reload &7- Reload your config files"));
            } else if (args.length >= 1) {
                if (BungeeAnnounce.getConfig().getBoolean("Format.ENABLED")) {
                    String message = Joiner.on(" ").join(args);

                    for (String format : BungeeAnnounce.getConfig().getStringList("Format.LINES")) {
                        ProxyServer.getInstance().getPlayers().forEach(p -> p.sendMessage(Utils.formatComponent(format.replace("{arrow}", "»").replace("{message}", message))));
                    }
                } else {
                    String message = Joiner.on(" ").join(args);

                    for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                        p.sendMessage(Utils.formatComponent(BungeeAnnounce.getConfig().getString("Prefix") + message));
                    }
                }
            }
        }
    }
}