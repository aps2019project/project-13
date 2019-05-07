package Model.BuffClasses;

import Model.Account;
import Model.Warrior;


public class HolyBuff extends ABuff implements Cloneable {
    private int shield;
    private boolean isAffected;

    //TODO CHECK LOGIC
    public HolyBuff(int shield, Account account, int duration, boolean isDispellable) {
        super(account, duration, PositiveNegative.POSITIVE, isDispellable);
        this.shield = shield;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }


    public boolean isAffected() {
        return isAffected;
    }

    public void setAffected(boolean affected) {
        isAffected = affected;
    }

    private void affectOnWarrior(Warrior warrior) {
        if (getDuration() > 0 && !isAffected()) {
            warrior.increaseShield(getShield());
            setAffected(true);
        }

    }


    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            affectOnWarrior(warrior);
            if (getDuration() <= 0 && isAffected()) {
                warrior.decreaseShield(getShield());
                setAffected(false);
            }
        }
    }

    @Override
    public <T> void update(T t) {

        decrementDuration();
        if (getDuration() == 0) {
            if (t instanceof Warrior) {
                ((Warrior) t).getBuffs().remove(this);
            }
        }
    }
}
