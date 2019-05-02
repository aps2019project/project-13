package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class DisarmBuff extends ABuff {


    public DisarmBuff(Account account, int duration) {
        super(account, duration , PositiveNegative.NEGATIVE);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior)t ;
            warrior.setValidCounterAttack(false);
        }

    }

}
