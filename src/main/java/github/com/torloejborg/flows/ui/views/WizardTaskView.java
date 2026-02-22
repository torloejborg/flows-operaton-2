package github.com.torloejborg.flows.ui.views;

import github.com.torloejborg.flows.ui.forms.FormRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Route("wizard/{processInstanceId}")
public class WizardTaskView extends VerticalLayout
        implements HasUrlParameter<String> {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    private FormRepository formRepository; // your JSON loader

    private Task task;

    @Override
    public void setParameter(
            BeforeEvent event,
            @OptionalParameter String processInstanceId
    ) {
        removeAll();
        formRepository = new FormRepository();
        task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .initializeFormKeys()
                .active()
                .singleResult();

        if (task == null) {
            add(new H2("Wizard completed - Thank your for your order!"));
            return;
        }

        renderForm(processInstanceId);
    }

    private void renderForm(String processInstanceId) {

        String formKey = task.getFormKey();

        Button submit = new Button("Next", e -> submitForm(processInstanceId));
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(
                new H3(task.getName()),
                formRepository.render(formKey),
                submit
        );
    }

    private void submitForm(String processInstanceId) {

        Map<String, Object> variables = new HashMap<>();

        formRepository.getFields().values().forEach(field ->
                variables.put(field.key(), field.valueSupplier().get())
        );

        taskService.complete(task.getId(), variables);

        UI.getCurrent().navigate(
                WizardTaskView.class,
                processInstanceId
        );
    }
}
