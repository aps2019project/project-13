package Model;

import java.util.ArrayList;

public class Spell extends Card {
    private static ArrayList<Spell> allSpells = new ArrayList<>();
    private TargetSocietyKind targetSocietyKind;
    private SpellName spellName;
    private  ArrayList<Buff> buffs = new ArrayList<>();
    private ActivationCondition activationCondition;


    public Spell(String cardName , String cardId, int manaCost, int darikCost, String cardDescription, TargetSocietyKind targetSocietyKind, SpellName spellName, ActivationCondition activationCondition, ArrayList<Buff> buffs) {
        super(cardName , cardId, manaCost, darikCost, CardKind.MINION, cardDescription);
        this.targetSocietyKind = targetSocietyKind;
        this.spellName = spellName;
        this.activationCondition = activationCondition;
        if (buffs!=null) {
            this.buffs.addAll(buffs);
        }
    }

    public void affectAllBuffsOnCard(Card card)
    {
        for (int i =0;i<buffs.size();i++)
        {
            Buff buff = buffs.get(i);
            if (buff!=null)
            { //TODO EFFECT DURATION MUST BE ADDED SOMEHOW
                buff.affectOnCard(card,buff.getPowerAndWeaknessBuffKind(),buff.getBuffNumber());
            }
        }
    }

    public ArrayList<Spell> getAllSpells() {
        return allSpells;
    }

    public void addToAddSpells(Spell spell) {
        allSpells.add(spell);
    }

    public TargetSocietyKind getTargetSocietyKind() {
        return targetSocietyKind;
    }

    public SpellName getSpellName() {
        return spellName;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public ActivationCondition getActivationCondition() {
        return activationCondition;
    }

    @Override
    public String toString() {
        String str = "Type: Spell" +  "- Name: "+ this.getSpellName().getName() + "- MP:" + this.getManaCost() + "-Description: " + this.getCardDescription();
        return str;
    }
}
