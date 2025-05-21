package teamrevolution.serverCore.exceptions;

import java.util.UUID;

public class PlayerCannotBeLoaded extends RuntimeException {
    public PlayerCannotBeLoaded(UUID uuid) {
        super("Player with uuid" + uuid + "could not be loaded");
    }
}
