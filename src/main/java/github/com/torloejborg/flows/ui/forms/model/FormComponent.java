package github.com.torloejborg.flows.ui.forms.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Map;

@JsonRootName("component")
public class FormComponent {
        public String label;
        public String type;
        public String subtype;
        public String text;
        public Layout layout;
        public String id;
        public String key;
        public Validate validate;
        public Map<String,String> properties;
        public List<ValueItem> values;
        public String description;
}
