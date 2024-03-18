package v.crypto.informer.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import v.crypto.informer.model.TokenInfo;
import v.crypto.informer.model.TokenInfoCollection;
import v.crypto.informer.service.CryptoJsonGetter;

import java.util.Objects;

@Log4j
@Service
public class CryptoJsonGetterImpl implements CryptoJsonGetter {
    private final WebClient webClient;
    private final TokenService tokenService;

    public CryptoJsonGetterImpl(WebClient webClient, TokenService tokenService) {
        this.webClient = webClient;
        this.tokenService = tokenService;
    }

    @Override
    @Scheduled(fixedDelay = 5_000)
    public void getJson() {
        TokenInfoCollection response = webClient.get()
                .retrieve()
                .bodyToMono(TokenInfoCollection.class)
                .block();
        if (Objects.requireNonNull(response).getData() != null) {
            var tokens = response.getData();
            for (TokenInfo token : tokens) {
                tokenService.updateToken(token.getSymbol(), token);
            }
        }
    }
}