package v.crypto.informer.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import v.crypto.informer.model.TokenInfo;
import v.crypto.informer.repository.TokenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public List<TokenInfo> findAllTokens() {
        return tokenRepository.findAll();
    }

    public List<TokenInfo> findTop() {
        return tokenRepository.findTop100ByOrderByPriceUsdDesc();
    }

    public Optional<TokenInfo> findTokenById(String id) {
        return tokenRepository.findById(id);
    }

    @Transactional
    public void saveToken(TokenInfo token) {
        tokenRepository.save(token);
    }

    @Transactional
    public void updateToken(String id, TokenInfo tokenToUpdate) {
        Optional<TokenInfo> optionalToken = findTokenById(id);
        if (optionalToken.isPresent()) {
            var token = optionalToken.get();
            token.setName(tokenToUpdate.getName());
            token.setPriceUsd(String.valueOf(tokenToUpdate.getPriceUsd()));
            saveToken(token);
        } else {
            saveToken(tokenToUpdate);
        }
    }
}