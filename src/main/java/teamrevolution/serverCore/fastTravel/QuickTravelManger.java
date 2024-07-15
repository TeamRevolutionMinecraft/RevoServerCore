package teamrevolution.serverCore.fastTravel;

import org.bukkit.Location;

import java.util.HashMap;

public class QuickTravelManger {
    private HashMap<String, QuickTravelLocation> quickTravelData;

    public QuickTravelManger() {
        this.quickTravelData = new HashMap<>();
    }

    QuickTravelLocation getLocation(String name) {
        return quickTravelData.get(name);
    }

    public void addLocation(Location location, String name) {
        quickTravelData.put(name, new QuickTravelLocation(location));
    }
}
