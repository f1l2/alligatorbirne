package common.lang.rule;

import common.data.dto.RuleDTO;
import common.transformer.Transformer;

public abstract class AbstractRuleLangTransformer extends Transformer<RuleLang, RuleDTO> {

    @Override
    public RuleLang toLocal(RuleDTO remote) {
        RuleLang item = new RuleLang();
        item.setIsActivated(remote.getIsActive());
        item.setName(remote.getName());

        return item;
    }

    @Override
    public RuleDTO toRemote(RuleLang local) {

        RuleDTO item = new RuleDTO();
        item.setIsActive(local.getIsActivated());
        item.setRule(local.getNativeRule());
        item.setName(local.getName());

        return item;
    }

}
