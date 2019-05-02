package Model.BuffClasses;


import Model.Warrior;

public class PowerBuff extends ABuff {


    private PowerAndWeaknessBuffType powerBuffKind;
    private int buffPower;

    public PowerBuff(PowerAndWeaknessBuffType powerBuffKind, int buffPower, int duration) {
        super(duration);
        this.powerBuffKind = powerBuffKind;
        this.buffPower = buffPower;
    }

    @Override
    public <T> void affect(T t) {
        if (t instanceof Warrior) {
            Warrior warrior = (Warrior) t;
            if (powerBuffKind == PowerAndWeaknessBuffType.ATTACK) {
                warrior.increaseActionPower(buffPower);
            } else
                warrior.increaseHealthPoint(buffPower);
        }
    }

    @Override
    public <T> void update(T t) {

    }
}
