import java.util.ArrayList;

public class Map {
    private ArrayList<Cell> cells = new ArrayList<>();
    private Battle battle;

    Map(Battle battle) {
        this.battle = battle;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public int getDistanceOfTwoCell(Cell firstCell, Cell secondCell) {
        return Math.abs(firstCell.getColumn() - secondCell.getColumn()) + Math.abs(firstCell.getRow() - secondCell.getRow());
    }

    public Item getItem(Cell cell) {
        return cell.getCard().getItem();
    }

    public void moveCard(Card card, Cell sourceCell, Cell destinationCell) {
        sourceCell.setCard(null);
        destinationCell.setCard(card);
    }

    public void insertCard(Card card, Cell cell) {
        if (cell.getCard() == null)
            return;
        cell.setCard(card);
    }

    public Battle getBattle() {
        return battle;
    }
}
