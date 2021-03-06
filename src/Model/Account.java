package Model;

import View.*;
import View.Error;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

public class Account implements Cloneable {
    private static Account loginedAccount = null;
    private static ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<String> battleHistory;
    private ArrayList<Deck> decks;
    private CardCollection cardCollection;
    private Deck mainDeck;
    private String username;
    private String password;
    private int countOfWins;
    private int darick;
    private ArrayList<Item> collectableItems;

    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
        decks = new ArrayList<>();
        setCardCollection(new CardCollection(this));
        setBattleHistory(new ArrayList<>());
        collectableItems = new ArrayList<>();
        setDarick(100000);
        if (!(this instanceof Ai)) {
            accounts.add(this);
            loginedAccount = this;
        }
    }

    public void increaseDarick(int number) {
        setDarick(getDarick() + number);
    }

    public void decreaseDarick(int number) {
        setDarick(getDarick() - number);
    }

    public void selectDeckAsMainDeck(String deckName) {
        Deck deck = findDeck(deckName);
        if (deck != null) {
            setMainDeck(deck);
        }
    }

    public Account findAccount(String username) {
        for (Account account : accounts) {
            if (account != null && account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;//TODO dar controler in tabe piade shode ast,shayad jabeja kardim be inja shayadam ino hazf kardim
    }

    public Deck findDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck != null && deck.getDeckName().equals(deckName)) {
                return deck;
            }
        }
        return null;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<String> getBattleHistory() {
        return battleHistory;
    }

    public void setBattleHistory(ArrayList<String> battleHistory) {
        this.battleHistory = battleHistory;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void addDeck(Deck deck) {
        this.decks.add(deck);
    }

    public CardCollection getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(CardCollection cardCollection) {
        this.cardCollection = cardCollection;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDarick() {
        return darick;
    }

    public void setDarick(int darick) {
        this.darick = darick;
    }

    public static Account getLoginedAccount() {
        return loginedAccount;
    }

    public static void setLoginedAccount(Account loginedAccount) {
        if (loginedAccount instanceof Ai)
            return;
        Account.loginedAccount = loginedAccount;
    }

    public static void createAccount(Request request, AccountCommand accountCommand) throws Error {
        Show show = Show.getInstance();
        String userName = accountCommand.getData();
        getAccount(userName);
        show.getPassword();
        String passWord;
        passWord = request.getPassWord();
        while (passWord.length() < 4) {
            show.unreliablePassWord();
            show.getPassword();
            passWord = request.getPassWord();
        }
        Account account = new Account(userName, passWord);
        show.createdAccount(userName);
        Account.setLoginedAccount(account);
        request.addNewMenu(KindOfOrder.MAIN_MENU);
        show.showMainMenu();
    }

    private static void getAccount(String userName) {
        for (Account account :
                Account.getAccounts()) {
            if (account.getUsername().equals(userName)) {
                throw new Error(ConstantMessages.USERNAME_EXIST.getMessage());
            }
        }
    }

    public static void login(Request request, AccountCommand accountCommand) {
        Show show = Show.getInstance();
        String userName = accountCommand.getData();
        Account foundAccount = null;
        foundAccount = getAccount(userName, foundAccount);
        if (foundAccount == null) {
            throw new Error(ConstantMessages.USERNAME_NOT_EXIST.getMessage());
        }
        show.getYourPasWord();
        String passWord;
        passWord = request.getPassWord();
        while (!passWord.equals(foundAccount.getPassword())) {
            show.incorrectPassWord();
            show.getYourPasWord();
            passWord = request.getPassWord();
        }
        Account.setLoginedAccount(foundAccount);
        request.addNewMenu(KindOfOrder.MAIN_MENU);
        show.showMainMenu();

    }

    public static Account getAccount(String userName, Account foundAccount) {
        for (Account account :
                Account.getAccounts()) {
            if (account.getUsername().equals(userName)) {
                foundAccount = account;
            }
        }
        return foundAccount;
    }

    private static Comparator<Account> sortByWin = (player1, player2) -> {
        if (player1.getCountOfWins() > player2.getCountOfWins())
            return -1;
        else if (player1.getCountOfWins() == player2.getCountOfWins())
            return 0;
        else
            return 1;
    };

    public static void sortAccounts() {
        Collections.sort(accounts, Account.sortByWin);
    }

    public void incrementCountOfWins(int i) {
        setCountOfWins(getCountOfWins() + i);
    }

    public int getCountOfWins() {
        return countOfWins;
    }

    public void setCountOfWins(int countOfWins) {
        this.countOfWins = countOfWins;
    }

    @Override
    public String toString() {
        return "UserName: " + username + " - Wins: " + countOfWins;
    }

    public void addCollectableItem(Item item) {
        collectableItems.add(item);
    }

    public ArrayList<Item> getCollectableItems() {
        return collectableItems;
    }

    public static void saveAccount() {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter("accounts.json");
            String s = new String(yaGson.toJson(accounts));
            writer.write(s);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
