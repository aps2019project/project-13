package Model;

import java.lang.annotation.Target;

public enum SpellName {
    TOTAL_DISARM(0), AREA_DISPEL(2), EMPOWER(1), FIREBALL(1), GOD_STRENGTH(2), HELLFIRE(3),
    LIGHTING_BOLT(2), POISON_LAKE(5), MADNESS(0), ALL_DISARM(9), ALL_POISON(8), DISPEL(0),
    HELATH_WITH_PROFIT(0), GHAZA_BOKHOR_JOON_BEGIRI(2), ALL_POWER(4), ALL_ATTACK(4), WEAKENING(1),
    SACRIFICE(3), KINGS_GUARD(3), SHOCK(1);


    private SpellName() {

    }

    private int manaCost;

    private SpellName(int manaCost) {

        this.manaCost = manaCost;
    }


}
