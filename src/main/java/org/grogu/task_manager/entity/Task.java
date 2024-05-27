package org.grogu.task_manager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "head")
    private String head;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "priority")
    private String priority;

    @ManyToOne
    @JoinColumn(name = "author")
    private Employee author;

    @ManyToOne
    @JoinColumn(name = "executor")
    private Employee executor;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Task() {
    }

    public Task(String head, String description, String status, String priority, Employee author, Employee executor, List<Comment> comments) {
        this.head = head;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.executor = executor;
        this.comments = comments;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public Employee getExecutor() {
        return executor;
    }

    public void setExecutor(Employee executor) {
        this.executor = executor;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
