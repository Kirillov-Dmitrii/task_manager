package org.grogu.task_manager.service;

import jakarta.persistence.EnumType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grogu.task_manager.entity.User;
import org.grogu.task_manager.entity.Role;
import org.grogu.task_manager.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository employeeRepository;


    public void remove(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Optional<User> findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<User> get(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public User createNewUser(User employee) {
        employee.setRole(Role.USER);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findEmployeeByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь с '$s' не найден", email)
        ));
        log.info(user.getEmail() + " : " + user.getPassword());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Arrays.stream(EnumType.values()).map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
        );
    }

}
