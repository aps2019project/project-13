package Model.BuffClasses;

import Model.Account;
import Model.Card;
import Model.Warrior;


public class IncrementalAttackingBuff extends ABuff {

    //TODO MAYBE IT NEEDS MORE COMPLETION
    int baseAttackIncrease;
    int attackIncrease;
    int turns = 0;

    public IncrementalAttackingBuff(Account account, int duration, PositiveNegative positiveNegative, boolean isDispellable, int baseAttackIncrease) {
        super(account, duration, positiveNegative, isDispellable);
        this.baseAttackIncrease = baseAttackIncrease;

    }

    public void affectOnCard(Card card) {

        if (hasBuffType(IncrementalAttackingBuff.class, card.getBuffs())) {
            card.getBuffs().add(this);
        } else {
            incrementTurns(1);
            setAttackIncrease(baseAttackIncrease * turns);
        }
    }


    @Override
    public <T> void affect(T t) {
        if (t instanceof Card) {
            affectOnCard((Card) t);
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

    public void incrementTurns(int i) {
        setTurns(getTurns() + i);
    }

    public int getAttackIncrease() {
        return attackIncrease;
    }

    public void setAttackIncrease(int attackIncrease) {
        this.attackIncrease = attackIncrease;
    }

    public int getBaseAttackIncrease() {
        return baseAttackIncrease;
    }


    public void setBaseAttackIncrease(int baseAttackIncrease) {
        this.baseAttackIncrease = baseAttackIncrease;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
