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

public class CMDRufen implements CommandExecutor {
    public CMDRufen(RevoCore plugin) {
        Objects.requireNonNull(plugin.getCommand("rufen")).setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        SelectChat.selectChat(RevoCore.getInstance().getCharacter(((Player) sender).getUniqueId()).orElseThrow(), ChatChannel.RUFEN);


        return true;
    }

}
