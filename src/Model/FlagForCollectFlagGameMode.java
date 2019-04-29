package Model;

public class FlagForCollectFlagGameMode extends Item {

    private Cell currentCell;

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
}
