package v.crypto.informer.util;

import v.crypto.informer.service.impl.TokenService;

import java.util.StringJoiner;

public class TokenListUpdater {
    public static void updateTokenList(TokenService tokenService) {
        var list = tokenService.findTop();
        StringJoiner joiner = new StringJoiner(" ");
        list.forEach(token -> joiner.add(token.getSymbol()));
        TextConstants.tokenList = joiner.toString();
    }
}