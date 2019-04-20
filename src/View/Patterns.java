package View;

import java.util.regex.Pattern;

public class Patterns {
    //This class maybe will be merged in input system. But as of now, it is just a Place for View.Patterns;
    public static Pattern[] accountPatterns = new Pattern[]{
            Pattern.compile("^back\\s+$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Show menu\\s*$"),
            Pattern.compile("^create account (\\S+)\\s*$"),
            Pattern.compile("^login (\\S+)\\s*$"),
            Pattern.compile("^show leaderboard\\s*$"),
            Pattern.compile("^save\\s*$"),
            Pattern.compile("^logout\\s*$"),
            Pattern.compile("^help\\s*$"),
    };
    public static Pattern[] mainMenuPatterns = new Pattern[]{
            Pattern.compile("^back\\s+$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Show menu\\s*$"),
            Pattern.compile("^Enter Collection\\s*$"),
            Pattern.compile("^Enter Shop\\s*$"),
            Pattern.compile("^Enter Battle\\s*$"),
            Pattern.compile("^Enter Exit\\s*$"),
            Pattern.compile("^Enter Help\\s*$"),
    };
    public static Pattern[] collectionPatterns = new Pattern[]{
            Pattern.compile("^back\\s+$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Show menu\\s*$"),
            Pattern.compile("^show\\s*$"),
            Pattern.compile("^search (\\S+)\\s*$"),
            Pattern.compile("^save\\s*$"),
            Pattern.compile("^create deck (\\S+)\\s*$"),
            Pattern.compile("^delete deck (\\S+)\\s*$"),
            Pattern.compile("^add (\\S+) to deck (\\S+)\\s*$"),
            Pattern.compile("^remove (\\S+) from deck (\\S+)\\s*$"),
            Pattern.compile("^validate deck (\\S+)\\s*$"),
            Pattern.compile("^select deck (\\S+)\\s*$"),
            Pattern.compile("^show all decks\\s*$"),
            Pattern.compile("^show deck (\\S+)\\s*$"),
            Pattern.compile("^help$")
    };
    public static Pattern[] shopPatterns = new Pattern[]{
            Pattern.compile("^back\\s*$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Show menu\\s*$"),
            Pattern.compile("^show collection\\s*$"),
            Pattern.compile("^search (\\S+)\\s*$"),
            Pattern.compile("^search collection (\\S+)\\s*$"),
            Pattern.compile("^buy (\\S+)\\s*$"),
            Pattern.compile("^sell (\\S+)\\s*$"),
            Pattern.compile("^show (\\S+)\\s*$"),
            Pattern.compile("^help (\\S+)\\s*$")
    };
    public static Pattern[] battlePatterns = new Pattern[]{
            Pattern.compile("^Game info\\s*$"),
            Pattern.compile("^Show my minions\\s*$"),
            Pattern.compile("^Show opponent minions\\s*$"),
            Pattern.compile("^Show card info (\\S+)\\s*$"),
            Pattern.compile("^Select (\\S+)\\s*$"),
            //Model.Card move and attack patterns are in another array
            Pattern.compile("^Insert \\s*(\\S+) \\s*in\\s* \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$"),
            Pattern.compile("^End turn\\s*$"),
            Pattern.compile("^Show collectables\\s*$"),
            //Collectable patterns are in another array
            Pattern.compile("^Show Next Card\\s*$"),
            Pattern.compile("^Enter graveyard\\s*$"),
            //Graveyard pattern are in another array
            Pattern.compile("^Help\\s*$"),
            Pattern.compile("^End Game\\s*$")

    };
    public static Pattern[] cardPatterns = new Pattern[]{
            Pattern.compile("^back\\s+$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Move to \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$"),
            Pattern.compile("^Attack (\\S+)\\s*$"),
            //TODO ATTACK COMBO REGEX
            Pattern.compile("^Use special power \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$"),
            Pattern.compile("^Show hand\\s*$")

    };
    public static Pattern[] collectablePatterns = new Pattern[]{
            Pattern.compile("^back\\s+$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Show info\\s*$"),
            Pattern.compile("^Use \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$")
    };
    public static Pattern[] graveyardPatters = new Pattern[]{
            Pattern.compile("^back\\s+$"),
            Pattern.compile("^exit\\s*$"),
            Pattern.compile("^Show info (\\S+)\\s*$"),
            Pattern.compile("^Show Cards\\s*$")
    };

}