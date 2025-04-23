package teamrevolution.serverCore.listener.gui;

import net.kyori.adventure.text.Component;
import net.wesjd.anvilgui.AnvilGUI;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import teamrevolution.serverCore.character.RevoPlayer;
import teamrevolution.serverCore.gui.charakter.CharGui;
import teamrevolution.serverCore.enums.Job;
import teamrevolution.serverCore.enums.Race;
import teamrevolution.serverCore.RevoCore;

/**
 * Handles the input from the Char gui
 */
public class CharacterGuiListener implements Listener {

    public CharacterGuiListener(RevoCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    // TODO i18n instead of plain text
    @EventHandler
    public void charGuiOnClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        RevoPlayer revoPlayer = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null && clickedItem.hasItemMeta()) {
            PersistentDataContainer container = clickedItem.getItemMeta().getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(RevoCore.getInstance(), "charGuiId");
            if(container.has(key, PersistentDataType.STRING)) {
                String customID = container.get(key, PersistentDataType.STRING);

                event.setCancelled(true);

                // TODO NullPointerException
                // TODO also maybe use enum instead? functional decomposition?
                switch (customID) {
//                    case "delete":
//                        ItemStack deleteCheckItem = new ItemStack(Material.PAPER);
//                        ItemMeta deleteCheckItemMeta = deleteCheckItem.getItemMeta();
//                        deleteCheckItemMeta.displayName(Component.text(" "));
//                        deleteCheckItem.setItemMeta(deleteCheckItemMeta);
//                        new AnvilGUI.Builder()
//                                .onClose(player1 ->  {player1.openInventory(CharGui.charCreateGui("main", player1));})
//                                .onComplete((player1, name) ->  {
//                                    if (name.equalsIgnoreCase(player1.getName())) {
//                                        // TODO use return value?
//                                        CharacterStorage.delete(player1.getUniqueId());
//                                    }
//                                    player.sendMessage(!CharacterStorage.isRegistered(player.getUniqueId()) ? "Dein char wurde gelöscht" : "Da ist etwas schiefgelaufen mit dem Löschen");
//                                    player.closeInventory();
//                                    return AnvilGUI.Response.close();
//                                })
//                                .itemLeft(deleteCheckItem)
//                                .title("Minecraft namen")
//                                .plugin(RevoCore.getInstance())
//                                .open(player);
//                        break;

                    case "save":
                        if (revoPlayer.store()) {
                            player.sendMessage(Component.text("Der char wurde erfolgreich gespeichert. Viel Spaß :)"));
                        } else {
                            player.sendMessage(Component.text("Da ist etwas schiefgegangen probiere es bitte noch einmal"));
                            player.sendMessage(Component.text("Sollte das problem bestehen bleiben kontaktriere bitten ein Teammitglied oder erstelle ein Ticket mit \"/ticket\""));
                        }
                        player.closeInventory();
                        break;

                    case "back":
                        player.openInventory(CharGui.charCreateGui("main", player));
                        break;

                    case "raceSelect":
                        player.openInventory(CharGui.charCreateGui("raceSelect", player));
                        break;

                    case "jobSelect":
                        player.openInventory(CharGui.charCreateGui("jobSelect", player));
                        break;

                    case "nameSelect":
                        ItemStack nameItem = new ItemStack(Material.PAPER);
                        ItemMeta nameItemMeta = nameItem.getItemMeta();
                        nameItemMeta.displayName(Component.text("Wähle deinen Namen"));
                        nameItem.setItemMeta(nameItemMeta);
                        new AnvilGUI.Builder()
                                .onClose(player1 ->  {player1.openInventory(CharGui.charCreateGui("main", player1));})
                                .onComplete((player1, name) ->  {
                                    revoPlayer.getRoleplayData().setRolePlayName(name);
                                    return AnvilGUI.Response.openInventory(CharGui.charCreateGui("main", player1));
                                })
                                .itemLeft(nameItem)
                                .title("Wähle deinen Namen")
                                .plugin(RevoCore.getInstance())
                                .open(player);
                        break;

                    //player Values

                    // race select
                    case "raceSelectMonthos":
                    case "raceSelectAvari":
                    case "raceSelectLumia":
                    case "raceSelectEderki":
                    case "raceSelectTipas":
                    case "raceSelectGamta":
                        var race = Race.valueOf(StringUtils.substringAfter(customID, "raceSelect").toUpperCase());
                        revoPlayer.getRoleplayData().setRace(race);
                        player.openInventory(CharGui.charCreateGui("main", player));
                        break;


                    // job select
                    case "jobSelectWEAPONSMITH":
                    case "jobSelectARMORSMITH":
                    case "jobSelectCOOK":
                    case "jobSelectCARPENTER":
                    case "jobSelectMASON":
                    case "jobSelectALCHEMIST":
                    case "jobSelectSCHOLAR":
                        var job = Job.valueOf(StringUtils.substringAfter(customID, "jobSelect").toUpperCase());
                        revoPlayer.getRoleplayData().setJob(job);
                        player.openInventory(CharGui.charCreateGui("main", player));
                        break;

                }
            }
        }
    }
}
