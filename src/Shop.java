import java.util.ArrayList;

public class Shop {

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private static Shop instance;

    private Shop() {

    }

    public static Shop getInstance() {
        if (instance == null)
            instance = new Shop();
        return instance;
    }

    public void show() {

    }

    public String search(String name) {
        return "";
    }

    public void buy(String name, Account account) {
        Card card = searchAndGetCard(name);
        Item item = searchAndGetItem(name);
        if (card != null && card.getDarikCost() <= account.getDarick()) {
            account.getCardCollection().addCard(card);
        } else if (item != null && item.getDarickCost() <= account.getDarick()) {
            account.getCardCollection().addItem(item);
        }

    }

    public void sell(String cardId, Account account) {
        Card card = account.getCardCollection().findCard(cardId);
        if (card!=null)
        {
            account.increaseDarick(card.getDarikCost());
            account.getCardCollection().removeCard(card);
        }
    }

    public void help() {

    }

    public Card searchAndGetCard(String name) {
        return Card.findCardInArrayList(name, getCards());
        //return new Card(""  , 1 , 1 ,CardKind.HERO , "");
    }

    public Item searchAndGetItem(String name) {
        return Item.findItemInArrayList(name, getItems());
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addItem(Item item) {
        items.add(item);
    }


}
