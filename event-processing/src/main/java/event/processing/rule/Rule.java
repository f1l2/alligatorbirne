package event.processing.rule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationDelegation;
import common.data.ConfigurationModification;
import common.data.Connection;
import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.config.UtilsConfiguration;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import event.processing.engine.EngineListener;
import event.processing.rule.model.Reaction;

public class Rule extends EngineListener {

    /**
     * Enum Keywords
     */
    public static enum KEYWORD {
        TRIGGERS("TRIGGERS");

        private String keyword;

        KEYWORD(String keyword) {
            this.setKeyword(keyword);
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

    private Boolean isActivated = false;

    private String query;

    private List<Reaction> reactions;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Reaction> getReactions() {

        if (null == reactions) {
            reactions = new ArrayList<Reaction>();
        }

        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    @Override
    public void trigger() {

        try {

            Connection local = UtilsConfiguration.getLocalConnection();
            Connection cm = UtilsConfiguration.getCMConnection();

            String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, cm);

            RestTemplate restTemplate = new RestTemplate();

            for (Reaction reaction : reactions) {
                restTemplate.postForEntity(url, getConfiguraitonDelegation(reaction, local), Connection.class);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private ConfigurationDelegation getConfiguraitonDelegation(Reaction reaction, Connection local) {

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName(reaction.getDeviceInformation());
        DomainInformation domainInformation = new DomainInformation();
        domainInformation.setName(reaction.getDomainInformation());
        ConfigurationModification configuraitonModification = new ConfigurationModification();
        configuraitonModification.setName(reaction.getConfigurationModification());
        configuraitonModification.setDataSink(local);

        ConfigurationDelegation cd = new ConfigurationDelegation();
        cd.setDeviceInformation(deviceInformation);
        cd.setDomainInformation(domainInformation);
        cd.setConfigurationModification(configuraitonModification);

        return cd;
    }
}
