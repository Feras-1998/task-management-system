package com.feras.task.management.repository.task;

import com.feras.task.management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {

    Optional<Task> findByIdAndUserId(long id, long userId);

    @Query(value = "SELECT * FROM tasks WHERE user_id = :userId", nativeQuery = true)
    List<Task> findAllByUserId(long userId);

    @Modifying
    @Query(value = "DELETE FROM tasks WHERE user_id = :userId", nativeQuery = true)
    void deleteAllByUserId(long userId);

    @Modifying
    void deleteByIdAndUserId(long id, long userId);
}
