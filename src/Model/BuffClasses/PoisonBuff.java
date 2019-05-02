package Model.BuffClasses;

import Model.Account;

public class PoisonBuff extends ABuff {

    public PoisonBuff(Account account , int duration){
        super(account , duration);
    }

    @Override
    public <T> void affect(T t) {

    }

    @Override
    public <T> void update(T t) {

    }
}
