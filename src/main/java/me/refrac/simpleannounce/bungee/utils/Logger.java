/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */
package me.refrac.simpleannounce.bungee.utils;

import net.md_5.bungee.api.ProxyServer;

public enum Logger {

    NONE('r'), SUCCESS('a'), ERROR('c'), WARNING('e'), INFO('b');

    char color;

    Logger(char color) { this.color = color; }

    public void out(String message) {
        message = Utils.format(String.format("&%c%s", this.color, message));
        ProxyServer.getInstance().getConsole().sendMessage(Utils.formatComponent(message));
    }
}