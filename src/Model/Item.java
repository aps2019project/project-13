package Model;

import java.util.ArrayList;

public class Item {
    private String itemId;
    private String itemDescription;
    private int darickCost;

    public Item(String itemId, String itemDescription, int darickCost) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.darickCost = darickCost;
    }

    public static Item findItemInArrayList(String itemId, ArrayList<Item> items) {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null && items.get(i).getItemId().equals(itemId)) {
                    return items.get(i);
                }
            }
        }
        return null;
    }

    public int getDarickCost() {
        return darickCost;
    }

    public void setDarickCost(int darickCost) {
        this.darickCost = darickCost;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}