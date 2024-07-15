package teamrevolution.serverCore.character;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import teamrevolution.serverCore.enums.*;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.gui.GuiUtils;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Character {

    private final Player player;
    private final Rank rank;
    private final CharacterData characterData;
    private final List<ChatChannel> activeChannels;
    private ChatChannel currentChannel;
    private PlayerEditMode editMode;

    @Deprecated
    private boolean isRoleplaying;
    private boolean isSupporting;


    public Character(Player player) {
        this.player = player;
        this.rank = LuckyPermsIntegration.getRankFromPlayer(player);
        if (CharacterStorage.isRegistered(player.getUniqueId())) {
            this.characterData = CharacterStorage.load(player.getUniqueId());
        } else {
            this.characterData = new CharacterData();
        }
        this.currentChannel = ChatChannel.OOC;
        this.editMode = null;
        this.isSupporting = false;

        this.activeChannels = new ArrayList<>();
        this.activeChannels.addAll(Arrays.asList(ChatChannel.values()));

        RevoCore.getInstance().getLogger().info(player.getName() + " has been registered!");

    }

    public Inventory getBadge() {
        // TODO i18n
        var badge = GuiUtils.createChestGui(1, "util", "Ausweis von " + player.getName(), Material.WHITE_STAINED_GLASS_PANE, false);

        var statText = new ArrayList<Component>();
        statText.add(Component.text(ChatColor.GRAY + characterData.getRoleplayName()));
        statText.add(Component.text(ChatColor.GRAY + characterData.getRace().toString()));

        List<Component> lookText = characterData.getLookDescription()
                .stream().map(s -> Component.text(ChatColor.GRAY + s)).collect(Collectors.toList());

        // TODO Events müssen abgebrochen werden

        badge.setItem(2, GuiUtils.createItemWithLore("util",  ChatColor.AQUA + "Name/Rasse", Material.BOOK, "util", true, statText));
        badge.setItem(6, GuiUtils.createPlayerHeadWithLore("util", ChatColor.AQUA + "Aussehen", getPlayer(), "util", true, lookText));

        return badge;
    }

    public Player getPlayer() {
        return player;
    }

    public CharacterData getCharacterData() {
        return characterData;
    }

    public boolean isRoleplaying() {
        return isRoleplaying;
    }

    public void setRoleplaying(boolean roleplaying) {
        this.isRoleplaying = roleplaying;
    }

    public void toggleRoleplaying() {
        this.isRoleplaying = !this.isRoleplaying;
    }

    public void activateChat(ChatChannel chat) {
        activeChannels.add(chat);
    }

    public void deactivateChat(ChatChannel chat) {
        activeChannels.remove(chat);
    }

    public ChatChannel getCurrentChannel() {
        return currentChannel;
    }

    public List<ChatChannel> getActiveChannels() {
        return activeChannels;
    }

    public void setCurrentChannel(ChatChannel currentChannel) {
        this.currentChannel = currentChannel;
    }
    public PlayerEditMode isEditMode() {
        return editMode;
    }
    public void setEditMode(PlayerEditMode editMode) {
        this.editMode = editMode;
    }

    public boolean isCurrentlySupporting() {
        return isSupporting;
    }

    public void setCurrentlySupporting(boolean newValue) {
        this.isSupporting = newValue;
    }
    @Override
    public String toString() {
        return "RevoPlayer{" +
                "player=" + player +
                ", rank=" + rank +
                ", rolePlayName='" + characterData.getRoleplayName() + '\'' +
                ", race=" + characterData.getRace() +
                ", job=" + characterData.getJob() +
                ", currentChat=" + currentChannel +
                ", editMode=" + editMode +
                ", activeChats=" + activeChannels +
                ", look=" + characterData.getLookDescription() +
                ", rp=" + isRoleplaying +
                '}';
    }
}
