package Model;

import Model.BuffClasses.ABuff;
import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.Collections;

public class Warrior extends Card implements Cloneable {
    //TODO CLONE

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
    private SpecialPowerBuffs specialPowerBuffs;
    private ArrayList<ABuff> buffs = new ArrayList<>();


    public Warrior(String cardName, String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription,
                   int healthPoint, int actionPower, int attackRange, AttackKind attackKind, SpecialPowerBuffs specialPowerBuffs) {

        super(cardName, cardId, manaCost, darikCost, cardKind, cardDescription);
        this.attackKind = attackKind;
        this.healthPoint = healthPoint;
        this.actionPower = actionPower;
        this.attackRange = attackRange;
        this.specialPowerBuffs = specialPowerBuffs;
        this.shield = 0;
    }


    public static Warrior deepClone(Warrior warrior) {
        //TODO deepClone mayNeed some work and CHECK!!!
        Cloner cloner = new Cloner();
        cloner.dontClone(Account.class);
        Warrior clonedWarrior = cloner.deepClone(warrior);
        clonedWarrior.setCardId(makeNewID(Account.getLoginedAccount().getUsername(), clonedWarrior.getCardName(), CardCollection.getCountOfCard(Account.getLoginedAccount().getCardCollection().getCards(), warrior)));
        return clonedWarrior;
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

    public SpecialPowerBuffs getSpecialPowerBuffs() {
        return specialPowerBuffs;
    }

    public void setSpecialPowerBuffs(SpecialPowerBuffs specialPowerBuffs) {
        this.specialPowerBuffs = specialPowerBuffs;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Warrior warrior = (Warrior) super.clone();
        ArrayList<ABuff> buffsClone = ABuff.aBuffClone(this.getBuffs());
        warrior.setBuffs(buffsClone);
        return warrior;
    }

    public void addBuff(ABuff buff) {
        buffs.add(buff);
    }

    public void deleteBuff(ABuff buff) {
        buffs.remove(buff);
    }

    public void clearAllBuffs() {
        buffs.clear();
    }

    public ArrayList<ABuff> getBuffs() {
        return buffs;
    }

    public void setBuffs(ArrayList<ABuff> buffs) {
        this.buffs = buffs;
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
