package me.refracdevelopment.simpleannounce.locale;

import dev.rosewood.rosegarden.locale.Locale;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnglishLocale implements Locale {

    @Override
    public String getLocaleName() {
        return "en_US";
    }

    @Override
    public String getTranslatorName() {
        return "Refrac";
    }

    @Override
    public Map<String, Object> getDefaultLocaleValues() {
        return new LinkedHashMap<String, Object>() {{
            this.put("#0", "Plugin Message Prefix");
            this.put("prefix", "<g:#8A2387:#E94057:#F27121>SimpleAnnounce &8| &f");

            this.put("#1", "Generic Command Messages");
            this.put("unknown-command", "Unknown command, use #00B4DB/%cmd%&f help for more info");
            this.put("unknown-sound-name", "&cSomething went wrong with the sound name. Check console for more information.");
        }};
    }
}