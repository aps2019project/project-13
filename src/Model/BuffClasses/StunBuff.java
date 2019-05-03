package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class StunBuff extends ABuff {


    public StunBuff(Account account, int duration, boolean isDispellable) {
        super(account, duration, PositiveNegative.NEGATIVE, isDispellable);
    }

    public void affectOnWarrior(Warrior warrior) {
        warrior.setValidToAttack(false);
        warrior.setValidToMove(false);
        warrior.setValidCounterAttack(false);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            affectOnWarrior((Warrior) t);
        }

    }

    @Override
    public <T> void update(T t) {

        decrementDuration();
        if (getDuration() == 0) {
            if (t instanceof Warrior) {
                Warrior warrior = (Warrior) t;
                warrior.getBuffs().remove(this);
                warrior.setValidToAttack(true);
                warrior.setValidToMove(true);
                warrior.setValidCounterAttack(true);
            }
        }

    }


}
