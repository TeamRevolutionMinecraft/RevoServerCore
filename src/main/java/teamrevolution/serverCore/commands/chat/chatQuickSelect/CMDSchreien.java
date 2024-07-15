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

public class CMDSchreien implements CommandExecutor {
    public CMDSchreien(RevoCore plugin) {
        Objects.requireNonNull(plugin.getCommand("schreien")).setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        SelectChat.selectChat(RevoCore.getInstance().getCharacter(((Player) sender).getUniqueId()).orElseThrow(), ChatChannel.SCHREIEN);


        return true;
    }

}
