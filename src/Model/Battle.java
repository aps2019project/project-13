package Model;

import Model.BuffClasses.ABuff;
import Model.BuffClasses.HolyBuff;
import Model.BuffClasses.ManaBuff;
import Model.BuffClasses.PowerBuff;
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
    private int numberOfFlagForWin;
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
        setFirstPlayerNextCard();
        setSecondPlayerNextCard();
        if (gameGoal == GameGoal.HOLD_FLAG) {
            flagForHoldFlagGameMode = new FlagForHoldFlagGameMode("0", "Flag", ItemKind.FLAG, map.getCell(2, 4));
            map.getCell(2, 4).setItem(flagForHoldFlagGameMode);
        }
        if (firstPlayer.getMainDeck().getItem() != null && firstPlayer.getMainDeck().getItem().getItemName().equals("Simorq_Feather")) {
            ((UsableItem) firstPlayer.getMainDeck().getItem()).pareSimorgh();
        }
        if (secondPlayer.getMainDeck().getItem() != null && secondPlayer.getMainDeck().getItem().getItemName().equals("Simorq_Feather")) {
            ((UsableItem) secondPlayer.getMainDeck().getItem()).pareSimorgh();
        }
        if (firstPlayer.getMainDeck().getItem() != null && firstPlayer.getMainDeck().getItem().getItemName().equals("Namoos_Separ")) {
            ((UsableItem) firstPlayer.getMainDeck().getItem()).namooseSepar();
        }
        if (secondPlayer.getMainDeck().getItem() != null && secondPlayer.getMainDeck().getItem().getItemName().equals("Namoos_Separ")) {
            ((UsableItem) secondPlayer.getMainDeck().getItem()).namooseSepar();
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
            if (warrior.getCardKind() == CardKind.HERO) {
                if (warrior.getAccount().getMainDeck().getItem() != null && warrior.getAccount().getMainDeck().getItem().getItemName().equals("Shock_Hammer")) {
                    ((UsableItem) warrior.getAccount().getMainDeck().getItem()).shockHammer(defender);
                }
            }
            if (warrior.getCardKind() == CardKind.HERO) {
                if (warrior.getAccount().getMainDeck().getItem() != null && warrior.getAccount().getMainDeck().getItem().getItemName().equals("Bow_of_Damol")) {
                    ((UsableItem) warrior.getAccount().getMainDeck().getItem()).kamaneDamol(defender);
                }
            }
            if (defender.getAccount().getMainDeck().getItem() != null && defender.getAccount().getMainDeck().getItem().getItemName().equals("Poisonous_Dagger")) {
                ((UsableItem) defender.getAccount().getMainDeck().getItem()).poisonousDagger(warrior);
            }
            if (warrior.getAccount().getMainDeck().getItem() != null && warrior.getAccount().getMainDeck().getItem().getItemName().equals("Terror_Hood")) {
                ((UsableItem) warrior.getAccount().getMainDeck().getItem()).terrorHood();
            }
        }
        affectBuffs(warrior, targetCell, defender);
        if (defender.isValidCounterAttack() && isAttack) {
            attack(warrior.getCardId(), defender, false);
        }
        if (defender.getHealthPoint() <= 0 && defender.getSpecialPowerBuffs().getActivationCondition().equals(ActivationCondition.DEATH)) {
            defender.getSpecialPowerBuffs().useBuffsOnGeneric(warrior);
        }
        deathOfSiavash(defender);

        if (gameGoal == GameGoal.HOLD_FLAG)
            flagForHoldFlagGameMode.updateFlagHolder();

    }

    private void deathOfSiavash(Warrior defender) {
        if (defender.getCardName().equals("Siavash")) {
            Hero hero;
            if (defender.getAccount().equals(firstPlayer)) {
                hero = secondPlayerDeck.getHero();
            } else hero = firstPlayerDeck.getHero();
            hero.decreaseHealthPoint(6);
        }
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

    public void attackCombo(String oppnentId, ArrayList<String> warriorsCarIds) throws Error {
        ArrayList<Card> cards;
        if (turn % 2 == 1) {
            cards = firstPlayerInGameCards;
        } else cards = secondPlayerInGameCards;
        boolean validOpponent = false;
        for (Card card :
                cards) {
            if (card.getCardId().equals(oppnentId)) {
                validOpponent = true;
            }
        }
        if (!validOpponent) {
            throw new Error("invalid Opponent for Combo");
        }
        boolean validAttackers;
        for (int i = 0; i < warriorsCarIds.size(); i++) {
            validAttackers = false;
            for (Card card :
                    cards) {
                if (card.getCardId().equals(oppnentId)) {
                    validAttackers = true;
                }
            }
            if (!validAttackers) {
                throw new Error("invalid attacker for Combo");
            }
        }
        Card card = Card.findCardInArrayList(oppnentId, (turn % 2 == 1) ? firstPlayerInGameCards : secondPlayerInGameCards);
        for (String s :
                warriorsCarIds) {
            attack(s, (Warrior) card, true);
        }
    }

    public void useSpecialPower(Hero hero, int x, int y) {
        if (hero.getSpecialPowerBuffs() == null) {
            throw new Error(ConstantMessages.NO_SPECIAL_POWER.getMessage());
        }
        if (hero.getRemainginTurntoCoolDown() <= 0) {

            if (turn % 2 == 1) {
                if (firstPlayerMana < hero.getSpecialPower().getManaCost()) {
                    throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                }
            } else {
                if (secondPlayerMana < hero.getSpecialPower().getManaCost()) {
                    throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                }
            }
            Spell spell = new Spell("", "", 0, 0, "", TargetSocietyKind.FRIENDLY_CARDS, ActivationCondition.PASSIVE, hero.getSpecialPowerBuffs());
            applySpell(spell, x, y);

        } else {
            throw new Error(ConstantMessages.COOLDOWN.getMessage());
        }
    }

    private void applySpell(Spell spell, int x, int y) {
        AreaDispel(spell);
        Cell cell = map.getCell(x, y);
        Card card = null;
        if (cell.getCard() != null) {
            card = cell.getCard();
        }
        for (ABuff aBuff :
                spell.getBuffs()) {
            aBuff.affect(map.getCell(x, y));
            aBuff.affect(card);
        }
    }

    private void AreaDispel(Spell spell) {
        setCurrentTurnPlayer();
        Random random = new Random();
        if (spell.getCardName().equals("Dispel")) {
            dispel();
        } else if (spell.getCardName().equals("Area Dispel")) {
            AreaDispel(random);
        }
    }

    private void dispel() {
        ArrayList<Card> cards = (currentTurnPlayer.equals(firstPlayer)) ? firstPlayerInGameCards : secondPlayerInGameCards;
        if (cards == null)
            return;
        ArrayList<Card> opponents = (currentTurnPlayer.equals(firstPlayer)) ? secondPlayerInGameCards : firstPlayerInGameCards;
        if (opponents == null)
            return;
        for (Card card :
                cards) {
            myDispel(card);
        }
        for (Card card :
                opponents) {
            opponentDispel(card);
        }
    }

    private void AreaDispel(Random random) {
        ArrayList<Card> cards = (currentTurnPlayer.equals(firstPlayer)) ? firstPlayerInGameCards : secondPlayerInGameCards;
        if (cards == null)
            return;
        ArrayList<Card> opponents = (currentTurnPlayer.equals(firstPlayer)) ? secondPlayerInGameCards : firstPlayerInGameCards;
        if (opponents == null)
            return;
        int randomNumber = random.nextInt(cards.size());
        Card card = cards.get(randomNumber);
        randomNumber = random.nextInt(cards.size());
        Card cardOpponent = opponents.get(randomNumber);
        CancelBuff(card, cardOpponent);
    }

    private void CancelBuff(Card card, Card cardOpponent) {
        myDispel(card);
        opponentDispel(cardOpponent);
    }

    private void opponentDispel(Card cardOpponent) {
        for (int i = 0; i < cardOpponent.getBuffs().size(); i++) {
            ABuff aBuff = cardOpponent.getBuffs().get(i);
            if ((aBuff instanceof PowerBuff) || (aBuff instanceof HolyBuff)) {
                cardOpponent.getBuffs().remove(aBuff);
            }
        }
    }

    private void myDispel(Card card) {
        for (int i = 0; i < card.getBuffs().size(); i++) {
            ABuff aBuff = card.getBuffs().get(i);
            if (!(aBuff instanceof PowerBuff) && !(aBuff instanceof HolyBuff)) {
                card.getBuffs().remove(aBuff);
            }
        }
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
        if (currentTurnPlayer.getMainDeck().getItem() != null && currentTurnPlayer.getMainDeck().getItem().getItemName().equals("Baptism")) {
            ((UsableItem) currentTurnPlayer.getMainDeck().getItem()).ghosleTamid();
        }
        if (currentTurnPlayer.getMainDeck().getItem() != null && currentTurnPlayer.getMainDeck().getItem().getItemName().equals("Assassination_Dagger")) {
            ((UsableItem) currentTurnPlayer.getMainDeck().getItem()).assassinationDagger();
        }
    }

    public void endTurn() throws Error {
        setCurrentTurnPlayer();
        endGame();
        if (isEndGame()) {
            setHistoryAfterGame();
            return;
        }
        setMana();
        if (currentTurnPlayer.getMainDeck().getItem() != null && currentTurnPlayer.getMainDeck().getItem().getItemName().equals("Wisdom_Crown")) {
            ((UsableItem) currentTurnPlayer.getMainDeck().getItem()).tajeDanaii();
        }

        incrementTurn();
        if (firstPlayerHand.size() < 5 && getTurn() % 2 == 1) {
            firstPlayerHand.add(firstPlayerNextCard);
            setFirstPlayerNextCard();
        }
        if (secondPlayerHand.size() < 5 && getTurn() % 2 == 0) {
            secondPlayerHand.add(secondPlayerNextCard);
            setSecondPlayerNextCard();
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
                    ((Warrior) card).getSpecialPowerBuffs().useBuffsOnGeneric(card);
                }
            }
        }

        for (Card card :
                getFirstPlayerInGameCards()) {
            if (card instanceof Warrior) {
                if (((Warrior) card).getSpecialPowerBuffs().getTargetSocietyKind().equals(TargetSocietyKind.SELF)) {
                    ((Warrior) card).getSpecialPowerBuffs().useBuffsOnGeneric(card);
                }
            }
        }
        firstPlayerDeck.getHero().decreaseCoolDonw();
        secondPlayerDeck.getHero().decreaseCoolDonw();

        if (currentTurnPlayer.getMainDeck().getItem() != null && currentTurnPlayer.getMainDeck().getItem().getItemName().equals("King_Wisdom")) {
            ((UsableItem) currentTurnPlayer.getMainDeck().getItem()).kingWisdom();
        }

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
            setFirstPlayerCapacityMana(turn / 2 + 2);
            setSecondPlayerCapacityMana(turn / 2 + 2);
        } else {
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
        for (Card card : cards) {
            card.getCurrentCell().setCard(null);
            card.setCurrentCell(null);
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

        for (Card inGameCard : inGameCards) {
            if (inGameCard.getCurrentCell() == flagForHoldFlagGameMode.getCurrentCell()) {
                if (flagForHoldFlagGameMode.getFlagHolder() != inGameCard) {
                    flagForHoldFlagGameMode.setFlagHolder((Warrior) inGameCard);
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
        for (Card inGameCard : inGameCards) {
            for (int j = 0; j < flagForCollectFlagGameModes.size(); j++) {
                if (inGameCard.getCurrentCell() == flagForCollectFlagGameModes.get(j).getCurrentCell()) {
                    if (currentTurnPlayer.equals(firstPlayer)) {
                        firstPlayerFlags++;
                    } else {
                        secondPlayerFlags++;
                    }
                    flagForCollectFlagGameModes.get(j).setOwner(inGameCard);
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

    void findValidCell(KindOfActionForValidCells kindOfActionForValidCells) {

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

    private void setHandOfFirstPlayer() {
        firstPlayerHand = selectRandomCardsForHand(firstPlayerDeck.getCards());
    }

    private void setHandOfSecondPlayer() {
        secondPlayerHand = selectRandomCardsForHand(secondPlayerDeck.getCards());

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

    private ArrayList<Card> selectRandomCardsForHand(ArrayList<Card> cards) {
        Random random = new Random();
        ArrayList<Card> temp = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
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

    private void setFirstPlayerCapacityMana(int firstPlayerCapacityMana) {
        this.firstPlayerCapacityMana = firstPlayerCapacityMana;
    }

    private void setSecondPlayerCapacityMana(int secondPlayerCapacityMana) {
        this.secondPlayerCapacityMana = secondPlayerCapacityMana;
    }

    public void increaseCapacityMana(Account account, int i) {
        if (account.equals(getFirstPlayer())) {
            incrementFirstPlayerCapacityMana(i);
        } else if (account.equals(getSecondPlayer())) {
            incrementSecondPlayerCapacityMana(i);
        }
    }

    private void incrementFirstPlayerCapacityMana(int i) {
        setFirstPlayerCapacityMana(getFirstPlayerCapacityMana() + i);
    }

    private void incrementSecondPlayerCapacityMana(int i) {
        setSecondPlayerCapacityMana(getSecondPlayerCapacityMana() + i);
    }

    private void setFirstPlayerMana(int firstPlayerMana) {
        this.firstPlayerMana = firstPlayerMana;
    }

    private void setSecondPlayerMana(int secondPlayerMana) {
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
        return (rx == 2 && ry == 0) || (rx == 2 && ry == 8);
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

    private int getFirstPlayerCapacityMana() {
        return firstPlayerCapacityMana;
    }

    private int getSecondPlayerCapacityMana() {
        return secondPlayerCapacityMana;
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

    ArrayList<Cell> getValidCells() {
        return validCells;
    }

    public boolean isEndGame() {
        return endGame;
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
                    if (playerInGameCard.getAccount().getMainDeck().getItem() != null && playerInGameCard.getAccount().getMainDeck().getItem().getItemName().equals("Soul_Eater")) {
                        ((UsableItem) playerInGameCard.getAccount().getMainDeck().getItem()).soulEater();
                    }
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

    public void increaseMana(Account account, int number) {
        if (account.getUsername().equals(firstPlayer.getUsername()))
            firstPlayerMana += number;
        else if (account.getUsername().equals(secondPlayer.getUsername()))
            secondPlayerMana += number;
    }

    private void turnBeiginingInit() {

        for (Card firstPlayerInGameCard : firstPlayerInGameCards) {
            firstPlayerInGameCard.setAbleToMove(true);
            ((Warrior) firstPlayerInGameCard).setValidToAttack(true);
            ((Warrior) firstPlayerInGameCard).setValidCounterAttack(true);
        }
        for (Card secondPlayerInGameCard : secondPlayerInGameCards) {
            secondPlayerInGameCard.setAbleToMove(true);
            ((Warrior) secondPlayerInGameCard).setValidToAttack(true);
            ((Warrior) secondPlayerInGameCard).setValidCounterAttack(true);
        }

    }

    public Deck getFirstPlayerDeck() {
        return firstPlayerDeck;
    }

    public Deck getSecondPlayerDeck() {
        return secondPlayerDeck;
    }
}
