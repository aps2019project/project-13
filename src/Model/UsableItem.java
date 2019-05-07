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

        if (battle.getTurn() % 2 == 1) {
            for (int i = 0; i < battle.getFirstPlayerDeck().getMinions().size(); i++) {
                battle.getFirstPlayerDeck().getMinions().get(i).addBuff(new HolyBuff(1, battle.getFirstPlayer(), 2, true));
            }
        } else {
            for (int i = 0; i < battle.getSecondPlayerDeck().getMinions().size(); i++) {
                battle.getSecondPlayerDeck().getMinions().get(i).addBuff(new HolyBuff(1, battle.getSecondPlayer(), 2, true));
            }
        }
    }

    public void soulEater() {

        Random random = new Random();

        if (battle.getTurn() % 2 == 1) {
            int randomIndex = random.nextInt(battle.getFirstPlayerInGameCards().size());
            battle.getFirstPlayerInGameCards().get(randomIndex).addBuff(new PowerBuff(PowerAndWeaknessBuffType.ATTACK, 1, battle.getFirstPlayer(), 1000000, true));
        } else {
            int randomIndex = random.nextInt(battle.getSecondPlayerInGameCards().size());
            battle.getSecondPlayerInGameCards().get(randomIndex).addBuff(new PowerBuff(PowerAndWeaknessBuffType.ATTACK, 1, battle.getSecondPlayer(), 1000000, true));
        }
    }


    public void shockHammer(Warrior defender) {
        Account player;
        if (battle.getTurn() % 2 == 1) {
            player = battle.getSecondPlayer();
        } else
            player = battle.getFirstPlayer();

        defender.addBuff(new DisarmBuff(player, 2, true));
    }

    public void poisonousDagger(Warrior attacker) {
        Account player;
        if (battle.getTurn() % 2 == 1) {
            player = battle.getSecondPlayer();
        } else
            player = battle.getFirstPlayer();
        attacker.addBuff(new PoisonBuff(player, 1, 1, true));//TODO should be random
    }

    public void assassinationDagger() {
        Deck deck;
        if (battle.getTurn() % 2 == 1) {
            deck = battle.getSecondPlayerDeck();
        } else
            deck = battle.getFirstPlayerDeck();
        deck.getHero().decreaseHealthPoint(1);
    }

    public void kingWisdom() {
        Account player;
        if (battle.getTurn() % 2 == 1) {
            player = battle.getSecondPlayer();
        } else
            player = battle.getFirstPlayer();
        battle.increaseMana(player, 1);
    }

    public void terrorHood() {
        Random random = new Random();
        if (battle.getTurn() % 2 == 1) {
            battle.getSecondPlayerInGameCards().get(random.nextInt(battle.getSecondPlayerInGameCards().size())).addBuff(new WeaknessBuff(PowerAndWeaknessBuffType.ATTACK, 2, battle.getSecondPlayer(), 1, true));
        } else
            battle.getFirstPlayerInGameCards().get(random.nextInt(battle.getFirstPlayerInGameCards().size())).addBuff(new WeaknessBuff(PowerAndWeaknessBuffType.ATTACK, 2, battle.getFirstPlayer(), 1, true));
    }

    public void pareSimorgh() {

        if (battle.getTurn() % 2 == 1) {
            if (battle.getSecondPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                battle.getSecondPlayerDeck().getHero().decreaseActionPower(2);
            }
        } else {
            if (battle.getFirstPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                battle.getFirstPlayerDeck().getHero().decreaseActionPower(2);
            }
        }
    }

    public void kamaneDamol(Warrior defender) {

        if (battle.getTurn() % 2 == 1) {
            if (battle.getFirstPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                defender.addBuff(new DisarmBuff(battle.getSecondPlayer(), 2, true));
            }
        } else {
            if (battle.getSecondPlayerDeck().getHero().getAttackKind() != AttackKind.MELEE) {
                defender.addBuff(new DisarmBuff(battle.getFirstPlayer(), 2, true));
            }
        }
    }

    public void namooseSepar() {
        if (battle.getTurn() % 2 == 1) {
            battle.getFirstPlayerDeck().getHero().addBuff(new HolyBuff(12, battle.getFirstPlayer(), 100000, true));
        } else
            battle.getSecondPlayerDeck().getHero().addBuff(new HolyBuff(12, battle.getSecondPlayer(), 100000, true));
    }

    public void tajeDanaii() {
        if (battle.getTurn() % 2 == 1) {
            battle.increaseMana(battle.getFirstPlayer(), 1);
        } else
            battle.increaseMana(battle.getSecondPlayer(), 1);
    }


    @Override
    public String toString() {
        return "Name:" + this.getItemName() + "- Desc: " + this.getItemDescription();
    }

    public SpecialPowerBuffs getSpecialPowerBuffs() {
        return specialPowerBuffs;
    }
}
