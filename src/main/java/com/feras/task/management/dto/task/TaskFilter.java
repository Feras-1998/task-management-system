package com.feras.task.management.dto.task;

import com.feras.task.management.constant.OrderByTask;
import com.feras.task.management.constant.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFilter {
    private String searchKey;
    private long dueDateFrom;
    private long dueDateTo;
    private Set<TaskStatus> status;
    private Set<OrderByTask> orderBy;
}
