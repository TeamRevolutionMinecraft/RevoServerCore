package teamrevolution.serverCore.listener.chat;


import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import teamrevolution.serverCore.character.RevoPlayer;
import teamrevolution.serverCore.chat.MessageBuilder;
import teamrevolution.serverCore.chat.RevoChat;
import teamrevolution.serverCore.enums.ChatChannel;
import teamrevolution.serverCore.RevoCore;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class DiscordListener {
    private int port;
    private RevoCore plugin;
    private boolean isActive;
    private String url;

    private static DiscordListener discordListener;

    public DiscordListener(RevoCore plugin, String url, int port) {
        this.plugin = plugin;
        this.url = url;
        this.port = port;
        this.isActive = true;

        discordListener = this;
    }

    public void listenToDiscordTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (isActive()) {
                HttpResponse<String> response = ApiCall();
                if (response != null) {
                    sendMinecraftMessage(convertBodyToMap(response.body()));
                }
            }
        }, 0, 20);
    }

    public HttpResponse<String> ApiCall() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + url + ":" + port + "/fromDC"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return response;
    }


    public void sendMinecraftMessage(HashMap<String, String> msg) {
        Bukkit.getOnlinePlayers().forEach(player -> {

            if (RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow().getPreferences().getSubscribesChannels().contains(ChatChannel.DISCORD)) {
                player.sendMessage(MessageBuilder.getMessageFromDiscord(msg));
            }
        });
    }
    public void sendMinecraftMessage(String msgSender, String msg) {
        Bukkit.getOnlinePlayers().forEach(player -> {

            if (RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow().getPreferences().getSubscribesChannels().contains(ChatChannel.DISCORD)) {
                player.sendMessage(MessageBuilder.getMessageFromDiscord(msgSender, msg));
            }
        });
    }

    public void sendDiscordMessage(RevoPlayer sender, String content) {
        JSONObject data = new JSONObject();
        JSONObject body = new JSONObject();

        body.put("key", "MASTERKEY");
        body.put("sender", "12");
        body.put("target", "1");


        data.put("channel", RevoChat.getChatConfig().get("discordRoom"));
        data.put("content", content);
        data.put("color", 0xff5500);
        data.put("author", sender.getPlayer().getName());
        data.put("author_picture", "https://minotar.net/avatar/" + sender.getPlayer().getUniqueId() + "/250");

        body.put("data", data);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + url + ":" + port + "/toDiscord"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toJSONString()))
                .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ignored) {

        }

    }



    public void setActive(boolean value) {
        this.isActive = value;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void toggleActive() {
        this.isActive = !this.isActive;
    }


    public static HashMap<String, String> convertBodyToMap(String body) {
        HashMap<String, String> map = new HashMap<>();
        body = body.replace("{", "").replace("}", "").replace("\"", "");
        for (String st : body.split(",")) {

            String s[] = st.split(":");
            map.put(s[0].trim(), s[1].trim());
        }
        return map;
    }


    public static DiscordListener getDiscordListener() {
        return discordListener;
    }
}
