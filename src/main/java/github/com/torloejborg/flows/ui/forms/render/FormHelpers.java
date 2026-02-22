package github.com.torloejborg.flows.ui.forms.render;


import github.com.torloejborg.flows.ui.forms.model.FormComponent;

public class FormHelpers {

    public static boolean isEnabled(FormComponent formComponent) {
        return true;
    }

    public static boolean isRequired(FormComponent formComponent) {
        if (formComponent.validate == null) {
            return false;
        }

        return formComponent.validate.required;
    }

}
