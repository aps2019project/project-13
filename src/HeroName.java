public enum HeroName {

    DIVE_SEFID(8000, 4, 50, AttackKind.MELEE, 2, null),
    SIMORGH(9000, 4, 50, AttackKind.MELEE, 8, null),
    EJDEHA(8000, 4, 50, AttackKind.MELEE, 1, null),
    RAKSH(8000, 4, 50, AttackKind.MELEE, 2, null),
    ZAHHAK(10000, 4, 50, AttackKind.MELEE, 3, null),
    KAVEH(8000, 4, 50, AttackKind.MELEE, 3, null),
    ARASH(10000, 2, 30, AttackKind.RANGED, 2, null),
    AFSANEH(11000, 3, 40, AttackKind.RANGED, 2, null),
    ESFANDIAR(12000, 3, 35, AttackKind.HYBRID, 1, null),
    ROSTAM(8000, 7, 55, AttackKind.HYBRID, 0, null);


    private int price;
    private int powerAction;
    private int healthPoint;
    private int specialPowerCooldown;
    private AttackKind attackKind;
    private Spell specialPower;

    private HeroName() {

    }

    private HeroName(int price, int powerAction, int healthPoint, AttackKind attackKind, int specialPowerCooldown, Spell specialPower) {
        this.price = price;
        this.powerAction = powerAction;
        this.healthPoint = healthPoint;
        this.attackKind = attackKind;
        this.specialPowerCooldown = specialPowerCooldown;
        this.specialPower = specialPower;
    }

    public int getPowerAction() {
        return powerAction;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getSpecialPowerCooldown() {
        return specialPowerCooldown;
    }

    public AttackKind getAttackKind() {
        return attackKind;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public int getPrice() {
        return price;
    }
}