package github.com.torloejborg.flows.actions;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PersistData implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Persisting data!!");

        var variables = execution.getVariables();

        variables.keySet().forEach( k -> {
            System.out.println("\t"+k+": "+variables.get(k));
        });


    }
}
