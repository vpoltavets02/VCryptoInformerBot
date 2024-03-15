package v.crypto.informer.callback;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import v.crypto.informer.callback.enums.CallbackName;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;

@Component
public class CallbackContainer {
    private final ImmutableMap<String, Callback> callbackQueries;

    public CallbackContainer(UserService userService, SendBotMessageService sendBotMessageService) {
        callbackQueries = ImmutableMap.<String, Callback>builder()
                .put(CallbackName.ADD.getCallbackName(), new AddCallback(userService, sendBotMessageService))
                .put(CallbackName.REMOVE.getCallbackName(), new RemoveCallback(userService, sendBotMessageService))
                .build();
    }

    public Callback retrieveCallback(String callbackName) {
        return callbackQueries.get(callbackName);
    }
}