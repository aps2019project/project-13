package Model;

import java.util.ArrayList;

public class Minion extends Warrior implements Cloneable {

    private static ArrayList<Minion> allMinions = new ArrayList<>();


    private boolean hasSpecialPower;

    public Minion(String cardName, String cardId, int manaCost, int darikCost, String cardDescription, MinionName minionName,
                  int healthPoint, int actionPower, int attackRange, AttackKind attackKind, SpecialPowerBuffs specialPowerBuffs) {
        super(cardName, cardId, manaCost, darikCost, CardKind.MINION, cardDescription, healthPoint, actionPower, attackRange, attackKind, specialPowerBuffs);

        addMinion(this);
    }

    public boolean hasSpecialPower() {
        return hasSpecialPower;
    }



    public static ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    private void addMinion(Minion minion) {
        allMinions.add(minion);
    }

    @Override
    public String toString() {
        return "Type: Minion " + " - Name: " + this.getCardName() + " - Class " + this.getAttackKind() + " - AP: " + this.getActionPower() + " - HP: " + this.getHealthPoint() +
                "- MP:" + this.getManaCost() + "- Special Power: " + this.getCardDescription() + "- CardId:" + this.getCardId();
    }
}
