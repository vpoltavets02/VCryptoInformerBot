package v.crypto.informer.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import v.crypto.informer.service.impl.TokenService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Keyboards {
    private final TokenService tokenService;

    public Keyboards(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public InlineKeyboardMarkup getAddMarkup() {
        int counter = 0;
        var tokens = tokenService.findTop();
        List<InlineKeyboardButton> row;
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            row = new ArrayList<>();
            InlineKeyboardButton button;
            for (int j = 0; j < 5; j++) {
                var symbol = tokens.get(counter).getSymbol();
                button = new InlineKeyboardButton(symbol);
                button.setCallbackData(String.format("%s add", symbol));
                row.add(button);
                counter++;
            }
            rows.add(row);
        }
        return new InlineKeyboardMarkup(rows);
    }

    public InlineKeyboardMarkup getRemoveMarkup(List<String> list) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String token : list) {
            InlineKeyboardButton button = new InlineKeyboardButton(token);
            button.setCallbackData(String.format("%s remove", token));
            row.add(button);
        }
        rows.add(row);
        return new InlineKeyboardMarkup(rows);
    }
}