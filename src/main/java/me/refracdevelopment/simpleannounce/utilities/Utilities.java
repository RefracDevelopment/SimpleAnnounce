package me.refracdevelopment.simpleannounce.utilities;

import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.manager.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utilities {

    public static final UUID getDevUUID = UUID.fromString("d9c670ed-d7d5-45fb-a144-8b8be86c4a2d");
    public static final UUID getDevUUID2 = UUID.fromString("ab898e40-9088-45eb-9d69-e0b78e872627");

    public static void sendDevMessage(Player player) {
        final LocaleManager locale = SimpleAnnounce.getInstance().getManager(LocaleManager.class);

        player.sendMessage("");
        locale.sendCustomMessage(player, "&aWelcome " + SimpleAnnounce.getInstance().getDescription().getName() + " Developer!");
        locale.sendCustomMessage(player, "&aThis server is currently running " + SimpleAnnounce.getInstance().getDescription().getName() + " &bv" + SimpleAnnounce.getInstance().getDescription().getVersion() + "&a.");
        locale.sendCustomMessage(player, "&aPlugin name&7: &f" + SimpleAnnounce.getInstance().getDescription().getName());
        player.sendMessage("");
        locale.sendCustomMessage(player, "&aServer version&7: &f" + Bukkit.getVersion());
        player.sendMessage("");
    }

}