package Model;

public class CollectableItem extends Item{

    Cell cell;
    Spell spell;

    public CollectableItem(String itemId, String itemDescription, ItemName itemName) {
        super(itemId, itemDescription, ItemKind.COLLECTIBLE , itemName);
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
}