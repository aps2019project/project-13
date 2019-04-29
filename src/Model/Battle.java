package Model;


import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private Map map;
    private Account firstPlayer;
    private Account secondPlayer;
    private Account winner;
    private int turn = 1;
    private int firstPlayerCapacityMana;
    private int secondPlayerCapacityMana;
    private int turnMaximumMana = 2;
    private int firstPlayerMana = 2;
    private int secondPlayerMana = 2;
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
    private boolean endGame;
    private FlagForHoldFlagGameMode flagForHoldFlagGameMode;
    private int firstPlayerFlags;
    private int secondPlayerFlags;
    private FlagForCollectFlagGameMode[] flagForCollectFlagGameModes = new FlagForCollectFlagGameMode[6];//TODO bejaye 6 bayad moteghayyer gozasht


    Battle(Account firstPlayer, Account secondPlayer, GameMode gameMode, GameGoal gameGoal) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameMode = gameMode;
        this.gameGoal = gameGoal;
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode = new FlagForHoldFlagGameMode("0", "Flag", ItemKind.FLAG);
        } else if (gameGoal == GameGoal.COLLECT_FLAG)
            setFlagForCollectFlagGameModes();
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
        Account account;

        if (numberOfPlayer == 1) {
            account = firstPlayer;
        } else {
            account = secondPlayer;
        }
        ArrayList<Card> cards = new ArrayList<>();
        Cell[][] cells = map.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getCard() != null && cells[i][j].getCard().getAccount().equals(account)) {
                    cards.add(cells[i][j].getCard());
                }
            }
        }

        return cards;
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
        if (!isValidMove(destinationCell)) {
            return;
        }
        if (selectedCard.isAbleToMove())
            map.moveCard(selectedCard, selectedCard.getCurrentCell(), destinationCell);
    }

    public void attack(Cell targetCell) {
        Card targetCard = targetCell.getCard();
        Warrior warrior;
        Warrior defender;
        defender = (Warrior) targetCard;
        warrior = (Warrior) selectedCard;
        if (!isValidAttack(targetCell)) {
            return;
        }
        defender.decreaseHealthPoint(warrior.getActionPower());
        //TODO check valid counterAttack
        warrior.decreaseHealthPoint(defender.getActionPower());


    }

    public void attackCombo(Cell targetCell, String... warriorsCarIds) {

    }

    public void useSpecialPower(Cell cell) {

    }

    public String showHand(int numberOfPlayer) {
        return null;
    }

    public void insertCard(String cardID, Cell cell) {
        Card card;
        if (turn % 2 == 1) {
            card = Card.findCardInArrayList(cardID, firstPlayer.getMainDeck().getCards());
        } else {
            card = Card.findCardInArrayList(cardID, secondPlayer.getMainDeck().getCards());
        }
        if (!isValidInsert(cardID, cell))
            cell.setCard(card);
    }

    public void endTurn() {
        endGame();
        if (endGame) {
            //TODO if game finish what to do , what not to do :\
            return;
        }
        setPlayersManaForNewTurn();
        incrementTurn();
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode.updateFlagCell();
            takeFlag(firstPlayer);
            takeFlag(secondPlayer);
        }

    }

    private void incrementTurnMaximumMana() {
        turnMaximumMana++;
    }

    private void setPlayersManaForNewTurn() {
        incrementTurnMaximumMana();
        firstPlayerMana = turnMaximumMana;
        secondPlayerMana = turnMaximumMana;
    }

    private void incrementTurn() {
        turn++;
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
        switch (gameGoal) {
            case KILL_HERO:
                endOfKillHeroGameMode();
                break;
            case HOLD_FLAG:
                endOfHoldFlagGameMode();
                break;
            case COLLECT_FLAG:
                endOfCollectFlagGameMode();

        }

    }

    private void endOfKillHeroGameMode() {
        if (firstPlayer.getMainDeck().getHero().isDeath()) {
            endGame = true;
            setWinner(secondPlayer);
        } else if (secondPlayer.getMainDeck().getHero().isDeath()) {
            endGame = true;
            setWinner(firstPlayer);
        }
    }

    private void endOfHoldFlagGameMode() {
        if (flagForHoldFlagGameMode.getNumberOfTurns() == 6) {
            setWinner(flagForHoldFlagGameMode.getFlagHolder().getAccount());
            endGame = true;
        }
    }

    private void takeFlag(Account player) {
        for (int i = 0; i < player.getMainDeck().getCards().size(); i++) {
            if (player.getMainDeck().getCards().get(i).getCurrentCell() == flagForHoldFlagGameMode.getCurrentCell()) {
                if (flagForHoldFlagGameMode.getFlagHolder() != player.getMainDeck().getCards().get(i))
                    flagForHoldFlagGameMode.setFlagHolder((Warrior) player.getMainDeck().getCards().get(i));
                return;
            }
        }
    }

    private void endOfCollectFlagGameMode() {
        if (firstPlayerFlags >= flagForCollectFlagGameModes.length)
            setWinner(firstPlayer);
        else if (secondPlayerFlags >= flagForCollectFlagGameModes.length)
            setWinner(secondPlayer);

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

    private boolean isValidInsert(String cardID, Cell destinationCell) {
        Card card;
        if (turn % 2 == 1) {
            card = Card.findCardInArrayList(cardID, firstPlayer.getMainDeck().getCards());
        } else {
            card = Card.findCardInArrayList(cardID, secondPlayer.getMainDeck().getCards());
        }
        if (destinationCell.getCard() != null) {
            //TODO send error
            return false;
        }
        if ((turn % 2 == 1 && card.getManaCost() > firstPlayerMana) || (turn % 2 == 0 && card.getManaCost() > secondPlayerMana)) {
            //TODO send error
            return false;
        }
        if (validCells.contains(destinationCell)) {
            return true;
        } else {
            //TODO send error
            return false;
        }
    }

    private boolean isValidMove(Cell targetCell) {
        return true;
    }

    private boolean isValidComboAttack(Cell targetCell, String... warriorsCardID) {
        return true;
    }

    private boolean isValidAttack(Cell targetCell) {
        Card targetCard = targetCell.getCard();
        Warrior warrior;
        Warrior defender;
        if (targetCard instanceof Warrior) {
            defender = (Warrior) targetCard;
        } else {
            //TODO send error
            return false;
        }
        if (selectedCard instanceof Warrior) {
            warrior = (Warrior) selectedCard;
        } else {
            //TODO send error
            return false;
        }
        if (warrior.getAccount().equals(defender.getAccount())) {
            //TODO send error
            return false;
        }
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

    public void setFlagForCollectFlagGameModes() {
        int[] randomX = new int[6];
        int[] randomY = new int[6];
        getNRandomNumber(6, randomX, randomY);
        for (int i = 0; i < flagForCollectFlagGameModes.length; i++) {
            flagForCollectFlagGameModes[i] = new FlagForCollectFlagGameMode(Map.getCell(randomX[i], randomY[i]));
            Map.getCell(randomX[i], randomY[i]).setItem(flagForCollectFlagGameModes[i]);
        }
    }

    public void getNRandomNumber(int n, int[] randomX, int[] randomY) {

        randomX = new int[n];
        randomY = new int[n];
        Random random = new Random();

        for (int i = 0; i < randomX.length; i++) {
            randomX[i] = random.nextInt();
            randomY[i] = random.nextInt();
            while (hasNumber(randomX, randomX[i])) {
                randomX[i] = random.nextInt();
            }
            while (hasNumber(randomY, randomY[i])) {
                randomY[i] = random.nextInt();
            }
        }
    }

    private boolean hasNumber(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number)
                return true;
        }
        return false;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public GameGoal getGameGoal() {
        return gameGoal;
    }

    public FlagForHoldFlagGameMode getFlagForHoldFlagGameMode() {
        return flagForHoldFlagGameMode;
    }

    public void setWinner(Account winner) {
        this.winner = winner;
    }

    public Account getWinner() {
        return winner;
    }
}
