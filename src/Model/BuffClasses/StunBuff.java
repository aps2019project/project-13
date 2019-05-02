package Model.BuffClasses;

import Model.Account;
import Model.Warrior;

public class StunBuff extends ABuff {


    public StunBuff(Account account, int duration) {
        super(account, duration, PositiveNegative.NEGATIVE);
    }

    public void affectOnWarriro(Warrior warrior) {
        warrior.setValidToAttack(false);
        warrior.setValidToMove(false);
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            affectOnWarriro((Warrior) t);
        }

    }

    @Override
    public <T> void update(T t) {

        decrementDuration();
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            warrior.getBuffs().remove(this);
        }

    }


}
