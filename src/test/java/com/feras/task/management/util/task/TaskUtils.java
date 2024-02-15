package com.feras.task.management.util.task;

import com.feras.task.management.constant.TaskStatus;
import com.feras.task.management.dto.task.TaskDto;
import com.feras.task.management.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskUtils {

    public static List<Task> prepareTaskList(int numberOfTasks) {
        List<Task> result = new ArrayList<>();
        for (int index = 0; index < numberOfTasks; index++) {
            result.add(prepareTask(index));
        }
        return result;
    }

    public static Task prepareTask(int index) {
        return Task.builder()
                .id(index + 1)
                .title("Title " + index)
                .description("Description" + index)
                .dueDate(System.currentTimeMillis())
                .status(TaskStatus.TODO)
                .build();
    }

    public static List<TaskDto> prepareTaskDtoList(int numberOfTasks) {
        List<TaskDto> result = new ArrayList<>();
        for (int index = 0; index < numberOfTasks; index++) {
            result.add(prepareTaskDto(index));
        }
        return result;
    }

    public static TaskDto prepareTaskDto(int index) {
        return TaskDto.builder().id(index + 1).title("Title " + index).description("Description" + index).dueDate(System.currentTimeMillis()).status(TaskStatus.TODO).build();
    }

}
