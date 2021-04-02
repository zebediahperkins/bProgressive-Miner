package scripts.helpers;

import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import scripts.settings.DisplaySetting;

public class InterfacesHelper {
    public static void hideChat() {
        if (!Interfaces.get(162, 58).isHidden())
            Interfaces.get(162, 7).click();
    }

    public static DisplaySetting getLayout() {
        return switch (Interfaces.get(116, 12, 4).getText()) {
            case "Fixed - Classic layout" -> DisplaySetting.FIXED;
            case "Resizable - Classic layout" -> DisplaySetting.RESIZABLE_CLASSIC;
            default -> DisplaySetting.RESIZABLE_MODERN;
        };
    }

    public static void setLayout(DisplaySetting setting) {
        while (getLayout() != setting) {
            if (!GameTab.TABS.OPTIONS.isOpen())
                GameTab.TABS.OPTIONS.open();
            Interfaces.get(116, 71).click();
            Interfaces.get(116, 12, 4).click();
            switch (setting) {
                case FIXED -> Interfaces.get(116, 37, 1).click();
                case RESIZABLE_CLASSIC -> Interfaces.get(116, 37, 2).click();
                case RESIZABLE_MODERN -> Interfaces.get(116, 37, 3).click();
            }
        }
    }
}
