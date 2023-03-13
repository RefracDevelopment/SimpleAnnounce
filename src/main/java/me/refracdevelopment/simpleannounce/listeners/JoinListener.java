package me.refracdevelopment.simpleannounce.listeners;

import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.data.Profile;
import me.refracdevelopment.simpleannounce.utilities.Permissions;
import me.refracdevelopment.simpleannounce.utilities.Utilities;
import me.refracdevelopment.simpleannounce.utilities.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        try {
            SimpleAnnounce.getInstance().getProfileManager().handleProfileCreation(event.getUniqueId(), event.getName());
        } catch (NullPointerException exception) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, "&cERROR: Could not create profile!");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = SimpleAnnounce.getInstance().getProfileManager().getProfile(player.getUniqueId());

        try {
            Bukkit.getScheduler().runTaskAsynchronously(SimpleAnnounce.getInstance(), () -> profile.getData().load(player));
        } catch (NullPointerException exception) {
            player.kickPlayer(ChatColor.RED + "ERROR: Profile returned null.");
            return;
        }

        if (profile == null || profile.getData() == null) {
            player.kickPlayer(ChatColor.RED + "ERROR: Profile returned null.");
            return;
        }

        if (player.getUniqueId().equals(Utilities.getDevUUID)) {
            Utilities.sendDevMessage(player);
        } else if (player.getUniqueId().equals(Utilities.getDevUUID2)) {
            Utilities.sendDevMessage(player);
        }

        if (Config.UPDATE_ON_JOIN) {
            if (!player.hasPermission(Permissions.UPDATE_ON_JOIN)) return;

            SimpleAnnounce.getInstance().updateCheck(player, false);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = SimpleAnnounce.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile == null) return;
        if (profile.getData() == null) return;

        Bukkit.getScheduler().runTaskAsynchronously(SimpleAnnounce.getInstance(), () -> profile.getData().save(player));
    }
}