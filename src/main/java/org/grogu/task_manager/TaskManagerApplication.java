package org.grogu.task_manager;

import org.grogu.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TaskManagerApplication {
    @Autowired
    private UserRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        employeeRepository.deleteById(1l);
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                .build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("123"))
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }



}
