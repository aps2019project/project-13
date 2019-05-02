package Model.BuffClasses;

import Model.Account;

public abstract class ABuff {
    private int duration;
    private Account account;
    private PositiveNegative positiveNegative;
    private boolean isDispellable;

    public ABuff(Account account, int duration , PositiveNegative positiveNegative , boolean isDispellable) {
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
}
