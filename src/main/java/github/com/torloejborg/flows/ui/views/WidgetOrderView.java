package github.com.torloejborg.flows.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import github.com.torloejborg.flows.model.User;
import github.com.torloejborg.flows.repsitories.UserRepository;
import github.com.torloejborg.flows.ui.dto.TaskDTO;
import org.camunda.bpm.engine.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Route("/widgetOrders")
public class WidgetOrderView extends VerticalLayout {

    public WidgetOrderView(TaskService taskService, UserRepository userRepository) {
        List<TaskDTO> readyForReview = taskService
                .createTaskQuery()
                .processDefinitionName("ReviewWidgetOrder")
                .taskUnassigned()
                .list().stream().map(
                        TaskDTO::map
                ).collect(Collectors.toList());

        add(new H1("Assign Approval process to user"));

        Grid<TaskDTO> grid = new Grid<>(TaskDTO.class);
        grid.setItems(readyForReview);
        final GridContextMenu<TaskDTO> menu = grid.addContextMenu();

        for (User user : userRepository.findAll()) {

            menu.addItem("Assign to " + user.getName(), e -> {
                String taskId = e.getItem().get().getId();
                taskService.setAssignee(taskId, user.getName());

                UI.getCurrent().navigate(HomeView.class);

            });

        }


        add(grid);


    }

}
