# Roleplay Core Plugin

https://github.com/deanveloper/SkullCreator

https://github.com/WesJD/AnvilGUI

## Docker
```bash
run-docker.sh -e OPS=<name> [-v $(pwd)/docker-server:/data]
```

## Access character data

Example of accessing the role play name

```java
import org.bukkit.entity.Player;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.character.Character;

class Implementation {
    Player player = Player;  // Player that needs to be accessed
    Character character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
    String rpName = character.getCharacterData().getRoleplayName();
}

```