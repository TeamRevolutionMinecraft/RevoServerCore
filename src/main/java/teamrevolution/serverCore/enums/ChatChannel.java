package teamrevolution.serverCore.enums;

import org.bukkit.ChatColor;

public enum ChatChannel {
    /**
     * Chatchannels. Range = -1 ==> Global
     */
    DISCORD("chatDC", -1, "[DC]", ChatColor.DARK_PURPLE),
    OOC("chatOOC", -1, "[G]", ChatColor.AQUA),
    FLUESTERN("chatFlüstern", 3, "[F]", ChatColor.GRAY),
    REDEN("chatReden", 10, "[R]", ChatColor.GRAY),
    RUFEN("chatRufen", 25, "[S]", ChatColor.GRAY),
    SUPPORT("chatSupport", -1, "[SUPPORT]", ChatColor.DARK_RED),
    SCHREIEN("chatSchreien", 75, "[SCH]", ChatColor.GRAY);



    final String channelName;
    final String prefix;
    final int range;

    ChatChannel(String channelName, int range, String prefix, ChatColor color) {
        this.range = range;
        this.prefix = color + prefix + ChatColor.WHITE;
        this.channelName = channelName;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getRange() {
        return range;
    }
}
