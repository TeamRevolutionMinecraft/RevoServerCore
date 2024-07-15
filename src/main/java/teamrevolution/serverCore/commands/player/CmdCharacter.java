package teamrevolution.serverCore.commands.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrevolution.serverCore.character.CharacterStorage;
import teamrevolution.serverCore.commands.MenuCommand;
import teamrevolution.serverCore.commands.CommandComponents;
import teamrevolution.serverCore.gui.charakter.CharGui;
import teamrevolution.serverCore.enums.PlayerEditMode;
import teamrevolution.serverCore.enums.Rank;
import teamrevolution.serverCore.RevoCore;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmdCharacter extends MenuCommand {

    public CmdCharacter(@NotNull RevoCore plugin) {
        super(plugin, "charakter", null);
        // TODO i18n

        this.actions.put("erstellen", ((player, command, label, args) -> {
            if (CharacterStorage.isRegistered(player.getUniqueId())) {
                player.sendMessage(Component.textOfChildren(
                        Component.translatable("command.character.character_already_exists"),
                        Component.translatable("command.character.change_proposal")
                ));
            } else {
                Inventory inventory = CharGui.charCreateGui("main", player);
                player.openInventory(inventory);
            }
        }));
        this.actions.put("ändern", (player, command, label, args) -> {
            if (!player.hasPermission("revo.changeChar")) {
                player.sendMessage(CommandComponents.COMMAND_NO_PERMISSION);
            } else if (!CharacterStorage.isRegistered(player.getUniqueId())) {
                player.sendMessage(Component.textOfChildren(
                        Component.translatable("command.character.no_character_created"),
                        Component.translatable("command.character.create_proposal")
                ));
            } else {
                player.openInventory(CharGui.charCreateGui("main", player));
            }
        });
        this.actions.put("löschen", (player, command, label, args) -> {
            if (!CharacterStorage.isRegistered(player.getUniqueId())) {
                player.sendMessage(Component.translatable("command.character.no_character_created"));
            } else {
                // TODO inconsistent argument language
                if (args.length == 2 && args[0].equalsIgnoreCase("force") && player.hasPermission("revo.Admin")) {
                    var playerToRemove = Bukkit.getPlayerExact(args[1]);
                    if (playerToRemove != null && CharacterStorage.delete(playerToRemove.getUniqueId())) {
                        player.sendMessage(Component.translatable());
                        player.sendMessage(Component.translatable("command.character.delete_successful", Component.text(args[1])));
                    } else {
                        player.sendMessage(Component.translatable("command.character.delete_failed", Component.text(args[1])));
                    }
                } else {
                    player.openInventory(CharGui.charCreateGui("deleteMenu", player));
                }
            }
        });
        this.actions.put("auswählen", (player, command, label, args) -> {
            if (!player.hasPermission("revo.rank.spielleiter")) {
                // TODO Rewrite rank to use adventure api colors
                // player.sendMessage(Component.text("Du kannst diese funktion nur als " + Rank.SPIELLEITER.getChatPrefix() + " ausführen"));
                player.sendMessage(Component.translatable("command.character.function_only_runnable_as", Component.text(Rank.SPIELLEITER.getRankName())));
            } else {
                //TODO open char select menü
            }
        });
        this.actions.put("aussehen", (player, command, label, args) -> {
            RevoCore.getInstance().getCharacter(player.getUniqueId()).ifPresent(revoPlayer -> {
                revoPlayer.setEditMode(PlayerEditMode.LOOK);
                player.sendMessage(Component.translatable("command.character.describe_your_character"));
            });
        });
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 1 && args[0].equalsIgnoreCase("löschen")) {
            if(args.length > 2) {
                return Bukkit.getOnlinePlayers().stream()
                        .map(Player::getName)
                        .filter(name -> name.startsWith(args[2]))
                        .collect(Collectors.toList());
            }
            return Stream.of("force").filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        }
        return super.onTabComplete(sender, command, label, args);
    }
}
