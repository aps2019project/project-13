package Model;

import java.util.ArrayList;
import java.util.Random;

public class Ai extends Account {

    private Battle battle;

    public Ai( int numberOfAi) {
        super("AI", "Password");
        if (numberOfAi == 1) {
            this.setMainDeck(Deck.AiDeckBuilder(1));
        } else if (numberOfAi == 2) {
            this.setMainDeck(Deck.AiDeckBuilder(2));
        } else if (numberOfAi == 3) {
            this.setMainDeck(Deck.AiDeckBuilder(3));
        }
        setDarick(1000000000);

    }

    public void setDeck(Deck deck) {
        setMainDeck(deck);
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public void playGame() {
        Random random = new Random();
        int randomInteger = random.nextInt() % 4;
        switch (randomInteger) {
            case 0:
                attack();
                break;
            case 1:
                move();
                break;
            case 2:
                insertCard();
                break;
            case 3:
                endTurn();
        }
    }

    private void attack() {

    }

    private void move() {


    }

    private void insertCard() {

    }
    private void endTurn() {

    }
}
