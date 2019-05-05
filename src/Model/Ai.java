package Model;

import java.util.ArrayList;
import java.util.Random;

public class Ai extends Account{

    private static Ai ai1;
    private static Ai ai2;
    private static Ai ai3;
    private Battle battle;

    public Ai(Battle battle, int numberOfAi) {
        super("name" , "Password");
        this.battle = battle;
        if (numberOfAi == 1) {
            ai1 = this;
        } else if (numberOfAi == 2) {
            ai2 = this;
        } else {
            ai3 = this;
        }
        setDarick(1000000000);

    }

    public void addCardToAi(int numberOfAi , Card card){
        if (numberOfAi == 1) {
            ai1.getMainDeck().addCard(card);
        } else if (numberOfAi == 2) {
            ai2.getMainDeck().addCard(card);
        } else {
            ai3.getMainDeck().addCard(card);
        }
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
