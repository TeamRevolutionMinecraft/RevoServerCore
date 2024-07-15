package teamrevolution.serverCore.chat;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import teamrevolution.serverCore.character.Character;
import teamrevolution.serverCore.enums.ChatChannel;
import teamrevolution.serverCore.enums.Rank;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

import java.util.HashMap;

public class MessageBuilder {

    private static final Component space = Component.space();
    private static final String chatSupportPrefix = ChatChannel.SUPPORT.getPrefix();
    private static final String globalChatPrefix = ChatChannel.OOC.getPrefix();
    private static final ChatColor emoteColor = ChatColor.YELLOW;

    //TODO Refactor this shit

    /**
     * Creates a formatted message for the Chat
     * It contains the rang, the name and the message
     * @param player sending player
     * @param originalMsg to send message
     * @return the formatted message
     */
    public static Component getFormattedMessage(Character player, Component originalMsg) {
        String name = ChatColor.GRAY + player.getPlayer().getName();
        Rank rank = LuckyPermsIntegration.getRankFromPlayer(player.getPlayer());
        String msg = msgToString(originalMsg);


        if (msgToString(originalMsg).startsWith("~")) {
            return getEmoteChatMessage(player, msgToString(originalMsg));
        }


        if (player.isRoleplaying()) {
            name = player.getCharacterData().getRoleplayName();
            if (player.getCurrentChannel() == ChatChannel.SCHREIEN) {
                msg = msg.toUpperCase();
            }
        }

        return Component.text(player.getCurrentChannel().getPrefix())
                .append(space)
                .append(Component.text(rank.getChatPrefix()))
                .append(Component.text(name).hoverEvent(HoverEvent.showText(Component.text(player.getPlayer().getName()))))
                .append(Component.text(": "))
                .append(Component.text(msg));


    }

    /**
     * Used to Build a support Message
     * @param player sending Player
     * @param originalMsg chat message
     * @param teleport boolean if player tp to player command is embedded
     * @return supportmessage
     */
    public static Component getSupportMessage(Player player, Component originalMsg, boolean teleport) {
        Component name = Component.text(player.getName());

        String msg = msgToString(originalMsg);

        if (msg.startsWith("!!")) {
            msg = msg.replaceFirst("!!", "");
        }

        if (teleport) {
            name = Component.text(player.getName())
                    .hoverEvent(HoverEvent.showText(Component.text("Teleport to player")))
                    .clickEvent(ClickEvent.runCommand("/tp " + player.getName()));
        }
        return Component.text(chatSupportPrefix)
                .append(space)
                .append(name)
                .append(Component.text(": "))
                .append(Component.text(msg));
    }

    public static Component getGlobalMessage(Player player, Component originalMsg) {

        String msg = msgToString(originalMsg);
        if (msg.startsWith("!")) {
            msg = msg.replaceFirst("!", "");
        }

        return Component.text(globalChatPrefix)
                .append(space)
                .append(Component.text(LuckyPermsIntegration.getRankFromPlayer(player).getChatPrefix()))
                .append(Component.text(player.getName()))
                .append(Component.text(": "))
                .append(Component.text(msg));
    }

    public static Component getMessageFromDiscord(HashMap<String, String> msgRaw) {
        return Component.text(ChatChannel.DISCORD.getPrefix())
                .append(space)
                .append(Component.text(msgRaw.get("sender")))
                .append(Component.text(": "))
                .append(Component.text(msgRaw.get("msg")));

    }

    public static Component getMessageFromDiscord(String msgSender, String msg) {
        return Component.text(ChatChannel.DISCORD.getPrefix())
                .append(space)
                .append(Component.text(msgSender))
                .append(Component.text(": "))
                .append(Component.text(msg));

    }

    public static String discordMessageBuilder(String sender, String content) {
        return sender + " -> " + content;
    }


    /**
     * Converts Text Component into String
     * @param msg Message as Component
     * @return Message as String
     */
    private static String msgToString(Component msg) {
        return PlainTextComponentSerializer.plainText().serialize(msg);
    }

    private static Component getEmoteChatMessage(Character player, String msg) {
        msg = msg.replaceFirst("~", "");

        String name = player.isRoleplaying() ? player.getCharacterData().getRoleplayName() : player.getPlayer().getName();

        return Component.text(ChatColor.GOLD + name).hoverEvent(HoverEvent.showText(Component.text(player.getPlayer().getName())))
                .append(space)
                .append(Component.text(emoteColor + msg));
    }
}
