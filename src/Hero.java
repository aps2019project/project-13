import java.util.ArrayList;

public class Hero extends Card {

    private static ArrayList<Hero> allHeroes = new ArrayList<>();
    private Spell specialPower;
    private int spceialPowerCooldownTime;
    private HeroName heroName;
    private boolean validCounterAttack;
    private int healthPoint;
    private int actionPower;
    private int attackRange;
    private AttackKind attackKind;

    public Hero(int cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription, HeroName heroName) {
        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.heroName = heroName;
        addHero(this);
    }


    public void setValidCounterAttack(boolean validCounterAttack) {
        this.validCounterAttack = validCounterAttack;
    }

    public boolean validCounterAttack() {
        return validCounterAttack;
    }

    private void setSpecialPower(Spell specialPower) {
        this.specialPower = specialPower;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    private void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    private void setActionPower(int actionPower) {
        this.actionPower = actionPower;
    }

    public int getActionPower() {
        return actionPower;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
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


}
