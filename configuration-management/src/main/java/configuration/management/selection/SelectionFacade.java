package configuration.management.selection;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import common.selection.DISTRIBUTION_STRATEGY;
import configuration.management.model.EventProcessingDLO;

@Component
public class SelectionFacade {

    @Autowired
    private MinNumberOfActiveRules minNumberOfActiveRules;

    @Autowired
    private MinCpuUtilization minCpuUtilization;

    @Autowired
    private MinRAMUtilization minRAMUtilization;

    public Optional<EventProcessingDLO> select(DISTRIBUTION_STRATEGY strategy) {

        if (DISTRIBUTION_STRATEGY.MIN_ACTIVATED_RULES.equals(strategy)) {
            return minNumberOfActiveRules.select();
        } else if (DISTRIBUTION_STRATEGY.MIN_CPU_UTILIZATION.equals(strategy)) {
            return minCpuUtilization.select();
        } else if (DISTRIBUTION_STRATEGY.MIN_RAM_UTILIZATION.equals(strategy)) {
            return minRAMUtilization.select();
        }
        return Optional.empty();
    }

}
