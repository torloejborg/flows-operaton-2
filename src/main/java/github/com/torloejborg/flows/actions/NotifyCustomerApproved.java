package github.com.torloejborg.flows.actions;

import org.operaton.bpm.engine.delegate.DelegateExecution;
import org.operaton.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("NotifyCustomerApproved")
public class NotifyCustomerApproved implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Your order was approved!");
    }
}
