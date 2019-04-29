package View;

public class Show {
    private static final Show show = new Show();
    private static final String HELP_SHOW = "create account [user name]\nlogin [username]\nshow leaderboard\nsave\nlogout";
    private static final String GET_PASSWORD = "PassWord [ password > 5 letter ] : ";
    private static final String UNRELIBALE_PASSWORD = "password is unreliable! , input secure password";
    private static final String YOUR_PASSWORD = "PassWord : ";
    private static final String INCORRECT_PASSWORD = "incorrect password! , try again!";
    private static final String COLLECTABLE_HELP = "Show info\nUse [location x,y]";
    private static final String GRAVEYARD_HELP = "exit\nShow info\nShow Cards";
    private static final String SHOP_HELP = "Show menu\nshow collection\nsearch [card name]\nsearch collection [name]\nbuy [name]\nsell [name]\nshow";
    private static final String BATTLE_HELP = "Game info\nShow my minions\nShow opponent minions\n" +
            "Show card info [cardID]\nSelect [card id]\nInsert [card name] in (x,y)\nEnd turn\nShow collectables\n" +
            "Show Next Card\nShow hand\nEnter graveyard\nEnd Game\nMove to (x,y)\nAttack [[opponent card id]\nUse special power (x,y)";
    private static final String COLLECTION_HELP = "Show menu\nshow\nsearch [card name] \nsave \ncreate deck [deck name] \ndelete deck [deck name]" +
            "\nadd [card id] to deck [deck name]\nremove [card id] from deck [deck name] \nvalidate deck [deck name]\n" +
            "select deck [deck name] \nshow all decks \nshow deck [deck name]";
    public static final String MAIN_MENU_HELP = "Show menu\nEnter Collection\nEnter Shop\nEnter Battle\nEnter Exit";

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
        System.out.println(HELP_SHOW);
    }

    public void getPassword() {
        System.out.println(GET_PASSWORD);
    }

    public void unreliablePassWord() {
        System.out.println(UNRELIBALE_PASSWORD);
    }

    public void createdAccount(String username) {
        System.out.println("new account " + username + " is created");
    }
        private void showCollectabletHelp () {
            System.out.println(COLLECTABLE_HELP);
        }

        private void showGraveYardHelp () {
            System.out.println(GRAVEYARD_HELP);
        }

        private void showShopHelp () {
            System.out.println(SHOP_HELP);
        }

        private void showBattleHelp () {
            System.out.println(BATTLE_HELP);
        }

        private void showColectionHelp () {
            System.out.println(COLLECTION_HELP);
        }

        private void showMainMenuHelp () {
            System.out.println(MAIN_MENU_HELP);
        }
}