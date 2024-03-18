package v.crypto.informer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import v.crypto.informer.model.TokenInfo;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<TokenInfo, String>, PagingAndSortingRepository<TokenInfo, String> {
    List<TokenInfo> findTop100ByOrderByPriceUsdDesc();
    Page<TokenInfo> findAll(Pageable pageable);
}