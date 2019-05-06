package Model;
import com.rits.cloning.Cloner;

import java.util.ArrayList;

public class Item {
    private String itemId;
    private String itemDescription;
    private String itemName;
    private Account account;
    private ItemKind itemKind;
    private static int counter = 1;
    //   private int darickCost;

    public Item(String itemId, String itemDescription, ItemKind itemKind, String itemName) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.itemName = itemName;
        this.itemKind = itemKind;
//        this.darickCost = darickCost;
    }

    public static String makeNewID(String accountName, String itemName) {
        return accountName + "_" + itemName + "_" + (counter++);
    }

    public static Item deepClone(Item item) {
        try {
            if (item != null) {
                if (item instanceof CollectableItem) {
                    Item temp = CollectableItem.deepCloneCollect((CollectableItem) item);
                    temp.setItemId(makeNewID(Account.getLoginedAccount().getUsername(), item.getItemName()));
                    return temp;
                } else if (item instanceof UsableItem) {
                    Item temp = UsableItem.deepCloneUse((UsableItem) item);
                    temp.setItemId(makeNewID(Account.getLoginedAccount().getUsername(), item.getItemName()));
                    return temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public String getItemName() {
        return itemName;
    }

    public ItemKind getItemKind() {
        return itemKind;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
