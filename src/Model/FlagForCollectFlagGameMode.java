package Model;

public class FlagForCollectFlagGameMode extends Item {

    private Cell currentCell;
    private Card owner;

    public FlagForCollectFlagGameMode(Cell currentCell) {
        super("00", "Flag", ItemKind.FLAG, null);
        setCurrentCell(currentCell);
    }


    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }


    public Card getOwner() {
        return owner;
    }

    public void setOwner(Card owner) {
        this.owner = owner;
    }
}
