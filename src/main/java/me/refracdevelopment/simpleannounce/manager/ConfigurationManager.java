package me.refracdevelopment.simpleannounce.manager;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.config.RoseSetting;
import dev.rosewood.rosegarden.manager.AbstractConfigurationManager;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Arrays;

public class ConfigurationManager extends AbstractConfigurationManager {

    public enum Setting implements RoseSetting {
        // Config Settings
        INTERVAL("interval", 300, "Set all announcements interval in seconds"),
        ANNOUNCE_OUTPUT("announce-output", "default"),
        ANNOUNCE_MESSAGE("announce-message", Arrays.asList(
                "",
                "<g:#8A2387:#E94057:#F27121>SimpleAnnounce &8| &f Available Commands:",
                "&8- &d/announce <message> &7- Allows you to send announcements.",
                "&8- &d/announcereload &7- Reloads the config files.",
                ""
        )),
        FORMAT_ENABLED("format.enabled", true, "This format is if you want to announce manually with /announce <message>", "If this is disabled the command will have the messages prefix instead"),
        FORMAT_SOUND_ENABLED("format.sound.enabled", false, "Do you want a sound to play?"),
        FORMAT_SOUND_NAME("format.sound.name", "ENTITY_PLAYER_LEVELUP", "Sound names changes through versions so if this doesn't work then that is why"),
        FORMAT_SOUND_VOLUME("format.sound.volume", 1.0, "You should keep both of these the same 1.0 & 1.0 etc"),
        FORMAT_SOUND_PITCH("format.sound.pitch", 1.0),
        FORMAT_LINES("format.lines", Arrays.asList(
                "&8&m-------------------------------------------------",
                "&8%arrow% &b&lSIMPLEANNOUNCE",
                "",
                "&8%arrow% &7%message%&7",
                "",
                "&8&m-------------------------------------------------"
        )),
        ANNOUNCEMENTS("announcements", ConfigurationSection.class),
        ANNOUNCEMENTS_TEST_PERMISSION("announcements.test.permission", "simpleannounce.test", "Do you want only certain players to see this?", "Set this to \"none\" if you want this to be a public announcement"),
        ANNOUNCEMENTS_TEST_SOUND_ENABLED("announcements.test.sound.enabled", false, "Do you want a sound to play?"),
        ANNOUNCEMENTS_TEST_SOUND_NAME("announcements.test.sound.name", "ENTITY_PLAYER_LEVELUP", "Sound names changes through versions so if this doesn't work then that is why"),
        ANNOUNCEMENTS_TEST_SOUND_VOLUME("announcements.test.sound.volume", 1.0, "You should keep both of these the same 1.0 & 1.0 etc"),
        ANNOUNCEMENTS_TEST_SOUND_PITCH("format.sound.pitch", 1.0),
        ANNOUNCEMENTS_TEST_LINES("announcements.test.lines", Arrays.asList(
                "&8&m-------------------------------------------------",
                "&8%arrow% &b&lSIMPLEANNOUNCE &8| &f&lHELLO!",
                "",
                "&8%arrow% &7Thank you for downloading SimpleAnnounce!",
                "&8%arrow% &7This is the default config, edit it to your liking!",
                "",
                "&8%arrow% &7You can visit our discord @ &b&ndiscord.refracdev.ml",
                "&8&m-------------------------------------------------"
        ))
        ;

        private final String key;
        private final Object defaultValue;
        private final String[] comments;
        private Object value = null;

        Setting(String key, Object defaultValue, String... comments) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.comments = comments != null ? comments : new String[0];
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Object getDefaultValue() {
            return this.defaultValue;
        }

        @Override
        public String[] getComments() {
            return this.comments;
        }

        @Override
        public Object getCachedValue() {
            return this.value;
        }

        @Override
        public void setCachedValue(Object value) {
            this.value = value;
        }

        @Override
        public CommentedFileConfiguration getBaseConfig() {
            return SimpleAnnounce.getInstance().getManager(ConfigurationManager.class).getConfig();
        }
    }

    public ConfigurationManager(RosePlugin rosePlugin) {
        super(rosePlugin, Setting.class);
    }

    @Override
    protected String[] getHeader() {
        return new String[]{
                "  ___________                __            _____                                                   ",
                " /   _____/__| _____ ______ |  |   ____   /  _  \\   ____   ____   ____  __ __  ____   ____   ____  ",
                " \\_____  \\|  |/     \\\\____ \\|  | _/ __ \\ /  /_\\  \\ /    \\ /    \\ / __ \\|  |  \\/    \\_/ ___\\_/ __ \\ ",
                " /        \\  |  | |  \\  |_\\ \\  |__  ___/_    |    \\   |  \\   |  \\  \\_\\ )  |  /   |  \\  \\___\\  ___/_",
                "/_______  /__|__|_|  /   ___/____/\\___  /____|__  /___|  /___|  /\\____/|____/|___|  /\\___  /\\___  /",
                "        \\/         \\/|__|             \\/        \\/     \\/     \\/                  \\/     \\/     \\/ "
        };
    }

}