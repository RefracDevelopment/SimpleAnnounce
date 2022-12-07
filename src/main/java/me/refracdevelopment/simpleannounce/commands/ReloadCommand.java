package me.refracdevelopment.simpleannounce.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.manager.LocaleManager;
import me.refracdevelopment.simpleannounce.utilities.chat.Placeholders;
import org.bukkit.command.CommandSender;

@CommandAlias("announcereload")
@CommandPermission("simpleannounce.command.reload")
public class ReloadCommand extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        final LocaleManager locale = SimpleAnnounce.getInstance().getManager(LocaleManager.class);

        SimpleAnnounce.getInstance().reload();
        locale.sendMessage(sender, "reload", Placeholders.setPlaceholders(sender));
    }
}