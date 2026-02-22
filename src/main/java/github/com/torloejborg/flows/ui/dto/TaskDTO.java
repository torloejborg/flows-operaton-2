package github.com.torloejborg.flows.ui.dto;

public class TaskDTO {
    private String created;
    private String name;
    private String assignee;
    private String status;


    public TaskDTO(String created, String name, String assignee, String status) {
        this.created = created;
        this.name = name;
        this.assignee = assignee;
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
