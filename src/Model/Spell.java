package Model;

import Model.BuffClasses.ABuff;

import java.util.ArrayList;

public class Spell extends Card implements Cloneable {
    private static ArrayList<Spell> allSpells = new ArrayList<>();
    private TargetSocietyKind targetSocietyKind;
    private SpellName spellName;
    private ArrayList<ABuff> buffs = new ArrayList<>();
    private ActivationCondition activationCondition;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Spell spell = (Spell) super.clone();
        ArrayList<ABuff> buffs = ABuff.aBuffClone( spell.buffs);
        spell.buffs = buffs;
        return spell;
    }

    public Spell(String cardName, String cardId, int manaCost, int darikCost, String cardDescription, TargetSocietyKind targetSocietyKind, SpellName spellName, ActivationCondition activationCondition, ArrayList<ABuff> buffs) {
        super(cardName, cardId, manaCost, darikCost, CardKind.MINION, cardDescription);
        this.targetSocietyKind = targetSocietyKind;
        this.spellName = spellName;
        this.activationCondition = activationCondition;
        addToAllSpells(this);
        if (buffs != null) {
            this.buffs.addAll(buffs);
        }
    }

    public void affectOnCell(Cell cell) {

        for (int i = 0; i < buffs.size(); i++) {
            cell.addBuff(buffs.get(i));
            buffs.get(i).affect(cell);
        }
    }

    public void affectOnWarrior(Warrior warrior) {

        for (int i = 0; i < buffs.size(); i++) {
            warrior.addBuff(buffs.get(i));
            buffs.get(i).affect(warrior);
        }
    }

    public <T> void affectSpell(T... e) {
        if (targetSocietyKind == TargetSocietyKind.CELL) {
            for (int i = 0; i < e.length; i++) {
                affectOnCell((Cell) e[i]);
            }

        } else {
            for (int i = 0; i < e.length; i++) {
                affectOnWarrior((Warrior) e[i]);
            }
        }
    }

    public ArrayList<Spell> getAllSpells() {
        return allSpells;
    }

    public void addToAllSpells(Spell spell) {
        allSpells.add(spell);
    }

    public TargetSocietyKind getTargetSocietyKind() {
        return targetSocietyKind;
    }

    public SpellName getSpellName() {
        return spellName;
    }

    public ArrayList<ABuff> getBuffs() {
        return buffs;
    }

    public ActivationCondition getActivationCondition() {
        return activationCondition;
    }

    @Override
    public String toString() {
        String str = "Type: Spell" + "- Name: " + this.getSpellName().getName() + "- MP:" + this.getManaCost() + "-Description: " + this.getCardDescription();
        return str;
    }

}
