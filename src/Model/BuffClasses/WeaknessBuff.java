package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class WeaknessBuff extends ABuff {

    private PowerAndWeaknessBuffType weaknessBuffType;
    private int buffPower;
    private boolean isAffect = false;


    public WeaknessBuff(PowerAndWeaknessBuffType weaknessBuffType, int buffPower, Account account, int duration, boolean isDispellable) {
        super(account, duration, PositiveNegative.NEGATIVE, isDispellable);
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
            if (!isAffect()) {
                affectOnWarrior((Warrior) t);
                setAffect(true);
            }
        }

    }

    @Override
    public <T> void update(T t) {
        decrementDuration();
        if (getDuration() == 0) {
            if (t instanceof Warrior) {
                Warrior warrior = (Warrior) t;
                warrior.getBuffs().remove(this);
                if (weaknessBuffType == PowerAndWeaknessBuffType.ATTACK)
                    warrior.increaseActionPower(buffPower);
                else {
                    warrior.increaseHealthPoint(buffPower);
                }
            }
        }
    }

    public boolean isAffect() {
        return isAffect;
    }

    public void setAffect(boolean affect) {
        isAffect = affect;
    }
}
