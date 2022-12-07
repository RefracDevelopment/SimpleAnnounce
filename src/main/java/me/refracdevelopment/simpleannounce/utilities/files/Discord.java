package me.refracdevelopment.simpleannounce.utilities.files;

public class Discord {
    public static boolean DISCORD_ENABLED;
    public static String DISCORD_WEBHOOK;
    public static boolean DISCORD_EMBED;
    public static String DISCORD_TITLE;
    public static String DISCORD_FOOTER;

    public static void loadDiscord() {
        DISCORD_ENABLED = Files.getDiscord().getBoolean("discord.enabled");
        DISCORD_WEBHOOK = Files.getDiscord().getString("discord.webhook");
        DISCORD_EMBED = Files.getDiscord().getBoolean("discord.embed-message");
        DISCORD_TITLE = Files.getDiscord().getString("discord.title");
        DISCORD_FOOTER = Files.getDiscord().getString("discord.footer");
    }
}