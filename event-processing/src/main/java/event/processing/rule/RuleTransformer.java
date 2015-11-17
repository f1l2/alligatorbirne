package event.processing.rule;

import org.springframework.stereotype.Component;

import common.data.dto.RuleDTO;
import common.transformer.Transformer;

@Component
public class RuleTransformer extends Transformer<Rule, RuleDTO> {

    @Override
    public Rule toLocal(RuleDTO remote) {
        Rule item = new Rule();
        item.setIsActivated(remote.getIsActive());
        item.setName(remote.getName());

        return item;
    }

    @Override
    public RuleDTO toRemote(Rule local) {

        RuleDTO item = new RuleDTO();
        item.setIsActive(local.getIsActivated());
        item.setRule(local.toString());
        item.setName(local.getName());

        return item;
    }

}
