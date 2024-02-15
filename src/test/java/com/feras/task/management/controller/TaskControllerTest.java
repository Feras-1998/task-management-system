//package com.feras.task.management.controller;
//
//import com.feras.task.management.config.JwtUtil;
//import com.feras.task.management.dto.task.TaskDto;
//import com.feras.task.management.dto.task.TaskRequest;
//import com.feras.task.management.dto.task.TaskResponse;
//import com.feras.task.management.model.User;
//import com.feras.task.management.service.TaskService;
//import com.feras.task.management.util.task.TaskUtils;
//import com.feras.task.management.util.task.TestUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//@Slf4j
//public class TaskControllerTest extends TestUtils {
//    private final User currentUser = User.builder().id(1L).username("feras").build();
//
//    @Mock
//    private JwtUtil jwtUtil;
//    //    private final JwtUtil jwtUtil = new JwtUtil();
//    @Mock
//    private TaskService taskService;
//    @InjectMocks
//    private TaskController taskController;
//
//    @BeforeEach
//    void setUp() {
//        openMocks(this);
//
//        JwtUtil jwtUtil = mock(JwtUtil.class);
//
//        String token = generateRandomToken();
//
//        when(jwtUtil.extractUsername(token)).thenReturn(USERNAME);
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.addHeader("Authorization", "Bearer " + token);
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//    }
//
//    @Test
//    void testFindAll() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        List<TaskDto> expectedTasks = TaskUtils.prepareTaskDtoList(generateRandomNumber());
//
//        when(taskService.findAll(currentUser)).thenReturn(expectedTasks);
//        when(taskController.getCurrentUser()).thenReturn(currentUser);
//
//        List<TaskDto> actualTasks = invokePrivateDeclaredMethod(TaskController.class, "findAll", new Class[]{}, taskController);
//
//        assertEquals(expectedTasks, actualTasks);
//        verify(taskService, times(1)).findAll(currentUser);
//    }
//
//    @Test
//    void testFindById() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        long taskId = 1;
//        TaskDto expectedTask = TaskUtils.prepareTaskDto(0);
//
//        when(taskService.findById(currentUser, taskId)).thenReturn(expectedTask);
//
//        TaskDto actualTask = invokePrivateDeclaredMethod(TaskController.class, "findById", new Class[]{Long.class}, taskController, taskId);
//
//        assertEquals(expectedTask, actualTask);
//
//        verify(taskService, times(1)).findById(currentUser, taskId);
//    }
//
//    @Test
//    void testSave() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        long userId = 1;
//        int listSize = generateRandomNumber();
//        TaskRequest taskRequest = new TaskRequest(userId, TaskUtils.prepareTaskDtoList(listSize));
//        TaskResponse expectedResponse = new TaskResponse(TaskUtils.prepareTaskDtoList(listSize));
//
//        when(taskService.save(currentUser, taskRequest.getTasks())).thenReturn(expectedResponse);
//
//        TaskResponse actualResponse = invokePrivateDeclaredMethod(TaskController.class, "save", new Class[]{TaskRequest.class}, taskController, taskRequest);
//
//        assertEquals(expectedResponse, actualResponse);
//        verify(taskService, times(1)).save(currentUser, taskRequest.getTasks());
//    }
//
//    @Test
//    void testDeleteByTaskId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        long taskId = 1L;
//        invokePrivateDeclaredMethod(TaskController.class, "deleteByTaskId", new Class[]{Long.class}, taskController, taskId);
//        verify(taskService, times(1)).deleteByTaskId(currentUser, taskId);
//    }
//
//    @Test
//    void testDeleteByUserId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        long userId = 1L;
//        invokePrivateDeclaredMethod(TaskController.class, "deleteByUserId", new Class[]{Long.class}, taskController, userId);
//        verify(taskService, times(1)).deleteByUserId(currentUser);
//    }
//}
