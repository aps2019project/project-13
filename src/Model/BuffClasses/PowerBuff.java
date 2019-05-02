package Model.BuffClasses;


import Model.Account;
import Model.Warrior;

public class PowerBuff extends ABuff {


    private PowerAndWeaknessBuffType powerBuffKind;
    private int buffPower;

    public PowerBuff(PowerAndWeaknessBuffType powerBuffKind, int buffPower, Account account , int duration) {
        super(account , duration);
        this.powerBuffKind = powerBuffKind;
        this.buffPower = buffPower;
    }
    public void affectOnWarrior(Warrior warrior)
    {
        if (powerBuffKind == PowerAndWeaknessBuffType.ATTACK) {
            warrior.increaseActionPower(getBuffPower());
        } else {
            warrior.increaseHealthPoint(getBuffPower());
        }
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            affectOnWarrior((Warrior) t);
        }
    }

    @Override
    public <T> void update(T t) {

    }

    public int getBuffPower() {
        return buffPower;
    }
}
