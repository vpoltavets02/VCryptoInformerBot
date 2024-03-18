package v.crypto.informer.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import v.crypto.informer.model.TokenInfo;
import v.crypto.informer.service.impl.TokenService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class KeyboardMarkups {
    private final TokenService tokenService;

    public KeyboardMarkups(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public ReplyKeyboardMarkup getReplyKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>(
                List.of(
                        new KeyboardRow(List.of(new KeyboardButton("➕Add"), new KeyboardButton("➖Remove"),
                                new KeyboardButton("\uD83D\uDC64Profile"))),
                        new KeyboardRow(List.of(new KeyboardButton("\uD83D\uDCCAStatistic"),
                                new KeyboardButton("\uD83D\uDD14Notifications"), new KeyboardButton("❓Help")))
                )
        );
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(rows);
        markup.setResizeKeyboard(true);
        return markup;
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

    public InlineKeyboardMarkup getAddMarkup(String page) {
        return getPageInlineKeyboardMarkup(Integer.parseInt(String.valueOf(page.charAt(page.length() - 1))));
    }

    private InlineKeyboardMarkup getPageInlineKeyboardMarkup(int pageNum) {
        int counter = 0;
        InlineKeyboardButton button;
        List<InlineKeyboardButton> row;
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<TokenInfo> tokens = tokenService.getTokensPage(pageNum).stream().toList();
        for (int i = 0; i < 5; i++) {
            row = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                var symbol = tokens.get(counter).getSymbol();
                counter++;
                button = new InlineKeyboardButton(symbol);
                button.setCallbackData(String.format("%s add", symbol));
                row.add(button);
            }
            rows.add(row);
        }
        if (pageNum == 0) {
            button = new InlineKeyboardButton(">>");
            button.setCallbackData("page1 add");
            rows.add(Collections.singletonList(button));
        } else if (pageNum == 9) {
            button = new InlineKeyboardButton("<<");
            button.setCallbackData("page8 add");
            rows.add(Collections.singletonList(button));
        } else {
            row = new ArrayList<>();
            button = new InlineKeyboardButton("<<");
            button.setCallbackData(String.format("page%d add", pageNum - 1));
            row.add(button);
            button = new InlineKeyboardButton(">>");
            button.setCallbackData(String.format("page%d add", pageNum + 1));
            row.add(button);
            rows.add(row);
        }
        return new InlineKeyboardMarkup(rows);
    }
}