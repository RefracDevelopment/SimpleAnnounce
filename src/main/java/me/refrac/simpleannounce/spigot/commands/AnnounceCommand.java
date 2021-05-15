/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.spigot.commands;

import com.google.common.base.Joiner;
import me.refrac.simpleannounce.spigot.SimpleAnnounce;
import me.refrac.simpleannounce.spigot.utils.Logger;
import me.refrac.simpleannounce.spigot.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zachary Baldwin / Refrac
 */
public class AnnounceCommand implements CommandExecutor {

    private final SimpleAnnounce instance;

    public AnnounceCommand(SimpleAnnounce instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Utils.format("&b&lSimpleAnnounce &7by Refrac"));
            player.sendMessage("");
            player.sendMessage(Utils.format("&b/announce <message> &7- Announce your messages"));
            player.sendMessage(Utils.format("&b/announce reload &7- Reload your config files"));
        } else if (args.length >= 1) {
            if (instance.getConfig().getBoolean("Format.ENABLED")) {
                if (!player.hasPermission("simpleannounce.use")) {
                    player.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
                    return false;
                }

                String message = Joiner.on(" ").join(args);

                for (String format : instance.getConfig().getStringList("Format.LINES")) {
                    Bukkit.getOnlinePlayers().forEach((p -> p.sendMessage(Utils.format(format.replace("{arrow}", "»")
                            .replace("{message}", message)))));
                }

                if (instance.getConfig().getBoolean("Format.SOUND.ENABLED") && instance.getConfig().getString("Format.SOUND.NAME") != null) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        try {
                            p.playSound(p.getLocation(), Sound.valueOf(instance.getConfig().getString("Format.SOUND.NAME")), 5.0F, 5.0F);
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
                if (!player.hasPermission("simpleannounce.use")) {
                    player.sendMessage(Utils.format("&cYou don't have permission to execute this command."));
                    return false;
                }

                String message = Joiner.on(" ").join(args);

                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.sendMessage(Utils.format(instance.getConfig().getString("Prefix") + message));
                }
            }
        }
        return false;
    }
}