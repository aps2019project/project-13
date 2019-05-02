package Model;

import View.ConstantMessages;
import View.Error;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private static Battle runningBattle = null;
    private Map map;
    private Account firstPlayer;
    private Account secondPlayer;
    private Account winner;
    private int turn = 1;
    private int numberOfFlagForWin ;
    private Account currentTurnPlayer;
    private int firstPlayerCapacityMana;
    private int secondPlayerCapacityMana;
    private int firstPlayerMana;
    private int secondPlayerMana;
    private Card selectedCard;
    private ArrayList<Card> firstPlayerGraveYard = new ArrayList<>();
    private ArrayList<Card> secondPlayerGraveYard = new ArrayList<>();
    private ArrayList<Card> firstPlayerHand = new ArrayList<>();
    private ArrayList<Card> secondPlayerHand = new ArrayList<>();
    private Card firstPlayerNextCard;
    private Card secondPlayerNextCard;
    private ArrayList<Cell> validCells = new ArrayList<>();
    private ArrayList<Card> firstPlayerDeck = new ArrayList<>();
    private ArrayList<Card> secondPlayerDeck = new ArrayList<>();
    private ArrayList<Card> firstPlayerInGameCards = new ArrayList<>();
    private ArrayList<Card> secondPlayerInGameCards = new ArrayList<>();
    private ArrayList<Item> firstPlayerItems = new ArrayList<>();
    private ArrayList<Item> secondPlayerItems = new ArrayList<>();
    private GameMode gameMode;
    private GameGoal gameGoal;
    private boolean endGame;
    private FlagForHoldFlagGameMode flagForHoldFlagGameMode;
    private int firstPlayerFlags;
    private int secondPlayerFlags;
    private ArrayList<FlagForCollectFlagGameMode> flagForCollectFlagGameModes = new ArrayList<>();//TODO bejaye 6 bayad moteghayyer gozasht


    public Battle(Account firstPlayer, Account secondPlayer, GameMode gameMode, GameGoal gameGoal) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameMode = gameMode;
        this.gameGoal = gameGoal;
        if (firstPlayer.getMainDeck() != null && firstPlayer.getMainDeck().isValid()) {
            firstPlayerDeck.addAll(firstPlayer.getMainDeck().getCards());
        } else throw new Error(ConstantMessages.INVALID_DECK.getMessage());
        if (secondPlayer.getMainDeck() != null && firstPlayer.getMainDeck().isValid()) {
            secondPlayerDeck.addAll(secondPlayer.getMainDeck().getCards());
        } else throw new Error(ConstantMessages.INVALID_DECK.getMessage());
        map = new Map(this);
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode = new FlagForHoldFlagGameMode("0", "Flag", ItemKind.FLAG, map);
        } else if (gameGoal == GameGoal.COLLECT_FLAG)
            setFlagForCollectFlagGameModes();
        runningBattle = this;
    }


    public void decreaseMana(int number, int numberOfPlayer) {
        if (numberOfPlayer == 1) {
            firstPlayerMana -= number;
        } else secondPlayerMana -= number;
    }

    public void selectCard(String cardID) {
        Card card;
        card = Card.findCardInArrayList(cardID, currentTurnPlayer.getMainDeck().getCards());
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
            throw new Error(ConstantMessages.INVALID_CELL_TO_MOVE.getMessage());
        }
        if (selectedCard.isAbleToMove())
            map.moveCard(selectedCard, selectedCard.getCurrentCell(), destinationCell);
        //Take Flags for win the game
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode.updateFlagCell();
            takeHoldingFlag(currentTurnPlayer);
        } else if (gameGoal == GameGoal.COLLECT_FLAG) {
            takeCollectingFlag(currentTurnPlayer);
        }
    }

    public void attack(Cell targetCell) {
        Card targetCard = targetCell.getCard();
        Warrior warrior;
        Warrior defender;
        defender = (Warrior) targetCard;
        warrior = (Warrior) selectedCard;
        if (!isValidAttack(targetCell, warrior)) {
            throw new Error(ConstantMessages.INVALID_TARGET.getMessage());
        }
        defender.decreaseHealthPoint(warrior.getActionPower() - defender.getShield());
        //TODO check valid counterAttack
        warrior.decreaseHealthPoint(defender.getActionPower());
    }

    public void attackCombo(Cell targetCell, String... warriorsCarIds) {

    }

    public void useSpecialPower(Cell cell) {


    }

    public void insertCard(String cardName, int x, int y) throws Error {

        Card card = Card.findCardInArrayListByName(cardName, currentTurnPlayer.getMainDeck().getCards());
        if (card != null) {
            Cell cell = map.getCell(x, y);
            if (isValidInsert(cell)) {
                if (turn % 2 == 1) {
                    if (firstPlayerMana >= card.getManaCost()) {
                        cell.setCard(card);
                        firstPlayerHand.remove(card);
                        addToFirstPlayerInGameCards(card);
                    } else
                        throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                } else {
                    if (secondPlayerMana >= card.getManaCost()) {
                        cell.setCard(card);
                        secondPlayerHand.remove(card);
                        addToSecondPlayerInGameCards(card);

                    } else
                        throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                }
            } else {
                throw new Error(ConstantMessages.INVALID_CELL_TO_INSERT_CARD.getMessage());
            }
        } else {
            throw new Error(ConstantMessages.INVALID_CARD_NAME.getMessage());
        }
    }

    public void endTurn() {
        endGame();
        if (endGame) {
            setHistoryAfterGame();
            return;
        }
        setMana();
        incrementTurn();
        addUsedCardsToGraveYard();

    }

    private void setHistoryAfterGame() {
        winner.incrementCountOfWins();
        int numberOfWinnerPlayer;
        if (winner.equals(firstPlayer)) {
            numberOfWinnerPlayer = 1;
        } else numberOfWinnerPlayer = 2;
        ArrayList<String> firstPlayerHistory = firstPlayer.getBattleHistory();
        firstPlayerHistory.add(secondPlayer.getUsername() + " - " + ((numberOfWinnerPlayer == 1) ? "Win" : "Lose"));
        ArrayList<String> secondPlayerHistory = secondPlayer.getBattleHistory();
        secondPlayerHistory.add(firstPlayer.getUsername() + " - " + ((numberOfWinnerPlayer == 1) ? "Win" : "Lose"));
    }

    private void setMana() {
        if (turn == 1) {
            firstPlayerCapacityMana = 2;
        } else if (turn == 2) {
            secondPlayerCapacityMana = 3;
        } else if (turn % 2 == 1 && turn < 14) {
            firstPlayerCapacityMana++;
            secondPlayerCapacityMana++;
        } else if (turn >= 14) {
            firstPlayerCapacityMana = 9;
            secondPlayerCapacityMana = 9;
        }
        firstPlayerMana = firstPlayerCapacityMana;
        secondPlayerMana = secondPlayerCapacityMana;
    }

    private void incrementTurn() {
        setFirstPlayerNextCard();
        setSecondPlayerNextCard();
        turn++;
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

    private void takeHoldingFlag(Account player) {

        for (int i = 0; i < player.getMainDeck().getCards().size(); i++) {
            if (player.getMainDeck().getCards().get(i).isInGame() && player.getMainDeck().getCards().get(i).getCurrentCell() == flagForHoldFlagGameMode.getCurrentCell()) {
                if (flagForHoldFlagGameMode.getFlagHolder() != player.getMainDeck().getCards().get(i))
                    flagForHoldFlagGameMode.setFlagHolder((Warrior) player.getMainDeck().getCards().get(i));
                return;
            }
        }
    }

    private void endOfCollectFlagGameMode() {
        if (firstPlayerFlags >= 6 / 2) // Bejaye 6 bayad moteghayyer bezarim
            setWinner(firstPlayer);
        else if (secondPlayerFlags >= 6 / 2)
            setWinner(secondPlayer);
    }

    private void takeCollectingFlag(Account player) {
        for (int i = 0; i < player.getMainDeck().getCards().size(); i++) {
            for (int j = 0; j < flagForCollectFlagGameModes.size(); j++) {
                if (player.getMainDeck().getCards().get(i).isInGame() && player.getMainDeck().getCards().get(i).getCurrentCell() == flagForCollectFlagGameModes.get(j).getCurrentCell()) {
                    if (currentTurnPlayer.equals(firstPlayer)) {
                        firstPlayerFlags++;
                        flagForCollectFlagGameModes.get(j).setOwner(player.getMainDeck().getCards().get(i));
                    } else {
                        secondPlayerFlags++;
                        flagForCollectFlagGameModes.get(j).setOwner(player.getMainDeck().getCards().get(i));
                    }
                    flagForCollectFlagGameModes.remove(j);
                    break;
                }
            }
        }
    }


    public Account getOtherTurnPlayer() {
        if (getTurn() % 2 == 1) {
            return secondPlayer;
        } else {
            return firstPlayer;
        }
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
        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                if (cell1.isEmpty() && map.getDistanceOfTwoCell(warrior.getCurrentCell(), cell1) <= 2 && isValidMove(cell1))
                    validCells.add(cell1);
            }
        }

    }

    private void findValidCellToAttack() {

        Cell[][] cells = map.getCells();
        Warrior warrior = (Warrior) selectedCard;
        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                if (!cell1.isEmpty() && isValidAttack(cell1, warrior))
                    validCells.add(cell1);
            }
        }
    }

    private void findValidCellToInsert() {

        Cell[][] cells = map.getCells();

        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                if (cell1.isEmpty())
                    validCells.add(cell1);
            }
        }
    }

    private void findValidCellToItem() {

    }

    private void findValidCellToSpell() {

    }

    private boolean isValidInsert(Cell destinationCell) {
        return destinationCell.isEmpty();
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

        if (targetCell.getCard().getAccount() == warrior.getAccount()) {
            return false;
        }

        switch (warrior.getAttackKind()) {
            case MELEE:
                return isValidMeleeAttack(targetCell, warrior);
            case RANGED:
                return isValidRangedAttack(targetCell, warrior);
            case HYBRID:
                boolean flag1 = isValidRangedAttack(targetCell, warrior);
                boolean flag2 = isValidMeleeAttack(targetCell, warrior);
                return (flag1 || flag2);
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
        firstPlayerHand = selectRandomCardsForHand(firstPlayerDeck, 5);
    }

    public void setHandOfSecondPlayer() {
        secondPlayerHand = selectRandomCardsForHand(secondPlayerDeck, 5);

    }

    private ArrayList<Card> selectRandomCardsForHand(ArrayList<Card> cards, int totalRandomCardsNeeded) {
        Random random = new Random();
        ArrayList<Card> temp = new ArrayList<>();

        for (int i = 0; i < totalRandomCardsNeeded; i++) {
            int randomIndex = random.nextInt();
            temp.add(cards.get(randomIndex));
            cards.remove(randomIndex);
        }
        return temp;
    }

    public void stratGame() {

    }

    private void setFirstPlayerNextCard() {
        Random random = new Random();
        firstPlayerNextCard = firstPlayerDeck.get(random.nextInt());
    }

    private void setSecondPlayerNextCard() {
        Random random = new Random();
        secondPlayerNextCard = firstPlayerDeck.get(random.nextInt());
    }

    public Card getNextCard() {

        if (turn % 2 == 1) {
            return getFirstPlayerNextCard();
        } else
            return getSecondPlayerNextCard();
    }

    private Card getFirstPlayerNextCard() {
        return firstPlayerNextCard;
    }

    private Card getSecondPlayerNextCard() {
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
        for (int i = 0; i < randomX.length; i++) {
            flagForCollectFlagGameModes.add(new FlagForCollectFlagGameMode(getMap().getCell(randomX[i], randomY[i])));
            getMap().getCell(randomX[i], randomY[i]).setItem(flagForCollectFlagGameModes.get(i));
        }
    }

    private void getNRandomNumber(int n, int[] randomX, int[] randomY) {

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
        for (int n : array) {
            if (n == number)
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

    private void setWinner(Account winner) {
        this.winner = winner;
    }

    public Account getWinner() {
        return winner;
    }

    public Map getMap() {
        return map;
    }

    public Account getFirstPlayer() {
        return firstPlayer;
    }

    public Account getSecondPlayer() {
        return secondPlayer;
    }

    public int getFirstPlayerCapacityMana() {
        return firstPlayerCapacityMana;
    }

    public int getSecondPlayerCapacityMana() {
        return secondPlayerCapacityMana;
    }

    public int getFirstPlayerMana() {
        return firstPlayerMana;
    }

    public int getSecondPlayerMana() {
        return secondPlayerMana;
    }

    public ArrayList<Card> getFirstPlayerGraveYard() {
        return firstPlayerGraveYard;
    }

    public ArrayList<Card> getFirstPlayerHand() {
        return firstPlayerHand;
    }

    public ArrayList<Card> getSecondPlayerHand() {
        return secondPlayerHand;
    }

    public ArrayList<Cell> getValidCells() {
        return validCells;
    }

    public ArrayList<Card> getFirstPlayerDeck() {
        return firstPlayerDeck;
    }

    public ArrayList<Card> getSecondPlayerDeck() {
        return secondPlayerDeck;
    }


    public boolean isEndGame() {
        return endGame;
    }

    public int getFirstPlayerFlags() {
        return firstPlayerFlags;
    }

    public int getSecondPlayerFlags() {
        return secondPlayerFlags;
    }

    public ArrayList<FlagForCollectFlagGameMode> getFlagForCollectFlagGameModes() {
        return flagForCollectFlagGameModes;
    }

    public static Battle getRunningBattle() {
        return runningBattle;
    }

    public static void setRunningBattle(Battle runningBattle) {
        Battle.runningBattle = runningBattle;
    }

    public void setCurrentTurnPlayer() {
        if (getTurn() % 2 == 1) {
            currentTurnPlayer = firstPlayer;
        } else {
            currentTurnPlayer = secondPlayer;
        }
    }

    public Account getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    private void addUsedCardsToGraveYard() {

        for (int i = 0; i < firstPlayerDeck.size(); i++) {
            if (firstPlayerDeck.get(i) instanceof Spell && firstPlayerDeck.get(i).isInGame()) {
                firstPlayerDeck.add(firstPlayerDeck.get(i));
            }
        }
        firstPlayerGraveYard.addAll(findDeathCards(firstPlayerInGameCards));
        firstPlayerInGameCards.removeAll(findDeathCards(firstPlayerInGameCards));
        secondPlayerGraveYard.addAll(findDeathCards(secondPlayerInGameCards));
        secondPlayerInGameCards.removeAll(findDeathCards(secondPlayerInGameCards));

    }

    private ArrayList<Card> findDeathCards(ArrayList<Card> playerInGameCards) {

        ArrayList<Card> deathCards = new ArrayList<>();
        for (Card playerInGameCard : playerInGameCards) {
            if (playerInGameCard.isInGame() && (playerInGameCard instanceof Warrior)) {
                if (((Warrior) playerInGameCard).getHealthPoint() <= 0)
                    deathCards.add(playerInGameCard);
            }
        }
        return deathCards;
    }

    @Override
    public String toString() {
        if (gameGoal == GameGoal.KILL_HERO)
            return "Hero HealthPoints     FirstPlayer: " + firstPlayer.getMainDeck().getHero().getHealthPoint()
                    + " SecondPlayerL " + secondPlayer.getMainDeck().getHero().getHealthPoint();
        else if (gameGoal == GameGoal.HOLD_FLAG) {
            return "Flag Coordinates : Row: " + flagForHoldFlagGameMode.getCurrentCell().getRow()
                    + " Column: " + flagForHoldFlagGameMode.getCurrentCell().getColumn()
                    + " Flag Owner: " + flagForHoldFlagGameMode.getFlagHolder().getAccount();
        } else {
            StringBuilder output = new StringBuilder();
            for (FlagForCollectFlagGameMode flagForCollectFlagGameMode : flagForCollectFlagGameModes) {
                output.append("CardName: ").append(flagForCollectFlagGameMode.getOwner().getCardName()).append(" ").append("Player: ").append(flagForCollectFlagGameMode.getOwner().getAccount().getUsername()).append("\n");
            }
            return output.toString();
        }
    }

    public ArrayList<Card> getSecondPlayerGraveYard() {
        return secondPlayerGraveYard;
    }

    private void addToFirstPlayerInGameCards(Card card) {
        firstPlayerInGameCards.add(card);
    }

    private void addToSecondPlayerInGameCards(Card card) {
        secondPlayerInGameCards.add(card);
    }

    public ArrayList<Card> getFirstPlayerInGameCards() {
        return firstPlayerInGameCards;
    }

    public ArrayList<Card> getSecondPlayerInGameCards() {
        return secondPlayerInGameCards;
    }

    public void setNumberOfFlagForWin(int numberOfFlagForWin) {
        this.numberOfFlagForWin = numberOfFlagForWin;
    }
}
