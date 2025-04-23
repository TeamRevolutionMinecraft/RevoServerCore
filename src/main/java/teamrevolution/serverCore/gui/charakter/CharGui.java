package teamrevolution.serverCore.gui.charakter;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import teamrevolution.serverCore.RevoCore;
import teamrevolution.serverCore.character.RevoPlayer;
import teamrevolution.serverCore.gui.GuiUtils;


public class CharGui {
    private static final String keyValue = "charGuiId";
    public static Inventory charCreateGui(String typeOfIventory, Player player) {
        RevoPlayer character = RevoCore.getInstance().getCharacter(player.getUniqueId()).orElseThrow();
        String nameOfInventory = "Etwas ist falsch gelaufen";
        int height = -1;
        Inventory gui;


        switch (typeOfIventory) {
            case "main":
                nameOfInventory = "§l§6Charakter Hauptmenü";
                height = 3;
                break;

            case "raceSelect":
                nameOfInventory = "§l§6Rassen Auswahl";
                height = 5;
                break;

            case "jobSelect":
                nameOfInventory = "§l§6Berufs Auswahl";
                height = 5;
                break;

            case "deleteMenu":
                nameOfInventory = "§l§4Achtung Gefahr";
                height = 1;
                break;


        }


        gui = GuiUtils.createChestGui(height, keyValue, nameOfInventory, Material.WHITE_STAINED_GLASS_PANE, false);
        Material infoItem = Material.BLUE_STAINED_GLASS_PANE;


        if (!(typeOfIventory.equals("deleteMenu") || typeOfIventory.equals("main"))) {
            gui.setItem(height * 9 - 1, GuiUtils.createItemWithPlayerInfo(keyValue, "Zurueck", Material.RED_STAINED_GLASS_PANE, "back", false, character));
        }
        switch (typeOfIventory) {
            case "main":
                gui.setItem(height * 9 - 1, GuiUtils.createItemWithPlayerInfo(keyValue, "Speichern", Material.GREEN_STAINED_GLASS_PANE, "save", false, character));
                gui.setItem(10, GuiUtils.createItemWithPlayerInfo(keyValue,"Rassenauswahl", "93ffeb6b987d57046002e70042c7451ab97790110fe59772f1fe929612a39b53", "raceSelect", character)); //Elfen Kopf //rasse
                gui.setItem(16, GuiUtils.createItemWithPlayerInfo(keyValue,"Berufsauswahl", "69a600ab0a83097065b95ae284f8059961774609adb3dbd3a4ca269d44409551", "jobSelect", character));
                gui.setItem(13, GuiUtils.createItemWithPlayerInfo(keyValue,"Namensauswahl", Material.MAP, "nameSelect", true, character));
                break;

            case "raceSelect":
                gui.setItem(2, GuiUtils.createItem(keyValue,"Monthos", "7c8de6232dbd3cd0212d9f3042c3de0738e487c7a62de6f9894a39a72af84e3d", "raceSelectMonthos")); //Zwerg Kopf
                gui.setItem(2 + 9, GuiUtils.createInfoItem(keyValue,"Info über Monthos", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(4, GuiUtils.createItem(keyValue,"Avari", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "raceSelectAvari")); //Elfen Kopf
                gui.setItem(4 + 9, GuiUtils.createInfoItem(keyValue,"Info über Avari", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(6, GuiUtils.createItem(keyValue,"Lumia", "863ab911a7f5af6c65d1cd9600b2b1d26b2e3d5f327e24d1b74d2e96dff", "raceSelectLumia")); //Avatar kopf
                gui.setItem(6 + 9, GuiUtils.createInfoItem(keyValue,"Info über Lumia", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(28, GuiUtils.createItem(keyValue,"Ederki", "9da2813d4f5acd69ed590c1d8a442b595519dcbc0e17198adfaaaaf15f89b0", "raceSelectEderki")); //Echsen kopf
                gui.setItem(28 + 9, GuiUtils.createInfoItem(keyValue,"Info über Ederki", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(31, GuiUtils.createItem(keyValue,"Tipas", "13a5664a92b6f1dad478634fd2a34263720db87bb7d7f80919064db259d7d", "raceSelectTipas")); //Bilbo kopf
                gui.setItem(31 + 9, GuiUtils.createInfoItem(keyValue,"Info über Tipas", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(34, GuiUtils.createItem(keyValue,"Gamta", "13a5664a92b6f1dad478634fd2a34263720db87bb7d7f80919064db259d7d", "raceSelectGamta")); //Bilbo kopf
                gui.setItem(34 + 9, GuiUtils.createInfoItem(keyValue,"Info über Gamtas", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));


                break;

            case "jobSelect":
                gui.setItem(1, GuiUtils.createItemWithPlayerInfo(keyValue, "Waffenschmied", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectWEAPONSMITH", character)); //Elfen Kopf
                gui.setItem(1 + 9, GuiUtils.createInfoItem(keyValue,"Info über Waffenschmied", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(4, GuiUtils.createItemWithPlayerInfo(keyValue, "Rüstungsschmied", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectARMORSMITH", character)); //Elfen Kopf
                gui.setItem(4 + 9, GuiUtils.createInfoItem(keyValue,"Info über Rüstungsschmied", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(7, GuiUtils.createItemWithPlayerInfo(keyValue, "Koch", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectCOOK", character)); //Elfen Kopf
                gui.setItem(7 + 9, GuiUtils.createInfoItem(keyValue,"Info über Koch", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(28, GuiUtils.createItemWithPlayerInfo(keyValue, "Tischler", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectCARPENTER", character)); //Elfen Kopf
                gui.setItem(28 + 9, GuiUtils.createInfoItem(keyValue,"Info über Tischler", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(30, GuiUtils.createItemWithPlayerInfo(keyValue, "Steinmetz", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectMASON", character)); //Elfen Kopf
                gui.setItem(30 + 9, GuiUtils.createInfoItem(keyValue,"Info über Steinmetz", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(32, GuiUtils.createItemWithPlayerInfo(keyValue, "Gelehrter", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectSCHOLAR", character)); //Elfen Kopf
                gui.setItem(32 + 9, GuiUtils.createInfoItem(keyValue,"Info über Gelehrter", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));

                gui.setItem(34, GuiUtils.createItemWithPlayerInfo(keyValue, "Alchemist", "f6626b2b295902b5145bd8dbc3afe67459e4c32d3529c0c02c5f335033e26072", "jobSelectALCHEMIST", character)); //Elfen Kopf
                gui.setItem(34 + 9, GuiUtils.createInfoItem(keyValue,"Info über Alchemist", "https://docs.google.com/document/d/1RKYZZlwM024xYcxSE-NPyvKrrPj22Xwsv9IhLpyE8Xk/edit", infoItem));


                break;

            case "deleteMenu":
                gui.setItem(4, GuiUtils.createItemWithPlayerInfo(keyValue, "Bestätige das löschen", Material.MAP, "delete", false, character));
                break;
        }



        return gui;
    }

}
