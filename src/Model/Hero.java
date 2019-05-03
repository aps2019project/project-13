package Model;

import java.util.ArrayList;

public class Hero extends Warrior {

    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private int specialPowerCoolDownTime;
    private HeroName heroName;

    public Hero(String cardName, String cardId, int manaCost, int darikCost, String cardDescription,
                HeroName heroName, AttackKind attackKind, int healthPoint, int actionPower, int attackRange,
                Spell specialPower, int specialPowerCooldownTime) {
        super(cardName, cardId, manaCost, darikCost, CardKind.HERO, cardDescription, healthPoint, actionPower, attackRange, attackKind, specialPower);
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

    @Override
    public String toString() {
        String str = "Name: " + this.getHeroName().getName() + "- AP: " + this.getActionPower() + "- HP: " + this.getHealthPoint() + "- Class: " + getAttackKind().name() + "- Special Power: " + getCardDescription();
        return str;
    }
}
