package com.example.flows.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    
    public MainView() {
        add(new H1("Welcome to Flows with Camunda 7"));
        add(new com.vaadin.flow.component.html.Paragraph("Spring Boot 3.2 + Vaadin 24 + Camunda 7 integration"));
    }
}
