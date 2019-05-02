package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class WeaknessBuff extends ABuff {

    private PowerAndWeaknessBuffType weaknessBuffType;
    private int buffPower;

    public WeaknessBuff(PowerAndWeaknessBuffType weaknessBuffType, int buffPower, Account account, int duration) {
        super(account , duration);
        this.weaknessBuffType = weaknessBuffType;
        this.buffPower = buffPower;
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            if (weaknessBuffType == PowerAndWeaknessBuffType.ATTACK)
                warrior.decreaseActionPower(buffPower);
            else
                warrior.decreaseHealthPoint(buffPower);
        }

    }

    @Override
    public <T> void update(T t) {

    }
}
