package Model.BuffClasses;

import Model.Account;

public class NeutralizeBuff extends ABuff {

    public NeutralizeBuff(Account account , int duration) {
        super(account ,duration);
    }

    @Override
    public <T> void affect(T t) {

    }

    @Override
    public <T> void update(T t) {

    }
}
