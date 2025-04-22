package teamrevolution.serverCore.character;

import teamrevolution.serverCore.enums.ChatChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat {
    private Object editMode;
    private ChatChannel currentChannel;
    private boolean isSupporting;
    private final List<ChatChannel> activeChannels;
    private ChatChannel currentChannel;


    public Chat() {
        this.currentChannel = ChatChannel.OOC;
        this.isSupporting = false;

        this.activeChannels = new ArrayList<>();
        this.activeChannels.addAll(Arrays.asList(ChatChannel.values()));

    }
}
