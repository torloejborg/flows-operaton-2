package github.com.torloejborg.flows.ui.forms;

import github.com.torloejborg.flows.ui.forms.model.JsonForm;
import github.com.torloejborg.flows.ui.forms.model.JsonFormReader;
import github.com.torloejborg.flows.ui.forms.render.DynamicFormBuilder;
import github.com.torloejborg.flows.ui.forms.render.FormField;
import com.vaadin.flow.component.formlayout.FormLayout;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class FormRepository {

    DynamicFormBuilder dynamicFormBuilder = new DynamicFormBuilder();

    public FormLayout render(String formId)  {
        JsonFormReader jsonFormReader = new JsonFormReader();

        JsonForm jsonForm = null;
        try {

            if (formId.contains("_")) {
                formId = formId.replaceAll("_","/");
            }

            jsonForm = jsonFormReader.readJsonForm(new ClassPathResource("forms/"+formId+".form").getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dynamicFormBuilder.doLayout(jsonForm);
    }

    public Map<String, FormField> getFields() {
        return dynamicFormBuilder.getFields();
    }

}
