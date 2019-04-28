package Model;

import java.util.ArrayList;

public class Minion extends Warrior {

    private static ArrayList<Minion> allMinions = new ArrayList<>();

    private MinionName minionName;
    private boolean hasSpecialPower;

    public Minion(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription, MinionName minionName,
                  int healthPoint, int actionPower, int attackRange, AttackKind attackKind, Spell specialPower) {
        super(cardId, manaCost, darikCost, cardKind, cardDescription, healthPoint, actionPower, attackRange, attackKind, specialPower);
        this.minionName = minionName;
        addMinion(this);
    }


    public boolean hasSpecialPower() {
        return hasSpecialPower;
    }


    public MinionName getMinionName() {
        return minionName;
    }

    public static ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    private void addMinion(Minion minion) {
        allMinions.add(minion);
    }


}
