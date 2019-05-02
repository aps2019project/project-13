package Model.BuffClasses;

import Model.Account;

public abstract class ABuff {
    int duration;
    Account account;

    public ABuff(Account account, int duration) {
        this.account = account;
        this.duration = duration;
    }

    public abstract <T> void affect(T t);

    public <T> void update(T t){
        changeDuration(-1);

    };

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void changeDuration(int i) {
        setDuration(getDuration() - i);
    }
}
