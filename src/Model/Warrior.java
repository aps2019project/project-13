package Model;

public class Warrior extends Card {


    private int healthPoint;
    private int actionPower;
    private int attackRange;
    private AttackKind attackKind;
    private boolean validCounterAttack;
    private Spell specialPower;
    private boolean isDeath;

    public Warrior(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription,
                   int healthPoint, int actionPower, int attackRange, AttackKind attackKind, Spell specialPower) {

        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.attackKind = attackKind;
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.attackRange = attackRange;
        this.specialPower = specialPower;
    }


    public int getHealthPoint() {
        return healthPoint;
    }

    public void increaseHealthPoint(int number) {
        healthPoint += number;
    }

    public void decreaseHealthPoint(int number) {
        healthPoint -= number;
    }

    public int getActionPower() {
        return actionPower;
    }

    public void increaseActionPower(int number) {
        actionPower += number;
    }

    public void decreaseActionPower(int number) {
        actionPower -= number;
    }

    private void checkDeath() {
        if (healthPoint <= 0)
            isDeath = true;
    }

    public boolean isDeath() {
        checkDeath();
        return isDeath;
    }

    public int getAttackRange() {
        return attackRange;
    }


    public AttackKind getAttackKind() {
        return attackKind;
    }

    public boolean isValidCounterAttack() {
        return validCounterAttack;
    }

    public void setValidCounterAttack(boolean validCounterAttack) {
        this.validCounterAttack = validCounterAttack;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }
}
