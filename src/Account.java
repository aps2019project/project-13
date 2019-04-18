import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<String> battleHistory;
    private ArrayList<Deck> decks;
    private CardCollection cardCollection;
    private Deck mainDeck;
    private String username;
    private String password;
    private int darick;

    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
        setDecks(new ArrayList<>());
        setCardCollection(new CardCollection(this));
        setBattleHistory(new ArrayList<>());
        accounts.add(this);
    }

    public boolean validateLogin(String username, String password) {
        Account account = findAccount(username);
        if (account == null) {
            return false;
        } else if (!account.getPassword().equals(password)) {
            return false;
        }
        return true;

    }
    public void selectDeckAsMainDeck(String deckName)
    {
        Deck deck = findDeck(deckName);
        if (deck !=null)
        {
            setMainDeck(deck);
        }
    }

    public boolean playerExistWithUsername(String username) {
        Account account = findAccount(username);
        if (account == null) {
            return false;
        }
        return true;
    }

    public Account findAccount(String username) {
        for (Account account : accounts) {
            if (account != null && account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
    public Deck findDeck(String deckName)
    {
        for (Deck deck : decks)
        {
            if (deck!=null && deck.getDeckName().equals(deckName))
            {
                return deck;
            }
        }
        return  null;
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


}
