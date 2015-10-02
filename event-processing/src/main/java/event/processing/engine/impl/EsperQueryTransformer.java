package event.processing.engine.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;

@Component
public class EsperQueryTransformer implements QueryTransformer {

    private static final Logger logger = LoggerFactory.getLogger(EsperQueryTransformer.class);

    private EPLBuilder builder;

    @Override
    public List<String> transform(Query query) {

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
    public List<String> transform(String in) throws IOException {

        logger.info("Start query transformation. Input: {}", in);

        Query query = null;

        QueryFactory queryFactory = new QueryFactory();
        query = queryFactory.parse(in);

        return transform(query);

    }

    public void accept(EPLBuilder builder) {
        this.builder = builder;
    }
}
