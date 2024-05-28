package org.grogu.task_manager;
import org.grogu.task_manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.deleteById(1l);
    }
}
