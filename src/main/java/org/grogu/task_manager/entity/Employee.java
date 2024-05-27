package org.grogu.task_manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Task> authorTasks = new ArrayList<>();

    @OneToMany(mappedBy = "executor", cascade = CascadeType.REMOVE)
    private List<Task> executorTasks = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getAuthorTasks() {
        return authorTasks;
    }

    public void setAuthorTasks(List<Task> authorTasks) {
        this.authorTasks = authorTasks;
    }

    public List<Task> getExecutorTasks() {
        return executorTasks;
    }

    public void setExecutorTasks(List<Task> executorTasks) {
        this.executorTasks = executorTasks;
    }
}
