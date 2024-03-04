package v.crypto.informer.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import v.crypto.informer.data.TokenData;
import v.crypto.informer.model.TokenInfoCollection;
import v.crypto.informer.service.CryptoJsonGetter;

@Log4j
@Service
public class CryptoJsonGetterImpl implements CryptoJsonGetter {
    @Value("${token.info.url}")
    private String URL;
    private final RestTemplate restTemplate;

    public CryptoJsonGetterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Scheduled(fixedDelay = 50_000)
    public void getJson() {
//        Data.data = webClient.get()
//                .uri(URL)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
        ResponseEntity<TokenInfoCollection> response = restTemplate.exchange(URL, HttpMethod.GET, null, TokenInfoCollection.class);
        TokenData.updateData(response.getBody().getData());
    }
}
