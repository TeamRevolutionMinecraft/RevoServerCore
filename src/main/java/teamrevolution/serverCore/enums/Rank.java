package teamrevolution.serverCore.enums;

import org.bukkit.ChatColor;

/**
 * @deprecated TODO Rewrite to Adventure API. Also maybe move to domain library?
 */
public enum Rank {
    TEAM(ChatColor.GOLD, "[TEAM] ", "team"),
    SPIELLEITER(ChatColor.AQUA, "[SPIELLEITER] ", "spielleiter"),
    ABENTEURER(ChatColor.BLUE, "[ABENTEURER] ", "abenteurer"),
    ERKUNDER(ChatColor.GRAY, "[ERKUNDER] ", "erkunder"),
    BUG(ChatColor.DARK_RED, "Bug ", "bug");

    private final String chatPrefix;
    private final String rankName;
    Rank(ChatColor color, String prefix, String rankName) {
        this.chatPrefix = color + prefix + ChatColor.WHITE;
        this.rankName = rankName;
    }

    public String getChatPrefix() {
        return chatPrefix;
    }
    public String getRankName() {
        return rankName;
    }
}
