package Model;

import Model.BuffClasses.ABuff;
import Model.BuffClasses.ManaBuff;
import View.ConstantMessages;
import View.Error;
import View.Show;

import java.util.ArrayList;
import java.util.Random;

public class Battle {

    private static Battle runningBattle = null;
    private Map map;
    private Account firstPlayer;
    private Account secondPlayer;
    private Account winner;
    private int turn = 1;
    private int numberOfFlagForWin;
    private Account currentTurnPlayer;
    private int firstPlayerCapacityMana;
    private int secondPlayerCapacityMana;
    private int firstPlayerMana = 1000;
    private int secondPlayerMana = 1000;
    private Card selectedCard;
    private ArrayList<Card> firstPlayerGraveYard = new ArrayList<>();
    private ArrayList<Card> secondPlayerGraveYard = new ArrayList<>();
    private ArrayList<Card> firstPlayerHand = new ArrayList<>();
    private ArrayList<Card> secondPlayerHand = new ArrayList<>();
    private Card firstPlayerNextCard;
    private Card secondPlayerNextCard;
    private ArrayList<Cell> validCells = new ArrayList<>();
    private Deck firstPlayerDeck;
    private Deck secondPlayerDeck;
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
    private ArrayList<FlagForCollectFlagGameMode> flagForCollectFlagGameModes = new ArrayList<>();


