package Model.BuffClasses;


import Model.Account;
import Model.Warrior;

public class PowerBuff extends ABuff {


    private PowerAndWeaknessBuffType powerBuffKind;
    private int buffPower;
    private boolean isAffect = false;

    public PowerBuff(PowerAndWeaknessBuffType powerBuffKind, int buffPower, Account account, int duration, boolean isDispellable) {
        super(account, duration, PositiveNegative.POSITIVE, isDispellable);
        this.powerBuffKind = powerBuffKind;
        this.buffPower = buffPower;
    }

    public void affectOnWarrior(Warrior warrior) {
        if (powerBuffKind == PowerAndWeaknessBuffType.ATTACK) {
            warrior.increaseActionPower(getBuffPower());
        } else {
            warrior.increaseHealthPoint(getBuffPower());
        }
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            if(!isAffect()) {
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
                if (powerBuffKind == PowerAndWeaknessBuffType.ATTACK) {
                    warrior.decreaseActionPower(getBuffPower());
                } else {
                    warrior.decreaseHealthPoint(getBuffPower());
                }
            }
        }
    }

    public int getBuffPower() {
        return buffPower;
    }

    public boolean isAffect() {
        return isAffect;
    }

    public void setAffect(boolean affect) {
        isAffect = affect;
    }
}
