package iot.device.rest.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import iot.device.delivery.DynamicDeliveryTaskFactory;
import iot.device.repo.DeliveryTaskRepositoryImpl;

public abstract class IoTActivity<T1, T2> extends Activity<T1, T2> {

    @Autowired
    protected DeliveryTaskRepositoryImpl deliveryTaskRepository;

    @Autowired
    protected ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    protected DynamicDeliveryTaskFactory ddtf;
}
