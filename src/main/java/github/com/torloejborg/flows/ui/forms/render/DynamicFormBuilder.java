package github.com.torloejborg.flows.ui.forms.render;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.dom.Style;
import github.com.torloejborg.flows.ui.forms.model.FormComponent;
import github.com.torloejborg.flows.ui.forms.model.JsonForm;
import github.com.torloejborg.flows.ui.forms.model.ValueItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DynamicFormBuilder {

    private final LinkedHashMap<String, List<FormComponent>> rows = new LinkedHashMap<>();
    private final Map<String, FormField> fields = new LinkedHashMap<>();

    public FormLayout doLayout(JsonForm jsonForm) {

        // builds a row-like structure, we can have many components on each row
        for (FormComponent component : jsonForm.components) {
            rows.putIfAbsent(component.layout.row, new ArrayList<>());
            rows.get(component.layout.row).add(component);
        }

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 12)
        );

        for (String row : rows.keySet()) {
            List<FormComponent> components = rows.get(row);

            for (FormComponent component : components) {

                Component widget = getWidget(component);
                if (widget instanceof Text) {
                    formLayout.add(widget);
                } else {
                    widget.setId(component.id);
                    formLayout.add(widget);
                    int colspan = calculateColspan(component,components.size());
                    formLayout.setColspan(widget, colspan);
                }
            }
        }

        return formLayout;
    }

    public Map<String, FormField> getFields() {
        return fields;
    }

    private int calculateColspan(
            FormComponent component,
            int componentsInRow
    ) {
        if (component.layout.column == 0) {
            int TOTAL_COLUMNS = 12;
            return TOTAL_COLUMNS / componentsInRow;
        }
        return component.layout.column;
    }


    private Component getWidget(FormComponent formComponent) {

        return switch (formComponent.type) {
            case "textfield" -> getTextField(formComponent);
            case "text" -> getText(formComponent);
            case "radio" -> getRadioButtonGroup(formComponent);
            case "number" -> getNumberField(formComponent);
            case "textarea" -> getTextArea(formComponent);
            case "button" -> getButton(formComponent);
            case "datetime" -> getDatePicker(formComponent);
            case "spacer" -> getSpacer(formComponent);
            case "checkbox" -> getCheckbox(formComponent);
            default -> new Text("?");
        };
    }

    private TextField getTextField(FormComponent formComponent) {

        TextField widget = new TextField(formComponent.label);
        widget.setLabel(formComponent.label);
        widget.setRequired(FormHelpers.isRequired(formComponent));

        fields.put(
                formComponent.key,
                new FormField(
                        formComponent.key,
                        widget::getValue
                )
        );

        return widget;
    }

    private RadioButtonGroup<String> getRadioButtonGroup(FormComponent formComponent) {
        RadioButtonGroup<String> group = new RadioButtonGroup<>(formComponent.label);
        group.setLabel(formComponent.label);
        group.setRequired(FormHelpers.isRequired(formComponent));

        Map<String, String> items = formComponent.values.stream().collect(Collectors.toMap(ValueItem::getValue, ValueItem::getLabel));
        group.setItems(items.keySet());
        group.setItemLabelGenerator(items::get);

        fields.put(
                formComponent.key,
                new FormField(
                        formComponent.key,
                        group::getValue
                )
        );

        return group;
    }


    private NumberField getNumberField(FormComponent formComponent) {
        NumberField widget = new NumberField(formComponent.label);

        fields.put(
                formComponent.key,
                new FormField(
                        formComponent.key,
                        widget::getValue
                )
        );

        return widget;
    }

    private TextArea getTextArea(FormComponent formComponent) {
        TextArea widget = new TextArea(formComponent.label);
        widget.setLabel(formComponent.label);
        widget.setRequired(FormHelpers.isRequired(formComponent));

        fields.put(
                formComponent.key,
                new FormField(
                        formComponent.key,
                        widget::getValue
                )
        );

        return widget;
    }

    private Component getText(FormComponent formComponent) {

        if (formComponent.text == null) {
            return new Text("");
        } else {

            String txt = formComponent.text.replaceAll("#", "");
            if (formComponent.text.contains("##")) {
                return new H2(txt);
            }
            else if (formComponent.text.contains("#")) {
                return new H1(txt);
            } else {
                return new Text(formComponent.text);
            }
        }



    }

    private Button getButton(FormComponent formComponent) {
        Button button = new Button(formComponent.label);
        button.setEnabled(FormHelpers.isEnabled(formComponent));
        return button;
    }

    private DatePicker getDatePicker(FormComponent formComponent) {
        DatePicker datePicker = new DatePicker();
        datePicker.setLabel(formComponent.dateLabel);
        datePicker.setOpened(false);
        return datePicker;
    }

    private Checkbox getCheckbox(FormComponent formComponent) {
        Checkbox widget = new Checkbox(formComponent.label);
        widget.setLabel(formComponent.label);

        fields.put(
                formComponent.key,
                new FormField(
                        formComponent.key,
                        widget::getValue
                )
        );

        return widget;
    }

    private Text getSpacer(FormComponent formComponent) {
        Div div = new Div();
        Text text = new Text("");
        return text;
    }

}
