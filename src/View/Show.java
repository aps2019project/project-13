package View;

public class Show {
    private static final Show show = new Show();

    private Show(){

    }

    public static Show getInstance() {
        return show;
    }
}
