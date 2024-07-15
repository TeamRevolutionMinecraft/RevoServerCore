package teamrevolution.serverCore.utils;

import org.bukkit.entity.Player;
import teamrevolution.serverCore.enums.Rank;

public class LuckyPermsIntegration {
    /**
     * returns the rank from the Player
     * @param player player that is need to be accessed
     * @return the rank [BUG rank if player has no correct rank]
     */
    public static Rank getRankFromPlayer(Player player) {
        for (Rank rank : Rank.values()) {
            if (player.hasPermission("revo.rank." + rank.getRankName())) {
                return rank;
            }
        }
        return Rank.BUG;
    }
}
