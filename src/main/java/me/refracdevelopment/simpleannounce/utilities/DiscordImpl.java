package me.refracdevelopment.simpleannounce.utilities;

import me.refracdevelopment.simpleannounce.utilities.files.Discord;

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
        if (!Discord.DISCORD_ENABLED) return;

        DiscordWebhook webhook = new DiscordWebhook(Discord.DISCORD_WEBHOOK);
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