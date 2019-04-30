package View;

public class Show {
    private static final Show show = new Show();

    private Show() {

    }

    public static Show getInstance() {
        return show;
    }

    public void showHelp(KindOfOrder kindOfOrder) {
        switch (kindOfOrder) {
            case COLLECTABLE:
                showCollectabletHelp();
                break;
            case GRAVEYARD:
                showGraveYardHelp();
                break;
            case SHOP:
                showShopHelp();
                break;
            case BATTLE:
                showBattleHelp();
                break;
            case ACCOUNT:
                showAccountHelp();
                break;
            case MAIN_MENU:
                showMainMenuHelp();
                break;
            case COLLECTION:
                showColectionHelp();
                break;
        }
    }

    private void showAccountHelp() {
        System.out.println(ConstantMessages.HELP_SHOW.getMessage());
    }

    public void getPassword() {
        System.out.println(ConstantMessages.GET_PASSWORD.getMessage());
    }

    public void unreliablePassWord() {
        System.out.println(ConstantMessages.UNRELIABLE_PASSWORD.getMessage());
    }

    public void createdAccount(String username) {
        System.out.println("new account " + username + " is created");
    }

    public void getYourPasWord() {
        System.out.println(ConstantMessages.YOUR_PASSWORD.getMessage());
    }

    public void incorrectPassWord() {
        System.out.println(ConstantMessages.INVALID_PASSWORD.getMessage());
    }

    private void showCollectabletHelp() {
        System.out.println(ConstantMessages.COLLECTABLE_HELP.getMessage());
    }

    private void showGraveYardHelp() {
        System.out.println(ConstantMessages.GRAVEYARD_HELP.getMessage());
    }

    private void showShopHelp() {
        System.out.println(ConstantMessages.SHOP_HELP.getMessage());
    }

    private void showBattleHelp() {
        System.out.println(ConstantMessages.BATTLE_HELP.getMessage());
    }

    private void showColectionHelp() {
        System.out.println(ConstantMessages.COLLECTION_HELP.getMessage());
    }

    private void showMainMenuHelp() {
        System.out.println(ConstantMessages.MAIN_MENU_HELP.getMessage());
    }
}