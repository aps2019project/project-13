package Model;

public enum SpellName {
    TOTAL_DISARM(0, "Total Disarm"),
    AREA_DISPEL(2, "Area Dispel"),
    EMPOWER(1, "Empower"),
    FIREBALL(1, "Fireball"),
    GOD_STRENGTH(2, "God Strength"),
    HELLFIRE(3, "HellFire"),
    LIGHTING_BOLT(2, "Lightning Bolt"),
    POISON_LAKE(5, "Poison Lake"),
    MADNESS(0, "Madness"),
    ALL_DISARM(9, "All Disarm"),
    ALL_POISON(8, "All Poison"),
    DISPEL(0, "Dispel"),
    HEALTH_WITH_PROFIT(0, "Health With Profit"),
    POWER_UP(2, "Power Up"),
    ALL_POWER(4, "All Power"),
    ALL_ATTACK(4, "All Attack"),
    WEAKENING(1, "Weakening"),
    SACRIFICE(3, "Sacrifice"),
    KINGS_GUARD(3, "King's Guard"),
    SHOCK(1, "Shock");

    private int manaCost;
    private String name;

    private SpellName() {

    }

    private SpellName(int manaCost, String name) {

        this.manaCost = manaCost;
        this.name = name;
    }


    public int getManaCost() {
        return manaCost;
    }

    public String getName() {
        return name;
    }
}
