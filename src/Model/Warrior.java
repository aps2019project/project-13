package Model;

import Model.BuffClasses.ABuff;

import java.util.ArrayList;

public class Warrior extends Card implements Cloneable{

    private int healthPoint;
    private int actionPower;
    private int attackRange;
    private AttackKind attackKind;
    private boolean validCounterAttack;
    private Spell specialPower;
    private boolean isDeath;
    private int shield;
    private boolean isValidToAttack = true;
    private boolean IsValidToMove = true;

    public Warrior(String cardName, String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription,
                   int healthPoint, int actionPower, int attackRange, AttackKind attackKind, Spell specialPower) {

        super(cardName, cardId, manaCost, darikCost, cardKind, cardDescription);
        this.attackKind = attackKind;
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.attackRange = attackRange;
        this.specialPower = specialPower;
        this.shield = 0;
    }

    public void changeShield(int i) {
        setShield(getShield() + i);
    }

    public void decreaseShield(int i) {
        setShield(getShield() - i);
    }

    public void increaseShield(int i) {
        setShield(getShield() + i);
    }

    public void setShield(int shield) {
        this.shield = shield;
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

    public int getShield() {
        return shield;
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

    public boolean isValidToAttack() {
        return isValidToAttack;
    }

    public void setValidToAttack(boolean validToAttack) {
        isValidToAttack = validToAttack;
    }

    public boolean isValidToMove() {
        return IsValidToMove;
    }

    public void setValidToMove(boolean validToMove) {
        this.IsValidToMove = validToMove;
    }

    //TODO MAYBE THIS NEEDS TO BE IMPLEMENTED HERE. THIS CLONE IS CURRENTLY Implemented IN CARD
    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        Warrior warrior = (Warrior) super.clone();
        ArrayList<ABuff> buffsClone = ABuff.aBuffClone(this.getBuffs());
        warrior.setBuffs(buffsClone);
        return warrior;
    }*/
}
