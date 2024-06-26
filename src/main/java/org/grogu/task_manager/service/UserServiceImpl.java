package org.grogu.task_manager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.dto.RegistrationDto;
import org.grogu.task_manager.entity.Role;
import org.grogu.task_manager.entity.Task;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.repository.TaskRepository;
import org.grogu.task_manager.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void remove(Long employeeId) {
        userRepository.deleteById(employeeId);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createNewUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> getCreatedTasks(Long id) {
        User user =
                userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("Такого " +
                        "пользователя не существет : '$s'", id)));
        List<Task> list = user.getAuthorTasks();
        if (list.isEmpty()) {
            list = new ArrayList<>();
        }
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<?> getExecutingTasks(Long id) {
        User user =
                userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("Такого " +
                        "пользователя не существет : '$s'", id)));
        List<Task> list = user.getExecutorTasks();
        return ResponseEntity.ok(list);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь с '$s' не найден", email)
        ));
        log.info(user.getEmail() + " : " + user.getPassword());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Stream.of(user.getRole()).map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
        );
    }

}
