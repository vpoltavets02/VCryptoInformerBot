package v.crypto.informer.callback;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface Callback {
    void execute(CallbackQuery callbackQuery);
}
