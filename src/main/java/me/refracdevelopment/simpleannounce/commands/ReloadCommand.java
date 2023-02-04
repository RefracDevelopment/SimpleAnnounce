package me.refracdevelopment.simpleannounce.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.manager.LocaleManager;
import me.refracdevelopment.simpleannounce.tasks.AnnounceTask;
import me.refracdevelopment.simpleannounce.utilities.chat.Placeholders;
import me.refracdevelopment.simpleannounce.utilities.files.Config;
import me.refracdevelopment.simpleannounce.utilities.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandAlias("announcereload")
@CommandPermission("simpleannounce.command.reload")
public class ReloadCommand extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        final LocaleManager locale = SimpleAnnounce.getInstance().getManager(LocaleManager.class);

        SimpleAnnounce.getInstance().reload();
        Files.reloadFiles();
        Bukkit.getScheduler().cancelTasks(SimpleAnnounce.getInstance());
        Bukkit.getScheduler().runTaskTimerAsynchronously(SimpleAnnounce.getInstance(), new AnnounceTask(), Config.INTERVAL*20L, Config.INTERVAL*20L);
        locale.sendMessage(sender, "reload", Placeholders.setPlaceholders(sender));
    }
}