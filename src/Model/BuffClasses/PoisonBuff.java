package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class PoisonBuff extends ABuff {

    public PoisonBuff(Account account , int duration){
        super(account , duration);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            warrior.decreaseHealthPoint(1);
        }
    }
    @Override
    public <T> void update(T t) {

    }
}
