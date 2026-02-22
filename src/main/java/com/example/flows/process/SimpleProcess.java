package com.example.flows.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class SimpleProcess implements JavaDelegate {
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Simple process executed with variable: " + execution.getVariable("message"));
    }
}
