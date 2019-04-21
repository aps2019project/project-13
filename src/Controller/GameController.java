package Controller;

import View.*;
import Model.*;

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
            commandManagement(request, request.getKindOfOrder().get(request.getKindOfOrder().size() - 1));
        }
    }

    private void commandManagement(Request request, KindOfOrder kindOfOrder) {
        switch (kindOfOrder) {
            case COLLECTION:
                collectionCommandManagement(request, request.getCollectionCommand());
                break;
            case MAIN_MENU:
                mainMenuCommandManagement(request, request.getMainCommand());
                break;
            case ACCOUNT:
                accountCommandManagement(request, request.getAccountCommand());
                break;
            case BATTLE:
                battleCommandManagement(request, request.getBattleCommand());
                break;
            case SHOP:
                shopCommandManagement(request, request.getShopCommand());
                break;
            case CARD:
                cardCommandManagement(request, request.getCardCommand());
                break;
            case GRAVEYARD:
                graveYardCommandManagement(request, request.getGraveYardCommand());
                break;
            case COLLECTABLE:
                collectableCommandManagement(request, request.getCollectableCommand());
        }
    }

    private void shopCommandManagement(Request request, ShopCommand shopCommand) {
        Shop shop = Shop.getInstance();
        Show show = Show.getInstance();
        switch (shopCommand) {
            case BUY:
                shop.buy(shopCommand.getData(), new Account("userName", "password"));
                break;
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case HELP:
                show.showHelp(KindOfOrder.SHOP);
            case SELL:
                shop.sell(shopCommand.getData(), new Account("userName", "password"));
            case SHOW:
            case SEARCH:
            case SHOW_MENU:
            case SHOW_COLLECTION:
            case SEARCH_COLLECTION:
        }
    }

    private void mainMenuCommandManagement(Request request, MainCommand mainCommand) {
        switch (mainCommand) {
            case SHOW_MENU:
                //TODO bde b show
            case ENTER_EXIT:
                isFinish = true;
                break;
            case ENTER_HELP:
                //TODO bde b show
            case ENTER_SHOP:
                //TODO bde b show
            case ENTER_BATTLE:
                //TODO bde b show
            case ENTER_COLLECTION:
                //TODO bde b show
        }
    }

    private void accountCommandManagement(Request request, AccountCommand accountCommand) {
        switch (accountCommand) {
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case SHOW_MENU:
            case HELP:
            case SAVE:
            case LOGIN:
            case LOGOUT:
            case CREATE_ACCOUNT:
            case SHOW_LEADERBOARD:
        }
    }

    private void battleCommandManagement(Request request, BattleCommand battleCommand) {
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
            case SHOW_MY_MINIONS:
            case SHOW_COLLECTABLE:
            case SHOW_OPPONENT_MINIONS:
        }
    }

    private void collectionCommandManagement(Request request, CollectionCommand collectionCommand) {
        switch (collectionCommand) {
            case HELP:
            case SAVE:
            case SHOW_MENU:
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
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

    private void cardCommandManagement(Request request, CardCommand cardCommand) {
        switch (cardCommand) {
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case MOVE:
            case ATTACK:
            case USE_SPECIAL_POWER:
        }
    }

    private void graveYardCommandManagement(Request request, GraveYardCommand graveYardCommand) {
        switch (graveYardCommand) {
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case SHOW_CARD:
            case SHOW_INFO:
        }
    }

    private void collectableCommandManagement(Request request, CollectableCommand collectableCommand) {
        switch (collectableCommand) {
            case SHOW_INFO:
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case USE:
        }
    }


}
