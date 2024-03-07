package v.crypto.informer.util;

public class TextConstants {
    public static final String START_MESSAGE = "\uD83D\uDC4B Welcome to my Telegram bot! Here, you can stay updated on the latest crypto token " +
            "rates. Whether you're a seasoned investor or just curious about the world of cryptocurrencies, my bot " +
            "is here to provide you with real-time information on token prices. Feel free to explore and discover " +
            "the exciting world of crypto! Happy trading! \uD83D\uDE80\uD83D\uDCC8";

    public static final String HELP_MESSAGE = """
            Here the whole list of all available commands:

            \uD83C\uDFC1 /start - start bot

            ➕ /add - add crypto token to your list

            ➖ /remove - remove crypto token from your list

            \uD83D\uDCCA /stat - get a stat about tokens from your list

            ❓ /help - get a list of all available commands""";

    public static final String EMPTY_LIST = "You need to add at least one token" +
            " to see statistic. For adding token to your list use command /add";

    public static final String FULL_LIST = "You reached the maximum number of tokens in your list." +
            " Remove at least one token to add new token.";

    public static final String TOKEN_LIST = """
            1INCH AAVE ADA AGIX AKT
            
            ALGO AR ATOM AVAX AXS
            
            BCH BNB BSV BTC BTCB
            
            BTG CAKE CELO CFX CHZ
            
            CKB COMP CRO CRV DAI
            
            DOGE DOT DYDX EGLD ENJ
            
            EOS ETC ETH FET FIL
            
            FLOW FTM FTT FXS GALA
            
            GLM GNO GRT HBAR HNT
            
            HOT ICP INJ IOTA IOTX
            
            KAVA KCS KLAY LDO LEO
            
            LINK LTC MANA MATIC MINA
            
            MKR NEAR NEO NEXO OCEAN
            
            OKB PENDLE QNT RBN RNDR
            
            ROSE RPL RUNE SAND SHIB
            
            SNX SOL STX SUPER TFUEL
            
            THETA TRAC TRX TUSD TWT
            
            UNI USDC USDT VET WBTC
            
            WEMIX WOO XDC XEC XLM
            
            XMR XRP XTZ ZEC ZIL""";
}