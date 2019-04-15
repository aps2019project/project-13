import java.util.ArrayList;

public class Spell extends Card {
    ArrayList<Spell> allSpells = new ArrayList<>();
    TargetSocietyKind targetSocietyKind;
    SpellName spellName;
    ArrayList<Buff> buffs;
    ActivationCondition activationCondition;


    public Spell(int cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription, TargetSocietyKind targetSocietyKind, SpellName spellName, ActivationCondition activationCondition) {

        super(cardId, manaCost, darikCost, cardKind, cardDescription);
        this.targetSocietyKind = targetSocietyKind;
        this.spellName = spellName;
        this.activationCondition = activationCondition;
    }


}
