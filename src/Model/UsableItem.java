package Model;

import Model.BuffClasses.*;
import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.Random;

public class UsableItem extends Item {
    private int DarickCost;
    private SpecialPowerBuffs specialPowerBuffs;
    private Battle battle = Battle.getRunningBattle();

    public UsableItem(String itemId, String itemDescription, String itemName, int darickCost, SpecialPowerBuffs specialPowerBuffs) {
        super(itemId, itemDescription, ItemKind.USABLE, itemName);
        battle = Battle.getRunningBattle();
        this.DarickCost = darickCost;
        this.specialPowerBuffs = specialPowerBuffs;
    }

    public static UsableItem deepCloneUse(UsableItem usableItem) {
        if (usableItem != null) {
            Cloner cloner = new Cloner();
            cloner.dontClone(Account.class);
            UsableItem usableItemClone = cloner.deepClone(usableItem);
            //TODO ITEM ID
        }
        return usableItem;
    }

    public int getDarickCost() {
        return DarickCost;
    }

    public static UsableItem findUsableItemInArrayList(String itemId, ArrayList<UsableItem> items) {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null && items.get(i).getItemId().equals(itemId)) {
                    return items.get(i);
                }
            }
        }
        return null;
    }

    public void ghosleTamid() {

        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            for (int i = 0; i < Battle.getRunningBattle().getFirstPlayerDeck().getMinions().size(); i++) {
                Battle.getRunningBattle().getFirstPlayerDeck().getMinions().get(i).addBuff(new HolyBuff(1, Battle.getRunningBattle().getFirstPlayer(), 2, true));
            }
        } else {
            for (int i = 0; i < Battle.getRunningBattle().getSecondPlayerDeck().getMinions().size(); i++) {
                Battle.getRunningBattle().getSecondPlayerDeck().getMinions().get(i).addBuff(new HolyBuff(1, Battle.getRunningBattle().getSecondPlayer(), 2, true));
            }
        }
    }

    public void soulEater() {

        Random random = new Random();

        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            int randomIndex = random.nextInt(Battle.getRunningBattle().getFirstPlayerInGameCards().size());
            Battle.getRunningBattle().getFirstPlayerInGameCards().get(randomIndex).addBuff(new PowerBuff(PowerAndWeaknessBuffType.ATTACK, 1, Battle.getRunningBattle().getFirstPlayer(), 1000000, true));
        } else {
            int randomIndex = random.nextInt(Battle.getRunningBattle().getSecondPlayerInGameCards().size());
            Battle.getRunningBattle().getSecondPlayerInGameCards().get(randomIndex).addBuff(new PowerBuff(PowerAndWeaknessBuffType.ATTACK, 1, Battle.getRunningBattle().getSecondPlayer(), 1000000, true));
        }
    }


    public void shockHammer(Warrior defender) {
        Account player;
        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            player = Battle.getRunningBattle().getSecondPlayer();
        } else
            player = Battle.getRunningBattle().getFirstPlayer();

        defender.addBuff(new DisarmBuff(player, 2, true));
    }

    public void poisonousDagger(Warrior attacker) {
        Account player;
        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            player = Battle.getRunningBattle().getSecondPlayer();
        } else
            player = Battle.getRunningBattle().getFirstPlayer();
        attacker.addBuff(new PoisonBuff(player, 1, 1, true));//TODO should be random
    }

    public void assassinationDagger() {
        Deck deck;
        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            deck = Battle.getRunningBattle().getSecondPlayerDeck();
        } else
            deck = Battle.getRunningBattle().getFirstPlayerDeck();
        deck.getHero().decreaseHealthPoint(1);
    }

    public void kingWisdom() {
        Account player;
        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            player = Battle.getRunningBattle().getSecondPlayer();
        } else
            player = Battle.getRunningBattle().getFirstPlayer();
        Battle.getRunningBattle().increaseMana(player, 1);
    }

    public void terrorHood() {
        Random random = new Random();
        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            Battle.getRunningBattle().getSecondPlayerInGameCards().get(random.nextInt(Battle.getRunningBattle().getSecondPlayerInGameCards().size())).addBuff(new WeaknessBuff(PowerAndWeaknessBuffType.ATTACK, 2, Battle.getRunningBattle().getSecondPlayer(), 1, true));
        } else
            Battle.getRunningBattle().getFirstPlayerInGameCards().get(random.nextInt(Battle.getRunningBattle().getFirstPlayerInGameCards().size())).addBuff(new WeaknessBuff(PowerAndWeaknessBuffType.ATTACK, 2, Battle.getRunningBattle().getFirstPlayer(), 1, true));
    }

    public void pareSimorgh() {

        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            if (Battle.getRunningBattle().getSecondPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                Battle.getRunningBattle().getSecondPlayerDeck().getHero().decreaseActionPower(2);
            }
        } else {
            if (Battle.getRunningBattle().getFirstPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                Battle.getRunningBattle().getFirstPlayerDeck().getHero().decreaseActionPower(2);
            }
        }
    }

    public void kamaneDamol(Warrior defender) {

        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            if (Battle.getRunningBattle().getFirstPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                defender.addBuff(new DisarmBuff(Battle.getRunningBattle().getSecondPlayer(), 2, true));
            }
        } else {
            if (Battle.getRunningBattle().getSecondPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                defender.addBuff(new DisarmBuff(Battle.getRunningBattle().getFirstPlayer(), 2, true));
            }
        }
    }

    public void namooseSepar() {
        if (Battle.getRunningBattle().getTurn() % 2 == 1) {
            Battle.getRunningBattle().getFirstPlayerDeck().getHero().addBuff(new HolyBuff(12, Battle.getRunningBattle().getFirstPlayer(), 100000, true));
        } else
            Battle.getRunningBattle().getSecondPlayerDeck().getHero().addBuff(new HolyBuff(12, Battle.getRunningBattle().getSecondPlayer(), 100000, true));
    }

    public void tajeDanaii() {
        battle=Battle.getRunningBattle();
        if (battle.getTurn() % 2 == 1) {
            battle.increaseMana(battle.getFirstPlayer(), 1);
        } else
            Battle.getRunningBattle().increaseMana(Battle.getRunningBattle().getSecondPlayer(), 1);
    }


    @Override
    public String toString() {
        return "Name:" + this.getItemName() + "- Desc: " + this.getItemDescription();
    }

    public SpecialPowerBuffs getSpecialPowerBuffs() {
        return specialPowerBuffs;
    }
}
