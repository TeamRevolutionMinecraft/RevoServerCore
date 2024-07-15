package teamrevolution.serverCore.tasks;


import com.nametagedit.plugin.NametagEdit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.character.Character;
import teamrevolution.serverCore.utils.LuckyPermsIntegration;

public class TablistTask {
    private final String rpSuffix = ChatColor.DARK_PURPLE + " [RP]";
    public TablistTask(RevoCore plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::updateTab, 0, 50);
    }
    private void updateTab() {
        var onlinePlayers = RevoCore.getInstance().getOnlinePlayers().values();
        for (Character p : onlinePlayers) {
            Player player = p.getPlayer();
            if(NametagEdit.getApi() != null) {
                NametagEdit.getApi().setPrefix(player, LuckyPermsIntegration.getRankFromPlayer(player).getChatPrefix());
                if (p.isRoleplaying()) {
                    NametagEdit.getApi().setSuffix(player, rpSuffix);
                } else {
                    NametagEdit.getApi().setSuffix(player, "");
                }
            }
        }
    }
}
