package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;
import teamrevolution.serverCore.enums.ChatChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preferences implements IProperty{

    private boolean quick_menu;
    private List<ChatChannel> subscribesChannels;

    public Preferences() {
        quick_menu = false;
        this.subscribesChannels = new ArrayList<>();
        this.subscribesChannels.addAll(Arrays.asList(ChatChannel.values()));
    }

    public Preferences(YamlConfiguration configuration) {
        this.quick_menu = configuration.getBoolean("PREF.quick_menu");

        this.subscribesChannels = new ArrayList<>();
        for(String s : configuration.getStringList("PREF.subsChannel")) {
            this.subscribesChannels.add(ChatChannel.valueOf(s));
        }
    }

    @Override
    public String toSQLString(String collumName) {
        return "";
    }

    @Override
    public void updateYamlConfig(YamlConfiguration configuration) {
        configuration.set("PREF.quick_menu", quick_menu);
        configuration.set("PREF.subsChannel", subscribesChannels);
    }


    public boolean isQuick_menu() {
        return quick_menu;
    }

    public void setQuick_menu(boolean quick_menu) {
        this.quick_menu = quick_menu;
    }

    public List<ChatChannel> getSubscribesChannels() {
        return subscribesChannels;
    }

    public void subScripeChannel(ChatChannel c) {
        this.subscribesChannels.add(c);
    }

    public void unSubScribeChannel(ChatChannel c) {
        this.subscribesChannels.remove(c);
    }
}
