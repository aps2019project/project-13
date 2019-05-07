package View;

public enum ConstantMessages {
    //Errors
    //**************************************
    INVALID_COMMAND("The input command is not valid!"),
    USERNAME_NOT_EXIST("No user with this Username found!"),
    INVALID_CELL_TO_INSERT_CARD("Selected cell is invalid to insert card"),
    INVALID_CELL_TO_USE_SPECIAL_POWER("this cell invalid to use special power!"),
    INVALID_PASSWORD("Wrong Password!"),
    DECK_NOT_EXIST("No Deck exists with this name!"),
    DECK_EXIST("A Deck Exists with this name!"),
    INVALID_DECK_SECOND_USER("Selected deck for second user is Invalid!"),
    INVALID_CELL_TO_MOVE("Can't move to this Cell,Please try another Cell"),
    ITEM_NOT_EXIST("No Item exists with this name!"),
    CARD_NOT_EXIST("NO Card exists with this name!"),
    INVALID_DECK("Current Deck combination is Invalid!"),
    INVALID_ADD_TO_DECK("This card Id already exist in this deck"),
    ITEM_NOT_IN_SHOP("We don't have this Item in Shop!"),
    CARD_NOT_IN_SHOP("We don't have this Card in Shop!"),
    NOT_ENOUGH_MONEY("You don't have enough Darick!"),
    MORE_THAN_3_ITEM("You have more than 3 Items in your deck!"),
    INVALID_CARD_ID("Invalid Card ID!"),
    INVALID_TARGET("Invalid Target!"),
    CARD_MOVED_BEFORE("selected card moved previously!"),
    MINION_NOT_AVAILABLE_ATTACK("Opponent minion is unavailable for attack!"),
    HERO_NOT_AVAILABLE_ATTACK("Opponent hero is unavailable for attack!"),
    NO_SPECIAL_POWER("This Card doesnt' have Special Power!"),
    NOT_ENOUGH_MANA("Not enough Mana!"),
    INVALID_CARD_NAME("Invalid Card name!"),
    NOT_YOUR_TURN("It is not your turn!"),
    NO_CARD_SELECTED("Please Select a Card"),
    NOT_IN_SHOP("Sorry!We don't have this one in our shop"),
    NOT_ENOUGH_CARD_TO_ADD_TO_DECK("You don't have enough cards of this one to add more to this deck!"),
    //*****************************************

    //Successful Messages
    //*****************************************
    USERNAME_EXIST("A user exists with this Username!"),

    ITEM_EXIST("An Item exists with this name!"),
    GAMED_SAVED("Game Saved Successfully :)"),
    CARD_EXIST("A Card exists with this name!"),
    DECK_CREATED("Deck successfully created!"),
    VALID_DECK("Selected Deck is a Valid Deck"),
    MAIN_DECK_SELECTED("Main Deck Selected Successfully"),
    CARD_ADDED_TO_DECK("Card successfully added to your deck!"),

    BUY_SUCCESSFUL("Your buy request was done successfully!"),

    SELL_SUCCESSFUL("Your sell request was done successfully!"),
    //*****************************************

    //Menus
    //*****************************************
    HELP_SHOW("create account [user name]\nlogin [username]\nshow leaderboard\nsave\nlogout"),

    GET_PASSWORD("PassWord [ password > 5 letter ] : "),

    UNRELIABLE_PASSWORD("password is unreliable! , input secure password"),

    YOUR_PASSWORD("PassWord : "),

    COLLECTABLE_HELP("Show info\nUse [location x,y]"),

    GRAVEYARD_HELP("exit\nShow info\nShow Cards"),

    SHOP_HELP("show collection\nsearch [card name]\nsearch collection [name]\nbuy [name]\nsell [name]\nshow"),

    BATTLE_HELP("end Game\nGame info\nShow my minions\nShow opponent minions\n" +
            "Show card info [cardID]\nSelect [card id]\nInsert [card name] in (x,y)\nEnd turn\nShow collectables\n" +
            "Show Next Card\nShow hand\nEnter graveyard\nEnd Game\nMove to (x,y)\nAttack [[opponent card id]\nUse special power (x,y)"),

    COLLECTION_HELP("show\nsearch [card name] \nsave \ncreate deck [deck name] \ndelete deck [deck name]" +
            "\nadd [card id] to deck [deck name]\nremove [card id] from deck [deck name] \nvalidate deck [deck name]\n" +
            "select deck [deck name] \nshow all decks \nshow deck [deck name]\n exit"),

    MAIN_MENU_HELP("Enter Collection\nEnter Shop\nEnter Battle\nexit"),

    GAME_MODE("Enter Game Mode :\n1.Single Player\n2.Multi Player\n(1 , 2)? "),

    NUMBER_OF_GAME_MODE("1 or 2 !!? :)"),

    GAME_GOAL("Enter Game Goal :\n1.Hold Flag\n2.Collect Flag\n3.Kill Hero\n(1,2,3)?"),

    NUMBER_OF_GAME_GOAL("1 or 2 or 3 !!? :)"),

    GAME_FLAG("Enter Game Goal :\n1.Hold Flag\n2.Collect Flag\n3.Kill Hero\n(1,2,3)?"),

    NUMBER_OF_GAME_FLAG("input Number !! , try again "),

    MAIN_MENU("1.Collection\n2.Shop\n3.Battle\n4.Exit\n5.Help");

    //*****************************************

    private String message;

    ConstantMessages(String message) {
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
