package scripts.helpers;

import org.tribot.api.input.Keyboard;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import scripts.wastedbro.api.rsitem_services.GrandExchange;

import java.util.Arrays;

public class InventoryHelper {
    public static void shiftDrop(int amount, String... names) {
        RSItem[] items = Inventory.find(names);
        Keyboard.sendPress((char) 16, 16);
        for (int i = 0; i < amount && i < items.length; ++i) {
            items[i].click();
        }
        Keyboard.sendRelease((char) 16, 16);
    }

    public static void shiftDropContains(String... contains) {
        RSItem[] items = getItemsContaining(contains);
        Keyboard.sendPress((char) 16, 16);
        for (int i = 0; i < items.length; ++i) {
            items[i].click();
        }
        Keyboard.sendRelease((char) 16, 16);
    }

    public static RSItem[] getItemsContaining(String... contains) {
        return Arrays.stream(Inventory.getAll())
                .filter(rsItem -> {
                    for (String contain : contains) {
                        if (rsItem.getDefinition().getName().contains(contain)) {
                            return true;
                        }
                    }
                    return false;
                }).toArray(RSItem[]::new);
    }

    public static int getWaterskinCount() {
        RSItem[] waterskins = getItemsContaining("Waterskin");
        int count = 0;
        for (RSItem waterskin : waterskins) {
            count += Integer.parseInt(
                    String.valueOf(
                            waterskin.getDefinition().getName().toCharArray()[10]
                    )
            );
        }
        return count;
    }

    public static int getInventoryValue() {
        int price = 0;
        for (RSItem item : Inventory.getAll()) {
            price += GrandExchange.getPrice(item.getDefinition().getID());
        }
        return price;
    }
}
