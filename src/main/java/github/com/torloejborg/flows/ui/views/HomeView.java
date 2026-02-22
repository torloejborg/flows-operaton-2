package github.com.torloejborg.flows.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
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
        Button start = new Button("Get a widget here", e -> startWizard());
        add(start);
    }

    private void startWizard() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("wizard-v1");

        UI.getCurrent().navigate(
                WizardTaskView.class,
                pi.getId()
        );
    }
}
