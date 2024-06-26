package org.grogu.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonBackReference
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor")
    @JsonBackReference
    private User executor;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();
}
