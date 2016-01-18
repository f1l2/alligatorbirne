package configuration.management.selection;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.transformer.Transformer;
import configuration.management.model.EventProcessingDLO;
import configuration.management.repo.EventProcessingRepository;

@Component
public class MinNumberOfActiveRules implements Selectable {

    @Autowired
    private EventProcessingRepository eventProcessingRepository;

    @Override
    public Optional<EventProcessingDLO> select() {

        List<EventProcessingDLO> allEPs = Transformer.makeCollection(eventProcessingRepository.findAll());

        if (CollectionUtils.isEmpty(allEPs)) {

            return Optional.empty();
        }

        Optional<EventProcessingDLO> selected = Optional.of(allEPs.get(0));

        for (int i = 1; i < allEPs.size(); i++) {

            if (allEPs.get(i).getNumberOfActiveRules() > selected.get().getNumberOfActiveRules()) {
                selected = Optional.of(allEPs.get(i));
            }
        }

        return selected;
    }
}
