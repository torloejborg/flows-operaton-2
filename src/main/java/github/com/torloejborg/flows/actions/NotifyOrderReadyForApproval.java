package github.com.torloejborg.flows.actions;

import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("NotifyOrderReadyForApproval")
public class NotifyOrderReadyForApproval implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("NotifyOrderReadyForApproval!");
    }
}
