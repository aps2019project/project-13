package Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Hero extends Warrior implements Cloneable {

    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private int specialPowerCoolDownTime;
    private int remainginTurntoCoolDown = 0;
    // private HeroName heroName;


    public int getRemainginTurntoCoolDown() {
        return remainginTurntoCoolDown;
    }

    public void setRemainginTurntoCoolDown(int remainginTurntoCoolDown) {
        this.remainginTurntoCoolDown = remainginTurntoCoolDown;
    }

    public void decreaseCoolDonw() {
        setRemainginTurntoCoolDown(getRemainginTurntoCoolDown() - 1);
    }

    public Hero(String cardName, String cardId, int manaCost, int darikCost, String cardDescription,
                HeroName heroName, AttackKind attackKind, int healthPoint, int actionPower, int attackRange,
                SpecialPowerBuffs specialPowerBuffs, int specialPowerCooldownTime) {
        super(cardName, cardId, manaCost, darikCost, CardKind.HERO, cardDescription, healthPoint, actionPower, attackRange, attackKind, specialPowerBuffs);
        //  this.heroName = heroName;
        this.specialPowerCoolDownTime = specialPowerCooldownTime;
        addHero(this);
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
        return "Type: Hero " + "Name: " + this.getCardName() + "- AP: " + this.getActionPower() +
                "- HP: " + this.getHealthPoint() + "- Class: " + getAttackKind().name() +
                "- Special Power: " + getCardDescription() + "- CardId:" + this.getCardId();
    }
}
