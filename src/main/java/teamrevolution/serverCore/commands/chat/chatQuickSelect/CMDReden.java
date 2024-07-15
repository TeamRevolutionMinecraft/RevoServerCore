package teamrevolution.serverCore.commands.chat.chatQuickSelect;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.chat.SelectChat;
import teamrevolution.serverCore.enums.ChatChannel;


import java.util.Objects;

public class CMDReden implements CommandExecutor {
    public CMDReden(RevoCore plugin) {
        Objects.requireNonNull(plugin.getCommand("reden")).setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        SelectChat.selectChat(RevoCore.getInstance().getCharacter(((Player) sender).getUniqueId()).orElseThrow(), ChatChannel.REDEN);


        return true;
    }

}
