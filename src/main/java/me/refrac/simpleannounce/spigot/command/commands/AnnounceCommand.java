/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.command.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refrac.simpleannounce.spigot.*;
import me.refrac.simpleannounce.spigot.command.*;
import me.refrac.simpleannounce.spigot.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.awt.*;

@CommandInfo(name = "announce", description = "Allows you to announce your messages manually", requiresPlayer = true)
public class AnnounceCommand extends CommandFramework {
    private final SimpleAnnounce instance;

    public AnnounceCommand(SimpleAnnounce instance) {
        this.instance = instance;
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Utils.format("&b&lSimpleAnnounce &7by &bRefrac"));
            player.sendMessage("");
            player.sendMessage(Utils.format("&b/announce <message> &7- Allows you to announce your messages manually."));
            player.sendMessage(Utils.format("&b/announcereload &7- Reloads the Simple Announce plugin"));
        }

        if (!(args.length >= 1)) return;

        if (!player.hasPermission(instance.getConfig().getString("Commands.ANNOUNCE.PERMISSION"))) {
            player.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
            return;
        }

        if (instance.getConfig().getBoolean("Format.ENABLED")) {
            for (String format : instance.getConfig().getStringList("Format.LINES")) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(PlaceholderAPI.setPlaceholders(p, Utils.format(format.replace("{arrow}", "»")
                            .replace("{message}", stringArrayToString(args))))));
                } else {
                    Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Utils.format(format.replace("{arrow}", "»")
                            .replace("{message}", stringArrayToString(args)))));
                }
            }

            if (instance.getConfig().getBoolean("Format.SOUND.ENABLED") && instance.getConfig().getString("Format.SOUND.NAME") != null) {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    try {
                        p.playSound(p.getLocation(), Sound.valueOf(instance.getConfig().getString("Format.SOUND.NAME")),
                                (float) instance.getConfig().getInt("Format.SOUND.VOLUME"), (float) instance.getConfig().getInt("Format.SOUND.PITCH"));
                    } catch (Exception e) {
                        if (p.hasPermission("simpleannounce.admin")) {
                            p.sendMessage(ChatColor.RED + "Something went wrong with the Sound Name. Check console for more information.");
                        }
                        Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                        Logger.ERROR.out("SimpleAnnounce - Sound Error");
                        Logger.NONE.out("");
                        Logger.ERROR.out("The Sound '" + instance.getConfig().getString("Format.SOUND.NAME") + "' is invalid!");
                        Logger.ERROR.out("Please make sure your SOUND NAME is correct.");
                        Logger.NONE.out("");
                        Logger.NONE.out(Utils.format("&8&m==&c&m=====&f&m======================&c&m=====&8&m=="));
                    }
                }
            }
        } else {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    p.sendMessage(PlaceholderAPI.setPlaceholders(p, Utils.format(instance.getConfig().getString("Prefix") + stringArrayToString(args).replace("{arrow}", "»"))));
                } else {
                    p.sendMessage(Utils.format(instance.getConfig().getString("Prefix") + stringArrayToString(args).replace("{arrow}", "»")));
                }
            }
        }

        if (instance.getFileUtil().getDiscord().getBoolean("Discord.EMBED-MESSAGE")) {
            instance.getDiscordImpl().sendEmbed(stringArrayToString(args).replace("{arrow}", "»"), Color.CYAN);
        } else
            instance.getDiscordImpl().sendMessage(stringArrayToString(args).replace("{arrow}", "»"));
    }

    protected String stringArrayToString(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if (i != args.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}