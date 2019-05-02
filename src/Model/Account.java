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

public class Account {
    private static Account loginedAccount = null;
    private static ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<String> battleHistory;
    private ArrayList<Deck> decks;
    private CardCollection cardCollection;
    private Deck mainDeck;
    private String username;
    private String password;
    private int countOfWins;
    private int darick = 10000;
    private ArrayList<Item> collectableItems;

    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
        setDecks(new ArrayList<>());
        setCardCollection(new CardCollection(this));
        setBattleHistory(new ArrayList<>());
        collectableItems = new ArrayList<>();
        setDarick(100000);
        accounts.add(this);
        loginedAccount = this;
    }

    void increaseDarick(int number) {
        setDarick(getDarick() + number);
    }

    void decreaseDarick(int number) {
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
        return null;
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

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
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
        Account trueAccount = null;
        trueAccount = getAccount(userName, trueAccount);
        if (trueAccount == null) {
            throw new Error(ConstantMessages.USERNAME_NOT_EXIST.getMessage());
        }
        show.getYourPasWord();
        String passWord;
        passWord = request.getPassWord();
        while (!passWord.equals(trueAccount.getPassword())) {
            show.incorrectPassWord();
            show.getYourPasWord();
            passWord = request.getPassWord();
        }
        Account.setLoginedAccount(trueAccount);
        request.addNewMenu(KindOfOrder.MAIN_MENU);
        show.showMainMenu();

    }

    private static Account getAccount(String userName, Account trueAccount) {
        for (Account account :
                Account.getAccounts()) {
            if (account.getUsername().equals(userName)) {
                trueAccount = account;
            }
        }
        return trueAccount;
    }

    private static Comparator<Account> sortByWin = new Comparator<Account>() {
        @Override
        public int compare(Account player1, Account player2) {
            if (player1.getCountOfWins() > player2.getCountOfWins())
                return -1;
            else if (player1.getCountOfWins() == player2.getCountOfWins())
                return 0;
            else
                return 1;
        }
    };

    public static void sortAccounts() {
        Collections.sort(accounts, Account.sortByWin);
    }

    public void incrementCountOfWins() {
        countOfWins++;
    }

    public int getCountOfWins() {
        return countOfWins;
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
