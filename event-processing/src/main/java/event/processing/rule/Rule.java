package event.processing.rule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import event.processing.engine.EngineListener;
import event.processing.query.Query;
import event.processing.rule.model.Reaction;

public class Rule extends EngineListener {

    private static final Logger logger = LoggerFactory.getLogger(Rule.class);

    /**
     * Enum Keywords
     */
    public static enum KEYWORD {
        TRIGGERS("triggers");

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

    private List<String> queryNames;

    private List<Query> queries;

    private List<Reaction> reactions;

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public void addQuery(Query query) {
        if (null == this.queries) {
            this.queries = new ArrayList<Query>();
        }
        this.queries.add(query);
    }

    public List<String> getQueryNames() {
        return queryNames;
    }

    public void setQueryNames(List<String> queryNames) {
        this.queryNames = queryNames;
    }

    public void addQueryName(String queryName) {
        if (null == this.queryNames) {
            this.queryNames = new ArrayList<String>();
        }
        this.queryNames.add(queryName);
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

        logger.info("Rule is triggered. Reaction: {}", this.reactions.get(0).toString());

        try {

            Connection local = SettingUtils.getLocalConnection();
            local.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            Connection cm = SettingUtils.getCMConnection();

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, cm);

            RestTemplate restTemplate = new RestTemplate();

            for (Reaction reaction : reactions) {
                CDBuilder cDBuilder = new CDBuilder();
                cDBuilder.buildDeviceInformation(reaction.getDeviceInformation())
                        //
                        .buildDomainInformation(reaction.getDomainInformation())
                        //
                        .buildConfigurationModification(local, reaction.getConfigurationModification());

                restTemplate.postForEntity(url, cDBuilder.getResult(), String.class);
            }

        } catch (Exception ex) {
            logger.error("Error telling CM that Rule was triggered. Rule: {}", this.toString());
        }

    }

}
