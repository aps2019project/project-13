package View;

public enum ConstantMessages {
    //Errors
    //**************************************
    INVALID_COMMAND("The input command is not valid!"),
    USERNAME_NOT_EXIST("No user with this Username found!"),
    INVALID_CELL_TO_INSERT_CARD("Selected cell is invalid to insert card"),
    INVALID_PASSWORD("Wrong Password!"),
    DECK_NOT_EXIST("No Deck exists with this name!"),
    DECK_EXIST("A Deck Exists with this name!"),
    INVALID_DECK_SECOND_USER("Selected deck for second user is Invalid!"),
    INVALID_CELL_TO_MOVE("Can't move to this Cell,Please try another Cell"),
    ITEM_NOT_EXIST("No Item exists with this name!"),
    CARD_NOT_EXIST("NO Card exists with this name!"),
    INVALID_DECK("Current Deck combination is Invalid!"),
    ITEM_NOT_IN_SHOP("We don't have this Item in Shop!"),
    CARD_NOT_IN_SHOP("We don't have this Card in Shop!"),
    NOT_ENOUGH_MONEY("You don't have enough Darick!"),
    MORE_THAN_3_ITEM("You have more than 3 Items in your deck!"),
    INVALID_CARD_ID("Invalid Card ID!"),
    INVALID_TARGET("Invalid Target!"),
    MINION_NOT_AVAILABLE_ATTACK("Opponent minion is unavailable for attack!"),
    HERO_NOT_AVAILABLE_ATTACK("Opponent hero is unavailable for attack!"),
    NO_SPECIAL_POWER("This Card doesnt' have Special Power!"),
    NOT_ENOUGH_MANA("Not enough Mana!"),
    INVALID_CARD_NAME("Invalid Card name!"),
    NOT_YOUR_TURN("It is not your turn!"),
    NO_CARD_SELECTED("Please Select a Card"),
    NOT_IN_SHOP("Sorry!We don't have this one in our shop"),
    //*****************************************

    //Successful Messages
    //*****************************************
    USERNAME_EXIST("A user exists with this Username!"),
    ITEM_EXIST("An Item exists with this name!"),
    CARD_EXIST("A Card exists with this name!"),
    VALID_DECK("Selected Deck is a Valid Deck"),
    BUY_SUCCESFUL("Your buy request was done successfully!"),
    SELL_SUCCESFULL("Your sell request was done successfully!"),
    //*****************************************

    //Menus
    //*****************************************
    HELP_SHOW("create account [user name]\nlogin [username]\nshow leaderboard\nsave\nlogout"),
    GET_PASSWORD("PassWord [ password > 5 letter ] : "),
    UNRELIABLE_PASSWORD("password is unreliable! , input secure password"),
    YOUR_PASSWORD("PassWord : "),
    COLLECTABLE_HELP("Show info\nUse [location x,y]"),
    GRAVEYARD_HELP("exit\nShow info\nShow Cards"),
    SHOP_HELP("Show menu\nshow collection\nsearch [card name]\nsearch collection [name]\nbuy [name]\nsell [name]\nshow"),
    BATTLE_HELP("Game info\nShow my minions\nShow opponent minions\n" +
            "Show card info [cardID]\nSelect [card id]\nInsert [card name] in (x,y)\nEnd turn\nShow collectables\n" +
            "Show Next Card\nShow hand\nEnter graveyard\nEnd Game\nMove to (x,y)\nAttack [[opponent card id]\nUse special power (x,y)"),
    COLLECTION_HELP("Show menu\nshow\nsearch [card name] \nsave \ncreate deck [deck name] \ndelete deck [deck name]" +
            "\nadd [card id] to deck [deck name]\nremove [card id] from deck [deck name] \nvalidate deck [deck name]\n" +
            "select deck [deck name] \nshow all decks \nshow deck [deck name]"),
    MAIN_MENU_HELP("Show menu\nEnter Collection\nEnter Shop\nEnter Battle\nEnter Exit");
    //*****************************************

    private String message;

    private ConstantMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
