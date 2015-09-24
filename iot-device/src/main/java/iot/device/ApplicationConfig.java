package iot.device;

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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;

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

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

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
                     * If status is changed, immediately carry out next step.
                     */
                    difference = 1000l;

                } else if (status.getCurrent().equals(STATUS_TYPE.REGISTER_DEVICE)) {
                    /**
                     * Add one time unit as long device tries to register.
                     */

                    if (difference < STATUS_TYPE.REGISTER_DEVICE.getDelay() * 1000) {
                        difference = 0;
                    }

                    difference += (STATUS_TYPE.REGISTER_DEVICE.getDelay() * 1000);

                } else if (status.getCurrent().equals(STATUS_TYPE.REGISTER_DATA_SOURCES)) {

                    /**
                     * Add one time unit as long device tries to register.
                     */

                    if (difference < STATUS_TYPE.REGISTER_DATA_SOURCES.getDelay() * 1000) {
                        difference = 0;
                    }

                    difference += (STATUS_TYPE.REGISTER_DATA_SOURCES.getDelay() * 1000);

                } else {
                    /**
                     * If device is in working or error status, carry out task periodically according specification.
                     */
                    difference = status.getCurrent().getDelay();
                }

                /**
                 * Check boundaries
                 */

                difference = (difference > MAX_DELAY) ? MAX_DELAY : difference;

                difference = (difference < MIN_DELAY) ? MIN_DELAY : difference;

                System.out.println(difference);

                return new Date(execution.getTime() + difference);
            }
        });

    }
}
