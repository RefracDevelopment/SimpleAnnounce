package me.refracdevelopment.simpleannounce.utilities.files;

import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.utilities.chat.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Files {

    private static File discordFile;
    private static FileConfiguration discord;

    public static void loadFiles() {
        if (!SimpleAnnounce.getInstance().getDataFolder().exists()) {
            SimpleAnnounce.getInstance().getDataFolder().mkdirs();
        }

        discordFile = new File(SimpleAnnounce.getInstance().getDataFolder(), "discord.yml");
        if (!discordFile.exists()) {
            SimpleAnnounce.getInstance().saveResource("discord.yml", false);
        }
        discord = YamlConfiguration.loadConfiguration(discordFile);

        Config.loadConfig();
        Discord.loadDiscord();

        Color.log("&c==========================================");
        Color.log("&aAll files have been loaded correctly!");
        Color.log("&c==========================================");
    }

    public static FileConfiguration getDiscord() {
        return discord;
    }

    public static void reloadFiles() {
        discordFile = new File(SimpleAnnounce.getInstance().getDataFolder(), "discord.yml");
        try {
            discord = YamlConfiguration.loadConfiguration(discordFile);
        } catch (Exception exception) {
            Color.log("&cFailed to reload the discord file!");
            exception.printStackTrace();
        }

        Config.loadConfig();
        Discord.loadDiscord();

        Color.log("&c==========================================");
        Color.log("&aAll files have been loaded correctly!");
        Color.log("&c==========================================");
    }
}