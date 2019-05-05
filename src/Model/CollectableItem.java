package Model;

import Model.BuffClasses.ABuff;
import com.rits.cloning.Cloner;

import java.util.ArrayList;

public class CollectableItem extends Item {

    private Cell cell;
    private Spell spell;
    private ArrayList<ABuff> aBuffs = new ArrayList<>();

    public CollectableItem(String itemId, String itemDescription, String itemName, ArrayList<ABuff> aBuffs) {
        super(itemId, itemDescription, ItemKind.COLLECTIBLE, itemName);
        this.aBuffs = aBuffs;
    }
    public static CollectableItem deepClone(CollectableItem collectableItem)
    {
        if (collectableItem!=null) {
            Cloner cloner = new Cloner();
            cloner.dontClone(Account.class);
            CollectableItem collectableItemClone = cloner.deepClone(collectableItem);
            //TODO ITEM ID
        }
            return collectableItem;
    }

    public ArrayList<ABuff> getaBuffs() {
        return aBuffs;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public String toString() {
        return "Name:" + this.getItemName() + "- Desc: " + this.getItemDescription();
    }
}
