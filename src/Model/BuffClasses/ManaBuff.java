package Model.BuffClasses;

import Model.Account;
import Model.Battle;
import Model.Warrior;

public class ManaBuff extends ABuff implements Cloneable {

    private int manaAmountIncrease;

    public ManaBuff(int manaAmountIncrease, Account account, int duration, boolean isDispellable) {
        super(account, duration, PositiveNegative.POSITIVE, isDispellable);
        this.manaAmountIncrease = manaAmountIncrease;
    }

    public void affectOnBattle(Battle battle) {
        battle.increaseMana(getAccount(), getManaAmountIncrease());
        battle.increaseCapacityMana(getAccount(), getManaAmountIncrease());
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Battle) {
            affectOnBattle((Battle) t);
        }

    }


    @Override
    public <T> void update(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            warrior.getBuffs().remove(this);
        }
    }

    public int getManaAmountIncrease() {
        return manaAmountIncrease;
    }
}
