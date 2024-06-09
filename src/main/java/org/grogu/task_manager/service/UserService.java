package org.grogu.task_manager.service;

import org.grogu.task_manager.dto.RegistrationDto;
import org.grogu.task_manager.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    List<User> getAll();
    Optional<User> get(Long id);
    User createNewUser(RegistrationDto registrationDto);

    ResponseEntity<?> getCreatedTasks(Long id);

    ResponseEntity<?> getExecutingTasks(Long id);
}
