package me.refracdevelopment.simpleannounce.tasks;

import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.data.ProfileData;
import me.refracdevelopment.simpleannounce.manager.LocaleManager;
import me.refracdevelopment.simpleannounce.utilities.Permissions;
import me.refracdevelopment.simpleannounce.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.utilities.chat.Placeholders;
import me.refracdevelopment.simpleannounce.utilities.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AnnounceTask implements Runnable {

    public void run() {
        Set<String> broadcastList = Config.ANNOUNCEMENTS.getKeys(false);

        if (broadcastList.isEmpty()) {
            Color.log("&eThere are no announcements :(");
            Bukkit.getScheduler().cancelTasks(SimpleAnnounce.getInstance());
            return;
        }

        final LocaleManager locale = SimpleAnnounce.getInstance().getManager(LocaleManager.class);

        String broadcastId = getRandom(broadcastList);
        ConfigurationSection broadcast = Config.ANNOUNCEMENTS.getConfigurationSection(broadcastId);

        if (broadcast == null) {
            Bukkit.getScheduler().cancelTasks(SimpleAnnounce.getInstance());
            return;
        }

        for (String message : broadcast.getStringList("lines")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ProfileData profile = SimpleAnnounce.getInstance().getProfileManager().getProfile(p.getUniqueId()).getData();

                if (profile.getAnnouncements().isToggle()) continue;

                if (p.hasPermission(Objects.requireNonNull(broadcast.getString("permission"))) && !broadcast.getString("permission").equalsIgnoreCase("none")) {
                    locale.sendCustomMessage(p, Placeholders.setPlaceholders(p, message));
                    if (Config.BUNGEECORD) {
                        SimpleAnnounce.getInstance().getPluginMessage().sendMessage(Placeholders.setPlaceholders(p, message));
                    }
                } else if (broadcast.getString("permission").equalsIgnoreCase("none")) {
                    locale.sendCustomMessage(p, Placeholders.setPlaceholders(p, message));
                    if (Config.BUNGEECORD) {
                        SimpleAnnounce.getInstance().getPluginMessage().sendMessage(Placeholders.setPlaceholders(p, message));
                    }
                }
            }
        }

        if (broadcast.getBoolean("sound.enabled")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ProfileData profile = SimpleAnnounce.getInstance().getProfileManager().getProfile(p.getUniqueId()).getData();

                if (profile.getAnnouncements().isToggle()) continue;

                try {
                    if (p.hasPermission(Objects.requireNonNull(broadcast.getString("permission"))) && !broadcast.getString("permission").equalsIgnoreCase("none")) {
                        p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("sound.name")),
                                (float) broadcast.getInt("sound.volume"), (float) broadcast.getInt("sound.pitch"));
                    } else if (broadcast.getString("permission").equalsIgnoreCase("none")) {
                        p.playSound(p.getLocation(), Sound.valueOf(broadcast.getString("sound.name")),
                                (float) broadcast.getInt("sound.volume"), (float) broadcast.getInt("sound.pitch"));
                    }
                } catch (Exception e) {
                    if (p.hasPermission("simpleannounce.admin")) {
                        locale.sendMessage(p, "unknown-sound-name", Placeholders.setPlaceholders(p));
                    }
                    Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
                    Color.log("&cSimpleAnnounce - Sound Error");
                    Color.log("");
                    Color.log("&cThe sound name '" + broadcast.getString("sound.name") + "' is invalid!");
                    Color.log("&cPlease make sure your sound name is correct you can check this list:");
                    Color.log("&chttps://helpch.at/docs/" + Bukkit.getBukkitVersion().replace("-R0.1-SNAPSHOT", "") + "/org/bukkit/Sound.html");
                    Color.log("");
                    Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
                }
            }
        }
    }

    private String getRandom(Set<String> set) {
        int index = ThreadLocalRandom.current().nextInt(set.size());
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}