package event.processing;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@Configuration
@EnableScheduling
public class ApplicationConfig implements SchedulingConfigurer {

    private final static int MAX_DELAY = 120 * 1000;

    private final static int MIN_DELAY = 1 * 1000;

    private static Date lastExecution = new Date(), execution = new Date();

    private static Status lastStatus = new Status();

    @Autowired
    private ApplicationScheduler applicationScheduler;

    @Autowired
    private Status status;

    @Bean(destroyMethod = "shutdown")
    public Executor applicationExcutor() {
        return Executors.newScheduledThreadPool(1);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(applicationExcutor());
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                lastExecution.setTime(execution.getTime());
                lastStatus.setCurrent(status.getCurrent());
                execution = new Date();
                applicationScheduler.carryOutActivity();
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {

                long difference = execution.getTime() - lastExecution.getTime();

                if (!status.getCurrent().equals(lastStatus.getCurrent())) {

                    /**
                     * If status has changed, immediately carry out next step.
                     */
                    difference = 1000l;

                } else if (status.getCurrent().equals(STATUS_TYPE.REGISTER_EP)) {
                    /**
                     * Add one time unit as long as device tries to register.
                     * 
                     */

                    if (difference < STATUS_TYPE.REGISTER_EP.getDelay() * 1000l) {
                        difference = 0;
                    }

                    difference += (STATUS_TYPE.REGISTER_EP.getDelay() * 1000l);

                } else {
                    /**
                     * If device is in working or error status, carry out task periodically according specification.
                     */
                    difference = status.getCurrent().getDelay() * 1000l;
                }

                /**
                 * Check boundaries
                 */

                difference = (difference > MAX_DELAY) ? MAX_DELAY : difference;

                difference = (difference < MIN_DELAY) ? MIN_DELAY : difference;

                return new Date(execution.getTime() + difference);
            }
        });

    }
}
