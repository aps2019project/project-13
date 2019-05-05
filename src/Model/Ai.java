package Model;

import java.util.ArrayList;
import java.util.Random;

public class Ai {

    private static Ai ai1;
    private static Ai ai2;
    private static Ai ai3;
    private Deck mainDeck;
    private Battle battle;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardsInGame;

    public Ai(Deck mainDeck, Battle battle, int numberOfAi) {
        this.mainDeck = mainDeck;
        this.battle = battle;
        hand = battle.getSecondPlayerHand();//TODO hamishe AI bazikone dovvome felan :)
        cardsInGame = battle.getSecondPlayerInGameCards();//TODO hamishe AI bazikone dovvome felan :)
        if (numberOfAi == 1) {
            ai1 = this;
        } else if (numberOfAi == 2) {
            ai2 = this;
        } else {
            ai3 = this;
        }

    }


    public Deck getMainDeck() {
        return mainDeck;
    }


    public void playGame() {
        Random random = new Random();
        int randomInteger = random.nextInt() % 3;
        switch (randomInteger) {
            case 0:
                attack();
                break;
            case 1:
                move();
                break;
            case 2:
                insertCard();
                battle.endTurn();
        }
    }

    private void attack() {

    }

    private void move() {

    }

    private void insertCard() {

    }


    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getCardsInGame() {
        return cardsInGame;
    }

    public static Ai getAi1() {
        return ai1;
    }

    public static Ai getAi2() {
        return ai2;
    }

    public static Ai getAi3() {
        return ai3;
    }
}
