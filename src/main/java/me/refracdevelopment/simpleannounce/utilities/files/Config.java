package me.refracdevelopment.simpleannounce.utilities.files;

import me.refracdevelopment.simpleannounce.manager.ConfigurationManager;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class Config {
    // General
    public static long INTERVAL;
    public static boolean BUNGEECORD;
    public static boolean UPDATE_ON_JOIN;

    // Help Command
    public static String ANNOUNCE_OUTPUT;
    public static List<String> ANNOUNCE_MESSAGE;

    // Format
    public static boolean FORMAT_ENABLED;
    public static boolean FORMAT_SOUND_ENABLED;
    public static String FORMAT_SOUND_NAME;
    public static int FORMAT_SOUND_VOLUME;
    public static int FORMAT_SOUND_PITCH;
    public static List<String> FORMAT_LINES;

    // Announcements
    public static ConfigurationSection ANNOUNCEMENTS;

    public static void loadConfig() {
        // General
        INTERVAL = ConfigurationManager.Setting.INTERVAL.getLong();
        BUNGEECORD = ConfigurationManager.Setting.BUNGEECORD.getBoolean();

        // Help Command
        ANNOUNCE_OUTPUT = ConfigurationManager.Setting.ANNOUNCE_OUTPUT.getString();
        ANNOUNCE_MESSAGE = ConfigurationManager.Setting.ANNOUNCE_MESSAGE.getStringList();

        // Format
        FORMAT_ENABLED = ConfigurationManager.Setting.FORMAT_ENABLED.getBoolean();
        FORMAT_SOUND_ENABLED = ConfigurationManager.Setting.FORMAT_SOUND_ENABLED.getBoolean();
        FORMAT_SOUND_NAME = ConfigurationManager.Setting.FORMAT_SOUND_NAME.getString();
        FORMAT_SOUND_VOLUME = ConfigurationManager.Setting.FORMAT_SOUND_VOLUME.getInt();
        FORMAT_SOUND_PITCH = ConfigurationManager.Setting.FORMAT_SOUND_PITCH.getInt();
        FORMAT_LINES = ConfigurationManager.Setting.FORMAT_LINES.getStringList();

        // Announcements
        ANNOUNCEMENTS = ConfigurationManager.Setting.ANNOUNCEMENTS.getSection();
    }
}