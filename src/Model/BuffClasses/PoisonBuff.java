package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class PoisonBuff extends ABuff {

    int poisonDamage;

    public PoisonBuff(Account account, int duration, int poisonDamage) {
        super(account, duration , PositiveNegative.POSITIVE);
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

    public int getPoisonDamage() {
        return poisonDamage;
    }


    public void setPoisonDamage(int poisonDamage) {
        this.poisonDamage = poisonDamage;

    }
}
