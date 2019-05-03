package Model;

import Model.BuffClasses.ABuff;

import java.util.ArrayList;

public class Spell extends Card {
    private static ArrayList<Spell> allSpells = new ArrayList<>();
    private TargetSocietyKind targetSocietyKind;
    private SpellName spellName;
    private ArrayList<ABuff> buffs = new ArrayList<>();
    private ActivationCondition activationCondition;


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

    public <T> void affectSpell(T e) {
        if (targetSocietyKind == TargetSocietyKind.CELL) {
            Cell cell = (Cell) e;
            for (ABuff buff : buffs) {
                cell.addBuff(buff);
                buff.affect(cell);
            }
        } else {
            Warrior warrior = (Warrior) e;
            for (ABuff buff : buffs) {
                warrior.addBuff(buff);
                buff.affect(warrior);
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
