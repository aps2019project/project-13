package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class NeutralizeBuff extends ABuff {

    public NeutralizeBuff(Account account, int duration) {
        super(account, duration);
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

    }
}
