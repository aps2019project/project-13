package Model;

import java.util.ArrayList;
import java.util.Random;

public class Ai {

    private Deck mainDeck;
    private Battle battle;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardsInGame;

    public Ai(Deck mainDeck, Battle battle) {
        this.mainDeck = mainDeck;
        this.battle = battle;
        hand = battle.getSecondPlayerHand();//TODO hamishe AI bazikone dovvome felan :)
        cardsInGame = battle.getSecondPlayerInGameCards();//TODO hamishe AI bazikone dovvome felan :)
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


}
