package Model;

import java.util.ArrayList;

public class UsableItem extends Item {
    private int DarickCost;
    private Spell spell;
    public UsableItem(String itemId, String itemDescription ,String itemName, int darickCost , Spell spell) {
        super(itemId, itemDescription, ItemKind.USABLE, itemName );
        this.DarickCost=darickCost;
        this.spell = spell;
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
    @Override
    public String toString() {
        return "Name:"+ this.getItemName() + "- Desc: " + this.getItemDescription();
    }

    public Spell getSpell() {
        return spell;
    }
}
