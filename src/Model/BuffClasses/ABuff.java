package Model.BuffClasses;

import Model.Account;

public abstract class ABuff {
    int duration;
    Account account;

    public ABuff(Account account, int duration ) {
        this.account = account;
        this.duration = duration;
    }

    public abstract <T> void affect(T t);

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
