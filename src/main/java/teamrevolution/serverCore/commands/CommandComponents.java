package teamrevolution.serverCore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class CommandComponents {

    public static final Component COMMAND_NO_PERMISSION = Component
            .translatable("commands.no_permission")
            .color(TextColor.color(NamedTextColor.RED));

}
