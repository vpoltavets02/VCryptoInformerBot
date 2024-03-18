package v.crypto.informer.util;

import org.springframework.stereotype.Component;
import v.crypto.informer.model.User;
import v.crypto.informer.service.impl.TokenService;

import java.util.Collections;

@Component
public class StatGenerator {
    private final TokenService tokenService;

    public StatGenerator(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String generateStat(User user) {
        var userTokens = user.getList();
        Collections.sort(userTokens);
        StringBuilder builder = new StringBuilder();
        if (!userTokens.isEmpty()) {
            for (String tokenName : userTokens) {
                var token = tokenService.findTokenById(tokenName).get();
                builder.append(token)
                        .append("\n");
            }
        }
        return builder.toString().trim();
    }
}