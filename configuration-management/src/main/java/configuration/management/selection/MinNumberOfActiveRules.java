package configuration.management.selection;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        Optional<EventProcessingDLO> max = allEPs.stream().min((ep1, ep2) -> new Integer(ep1.getNumberOfActiveRules()).compareTo(ep2.getNumberOfActiveRules()));

        return max;
    }
}