    public Battle(Account firstPlayer, Account secondPlayer, GameMode gameMode, GameGoal gameGoal) throws Error {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameMode = gameMode;
        this.gameGoal = gameGoal;
        if (secondPlayer instanceof Ai) {
            ((Ai) secondPlayer).setBattle(this);
        }
        map = new Map(this);
        runningBattle = this;
        checkDeckAtFirst(firstPlayer, secondPlayer);
        insertPlayerHeroesInMap();
        setHandOfFirstPlayer();
        setHandOfSecondPlayer();
        if (getTurn() % 2 == 1)
            setFirstPlayerNextCard();
        if (getTurn() % 2 == 0)
            setSecondPlayerNextCard();
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode = new FlagForHoldFlagGameMode("0", "Flag", ItemKind.FLAG, map.getCell(2, 4));
            map.getCell(2, 4).setItem(flagForHoldFlagGameMode);
        }
    }

    public Battle(Account firstPlayer, Account secondPlayer, GameMode gameMode, GameGoal gameGoal, int numberOfFlagForWin) throws Error {
        this(firstPlayer, secondPlayer, gameMode, gameGoal);
        this.numberOfFlagForWin = numberOfFlagForWin;
        setFlagForCollectFlagGameModes(numberOfFlagForWin);
    }

    private void checkDeckAtFirst(Account firstPlayer, Account secondPlayer) {
        if (firstPlayer.getMainDeck() != null && Deck.validateDeck(firstPlayer.getMainDeck())) {
            firstPlayerDeck = Deck.deepClone(firstPlayer.getMainDeck());
        } else {
            throw new Error(ConstantMessages.INVALID_DECK.getMessage());
        }
        if (secondPlayer.getMainDeck() != null && Deck.validateDeck(secondPlayer.getMainDeck())) {
            secondPlayerDeck = Deck.deepClone(secondPlayer.getMainDeck());
        } else {
            throw new Error(ConstantMessages.INVALID_DECK_SECOND_USER.getMessage());
        }
    }

    public void handelBattleSinglePlayer() {


    }

    public void handelBattleMultiPlayer() {

    }

    public void decreaseMana(int number, int numberOfPlayer) {
        if (numberOfPlayer == 1) {
            firstPlayerMana -= number;
        } else secondPlayerMana -= number;
    }

    public void selectCard(Card card) {
        selectedCard = card;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public int getTurn() {
        return turn;
    }

    public void moveCard(int x, int y) {
        Cell cell = map.getCell(x, y);

        if (!isValidMove(x, y)) {
            throw new Error(ConstantMessages.INVALID_CELL_TO_MOVE.getMessage());
        }
        if (selectedCard.isAbleToMove()) {
            map.moveCard(selectedCard, cell);
            selectedCard.setAbleToMove(false);
        } else
            throw new Error(ConstantMessages.CARD_MOVED_BEFORE.getMessage());
        //Take Flags for win the game
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode.updateFlagCell();
            takeHoldingFlag(firstPlayerInGameCards);
            takeHoldingFlag(secondPlayerInGameCards);
        } else if (gameGoal == GameGoal.COLLECT_FLAG) {
            takeCollectingFlag(firstPlayerInGameCards);
            takeCollectingFlag(secondPlayerInGameCards);
        }
    }

    public void attack(String cardID, Warrior warrior, boolean isAttack) {
        Cell targetCell = getCellFromCardID(cardID);
        Card targetCard = targetCell.getCard();
        Warrior defender;
        defender = (Warrior) targetCard;
        if (!isValidAttack(targetCell, warrior)) {
            throw new Error(ConstantMessages.INVALID_TARGET.getMessage());
        }
        if (warrior.getSpecialPowerBuffs().getActivationCondition().equals(ActivationCondition.ATTACK)) {
            warrior.getSpecialPowerBuffs().useBuffsOnGeneric(warrior);
            warrior.getSpecialPowerBuffs().useBuffsOnGeneric(targetCell);
        }
        if (warrior.isValidToAttack()) {
            defender.decreaseHealthPoint(warrior.getActionPower() - defender.getShield());
            warrior.setValidToAttack(false);
        }
        affectBuffs(warrior, targetCell, defender);
        if (defender.isValidCounterAttack() && isAttack) {
            attack(warrior.getCardId(), defender, false);
        }
        if (defender.getHealthPoint() <= 0 && defender.getSpecialPowerBuffs().getActivationCondition().equals(ActivationCondition.DEATH)) {
            defender.getSpecialPowerBuffs().useBuffsOnGeneric(warrior);
        }

        if (gameGoal == GameGoal.HOLD_FLAG)
            flagForHoldFlagGameMode.updateFlagHolder();

    }

    private void affectBuffs(Warrior warrior, Cell targetCell, Warrior defender) {
        ArrayList<ABuff> aBuffs = warrior.getBuffs();
        for (ABuff aBuff :
                aBuffs) {
            if (aBuff instanceof ManaBuff) {
                aBuff.affect(this);
            } else {
                aBuff.affect(defender);
                aBuff.affect(targetCell);
            }
        }
    }

    private Cell getCellFromCardID(String cardID) {
        Cell targetCell = null;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (map.getCells()[i][j].getCard() != null && map.getCells()[i][j].getCard().getCardId().equals(cardID)) {
                    targetCell = map.getCells()[i][j];
                    break;
                }
            }
        }
        return targetCell;
    }

    public void attackCombo(Cell targetCell, String... warriorsCarIds) {
        //TODO combo :(
    }

    public void useSpecialPower(Hero hero, int x, int y) {
        if (hero.getSpecialPowerBuffs() == null) {
            throw new Error(ConstantMessages.NO_SPECIAL_POWER.getMessage());
        }
        if (hero.getRemainginTurntoCoolDown() <= 0) {


            if (!isValidSpeicalPower(x, y)) {
                throw new Error(ConstantMessages.INVALID_CELL_TO_USE_SPECIAL_POWER.getMessage());
            }
            if (turn % 2 == 1) {
                if (firstPlayerMana < hero.getSpecialPower().getManaCost()) {
                    throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                }
            } else {
                if (secondPlayerMana < hero.getSpecialPower().getManaCost()) {
                    throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                }
            }
            applySpell(hero.getSpecialPowerBuffs(), x, y);

        } else {
            throw new Error(ConstantMessages.COOLDOWN.getMessage());
        }
    }

    private void applySpell(Spell spell, int x, int y) {


    }

    public void insertCard(String cardName, int x, int y) throws Error {
        setCurrentTurnPlayer();
        Card card;
        if (turn % 2 == 1) {
            card = Card.findCardInArrayListByName(cardName, firstPlayerHand);
        } else
            card = Card.findCardInArrayListByName(cardName, secondPlayerHand);

        if (card != null) {
            if (card instanceof Spell) {
                applySpell((Spell) card, x, y);
                return;
            }
            Cell cell = map.getCell(x, y);
            if (isValidInsert(cell)) {
                if (turn % 2 == 1) {
                    if (firstPlayerMana >= card.getManaCost()) {
                        cell.setCard(card);
                        card.setCurrentCell(cell);
                        firstPlayerHand.remove(card);
                        addToFirstPlayerInGameCards(card);
                        card.setInGame(true);
                        card.setAbleToMove(true);
                    } else
                        throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                } else {
                    if (secondPlayerMana >= card.getManaCost()) {
                        cell.setCard(card);
                        card.setCurrentCell(cell);
                        secondPlayerHand.remove(card);
                        addToSecondPlayerInGameCards(card);
                        card.setInGame(true);
                        card.setAbleToMove(true);

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

    public void endTurn() throws Error {
        endGame();
        if (isEndGame()) {
            setHistoryAfterGame();
            return;
        }
        setMana();
        incrementTurn();
        if (firstPlayerHand.size() < 5 && getTurn() % 2 == 1) {
            firstPlayerHand.add(firstPlayerNextCard);
            firstPlayerNextCard = null;
        }
        if (secondPlayerHand.size() < 5 && getTurn() % 2 == 0) {
            secondPlayerHand.add(secondPlayerNextCard);
            secondPlayerNextCard = null;
        }
        if (turn % 2 == 0) {
            if (secondPlayer instanceof Ai) {
                try {
                    ((Ai) secondPlayer).playGame();
                } catch (Error error) {
                    endTurn();
                }
            }
        }
        if (gameGoal == GameGoal.HOLD_FLAG)
            flagForHoldFlagGameMode.incrementNumberOfTurns();
        for (Card card :
                getSecondPlayerInGameCards()) {
            if (card instanceof Warrior) {
                if (((Warrior) card).getSpecialPowerBuffs().getTargetSocietyKind().equals(TargetSocietyKind.SELF)) {
                    ((Warrior) card).getSpecialPowerBuffs().useBuffsOnGeneric((Warrior) card);
                }
            }
        }

        for (Card card :
                getFirstPlayerInGameCards()) {
            if (card instanceof Warrior) {
                if (((Warrior) card).getSpecialPowerBuffs().getTargetSocietyKind().equals(TargetSocietyKind.SELF)) {
                    ((Warrior) card).getSpecialPowerBuffs().useBuffsOnGeneric((Warrior) card);
                }
            }
        }
        firstPlayerDeck.getHero().decreaseCoolDonw();
        secondPlayerDeck.getHero().decreaseCoolDonw();

        selectCard(null);
        turnBeiginingInit();
    }

    private void setHistoryAfterGame() {
        getWinner().incrementCountOfWins(1);
        int numberOfWinnerPlayer;
        if (getWinner().equals(getFirstPlayer())) {
            numberOfWinnerPlayer = 1;
        } else numberOfWinnerPlayer = 2;
        ArrayList<String> firstPlayerHistory = getFirstPlayer().getBattleHistory();
        firstPlayerHistory.add(getSecondPlayer().getUsername() + " - " + ((numberOfWinnerPlayer == 1) ? "Win" : "Lose"));
        ArrayList<String> secondPlayerHistory = getSecondPlayer().getBattleHistory();
        secondPlayerHistory.add(getFirstPlayer().getUsername() + " - " + ((numberOfWinnerPlayer == 1) ? "Win" : "Lose"));
    }

    private void setMana() {
        if (turn < 14) {
            setFirstPlayerCapacityMana(turn / 2 + 2 + 1000);//TODO Reval kkon
            setSecondPlayerCapacityMana(turn / 2 + 2 + 1000);
        } else if (turn >= 14) {
            setFirstPlayerCapacityMana(9);
            setSecondPlayerCapacityMana(9);
        }
        setFirstPlayerMana(getFirstPlayerCapacityMana());
        setSecondPlayerMana(getSecondPlayerCapacityMana());

    }

    private void incrementTurn() {
        setCurrentTurnPlayer();
        if (firstPlayerNextCard == null && getTurn() % 2 == 1)
            setFirstPlayerNextCard();
        if (secondPlayerNextCard == null && getTurn() % 2 == 0)
            setSecondPlayerNextCard();
        turn++;
    }

    public void deleteDeathCardsFromMap() {
        ArrayList<Card> firstDeathCards = findDeathCards(firstPlayerInGameCards);
        ArrayList<Card> secondDeathCards = findDeathCards(secondPlayerInGameCards);
        if (secondDeathCards.size() > 0)
            System.out.println(secondDeathCards.get(0).getCardName());
        deleteFromMap(firstDeathCards);
        deleteFromMap(secondDeathCards);
        addUsedCardsToGraveYard(firstDeathCards, secondDeathCards);

    }

    private void deleteFromMap(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).getCurrentCell().setCard(null);
            cards.get(i).setCurrentCell(null);
        }
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
        if (firstPlayerDeck.getHero().getHealthPoint() <= 0) {
            this.endGame = true;
            setWinner(secondPlayer);
        } else if (secondPlayerDeck.getHero().getHealthPoint() <= 0) {
            this.endGame = true;
            setWinner(firstPlayer);
        }
    }

    private void endOfHoldFlagGameMode() {
        if (flagForHoldFlagGameMode.getNumberOfTurns() == 6) {
            setWinner(flagForHoldFlagGameMode.getFlagHolder().getAccount());
            endGame = true;
        }
    }

    private void takeHoldingFlag(ArrayList<Card> inGameCards) {

        for (int i = 0; i < inGameCards.size(); i++) {
            if (inGameCards.get(i).getCurrentCell() == flagForHoldFlagGameMode.getCurrentCell()) {
                if (flagForHoldFlagGameMode.getFlagHolder() != inGameCards.get(i)) {
                    flagForHoldFlagGameMode.setFlagHolder((Warrior) inGameCards.get(i));
                    return;
                }
            }
        }
    }

    private void endOfCollectFlagGameMode() {
        if (firstPlayerFlags >= numberOfFlagForWin / 2) {
            setWinner(firstPlayer);
            endGame = true;
        } else if (secondPlayerFlags >= numberOfFlagForWin / 2) {
            setWinner(secondPlayer);
            endGame = true;
        }
    }

    private void takeCollectingFlag(ArrayList<Card> inGameCards) {
        setCurrentTurnPlayer();
        for (int i = 0; i < inGameCards.size(); i++) {
            for (int j = 0; j < flagForCollectFlagGameModes.size(); j++) {
                if (inGameCards.get(i).getCurrentCell() == flagForCollectFlagGameModes.get(j).getCurrentCell()) {
                    if (currentTurnPlayer.equals(firstPlayer)) {
                        firstPlayerFlags++;
                    } else {
                        secondPlayerFlags++;
                    }
                    flagForCollectFlagGameModes.get(j).setOwner(inGameCards.get(i));
                    flagForCollectFlagGameModes.get(j).getCurrentCell().setItem(null);
                    flagForCollectFlagGameModes.get(j).setCurrentCell(null);
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
                if (isValidInsert(cell1))
                    validCells.add(cell1);
            }
        }
    }

    private void findValidCellToItem() {

    }

    private void findValidCellToSpell() {

    }

    private boolean isValidInsert(Cell destinationCell) {
        Deck deck;
        if (turn % 2 == 1)
            deck = firstPlayerDeck;
        else
            deck = secondPlayerDeck;
        return (destinationCell != null) && destinationCell.isEmpty() && map.getDistanceOfTwoCell(destinationCell, deck.getHero().getCurrentCell()) <= 2;
    }

    private boolean isValidMove(Cell destinationCell) {
        if (destinationCell.getRow() == selectedCard.getCurrentCell().getRow()) {
            if (destinationCell.getColumn() < selectedCard.getCurrentCell().getColumn()) {
                return map.getCells()[destinationCell.getRow()][selectedCard.getCurrentCell().getColumn() - 1].isEmpty();
            } else {
                return map.getCells()[destinationCell.getRow()][selectedCard.getCurrentCell().getColumn() + 1].isEmpty();
            }

        } else if (destinationCell.getColumn() == selectedCard.getCurrentCell().getColumn()) {
            if (destinationCell.getRow() < selectedCard.getCurrentCell().getRow()) {
                return map.getCells()[selectedCard.getCurrentCell().getRow() - 1][destinationCell.getColumn()].isEmpty();
            } else {
                return map.getCells()[selectedCard.getCurrentCell().getRow() + 1][destinationCell.getColumn()].isEmpty();
            }
        }
        return false;
    }

    private boolean isValidMove(int x, int y) {
        Cell cell = map.getCell(x, y);
        return map.getDistanceOfTwoCell(selectedCard.getCurrentCell(), cell) <= 2 && cell.isEmpty();
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

        if (map.getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= 1) {
            return false;
        } else {
            return map.getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= warrior.getAttackRange();
        }
    }

    private boolean isValidMeleeAttack(Cell targetCell, Warrior warrior) {
        return map.getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= 1;
    }

    private boolean isValidSpeicalPower(int row, int column) {

        return true;
    }

    private void setHandOfFirstPlayer() {
        firstPlayerHand = selectRandomCardsForHand(firstPlayerDeck.getCards(), 5);
    }

    private void setHandOfSecondPlayer() {
        secondPlayerHand = selectRandomCardsForHand(secondPlayerDeck.getCards(), 5);

    }

    private void insertPlayerHeroesInMap() {

        firstPlayerDeck.getHero().setCurrentCell(map.getCell(2, 0));
        firstPlayerDeck.getHero().setAbleToMove(true);
        map.getCell(2, 0).setCard(firstPlayerDeck.getHero());
        firstPlayerDeck.getCards().remove(firstPlayerDeck.getHero());
        firstPlayerInGameCards.add(firstPlayerDeck.getHero());
        secondPlayerDeck.getHero().setCurrentCell(map.getCell(2, 8));
        secondPlayerDeck.getHero().setAbleToMove(true);
        map.getCell(2, 8).setCard(secondPlayerDeck.getHero());
        secondPlayerDeck.getCards().remove(secondPlayerDeck.getHero());
        secondPlayerInGameCards.add(secondPlayerDeck.getHero());
    }

    private ArrayList<Card> selectRandomCardsForHand(ArrayList<Card> cards, int totalRandomCardsNeeded) {
        Random random = new Random();
        ArrayList<Card> temp = new ArrayList<>();

        for (int i = 0; i < totalRandomCardsNeeded; i++) {
            int randomIndex = 1 + random.nextInt(5);
            temp.add(cards.get(randomIndex));
            cards.remove(randomIndex);
        }
        return temp;
    }

    private void setFirstPlayerNextCard() {
        Random random = new Random();
        firstPlayerNextCard = firstPlayerDeck.getCards().get(random.nextInt(firstPlayerDeck.getCards().size()));
    }

    public void setFirstPlayerCapacityMana(int firstPlayerCapacityMana) {
        this.firstPlayerCapacityMana = firstPlayerCapacityMana;
    }

    public void setSecondPlayerCapacityMana(int secondPlayerCapacityMana) {
        this.secondPlayerCapacityMana = secondPlayerCapacityMana;
    }

    public void increaseCapacityMana(Account account, int i) {
        if (account.equals(getFirstPlayer())) {
            incrementFirstPlayerCapacityMana(i);
        } else if (account.equals(getSecondPlayer())) {
            incrementSecondPlayerCapacityMana(i);
        }
    }

    public void incrementFirstPlayerCapacityMana(int i) {
        setFirstPlayerCapacityMana(getFirstPlayerCapacityMana() + i);
    }

    public void incrementSecondPlayerCapacityMana(int i) {
        setSecondPlayerCapacityMana(getSecondPlayerCapacityMana() + i);
    }

    public void setFirstPlayerMana(int firstPlayerMana) {
        this.firstPlayerMana = firstPlayerMana;
    }

    public void setSecondPlayerMana(int secondPlayerMana) {
        this.secondPlayerMana = secondPlayerMana;
    }

    private void setSecondPlayerNextCard() {
        Random random = new Random();
        secondPlayerNextCard = secondPlayerDeck.getCards().get(random.nextInt(secondPlayerDeck.getCards().size()));
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

    private void setFlagForCollectFlagGameModes(int n) {
        int[] randomX = new int[n];
        int[] randomY = new int[n];
        getNRandomNumber(randomX, randomY, 0, n / 2, 0);
        getNRandomNumber(randomX, randomY, n / 2, n, 5);
        for (int i = 0; i < randomX.length; i++) {
            flagForCollectFlagGameModes.add(new FlagForCollectFlagGameMode(getMap().getCell(randomX[i], randomY[i])));
            getMap().getCell(randomX[i], randomY[i]).setItem(flagForCollectFlagGameModes.get(i));
        }

    }

    private void getNRandomNumber(int[] randomX, int[] randomY, int first, int last, int extra) {

        Random random = new Random();
        int rx, ry;
        for (int i = first; i < last; i++) {
            rx = random.nextInt(5);
            ry = random.nextInt(4) + extra;
            while (hasPoint(randomX, randomY, rx, ry)) {
                rx = random.nextInt(5);
                ry = random.nextInt(4) + extra;
            }
            randomX[i] = rx;
            randomY[i] = ry;
        }
    }

    private boolean hasPoint(int[] arrayX, int[] arrayY, int rx, int ry) {
        for (int i = 0; i < arrayX.length; i++) {
            if (arrayX[i] == rx && arrayY[i] == ry)
                return true;
        }
        if ((rx == 2 && ry == 0) || (rx == 2 && ry == 8))
            return true;
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

    public Deck getFirstPlayerDeck() {
        return firstPlayerDeck;
    }

    public Deck getSecondPlayerDeck() {
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

    private void addUsedCardsToGraveYard(ArrayList<Card> firstDeathCards, ArrayList<Card> secondDeathCards) {

        for (int i = 0; i < firstPlayerDeck.getCards().size(); i++) {
            if (firstPlayerDeck.getCards().get(i) instanceof Spell && firstPlayerDeck.getCards().get(i).isInGame()) {
                firstPlayerDeck.getCards().add(firstPlayerDeck.getCards().get(i));
            }
        }
        firstPlayerGraveYard.addAll(firstDeathCards);
        firstPlayerInGameCards.removeAll(firstDeathCards);
        secondPlayerGraveYard.addAll(secondDeathCards);
        secondPlayerInGameCards.removeAll(secondDeathCards);
    }

    private ArrayList<Card> findDeathCards(ArrayList<Card> playerInGameCards) {

        ArrayList<Card> deathCards = new ArrayList<>();
        for (Card playerInGameCard : playerInGameCards) {
            if (playerInGameCard.isInGame() && (playerInGameCard instanceof Warrior)) {
                if (((Warrior) playerInGameCard).getHealthPoint() <= 0) {
                    deathCards.add(playerInGameCard);
                    playerInGameCard.setInGame(false);
                }
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

    public void increaseMana(Account account, int number) {
        if (account.getUsername().equals(firstPlayer.getUsername()))
            firstPlayerMana += number;
        else if (account.getUsername().equals(secondPlayer.getUsername()))
            secondPlayerMana += number;
    }

    public int getNumberOfFlagForWin() {
        return numberOfFlagForWin;
    }

    private void turnBeiginingInit() {

        for (Card firstPlayerInGameCard : firstPlayerInGameCards) {
            firstPlayerInGameCard.setAbleToMove(true);
        }
        for (Card secondPlayerInGameCard : secondPlayerInGameCards) {
            secondPlayerInGameCard.setAbleToMove(true);
        }
        for (Card firstPlayerInGameCard : firstPlayerInGameCards) {
            ((Warrior) firstPlayerInGameCard).setValidToAttack(true);
        }
        for (Card secondPlayerInGameCard : secondPlayerInGameCards) {
            ((Warrior) secondPlayerInGameCard).setValidToAttack(true);
        }

    }

}
