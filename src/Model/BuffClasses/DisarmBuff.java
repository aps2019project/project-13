package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class DisarmBuff extends ABuff {


    public DisarmBuff(Account account, int duration , boolean isDispellable) {
        super(account, duration , PositiveNegative.NEGATIVE , isDispellable);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            warrior.setValidCounterAttack(false);
        }

    }

    @Override
    public <T> void update(T t) {
        decrementDuration();
        if(getDuration()==0) {
            if (t instanceof Warrior) {
                Warrior warrior = (Warrior) t;
                warrior.getBuffs().remove(this);
                warrior.setValidCounterAttack(true);
            }
        }
    }
}
