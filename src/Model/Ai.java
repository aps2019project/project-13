package Model;

import Controller.GameController;
import View.BattleCommand;

import java.util.ArrayList;
import java.util.Random;

public class Ai extends Account {

    private Battle battle;

    public Ai(int numberOfAi) {
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

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    void playGame() {
        attack();
        move();
        insertCard();
        endTurn();
    }

    private void attack() {
        selectCard();
        ArrayList<Card> cards = battle.getFirstPlayerInGameCards();
        Random random = new Random();
        int numberOfCard = random.nextInt(cards.size());
        Card card = cards.get(numberOfCard);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(card.getCardId());
        GameController.getInstance().battleCommandManagement(BattleCommand.ATTACK.setData(strings));
    }


    private void move() {
        Card card = selectCard();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(Integer.toString(card.getCurrentCell().getRow()+1));
        strings.add(Integer.toString(card.getCurrentCell().getColumn()+1));
        GameController.getInstance().battleCommandManagement(BattleCommand.MOVE.setData(strings));
    }

    private void insertCard() {
        ArrayList<Card> cards = battle.getSecondPlayerHand();
        Random random = new Random();
        int numberOfCard = random.nextInt(cards.size());
        Card card = cards.get(numberOfCard);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(card.getCardName());
        GameController.getInstance().battleCommandManagement(BattleCommand.INSERT.setData(strings));
    }

    private Card selectCard() {
        ArrayList<Card> cards = battle.getSecondPlayerInGameCards();
        Random random = new Random();
        int numberOfCard = random.nextInt(cards.size());
        Card card = cards.get(numberOfCard);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(card.getCardId());
        GameController.getInstance().battleCommandManagement(BattleCommand.SELECT.setData(strings));
        return card;
    }

    private void endTurn() {

    }
}
