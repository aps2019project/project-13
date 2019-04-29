package View;

public class Show {
    private static final Show show = new Show();
    private static final String HELP_SHOW = "create account [user name]\nlogin [username]\nshow leaderboard\nsave\nlogout";
    private static final String GET_PASSWORD = "PassWord [ password > 5 letter ] : ";
    private static final String UNRELIBALE_PASSWORD = "password is unreliable! , input secure password";
    private static final String YOUR_PASSWORD = "PassWord : ";

    private Show() {

    }

    public static Show getInstance() {
        return show;
    }

    public void showHelp(KindOfOrder kindOfOrder) {
        switch (kindOfOrder) {
            case COLLECTABLE:
            case GRAVEYARD:
            case CARD:
            case SHOP:
            case BATTLE:
            case ACCOUNT:
                showAccountHelp();
            case MAIN_MENU:
            case COLLECTION:
        }
    }
    private void showAccountHelp(){
        System.out.println(HELP_SHOW);
    }
    public void getPassword(){
        System.out.println(GET_PASSWORD);
    }
    public void unreliablePassWord(){
        System.out.println(UNRELIBALE_PASSWORD);
    }
    public void createdAccount(String username){
        System.out.println("new account "+username+" is created");
    }
    public void getYourPasWord(){ System.out.println(YOUR_PASSWORD);}
    public void incorrectPassWord(){
        System.out.println(ConstantMessages.INVALID_PASSWORD);
    }

}
