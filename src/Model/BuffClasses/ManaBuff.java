package Model.BuffClasses;

import Model.Account;

public class ManaBuff extends ABuff {

    int manaAmountIncrease;

    public ManaBuff(int manaAmountIncrease,Account account , int duration) {
        super(account ,duration);
        this.manaAmountIncrease = manaAmountIncrease;
    }

    @Override
    public <T> void affect(T t) {
        if(t instanceof Integer){

        }


    }

    @Override
    public <T> void update(T t) {

    }
}
