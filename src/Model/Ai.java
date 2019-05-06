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
        insertCard();
        move();
        attack();
        endTurn();
    }


    private void move() throws Error{
        Random random = new Random();
        Card card = selectCard();
        if(card==null)
            return;
        ArrayList<String> strings = new ArrayList<>();
        if(card.getCurrentCell()==null){
            return;
        }
        battle.findValidCell(KindOfActionForValidCells.MOVE);
        ArrayList<Cell> cells = battle.getValidCells();
        if(cells==null)
            return;
        int numberOfCell = random.nextInt(cells.size());
        Cell cell = cells.get(numberOfCell);
        strings.add(Integer.toString(cell.getRow()));
        strings.add(Integer.toString(cell.getColumn()));
        GameController.getInstance().battleCommandManagement(BattleCommand.MOVE.setData(strings));
    }

    private void attack()throws Error {
        Random random = new Random();
        selectCard();
        battle.findValidCell(KindOfActionForValidCells.ATTACK);
        ArrayList<Cell> cells = battle.getValidCells();
        if(cells==null || cells.size()==0)
            return;
        int numberOfCell = random.nextInt(cells.size());
        Cell cell = cells.get(numberOfCell);
        if(cell.getCard()==null || cells.size()==0)
            return;
        ArrayList<String> strings = new ArrayList<>();
        strings.add(cell.getCard().getCardId());
        GameController.getInstance().battleCommandManagement(BattleCommand.ATTACK.setData(strings));
    }

    private void insertCard()throws Error {
        ArrayList<Card> cards = battle.getSecondPlayerHand();
        Random random = new Random();
        if(cards==null || cards.size()==0)
            return;
        int numberOfCard = random.nextInt(cards.size());
        Card card = cards.get(numberOfCard);
        if(card==null)
            return;
        ArrayList<String> strings = new ArrayList<>();
        strings.add(card.getCardName());
        battle.findValidCell(KindOfActionForValidCells.INSERT);
        ArrayList<Cell> cells = battle.getValidCells();
        if(cells==null || cells.size()==0)
            return;
        int numberOfCell = random.nextInt(cells.size());
        Cell cell = cells.get(numberOfCell);
        String x = Integer.toString(cell.getRow());
        String y = Integer.toString(cell.getColumn());
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
        GameController.getInstance().battleCommandManagement(BattleCommand.END_TURN);
    }
}
