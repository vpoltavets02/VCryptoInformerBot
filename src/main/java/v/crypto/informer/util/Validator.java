package v.crypto.informer.util;

import v.crypto.informer.data.TokenData;
import v.crypto.informer.model.User;

public class Validator {
    public static String validateToAdd(User user, String[] splitMessage) {
        String result = "ok";
        if (splitMessage.length != 2) {
            result = "Incorrect command syntax. Use command /help for getting more information";
        } else {
            var token = splitMessage[1].toUpperCase();
            if (!TokenData.tokenInfoMap.containsKey(token))
                result = String.format("I don't know anything about %s. Please use /all_tokens command to get a list of tokens which I know", token);
            else if (user.getList().contains(token))
                result = String.format("You had already added %s to your list", token);
        }
        return result;
    }

    public static String validateToRemove(User user, String[] splitMessage) {
        String result = "ok";
        if (splitMessage.length != 2) {
            result = "Incorrect command syntax. Use command /help for getting more information";
        } else {
            var token = splitMessage[1].toUpperCase();
            if (!user.getList().contains(token))
                result = String.format("You don't have %s in your list", token);
        }
        return result;
    }
}