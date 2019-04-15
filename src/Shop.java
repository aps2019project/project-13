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

    }

    public void sell(String cardId, Account account) {

    }

    public void help() {

    }

    public Card searchAndGetCard(String name ){

        return new Card(""  , 1 , 1 ,CardKind.HERO , "");
    }

    public Item searchAndGetItem(String name ){
        return new Item();
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
