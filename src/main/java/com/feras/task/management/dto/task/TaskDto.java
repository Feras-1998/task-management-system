package com.feras.task.management.dto.task;

import com.feras.task.management.constant.TaskStatus;
import com.feras.task.management.model.Task;
import com.feras.task.management.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private long id;
    private String title;
    private String description;
    private long dueDate;
    private TaskStatus status;

    public boolean isInValidData() {
        return Util.isEmpty(title) || dueDate <= 0 || status == null;
    }

    public static List<TaskDto> mapperTaskToTaskDto(List<Task> tasks) {
        if (tasks == null) {
            return new ArrayList<>();
        }

        return tasks.stream().map(TaskDto::mapperTaskToTaskDto).toList();
    }

    public static TaskDto mapperTaskToTaskDto(Task task) {
        if (task == null) {
            return null;
        }

        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .build();
    }

    public static List<Task> mapperTaskDtoToTask(List<TaskDto> tasksDto) {
        if (tasksDto == null) {
            return new ArrayList<>();
        }

        return tasksDto.stream().map(TaskDto::mapperTaskDtoToTask).toList();
    }

    public static Task mapperTaskDtoToTask(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .dueDate(taskDto.getDueDate())
                .status(taskDto.getStatus())
                .build();
    }
}
