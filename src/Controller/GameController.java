package Controller;

import View.*;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

public class GameController {
    private static final GameController gamecontroller = new GameController();
    private boolean isFinish = false;

    private GameController() {

    }

    public static GameController getInstance() {
        return gamecontroller;
    }

    public void main() {
        Request request = Request.getInstance();
        while (!isFinish) {
            request.getRequest();
            commandManagement(request, request.getKindOfOrder().get(request.getKindOfOrder().size()));
        }
    }

    private void commandManagement(Request request, KindOfOrder kindOfOrder) {
        switch (kindOfOrder) {
            case COLLECTION:
                collectionCommandManagement(request.getCollectionCommand());
                break;
            case MAIN_MENU:
                mainMenuCommandManagement(request.getMainCommand());
                break;
            case ACCOUNT:
                accountCommandManagement(request.getAccountCommand());
                break;
            case BATTLE:
                battleCommandManagement(request.getBattleCommand());
                break;
            case SHOP:
                shopCommandManagement(request.getShopCommand());
                break;
            case CARD:
                cardCommandManagement(request.getCardCommand());
                break;
            case GRAVEYARD:
                graveYardCommandManagement(request.getGraveYardCommand());
                break;
            case COLLECTABLE:
                collectableCommandManagement(request.getCollectableCommand());
        }
    }

    private void shopCommandManagement(ShopCommand shopCommand) {
        switch (shopCommand) {
            case BUY:
            case BACK:
            case EXIT:
            case HELP:
            case SELL:
            case SHOW:
            case SEARCH:
            case SHOW_MENU:
            case SHOW_COLLECTION:
            case SEARCH_COLLECTION:
        }
    }

    private void mainMenuCommandManagement(MainCommand mainCommand) {
        switch (mainCommand) {
            case SHOW_MENU:
            case EXIT:
            case BACK:
            case ENTER_EXIT:
            case ENTER_HELP:
            case ENTER_SHOP:
            case ENTER_BATTLE:
            case ENTER_COLLECTION:
        }
    }

    private void accountCommandManagement(AccountCommand accountCommand) {
        switch (accountCommand) {
            case BACK:
            case EXIT:
            case SHOW_MENU:
            case HELP:
            case SAVE:
            case LOGIN:
            case LOGOUT:
            case CREATE_ACCOUNT:
            case SHOW_LEADERBOARD:
        }
    }

    private void battleCommandManagement(BattleCommand battleCommand) {
        switch (battleCommand) {
            case HELP:
            case INSERT:
            case SELECT:
            case END_GAME:
            case END_TURN:
            case GAME_INFO:
            case SHOW_HAND:
            case SHOW_CARD_INFO:
            case SHOW_NEXT_CARD:
            case ENTER_GRAVE_YARD:
            case SHHOW_MY_MINIONS:
            case SHOW_COLLECTABLE:
            case SHOW_OPPONENT_MINIONS:
        }
    }

    private void collectionCommandManagement(CollectionCommand collectionCommand) {
        switch (collectionCommand) {
            case HELP:
            case SAVE:
            case SHOW_MENU:
            case EXIT:
            case BACK:
            case SEARCH:
            case SHOW:
            case SHOW_DECK:
            case ADD_TO_DECK:
            case CREATE_DECK:
            case DELETE_DECK:
            case SELECTE_DECK:
            case SHOW_ALL_DECK:
            case VALIDATE_DECK:
            case REMOVE_FROM_DECK:
        }
    }

    private void cardCommandManagement(CardCommand cardCommand) {
        switch (cardCommand) {
            case BACK:
            case EXIT:
            case MOVE:
            case ATTACK:
            case USE_SPECIAL_POWER:
        }
    }

    private void graveYardCommandManagement(GraveYardCommand graveYardCommand) {
        switch (graveYardCommand) {
            case EXIT:
            case BACK:
            case SHOW_CARD:
            case SHOW_INFO:
        }
    }

    private void collectableCommandManagement(CollectableCommand collectableCommand) {
        switch (collectableCommand) {
            case SHOW_INFO:
            case BACK:
            case EXIT:
            case USE:
        }
    }


}
