package v.crypto.informer.config;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import v.crypto.informer.bot.VCryptoInformerBot;

@Log4j
@Configuration
@EnableScheduling
public class VCryptoInformerBotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(VCryptoInformerBot bot) {
        TelegramBotsApi api = null;
        log.info("Bot starting");
        try {
            api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error("Something went wrong while bot starting", e);
        }
        log.info("Bot was successfully started");
        return api;
    }
}