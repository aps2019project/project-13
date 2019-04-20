package Model;

import java.util.ArrayList;

public class Spell extends Card {
    private ArrayList<Spell> allSpells = new ArrayList<>();
    private TargetSocietyKind targetSocietyKind;
    private SpellName spellName;
    private ArrayList<Buff> buffs;
    private ActivationCondition activationCondition;


    public Spell(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription, TargetSocietyKind targetSocietyKind, SpellName spellName, ActivationCondition activationCondition) {

        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.targetSocietyKind = targetSocietyKind;
        this.spellName = spellName;
        this.activationCondition = activationCondition;
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


}
