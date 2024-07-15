package teamrevolution.serverCore.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.equipmentMenu.EquipmentMain;

public class CmdEquipmentMenu extends PlayerCommand {

    public CmdEquipmentMenu(@NotNull RevoCore plugin) {
        super(plugin, "equipment", null);
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        EquipmentMain.openGui(player);
    }
}
