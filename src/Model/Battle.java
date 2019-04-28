package Model;

import Model.Account;

import java.util.ArrayList;

public class Battle {
    private Map map;
    private Account firstPlayer;
    private Account secondPlayer;
    private int turn = 1;
    private int firstPlayerCapacityMana;
    private int secondPlayerCapacityMana;
    private int firstPlayerMana;
    private int secondPlayerMana;
    private Card selectedCard;
    private ArrayList<Card> graveYardCards = new ArrayList<>();
    private ArrayList<Card> firstPlayerHand = new ArrayList<>();
    private ArrayList<Card> secondPlayerHand = new ArrayList<>();
    private Card firstPlayerNextCard;
    private Card secondPlayerNextCard;
    private ArrayList<Cell> validCells = new ArrayList<>();
    private ArrayList<Card> firstPlayerDeck = new ArrayList<>();
    private ArrayList<Card> secondPlayerDeck = new ArrayList<>();
    private ArrayList<Card> firstPlayerUnUsesdCard = new ArrayList<>();
    private ArrayList<Card> secondPlayerUnUsedCard = new ArrayList<>();
    private ArrayList<Item> firstPlayerItems = new ArrayList<>();
    private ArrayList<Item> secondPlayerItems = new ArrayList<>();
    private GameMode gameMode;
    private GameGoal gameGoal;

    Battle(Account firstPlayer, Account secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void nextTurn() {
        turn++;
        setMana();
    }

    public void setMana() {
        if (turn == 1) {
            firstPlayerCapacityMana = 2;
        } else if (turn == 2) {
            secondPlayerCapacityMana = 3;
        } else if (turn % 2 == 1 && turn < 14) {
            firstPlayerCapacityMana++;
            secondPlayerCapacityMana++;
        }
        if (turn >= 14) {
            firstPlayerCapacityMana = 9;
            secondPlayerCapacityMana = 9;
        }
        firstPlayerMana = firstPlayerCapacityMana;
        secondPlayerMana = secondPlayerCapacityMana;
    }

    public void decreaseMana(int number, int numberOfPlayer) {
        if (numberOfPlayer == 1) {
            firstPlayerMana -= number;
        } else secondPlayerMana -= number;
    }

    public void gameInfo() {

    }

    public void showMinions(boolean isFreindly) {
        if ((isFreindly && turn % 2 == 1) || (!isFreindly && turn % 2 == 0)) {
            showMinionsOfPlayer(1);
        } else {
            showMinionsOfPlayer(2);
        }
    }

    public ArrayList<Card> showMinionsOfPlayer(int numberOfPlayer) {
        Account account ;
        if(numberOfPlayer==1){
            account = firstPlayer ;
        }else {
            account = secondPlayer ;
        }
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Cell> cells = map.getCells() ;
        for (Cell cell:
             cells) {
            if(cell.getCard()!=null && cell.getCard().getAccount().equals(account)){
                cards.add(cell.getCard());
            }
        }
        return cards ;
    }

    public void showCardInfo(String cardID) {

    }

    public void selectCard(String cardID) {
        Card card;
        if (turn % 2 == 1) {
            card = Card.findCardInArrayList(cardID, firstPlayer.getMainDeck().getCards());
        } else {
            card = Card.findCardInArrayList(cardID, secondPlayer.getMainDeck().getCards());
        }
        selectedCard = card;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public int getTurn() {
        return turn;
    }

    public void moveCard(Cell destinationCell) {
        map.moveCard(selectedCard, selectedCard.getCurrentCell(), destinationCell);
        // TODO valid moves
    }

    public void attack(Cell targetCell) {
        Card targetCard = targetCell.getCard();
        if (targetCard instanceof Minion) {

        }

    }

    public void attackCombo(Cell targetCell, String... warriorsCarIds) {

    }

    public void useSpecialPower(Cell cell) {

    }

    public String showHand(int numberOfPlayer) {
        return new String();
    }

    public void insertCard(String cardID, Cell cell) {

    }

    public void endTurn() {

    }

    public void showCollectable() {

    }

    public void selectCollectable(String collectableID) {

    }

    public void showItemInfo(String itemID) {

    }

    public void showNextCard() {

    }

    public void enterGraveYard() {

    }

    public void showGraveYardCardInfo(String cardID) {

    }

    public void showGraveYardCards() {

    }

    public void helpMenu() {

    }

    public void endGame() {

    }

    public void exit() {

    }

    public void findValidCell(String kindOfAcction) {

    }

    public void findValidCellToMove() {

    }

    public void findValidCellToAttack() {

    }

    public void findValidCellToInsert() {

    }

    public void findValidCellToItem() {

    }

    public void findValidCellToSpell() {

    }

    private boolean isVAlidMove(Cell destinationCell) {
        return true;
    }

    private boolean isValidComboAttack(Cell targetCell, String... warriorsCardID) {
        return true;
    }

    private boolean isValidAttack(Cell targetCell, String warriorsCardID) {
        return true;
    }

    private boolean isValidSpeicalPower(int row, int column) {
        return true;
    }

    public void setHandOfFirstPlayer() {

    }

    public void setHandOfSecondPlayer() {

    }

    public void stratGame() {

    }

    public Card getFirstPlayerNextCard() {
        return firstPlayerNextCard;
    }

    public Card getSecondPlayerNextCard() {
        return secondPlayerNextCard;
    }

    public ArrayList<Item> getFirstPlayerItems() {
        return firstPlayerItems;
    }

    public ArrayList<Item> getSecondPlayerItems() {
        return secondPlayerItems;
    }

    public void addFirstPlayerItems(Item firstPlayerItems) {
        this.firstPlayerItems.add(firstPlayerItems);
    }

    public void addSecondPlayerItems(Item secondPlayerItems) {
        this.secondPlayerItems.add(secondPlayerItems);
    }

    public int[] getSixRandomNumber() {
        return new int[6];
    }
}
