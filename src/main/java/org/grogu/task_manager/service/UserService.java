package org.grogu.task_manager.service;

import org.grogu.task_manager.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findUserByEmail(String email);
    List<User> getAll();
    Optional<User> get(Long id);

    User createNewUser(User employee);

}
