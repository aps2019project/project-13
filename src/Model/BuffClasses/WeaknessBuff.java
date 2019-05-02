package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class WeaknessBuff extends ABuff {

    private PowerAndWeaknessBuffType weaknessBuffType;
    private int buffPower;


    public WeaknessBuff(PowerAndWeaknessBuffType weaknessBuffType, int buffPower, Account account, int duration , boolean isDispellable) {
        super(account, duration , PositiveNegative.NEGATIVE , isDispellable);
        this.weaknessBuffType = weaknessBuffType;
        this.buffPower = buffPower;
    }

    public void affectOnWarrior(Warrior warrior) {
        if (weaknessBuffType == PowerAndWeaknessBuffType.ATTACK)
            warrior.decreaseActionPower(buffPower);
        else {
            warrior.decreaseHealthPoint(buffPower);
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
}
