package github.com.torloejborg.flows.ui.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import github.com.torloejborg.flows.repsitories.UserName;
import github.com.torloejborg.flows.service.TaskQueryService;
import github.com.torloejborg.flows.ui.dto.TaskDTO;
import org.operaton.bpm.engine.RuntimeService;
import org.operaton.bpm.engine.runtime.ProcessInstance;

import java.util.List;

@Route("")
public class HomeView extends VerticalLayout {

    private final RuntimeService runtimeService;

    public HomeView(
            RuntimeService runtimeService,
            TaskQueryService taskService) {
        this.runtimeService = runtimeService;

        add(new H1("The Widget factory"));

        add(new HorizontalLayout(
                new Button("Produce a V1 widget", e -> startWizard("wizard-v1")),
                new Button("Produce a V2 widget", e -> startWizard("wizard-v2"))

        ));

        Button newOrders = new Button("Goto Unassigned Orders", e -> {
            UI.getCurrent().navigate(WidgetOrderView.class);
        });


        add(newOrders);

        add(new Text("My tasks"));
        List<TaskDTO> myTasks = taskService.getTasksForUser(UserName.USER_ADMIN.name);
        Grid<TaskDTO> grid = new Grid<>(TaskDTO.class);
        grid.setItems(myTasks);
        grid.setHeight(15, Unit.EM);
        var gcm = grid.addContextMenu();
        gcm.addItem("Take", c -> {
            System.out.println("redirect fo flow");
            var pid = c.getItem().get().getProcessInstanceId();
            UI.getCurrent().navigate(WizardTaskView.class, pid);
        });


        add(grid);

        add(new Text("Tasks assigned to other users"));
        List<TaskDTO> otherTasks = taskService.getTasksForUserExcept(UserName.USER_ADMIN.name);
        Grid<TaskDTO> gridOthers = new Grid<>(TaskDTO.class);
        gridOthers.setItems(otherTasks);
        gridOthers.setHeight(15, Unit.EM);
        add(gridOthers);

    }

    private void startWizard(String flowName) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(flowName);

        UI.getCurrent().navigate(
                WizardTaskView.class,
                pi.getId()
        );
    }
}
