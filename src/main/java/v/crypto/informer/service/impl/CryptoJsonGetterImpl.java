package v.crypto.informer.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import v.crypto.informer.data.TokenData;
import v.crypto.informer.model.TokenInfoCollection;
import v.crypto.informer.service.CryptoJsonGetter;

import java.util.Objects;

@Log4j
@Service
public class CryptoJsonGetterImpl implements CryptoJsonGetter {
    private final WebClient webClient;

    public CryptoJsonGetterImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Scheduled(fixedDelay = 50_000)
    public void getJson() {
        TokenInfoCollection response = webClient.get()
                .retrieve()
                .bodyToMono(TokenInfoCollection.class)
                .block();
        TokenData.updateData(Objects.requireNonNull(response).getData());
    }
}