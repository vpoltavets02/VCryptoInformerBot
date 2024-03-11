package v.crypto.informer.util;

public class TextConstants {
    public static final String START_MESSAGE = """
            \uD83D\uDC4B Welcome to my Telegram bot! Here, you can stay updated on the latest crypto token rates. Whether you're a seasoned investor or just curious about the world of cryptocurrencies, my bot is here to provide you with real-time information on token prices. Feel free to explore and discover the exciting world of crypto! Happy trading! \uD83D\uDE80\uD83D\uDCC8

            To get help use /help command""";

    public static final String HELP_MESSAGE = """
            Here the whole list of all available commands:

            \uD83C\uDFC1 /start - start bot
                        
            \uD83D\uDCCB /all_tokens - display list of all available tokens

            ➕ /add tokenName - add crypto token to your list (example /add btc)

            ➖ /remove tokenName - remove crypto token from your list (example /remove btc)

            \uD83D\uDCCA /stat - get a stat about tokens from your list

            ❓ /help - get a list of all available commands""";

    public static final String EMPTY_LIST = "You need to add at least one token" +
            " to see statistic. For adding token to your list use command /add";

    public static final String FULL_LIST = "You reached the maximum number of tokens in your list." +
            " Remove at least one token to add new token.";

    public static final String TOKEN_LIST = """
            BTC ETH USDT    BNB	SOL
            
            USDC	XRP	ADA	DOGE	SHIB
            
            AVAX	DOT	TRX	LINK	MATIC
            
            WBTC	UNI	BCH	ICP	LTC
            
            ETC	NEAR	LEO	DAI	FIL
            
            STX	XLM	CRO	OKB	RNDR
            
            VET	ATOM	GRT	THETA	INJ
            
            LDO	XMR	RUNE	AR	BTCB
            
            FTM	BSV	ALGO	FET	AAVE
            
            FLOW	HBAR	MKR	EGLD	SAND
            
            QNT	AXS	AGIX	HNT	KCS
            
            MINA	XTZ	AKT	CHZ	EOS
            
            XEC	MANA	TUSD	GALA	CFX
            
            NEO	KAVA	ROSE	WEMIX	WOO
            
            GNO	IOTA	KLAY	SNX	BTG
            
            CKB	CAKE	CRV	RBN	NEXO
            
            FTT	DYDX	PENDLE	ENJ	SUPER
            
            COMP	1INCH	RPL	HOT	GLM
            
            FXS	OCEAN	TWT	CELO	TFUEL
            
            ZIL	IOTX	CSPR	AIOZ	TRAC""";
}