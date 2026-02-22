package github.com.torloejborg.flows;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@StyleSheet(Lumo.STYLESHEET)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
