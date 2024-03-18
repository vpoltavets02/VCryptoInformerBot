package v.crypto.informer.util;

public class TextConstants {
    public static final String START_MESSAGE = """
            \uD83D\uDC4B Welcome to my Telegram bot! Here, you can stay updated on the latest crypto token rates. Whether you're a seasoned investor or just curious about the world of cryptocurrencies, my bot is here to provide you with real-time information on token prices. Feel free to explore and discover the exciting world of crypto! Happy trading! \uD83D\uDE80\uD83D\uDCC8

            To get help use ❓help0220 command""";

    public static final String HELP_MESSAGE = """
            Here the whole list of all available commands:

            \uD83C\uDFC1/start - register in bot
                        
            \uD83D\uDC64profile - view profile
                        
            \uD83D\uDD14notifications - turn on/off notifications

            ➕add - add token to your list

            ➖remove - remove token from your list

            \uD83D\uDCCAstat - get a stat about tokens from your list

            ❓help - get a list of all available commands
            
            Found bug? Write to my father ➡ @vpoltavets02\uD83D\uDC68\u200D\uD83D\uDCBB""";

    public static final String UNREGISTERED_USER = "You're not registered in bot. Use /start command for registering";

    public static final String EMPTY_LIST = "You need to add at least one token" +
            " to see statistic. For adding token to your list use command ➕add";

    public static final String FULL_LIST = "You reached the maximum number of tokens in your list." +
            " Remove at least one token to add new token. For this use ➖remove command from menu";
}