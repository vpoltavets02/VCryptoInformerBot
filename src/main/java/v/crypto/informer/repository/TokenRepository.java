package v.crypto.informer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v.crypto.informer.model.TokenInfo;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<TokenInfo, String> {
    List<TokenInfo> findTop100ByOrderByPriceUsdDesc();
}