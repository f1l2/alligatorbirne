package event.processing.engine.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.lang.query.QueryLang;
import common.lang.rule.RuleLang;
import event.processing.engine.LanguageTransformer;

@Component
public class EsperEPLTransformer implements LanguageTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperEPLTransformer.class);

    private EPLBuilder builder;

    @Override
    public List<String> transformQuery(QueryLang query) {

        if (CollectionUtils.isEmpty(query.collectAggregateCondition())) {
            this.accept(new EPLBuilderSingle());
        } else {
            this.accept(new EPLBuilderMultiple());
        }

        List<String> epls = builder.createEPL(query);
        epls.forEach(epl -> logger.info("Generated EPL: {}", epl));

        return builder.createEPL(query);
    }

    @Override
    public List<String> transformQuery(List<QueryLang> queries) {

        List<String> nativeQueries = new ArrayList<String>();
        for (QueryLang query : queries) {
            nativeQueries.addAll(transformQuery(query));
        }
        return nativeQueries;
    }

    @Override
    public List<String> transformRule(RuleLang rule) throws IOException {

        List<String> epls = transformQuery(rule.getQueries());

        epls.addAll(transformRulePuristic(rule));

        return epls;
    }

    @Override
    public List<String> transformRulePuristic(RuleLang rule) throws IOException {

        List<String> epls = new ArrayList<String>();

        /**
         * If rule is triggered by a sequence of queries another epl statement is needed.
         */

        if (rule.getQueries().size() > 1) {

            this.accept(new EPLBuilderSequence());

            List<String> eplSequence = builder.createEPL(rule.getQueries());

            epls.addAll(eplSequence);
            epls.forEach(epl -> logger.info("Generated EPL: {}", eplSequence));
        }

        return epls;
    }

    public void accept(EPLBuilder builder) {
        this.builder = builder;
    }
}
