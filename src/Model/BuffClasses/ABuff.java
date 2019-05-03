package Model.BuffClasses;

import Model.Account;

import java.util.ArrayList;

public abstract class ABuff implements Cloneable {
    private int duration;
    private Account account;
    private PositiveNegative positiveNegative;
    private boolean isDispellable;

    public ABuff(Account account, int duration, PositiveNegative positiveNegative, boolean isDispellable) {
        this.account = account;
        this.duration = duration;
        this.positiveNegative = positiveNegative;
        this.isDispellable = isDispellable;
    }

    public abstract <T> void affect(T t);


    public PositiveNegative getPositiveNegative() {
        return positiveNegative;
    }

    public abstract <T> void update(T t);

    public Account getAccount() {
        return account;
    }


    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration--;
    }

    public boolean isDispellable() {
        return isDispellable;
    }

    public static ArrayList<ABuff> aBuffClone(ArrayList<ABuff> aBuffs) {
        ArrayList<ABuff> aBuffsClone = new ArrayList<>();
        for (ABuff aBuff : aBuffs) {
            if (aBuff != null) {
                try {
                    aBuffsClone.add((ABuff) aBuff.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return aBuffsClone;
    }
}
