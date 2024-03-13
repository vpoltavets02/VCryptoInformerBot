package v.crypto.informer.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import v.crypto.informer.model.User;
import v.crypto.informer.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

    public List<User> findByNotificationsTrue() {
        return repository.findByNotificationsTrue();
    }

    @Transactional
    public void save(User userToSave) {
        repository.save(userToSave);
    }

    @Transactional
    public void updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = findUserById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            user.setList(updatedUser.getList());
            user.setNotifications(updatedUser.getNotifications());
            save(user);
        }
    }
}