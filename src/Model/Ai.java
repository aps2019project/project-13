package Model;

import Controller.GameController;
import View.BattleCommand;
import View.Error;

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

    void playGame()throws Error {
        System.out.println("gi1");
        insertCard();
        System.out.println("gi2");
        move();
        System.out.println("gi3");
        attack();
        System.out.println("gi4");
        endTurn();
    }


    private void move() throws Error{
        Card card = selectCard();
        if(card==null)
            return;
        ArrayList<String> strings = new ArrayList<>();
        if(card.getCurrentCell()==null){
            System.out.println("-------  "  + card.getCardName());
            System.out.println("gi tosh");
            return;
        }
        strings.add(Integer.toString(card.getCurrentCell().getRow()+1));
        strings.add(Integer.toString(card.getCurrentCell().getColumn()+1));
        GameController.getInstance().battleCommandManagement(BattleCommand.MOVE.setData(strings));
    }

    private void attack()throws Error {
        selectCard();
        ArrayList<Card> cards = battle.getFirstPlayerInGameCards();
        Random random = new Random();
        int numberOfCard = random.nextInt(cards.size());
        Card card = cards.get(numberOfCard);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(card.getCardId());
        GameController.getInstance().battleCommandManagement(BattleCommand.ATTACK.setData(strings));
    }

    private void insertCard()throws Error {
        ArrayList<Card> cards = battle.getSecondPlayerHand();
        Random random = new Random();
        int numberOfCard = random.nextInt(cards.size());
        Card card = cards.get(numberOfCard);
        if(card==null)
            return;
        ArrayList<String> strings = new ArrayList<>();
        strings.add(card.getCardName());
        int[] validX = {1,-1,1,-1};
        int[] validY = {1,-1,-1,1};
        int numberOfCell = random.nextInt(4);
        String x = Integer.toString(battle.getSecondPlayerDeck().getHero().getCurrentCell().getRow()+validX[numberOfCell]);
        String y = Integer.toString(battle.getSecondPlayerDeck().getHero().getCurrentCell().getColumn()+validY[numberOfCell]);
        strings.add(x);
        strings.add(y);
        GameController.getInstance().battleCommandManagement(BattleCommand.INSERT.setData(strings));
    }

    private Card selectCard()throws Error {
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
        System.out.println("gi ^ 2");
        GameController.getInstance().battleCommandManagement(BattleCommand.END_TURN);
    }
}
