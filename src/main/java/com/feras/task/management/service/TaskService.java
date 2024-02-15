package com.feras.task.management.service;

import com.feras.task.management.dto.task.TaskDto;
import com.feras.task.management.dto.task.TaskFilter;
import com.feras.task.management.dto.task.TaskResponse;
import com.feras.task.management.exception.common.exceptions.InvalidDataException;
import com.feras.task.management.exception.database.exceptions.RecordNotFoundException;
import com.feras.task.management.model.Task;
import com.feras.task.management.model.User;
import com.feras.task.management.repository.task.TaskRepository;
import com.feras.task.management.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskDto> findAll(User user) {
        return TaskDto.mapperTaskToTaskDto(taskRepository.findAllByUserId(user.getId()));
    }

    public TaskDto findById(User user, long taskId) {
        if (Util.isInValidId(taskId)) {
            throw new InvalidDataException();
        }

        return TaskDto.mapperTaskToTaskDto(
                taskRepository
                        .findByIdAndUserId(taskId, user.getId())
                        .orElseThrow(() -> new RecordNotFoundException("Task", taskId)));
    }

    @Transactional
    public TaskResponse save(User user, List<TaskDto> tasksDto) {
        if (tasksDto.stream().anyMatch(TaskDto::isInValidData)) {
            throw new InvalidDataException();
        }

        List<Task> tasks = TaskDto.mapperTaskDtoToTask(tasksDto);
        tasks.forEach(task -> task.setUser(user));
        taskRepository.saveAll(tasks);
        return new TaskResponse(TaskDto.mapperTaskToTaskDto(tasks));
    }

    @Transactional
    public void deleteByTaskId(User user, long taskId) {
        if (Util.isInValidId(taskId)) {
            throw new InvalidDataException();
        }

        taskRepository.deleteByIdAndUserId(taskId, user.getId());
    }

    @Transactional
    public void deleteByUserId(User user) {
        taskRepository.deleteAllByUserId(user.getId());
    }

    public List<Task> searchTasks(User user, TaskFilter taskFilter) {
        return taskRepository.searchTasks(user.getId(), taskFilter);
    }
}
