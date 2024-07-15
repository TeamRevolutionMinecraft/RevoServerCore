package teamrevolution.serverCore.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;
import teamrevolution.serverCore.enums.Rank;
import teamrevolution.serverCore.enums.RevoItems;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CmdCustomItem extends PlayerCommand {
    private final List<String> items;
    public CmdCustomItem(@NotNull RevoCore plugin) {
        super(plugin, "customitem", null);
        this.items = Arrays.stream(RevoItems.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // TODO Incorporate LuckyPermsIntegration into permissions?
        if (!(player.isOp() && LuckyPermsIntegration.getRankFromPlayer(player).ordinal() <= Rank.SPIELLEITER.ordinal())) {
            return;
        }

        if (items.contains(args[0].toUpperCase())) {
            player.getInventory().addItem(RevoItems.valueOf(args[0].toUpperCase()).getItemStack());
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return items;
        }
        return super.onTabComplete(sender, command, label, args);
    }
}
