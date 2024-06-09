package org.grogu.task_manager.repository;

import org.grogu.task_manager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.task where c.id = :id")
    Comment findByIdFetchTask(@Param("id") Long id);
}
