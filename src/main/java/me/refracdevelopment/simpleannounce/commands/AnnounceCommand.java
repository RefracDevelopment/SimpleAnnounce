package me.refracdevelopment.simpleannounce.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.google.common.base.Joiner;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.manager.LocaleManager;
import me.refracdevelopment.simpleannounce.utilities.chat.Color;
import me.refracdevelopment.simpleannounce.utilities.chat.Placeholders;
import me.refracdevelopment.simpleannounce.utilities.files.Config;
import me.refracdevelopment.simpleannounce.utilities.files.Discord;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("announce")
@CommandPermission("simpleannounce.command.announce")
public class AnnounceCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void onDefault(CommandSender sender, String[] args) {
        final LocaleManager locale = SimpleAnnounce.getInstance().getManager(LocaleManager.class);

        if (args.length >= 1) {
            String message = Joiner.on(" ").join(args);

            if (Config.FORMAT_ENABLED) {
                for (String format : Config.FORMAT_LINES)
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        locale.sendCustomMessage(p, Placeholders.setPlaceholders(sender, format.replace("%message%", message)));
                    }

                if (Config.FORMAT_SOUND_ENABLED && Config.FORMAT_SOUND_NAME != null) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            p.playSound(p.getLocation(), Sound.valueOf(Config.FORMAT_SOUND_NAME),
                                    (float) Config.FORMAT_SOUND_VOLUME, (float) Config.FORMAT_SOUND_PITCH);
                        } catch (Exception e) {
                            if (p.hasPermission("simpleannounce.admin")) {
                                locale.sendMessage(sender, "unknown-sound-name");
                            }
                            Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
                            Color.log("&cSimpleAnnounce - Sound Error");
                            Color.log("");
                            Color.log("&cThe sound name '" + Config.FORMAT_SOUND_NAME + "' is invalid!");
                            Color.log("&cPlease make sure your sound name is correct you can check this list:");
                            Color.log("&chttps://helpch.at/docs/" + Bukkit.getBukkitVersion().replace("-R0.1-SNAPSHOT", "") + "/org/bukkit/Sound.html");
                            Color.log("");
                            Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
                        }
                    }
                }
            } else {
                String format = locale.getLocaleMessage("prefix") + message;

                for (Player p : Bukkit.getOnlinePlayers()) {
                    locale.sendCustomMessage(p, Placeholders.setPlaceholders(sender, format));
                }
            }

            if (Discord.DISCORD_ENABLED) {
                if (Discord.DISCORD_EMBED) {
                    SimpleAnnounce.getInstance().getDiscordImpl().sendEmbed(message.replace("%arrow%", "»"), java.awt.Color.CYAN);
                } else SimpleAnnounce.getInstance().getDiscordImpl().sendMessage(message.replace("%arrow%", "»"));
            }
        } else {
            if (Config.ANNOUNCE_OUTPUT.equalsIgnoreCase("custom")) {
                Config.ANNOUNCE_MESSAGE.forEach(s -> {
                    locale.sendCustomMessage(sender, Placeholders.setPlaceholders(sender, s));
                });
            } else {
                locale.sendCustomMessage(sender, "");
                locale.sendCustomMessage(sender, "<g:#8A2387:#E94057:#F27121>SimpleAnnounce &8| &f Available Commands:");
                locale.sendCustomMessage(sender, "");
                locale.sendCustomMessage(sender, "&8- &d/announce <message> &7- Allows you to send announcements.");
                locale.sendCustomMessage(sender, "&8- &d/announcereload &7- Reloads the config files.");
                locale.sendCustomMessage(sender, "");
            }
        }
    }
}