package me.refracdevelopment.simpleannounce.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Setting {

    private String name;
    private boolean toggle;
    private String description;

    public Setting(String name, boolean toggle, String description) {
        this.name = name;
        this.toggle = toggle;
        this.description = description;
    }
}