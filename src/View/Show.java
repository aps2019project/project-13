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
        System.out.println("create account [user name]\nlogin [username]\nshow leaderboard\nsave\nlogout");
    }
    public void getPassword(){
        System.out.println("PassWord [ password > 5 letter ] : ");
    }
    public void unreliablePassWord(){
        System.out.println("password is unreliable! , input secure password");
    }
    public void createdAccount(String username){
        System.out.println("new account "+username+" is created");
    }
    public void getYourPasWord(){
        System.out.println("PassWord : ");
    }
    public void incorrectPassWord(){
        System.out.println("incorrect password! , try again!");
    }

}
