package me.refracdevelopment.simpleannounce.utilities.chat;

import dev.rosewood.rosegarden.utils.HexUtils;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.manager.LocaleManager;
import org.bukkit.Bukkit;

public class Color {

    public static String translate(String source) {
        return HexUtils.colorify(source);
    }

    public static void log(String message) {
        final LocaleManager locale = SimpleAnnounce.getInstance().getManager(LocaleManager.class);

        String prefix = locale.getLocaleMessage("prefix");

        locale.sendCustomMessage(Bukkit.getConsoleSender(), prefix + message);
    }
}