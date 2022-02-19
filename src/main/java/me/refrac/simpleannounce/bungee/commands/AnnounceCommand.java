/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 RefracDevelopment
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package me.refrac.simpleannounce.bungee.commands;

import me.refrac.simpleannounce.bungee.*;
import me.refrac.simpleannounce.bungee.utilities.files.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;

public class AnnounceCommand extends Command {

    public AnnounceCommand() {
        super(Config.ANNOUNCE_COMAND, Config.ANNOUNCE_PERMISSION, Config.ANNOUNCE_ALIAS);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            player.sendMessage(me.refrac.simpleannounce.bungee.utilities.chat.Color.translate("&b&lSimpleAnnounce &7by &bRefrac"));
            player.sendMessage(new TextComponent(""));
            player.sendMessage(me.refrac.simpleannounce.bungee.utilities.chat.Color.translate("&b/announce <message> &7- Announce your messages"));
            player.sendMessage(me.refrac.simpleannounce.bungee.utilities.chat.Color.translate("&b/announcereload &7- Reload your config files"));
        }

        if (args.length >= 1) {
            if (Config.FORMAT_ENABLED) {
                for (String format : Config.FORMAT_LINES) {
                    ProxyServer.getInstance().getPlayers().forEach(p -> p.sendMessage(me.refrac.simpleannounce.bungee.utilities.chat.Color.translate(p, format.replace("{message}", stringArrayToString(args)))));
                }
            } else {
                ProxyServer.getInstance().getPlayers().forEach(p -> p.sendMessage(me.refrac.simpleannounce.bungee.utilities.chat.Color.translate(p, Config.PREFIX + stringArrayToString(args))));
            }

            if (Discord.DISCORD_EMBED) {
                BungeeAnnounce.getInstance().getDiscordImpl().sendEmbed(stringArrayToString(args).replace("{arrow}", "\u00BB"), Color.CYAN);
            } else
                BungeeAnnounce.getInstance().getDiscordImpl().sendMessage(stringArrayToString(args).replace("{arrow}", "\u00BB"));
        }
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