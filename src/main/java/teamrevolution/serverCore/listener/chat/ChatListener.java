package teamrevolution.serverCore.listener.chat;


import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import teamrevolution.serverCore.chat.MessageBuilder;
import teamrevolution.serverCore.enums.ChatChannel;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.character.Character;

import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {

    public ChatListener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public static void onMessage(AsyncChatEvent event) {
        var sendingPlayer = event.getPlayer();
        logMessage(sendingPlayer, event.originalMessage());

        Character character = RevoCore.getInstance().getRevoPlayer(sendingPlayer.getUniqueId());
        event.setCancelled(true);
        if (character.isEditMode() != null) {
            switch (character.isEditMode()) {
                case LOOK -> {
                    List<String> text = new ArrayList<>();
                    for (String s : PlainTextComponentSerializer.plainText().serialize(event.originalMessage()).split("\\|")) {
                        text.add(s.trim());
                    }
                    character.getCharacterData().setLookDescription(text);
                }
            }
            character.setEditMode(null);
            return;
        }

        if (character.getCurrentChannel() == ChatChannel.DISCORD) {
            DiscordListener.getDiscordListener().sendDiscordMessage(character, PlainTextComponentSerializer.plainText().serialize(event.originalMessage()));
            DiscordListener.getDiscordListener().sendMinecraftMessage(character.getPlayer().getName(), PlainTextComponentSerializer.plainText().serialize(event.originalMessage()));
            return;
        }



        ChatChannel chat = selectQuickChat(event.message());

        if (chat == ChatChannel.SUPPORT || character.getCurrentChannel() == ChatChannel.SUPPORT) {

            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.hasPermission("revo.rank.team") || player.hasPermission("revo.rank.spielleiter") || player == sendingPlayer ) {
                    player.sendMessage(MessageBuilder.getSupportMessage(sendingPlayer, event.originalMessage(), !(player == sendingPlayer)));
                }
            });
            return;
        }

        Location sendingPlayerLocation = sendingPlayer.getLocation();

        int range = (chat == ChatChannel.OOC) ? -1 : character.getCurrentChannel().getRange();

        Bukkit.getOnlinePlayers().forEach(receivingPlayer -> {
            if (RevoCore.getInstance().getRevoPlayer(receivingPlayer.getUniqueId()).getActiveChannels().contains(character.getCurrentChannel())) {
                Location recievingPlayerLocation = receivingPlayer.getLocation();
                if (range == -1) {
                    receivingPlayer.sendMessage(MessageBuilder.getGlobalMessage(sendingPlayer, event.originalMessage()));
                } else if (sendingPlayerLocation.distance(recievingPlayerLocation) <= range) {
                    receivingPlayer.sendMessage(MessageBuilder.getFormattedMessage(character, event.originalMessage()));
                }
            }
        });

    }
    /**
     * Logs the Message in the ServerConsole
     * @param player sending player
     * @param message textcomonent of the message
     */
    private static void logMessage(Player player, Component message) {
        String msg = player.getName() + " : " + PlainTextComponentSerializer.plainText().serialize(message);

        Bukkit.getServer().getLogger().info(msg);
    }

    /**
     * selects a quickchat level
     * 0 => No special quickchat
     * 1 => Globalchat
     * 2 => Supportchat
     * @param message
     * @return chat
     */
    private static ChatChannel selectQuickChat(Component message) {
        String msg = PlainTextComponentSerializer.plainText().serialize(message);

        if (msg.startsWith("!!")) return ChatChannel.SUPPORT;
        if (msg.startsWith("!")) return ChatChannel.OOC;

        return null;
    }
}
