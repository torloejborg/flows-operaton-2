package github.com.torloejborg.flows.service;

import github.com.torloejborg.flows.ui.dto.TaskDTO;
import org.camunda.bpm.engine.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskQueryService {

    private final TaskService taskService;

    public TaskQueryService(TaskService taskService) {
        this.taskService = taskService;
    }

    public List<TaskDTO> getTasksForUser(String userId) {
        return taskService.createTaskQuery()
                .taskAssignee(userId)
                .list()
                .stream()
                .filter(task -> task.getAssignee()!=null)
                .map(TaskDTO::map)
                .toList();
    }

    public List<TaskDTO> getTasksForUserExcept(String userId) {
        return taskService.createTaskQuery()
                .list()
                .stream()
                .filter(task -> task.getAssignee()!=null)
                .filter(task -> !task.getAssignee().equals(userId))
                .map(TaskDTO::map)
                .toList();
    }

}
