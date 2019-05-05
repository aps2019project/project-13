package View;

import java.util.regex.Pattern;

public class Patterns {
    //This class maybe will be merged in input system. But as of now, it is just a Place for View.Patterns;
    static Pattern[] accountPatterns = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)show leaderboard\\s*$"),
            Pattern.compile("^(?i)save\\s*$"),
            Pattern.compile("^(?i)logout\\s*$"),
            Pattern.compile("^(?i)help\\s*$"),
            Pattern.compile("^(?i)create account (\\S+)\\s*$"),
            Pattern.compile("^(?i)login (\\S+)\\s*$"),
    };
    static Pattern[] mainMenuPatterns = new Pattern[]{
            Pattern.compile("^(?i)Enter Collection\\s*$"),
            Pattern.compile("^(?i)Enter Shop\\s*$"),
            Pattern.compile("^(?i)Enter Battle\\s*$"),
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)help\\s*$"),
    };
    static Pattern[] collectionPatterns = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)show\\s*$"),
            Pattern.compile("^(?i)save\\s*$"),
            Pattern.compile("^(?i)help$"),
            Pattern.compile("^(?i)show all decks\\s*$"),
            Pattern.compile("^(?i)search (\\S+)\\s*$"),
            Pattern.compile("^(?i)create deck (\\S+)\\s*$"),
            Pattern.compile("^(?i)delete deck (\\S+)\\s*$"),
            Pattern.compile("^(?i)validate deck (\\S+)\\s*$"),
            Pattern.compile("^(?i)select deck (\\S+)\\s*$"),
            Pattern.compile("^(?i)show deck (\\S+)\\s*$"),
            Pattern.compile("^(?i)add (\\S+) to deck (\\S+)\\s*$"),
            Pattern.compile("^(?i)remove (\\S+) from deck (\\S+)\\s*$"),
    };
    static Pattern[] shopPatterns = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)help\\s*$"),
            Pattern.compile("^(?i)show\\s*$"),
            Pattern.compile("^(?i)show collection\\s*$"),
            Pattern.compile("^(?i)search (\\S+)\\s*$"),
            Pattern.compile("^(?i)search collection (\\S+)\\s*$"),
            Pattern.compile("^(?i)buy (\\S+)\\s*$"),
            Pattern.compile("^(?i)sell (\\S+)\\s*$"),
    };
    static Pattern[] battlePatterns = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)Game info\\s*$"),
            Pattern.compile("^(?i)Show my minions\\s*$"),
            Pattern.compile("^(?i)Show opponent minions\\s*$"),
            Pattern.compile("^(?i)Show collectables\\s*$"),
            //Collectable patterns are in another array
            Pattern.compile("^(?i)Show Next Card\\s*$"),
            Pattern.compile("^(?i)End turn\\s*$"),
            Pattern.compile("^(?i)Show hand\\s*$"),
            Pattern.compile("^(?i)Enter graveyard\\s*$"),
            //Graveyard pattern are in another array
            Pattern.compile("^(?i)help\\s*$"),
            Pattern.compile("^(?i)End Game\\s*$"),
            Pattern.compile("^(?i)Show card info (\\S+)\\s*$"),
            Pattern.compile("^(?i)Select (\\S+)\\s*$"),
            //Model.Card move and attack patterns are in another array
            Pattern.compile("^(?i)Insert \\s*(\\S+) \\s*in\\s* \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$"),


    };
    static Pattern[] cardPatterns = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)Move to \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$"),
            Pattern.compile("^(?i)Attack (\\S+)\\s*$"),
            //TODO ATTACK COMBO REGEX
            Pattern.compile("^(?i)Use special power \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$")

    };
    static Pattern[] collectablePatterns = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)Show info\\s*$"),
            Pattern.compile("^(?i)Use \\s*\\(\\s*(\\d)\\s*,\\s*(\\d)\\s*\\)\\s*$")
    };
    static Pattern[] graveyardPatters = new Pattern[]{
            Pattern.compile("^(?i)exit\\s*$"),
            Pattern.compile("^(?i)Show info (\\S+)\\s*$"),
            Pattern.compile("^(?i)Show Cards\\s*$")
    };

}
