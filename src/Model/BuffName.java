package Model;

public enum BuffName {

    HOLY_BUFF(true), POWER_BUFF(true), POISON_BUFF(false),
    WEAKNESS_BUFF(false), STUN_BUFF(false), DISARM_BUFF(false),
    MANA_BUFF(true);

    private boolean isPositive;

    private BuffName() {



    }

    private BuffName(boolean isPositive) {
        this.isPositive = isPositive;
    }


    public boolean isPositive() {
        return isPositive;
    }
}
