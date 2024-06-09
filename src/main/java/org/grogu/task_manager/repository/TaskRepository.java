package org.grogu.task_manager.repository;

import org.grogu.task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM task WHERE author = ?1")
    List<Task> findAuthorTasks(Long id);
}
