package me.refracdevelopment.simpleannounce.spigot.command;

import me.refracdevelopment.simpleannounce.spigot.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.*;

public abstract class Command extends org.bukkit.command.Command implements Comparable<Command>, Executable {

    private final String name;
    private final Set<String> aliases;
    private String usage;
    private int argsLength;


    public Command(String name, String... aliases){
        this(0, "", name, aliases);
    }

    public Command(int argsLength, String usage, String name, String... aliases) {
        super(name, "", usage, Arrays.asList(aliases));

        this.name = name;
        this.argsLength = argsLength;
        this.usage = Color.translate(usage);

        this.aliases = new HashSet<>();
        this.aliases.add(name);
        Collections.addAll(this.aliases, aliases);

        registerBukkitCommand(aliases);
    }

    private void registerBukkitCommand(String[] aliases){
        try{
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("command", this);
            for(String alias : aliases) {
                commandMap.register(alias, "command", this);
            }
        } catch (NoSuchFieldException  | IllegalArgumentException | IllegalAccessException exception){
            Color.log("&cCould not register a command properly (name: " + this.name + "), stacktrace: ");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getUsage() {
        return this.usage;
    }

    @Override
    public int getArgsLength() {
        return this.argsLength;
    }

    @Override
    public Set<String> getNameList() {
        return this.aliases;
    }

    @Override
    public boolean hasUsage() {
        return !this.usage.isEmpty();
    }

    @Override
    public Command setArgsLength(int argsLength) {
        this.argsLength = argsLength;
        return this;
    }

    @Override
    public Command setUsage(String usage) {
        this.usage = Color.translate(usage);
        return this;
    }

}
