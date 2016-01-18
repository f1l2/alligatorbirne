package event.processing.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.lang.rule.GenericListener;
import common.lang.rule.RuleLang;
import common.lang.rule.model.Reaction;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;

public class RuleEP extends RuleLang implements GenericListener {

    private static final Logger logger = LoggerFactory.getLogger(RuleEP.class);

    @Override
    public void trigger() {

        logger.info("Rule is triggered. Reaction: {}", this.reactions.get(0).toString());

        try {

            Connection local = SettingUtils.getLocalConnection();
            local.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            Connection cm = SettingUtils.getCMConnection();

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DELEGATION, cm);

            RestTemplate restTemplate = new RestTemplate();

            for (Reaction reaction : this.getReactions()) {
                CDBuilder cDBuilder = new CDBuilder();
                cDBuilder.addDataSource(reaction.getDeviceInformation(), reaction.getDomainInformation())//
                        .buildDataSink(local)//
                        .buildProperties(reaction.getConfigurationModification());

                restTemplate.postForEntity(url, cDBuilder.getResult(), String.class);
            }

        } catch (Exception ex) {
            logger.error("Error telling CM that Rule was triggered. Rule: {}", this.toString());
        }

    }
}
