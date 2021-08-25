package me.refrac.simpleannounce.spigot.utils;

import me.refrac.simpleannounce.shared.DiscordWebhook;
import me.refrac.simpleannounce.spigot.SimpleAnnounce;

import java.awt.*;
import java.io.IOException;

public class DiscordImpl {
    private final SimpleAnnounce instance;

    public DiscordImpl(SimpleAnnounce instance) {
        this.instance = instance;
    }

    public void sendMessage(String content) {
        if (!instance.getFileUtil().getDiscord().getBoolean("Discord.ENABLED")) return;

        DiscordWebhook webhook = new DiscordWebhook(instance.getFileUtil().getDiscord().getString("Discord.WEBHOOK"));
        webhook.setUsername(instance.getFileUtil().getDiscord().getString("Discord.TITLE"));
        webhook.setContent(content);

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmbed(String content, Color color) {
        if (!instance.getFileUtil().getDiscord().getBoolean("Discord.ENABLED")) return;

        DiscordWebhook webhook = new DiscordWebhook(instance.getFileUtil().getDiscord().getString("Discord.WEBHOOK"));
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(instance.getFileUtil().getDiscord().getString("Discord.TITLE"))
                .setDescription(content).setColor(color)
                .setFooter(instance.getFileUtil().getDiscord().getString("Discord.FOOTER"), null));

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}