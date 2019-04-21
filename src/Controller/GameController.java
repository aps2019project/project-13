package Controller;

import View.Request;

public class GameController {
    private static final GameController GAME_CONTROLLER = new GameController();

    private GameController(){

    }

    public static GameController getInstance() {
        return GAME_CONTROLLER;
    }

    public void main(){
        Request request = Request.getInstance();
        while (true){
            request.getRequest();

        }
    }

}
