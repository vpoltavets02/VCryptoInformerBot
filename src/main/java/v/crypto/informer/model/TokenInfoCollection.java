package v.crypto.informer.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TokenInfoCollection {
    private List<TokenInfo> data;
    public static Map<String, TokenInfo> tokenInfoMap;

    static {
        tokenInfoMap = new HashMap<>();
    }

    public void updateValues() {
        for (TokenInfo token : data)
            tokenInfoMap.put(token.getSymbol(), token);
    }
}
