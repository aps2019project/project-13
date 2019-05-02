package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class PoisonBuff extends ABuff {

    int poisonDamage;

    public PoisonBuff(Account account, int duration, int poisonDamage , boolean isDispellable) {
        super(account, duration , PositiveNegative.POSITIVE , isDispellable);
        this.poisonDamage = poisonDamage;
    }


    public void affectOnWarrior(Warrior warrior) {
        if (getDuration() > 0) {
            warrior.decreaseHealthPoint(getPoisonDamage());
        }
    }


    @Override
    public <T> void affect(T t) {

        if (t instanceof Warrior) {
            affectOnWarrior((Warrior) t);
        }
    }

    @Override
    public <T> void update(T t) {

    }

    public int getPoisonDamage() {
        return poisonDamage;
    }

}
