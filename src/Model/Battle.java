package Model;

import Model.Account;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;

enum KindOfActionForValidCells {
    MOVE, ATTACK, INSERT, ITEM, SPELL
}

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
    private FlagForCollectFlagGameMode[] flagForCollectFlagGameModes = new FlagForCollectFlagGameMode[7];//TODO bejaye 7 bayad moteghayyer gozasht


    Battle(Account firstPlayer, Account secondPlayer, GameMode gameMode, GameGoal gameGoal) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameMode = gameMode;
        this.gameGoal = gameGoal;
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode = new FlagForHoldFlagGameMode("0", "Flag", ItemKind.FLAG);
        }
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
        if (selectedCard.isAbleToMove())
            map.moveCard(selectedCard, selectedCard.getCurrentCell(), destinationCell);
        // TODO valid moves
    }

    public void attack(Cell targetCell) {
        Card targetCard = targetCell.getCard();
        Warrior warrior;
        Warrior defender;
        if (targetCard instanceof Warrior) {
            defender = (Warrior) targetCard;
        } else {
            //TODO send error
        }
        if (selectedCard instanceof Warrior) {
            warrior = (Warrior) selectedCard;
        } else {
            //TODO send error
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

    private void clearValidCellsList() {
        validCells.clear();
    }

    public void findValidCell(KindOfActionForValidCells kindOfActionForValidCells) {

        clearValidCellsList();
        switch (kindOfActionForValidCells) {
            case MOVE:
                findValidCellToMove();
                break;
            case ATTACK:
                findValidCellToAttack();
                break;
            case INSERT:
                findValidCellToInsert();
                break;
            case ITEM:
                findValidCellToItem();
                break;
            case SPELL:
                findValidCellToSpell();
                break;
        }

    }


    private void findValidCellToMove() {
        Cell[][] cells = map.getCells();
        if (!selectedCard.isAbleToMove())
            return;
        Warrior warrior = (Warrior) selectedCard;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isEmpty() && map.getDistanceOfTwoCell(warrior.getCurrentCell(), cells[i][j]) <= 2 && isValidMove(cells[i][j]))
                    validCells.add(cells[i][j]);
            }
        }

    }

    private void findValidCellToAttack() {

        Cell[][] cells = map.getCells();
        Warrior warrior = (Warrior) selectedCard;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (isValidAttack(cells[i][j], warrior))
                    validCells.add(cells[i][j]);
            }
        }
    }

    private void findValidCellToInsert() {

        Cell[][] cells = map.getCells();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isEmpty())
                    validCells.add(cells[i][j]);
            }
        }
    }

    private void findValidCellToItem() {

    }

    private void findValidCellToSpell() {


    }

    private boolean isValidMove(Cell destinationCell) {
        if (destinationCell.getRow() == selectedCard.getCurrentCell().getRow()) {
            if (destinationCell.getColumn() < selectedCard.getCurrentCell().getColumn()) {
                return map.getCells()[destinationCell.getRow()][destinationCell.getColumn() + 1].isEmpty();
            } else {
                return map.getCells()[destinationCell.getRow()][destinationCell.getColumn() - 1].isEmpty();
            }

        } else if (destinationCell.getColumn() == selectedCard.getCurrentCell().getColumn()) {
            if (destinationCell.getRow() < selectedCard.getCurrentCell().getRow()) {
                return map.getCells()[destinationCell.getRow() + 1][destinationCell.getColumn()].isEmpty();
            } else {
                return map.getCells()[destinationCell.getRow() - 1][destinationCell.getColumn()].isEmpty();
            }
        }
        return false;
    }

    private boolean isValidComboAttack(Cell targetCell, String... warriorsCardID) {
        return true;
    }

    private boolean isValidAttack(Cell targetCell, Warrior warrior) {

        switch (warrior.getAttackKind()) {
            case MELEE:
                return isValidMeleeAttack(targetCell, warrior);
            case RANGED:
                return isValidRangedAttack(targetCell, warrior);
            case HYBRID:
                boolean flag1 = isValidRangedAttack(targetCell, warrior);
                boolean flag2 = isValidMeleeAttack(targetCell, warrior);
                return flag1 || flag2;
        }


        return true;
    }

    private boolean isValidRangedAttack(Cell targetCell, Warrior warrior) {

        if (Math.abs(targetCell.getRow() - warrior.getCurrentCell().getRow()) <= 1 && Math.abs(targetCell.getColumn() - warrior.getCurrentCell().getColumn()) <= 1) {
            return false;
        } else {
            return map.getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= warrior.getAttackRange();
        }
    }

    private boolean isValidMeleeAttack(Cell targetCell, Warrior warrior) {
        return Math.abs(targetCell.getRow() - warrior.getCurrentCell().getRow()) <= 1 && Math.abs(targetCell.getColumn() - warrior.getCurrentCell().getColumn()) <= 1;
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
