package teamrevolution.serverCore.commands.chat;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrevolution.serverCore.character.RevoPlayer;
import teamrevolution.serverCore.chat.SelectChat;
import teamrevolution.serverCore.commands.MenuCommand;
import teamrevolution.serverCore.enums.ChatChannel;
import teamrevolution.serverCore.RevoCore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CmdSelectChannel extends MenuCommand {

    public CmdSelectChannel(RevoCore plugin) {
        super(plugin, "channel", null);
        this.actions.put("join", (player, command, label, args) -> {
            var character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
            character.getPreferences().subScripeChannel(getChatFromArg(args[0]));
            player.sendMessage(Component.translatable("command.channel.joined_channel", Component.text(args[0].toLowerCase())).color(NamedTextColor.DARK_GRAY));
            player.sendMessage(Component.translatable("command.channel.subscribed").color(NamedTextColor.DARK_GRAY));
        });
        this.actions.put("leave", (player, command, label, args) -> {
            var character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
            if (getChatFromArg(args[0]) == character.getCurrentChannel()) {
                player.sendMessage(Component.translatable("command.channel.cant_leave_current_channel").color(NamedTextColor.RED));
            }
            character.getPreferences().unSubScribeChannel(getChatFromArg(args[1]));
            player.sendMessage(Component.translatable("command.channel.left_channel", Component.text(args[0].toLowerCase())).color(NamedTextColor.DARK_GRAY));
            player.sendMessage(Component.translatable("command.channel.unsubscribed").color(NamedTextColor.DARK_GRAY));
        });
        this.actions.put("select", (player, command, label, args) -> {
            var character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
            SelectChat.selectChat(character, getChatFromArg(args[0]));

            character.getPreferences().subScripeChannel(getChatFromArg(args[0]));
            player.sendMessage(Component.translatable("command.channel.joined_channel", Component.text(args[0].toLowerCase())).color(NamedTextColor.DARK_GRAY));
        });
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        AtomicReference<List<String>> complete = new AtomicReference<>(super.onTabComplete(sender, command, label, args));
        RevoCore.getInstance().getCharacter(((Player) sender).getUniqueId()).ifPresent(character -> {
            switch (args[0].toLowerCase()) {
                case "join" -> complete.set(getInactiveChannelsFromPlayer(character));
                case "leave", "select" -> complete.set(getActiveChannelsFromPlayer(character));
            }
        });
        return complete.get();
    }

    private List<String> getInactiveChannelsFromPlayer(RevoPlayer player) {
        List<String> channels = new ArrayList<>();

        for (ChatChannel chat : ChatChannel.values()) {
            if (!player.getPreferences().getSubscribesChannels().contains(chat)) {
                channels.add(chat.toString().toLowerCase());
            }
        }
        return channels;
    }
    private List<String> getActiveChannelsFromPlayer(RevoPlayer player) {
        List<String> channels = new ArrayList<>();

        for (ChatChannel chat : player.getPreferences().getSubscribesChannels()) {
            channels.add(chat.toString().toLowerCase());
        }
        return channels;
    }

    private ChatChannel getChatFromArg(String arg) {
        return switch (arg.toUpperCase()) {
            case "FLÜSTERN" -> ChatChannel.FLUESTERN;
            case "GLOBAL" -> ChatChannel.OOC;
            default -> ChatChannel.valueOf(arg.toUpperCase());
        };
    }
}
