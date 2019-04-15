import java.util.ArrayList;

public class Battle {
    private Map map;
    private Account firstPlayer;
    private Account secondPlayer;
    private int turn = 1;
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
            firstPlayerMana = 2;
        } else if (turn == 2) {
            secondPlayerMana = 3;
        } else if (turn % 2 == 1) {
            firstPlayerMana++;
            secondPlayerMana++;
        }
    }

    public void decreaseMana(int number, int numberOfPlayer) {
        if (numberOfPlayer == 1) {
            firstPlayerMana -= number;
        } else secondPlayerMana -= number;
    }

    public void gameInfo() {

    }

    public void showMinions(boolean isFreindly) {

    }

    public void showMinionsOfPlayer(int numberOfPlayer) {

    }
    public void showCardInfo(String cardID) {

    }

    public void selectedCard(String cardID) {

    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public int getTurn() {
        return turn;
    }

    public void moveCard(Card card, Cell destinationCell) {

    }

    public void attack(Cell targetCell, String warriorsCarId) {

    }

    public void attackCombo(Cell targetCell, String... warriorsCarIds) {

    }

    public void useSpecialPower(int row, int column) {

    }

    public void showHand(int numberOfPlayer) {

    }

    public void insertCard(String cardName, Cell cell) {

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

    public void findValidCell(String kindOfAcction){

    }
    public void findValidCellToMove(){

    }
    public void findValidCellToAttack(){

    }
    public void findValidCellToInsert(){

    }
    public void findValidCellToItem(){

    }
    public void findValidCellToSpell(){

    }
    private boolean isVAlidMove(Cell destinationCell){
        return true ;
    }
    private boolean isValidComboAttack(Cell targetCell , String... warriorsCardID){
        return true ;
    }
    private boolean isValidSpeicalPower(int row , int column){
        return true ;
    }





}
