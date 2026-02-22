package github.com.torloejborg.flows.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

@Route("")
public class HomeView extends VerticalLayout {

    private final RuntimeService runtimeService;

    public HomeView(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;

        add(new H1("The Widget factory"));

        add(new HorizontalLayout(
                new Button("Produce a V1 widget", e -> startWizard("wizard-v1")),
                new Button("Produce a V2 widget", e -> startWizard("wizard-v2"))

        ));

        Button newOrders = new Button("Current Orders", e->{
            UI.getCurrent().navigate(WidgetOrderView.class);
        });


        add(newOrders);
    }

    private void startWizard(String flowName) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(flowName);

        UI.getCurrent().navigate(
                WizardTaskView.class,
                pi.getId()
        );
    }
}
