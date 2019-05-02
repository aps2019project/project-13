package Model.BuffClasses;

import Model.Account;

public abstract class ABuff {
    int duration;
    Account account;
    PositiveNegative positiveNegative;

    public ABuff(Account account, int duration , PositiveNegative positiveNegative) {
        this.account = account;
        this.duration = duration;
        this.positiveNegative = positiveNegative;
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
}
