package event.processing.engine.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import event.processing.engine.LanguageTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.rule.Rule;
import event.processing.rule.RuleFactory;

@Component
public class EsperEPLTransformer implements LanguageTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperEPLTransformer.class);

    private EPLBuilder builder;

    @Autowired
    private QueryFactory queryFactory;

    @Autowired
    private RuleFactory ruleFactory;

    @Override
    public List<String> transformQuery(Query query) {

        if (CollectionUtils.isEmpty(query.collectAggregateCondition())) {
            this.accept(new EPLBuilderSingle(query));
        } else {
            this.accept(new EPLBuilderMultiple(query));
        }

        List<String> epls = builder.createEPL(query);
        epls.forEach(epl -> logger.info("Generated EPL: {}", epl));

        return builder.createEPL(query);
    }

    @Override
    public List<String> transformQuery(String in) throws IOException {

        logger.info("Start query transformation. Input: {}", in);

        Query query = queryFactory.parse(in);

        return transformQuery(query);

    }

    @Override
    public List<String> transformRule(String in) throws IOException {

        logger.info("Start rule transformation. Input: {}", in);

        Rule rule = ruleFactory.parse(in);

        return transformRule(rule);
    }

    @Override
    public List<String> transformRule(Rule rule) throws IOException {
        return transformQuery(rule.getQuery());
    }

    public void accept(EPLBuilder builder) {
        this.builder = builder;
    }
}
