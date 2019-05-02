package Model.BuffClasses;

import Model.Account;
import Model.Battle;

public class ManaBuff extends ABuff {

    private int manaAmountIncrease;

    public ManaBuff(int manaAmountIncrease, Account account, int duration) {
        super(account, duration);
        this.manaAmountIncrease = manaAmountIncrease;
    }
    public void affectOnBattle(Battle battle)
    {
        battle.increaseMana(getAccount(), getManaAmountIncrease());
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Battle) {
            affectOnBattle((Battle) t);
        }

    }


    @Override
    public <T> void update(T t) {

    }

    public int getManaAmountIncrease() {
        return manaAmountIncrease;
    }
}
