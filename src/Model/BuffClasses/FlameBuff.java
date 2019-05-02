package Model.BuffClasses;

import Model.Account;
import Model.Cell;
import Model.Warrior;

public class FlameBuff extends ABuff {


    public FlameBuff(Account account, int duration) {
        super(account, duration);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Cell) {
            Cell cell = (Cell) t;
            if (cell.getCard() instanceof Warrior)
                ((Warrior) cell.getCard()).decreaseHealthPoint(1);
        }
    }

    @Override
    public <T> void update(T t) {

    }
}
