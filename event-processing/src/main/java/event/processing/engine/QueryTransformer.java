package event.processing.engine;

import event.processing.query.Query;

public abstract class QueryTransformer {

    public abstract String transform(String query);

    public abstract String transform(Query query);

}
