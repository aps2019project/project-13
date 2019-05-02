package Model.BuffClasses;

import Model.Account;
import Model.Cell;
import Model.Warrior;

public class FlameBuff extends ABuff {
    int flameDamage;

    public FlameBuff(Account account, int duration , int flameDamage) {
        super(account, duration);
        this.flameDamage = flameDamage;
    }

    private void affectOnCell(Cell cell) {
        if (cell.getCard() instanceof Warrior)
            ((Warrior) cell.getCard()).decreaseHealthPoint(getFlameDamage());
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Cell) {
            affectOnCell((Cell) t);
        }
    }

    public int getFlameDamage() {
        return flameDamage;
    }

    public void setFlameDamage(int flameDamage) {
        this.flameDamage = flameDamage;
    }

}
