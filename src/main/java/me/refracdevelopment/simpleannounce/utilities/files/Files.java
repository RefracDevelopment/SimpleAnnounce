package me.refracdevelopment.simpleannounce.utilities.files;

import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.utilities.chat.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Files {

    private static File discordFile;
    private static FileConfiguration discord;

    private static File dataFile;
    private static FileConfiguration data;

    public static void loadFiles() {
        if (!SimpleAnnounce.getInstance().getDataFolder().exists()) {
            SimpleAnnounce.getInstance().getDataFolder().mkdirs();
        }

        discordFile = new File(SimpleAnnounce.getInstance().getDataFolder(), "discord.yml");
        if (!discordFile.exists()) {
            SimpleAnnounce.getInstance().saveResource("discord.yml", false);
        }
        discord = YamlConfiguration.loadConfiguration(discordFile);

        dataFile = new File(SimpleAnnounce.getInstance().getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            SimpleAnnounce.getInstance().saveResource("data.yml", false);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);

        Config.loadConfig();
        Discord.loadDiscord();

        Color.log("&c==========================================");
        Color.log("&aAll files have been loaded correctly!");
        Color.log("&c==========================================");
    }

    public static FileConfiguration getDiscord() {
        return discord;
    }

    public static FileConfiguration getData() {
        return data;
    }

    public static void saveData() {
        try {
            data.save(dataFile);
        } catch (Exception exception) {
            Color.log("&cFailed to save the data file!");
            exception.printStackTrace();
        }
    }

    public static void reloadFiles() {
        discordFile = new File(SimpleAnnounce.getInstance().getDataFolder(), "discord.yml");
        try {
            discord = YamlConfiguration.loadConfiguration(discordFile);
        } catch (Exception exception) {
            Color.log("&cFailed to reload the discord file!");
            exception.printStackTrace();
        }

        dataFile = new File(SimpleAnnounce.getInstance().getDataFolder(), "data.yml");
        try {
            data = YamlConfiguration.loadConfiguration(dataFile);
        } catch (Exception exception) {
            Color.log("&cFailed to reload the data file!");
            exception.printStackTrace();
        }

        Config.loadConfig();
        Discord.loadDiscord();

        Color.log("&c==========================================");
        Color.log("&aAll files have been loaded correctly!");
        Color.log("&c==========================================");
    }
}