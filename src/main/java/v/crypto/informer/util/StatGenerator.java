package v.crypto.informer.util;

import org.springframework.stereotype.Component;
import v.crypto.informer.data.TokenData;
import v.crypto.informer.model.User;

@Component
public class StatGenerator {
    public static String generateStat(User user) {
        StringBuilder builder = new StringBuilder();
        var tokens = user.getList();
        if (!tokens.isEmpty()) {
            for (String tokenName : tokens) {
                var token = TokenData.tokenInfoMap.get(tokenName);
                builder.append(token.toString())
                        .append("\n");
            }
        }
        return builder.toString().trim();
    }
}