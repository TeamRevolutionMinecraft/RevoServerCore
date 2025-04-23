package teamrevolution.serverCore.chat;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import teamrevolution.serverCore.character.RevoPlayer;
import teamrevolution.serverCore.enums.ChatChannel;

public class SelectChat {
    public static void selectChat(RevoPlayer player, ChatChannel channel) {

        player.setCurrentChannel(channel);
        player.getPlayer().sendMessage(Component.text(ChatColor.GRAY + "Du bist jetzt im channel: " + channel));
    }
}
