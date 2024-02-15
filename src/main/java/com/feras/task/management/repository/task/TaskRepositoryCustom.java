package com.feras.task.management.repository.task;

import com.feras.task.management.dto.task.TaskFilter;
import com.feras.task.management.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryCustom {
    List<Task> searchTasks(long userId, TaskFilter taskFilter);
}
