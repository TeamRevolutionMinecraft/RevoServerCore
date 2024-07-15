package teamrevolution.serverCore.commands.chat;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.commands.PlayerCommand;
import teamrevolution.serverCore.listener.chat.DiscordListener;

import java.util.List;

public class CmdDiscordConnection extends PlayerCommand {

    private final DiscordListener discordListener;

    public CmdDiscordConnection(RevoCore plugin, DiscordListener listener) {
        super(plugin, "discord", List.of("revo.rank.team"));
        this.discordListener = listener;
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        discordListener.toggleActive();
    }
}
