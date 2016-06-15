package configuration.management.rest.activity.receive;

import org.springframework.beans.factory.annotation.Autowired;

import configuration.management.repo.DeviceRepository;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.RuleRepository;
import configuration.management.rest.activity.Activity;

public abstract class ReceiveActivity<T1, T2> extends Activity<T1, T2> {

    @Autowired
    protected RuleRepository ruleRepository;

    @Autowired
    protected EventProcessingRepository eventProcessingRepository;

    @Autowired
    protected DeviceRepository deviceRepository;

}
