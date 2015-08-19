package event.processing.engine.impl;

import event.processing.engine.QueryTransformer;

public class EsperQueryTransformer extends QueryTransformer {

    @Override
    public String transform(String query) {
        return query;
    }

}
