package v.crypto.informer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import v.crypto.informer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}