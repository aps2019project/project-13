package Model;

import java.util.ArrayList;

public class UsableItem extends Item {
    int DarickCost;
    Spell spell;
    public UsableItem(String itemId, String itemDescription , int darickCost) {
        super(itemId, itemDescription, ItemKind.USABLE );
        this.DarickCost=darickCost;
    }

    public int getDarickCost() {
        return DarickCost;
    }
    public static UsableItem findUsableItemInArrayList(String itemId, ArrayList<UsableItem> items) {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null && items.get(i).getItemId().equals(itemId)) {
                    return items.get(i);
                }
            }
        }
        return null;
    }
}
