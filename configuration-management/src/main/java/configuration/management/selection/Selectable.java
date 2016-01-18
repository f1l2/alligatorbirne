package configuration.management.selection;

import java.util.Optional;

import configuration.management.model.EventProcessingDLO;

@FunctionalInterface
public interface Selectable {

	public Optional<EventProcessingDLO> select(); 
	
}
