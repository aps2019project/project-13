package View;

public enum ConstantMessages {
    INVALID_COMMAND ("The input command is not valid!"),
    USERNAME_NOT_EXIST("No user with this Username found!"),
    INVALID_PASSWORD("Wrong Password!"),
    DECK_NOT_EXIST ("No Deck exists with this name!"),
    DECK_EXIST ("A Deck Exists with this name!"),
    INVALID_DECK_SECOND_USER ("Selected deck for second user is Invalid!"),
    USERNAME_EXIST ("A user exists with this Username!"),
    ITEM_NOT_EXIST ("No Item exists with this name!"),
    CARD_NOT_EXIST ("NO Card exists with this name!"),
    ITEM_EXIST("An Item exists with this name!"),
    CARD_EXIST("A Card exists with this name!"),
    INVALID_DECK("Current Deck combination is Invalid!"),
    ITEM_NOT_IN_SHOP ("We don't have this Item in Shop!"),
    CARD_NOT_IN_SHOP ("We don't have this Card in Shop!"),
    NOT_ENOUGH_MONEY ("You don't have enough Darick!"),
    MORE_THAN_3_ITEM("You have more than 3 Items in your deck!"),
    BUY_SUCCESFUL("Your buy request was done successfully!"),
    SELL_SUCCESFULL("Your sell request was done successfully!"),
    INVALID_CARD_ID ("Invalid Card ID!"),
    INVALID_TARGET ("Invalid Target!"),
    MINION_NOT_AVAILABLE_ATTACK ("Opponent minion is unavailable for attack!"),
    HERO_NOT_AVAILABLE_ATTACK("Opponent hero is unavailable for attack!"),
    NO_SPECIAL_POWER ("This Card doesnt' have Special Power!"),
    NOT_ENOUGH_MANA ("Not enough Mana!"),
    INVALID_CARD_NAME ("Invalid Card name!"),
    NOT_YOUR_TURN ("It is not your turn!");

    private String message;
    private ConstantMessages(String message)
    {
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
