package Model;

import java.util.ArrayList;

public class Hero extends Warrior {

    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private int specialPowerCoolDownTime;
    private HeroName heroName;

    public Hero(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription,
                HeroName heroName, AttackKind attackKind, int healthPoint, int actionPower, int attackRange,
                Spell specialPower, int specialPowerCooldownTime) {
        super(cardId, manaCost, darikCost, cardKind, cardDescription, healthPoint, actionPower, attackRange, attackKind, specialPower);
        this.heroName = heroName;
        this.specialPowerCoolDownTime = specialPowerCooldownTime;
        addHero(this);
    }


    public HeroName getHeroName() {
        return heroName;
    }

    public static ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }

    private void addHero(Hero hero) {
        allHeroes.add(hero);
    }

    public int getSpecialPowerCoolDownTime() {
        return specialPowerCoolDownTime;
    }
}
