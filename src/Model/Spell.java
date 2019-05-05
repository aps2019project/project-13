package Model;

import com.rits.cloning.Cloner;

import java.util.ArrayList;

public class Spell extends Card implements Cloneable {
    private static ArrayList<Spell> allSpells = new ArrayList<>();
    private TargetSocietyKind targetSocietyKind;
    private SpellName spellName;
    private ActivationCondition activationCondition;
    SpecialPowerBuffs specialPowerBuffs;


    public static Spell deepClone(Spell spell)
    {
        Cloner cloner = new Cloner();
        cloner.dontClone(Account.class);
        return cloner.deepClone(spell);
    }

    protected Object clone() throws CloneNotSupportedException {
        Spell spell = (Spell) super.clone();
        SpecialPowerBuffs specialPowerBuffs =(SpecialPowerBuffs) spell.specialPowerBuffs.clone();
        spell.specialPowerBuffs = specialPowerBuffs;
        return spell;
    }

    public Spell(String cardName, String cardId, int manaCost, int darikCost, String cardDescription, TargetSocietyKind targetSocietyKind,
             ActivationCondition activationCondition, SpecialPowerBuffs specialPowerBuffs) {
        super(cardName, cardId, manaCost, darikCost, CardKind.MINION, cardDescription);
        this.targetSocietyKind = targetSocietyKind;
        this.spellName = spellName;
        this.activationCondition = activationCondition;
        this.specialPowerBuffs = specialPowerBuffs;
        addToAllSpells(this);
//        if (buffs != null) {
//            this.buffs.addAll(buffs);
//        }
    }
//
//    public void affectOnCell(Cell cell) {
//
//        for (int i = 0; i < buffs.size(); i++) {
//            cell.addBuff(buffs.get(i));
//            buffs.get(i).affect(cell);
//        }
//    }
//
//    public void affectOnWarrior(Warrior warrior) {
//
//        for (int i = 0; i < buffs.size(); i++) {
//            warrior.addBuff(buffs.get(i));
//            buffs.get(i).affect(warrior);
//        }
//    }

    public <T> void affectSpell(T... e) {
        if (e.length>0) {
            specialPowerBuffs.useBuffsOnGenericArray(e);
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

    public SpecialPowerBuffs getSpecialPowerBuffs() {
        return specialPowerBuffs;
    }

    public void setSpecialPowerBuffs(SpecialPowerBuffs specialPowerBuffs) {
        this.specialPowerBuffs = specialPowerBuffs;
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
