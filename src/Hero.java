import java.util.ArrayList;

public class Hero extends Card {

    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private Spell specialPower;
    private int specialPowerCooldownTime;
    private HeroName heroName;
    private boolean validCounterAttack;
    private int healthPoint;
    private int actionPower;
    private int attackRange;
    private AttackKind attackKind;

    public Hero(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription,
                HeroName heroName, AttackKind attackKind, int healthPoint, int actionPower, int attackRange,
                Spell specialPower, int specialPowerCooldownTime) {
        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.heroName = heroName;
        this.specialPower = specialPower;
        this.attackKind = attackKind;
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.attackRange = attackRange;
        this.specialPowerCooldownTime = specialPowerCooldownTime;
        addHero(this);
    }


    public void setValidCounterAttack(boolean validCounterAttack) {
        this.validCounterAttack = validCounterAttack;
    }

    public boolean validCounterAttack() {
        return validCounterAttack;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public int getHealthPoint() {
        return healthPoint;
    }


    public int getActionPower() {
        return actionPower;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public HeroName getHeroName() {
        return heroName;
    }

    public AttackKind getAttackKind() {
        return attackKind;
    }

    public static ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }

    private void addHero(Hero hero) {
        allHeroes.add(hero);
    }


    public int getSpecialPowerCooldownTime() {
        return specialPowerCooldownTime;
    }
}
