package Model;

public enum BuffName {

    HOLY_BUFF(true), POWER_BUFF(true), POISON_BUFF(false),
    WEAKNESS_BUFF(false), STUN_BUFF(false), DISARM_BUFF(false),
    MANA_BUFF(true), FLAME_BUFF(false), NEUTRALIZE_All_BUFFS(false);


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
