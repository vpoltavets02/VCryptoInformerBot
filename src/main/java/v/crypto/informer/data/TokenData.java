package v.crypto.informer.data;

import lombok.Data;
import v.crypto.informer.model.TokenInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class TokenData {
    public static Map<String, TokenInfo> tokenInfoMap;

    private TokenData() {
    }

    static {
        tokenInfoMap = new TreeMap<>();
    }

    public static void updateData(List<TokenInfo> tokenInfoList) {
        for (TokenInfo token : tokenInfoList)
            tokenInfoMap.put(token.getSymbol(), token);
    }

    public static String getListInfo() {
        StringBuilder builder = new StringBuilder();
        for (TokenInfo token : tokenInfoMap.values())
            builder.append(String.format("<b>%s</b> (%s) — %s\n", token.getSymbol(),
                    token.getName(), token.getPriceUsd()));
        return builder.toString().trim();
    }
}