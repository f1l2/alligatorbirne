package configuration.management.rest.activity.call;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.RuleRepository;
import configuration.management.rest.activity.Activity;

public abstract class CallActivity<T1, T2> extends Activity<T1, T2> {

    @Autowired
    protected RuleRepository ruleRepository;

    @Autowired
    protected EventProcessingRepository eventProcessingRepository;

    @Autowired
    protected DeviceRepository deviceRepository;

    @Autowired
    protected DeviceTransformer deviceTransformer;

    @Autowired
    protected ThreadPoolTaskExecutor taskExecutor;

    protected RestTemplate restTemplate = new RestTemplate();

}
