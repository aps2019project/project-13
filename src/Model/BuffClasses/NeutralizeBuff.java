package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class NeutralizeBuff extends ABuff implements Cloneable {

    public NeutralizeBuff(Account account, int duration, boolean isDispellable) {
        super(account, duration, PositiveNegative.NEUTRAL, isDispellable);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            warrior.getBuffs().clear();
        }
    }

    @Override
    public <T> void update(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            warrior.clearAllBuffs();
        }
    }
}
