package me.refracdevelopment.simpleannounce.data;

import lombok.Getter;
import lombok.Setter;
import me.refracdevelopment.simpleannounce.SimpleAnnounce;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private SimpleAnnounce plugin = SimpleAnnounce.getInstance();

    private ProfileData data;
    private UUID UUID;
    private String playerName;

    public Profile(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
        this.data = new ProfileData(uuid, name);
    }
}