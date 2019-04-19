package Model;

import java.util.ArrayList;

public class Minion extends Card {

    private static ArrayList<Minion> allMinions = new ArrayList<>();
    private boolean validCounterAttack;
    private boolean hasSpecialPower;
    private Spell specialPower;
    private int healthPoint;
    private int actionPower;
    private MinionName minionName;
    private int attackRange;
    private AttackKind attackKind;

    public Minion(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription, MinionName minionName) {
        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.minionName = minionName;
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
