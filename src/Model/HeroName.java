package Model;

import Model.AttackKind;

public enum HeroName {

    DIVE_SEFID(8000, 4, 50, AttackKind.MELEE, 2, null,"Dive Sefid"),
    SIMORGH(9000, 4, 50, AttackKind.MELEE, 8, null , "Simorq"),
    EJDEHA(8000, 4, 50, AttackKind.MELEE, 1, null , "Ezhdeha"),
    RAKSH(8000, 4, 50, AttackKind.MELEE, 2, null , "Rakhsh"),
    ZAHHAK(10000, 4, 50, AttackKind.MELEE, 3, null,"Zahak"),
    KAVEH(8000, 4, 50, AttackKind.MELEE, 3, null , "Kaveh"),
    ARASH(10000, 2, 30, AttackKind.RANGED, 2, null , "Arash"),
    AFSANEH(11000, 3, 40, AttackKind.RANGED, 2, null , "Afsaneh"),
    ESFANDIAR(12000, 3, 35, AttackKind.HYBRID, 1, null , "Esfandyar"),
    ROSTAM(8000, 7, 55, AttackKind.HYBRID, 0, null , "Rostam");


    private int price;
    private int powerAction;
    private int healthPoint;
    private int specialPowerCooldown;
    private AttackKind attackKind;
    private Spell specialPower;
    private String name;

    private HeroName() {

    }

    HeroName(int price, int powerAction, int healthPoint, AttackKind attackKind, int specialPowerCooldown, Spell specialPower , String name) {
        this.price = price;
        this.powerAction = powerAction;
        this.healthPoint = healthPoint;
        this.attackKind = attackKind;
        this.specialPowerCooldown = specialPowerCooldown;
        this.specialPower = specialPower;
        this.name=name;
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

    public String getName() {
        return name;
    }
}