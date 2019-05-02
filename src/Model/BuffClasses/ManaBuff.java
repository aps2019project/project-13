package Model.BuffClasses;

import Model.Account;
import Model.Battle;

public class ManaBuff extends ABuff {

    private int manaAmountIncrease;

    public ManaBuff(int manaAmountIncrease, Account account, int duration) {
        super(account, duration);
        this.manaAmountIncrease = manaAmountIncrease;
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Battle) {
            Battle battle = (Battle) t;
            battle.increaseMana(account, manaAmountIncrease);
        }

    }

    @Override
    public <T> void update(T t) {

    }

    public int getManaAmountIncrease() {
        return manaAmountIncrease;
    }
}
