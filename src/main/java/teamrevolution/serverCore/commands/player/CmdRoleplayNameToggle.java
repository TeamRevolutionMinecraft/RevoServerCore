package teamrevolution.serverCore.commands.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;

public class CmdRoleplayNameToggle extends PlayerCommand {
    public CmdRoleplayNameToggle(@NotNull RevoCore plugin) {
        super(plugin, "roleplay", null);
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        RevoCore.getInstance().getCharacter(player.getUniqueId()).ifPresent(character -> {
            var hoverEvent = HoverEvent.showText(Component.text("Klicke hier für mehr Infos über den RP Modus"));
            var clickEvent = ClickEvent.openUrl("https://www.google.de/");

            character.toggleRoleplaying();

            var message = Component.translatable(character.isRoleplaying() ? "command.roleplay.mode_enabled" : "command.roleplay.mode_disabled")
                            .color(TextColor.color(NamedTextColor.GRAY)).hoverEvent(hoverEvent).clickEvent(clickEvent);

            player.sendMessage(message);
        });
    }
}
