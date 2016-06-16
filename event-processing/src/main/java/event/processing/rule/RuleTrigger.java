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

public class RuleTrigger implements GenericListener {

    private static final Logger logger = LoggerFactory.getLogger(RuleTrigger.class);

    private RestTemplate restTemplate = new RestTemplate();

    private RuleLang ruleLang;

    private Connection local;

    private Connection cm;

    public RuleTrigger(RuleLang ruleLang) {
        this.ruleLang = ruleLang;
    }

    @Override
    public void trigger() {

        logger.info("Rule is triggered. Reaction: {}", ruleLang.getReactions().get(0).toString());
        loadConnections();

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DELEGATION, cm);

            for (Reaction reaction : ruleLang.getReactions()) {
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

    private void loadConnections() {
        try {
            local = SettingUtils.getLocalConnection();
            local.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            cm = SettingUtils.getCMConnection();
        } catch (Exception e) {
            logger.error("Error retrieving settings.");
        }
    }

    public RuleLang getRuleLang() {
        return ruleLang;
    }

    public void setRulerLang(RuleLang ruleLang) {
        this.ruleLang = ruleLang;
    }
}
