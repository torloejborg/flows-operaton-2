package github.com.torloejborg.flows.ui.forms.model;

public enum ComponentType {
    TEXTBOX("textbox"),
    CHECKBOX("checkbox"),
    NUMBER("number"),
    RADIO("radio"),
    TEXTAREA("textarea");

    private final String value;

    ComponentType(String value) {
        this.value = value;
    }
}
