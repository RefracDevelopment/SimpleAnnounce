package me.refracdevelopment.simpleannounce.spigot.utilities;

import org.bukkit.Bukkit;

public class VersionCheck {

    public static boolean isOnePointThirteenPlus() {
        if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.18") || Bukkit.getVersion().contains("1.19") || Bukkit.getVersion().contains("1.20")) {
            return true;
        }
        return false;
    }

    public static boolean isOnePointFourteenPlus() {
        if (Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.18") || Bukkit.getVersion().contains("1.19") || Bukkit.getVersion().contains("1.20")) {
            return true;
        }
        return false;
    }

    public static boolean isOnePointSixteenPlus() {
        if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17") || Bukkit.getVersion().contains("1.18") || Bukkit.getVersion().contains("1.19") || Bukkit.getVersion().contains("1.20")) {
            return true;
        }
        return false;
    }
}
