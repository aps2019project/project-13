package Model;

import Model.BuffClasses.ABuff;
import Model.BuffClasses.HolyBuff;
import Model.BuffClasses.ManaBuff;
import Model.BuffClasses.PowerBuff;
import View.ConstantMessages;
import View.Error;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

public class Battle {

    private static Battle runningBattle = null;
    private Map map;
    private Account winner;
    private int turn = 1;
    private int numberOfFlagForWin;
    private Account currentTurnPlayer;
    private Card selectedCard;
    private ArrayList<Cell> validCells = new ArrayList<>();

    private GameMode gameMode;
    private GameGoal gameGoal;
    private boolean endGame;
    private FlagForHoldFlagGameMode flagForHoldFlagGameMode;
    private ArrayList<FlagForCollectFlagGameMode> flagForCollectFlagGameModes = new ArrayList<>();


    private Player player1 = new Player();
    private Player player2 = new Player();


    public Battle(Account firstPlayer, Account secondPlayer, GameMode gameMode, GameGoal gameGoal) throws Error {
        this.setFirstPlayer(firstPlayer);
        this.setSecondPlayer(secondPlayer);
        this.setGameMode(gameMode);
        this.setGameGoal(gameGoal);
        if (secondPlayer instanceof Ai) {
            ((Ai) secondPlayer).setBattle(this);
        }
        setMap(new Map(this));
        setRunningBattle(this);
        checkDeckAtFirst(firstPlayer, secondPlayer);
        insertPlayerHeroesInMap();
        setHandOfFirstPlayer();
        setHandOfSecondPlayer();
        setFirstPlayerNextCard();
        setSecondPlayerNextCard();
        if (gameGoal == GameGoal.HOLD_FLAG) {
            setFlagForHoldFlagGameMode(new FlagForHoldFlagGameMode("0", "Flag", ItemKind.FLAG, getMap().getCell(2, 4)));
            getMap().getCell(2, 4).setItem(getFlagForHoldFlagGameMode());
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
        this.setNumberOfFlagForWin(numberOfFlagForWin);
        setFlagForCollectFlagGameModes(numberOfFlagForWin);
    }

    private void checkDeckAtFirst(Account firstPlayer, Account secondPlayer) {
        if (firstPlayer.getMainDeck() != null && Deck.validateDeck(firstPlayer.getMainDeck())) {
            setFirstPlayerDeck(Deck.deepClone(firstPlayer.getMainDeck()));
        } else {
            throw new Error(ConstantMessages.INVALID_DECK.getMessage());
        }
        if (secondPlayer.getMainDeck() != null && Deck.validateDeck(secondPlayer.getMainDeck())) {
            setSecondPlayerDeck(Deck.deepClone(secondPlayer.getMainDeck()));
        } else {
            throw new Error(ConstantMessages.INVALID_DECK_SECOND_USER.getMessage());
        }
    }


    public void moveCard(int x, int y) {
        Cell cell = getMap().getCell(x, y);

        if (!isValidMove(x, y)) {
            throw new Error(ConstantMessages.INVALID_CELL_TO_MOVE.getMessage());
        }
        if (getSelectedCard().isAbleToMove()) {
            getMap().moveCard(getSelectedCard(), cell);
            getSelectedCard().setAbleToMove(false);
        } else
            throw new Error(ConstantMessages.CARD_MOVED_BEFORE.getMessage());
        //Take Flags for win the game
        if (getGameGoal() == GameGoal.HOLD_FLAG) {
            getFlagForHoldFlagGameMode().updateFlagCell();
            takeHoldingFlag(getFirstPlayerInGameCards());
            takeHoldingFlag(getSecondPlayerInGameCards());
        } else if (getGameGoal() == GameGoal.COLLECT_FLAG) {
            takeCollectingFlag(getFirstPlayerInGameCards());
            takeCollectingFlag(getSecondPlayerInGameCards());
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

        if (getGameGoal() == GameGoal.HOLD_FLAG)
            getFlagForHoldFlagGameMode().updateFlagHolder();

    }

    private void deathOfSiavash(Warrior defender) {
        if (defender.getCardName().equals("Siavash")) {
            Hero hero;
            if (defender.getAccount().equals(getFirstPlayer())) {
                hero = getSecondPlayerDeck().getHero();
            } else hero = getFirstPlayerDeck().getHero();
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
                if (getMap().getCells()[i][j].getCard() != null && getMap().getCells()[i][j].getCard().getCardId().equals(cardID)) {
                    targetCell = getMap().getCells()[i][j];
                    break;
                }
            }
        }
        return targetCell;
    }

    public void attackCombo(String oppnentId, ArrayList<String> warriorsCarIds) throws Error {
        ArrayList<Card> cards;
        if (getTurn() % 2 == 1) {
            cards = getFirstPlayerInGameCards();
        } else cards = getSecondPlayerInGameCards();
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
        Card card = Card.findCardInArrayList(oppnentId, (getTurn() % 2 == 1) ? getFirstPlayerInGameCards() : getSecondPlayerInGameCards());
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

            if (getTurn() % 2 == 1) {
                if (getFirstPlayerMana() < hero.getSpecialPower().getManaCost()) {
                    throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                }
            } else {
                if (getSecondPlayerMana() < hero.getSpecialPower().getManaCost()) {
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
        Cell cell = getMap().getCell(x, y);
        Card card = null;
        if (cell.getCard() != null) {
            card = cell.getCard();
        }
        for (ABuff aBuff :
                spell.getBuffs()) {
            aBuff.affect(getMap().getCell(x, y));
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
        ArrayList<Card> cards = (getCurrentTurnPlayer().equals(getFirstPlayer())) ? getFirstPlayerInGameCards() : getSecondPlayerInGameCards();
        if (cards == null)
            return;
        ArrayList<Card> opponents = (getCurrentTurnPlayer().equals(getFirstPlayer())) ? getSecondPlayerInGameCards() : getFirstPlayerInGameCards();
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
        ArrayList<Card> cards = (getCurrentTurnPlayer().equals(getFirstPlayer())) ? getFirstPlayerInGameCards() : getSecondPlayerInGameCards();
        if (cards == null)
            return;
        ArrayList<Card> opponents = (getCurrentTurnPlayer().equals(getFirstPlayer())) ? getSecondPlayerInGameCards() : getFirstPlayerInGameCards();
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
        if (getTurn() % 2 == 1) {
            card = Card.findCardInArrayListByName(cardName, getFirstPlayerHand());
        } else
            card = Card.findCardInArrayListByName(cardName, getSecondPlayerHand());

        if (card != null) {
            if (card instanceof Spell) {
                applySpell((Spell) card, x, y);
                return;
            }
            Cell cell = getMap().getCell(x, y);
            if (isValidInsert(cell)) {
                if (getTurn() % 2 == 1) {
                    if (getFirstPlayerMana() >= card.getManaCost()) {
                        cell.setCard(card);
                        card.setCurrentCell(cell);
                        getFirstPlayerHand().remove(card);
                        addToFirstPlayerInGameCards(card);
                        card.setInGame(true);
                        card.setAbleToMove(true);
                    } else
                        throw new Error(ConstantMessages.NOT_ENOUGH_MANA.getMessage());
                } else {
                    if (getSecondPlayerMana() >= card.getManaCost()) {
                        cell.setCard(card);
                        card.setCurrentCell(cell);
                        getSecondPlayerHand().remove(card);
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
        if (getCurrentTurnPlayer().getMainDeck().getItem() != null && getCurrentTurnPlayer().getMainDeck().getItem().getItemName().equals("Baptism")) {
            ((UsableItem) getCurrentTurnPlayer().getMainDeck().getItem()).ghosleTamid();
        }
        if (getCurrentTurnPlayer().getMainDeck().getItem() != null && getCurrentTurnPlayer().getMainDeck().getItem().getItemName().equals("Assassination_Dagger")) {
            ((UsableItem) getCurrentTurnPlayer().getMainDeck().getItem()).assassinationDagger();
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
        if (getCurrentTurnPlayer().getMainDeck().getItem() != null && getCurrentTurnPlayer().getMainDeck().getItem().getItemName().equals("Wisdom_Crown")) {
            ((UsableItem) getCurrentTurnPlayer().getMainDeck().getItem()).tajeDanaii();
        }

        incrementTurn();
        if (getFirstPlayerHand().size() < 5 && getTurn() % 2 == 1) {
            getFirstPlayerHand().add(getFirstPlayerNextCard());
            setFirstPlayerNextCard();
        }
        if (getSecondPlayerHand().size() < 5 && getTurn() % 2 == 0) {
            getSecondPlayerHand().add(getSecondPlayerNextCard());
            setSecondPlayerNextCard();
        }
        if (getTurn() % 2 == 0) {
            if (getSecondPlayer() instanceof Ai) {
                try {
                    ((Ai) getSecondPlayer()).playGame();
                } catch (Error error) {
                    endTurn();
                }
            }
        }
        if (getGameGoal() == GameGoal.HOLD_FLAG)
            getFlagForHoldFlagGameMode().incrementNumberOfTurns();
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
        getFirstPlayerDeck().getHero().decreaseCoolDonw();
        getSecondPlayerDeck().getHero().decreaseCoolDonw();

        if (getCurrentTurnPlayer().getMainDeck().getItem() != null && getCurrentTurnPlayer().getMainDeck().getItem().getItemName().equals("King_Wisdom")) {
            ((UsableItem) getCurrentTurnPlayer().getMainDeck().getItem()).kingWisdom();
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
        if (getTurn() < 14) {
            setFirstPlayerCapacityMana(getTurn() / 2 + 2);
            setSecondPlayerCapacityMana(getTurn() / 2 + 2);
        } else {
            setFirstPlayerCapacityMana(9);
            setSecondPlayerCapacityMana(9);
        }
        setFirstPlayerMana(getFirstPlayerCapacityMana());
        setSecondPlayerMana(getSecondPlayerCapacityMana());

    }

    private void incrementTurn() {
        setCurrentTurnPlayer();
        if (getFirstPlayerNextCard() == null && getTurn() % 2 == 1)
            setFirstPlayerNextCard();
        if (getSecondPlayerNextCard() == null && getTurn() % 2 == 0)
            setSecondPlayerNextCard();
        setTurn(getTurn() + 1);
    }

    public void deleteDeathCardsFromMap() {
        ArrayList<Card> firstDeathCards = findDeathCards(getFirstPlayerInGameCards());
        ArrayList<Card> secondDeathCards = findDeathCards(getSecondPlayerInGameCards());
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

        switch (getGameGoal()) {
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
        if (getFirstPlayerDeck().getHero().getHealthPoint() <= 0) {
            this.setEndGame(true);
            setWinner(getSecondPlayer());
        } else if (getSecondPlayerDeck().getHero().getHealthPoint() <= 0) {
            this.setEndGame(true);
            setWinner(getFirstPlayer());
        }
    }

    private void endOfHoldFlagGameMode() {
        if (getFlagForHoldFlagGameMode().getNumberOfTurns() == 6) {
            setWinner(getFlagForHoldFlagGameMode().getFlagHolder().getAccount());
            setEndGame(true);
        }
    }

    private void takeHoldingFlag(ArrayList<Card> inGameCards) {

        for (Card inGameCard : inGameCards) {
            if (inGameCard.getCurrentCell() == getFlagForHoldFlagGameMode().getCurrentCell()) {
                if (getFlagForHoldFlagGameMode().getFlagHolder() != inGameCard) {
                    getFlagForHoldFlagGameMode().setFlagHolder((Warrior) inGameCard);
                    return;
                }
            }
        }
    }

    private void endOfCollectFlagGameMode() {
        if (getFirstPlayerFlags() >= getNumberOfFlagForWin() / 2) {
            setWinner(getFirstPlayer());
            setEndGame(true);
        } else if (getSecondPlayerFlags() >= getNumberOfFlagForWin() / 2) {
            setWinner(getSecondPlayer());
            setEndGame(true);
        }
    }

    private void takeCollectingFlag(ArrayList<Card> inGameCards) {
        setCurrentTurnPlayer();
        for (Card inGameCard : inGameCards) {
            for (int j = 0; j < getFlagForCollectFlagGameModes().size(); j++) {
                if (inGameCard.getCurrentCell() == getFlagForCollectFlagGameModes().get(j).getCurrentCell()) {
                    if (getCurrentTurnPlayer().equals(getFirstPlayer())) {
                        setFirstPlayerFlags(getFirstPlayerFlags() + 1);
                    } else {
                        setSecondPlayerFlags(getSecondPlayerFlags() + 1);
                    }
                    getFlagForCollectFlagGameModes().get(j).setOwner(inGameCard);
                    getFlagForCollectFlagGameModes().get(j).getCurrentCell().setItem(null);
                    getFlagForCollectFlagGameModes().get(j).setCurrentCell(null);
                    getFlagForCollectFlagGameModes().remove(j);
                    break;
                }
            }
        }
    }


    private void clearValidCellsList() {
        getValidCells().clear();
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
        Cell[][] cells = getMap().getCells();
        if (!getSelectedCard().isAbleToMove())
            return;
        Warrior warrior = (Warrior) getSelectedCard();
        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                if (cell1.isEmpty() && getMap().getDistanceOfTwoCell(warrior.getCurrentCell(), cell1) <= 2 && isValidMove(cell1))
                    getValidCells().add(cell1);
            }
        }
    }

    private void findValidCellToAttack() {

        Cell[][] cells = getMap().getCells();
        Warrior warrior = (Warrior) getSelectedCard();
        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                if (!cell1.isEmpty() && isValidAttack(cell1, warrior))
                    getValidCells().add(cell1);
            }
        }
    }

    private void findValidCellToInsert() {

        Cell[][] cells = getMap().getCells();

        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                if (isValidInsert(cell1))
                    getValidCells().add(cell1);
            }
        }
    }

    private void findValidCellToItem() {

    }

    private void findValidCellToSpell() {

    }

    private boolean isValidInsert(Cell destinationCell) {
        Deck deck;
        if (getTurn() % 2 == 1)
            deck = getFirstPlayerDeck();
        else
            deck = getSecondPlayerDeck();
        return (destinationCell != null) && destinationCell.isEmpty() && getMap().getDistanceOfTwoCell(destinationCell, deck.getHero().getCurrentCell()) <= 2;
    }

    private boolean isValidMove(Cell destinationCell) {
        if (destinationCell.getRow() == getSelectedCard().getCurrentCell().getRow()) {
            if (destinationCell.getColumn() < getSelectedCard().getCurrentCell().getColumn()) {
                return getMap().getCells()[destinationCell.getRow()][getSelectedCard().getCurrentCell().getColumn() - 1].isEmpty();
            } else {
                return getMap().getCells()[destinationCell.getRow()][getSelectedCard().getCurrentCell().getColumn() + 1].isEmpty();
            }

        } else if (destinationCell.getColumn() == getSelectedCard().getCurrentCell().getColumn()) {
            if (destinationCell.getRow() < getSelectedCard().getCurrentCell().getRow()) {
                return getMap().getCells()[getSelectedCard().getCurrentCell().getRow() - 1][destinationCell.getColumn()].isEmpty();
            } else {
                return getMap().getCells()[getSelectedCard().getCurrentCell().getRow() + 1][destinationCell.getColumn()].isEmpty();
            }
        }
        return false;
    }

    private boolean isValidMove(int x, int y) {
        Cell cell = getMap().getCell(x, y);
        return getMap().getDistanceOfTwoCell(getSelectedCard().getCurrentCell(), cell) <= 2 && cell.isEmpty();
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

        if (getMap().getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= 1) {
            return false;
        } else {
            return getMap().getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= warrior.getAttackRange();
        }
    }

    private boolean isValidMeleeAttack(Cell targetCell, Warrior warrior) {
        return getMap().getDistanceOfTwoCell(targetCell, warrior.getCurrentCell()) <= 1;
    }


    private void insertPlayerHeroesInMap() {

        getFirstPlayerDeck().getHero().setCurrentCell(getMap().getCell(2, 0));
        getFirstPlayerDeck().getHero().setAbleToMove(true);
        getMap().getCell(2, 0).setCard(getFirstPlayerDeck().getHero());
        getFirstPlayerDeck().getCards().remove(getFirstPlayerDeck().getHero());
        getFirstPlayerInGameCards().add(getFirstPlayerDeck().getHero());
        getSecondPlayerDeck().getHero().setCurrentCell(getMap().getCell(2, 8));
        getSecondPlayerDeck().getHero().setAbleToMove(true);
        getMap().getCell(2, 8).setCard(getSecondPlayerDeck().getHero());
        getSecondPlayerDeck().getCards().remove(getSecondPlayerDeck().getHero());
        getSecondPlayerInGameCards().add(getSecondPlayerDeck().getHero());
    }


    private boolean hasPoint(int[] arrayX, int[] arrayY, int rx, int ry) {
        for (int i = 0; i < arrayX.length; i++) {
            if (arrayX[i] == rx && arrayY[i] == ry)
                return true;
        }
        return (rx == 2 && ry == 0) || (rx == 2 && ry == 8);
    }


    private void addUsedCardsToGraveYard(ArrayList<Card> firstDeathCards, ArrayList<Card> secondDeathCards) {

        for (int i = 0; i < getFirstPlayerDeck().getCards().size(); i++) {
            if (getFirstPlayerDeck().getCards().get(i) instanceof Spell && getFirstPlayerDeck().getCards().get(i).isInGame()) {
                getFirstPlayerDeck().getCards().add(getFirstPlayerDeck().getCards().get(i));
            }
        }
        getFirstPlayerGraveYard().addAll(firstDeathCards);
        getFirstPlayerInGameCards().removeAll(firstDeathCards);
        getSecondPlayerGraveYard().addAll(secondDeathCards);
        getSecondPlayerInGameCards().removeAll(secondDeathCards);
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
        //TODO CHECK NULL POINTERS.
        String s = String.format("%-10%-20s%-20s%-20s%-20s\n","No.","Player","Hero Name" , "Health Points of Hero", "Mana");
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        s = String.format("%-10%-20s%-20s%-20s%-20s\n" , "1.",getFirstPlayer().getUsername(),getFirstPlayer().getMainDeck().getHero().getCardName(),
                getFirstPlayer().getMainDeck().getHero().getHealthPoint(),(getFirstPlayerMana()));
        sb.append(s);
        s = String.format("%-10%-20s%-20s%-20s%-20s\n" , "1.",getSecondPlayer().getUsername(),getSecondPlayer().getMainDeck().getHero().getCardName(),
                getSecondPlayer().getMainDeck().getHero().getHealthPoint(),(getSecondPlayerMana()));
        sb.append(s);
                sb.append("Game Goal: ").append(getGameGoal() == GameGoal.KILL_HERO ? "Kill Hero" : getGameGoal() == GameGoal.HOLD_FLAG ? "Hold Flag" : "Collect Flag");
        if (getGameGoal() == GameGoal.HOLD_FLAG) {
            sb.append("Flag Coordinates : Row: " + getFlagForHoldFlagGameMode().getCurrentCell().getRow() + " Column: " + getFlagForHoldFlagGameMode().getCurrentCell().getColumn()
                    + " Flag Owner: " + getFlagForHoldFlagGameMode().getFlagHolder().getAccount());
        } else if (getGameGoal() == GameGoal.COLLECT_FLAG) {
            StringBuilder temp = new StringBuilder();
            for (FlagForCollectFlagGameMode flagForCollectFlagGameMode : getFlagForCollectFlagGameModes()) {
                temp.append("CardName: ").append(flagForCollectFlagGameMode.getOwner().getCardName()).append(" ").append("Player: ").append(flagForCollectFlagGameMode.getOwner().getAccount().getUsername()).append("\n");
            }
            sb.append(temp);
        }
        return sb.toString();
//        if (getGameGoal() == GameGoal.KILL_HERO)
//            return "Hero HealthPoints     FirstPlayer: " + getFirstPlayer().getMainDeck().getHero().getHealthPoint()
//                    + " SecondPlayerL " + getSecondPlayer().getMainDeck().getHero().getHealthPoint();
//        else if (getGameGoal() == GameGoal.HOLD_FLAG) {
//            return "Flag Coordinates : Row: " + getFlagForHoldFlagGameMode().getCurrentCell().getRow()
//                    + " Column: " + getFlagForHoldFlagGameMode().getCurrentCell().getColumn()
//                    + " Flag Owner: " + getFlagForHoldFlagGameMode().getFlagHolder().getAccount();
//        } else {
//            StringBuilder output = new StringBuilder();
//            for (FlagForCollectFlagGameMode flagForCollectFlagGameMode : getFlagForCollectFlagGameModes()) {
//                output.append("CardName: ").append(flagForCollectFlagGameMode.getOwner().getCardName()).append(" ").append("Player: ").append(flagForCollectFlagGameMode.getOwner().getAccount().getUsername()).append("\n");
//            }
//            return output.toString();
//        }
    }

    private void addToFirstPlayerInGameCards(Card card) {
        getFirstPlayerInGameCards().add(card);
    }

    private void addToSecondPlayerInGameCards(Card card) {
        getSecondPlayerInGameCards().add(card);
    }

    public void increaseMana(Account account, int number) {
        if (account.getUsername().equals(getFirstPlayer().getUsername()))
            setFirstPlayerMana(getFirstPlayerMana() + number);
        else if (account.getUsername().equals(getSecondPlayer().getUsername()))
            setSecondPlayerMana(getSecondPlayerMana() + number);
    }

    private void turnBeiginingInit() {

        for (Card firstPlayerInGameCard : getFirstPlayerInGameCards()) {
            firstPlayerInGameCard.setAbleToMove(true);
            ((Warrior) firstPlayerInGameCard).setValidToAttack(true);
            ((Warrior) firstPlayerInGameCard).setValidCounterAttack(true);
        }
        for (Card secondPlayerInGameCard : getSecondPlayerInGameCards()) {
            secondPlayerInGameCard.setAbleToMove(true);
            ((Warrior) secondPlayerInGameCard).setValidToAttack(true);
            ((Warrior) secondPlayerInGameCard).setValidCounterAttack(true);
        }

    }

    public static Battle getRunningBattle() {
        return runningBattle;
    }

    public static void setRunningBattle(Battle runningBattle) {
        Battle.runningBattle = runningBattle;
    }

    public void setCurrentTurnPlayer() {
        if (getTurn() % 2 == 1) {
            setCurrentTurnPlayer(getFirstPlayer());
        } else {
            setCurrentTurnPlayer(getSecondPlayer());
        }
    }

    public Account getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public ArrayList<Card> getSecondPlayerGraveYard() {//
        return player2.getGraveyard();
    }

    public ArrayList<Card> getFirstPlayerInGameCards() {//
        return player1.getInGameCards();
    }

    public ArrayList<Card> getSecondPlayerInGameCards() {//
        return player2.getInGameCards();
    }

    public Deck getFirstPlayerDeck() {//
        return player1.getDeck();
    }

    public Deck getSecondPlayerDeck() {//
        return player2.getDeck();
    }

    public void setMap(Map map) {//
        this.map = map;
    }

    public void setFirstPlayer(Account firstPlayer) {//
        player1.setAccount(firstPlayer);
    }

    public void setSecondPlayer(Account secondPlayer) {//
        player2.setAccount(secondPlayer);
    }

    public void setTurn(int turn) {//
        this.turn = turn;
    }

    public int getNumberOfFlagForWin() {//
        return numberOfFlagForWin;
    }

    public void setNumberOfFlagForWin(int numberOfFlagForWin) {//
        this.numberOfFlagForWin = numberOfFlagForWin;
    }

    public void setCurrentTurnPlayer(Account currentTurnPlayer) {//
        this.currentTurnPlayer = currentTurnPlayer;
    }

    public int getFirstPlayerMana() {//
        return player1.getCurrentMana();
    }

    public int getSecondPlayerMana() {//
        return player2.getCurrentMana();
    }

    public void setSelectedCard(Card selectedCard) {//
        this.selectedCard = selectedCard;
    }

    public void setFirstPlayerGraveYard(ArrayList<Card> firstPlayerGraveYard) {//
        player1.setGraveyard(firstPlayerGraveYard);
    }

    public void setSecondPlayerGraveYard(ArrayList<Card> secondPlayerGraveYard) {//
        player2.setGraveyard(secondPlayerGraveYard);
    }

    public void setFirstPlayerHand(ArrayList<Card> firstPlayerHand) {//
        player1.setHand(firstPlayerHand);
    }

    public void setSecondPlayerHand(ArrayList<Card> secondPlayerHand) {//
        player2.setHand(secondPlayerHand);
    }

    public void setFirstPlayerNextCard(Card firstPlayerNextCard) {//
        player1.setNextCard(firstPlayerNextCard);
    }

    public void setSecondPlayerNextCard(Card secondPlayerNextCard) {//
        player2.setNextCard(secondPlayerNextCard);
    }

    public void setValidCells(ArrayList<Cell> validCells) {//
        this.validCells = validCells;
    }

    public void setFirstPlayerDeck(Deck firstPlayerDeck) {//
        player1.setDeck(firstPlayerDeck);
    }

    public void setSecondPlayerDeck(Deck secondPlayerDeck) {//
        player2.setDeck(secondPlayerDeck);
    }

    public void setFirstPlayerInGameCards(ArrayList<Card> firstPlayerInGameCards) {//
        player1.setInGameCards(firstPlayerInGameCards);
    }

    public void setSecondPlayerInGameCards(ArrayList<Card> secondPlayerInGameCards) {//
        player2.setInGameCards(secondPlayerInGameCards);
    }

    public ArrayList<Item> getFirstPlayerItems() {//
        return player1.getItems();
    }

    public void setFirstPlayerItems(ArrayList<Item> firstPlayerItems) {//
        player1.setItems(firstPlayerItems);
    }

    public ArrayList<Item> getSecondPlayerItems() {//
        return player2.getItems();
    }

    public void setSecondPlayerItems(ArrayList<Item> secondPlayerItems) {//
        player2.setItems(secondPlayerItems);
    }

    public GameMode getGameMode() {//
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {//
        this.gameMode = gameMode;
    }

    public GameGoal getGameGoal() {//
        return gameGoal;
    }

    public void setGameGoal(GameGoal gameGoal) {//
        this.gameGoal = gameGoal;
    }

    public void setEndGame(boolean endGame) {//
        this.endGame = endGame;
    }

    public FlagForHoldFlagGameMode getFlagForHoldFlagGameMode() {//
        return flagForHoldFlagGameMode;
    }

    public void setFlagForHoldFlagGameMode(FlagForHoldFlagGameMode flagForHoldFlagGameMode) {//
        this.flagForHoldFlagGameMode = flagForHoldFlagGameMode;
    }

    public int getFirstPlayerFlags() {//
        return player1.getFlags();
    }

    public void setFirstPlayerFlags(int firstPlayerFlags) {//
        player1.setFlags(firstPlayerFlags);
    }

    public int getSecondPlayerFlags() {//
        return player2.getFlags();
    }

    public void setSecondPlayerFlags(int secondPlayerFlags) {//
        player2.setFlags(secondPlayerFlags);
    }

    public ArrayList<FlagForCollectFlagGameMode> getFlagForCollectFlagGameModes() {//
        return flagForCollectFlagGameModes;
    }

    public void setFlagForCollectFlagGameModes(ArrayList<FlagForCollectFlagGameMode> flagForCollectFlagGameModes) {//
        this.flagForCollectFlagGameModes = flagForCollectFlagGameModes;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
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

    public Account getFirstPlayer() {//
        return player1.getAccount();
    }

    public Account getSecondPlayer() {//
        return player2.getAccount();
    }

    private int getFirstPlayerCapacityMana() {//
        return player1.getCapacityMana();
    }

    private int getSecondPlayerCapacityMana() {
        return player2.getCapacityMana();
    }

    public ArrayList<Card> getFirstPlayerGraveYard() {//
        return player1.getGraveyard();
    }

    public ArrayList<Card> getFirstPlayerHand() {//
        return player1.getHand();
    }

    public ArrayList<Card> getSecondPlayerHand() {//
        return player2.getHand();
    }

    ArrayList<Cell> getValidCells() {//
        return validCells;
    }

    public boolean isEndGame() {//
        return endGame;
    }

    private ArrayList<Card> selectRandomCardsForHand(ArrayList<Card> cards) {//
        Random random = new Random();
        ArrayList<Card> temp = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int randomIndex = 1 + random.nextInt(5);
            temp.add(cards.get(randomIndex));
            cards.remove(randomIndex);
        }
        return temp;
    }

    private void setFirstPlayerNextCard() {//
        Random random = new Random();
        setFirstPlayerNextCard(getFirstPlayerDeck().getCards().get(random.nextInt(getFirstPlayerDeck().getCards().size())));
    }

    private void setFirstPlayerCapacityMana(int firstPlayerCapacityMana) {//
        player1.setCapacityMana(firstPlayerCapacityMana);
    }

    private void setSecondPlayerCapacityMana(int secondPlayerCapacityMana) {//
        player2.setCapacityMana(secondPlayerCapacityMana);
    }

    public void increaseCapacityMana(Account account, int i) {//
        if (account.equals(getFirstPlayer())) {
            incrementFirstPlayerCapacityMana(i);
        } else if (account.equals(getSecondPlayer())) {
            incrementSecondPlayerCapacityMana(i);
        }
    }

    private void incrementFirstPlayerCapacityMana(int i) {//
        setFirstPlayerCapacityMana(getFirstPlayerCapacityMana() + i);
    }

    private void incrementSecondPlayerCapacityMana(int i) {//
        setSecondPlayerCapacityMana(getSecondPlayerCapacityMana() + i);
    }

    private void setFirstPlayerMana(int firstPlayerMana) {//
        player1.setCurrentMana(firstPlayerMana);
    }

    private void setSecondPlayerMana(int secondPlayerMana) {//
        player2.setCurrentMana(secondPlayerMana);
    }

    private void setSecondPlayerNextCard() {//
        Random random = new Random();
        setSecondPlayerNextCard(getSecondPlayerDeck().getCards().get(random.nextInt(getSecondPlayerDeck().getCards().size())));
    }

    public Card getNextCard() {//

        if (getTurn() % 2 == 1) {
            return getFirstPlayerNextCard();
        } else
            return getSecondPlayerNextCard();
    }

    private Card getFirstPlayerNextCard() {//
        return player1.getNextCard();
    }

    private Card getSecondPlayerNextCard() {//
        return player2.getNextCard();
    }

    private void setFlagForCollectFlagGameModes(int n) {
        int[] randomX = new int[n];
        int[] randomY = new int[n];
        getNRandomNumber(randomX, randomY, 0, n / 2, 0);
        getNRandomNumber(randomX, randomY, n / 2, n, 5);
        for (int i = 0; i < randomX.length; i++) {
            getFlagForCollectFlagGameModes().add(new FlagForCollectFlagGameMode(getMap().getCell(randomX[i], randomY[i])));
            getMap().getCell(randomX[i], randomY[i]).setItem(getFlagForCollectFlagGameModes().get(i));
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

    private void setHandOfFirstPlayer() {//
        player1.setHand(selectRandomCardsForHand(getFirstPlayerDeck().getCards()));
    }

    private void setHandOfSecondPlayer() {
        player2.setHand(selectRandomCardsForHand(getFirstPlayerDeck().getCards()));

    }

    public Account getOtherTurnPlayer() {
        if (getTurn() % 2 == 1) {
            return getSecondPlayer();
        } else {
            return getFirstPlayer();
        }
    }

    public void selectCard(Card card) {
        setSelectedCard(card);
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public int getTurn() {
        return turn;
    }
}