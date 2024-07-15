package teamrevolution.serverCore.fastTravel;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class QuickTravelLocation {
    private Location location;

    public QuickTravelLocation(Location location) {
        this.location = location;
    }

    boolean pullPlayer(Player player, int cost) {

        if (player.getLevel() < cost) return false;

        player.teleport(this.location);
        return true;
    }
    public Location getLocation() {
        return location;
    }

}
