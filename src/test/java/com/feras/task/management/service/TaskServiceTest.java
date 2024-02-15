package com.feras.task.management.service;

import com.feras.task.management.dto.task.TaskDto;
import com.feras.task.management.dto.task.TaskResponse;
import com.feras.task.management.exception.common.exceptions.InvalidDataException;
import com.feras.task.management.exception.database.exceptions.RecordNotFoundException;
import com.feras.task.management.model.Task;
import com.feras.task.management.model.User;
import com.feras.task.management.repository.UserRepository;
import com.feras.task.management.repository.task.TaskRepository;
import com.feras.task.management.util.TestUtils;
import com.feras.task.management.util.task.TaskUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class TaskServiceTest extends TestUtils {
    private final User currentUser = User.builder().id(1L).username("feras").build();
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testFindAll_Success() {
        long userId = 1L;
        List<Task> tasks = TaskUtils.prepareTaskList(generateRandomNumber());
        when(taskRepository.findAllByUserId(userId)).thenReturn(tasks);

        List<TaskDto> result = taskService.findAll(currentUser);

        assertEquals(tasks.size(), result.size());
    }

    @Test
    void testFindById_InvalidTaskId() {
        assertThrows(InvalidDataException.class, () -> taskService.findById(currentUser, -1L));
    }

    @Test
    void testFindById_RecordNotFound() {
        long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> taskService.findById(currentUser, taskId));
    }

    @Test
    void testFindById_Success() {
        long taskId = 1L;
        Task task = TaskUtils.prepareTask(0);
        when(taskRepository.findByIdAndUserId(currentUser.getId(), taskId)).thenReturn(Optional.of(task));

        TaskDto result = taskService.findById(currentUser, taskId);

        assertNotNull(result);
    }

    @Test
    void testSave_InvalidTaskData() {
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto()); // This task data is considered invalid
        assertThrows(InvalidDataException.class, () -> taskService.save(currentUser, tasksDto));
    }

    @Test
    void testSave_Success() {
        long userId = 1L;
        int listSize = generateRandomNumber();
        List<TaskDto> tasksDto = TaskUtils.prepareTaskDtoList(listSize);

        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Task task = new Task();
        when(taskRepository.saveAll(any())).thenReturn(List.of(task));

        TaskResponse response = taskService.save(currentUser, tasksDto);

        assertNotNull(response);
        assertEquals(listSize, response.getTasks().size());
    }

    @Test
    void testDeleteByTaskId_InvalidTaskId() {
        assertThrows(InvalidDataException.class, () -> taskService.deleteByTaskId(currentUser, -1L));
    }

    @Test
    void testDeleteByTaskId_Success() {
        long taskId = 1L;
        taskService.deleteByTaskId(currentUser, taskId);
        verify(taskRepository, times(1)).deleteByIdAndUserId(currentUser.getId(), taskId);
    }

    @Test
    void testDeleteByUserId_Success() {
        long userId = 1L;
        taskService.deleteByUserId(currentUser);
        verify(taskRepository, times(1)).deleteAllByUserId(userId);
    }

}
