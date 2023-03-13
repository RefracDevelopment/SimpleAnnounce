package me.refracdevelopment.simpleannounce.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.data.ProfileData;
import me.refracdevelopment.simpleannounce.utilities.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("announcetoggle|toggleannouncements")
@CommandPermission(Permissions.TOGGLE_COMMAND)
public class ToggleCommand extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return;

        Player player = (Player) sender;
        ProfileData profile = SimpleAnnounce.getInstance().getProfileManager().getProfile(player.getUniqueId()).getData();

        if (profile.getAnnouncements().isToggle()) {
            profile.getAnnouncements().setToggle(false);
            Bukkit.getScheduler().runTaskAsynchronously(SimpleAnnounce.getInstance(), () -> profile.save(player));
        } else {
            profile.getAnnouncements().setToggle(true);
            Bukkit.getScheduler().runTaskAsynchronously(SimpleAnnounce.getInstance(), () -> profile.save(player));
        }
    }
}