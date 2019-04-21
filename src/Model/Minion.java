package Model;

import java.util.ArrayList;

public class Minion extends Card {

    private static ArrayList<Minion> allMinions = new ArrayList<>();

    private MinionName minionName;
    private int healthPoint;
    private int actionPower;
    private int attackRange;
    private AttackKind attackKind;
    private boolean validCounterAttack;
    private boolean hasSpecialPower;
    private Spell specialPower;

    public Minion(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription, MinionName minionName,
                  int healthPoint, int actionPower, int attackRange, AttackKind attackKind, Spell specialPower) {
        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.minionName = minionName;
        this.attackKind = attackKind;
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.attackRange = attackRange;
        this.specialPower = specialPower;
        addMinion(this);
    }

    public void setValidCounterAttack(boolean validCounterAttack) {
        this.validCounterAttack = validCounterAttack;
    }

    public boolean validCounterAttack() {
        return validCounterAttack;
    }

    public void setHasSpecialPower(boolean hasSpecialPower) {
        this.hasSpecialPower = hasSpecialPower;
    }

    public boolean hasSpecialPower() {
        return hasSpecialPower;
    }

    public Spell getSpecialPower() {
        return specialPower;
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

    public int getAttackRange() {
        return attackRange;
    }

    public MinionName getMinionName() {
        return minionName;
    }

    public AttackKind getAttackKind() {
        return attackKind;
    }

    public static ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    private void addMinion(Minion minion) {
        allMinions.add(minion);
    }


}
