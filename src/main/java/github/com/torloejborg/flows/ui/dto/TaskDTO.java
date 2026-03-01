package github.com.torloejborg.flows.ui.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.task.Task;

@Data
@RequiredArgsConstructor
public class TaskDTO {
    private final String id;
    private final String created;
    private final String name;
    private final String assignee;
    private final String status;
    private final String processInstanceId;


    public static TaskDTO map(Task t) {
        return new TaskDTO(t.getId(),t.getCreateTime().toString(),t.getName(),t.getAssignee(),t.getTaskState(),t.getProcessInstanceId());
    }

}
