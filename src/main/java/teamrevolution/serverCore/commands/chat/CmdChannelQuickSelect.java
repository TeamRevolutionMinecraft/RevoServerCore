package teamrevolution.serverCore.commands.chat;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.chat.SelectChat;
import teamrevolution.serverCore.commands.PlayerCommand;
import teamrevolution.serverCore.enums.ChatChannel;

public class CmdChannelQuickSelect extends PlayerCommand {

    private final ChatChannel chatChannel;

    public CmdChannelQuickSelect(@NotNull RevoCore plugin, @NotNull String commandName, @NotNull ChatChannel chatChannel) {
        super(plugin, commandName, null);
        this.chatChannel = chatChannel;
    }

    @Override
    public void onPlayerCommand(@NotNull Player player, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        var character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
        SelectChat.selectChat(character, chatChannel);
    }
}
