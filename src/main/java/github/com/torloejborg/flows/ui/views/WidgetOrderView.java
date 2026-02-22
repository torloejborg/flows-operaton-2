package github.com.torloejborg.flows.ui.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import github.com.torloejborg.flows.ui.dto.TaskDTO;
import org.camunda.bpm.engine.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Route("/widgetOrders")
public class WidgetOrderView extends VerticalLayout {



    public WidgetOrderView (TaskService taskService) {
        List<TaskDTO> readyForReview = taskService
                .createTaskQuery()
                .processDefinitionName("ReviewWidgetOrder")
                .taskUnassigned()
                .list().stream().map(t-> {
                    return new TaskDTO(t.getCreateTime().toString(),t.getProcessDefinitionId(),t.getAssignee(),t.getTaskState());
                }).collect(Collectors.toList());

       Grid<TaskDTO> grid = new Grid<>(TaskDTO.class);
       grid.setItems(readyForReview);
       add(grid);

        System.out.println("there is " +  readyForReview.size() + " flows ready for review");


    }

}
