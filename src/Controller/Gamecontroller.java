package Controller;

import View.Request;

public class Gamecontroller {
    private static final Gamecontroller gamecontroller = new Gamecontroller();

    private Gamecontroller(){

    }

    public static Gamecontroller getInstance() {
        return gamecontroller;
    }

    public void main(){
        Request request = Request.getInstance();
        while (true){
            request.getRequest();

        }
    }

}
