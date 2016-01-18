package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.dto.RuleDTO;
import common.transformer.Transformer;
import configuration.management.model.RuleDLO;

@Component
public class RuleTransformer extends Transformer<RuleDLO, RuleDTO> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    final static DeviceTransformer deviceTransformer = new DeviceTransformer();
    
    @Override
    public RuleDLO toLocal(RuleDTO remote) {
        RuleDLO item = new RuleDLO();
        item.setName(remote.getName());
        item.setRule(remote.getRule());

        return item;
    }

    @Override
    public RuleDTO toRemote(RuleDLO local) {
	
        RuleDTO item = new RuleDTO();
        item.setName(local.getName());
        item.setRule(local.getRule());

        return item;
    }

}
