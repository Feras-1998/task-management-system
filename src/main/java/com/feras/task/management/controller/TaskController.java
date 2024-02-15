package com.feras.task.management.controller;

import com.feras.task.management.controller.common.BaseController;
import com.feras.task.management.dto.task.TaskDto;
import com.feras.task.management.dto.task.TaskFilter;
import com.feras.task.management.dto.task.TaskResponse;
import com.feras.task.management.model.Task;
import com.feras.task.management.service.TaskService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("apis/task")
@Tag(name = "Task Management", description = "Task Management APIs")
public class TaskController extends BaseController {

    private final TaskService taskService;

    @GetMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Data retrieved successfully")})
    public List<TaskDto> findAll() {
        return taskService.findAll(getCurrentUser());
    }

    @GetMapping("/{taskId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Task Id")
    })
    private TaskDto findById(@PathVariable Long taskId) {
        return taskService.findById(getCurrentUser(), taskId);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Task Data")
    })
    private TaskResponse save(@RequestBody List<TaskDto> tasks) {
        return taskService.save(getCurrentUser(), tasks);
    }

    @DeleteMapping("/{taskId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Task Id")
    })
    private void deleteByTaskId(@PathVariable Long taskId) {
        taskService.deleteByTaskId(getCurrentUser(), taskId);
    }

    @DeleteMapping("/user-tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid User Id")
    })
    private void deleteByUserId() {
        taskService.deleteByUserId(getCurrentUser());
    }

    @PostMapping("search")
    private List<Task> searchTasks(@RequestBody TaskFilter taskFilter) {
        return taskService.searchTasks(getCurrentUser(), taskFilter);
    }
}
