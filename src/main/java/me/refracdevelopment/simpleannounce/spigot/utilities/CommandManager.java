package me.refracdevelopment.simpleannounce.spigot.utilities;

import me.refracdevelopment.simpleannounce.spigot.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.spigot.command.Command;
import me.refracdevelopment.simpleannounce.spigot.command.commands.AnnounceCommand;
import me.refracdevelopment.simpleannounce.spigot.command.commands.AnnounceReloadCommand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CommandManager {

    private static SimpleAnnounce plugin;

    private CommandManager(SimpleAnnounce plugin) {
        CommandManager.plugin = plugin;
    }

    private static Set<Command> commands = new HashSet<>();

    public static void registerAll(){
        commands.addAll(Arrays.asList(
                new AnnounceCommand(plugin),
                new AnnounceReloadCommand(plugin)
        ));
    }

    public static void register(Command... command){
        commands.addAll(Arrays.asList(command));
    }

    public static Optional<Command> byCommand(String command){
        return commands.stream().filter(all -> {
            if(all.getName().equalsIgnoreCase(command)){
                return true;
            }else{
                for(String alias : all.getNameList()){
                    if(alias.equalsIgnoreCase(command)){
                        return true;
                    }
                }
                return false;
            }
        }).findFirst();
    }

}