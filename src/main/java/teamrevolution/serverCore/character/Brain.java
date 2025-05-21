package teamrevolution.serverCore.character;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Brain implements IProperty{
    private final String PREFIX = "BRAIN.";
    private final String KNOWN_PLAYERS = PREFIX + "known_uuids";

    private final List<RevoPlayer> known_players;

    public Brain(){
        this.known_players = new ArrayList<>();
    }
    public Brain(YamlConfiguration configuration){

        List<String> uuidStrings = configuration.getStringList(KNOWN_PLAYERS);

        this.known_players = new ArrayList<>();
        for (String uuidString : uuidStrings) {
            UUID uuid = UUID.fromString(uuidString);
            known_players.add(new RevoPlayer(uuid));
        }
    }

    public boolean doIknow(RevoPlayer player) {
        return known_players.contains(player);
    }

    @Override
    public String toSQLString(String collumName) {
        return "";
    }

    @Override
    public void updateYamlConfig(YamlConfiguration configuration) {
        List<UUID> uuids = new ArrayList<>();
        for (RevoPlayer player : known_players) {
            uuids.add(player.getPlayer().getUniqueId());
        }
        configuration.set(KNOWN_PLAYERS, uuids);
    }

    public List<RevoPlayer> getKnown_players() {
        return known_players;
    }
}
