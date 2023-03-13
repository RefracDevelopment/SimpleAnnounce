package me.refracdevelopment.simpleannounce.data;

import lombok.Getter;
import lombok.Setter;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;
import me.refracdevelopment.simpleannounce.utilities.files.Files;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
public class ProfileData {

    private final SimpleAnnounce plugin = SimpleAnnounce.getInstance();
    private final String name;
    private final UUID uuid;

    private final Setting announcements = new Setting("announcements", true, "Toggle announcements");

    public ProfileData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    /**
     * The #load method allows you to
     * load a specified player's data
     */
    public void load(Player player) {
        this.announcements.setToggle(Files.getData().getBoolean("data." + player.getUniqueId().toString() + ".announcements"));
        Files.getData().set("data." + player.getUniqueId().toString() + ".name", player.getName());
        Files.saveData();
    }

    /**
     * The #save method allows you to
     * save a specified player's data
     */
    public void save(Player player) {
        Files.getData().set("data." + player.getUniqueId().toString() + ".announcements", announcements.isToggle());
        Files.saveData();
    }
}