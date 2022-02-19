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
package me.refrac.simpleannounce.bungee.utilities;

import me.refrac.simpleannounce.bungee.utilities.files.Discord;
import me.refrac.simpleannounce.bungee.utilities.files.Files;
import me.refrac.simpleannounce.shared.DiscordWebhook;

import java.awt.*;
import java.io.IOException;

public class DiscordImpl {

    public void sendMessage(String content) {
        if (!Discord.DISCORD_ENABLED) return;

        DiscordWebhook webhook = new DiscordWebhook(Discord.DISCORD_WEBHOOK);
        webhook.setUsername(Discord.DISCORD_TITLE);
        webhook.setContent(content);

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmbed(String content, Color color) {
        if (!Files.getDiscord().getBoolean("Discord.ENABLED")) return;

        DiscordWebhook webhook = new DiscordWebhook(Files.getDiscord().getString("Discord.WEBHOOK"));
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(Discord.DISCORD_TITLE)
                .setDescription(content).setColor(color)
                .setFooter(Discord.DISCORD_FOOTER, null));

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}