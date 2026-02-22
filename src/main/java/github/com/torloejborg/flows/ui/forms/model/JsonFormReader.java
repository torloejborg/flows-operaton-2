package github.com.torloejborg.flows.ui.forms.model;

import tools.jackson.databind.json.JsonMapper;

import java.io.InputStream;

@org.springframework.stereotype.Component
public class JsonFormReader {
    private final JsonMapper mapper = new JsonMapper();

    public JsonForm readJsonForm(String json) {
        return mapper.readValue(json, JsonForm.class);
    }

    public JsonForm readJsonForm(InputStream inputStream) {
        return mapper.readValue(inputStream, JsonForm.class);
    }


}
